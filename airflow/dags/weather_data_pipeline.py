from airflow import DAG
from airflow.providers.docker.operators.docker import DockerOperator
from datetime import datetime, timedelta
import json

default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 2,
    'retry_delay': timedelta(minutes=5),
    'execution_timeout': timedelta(minutes=5),
    'pool_slots': 1,
}

# Load countries configuration at DAG definition time
config_path = '/opt/airflow/dags/config/countries.json'
with open(config_path, 'r') as f:
    countries = json.load(f)

# Load environment variables from .env file
env_vars = {}
env_file_path = '/opt/airflow/etl-service/.env'
with open(env_file_path, 'r') as f:
    for line in f:
        line = line.strip()
        if line and not line.startswith('#') and '=' in line:
            key, value = line.split('=', 1)
            env_vars[key] = value

# Limits: 3 req/sec, 25 req/hour, 500 req/day
# Strategy: Run once per day at 2 AM, fetch all 24 cities
# Rate limiting: 3 parallel tasks, spaced to stay under 25/hour
with DAG(
    'weather_data_pipeline',
    default_args=default_args,
    description='Fetch weather data once daily (3 req/sec limit, 24 cities)',
    schedule='0 2 * * *',  # Once daily at 2 AM
    start_date=datetime(2026, 1, 14),
    catchup=False,
    tags=['weather', 'etl', 's3'],
    max_active_runs=1,
    max_active_tasks=3,  # Max 3 tasks running at same time (3 req/sec)
) as dag:

    # For each country and city, create tasks
    all_tasks = []
    
    for country_code, cities in countries.items():
        for city_name, coordinates in cities.items():
            # Clean task ID
            task_id = f'{country_code}_{city_name}'.replace(' ', '_').replace('-', '_')
            
            # Task: Call Java container to fetch and upload weather data
            fetch_weather = DockerOperator(
                task_id=task_id,
                image='weather-etl:latest',
                api_version='auto',
                auto_remove='success',
                docker_url='unix://var/run/docker.sock',
                network_mode='bridge',
                environment={
                    **env_vars,  # Spread all env vars from .env file
                    'COUNTRY': country_code,
                    'CITY': city_name,
                    'LATITUDE': str(coordinates['lat']),
                    'LONGITUDE': str(coordinates['lon']),
                },
                pool='weather_api_pool',
            )
            
            all_tasks.append(fetch_weather)

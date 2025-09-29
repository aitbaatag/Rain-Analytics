# Rain Analytics 
This project is a data analytics pipeline for rainfall and weather monitoring. It collects, processes, and transforms weather data from APIs and public datasets to provide insights on rainfall patterns, extreme weather events, and trends over time. The goal is to enable data-driven decisions for environmental analysis, agriculture planning, and urban water management 
 
## project structure 
```
rain-analytics/
├── README.md
├── .env                 # API keys, credentials
├── requirements.txt
├── Dockerfile           # Optional: container for Airflow + DBT
├── dags/
│   ├── all_project_dag.py
│   ├── extract_weather_dag.py
│   └── dbt_dag.py
├── src/
│   ├── __init__.py
│   ├── config.yaml
│   ├── extract/
│   │   ├── __init__.py
│   │   └── weather_api.py
│   ├── load/
│   │   ├── __init__.py
│   │   └── loader.py
│   ├── transform/
│   │   └── dbt_rain/
│   │       ├── dbt_project.yml
│   │       ├── profiles.yml.template
│   │       ├── models/
│   │       │   ├── bronze/
│   │       │   ├── silver/
│   │       │   └── gold/
│   │       ├── macros/
│   │       └── tests/
│   └── utils.py
├── data/                # Optional: CSV backups / sample datasets
└── .github/
    └── workflows/
        ├── ci.yaml
        └── cd.yaml

``` 

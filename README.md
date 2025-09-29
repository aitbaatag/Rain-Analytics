# Rain Analytics 
This project is a data analytics pipeline for rainfall and weather monitoring. It collects, processes, and transforms weather data from APIs and public datasets to provide insights on rainfall patterns, extreme weather events, and trends over time. The goal is to enable data-driven decisions for environmental analysis, agriculture planning, and urban water management 
 
## project structure 
```
rain-analytics/
│── README.md
│── docker-compose.yml        # Orchestrates multiple containers (Airflow + Java ETL + dbt)
│── .env                      # API keys, credentials
│
├── etl-service/              # Java service → fetch weather & upload to S3
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/main/java/com/example/weather/
│       ├── App.java
│       ├── config/Config.java
│       ├── model/WeatherData.java
│       ├── service/
│       │   ├── WeatherApiClient.java
│       │   ├── DataTransformer.java
│       │   └── S3Uploader.java
│       └── util/DateUtils.java
│   └── src/main/resources/cities.json
│
├── airflow/                  # Airflow scheduler + DAGs
│   ├── Dockerfile
│   ├── dags/
│   │   ├── all_project_dag.py
│   │   ├── extract_weather_dag.py
│   │   └── dbt_dag.py
│   └── requirements.txt      # Airflow + AWS SDK libs
│
├── dbt-transform/            # dbt project container
│   ├── Dockerfile
│   ├── dbt_project.yml
│   ├── profiles.yml.template
│   ├── models/
│   │   ├── bronze/
│   │   ├── silver/
│   │   └── gold/
│   ├── macros/
│   └── tests/
│
├── data/                     # Optional: local CSV/JSON backups
│
└── .github/workflows/
    ├── ci.yaml                # Run tests, build JAR, lint dbt
    └── cd.yaml                # Deploy containers

``` 

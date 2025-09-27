# Public Transport Analytics 
 This project provides a comprehensive analytics platform for public transport data, enabling detailed insights into transit performance, delays, and usage patterns. Raw data from various sources, including APIs and real-time feeds, is collected and processed to create a structured, clean dataset that supports advanced reporting and analysis.

The project tracks metrics such as average delays, service reliability, and route performance over time, allowing users to identify trends, evaluate system efficiency, and make data-driven decisions. Historical and current data are integrated to offer both real-time insights and long-term performance analysis.

The platform supports automated data workflows, ensuring that new information is continuously incorporated into the analytics layer while maintaining data quality and consistency. The resulting datasets can be used for reporting, visualization, and operational planning to improve public transport services 

## project structure 
```
public-transport-analytics/
├── README.md                 # Project description and instructions
├── .gitignore                # Git ignore rules
├── docker-compose.yml        # Containers for Postgres/S3, Airflow, dbt, app
├── dbt_project/              # dbt project for transformations
│   ├── models/               # SQL models
│   │   ├── staging/          # Staging models (raw → clean)
│   │   ├── marts/            # Business-level models (analytics)
│   │   └── tests/            # dbt data quality tests
│   ├── macros/               # dbt macros for reusable SQL
│   ├── seeds/                # CSV seeds if needed
│   ├── snapshots/            # Historical snapshots
│   ├── dbt_project.yml
│   └── profiles.yml.template
├── airflow/                  # Airflow DAGs and configs
│   ├── dags/
│   │   ├── etl_raw_data.py
│   │   ├── dbt_transformations.py
│   │   └── quality_checks.py
│   ├── plugins/
│   └── airflow.cfg
├── src/                      # Custom extraction/loading scripts
│   ├── extract/              # Pull data from APIs, GPS, ticketing
│   ├── load/                 # Load data into S3/Postgres landing zone
│   └── utils/                # Helper functions
├── tests/                    # Unit/integration tests for Python scripts
├── requirements.txt          # Python dependencies
└── .env                      # Environment variables for secrets
``` 

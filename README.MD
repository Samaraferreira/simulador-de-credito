# Simulador de Crédito


## Como executar?

### Requisitos
- Gradle
- AWS CLI
- Docker

### Executar os seguintes comandos

1. Container com DynamoDB
```
docker-compose up -d
```

2. Criar tabela no DynamoDB
```
./scripts/create-dynamodb-table.sh
```

3. Executar aplicação
```
./gradlew bootRun
```

## Explicação sobre a estrutura do projeto e decisões de arquitetura


![Clean Arch](.github/assets/clean-architecture.png)

O projeto segue princípios do Clean Architecture, buscando manter o domínio de negócio isolado, sem dependência das camadas mais externas.
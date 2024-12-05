# Trading Data Consumer Service

This springboot application serves as consumer of trading data.Apparently Trading data
has been generated and pushed to rabbitMQ, here we are consuming messages from
queue,and persisting into postgresDb.

### Features

- Trading Data has been consumed and stored in postgres by batch size of 30
- API has been exposed with pageable feature.

### Technologies Used

- **Spring Boot 3.x**
- **Java 17**
- **rabbitMQ**
- **Docker**
- **Maven**
- **Lombok**
- **OpenCsv**
- **postgres**
- **jackson**

### Setup and Installation

- ** https://start.spring.io **

### Docker Set up for Postgres Db: used docker desktop

```bash
docker run --name cryptoTradingDb -e POSTGRES_USER=<****> -e POSTGRES_PASSWORD=<****> -e POSTGRES_DB=cryptoTradingDb -e POSTGRES_MAX_CONNECTIONS=10 -p 5440:5432 -d postgres:latest
```

```
GET- http://localhost:12350/api/v1/crypto-trade-data?page=0&size=10&sort=conversionPair,asc

RESPONSE:
{
  "content": [
    {
      "transactionId": "72d5d399-088a-45ce-88a7-6ae747f9686b",
      "conversionPair": "ETH-USD",
      "tradeType": "SELL",
      "tradeCountry": "US",
      "tradePrice": 273.7310715808,
      "exchangeRate": 280.5434525218,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "bd4c53d8-9bbf-4b3b-809f-75c9d165344e",
      "conversionPair": "ETH-USD",
      "tradeType": "SELL",
      "tradeCountry": "IN",
      "tradePrice": 340.2776075387,
      "exchangeRate": 223.2664520326,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "c83fb31f-f818-444a-b59d-6bce1b884187",
      "conversionPair": "ETH-USD",
      "tradeType": "SELL",
      "tradeCountry": "JP",
      "tradePrice": 319.2803185233,
      "exchangeRate": 139.2252745332,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "e4927e29-318e-479e-9924-f90d9530c39a",
      "conversionPair": "ETH-USD",
      "tradeType": "SELL",
      "tradeCountry": "JP",
      "tradePrice": 150.3216457472,
      "exchangeRate": 422.2978862368,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "c410848d-978d-475d-a0a8-9f2d43d4bf35",
      "conversionPair": "ETH-USD",
      "tradeType": "BUY",
      "tradeCountry": "JP",
      "tradePrice": 276.3937786859,
      "exchangeRate": 358.7983185046,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "9843c569-85d3-4335-80a0-8b6863f44d9b",
      "conversionPair": "ETH-USD",
      "tradeType": "BUY",
      "tradeCountry": "IN",
      "tradePrice": 437.2619729042,
      "exchangeRate": 476.0895131129,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "c2646783-afbf-492e-bb82-c0037b17c2f5",
      "conversionPair": "ETH-USD",
      "tradeType": "BUY",
      "tradeCountry": "IN",
      "tradePrice": 279.3353065726,
      "exchangeRate": 269.6201397378,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "615334ce-ed3a-4239-bbe5-68c38ee50abb",
      "conversionPair": "ETH-USD",
      "tradeType": "SELL",
      "tradeCountry": "IN",
      "tradePrice": 331.8154533822,
      "exchangeRate": 156.3032273372,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "0ab2f363-d9dd-4bca-8abd-9871c5f32eb9",
      "conversionPair": "ETH-USD",
      "tradeType": "SELL",
      "tradeCountry": "US",
      "tradePrice": 349.6094778021,
      "exchangeRate": 136.082376389,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    },
    {
      "transactionId": "13990c9b-5f51-4359-b4ee-a64e2e8c03b8",
      "conversionPair": "ETH-USD",
      "tradeType": "BUY",
      "tradeCountry": "JP",
      "tradePrice": 372.3446860356,
      "exchangeRate": 455.1025513091,
      "reportCreateDate": "2024-12-06T00:01:04.644523",
      "approved": true
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": false,
  "totalElements": 1290,
  "totalPages": 129,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 10,
  "empty": false
}
```
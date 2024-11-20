# The product spotter challenge

## Description

this challenge aims to highlight the devolopers ability to understand and implement business requirements, requiring in the process communication, analytical thinking and a moderate technical knowledge base.

## Prompt

create a RESTful API that has the responsibility of analizing pricing data of a product, and spotting places where it could have been bought and sold to make a profit. the service doesn't require any prediction models!


**Please keep in mind the performance of the algorithm, and take into consideration optimizing for both Space and Time Complexity!**

### The input

historical pricing data about a product like:

```json
{
  "pricingHistory": [
    {
      "sku": "1",
      "name": "spoon",
      "prices": [
        {"date": "07-10-2023", "price": 3.75},
        {"date": "13-10-2023", "price": 18},
        {"date": "23-10-2023", "price": 9},
        {"date": "01-11-2023", "price": 33},
        {"date": "07-11-2023", "price": 14},
        {"date": "12-11-2023", "price": 7}
      ]
    }
  ]
}
```

### The output

A list of actions showing where we should have bought and sold

```json
{
  "actions": [
    {
      "sku": "1",
      "type": "BUY",
      "date": "07-10-2023"
    },
    {
      "sku": "1",
      "type": "SELL",
      "date": "13-10-2023"
    },
    {
      "sku": "1",
      "type": "BUY",
      "date": "23-10-2023"
    },
    {
      "sku": "1",
      "type": "SELL",
      "date": "01-11-2023"
    }
  ]
}
```

notice that with these actions we make a profit of 38.25$ instead of just 29.25$ if we bought once on `07-10-2023` and sold on `01-11-2023`


## the tasks

 - Implement the RESTful API, validate the input, process it and return the appropriate response. please keep in mind clean coding standards and preferably use AOP.
 - Use appropriate status codes for the successful and faulty responses and validation issues.
 - Write unit tests for both the business logic parts of the code (the function or service that does the processing)
 - Dockerize the service.

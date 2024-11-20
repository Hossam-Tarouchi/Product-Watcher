# The product spotter challenge

## Description

this challenge aims to highlight the devolopers ability to understand and implement business requirements, requiring in the process communication, analytical thinking and a moderate technical knowledge base.

## Prompt

You are making a product storage service, this service holds information about the products and their historical market prices.

It has four parts:
 - The product itself, containing the following fields: SKU, name.
 - The prices, containing the fields: SKU (referencing the product), price, date.
 - A RESTful controller that permits inserting this data.
 - A Cron Job that periodically checks for opportunities.


### The cron job

the cron job checks the prices database each Friday at 00:00 GMT, loads up the prices for each product of the past 3 months, and tries to find places where the product could have been bought and sold to make the most profit.

Then it pushes a message to a (Kafka/RabbitMQ) exchange or a Pub/Sub to notify other consumers of this information. (we don't care who the consumers are as far as our service publishes the message)

**Please keep in mind the performance of the algorithm, and take into consideration optimizing for both Space and Time Complexity!**


#### The input

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

#### The output

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

 - Make the SQL migrations for the product and prices tables.
 - Implement the ProductController which has one method to create a new product
 - Implement the PriceController which has two methods:
   - A method to create a new Price
   - A method to paginate through the Prices of a product ordered by date from newest to oldest
 - Implement input validation for the past two controllers
 - Use appropriate status codes for the successful and faulty responses and validation issues.
 - Implement the cron job and publish the result to an unnamed queue (it can be called a topic or exchange depending on product terminology)
 - write unit tests for the ProductController
 - Write unit tests for the CronJob handler
 - Dockerize the service.
 - Implement a CI/CD pipelive using GitlabCI to build and test the project automatically
 - Push the code to a Gitlab repo and make it publically accessible


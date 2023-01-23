# rewardpoints
/*****************************************/
Class Name: TestDataController.java

End Point : /testdata/{count}/{noofCustomers} 
Description: this endpoint gives random transactions for given count (no of trasactions) and
no of Customers (customer id)
/*****************************************/

/*****************************************/
Class Name: Transaction.java
Class to store each transaction informaction i.e, (customerId, transactionAmont,
rewardsPoints, transactionDate}

customerId: Id of the customer
transactionAmont: Amount of the trasaction
rewardsPoints: Rewards points computed for this trasaction
transactionDate: date of the trasaction
/*****************************************/

/*****************************************/
Class Name: RewardsPointsController.java
End Point: /report
Input : transactionData (list of transactions) as JSON Array
Output: Rewards Points of last three months and total points for each customer ID

Description: 

1) Gets the last three months startdate and end date

Reads all the transactions for each element in the transactionData 
and map with customer Id. 
Checks transaction date with last three months period if trasaction falls in 
above period it will update points in the hashmap for the correspoinding customer id and
total computed rewardspoints for the sample.


Sample Input
[{"transactionAmount":79,"customerId":3,"transactionDate":"2022-11-21"},
{"transactionAmount":87,"customerId":3,"transactionDate":"2022-11-26"},
{"transactionAmount":91,"customerId":3,"transactionDate":"2022-12-31"},
{"transactionAmount":66,"customerId":2,"transactionDate":"2023-01-14"},
{"transactionAmount":143,"customerId":3,"transactionDate":"2023-01-14"},
{"transactionAmount":104,"customerId":1,"transactionDate":"2023-01-12"},
{"transactionAmount":73,"customerId":3,"transactionDate":"2022-12-04"},
{"transactionAmount":63,"customerId":5,"transactionDate":"2022-12-17"},
{"transactionAmount":88,"customerId":2,"transactionDate":"2022-11-27"},
{"transactionAmount":77,"customerId":4,"transactionDate":"2023-01-21"}]

Output:

{
    "1": {
        "TOTAL": 58,
        "DECEMBER": 58,
        "OCTOBER": 0,
        "NOVEMBER": 0
    },
    "2": {
        "TOTAL": 54,
        "DECEMBER": 16,
        "OCTOBER": 38,
        "NOVEMBER": 0
    },
    "3": {
        "TOTAL": 266,
        "DECEMBER": 136,
        "OCTOBER": 66,
        "NOVEMBER": 64
    },
    "4": {
        "TOTAL": 27,
        "DECEMBER": 27,
        "OCTOBER": 0,
        "NOVEMBER": 0
    },
    "5": {
        "TOTAL": 13,
        "DECEMBER": 0,
        "OCTOBER": 0,
        "NOVEMBER": 13
    }
}

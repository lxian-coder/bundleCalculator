# BundleCalculator Introduction
***
This Bundle Calculator can accept your order and calculate the bundles and money based on the submission format for you.

## How to use this calculator
***
### How to input order？
***
*  Run this application and the submission format form will pop up and you can enter order according to the information of the form.
* When you enter an order, you need to put format code behind the number of posts like: "number of posts"  "format code".for example: 10  img, means you want to order 10 Images.
* The order is case insensitive and you can click ENTER twice to finish you order.
* You can order multiple items at one time like "10 img 12 flac 23 vid", or
  "
  10 img
  12 flac
  23 void
  "
* If you enter wrong format code, incomplete order, or something else wrong with your order, calculator will feed back you instructions and allow you re-enter the order.
* If the number of posts you ordered can not be bundled without remainder, calculator will deduct the remainder from your order and add an minimal bundle to your order and then calculate the numbers of bundles and money. But do not worry, calculator will feed back you details of information and you can choose to change you order.
### How to change submission format?
***
* It is easy change the default submission format, you can find a "DataBoostrap" class and a "loadData" method defined in it. All calculation operated by the calculator depend on the default data initialized by the "DataBoostrap" class. You can easily upgrade it to change the submission format.

## Advantages of this application
***
* Lombok makes our life easier. It automatically generates getter,setter,constructor, hashcode,log etc.
* It is user friendly. It is easy for user to enter order. It is case insensitive and you can order one item or more items as you want. You can write you order in one line or series lines.
* If there is something wrong with the order submitted by users, this calculator will check the problem and feed back users with details instruction.
* This application put much importance on maintainable. It is easy to change the submission format by update the "Databoostrap" class.

## Tech Stack:
***
* SpringBoot 5
* Lombok
* Slf4j
* Java 8
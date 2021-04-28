# BundleCalculator Introduction

This Bundle Calculator can accept your order and calculate the bundles and money based on the submission format for you.

## How to use this calculator

### How to input order？

* Run this application, and the submission format form will pop up, and you can enter an order according to the information of the form.
* You need to put format code behind the number of posts like: "number of posts"  "format code".for example: 10  img, means you want to order 10 Images.
* You need to click ENTER twice to finish you order, and the order you entered is case-insensitive.
* You can order multiple items at one time like "10 img 12 flac 23 vid", or
  "
  10 img <br>
  12 flac <br>
  23 void <br>
  "
* If you enter wrong format code, incomplete order, or something else wrong with your order, calculator will feed back you instructions and allow you re-enter the order.
* If the number of posts you entered can not be bundled without remainder, calculator will deduct the remainder from your order and add a minimal bundle to it and then calculate the numbers of bundles and money. Of course, you will get all details of the change.
### How to change submission format?

* It is easy to change the default submission format, you can find a "DataBoostrap" class, and a "loadData" method defined in it. All calculation operated by the calculator depends on the default data initialized by the "DataBoostrap" class. You can easily update it to change the submission format.

## Advantages of this application

* Lombok makes our life easier. It automatically generates getter,setter,constructor, hashcode,log etc.
* User-friendly. It is easy for user to enter order. It is case-insensitive, and you can order one item or more items as you want. You can write you order in one line or series lines.
* Mistype prevention.If there is something wrong with the order submitted by users, this calculator will check the problem and feed back users with details instruction.
* Maintainable. This application put much importance on maintainable. It is easy to change the submission format by update the "Databoostrap" class.

## Tech Stack:

* SpringBoot 5
* Lombok
* Slf4j
* Java 8
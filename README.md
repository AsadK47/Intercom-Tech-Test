# Intercom-Tech-Test

## Instructions for the tech test

We have some customer records in a text file (customers.txt) -- one customer per line, JSON lines formatted. We want to invite any customer within 100km of our Dublin office for some food and drinks on us. 

Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).

● You must use the first formula from this Wikipedia article to calculate distance. Don't forget, you'll need to convert degrees to radians.

● The GPS coordinates for our Dublin office are 53.339428, -6.257664.

● You can find the Customer list here.

We're looking for you to produce working code, with enough room to demonstrate how to structure components in a small program. Good submissions are well composed. Calculating distances and reading from a file are separate concerns. Classes or functions have clearly defined responsibilities. 

Poor submissions will be in the form of one big function. It’s impossible to test anything smaller than the entire operation of the program, including reading from the input file.

## Opening project in an IDE
If you are opening the project in an IDE such as intellij, please do so by opening the build.gradle file
which will enable intellij to open the files as a project and enable the correct structure, download dependencies, etc.

## How to run the unit tests
### For all of the following operations please make sure you have JDK 8 installed and gradle version 5 or above

There are 2 ways to be able to run the test:
1. Run ./gradlew test in the terminal
2. (Recommended) Open the APITests in an IDE such as Intellij and click the run button
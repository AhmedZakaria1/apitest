## Please follow the below steps to set up and run the test.
## The project is gradle based.

1. As prerequisite, make sure you have git,java and gradle installed on your machine. e.g java version "1.8.0_221" , Gradle 5.4.1 ,git version 2.16.2.windows.1

2. Clone the repository in either Windows/Mac/Linux 
git clone https://github.com/AhmedZakaria1/apitest.git

3. Once repository is cloned, it can be imported as a gradle project in any of IDE's or can be run directly from command line.

4. The test is written as a JUnit class.The test basically checks the output (json response) from a url and validates three aspects.
Please refer the comments in  JUnit test APITest for details of validation criteria.

5. From the directory where you cloned,cd to project root folder  (cd apitest)

6. On windows/linux , assuming you have gradle and java installed , run below command to compile, resolve dependencies and execute the JUnit test. Note: The test may not run in private/VPN networks as the request could time out due to proxy settings.In such cases, use public network to run the test. 

Command : gradle test --tests APITest

7. The test results that get generated can be checked under 
<clonedDir>\apitest\build\reports\tests\test\index.html
<clonedDir>\apitest\build\test-results\test\TEST-api.tests.APITest.xml

Information about source code.
The JUnit test APITest is under package api.tests.
The utilities have been written in Utils class in package com.api.test.Third party library jackson has been used to parse json.


## Please follow the below steps to set up and run the test.

1. As prerequisite, make sure you have git,java and gradle installed on your machine. e.g java version "1.8.0_221" , Gradle 5.4.1 ,git version 2.16.2.windows.1

2. Clone the repository in either Windows/Mac/Linux 
git clone https://github.com/AhmedZakaria1/apitest.git

3. Once repository is cloned, it can be imported as a gradle project in any of IDE's or can be run directly from command line.

4. The test is written as a JUnit class.

5. From the directory where you cloned,cd to project root folder  (cd apitest)

6. In windows/linux , assuming you have gradle and java installed , run below command to compile, resolve dependencies and running the JUnit test. Note: The test needs to be run in public network as proxy could be set in private/VPN networks and test would fail.

Command : gradle test --tests APITest

7. The test results can be checked under 
<clonedDir>\apitest\build\reports
<clonedDir>\apitest\build\test-results

Information about source code.
The JUnit test APITest is under package api.tests.
The utilities have been written in Utils class in package com.api.test


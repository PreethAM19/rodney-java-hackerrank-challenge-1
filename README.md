#Rodney-HackerRank-Java-Backend-Challenge

### Quick Start
```$xslt
1. mvn clean spring-boot:run (run application,defaults to http://localhost:8000)
OR 
2. mvn clean install (INSTALL)
OR 
3. mvn clean test; cat target/customReports/result.txt (Run dynamic tests)
or 
4. mvn test (run all tests in test directory)
```

### Run With Docker 
```$xslt

1. mvn clean package (clean package)

2. docker build -t rodney-hackerrank-java-app .

3. docker run -p 8000:8000 rodney-hackerrank-java-app (application will be live on http://127.0.0.1:8000

You can type : docker images (to show docker images )
```


### Folder Structure

    
                            
    .                                    
    ├──src                               # Source files 
    ├─├--main                            # Main Folder 
    ├─├--├--java                         # Java Folder
    ├─├--├----com.hackerrank.github      # Package name 
    ├-├--├-------comparator              # Comparators for sorting lists ,Highest streak comparators and others 
    ├-├--├-------config                  # App global configuration for date and other app-wide config
    ├-├--├-------controller              # Events and Actors Api controllers
    ├-├--├-------datautil                # Non-database entity models used in app
    ├-├--├-------exception               # Exceptions package,SameDayException and other custom exceptions.
    ├-├--├-------model                   # Database entity models
    ├-├--├-------repository              # CrudRepository and all database repositories are here
    ├-├--├-------service                 # Service classes for feeding various controllers are here.
    ├-├--├-------util                    # DateUtil singleton class,for date calculations and other date manipulations.
    ├-├--├----------Application          # Base Application class that runs app
    ├-├--├resources                      # App configuration resources eg application.properties is here.
    ├-├--├test                           # Test Folder where all Tests reside
    ├-├--├--java
    ├-├--├--├--com.hackerrank.github     
    ├-├--├--├-----ComparatorTest         # Comparator custom unit tests
    ├-├--├--├-----HttpJsonDynamicUnit    # Dynamic Unit Tests from json files
    ├-├--├--resources                    # Http json test files are here
    ├-├target

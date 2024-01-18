
# PayMyBuddy

Openclassrooms Project6

## Livrables:
- Le diagramme de classe UML:  

![Seghdau_Walid_1_uml_012024](https://github.com/walid938/paymybuddy/assets/60928838/3018dc81-8d5d-49ef-bd94-a88e89409a9f)


- Le modèle physique de données:  


![modele conceptuel de données](https://github.com/walid938/paymybuddy/assets/60928838/c430366c-0365-4299-8f9f-0fbb554b1d07)



### Interface web:
- Login:


![Capture d'écran 2024-01-15 183915](https://github.com/walid938/paymybuddy/assets/60928838/8a1fd7c4-b34d-46e2-acd9-23dc67f501c1)

- Home:


![Capture d'écran 2024-01-15 184009](https://github.com/walid938/paymybuddy/assets/60928838/dd601158-5ce5-4589-b243-ccaac08ae096)

#### Note: 
Une fois un compte a été créé, il faut mettre au moins 1€ dedans pour activer le compte.

- Pour démarrer l'application, ajouter `application.properties` sous `resources`:
```properties
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/pay_my_buddy?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&characterEncoding=utf8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.open-in-view=false
server.error.whitelabel.enabled=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false
```

#### Jacoco test couverture sur la couche du service:   

![Capture d'écran 2024-01-15 184908](https://github.com/walid938/paymybuddy/assets/60928838/029cf35f-2571-403b-96ee-b85e74cc2a30)





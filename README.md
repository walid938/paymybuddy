
# PayMyBuddy

Openclassrooms Project6

## Livrables:
- Le diagramme de classe UML:  

![diagrammeUml](https://github.com/walid938/paymybuddy/assets/60928838/bc6810ab-30bd-4d90-a87f-eebbbe29d68f)

- Le modèle physique de données:  

![Diagramme de table](https://github.com/walid938/paymybuddy/assets/60928838/f1fd7d42-91c2-4fbb-88a5-53a93d32c01d)


### Interface web:
- Login:


![Capture d'écran 2024-01-15 183915](https://github.com/walid938/paymybuddy/assets/60928838/8a1fd7c4-b34d-46e2-acd9-23dc67f501c1)

- Home:


![Capture d'écran 2024-01-15 184009](https://github.com/walid938/paymybuddy/assets/60928838/dd601158-5ce5-4589-b243-ccaac08ae096)


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


#### Jacoco test couverture sur la couche du service:   

![Capture d'écran 2024-01-15 184908](https://github.com/walid938/paymybuddy/assets/60928838/029cf35f-2571-403b-96ee-b85e74cc2a30)





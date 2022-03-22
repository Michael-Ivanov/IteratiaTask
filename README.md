# Конвертер валют #
Tестовое задание для Iteratia
## Сборка и настройка ## 
1. Приложение использует для работы базу данных Postgres с названием **iterdb**, подключаясь к ней под именем пользователя **iteruser**. 
Перед запуском приложения, база данных и пользователь должны существовать. 
    + Для созднания можно использовать скрипт из директории **SQLscript**, запустив его от имени пользователя, который имеет права на создание баз и других пользователей. 
      Скрипт создает пользователя **iteruser** с паролем **iteruser**, базу данных **iterdb**, предоставляет пользователю **iteruser** все права на базу **iterdb**.
    + Либо использовать существующую базу данных и пользователя, заменив название базы, имя пользователя и пароль
      в **src/main/resources/application.properties** в блоке **datasource connection properties** на существующие перед сборкой приложения.
2. Приложение собирается со всеми зависимостями при помощи Maven стандартным образом: 
```
mvn package
```
4. Приложение запускается стандартным образом:
```
java -jar ./target/IteratiaTask-1.0.0.jar
```
 + Сервер работает на порту 8888
 + При первом запуске приложения в базе данных создаются необходимые для работы таблицы  и наполняются данными со страницы **http://www.cbr.ru/scripts/XML_daily.asp**

5. Клиентская часть приложения находится в директории **client** и запускается файлом **index.html**
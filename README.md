1. Загрузить проект
2. Подключить Maven
3. Необходимо в PostgreSQL создать новую БД "ArdasDB", порт 5432, логин = "postgres", пароль = "root"
4. Создание таблицы с по схеме "_schema.sql"
5. Тестовые данные будут заполняться автоматически из файла "data.sql"
6. Включить автоимпорт настроек pom.xml
7. Запусить класс Application.java

Доступная страница - http://localhost:8080/hello/

Возможные маппинги: 

/contactsAll - возвращает список контактов

/contactsById - с ключом "id" возвращает соответствующий Контакт

/contacts - с ключом "nameFilter" возвращает список Контактов, которые не попадают под регулярное выражение, написанное в паре с ключом

Для решения проблемы частого доступа к БД и возможного возврата большого количества данных я решил кэшировать их в ConcurrentHashMap.
Эту структуру я использовал также для решения проблемы множественных запросов.
В задании не было отдельно сказано, откуда может вестись работа с БД. Поэтому с выбором ConcurrentHashMap подразумевается, 
что работа с БД невозможна из других источников кроме данного приложения, иначе Map не будет реагировать на изменения.

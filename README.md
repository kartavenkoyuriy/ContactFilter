1. Загрузить проект
2. Необходимо в PostgreSQL создать новую БД "ArdasDB", порт 5432, логин = "postgres", пароль = "root"
3. Для создания таблицы использовать "\initial\schema.sql"
4. Тестовые данные будут заполняться автоматически из файла "data.sql"
5. Запусить метод main в классе Application.java

Доступная страница - http://localhost:8080/hello/

Возможные маппинги: 

/contacts - с ключом "nameFilter" возвращает список Контактов, которые не попадают под регулярное выражение, написанное в паре с ключом

/contactsAll - возвращает список контактов

/contactsById - с ключом "id" возвращает соответствующий Контакт

/addContact - POST-метод для добавления нового контакта

/updateContact - PUT-метод для обновления контакта

/deleteContact - DELETE-метод для удаления контакта

Для решения проблемы частого доступа к БД и возможного возврата большого количества данных я решил кэшировать их в ConcurrentHashMap.
Эту структуру я использовал также для решения проблемы множественных запросов.
В задании не было отдельно сказано, откуда может вестись работа с БД, поэтому я решил сделать полноценный сервис, который позволяет производить все CRUD-операции с БД.
Минус данной реализации в том, что кэш перечитывается только после перезапускаприложения.

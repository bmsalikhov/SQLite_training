# Простой проект для освоения навыков работы с SQLite

## Dependencies

Использовал [sqlite-jdbc](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.44.1.0)

## Что может делать приложение?
- создавать базу данных
- создавать таблицу
- добавлять записи в таблицу
- выводить записи согласно заданию

## Структура БД
![Untitled](https://github.com/bmsalikhov/SQLite_training/assets/153372291/c3d92740-839e-468d-b7a6-a27b1470caab)


## Packages
### data_source
Здесь хранится класс TableGenerator, в котором реализованы основные методы работы с БД.
#### Методы
- void createTable(String tableName) - создает таблицу
- void insertAll(List<Employee> employeeList, String tableName) - заносит записи с "работниками" в ранее созданную таблицу
- void selectAS(String tableName) - выводит записи из таблицы с псевдонимами согласно заданию
- void selectUPPER(String tableName) - выводит имена "работников" в верхнем регистре
- void selectId(String tableName) - выводит id "работников"
- void selectSUBSTRING(String tableName) - выводит первые три буквы имен работников
- void selectLIMIT(String tableName) - выводит первые 5 записей
- void printSelectResult(String query, ResultSet resultSet, ResultSetMetaData resultSetMetaData, int ... columns) - метод для печати записей
### models
Здесь хранится класс Employee, созданный для создания сущности "работник" для последующей записи её в базу данных.
### main
Здесь хранится класс Main, в котором мы создаем список "работников" и далее тестируем описанные выше методы.

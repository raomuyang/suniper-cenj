## SUNIPER-CENJ

[![Build Status](https://travis-ci.org/suniper/suniper-cenj.svg?branch=master)](https://travis-ci.org/suniper/suniper-cenj) [![Coverage Status](https://coveralls.io/repos/github/suniper/suniper-cenj/badge.svg?branch=master)](https://coveralls.io/github/suniper/suniper-cenj?branch=master)

> Crud Enhancer is Not a JPA framework.

This is a tool that can be auto-generated SQL from method name,
you can define a series of interfaces and hand over the work of creating
an instance to CENJ

### Quick start
* Maven dependency:
```xml
<dependency>
    <groupId>cn.suniper.cenj</groupId>
    <artifactId>suniper-cenj</artifactId>
    <version>0.2.0</version>
</dependency>
```

* Define interface:
```java
interface Operator {
    Entity findById(String id);
    void deleteByName(String name);
    // ... 
}
```
* Implement your SQLExecutor and create a RepositoryFactory, get an instance by factory:
```java
SQLExecutor executor = <Your Implement of SQLExecutor>;
String tableName = <Your table name>;
RepositoryFactory factory = new RepositoryFactory(executor, table);

Operator operator = factory.getRepositoryInstance(Operator.class);
operator.deleteByName("test");
...
```




### Some examples: method name to SQL
``` 
"deleteByA" => "DELETE * FROM TEST WHERE A = ?"
"queryTop10ByA" => "SELECT TOP 10 FROM TEST WHERE A = ?"
"queryTop10ByAAndB" => "SELECT TOP 10 FROM TEST WHERE A = ? AND B = ?"
"countByA" => "SELECT COUNT(*) FROM TEST WHERE A = ?"
"find" => "SELECT * FROM TEST"
"findAll" => "SELECT * FROM TEST"
"findByAGreaterThan" => "SELECT * FROM TEST WHERE A > ?"
"findByAGreaterThanEquals" => "SELECT * FROM TEST WHERE A >= ?"
"findByALessThan" => "SELECT * FROM TEST WHERE A < ?"
"findByALessThanEquals" => "SELECT * FROM TEST WHERE A <= ?"
"findByABefore" => "SELECT * FROM TEST WHERE A < ?"
"findByAAfter" => "SELECT * FROM TEST WHERE A > ?"
"findByANot" => "SELECT * FROM TEST WHERE A <> ?"
"findByANotIn" => "SELECT * FROM TEST WHERE A NOT IN (?)"
"findByAIn" => "SELECT * FROM TEST WHERE A IN (?)"
"findByAaBbStartingWith" => "SELECT * FROM TEST WHERE AABB LIKE '?%'"
"findByAContaining" => "SELECT * FROM TEST WHERE A LIKE '%?%'"
"findByAIsTrue" => "SELECT * FROM TEST WHERE A IS TRUE"
"findByAOrderByB" => "SELECT * FROM TEST WHERE A = ? ORDER BY B"
"findName" => "SELECT NAME FROM TEST"
"findNameOrderByCreatedTs" => "SELECT NAME FROM TEST ORDER BY CREATEDTS"
"deleteTop10" => "DELETE TOP 10 FROM TEST"
```

---

> Welcome to create issue or contact us when you have any problems, thanks
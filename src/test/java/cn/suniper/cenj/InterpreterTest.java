package cn.suniper.cenj;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Rao Mengnan
 *         on 2018/8/27.
 */
public class InterpreterTest {
    @Test
    public void getSql() throws Exception {
        String tableName = "test";

        String methodName = "deleteByA";
        String except = "DELETE * FROM TEST WHERE A = ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "find";
        except = "SELECT * FROM TEST";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "queryTop10ByA";
        except = "SELECT TOP 10 FROM TEST WHERE A = ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "queryTop10ByAAndB";
        except = "SELECT TOP 10 FROM TEST WHERE A = ? AND B = ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "countByA";
        except = "SELECT COUNT(*) FROM TEST WHERE A = ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByAGreaterThan";
        except = "SELECT * FROM TEST WHERE A > ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByAGreaterThanEquals";
        except = "SELECT * FROM TEST WHERE A >= ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByALessThan";
        except = "SELECT * FROM TEST WHERE A < ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByALessThanEquals";
        except = "SELECT * FROM TEST WHERE A <= ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByABefore";
        except = "SELECT * FROM TEST WHERE A < ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByAAfter";
        except = "SELECT * FROM TEST WHERE A > ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByANot";
        except = "SELECT * FROM TEST WHERE A <> ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByANotIn";
        except = "SELECT * FROM TEST WHERE A NOT IN (?)";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByAIn";
        except = "SELECT * FROM TEST WHERE A IN (?)";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByAaBbStartingWith";
        except = "SELECT * FROM TEST WHERE AABB LIKE '?%'";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByAContaining";
        except = "SELECT * FROM TEST WHERE A LIKE '%?%'";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByAIsTrue";
        except = "SELECT * FROM TEST WHERE A IS TRUE";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findByAOrderByB";
        except = "SELECT * FROM TEST WHERE A = ? ORDER BY B";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "deleteTop10";
        except = "DELETE TOP 10 FROM TEST";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findAll";
        except = "SELECT * FROM TEST";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "findName";
        except = "SELECT NAME FROM TEST";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());


        methodName = "findNameOrderByCreatedTs";
        except = "SELECT NAME FROM TEST ORDER BY CREATEDTS";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "updateNameAndValueById";
        except = "UPDATE TEST SET NAME = ? AND VALUE = ? WHERE ID = ?";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "update";
        except = "UPDATE TEST SET {UPDATE_PARAMS}";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());

        methodName = "insert";
        except = "INSERT INTO TEST ({INSERT_PARAMS}) VALUES ({INSERT_VALUES})";
        assertEquals(except, new Interpreter(methodName, tableName).getSQL());
    }

}
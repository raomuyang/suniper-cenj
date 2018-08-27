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
        String except = "DELETE * FROM TEST WHERE A = ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "find";
        except = "SELECT * FROM TEST ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "queryTop10ByA";
        except = "SELECT TOP 10 FROM TEST WHERE A = ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "queryTop10ByAAndB";
        except = "SELECT TOP 10 FROM TEST WHERE A = ? AND B = ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "countByA";
        except = "SELECT COUNT(*) FROM TEST WHERE A = ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByAGreaterThan";
        except = "SELECT * FROM TEST WHERE A > ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByAGreaterThanEquals";
        except = "SELECT * FROM TEST WHERE A >= ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByALessThan";
        except = "SELECT * FROM TEST WHERE A < ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByALessThanEquals";
        except = "SELECT * FROM TEST WHERE A <= ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByABefore";
        except = "SELECT * FROM TEST WHERE A < ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByAAfter";
        except = "SELECT * FROM TEST WHERE A > ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByANot";
        except = "SELECT * FROM TEST WHERE A <> ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByANotIn";
        except = "SELECT * FROM TEST WHERE A NOT IN (?) ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByAIn";
        except = "SELECT * FROM TEST WHERE A IN (?) ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByAaBbStartingWith";
        except = "SELECT * FROM TEST WHERE AABB LIKE '?%' ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByAContaining";
        except = "SELECT * FROM TEST WHERE A LIKE '%?%' ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByAIsTrue";
        except = "SELECT * FROM TEST WHERE A IS TRUE ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());

        methodName = "findByAOrderByB";
        except = "SELECT * FROM TEST WHERE A = ? ORDER BY B = ? ";
        assertEquals(except, new Interpreter(methodName, tableName).getSql());
    }

}
package cn.suniper.cenj;

import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author Rao Mengnan
 *         on 2018/8/30.
 */
public class MethodParserTest {
    @Test
    public void testGenerateSqlFromJPA() throws Exception {
        Method[] methods = Operator.class.getMethods();
        MethodParser parser;
        for (Method m : methods) {
            switch (m.getName()) {
                case "update":
                    parser = new MethodParser(m);
                    assertEquals("UPDATE TEST SET age = ? AND name = ? WHERE studentId = ?", parser.getSQL());
                    break;
                case "insert":
                    parser = new MethodParser(m);
                    assertEquals("INSERT INTO TEST (age, name, studentId) VALUES (?,?,?)", parser.getSQL());
                    break;
                case "updateAgeByStudentId":
                    parser = new MethodParser(m, "test");
                    assertEquals("UPDATE TEST SET AGE = ? WHERE STUDENTID = ?", parser.getSQL());
                    break;
                case "find":
                    parser = new MethodParser(m, "test");
                    assertEquals("SELECT * FROM TEST", parser.getSQL());
                    break;
                case "select":
                    // unsupported generate from parameters
                    parser = new MethodParser(m, "test");
                    assertEquals("SELECT * FROM TEST", parser.getSQL());
                    break;
                default:
                    parser = new MethodParser(m, "test");
                    System.out.println(parser.getSQL());
            }
        }
    }

    interface Operator {
        void update(TestTable entity);

        void insert(TestTable entity);

        void updateAgeByStudentId(String age, String studentId);

        void find();

        void select(String name, int age);
    }

    @Table(name = "test")
    @Entity
    class TestTable {
        @Id
        private String studentId;
        private int age;
        private String name;
    }

}
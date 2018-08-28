package cn.suniper.cenj;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author Rao Mengnan
 *         on 2018/8/28.
 */
public class RepositoryFactoryTest {

    @Test
    public void testRepositoryInstance() throws Exception {
        MockSQLExecutor executor = new MockSQLExecutor();
        String table = "test";
        RepositoryFactory factory = new RepositoryFactory(executor, table);
        SQLExpression instance = factory.getRepositoryInstance(SQLExpression.class);
        instance.deleteByFirstNameAndAge("testName", 12);

        assertEquals("DELETE * FROM TEST WHERE FIRSTNAME = ? AND AGE = ?", executor.sql);
        assertEquals(String.class, executor.returnType);
        assertArrayEquals(new Class[]{String.class, int.class}, executor.parameterTypes);
        assertArrayEquals(new Object[]{"testName", 12}, executor.parameters);

        instance.deleteAll();
        assertEquals(void.class, executor.returnType);
        assertArrayEquals(new Class[0], executor.parameterTypes);
        assertArrayEquals(new Object[0], executor.parameters);

        instance.queryAllOrderById();
        // TODO 泛型未处理
        assertEquals(List.class, executor.returnType);
        assertArrayEquals(new Class[0], executor.parameterTypes);
        assertArrayEquals(new Object[0], executor.parameters);
    }

    interface SQLExpression {
        String deleteByFirstNameAndAge(String name, int age);

        void deleteAll();

        List<String> queryAllOrderById();
    }

    static class MockSQLExecutor implements SQLExecutor {

        private String sql;
        private Class<?> returnType;
        private Object[] parameters;
        private Class<?>[] parameterTypes;

        @Override
        public <T> T execute(Class<T> returnType, String sql, Object[] parameters, Class<?>[] parameterTypes) {
            this.sql = sql;
            this.returnType = returnType;
            this.parameters = parameters;
            this.parameterTypes = parameterTypes;
            return null;
        }
    }

}
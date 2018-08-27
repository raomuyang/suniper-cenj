package cn.suniper.cenj;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public class RepositoryFactory implements MethodInterceptor {
    private static final Map<String, String> cachedSqlMap = new ConcurrentHashMap<>();

    private SqlExecutor executor;
    private String tableName;

    public RepositoryFactory(SqlExecutor executor, String tableName) {
        this.executor = executor;
        this.tableName = tableName;
    }

    public <T> T getRepositoryInstance(Class<?> clazz) {
        return getRepositoryInstance(clazz, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] parameters, MethodProxy methodProxy) throws Throwable {
        String key = tableName + "/" + method.getName();
        String sql = cachedSqlMap.computeIfAbsent(key, k -> new Interpreter(k, tableName).getSql());
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> returnType = method.getReturnType();
        return executor.execute(returnType, sql, parameters, parameterTypes);
    }

    @SuppressWarnings("unchecked")
    static <T> T getRepositoryInstance(Class<?> clazz, MethodInterceptor interceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(interceptor);
        return (T) enhancer.create();
    }

}

package cn.suniper.cenj;

/**
 * @author Rao Mengnan
 *         on 2018/8/24.
 */
public interface SqlExecutor {
    <T> T execute(Class<T> returnType, String sql, Object[] parameters, Class<?>[] parameterTypes);
}

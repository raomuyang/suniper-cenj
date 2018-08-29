/*
 * Copyright 2018 Suniper
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package cn.suniper.cenj;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Creating an instance by interface
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public class RepositoryFactory implements MethodInterceptor {
    private static final Map<String, String> cachedSqlMap = new ConcurrentHashMap<>();

    private SQLExecutor executor;
    private String tableName;

    public RepositoryFactory(SQLExecutor executor, String tableName) {
        this.executor = executor;
        this.tableName = tableName;
    }

    public <T> T getRepositoryInstance(Class<?> clazz) {
        return getRepositoryInstance(clazz, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] parameters, MethodProxy methodProxy) throws Throwable {
        String key = tableName + "/" + method.getName();
        String sql = cachedSqlMap.computeIfAbsent(key, k -> new Interpreter(method.getName(), tableName).getSQL());
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> returnType = method.getReturnType();
        return executor.execute(returnType, sql, parameters, parameterTypes);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getRepositoryInstance(Class<?> clazz, MethodInterceptor interceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(interceptor);
        return (T) enhancer.create();
    }

}

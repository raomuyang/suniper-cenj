package cn.suniper.cenj.crud;

import java.util.Iterator;
import java.util.List;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public interface UpdateExpression<T, ID> {
    T update(T entity);

    T updateById(T t, ID id);

    List<T> updateAll(Iterator<T> entities);
}

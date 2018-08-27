package cn.suniper.cenj.crud;

import java.util.Iterator;
import java.util.List;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public interface InsertExpression<T, ID> {
    T save(T entity);

    List<T> saveAll(Iterator<T> entities);
}

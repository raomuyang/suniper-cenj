package cn.suniper.cenj.crud;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public interface QueryExpression<T, ID> {
    long count();

    T findOne();

    T findById(ID id);

    Iterable<T> findAll();
}

package cn.suniper.cenj.crud;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public interface DeleteExpression<T, ID> {
    T deleteById(ID id);
}

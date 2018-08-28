package cn.suniper.cenj.example.crud;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public interface QueryExpression {
    long count();

    FakeEntity findOne();

    FakeEntity findById(String id);

    Iterable<FakeEntity> findAllOrderById();
}

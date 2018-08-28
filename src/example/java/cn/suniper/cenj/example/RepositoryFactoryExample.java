package cn.suniper.cenj.example;

import cn.suniper.cenj.RepositoryFactory;
import cn.suniper.cenj.SQLExecutor;
import cn.suniper.cenj.example.crud.DeleteExpression;
import cn.suniper.cenj.example.crud.FakeEntity;
import cn.suniper.cenj.example.crud.QueryExpression;


/**
 * @author Rao Mengnan
 *         on 2018/8/28.
 */
public class RepositoryFactoryExample {
    // add your executor
    static SQLExecutor executor;
    static String tableName;

    public static void main(String[] args) {
        RepositoryFactory factory = new RepositoryFactory(executor, tableName);
        DeleteExpression deleteOp = factory.getRepositoryInstance(DeleteExpression.class);
        deleteOp.deleteById("the id of entity");

        QueryExpression queryOp = factory.getRepositoryInstance(QueryExpression.class);
        Iterable<FakeEntity> list = queryOp.findAllOrderById();
        System.out.println(list);
    }
}

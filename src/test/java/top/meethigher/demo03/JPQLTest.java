package top.meethigher.demo03;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import top.meethigher.demo02.utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * JPQLTest
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/4/11
 */
public class JPQLTest {
    private static EntityManager em;
    private static EntityTransaction ts;
    @BeforeClass
    public static void beforeClass() throws Exception {
        em = JpaUtils.getEntityManager();
        ts = em.getTransaction();
        ts.begin();
        System.out.println("获取对象并开启事务");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        ts.commit();
        em.close();
        System.out.println("提交事务并释放对象");
    }

    /**
     * 查询全部
     */
    @Test
    public void testFindAll() {
        String jpql="from Customer";
        //创建Query对象，Query才是执行JPQL的对象
        Query query = em.createQuery(jpql);
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        //遍历
        for (Object o :
                resultList) {
            System.out.println(o);
        }
    }
    /**
     * 排序查询
     */
    @Test
    public void testFindOrById() {
        String jpql="from Customer order by custId desc ";
        //创建Query对象，Query才是执行JPQL的对象
        Query query = em.createQuery(jpql);
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        //遍历
        for (Object o :
                resultList) {
            System.out.println(o);
        }
    }

    /**
     * 统计查询
     */
    @Test
    public void testFindCount() {
        String jpql="select count(custId) from Customer";
        Query query = em.createQuery(jpql);
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);
    }

    /**
     * 分页查询
     */
    @Test
    public void testFindByPage() {
        String jpql="from Customer";
        Query query = em.createQuery(jpql);
        //分页查询
        //起始索引
        query.setFirstResult(1);
        //每页查询的条数
        query.setMaxResults(2);
        List resultList = query.getResultList();
        for (Object o :
                resultList) {
            System.out.println(o);
        }
    }
    /**
     * 条件查询
     */
    @Test
    public void testFindByCondition() {
        String jpql="from Customer  where custName like ?1";
        Query query = em.createQuery(jpql);
        //条件查询
        /**
         * 占位符参数
         * 第一个参数：占位符索引位置，从1开始
         * 第二个参数：取值
         */
        query.setParameter(1,"%美%");
        List resultList = query.getResultList();
        for (Object o :
                resultList) {
            System.out.println(o);
        }
    }
}

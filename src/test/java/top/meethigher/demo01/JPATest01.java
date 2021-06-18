package top.meethigher.demo01;

import org.junit.Test;
import top.meethigher.demo01.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JPATest01
 *
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/4/10
 */
public class JPATest01 {
    @Test
    public void testSave() {
        /*1. 加载配置文件创建工厂（实体管理类工厂）对象*/
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        /*2. 通过实体管理类工厂获取实体管理器*/
        EntityManager em = factory.createEntityManager();
        /*3. 获取事务对象，开启事务*/
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        /*4. 完成增删改查操作*/
        Customer customer=new Customer();
        customer.setCustName("美杜莎");
        customer.setCustIndustry("蛇人族");
        customer.setCustAddress("斗破苍穹");
        customer.setCustLevel("斗皇强者");
        customer.setCustPhone("保密");
        customer.setCustSource("保密");
        em.persist(customer);
        /*5. 提交事务（回滚事务）*/
        ts.commit();
        /*6. 释放资源*/
        em.close();
        factory.close();

    }
}

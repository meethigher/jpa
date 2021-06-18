package top.meethigher.demo02;

import org.junit.Test;
import top.meethigher.demo02.domain.Customer;
import top.meethigher.demo02.utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JPATest02
 * 因为使用了工具类，所以就不需要关闭工厂了，否则，下次再用获得的工厂就是个已经关闭的了
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/4/11
 */
public class JPATest02 {
    /**
     * 保存
     */
    @Test
    public void testSave() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        Customer customer=new Customer();
        customer.setCustName("美杜莎");
        customer.setCustIndustry("蛇人族");
        customer.setCustAddress("斗破苍穹");
        customer.setCustLevel("斗皇强者");
        customer.setCustPhone("保密");
        customer.setCustSource("保密");
        em.persist(customer);
        ts.commit();
        em.close();
    }

    /**
     * 查询
     */
    @Test
    public void testFind() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        /**
         * find：根据id查询，立即加载
         * getReference：根据id查询，通过代理实现懒加载
         */
//        Customer customer = em.find(Customer.class, 1L);//注意此处是1L，否则会报Long转Integer错误
        Customer customer = em.getReference(Customer.class, 1L);
        ts.commit();
        em.close();
    }

    /**
     * 删除
     * 先查再删
     */
    @Test
    public void testDelete() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        /**
         * 删除
         * 1. 先查
         * 2. 再删
         */
        Customer reference = em.getReference(Customer.class, 1L);
        em.remove(reference);
        ts.commit();
        em.close();
    }

    /**
     * 修改
     * 先查再改
     */
    @Test
    public void testUpdate(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        /**
         * 删除
         * 1. 先查
         * 2. 再删
         */
        Customer reference = em.getReference(Customer.class, 3L);
        reference.setCustName("胡列娜");
        em.merge(reference);
        ts.commit();
        em.close();
    }
}

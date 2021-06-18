package top.meethigher.demo02.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JpaUtils
 * 解决实体管理器工厂的浪费资源和耗时问题
 * 通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共的实体管理器工厂对象
 * @author kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/4/11
 */
public class JpaUtils {
    private static EntityManagerFactory myJpa;
    static{
        myJpa = Persistence.createEntityManagerFactory("myJpa");
    }
    /*获取EntityManager对象*/
    public static EntityManager getEntityManager(){
        return myJpa.createEntityManager();
    }
}

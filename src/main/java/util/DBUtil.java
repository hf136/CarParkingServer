package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wuyueqiu on 16-3-8.
 */
public class DBUtil {

    private static SqlSessionFactory factory;

    static{
        try{
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static SqlSession openSession(){
        return factory.openSession();
    }

    public static void closeSession(SqlSession session){
        if(session != null)
            session.close();
    }
}

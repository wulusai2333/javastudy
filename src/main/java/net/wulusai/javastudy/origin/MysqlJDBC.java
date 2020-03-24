package net.wulusai.javastudy.origin;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.origin
 * @ClassName: MysqlJDBC
 * @Description: 原生JDBC的mysql使用案例
 *  {@code @Component} 泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注
 *      {@code @Service} service层注解
 *      {@code @Controller} controller层注解
 *      (@code @Repository} dao层注解,确保DAO或者repositories提供异常转译，这个注解修饰的DAO或者repositories类会被ComponetScan发现并配置，同时也不需要为它们提供XML配置项
 *  {@code @Bean} 用@Bean标注方法等价于XML中配置的bean,将修饰的方法return的对象注入到容器中
 * @date: 2020/3/13 17:37
 */
@Component
public class MysqlJDBC {
    public void useOriginJDBC(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/javastudy?user=root&password=root&useUnicode=true&characterEncoding=utf-8mb4";
            Connection connection = DriverManager.getConnection("jdbc:mysql:///javastudy", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = (String)resultSet.getObject("name");
                System.out.println(name);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Connection getMysqlConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost/javastudy?user=root&password=root&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

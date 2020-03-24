package net.wulusai.javastudy;

import net.wulusai.javastudy.leetcode.SuperEggDrop;
import net.wulusai.javastudy.origin.MysqlJDBC;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class JavastudyApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 原生jdbc的使用
     * {@code @Autowired} 自动导入依赖的bean
     */
    @Autowired
    MysqlJDBC mysqlJDBC;

    @Test
    void testJDBC(){
        mysqlJDBC.useOriginJDBC();
        for(;;){

        }
    }

    @Test
    void testEggs(){
        for (int egg = 1; egg <= 10; egg++) {
            for (int floor = 1; floor <= 100; floor++) {
                int drop = SuperEggDrop.drop(egg, floor);
                System.out.print(drop+" \t");
            }
            System.out.println();
        }

    }

    @Test
    void testForEach(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }
        list.forEach((s)-> System.out.println(s));
//        HashMap<Integer, String> map = new HashMap<>();
//        map.forEach((k,v)->{});
        //枚举
        net.wulusai.constant.SeasumEnum se = net.wulusai.constant.SeasumEnum.STRING;
        System.out.println(se);
        System.out.println(se.getSeq());
    }
}

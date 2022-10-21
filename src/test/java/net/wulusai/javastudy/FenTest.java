package net.wulusai.javastudy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wulusai
 * @date 2021/3/8 16:27
 */
@SpringBootTest
public class FenTest {

    public static void main(String[] args) {
        Integer a =Integer.parseInt("1");
        Integer b =new Integer(1);
        System.out.println(a);
        System.out.println(b);
        System.out.println(a==b);
    }
}

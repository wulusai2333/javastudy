package net.wulusai.javastudy;

/**
 * @author wulusai
 * @date 2021/10/16 11:15
 */
public class JiTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int num = 10000;
        long ans = 0;
        for (int i = num; i > 0; i--) {
            for (int j = num / 3; j > 0; j--) {
                for (int k = num / 3 * 10; k > 0; k--) {
                    if (i + j * 3 + k * 3 / 10 <= num && i + j + k == num) {
                        ans += 1;
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(ans);
        System.out.println(end - start+"ms");
    }
}

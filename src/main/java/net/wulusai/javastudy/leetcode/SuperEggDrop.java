package net.wulusai.javastudy.leetcode;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.leetcode
 * @ClassName: SuperEggDrop
 * @Description: 双蛋问题,假设有n个鸡蛋从高m层楼落下,鸡蛋在>=楼层k时会碎,小于不会碎,求能测出k值的最小实验次数
 * @date: 2020/3/15 13:37
 */
public class SuperEggDrop {
    /**
     * time:O(nm) space:O(n)
     * @param egg
     * @param floor
     * @return
     */
    public static int drop(int egg,int floor){
        //总深度
        int[] dp =new int[floor+1];
        for (int i = 0; i <=floor ; i++) {
            dp[i]=i;
        }
        for (int k = 2; k <= egg; k++) {
            int[] dp2=new int[floor+1];
            int x=1;
            for (int n = 1; n <= floor; n++) {

                while(x<n&&Math.max(dp[x-1],dp2[n-x])>Math.max(dp[x],dp2[n-x-1])) {
                    x++;
                }
                    dp2[n] = 1 + Math.max(dp[x-1], dp2[n-x]);

            }
            dp = dp2;
        }

        return dp[floor];
    }
}

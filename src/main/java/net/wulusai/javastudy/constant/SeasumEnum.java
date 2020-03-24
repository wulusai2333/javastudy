package net.wulusai.javastudy.constant;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.constant
 * @ClassName: SeasumEnum
 * @Description:
 * @date: 2020/3/21 21:03
 */
public enum  SeasumEnum {
    /**
     * string 春
     */
    STRING(1),
    /**
     * summer 夏
     */
    SUMMER(2),
    /**
     * autumn 秋
     */
    AUTUMN(3),
    /**
     * winter 冬
     */
    WINTER(4);


    private int seq;
    SeasumEnum(int seq){
        this.seq=seq;
    }

    public int getSeq() {
        return seq;
    }

}

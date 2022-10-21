package net.wulusai.javastudy.designpattern.prototype;

/**
 * 原型模式
 * 用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象。性能上比new更快
 * Object类原型模式clone为浅拷贝
 * 如需深拷贝需要重写clone方法
 *
 * 缺点:
 * 违背了开闭原则
 * 深拷贝时需要为每个引用的类重写clone方法,代码复杂度较高时非常麻烦
 */

/**具体原型类**/
class RealizeType implements Cloneable {
    RealizeType() {
        System.out.println("具体原型创建成功！");
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return (RealizeType) super.clone();
    }
}
/**原型模式的测试类**/
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType obj1 = new RealizeType();
        RealizeType obj2 = (RealizeType) obj1.clone();
        System.out.println("obj1==obj2?" + (obj1 == obj2));
    }
}
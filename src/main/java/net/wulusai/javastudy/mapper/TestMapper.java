package net.wulusai.javastudy.mapper;


import net.wulusai.javastudy.pojo.Test;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.mapper
 * @ClassName: TestMapper
 * @Description: tk.mybatis.mapper.common.Mapper已经实现了一些基础方法,比如简单的curl和findAll
 *               它的使用需要传入一个序列化的类
 *               Repository //此注解并没有什么卵用,有了它service那编辑器不会提升错误,当然,没有也能正常运行
 *               在 starter 的逻辑中，如果你没有使用 @MapperScan 注解，你就需要在你的接口上增加 @Mapper 注解，否则 MyBatis 无法判断扫描哪些接口。
 *
 *               使用时如果默认方法不能满足需求,可以在方法上使用@Select
 * @date: 2020/3/16 14:32
 */
@Repository
public interface TestMapper  extends Mapper<Test> {

    @Select("select * from test where name=#{name} limit 1")
    Test selectName(String name);
}

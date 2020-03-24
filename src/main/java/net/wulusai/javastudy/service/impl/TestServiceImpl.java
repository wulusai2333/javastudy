package net.wulusai.javastudy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.wulusai.javastudy.mapper.TestMapper;
import net.wulusai.javastudy.pojo.Test;
import net.wulusai.javastudy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.service
 * @ClassName: TestService
 * @Description: service注解只能加在实体类上, 加在接口上没用
 * {@code @Autowired} 自动注入,将spring容器中对应的bean对象注入到此位置
 * @date: 2020/3/16 13:55
 */

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    /**
     * 调用方法计数+1
     *
     * @param test
     */
    @Override
    public Long get(Test test) {
        Test one = testMapper.selectOne(test);
        if (one != null) {

            one.setNumber(one.getNumber() + 1);
            testMapper.updateByPrimaryKey(one);
        } else {
            test.setNumber(1L);
            testMapper.insert(test);
        }
        return test.getNumber() + 1;
    }

    @Override
    public boolean put(Test test) {

        int i = testMapper.selectCount(test);
        if (i > 0) {
            return false;
        } else {
            testMapper.insert(test);
            return true;
        }

    }

    @Override
    public boolean update(Test test) {
        Test t = testMapper.selectByPrimaryKey(test.getId());
        if (t != null) {
            testMapper.updateByPrimaryKey(test);
            return true;
        } else {

            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        int i = testMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return true;
        } else {

            return false;
        }
    }

    @Override
    public Page<Test> allByPage(Integer pageNum, Integer pageSize) {
        //PageHelper对拦截Mybatis的sql语句
        PageHelper.startPage(pageNum,pageSize);
        //这里强转为Page对象,其实并没有改变实际的值,只是Page对象中的东西够多
        return (Page<Test>)testMapper.selectAll();
    }
}

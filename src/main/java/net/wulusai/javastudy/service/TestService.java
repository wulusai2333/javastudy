package net.wulusai.javastudy.service;

import com.github.pagehelper.Page;
import net.wulusai.javastudy.pojo.Test;
import org.springframework.stereotype.Service;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.service
 * @ClassName: TestService
 * @Description: service注解只是为了编辑器不提示报错,实际并不影响程序运行结果
 * @date: 2020/3/16 15:46
 */
@Service
public interface TestService {
    Long get(Test test);

    boolean put(Test test);

    boolean update(Test test);

    boolean delete(Long id);

    Page<Test> allByPage(Integer pageNum, Integer PageSize);
}

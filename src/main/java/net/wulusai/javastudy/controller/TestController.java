package net.wulusai.javastudy.controller;

import com.github.pagehelper.Page;
import net.wulusai.javastudy.pojo.Result;
import net.wulusai.javastudy.pojo.Test;
import net.wulusai.javastudy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.controller
 * @ClassName: TestController
 * @Description:
 *  {@code @RestController} 标记控制层组件
 *  {@code @CrossOrigin} 解决跨域请求问题
 *  {@code @RequestMapping} 请求的路径 路由信息到具体函数映射
 *  {@code @GetMapping} get请求
 *  {@code @PostMapping} put请求
 *  {@code @DeleteMapping} delete请求
 *  {@code @PathVariable} 获取参数
 *  {@code @RequestBody} 请求体参数
 * @date: 2020/3/16 13:54
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/{name}")
    public String get( @PathVariable String name){
        Test test = new Test();
        test.setName(name);
        Long number = testService.get(test);
        return "hello world! *"+number;
    }

    @PostMapping("/put")
    public String put(@RequestBody Test test){

        boolean result = testService.put(test);
        if (result){

        return "put success";
        }else {
            return "maybe exist";
        }
    }

    @PostMapping(value = "/{id}")
    public String update(@RequestBody Test test,@PathVariable Long id){
        test.setId(id);
        boolean result = testService.update(test);
        if (result){

            return "update success";
        }else {
            return "maybe not exist";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){

      boolean result=  testService.delete(id);
      if (result){
          return "delete success";
      }else {
          return "delete failed,maybe not exist";
      }
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public Result all(@PathVariable Integer pageNum, @PathVariable Integer pageSize){

        Page<Test> tests=testService.allByPage(pageNum,pageSize);

        return new Result(true,200,"success",tests);
    }

}

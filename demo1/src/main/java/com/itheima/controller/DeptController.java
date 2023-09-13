package com.itheima.controller;

import com.itheima.Service.DeptService;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {

//    注解 @Slf4j  可以省略以下内容
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)  //指定请求方式为get
//    与上述方式效果一致

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门数据
     * @return
     */
    @GetMapping
    public Result list(){
        List<Dept> deptlists =  deptService.list();
        log.info("查询全部部门数据");
        return Result.success(deptlists);
    }

    /**
     * 删除部门数据
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除{} 部门成功",id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门{}成功",dept.getName());
        deptService.add(dept);
        return Result.success();
    }

}

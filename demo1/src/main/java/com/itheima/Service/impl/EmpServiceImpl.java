package com.itheima.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.Service.EmpService;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        long total = empMapper.count();
//
//        Integer start = (page - 1)*pageSize;
//        List<Emp> lists = empMapper.page(start,pageSize);
//
//        PageBean pageBean = new PageBean(total,lists);
//        return pageBean;
//    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @Override
    public PageBean page(Integer page, Integer pageSize,
                         String name, Short gender,
                         LocalDateTime begin, LocalDateTime end){
        PageHelper.startPage(page,pageSize);
        List<Emp> lists = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) lists;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return  pageBean;
    }

    /**
     * 批量删除员工
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /**
     * 新增员工
     * @param emp
     */
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    /**
     * 查询回显
     * @param id
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

}

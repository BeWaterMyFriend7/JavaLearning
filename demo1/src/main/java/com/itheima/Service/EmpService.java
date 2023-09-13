package com.itheima.Service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDateTime begin, LocalDateTime end);

    /**
     * 批量删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    void add(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);
}

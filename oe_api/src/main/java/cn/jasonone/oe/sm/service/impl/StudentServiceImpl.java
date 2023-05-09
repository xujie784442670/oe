package cn.jasonone.oe.sm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.sm.domain.Student;
import cn.jasonone.oe.sm.service.StudentService;
import cn.jasonone.oe.sm.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author xujie
* @description 针对表【sm_student(学生表)】的数据库操作Service实现
* @createDate 2023-05-09 10:31:38
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}





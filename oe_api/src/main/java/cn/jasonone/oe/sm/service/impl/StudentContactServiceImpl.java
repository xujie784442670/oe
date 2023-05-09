package cn.jasonone.oe.sm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.sm.domain.StudentContact;
import cn.jasonone.oe.sm.service.StudentContactService;
import cn.jasonone.oe.sm.mapper.StudentContactMapper;
import org.springframework.stereotype.Service;

/**
* @author xujie
* @description 针对表【sm_student_contact(学生联系方式表)】的数据库操作Service实现
* @createDate 2023-05-09 10:31:38
*/
@Service
public class StudentContactServiceImpl extends ServiceImpl<StudentContactMapper, StudentContact>
    implements StudentContactService{

}





package cn.jasonone.oe.sm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.sm.domain.StudentEmergencyContact;
import cn.jasonone.oe.sm.service.StudentEmergencyContactService;
import cn.jasonone.oe.sm.mapper.StudentEmergencyContactMapper;
import org.springframework.stereotype.Service;

/**
* @author xujie
* @description 针对表【sm_student_emergency_contact(学生紧急联系人表)】的数据库操作Service实现
* @createDate 2023-05-09 10:31:38
*/
@Service
public class StudentEmergencyContactServiceImpl extends ServiceImpl<StudentEmergencyContactMapper, StudentEmergencyContact>
    implements StudentEmergencyContactService{

}





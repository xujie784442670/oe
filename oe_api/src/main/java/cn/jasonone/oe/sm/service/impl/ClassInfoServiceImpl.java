package cn.jasonone.oe.sm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.sm.domain.ClassInfo;
import cn.jasonone.oe.sm.service.ClassInfoService;
import cn.jasonone.oe.sm.mapper.ClassInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author xujie
* @description 针对表【sm_class_info(班级表)】的数据库操作Service实现
* @createDate 2023-05-09 10:31:38
*/
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo>
    implements ClassInfoService{

}





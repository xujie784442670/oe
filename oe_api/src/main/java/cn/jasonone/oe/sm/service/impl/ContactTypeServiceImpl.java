package cn.jasonone.oe.sm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.sm.domain.ContactType;
import cn.jasonone.oe.sm.service.ContactTypeService;
import cn.jasonone.oe.sm.mapper.ContactTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author xujie
* @description 针对表【sm_contact_type(联系方式类型表)】的数据库操作Service实现
* @createDate 2023-05-09 10:31:38
*/
@Service
public class ContactTypeServiceImpl extends ServiceImpl<ContactTypeMapper, ContactType>
    implements ContactTypeService{

}





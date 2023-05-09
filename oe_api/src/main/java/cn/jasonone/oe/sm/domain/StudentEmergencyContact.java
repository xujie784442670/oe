package cn.jasonone.oe.sm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 学生紧急联系人表
 * @TableName sm_student_emergency_contact
 */
@TableName(value ="sm_student_emergency_contact")
@Data
public class StudentEmergencyContact implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 关系
     */
    private String relationship;

    /**
     * 联系方式类型id
     */
    private Integer contactTypeId;

    /**
     * 联系方式
     */
    private String contact;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
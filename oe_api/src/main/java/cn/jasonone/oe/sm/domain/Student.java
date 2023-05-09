package cn.jasonone.oe.sm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 学生表
 * @TableName sm_student
 */
@TableName(value ="sm_student")
@Data
public class Student implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 账号id
     */
    private Integer accountId;

    /**
     * 班级id
     */
    private Integer classInfoId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 身份证
     */
    private String identityCard;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

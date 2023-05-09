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
 * 班级表
 * @TableName sm_class_info
 */
@TableName(value ="sm_class_info")
@Data
public class ClassInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 班级名
     */
    private String name;

    /**
     * 校区id
     */
    private Integer campusId;

    /**
     * 班主任账号id
     */
    private Integer principal;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 是否全日制: 0-否,1-是
     */
    private Integer fullTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

package cn.jasonone.oe.pms.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色表
 * @TableName pms_role
 */
@Data
@TableName(value = "pms_role")
public class Role implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}

package cn.jasonone.oe.pms.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限表
 * @TableName pms_permission
 */
@Data
@TableName(value = "pms_permission")
public class Permission implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 父id: 0-顶级权限
     */
    private Integer pid;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限编码
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

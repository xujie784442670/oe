package cn.jasonone.oe.pms.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 封禁表
 * @TableName pms_banned
 */
@Data
@TableName(value = "pms_banned")
public class Banned implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 账号id
     */
    private Integer accountId;

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 封禁原因
     */
    private String reason;

    /**
     * 封禁开始时间
     */
    private LocalDateTime startTime;

    /**
     * 封禁时长
     */
    private Long time;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}

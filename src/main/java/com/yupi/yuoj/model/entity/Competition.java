package com.yupi.yuoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 题目
 */
@Data
@TableName(value = "competition")
public class Competition implements Serializable {
    /**
     * id
     */
    @TableId(value = "competition_id", type = IdType.ASSIGN_ID)
    private Long competitionId;

    /**
     * 赛事名称
     */
    @TableField(value = "competition_name")
    private String competitionName;

    /**
     * 比赛时长
     */
    @TableField(value = "competition_duration")
    private Integer competitionDuration;

    /**
     * 创建人
     */
    @TableField(value = "competition_created_by")
    private Integer competitionCreatedBy;

    /**
     * 开始时间
     */
    @TableField(value = "competition_start_time")
    private Date competitionStartTime;

    /**
     * 赛事介绍
     */
    @TableField(value = "competition_context")
    private String competitionContext;

    /**
     * 是否删除
     */
    @TableField(value = "isDelete")
    private Byte isdelete;

    private static final long serialVersionUID = 1L;
}
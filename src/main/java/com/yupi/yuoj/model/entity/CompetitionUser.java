package com.yupi.yuoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 比赛用户表
 */
@Data
@TableName(value = "competition_user")
public class CompetitionUser implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 比赛id
     */
    @TableField(value = "competition_id")
    private Long competitionId;

    /**
     * 是否删除
     */
    @TableField(value = "isDelete")
    private Byte isdelete;

    private static final long serialVersionUID = 1L;
}
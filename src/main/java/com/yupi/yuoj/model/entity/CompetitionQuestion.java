package com.yupi.yuoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题目比赛表
 */
@Data
@TableName(value = "competition_question")
public class CompetitionQuestion implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 题目id
     */
    @TableField(value = "question_id")
    private Long questionId;

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
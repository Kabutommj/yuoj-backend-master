package com.yupi.yuoj.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserNameAndGameCountVO implements Serializable {

    private String userName;

    private Long gameCount;

    private int rank;

    private static final long serialVersionUID = 1L;
}

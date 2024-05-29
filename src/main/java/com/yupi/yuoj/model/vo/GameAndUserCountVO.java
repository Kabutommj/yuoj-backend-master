package com.yupi.yuoj.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameAndUserCountVO implements Serializable {

    private int rank;

    private String gameName;

    private Long userCount;

    private Long gameId;

    private static final long serialVersionUID = 1L;

}

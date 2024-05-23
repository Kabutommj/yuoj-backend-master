package com.yupi.yuoj.controller;

import com.yupi.yuoj.common.BaseResponse;
import com.yupi.yuoj.common.ResultUtils;
import com.yupi.yuoj.model.entity.Competition;
import com.yupi.yuoj.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/competition")
@Slf4j
public class CompetitionController {

    @Resource
    private CompetitionService competitionService;


    /**
     * 获取列表
     * @return
     */
    @RequestMapping("/getCompetitionList")
    public BaseResponse<List<Competition>> getCompetitionList() {

        return null;
    }

    /**
     * 获取详情
     * @return
     */
    @RequestMapping("/getCompetitionDetail")
    public BaseResponse<Competition> getCompetitionDetail(Long id) {
        Competition byId = competitionService.getById(id);
        return ResultUtils.success(byId);
    }



    /**
     * 创建
     * @return
     */
    @RequestMapping("/createCompetition")
    public BaseResponse<Competition> createCompetition(Competition competition) {
        competitionService.save(competition);
        return ResultUtils.success(competition);
    }

    /**
     * 更新
     * @return
     */
    @RequestMapping("/updateCompetition")
    public BaseResponse<Competition> updateCompetition(Competition competition) {
        competitionService.updateById(competition);
        return ResultUtils.success(competition);
    }


    /**
     * 删除
     * @return
     */
    @RequestMapping("/deleteCompetition")
    public BaseResponse<Boolean> deleteCompetition(Long id) {
        boolean b = competitionService.removeById(id);
        return ResultUtils.success(b);
    }



}

package com.yupi.yuoj.controller;

import com.yupi.yuoj.common.BaseResponse;
import com.yupi.yuoj.common.ResultUtils;
import com.yupi.yuoj.model.entity.Competition;
import com.yupi.yuoj.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getCompetitionList")
    public BaseResponse<List<Competition>> getCompetitionList() {
        List<Competition> list = competitionService.list();
        return ResultUtils.success(list);
    }

    /**
     * 获取详情
     * @return
     */
    @GetMapping("/getCompetitionDetail")
    public BaseResponse<Competition> getCompetitionDetail(Long id) {
        Competition byId = competitionService.getById(id);
        return ResultUtils.success(byId);
    }



    /**
     * 创建
     * @return
     */
    @PostMapping("/createCompetition")
    public BaseResponse<Competition> createCompetition(@RequestBody Competition competition) {
        competitionService.save(competition);
        return ResultUtils.success(competition);
    }

    /**
     * 更新
     * @return
     */
    @PostMapping("/updateCompetition")
    public BaseResponse<Competition> updateCompetition(@RequestBody Competition competition) {
        competitionService.updateById(competition);
        return ResultUtils.success(competition);
    }


    /**
     * 删除
     * @return
     */
    @GetMapping("/deleteCompetition")
    public BaseResponse<Boolean> deleteCompetition(Long id) {
        boolean b = competitionService.removeById(id);
        return ResultUtils.success(b);
    }



}

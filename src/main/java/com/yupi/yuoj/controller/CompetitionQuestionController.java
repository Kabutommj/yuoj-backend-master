package com.yupi.yuoj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.yuoj.common.BaseResponse;
import com.yupi.yuoj.common.ResultUtils;
import com.yupi.yuoj.model.entity.CompetitionQuestion;
import com.yupi.yuoj.service.CompetitionQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/CompetitionQuestionController")
@Slf4j
public class CompetitionQuestionController {

    @Resource
    private CompetitionQuestionService competitionQuestionService;



    /**
     * 新增
     * @param competitionQuestion
     * @param request
     * @return
     */
    @PostMapping("/addCompetitionQuestion")
    public BaseResponse<String> addCompetitionQuestion(@RequestBody CompetitionQuestion competitionQuestion, HttpServletRequest request) {
        boolean save = competitionQuestionService.save(competitionQuestion);
        return ResultUtils.success(save == true ? "新增成功" : "新增失败");
    }

    /**
     * 查询
     * @param gameId
     * @param request
     * @return
     */
    @GetMapping("/queryCompetitionQuestion")
    public BaseResponse<List<Long>> queryCompetitionQuestion(@RequestParam String gameId, HttpServletRequest request) {
        List<CompetitionQuestion> competitionQuestionList = competitionQuestionService.list(new LambdaQueryWrapper<CompetitionQuestion>()
                .eq(CompetitionQuestion::getCompetitionId, gameId));

        List<Long> answerList = competitionQuestionList.stream()
                .map(item -> item.getQuestionId())
                .collect(Collectors.toList());

        return ResultUtils.success(answerList);
    }

}

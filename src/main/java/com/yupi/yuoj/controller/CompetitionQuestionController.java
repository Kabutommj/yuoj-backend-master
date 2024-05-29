package com.yupi.yuoj.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.JsonObject;
import com.yupi.yuoj.common.BaseResponse;
import com.yupi.yuoj.common.ResultUtils;
import com.yupi.yuoj.model.entity.CompetitionQuestion;
import com.yupi.yuoj.model.entity.Question;
import com.yupi.yuoj.service.CompetitionQuestionService;
import com.yupi.yuoj.service.CompetitionService;
import com.yupi.yuoj.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
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


    @Resource
    private QuestionService questionService;



    /**
     * 新增
     * @param jsonObject
     * @param request
     * @return
     */
    @PostMapping("/addCompetitionQuestion")
    public BaseResponse<String> addCompetitionQuestion(@RequestBody JSONObject jsonObject, HttpServletRequest request) throws JSONException {
        Long gameId = jsonObject.getLong("gameId");
        List<Long> questionList = jsonObject.getBeanList("questionList",Long.class);

        questionList.forEach(questionId -> {
            CompetitionQuestion competitionQuestion = new CompetitionQuestion();
            competitionQuestion.setQuestionId(questionId);
            competitionQuestion.setCompetitionId(gameId);
            competitionQuestionService.save(competitionQuestion);
        });
        return ResultUtils.success("新增成功");
    }

    /**
     * 查询
     * @param gameId
     * @param request
     * @return
     */
    @GetMapping("/queryCompetitionQuestion")
    public BaseResponse<List<Question>> queryCompetitionQuestion(@RequestParam String gameId, HttpServletRequest request) {
        List<CompetitionQuestion> competitionQuestionList = competitionQuestionService.list(new LambdaQueryWrapper<CompetitionQuestion>()
                .eq(CompetitionQuestion::getCompetitionId, gameId));

        List<Long> answerList = competitionQuestionList.stream()
                .map(item -> item.getQuestionId())
                .collect(Collectors.toList());
        List<Question> competitionQuestions = questionService.listByIds(answerList);
        return ResultUtils.success(competitionQuestions);
    }

}

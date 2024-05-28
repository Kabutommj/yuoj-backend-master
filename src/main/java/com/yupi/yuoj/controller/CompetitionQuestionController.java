package com.yupi.yuoj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.JsonObject;
import com.yupi.yuoj.common.BaseResponse;
import com.yupi.yuoj.common.ResultUtils;
import com.yupi.yuoj.model.entity.CompetitionQuestion;
import com.yupi.yuoj.service.CompetitionQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
     * @param jsonObject
     * @param request
     * @return
     */
    @PostMapping("/addCompetitionQuestion")
    public BaseResponse<String> addCompetitionQuestion(@RequestBody JsonObject jsonObject, HttpServletRequest request) throws JSONException {
        Long gameId = jsonObject.get("gameId").getAsLong();
        List<Long> questionList = (List<Long>) jsonObject.get("questionList");

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
    public BaseResponse<List<Long>> queryCompetitionQuestion(@RequestParam String gameId, HttpServletRequest request) {
        List<CompetitionQuestion> competitionQuestionList = competitionQuestionService.list(new LambdaQueryWrapper<CompetitionQuestion>()
                .eq(CompetitionQuestion::getCompetitionId, gameId));

        List<Long> answerList = competitionQuestionList.stream()
                .map(item -> item.getQuestionId())
                .collect(Collectors.toList());

        return ResultUtils.success(answerList);
    }

}

package com.yupi.yuoj.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.yuoj.common.BaseResponse;
import com.yupi.yuoj.common.ResultUtils;
import com.yupi.yuoj.model.entity.CompetitionQuestion;
import com.yupi.yuoj.model.entity.CompetitionUser;
import com.yupi.yuoj.model.entity.User;
import com.yupi.yuoj.service.CompetitionUserService;
import com.yupi.yuoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/CompetitionUserController")
@Slf4j
public class CompetitionUserController {

    @Resource
    private CompetitionUserService competitionUserService;

    @Resource
    private UserService userService;

    /**
     * 新增
     * @param gameId
     * @return
     */
    @PostMapping("addCompetitionUser")
    public BaseResponse<String> addCompetitionUser(@RequestParam Long gameId, HttpServletRequest request) {
        User user = userService.getLoginUser(request);

        CompetitionUser competitionUser = new CompetitionUser();
        competitionUser.setUserId(user.getId());
        competitionUser.setCompetitionId(gameId);

        boolean save = competitionUserService.save(competitionUser);
        return ResultUtils.success(save == true ? "新增成功" : "新增失败");
    }

    /**
     * 查询用户id列表，通过赛事Id
     * @param gameId
     * @param request
     * @return
     */
    @GetMapping("/queryUserList")
    public BaseResponse<List<Long>> queryUserList(@RequestParam Long gameId, HttpServletRequest request) {
        List<CompetitionUser> competitionUserList = competitionUserService.list(new LambdaQueryWrapper<CompetitionUser>()
                .eq(CompetitionUser::getCompetitionId, gameId));

        List<Long> answerList = competitionUserList.stream()
                .map(item -> item.getUserId())
                .collect(Collectors.toList());

        return ResultUtils.success(answerList);
    }

    /**
     * 查询赛事id列表，通过用户id
     * @param userId
     * @param request
     * @return
     */
    @GetMapping("/queryCompetitionList")
    public BaseResponse<List<Long>> queryCompetitionList(@RequestParam Long userId, HttpServletRequest request) {
        List<CompetitionUser> competitionUserList = competitionUserService.list(new LambdaQueryWrapper<CompetitionUser>()
                .eq(CompetitionUser::getUserId, userId));

        List<Long> answerList = competitionUserList.stream()
                .map(item -> item.getCompetitionId())
                .collect(Collectors.toList());

        return ResultUtils.success(answerList);
    }


}

package com.yupi.yuoj.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.yuoj.common.BaseResponse;
import com.yupi.yuoj.common.ResultUtils;
import com.yupi.yuoj.model.entity.Competition;
import com.yupi.yuoj.model.entity.CompetitionQuestion;
import com.yupi.yuoj.model.entity.CompetitionUser;
import com.yupi.yuoj.model.entity.User;
import com.yupi.yuoj.model.vo.GameAndUserCountVO;
import com.yupi.yuoj.model.vo.UserNameAndGameCountVO;
import com.yupi.yuoj.service.CompetitionService;
import com.yupi.yuoj.service.CompetitionUserService;
import com.yupi.yuoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
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

    @Resource
    private CompetitionService competitionService;

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

    /**
     * 获取Game和 user数量
     * @return
     */
    @GetMapping("/queryGameAndUserCount")
    public BaseResponse<List<GameAndUserCountVO>> queryGameAndUserCount() {
        List<Competition> competitionList = competitionService.getBaseMapper().selectList(new LambdaQueryWrapper<Competition>());

        List<GameAndUserCountVO> answerList = new ArrayList<>();

        competitionList.forEach(one -> {
            long count = competitionUserService.count(new LambdaQueryWrapper<CompetitionUser>()
                    .eq(CompetitionUser::getCompetitionId, one.getCompetitionId()));

            GameAndUserCountVO gameAndUserCountVO = new GameAndUserCountVO();
            gameAndUserCountVO.setGameName(one.getCompetitionName());
            gameAndUserCountVO.setUserCount(count);
            gameAndUserCountVO.setGameId(one.getCompetitionId());

            answerList.add(gameAndUserCountVO);
        });

        List<GameAndUserCountVO> answer = answerList.stream()
                .sorted(Comparator.comparing(GameAndUserCountVO::getUserCount).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < answer.size(); i++) {
            answer.get(i).setRank(i+1);
        }

        return ResultUtils.success(answer);
    }

    /**
     * 获取UserName和 参赛数量
     * @return
     */
    @GetMapping("/queryUserNameAndGameCount")
    public BaseResponse<List<UserNameAndGameCountVO>> queryUserNameAndGameCount() {
        List<User> userList = userService.list(new LambdaQueryWrapper<User>());

        List<UserNameAndGameCountVO> answerList = new ArrayList<>();
        userList.forEach(one -> {
            UserNameAndGameCountVO item = new UserNameAndGameCountVO();
            long count = competitionUserService.count(new LambdaQueryWrapper<CompetitionUser>()
                    .eq(CompetitionUser::getUserId, one.getId()));

            item.setUserName(one.getUserName());
            item.setGameCount(count);

            answerList.add(item);
        });

        List<UserNameAndGameCountVO> answer = answerList.stream()
                .sorted(Comparator.comparing(UserNameAndGameCountVO::getGameCount).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < answer.size(); i++) {
            answer.get(i).setRank(i+1);
        }

        return ResultUtils.success(answer);
    }


}

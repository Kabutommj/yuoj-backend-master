package com.yupi.yuoj.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yuoj.mapper.CompetitionQuestionMapper;
import com.yupi.yuoj.model.entity.CompetitionQuestion;
import com.yupi.yuoj.service.CompetitionQuestionService;
@Service
public class CompetitionQuestionServiceImpl extends ServiceImpl<CompetitionQuestionMapper, CompetitionQuestion> implements CompetitionQuestionService{

}

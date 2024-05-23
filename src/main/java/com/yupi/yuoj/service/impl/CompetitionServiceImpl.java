package com.yupi.yuoj.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yuoj.mapper.CompetitionMapper;
import com.yupi.yuoj.model.entity.Competition;
import com.yupi.yuoj.service.CompetitionService;
@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService{

}

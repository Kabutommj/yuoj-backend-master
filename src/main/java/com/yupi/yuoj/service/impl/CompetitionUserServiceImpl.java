package com.yupi.yuoj.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yuoj.mapper.CompetitionUserMapper;
import com.yupi.yuoj.model.entity.CompetitionUser;
import com.yupi.yuoj.service.CompetitionUserService;
@Service
public class CompetitionUserServiceImpl extends ServiceImpl<CompetitionUserMapper, CompetitionUser> implements CompetitionUserService{

}

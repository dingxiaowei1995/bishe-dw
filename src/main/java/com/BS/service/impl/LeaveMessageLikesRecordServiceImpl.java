package com.BS.service.impl;

import com.BS.mapper.LeaveMessageLikesRecordMapper;
import com.BS.model.LeaveMessageLikesRecord;
import com.BS.service.LeaveMessageLikesRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
<<<<<<< HEAD
 * 
 * @Date: 2018/7/16 15:32
=======
>>>>>>> branch 'master' of https://github.com/dingxiaowei1995/bishe-dw.git
 * Describe:
 */
@Service
public class LeaveMessageLikesRecordServiceImpl implements LeaveMessageLikesRecordService {

    @Autowired
    LeaveMessageLikesRecordMapper leaveMessageLikesRecordMapper;


    @Override
    public boolean isLiked(String pageName, int pId, int likeId) {

        return leaveMessageLikesRecordMapper.isLiked(pageName, pId, likeId) != null;
    }

    @Override
    public void insertLeaveMessageLikesRecord(LeaveMessageLikesRecord leaveMessageLikesRecord) {
        leaveMessageLikesRecordMapper.insertLeaveMessageLikesRecord(leaveMessageLikesRecord);
    }
}

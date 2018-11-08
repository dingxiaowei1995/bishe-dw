package com.zhy.mapper;

import com.zhy.model.FeedBack;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
<<<<<<< HEAD
 * 
=======
 *  
>>>>>>> branch 'master' of https://github.com/dingxiaowei1995/bishe-dw.git
 * @Date: 2018/7/23 17:22
 * Describe: 反馈sql
 */
@Mapper
@Repository
public interface FeedBackMapper {

    @Insert("insert into feedback(feedbackContent,contactInfo,personId,feedbackDate) values(#{feedbackContent},#{contactInfo},#{personId},#{feedbackDate})")
    void insertFeedback(FeedBack feedBack);

    @Select("select * from feedback")
    List<FeedBack> getAllFeedback();

}

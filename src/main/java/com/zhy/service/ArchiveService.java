package com.zhy.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
<<<<<<< HEAD
 * 
=======
 *  
>>>>>>> branch 'master' of https://github.com/dingxiaowei1995/bishe-dw.git
 * @Date: 2018/7/18 12:07
 * Describe: 日记业务操作
 */
@Service
public interface ArchiveService {

    /**
     * 获得日记信息
     * @return
     */
    JSONObject findArchiveNameAndArticleNum();

    /**
     * 添加日记日期
     * @param archiveName
     */
    void addArchiveName(String archiveName);

}

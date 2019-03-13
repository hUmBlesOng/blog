package com.hs.blog.controller;

import com.hs.blog.entity.SysLog;
import com.hs.blog.entity.SysView;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO: 有关日志和浏览记录的Controller
 *
 * @author 83998
 * @date 2019/3/4 23:52
 */

@RestController
@RequestMapping("/admin")
public class SysController extends BaseController {

    /**
     * 返回所有的系统日志记录信息
     *
     * @return
     */
    @ApiOperation("返回所有的SysLog信息")
    @GetMapping("/sys/log")
    public List<SysLog> listAllLog() {
        return iSysService.listAllLog();
    }

    /**
     * 返回所有的系统浏览记录信息
     *
     * @return
     */
    @ApiOperation("返回所有的SysView信息")
    @GetMapping("/sys/view")
    public List<SysView> listAllView() {
        return iSysService.listAllView();
    }
}

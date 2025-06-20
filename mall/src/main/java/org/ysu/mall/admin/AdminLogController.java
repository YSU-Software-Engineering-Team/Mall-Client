package org.ysu.mall.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ysu.mall.common.ApiResponse;

@RestController
@RequestMapping("/admin/log")
public class AdminLogController {
    @Autowired
    private AdminLogService adminLogService;

    /**
     * 查询所有操作日志
     */
    @GetMapping
    public ApiResponse<List<AdminLog>> getAllLogs() {
        List<AdminLog> logs = adminLogService.getAllLogs();
        return ApiResponse.success(logs);
    }

    /**
     * 根据管理员ID查询日志
     */
    @GetMapping("/byAdmin/{adminId}")
    public ApiResponse<List<AdminLog>> getLogsByAdmin(@PathVariable Long adminId) {
        List<AdminLog> logs = adminLogService.getLogsByAdminId(adminId);
        return ApiResponse.success(logs);
    }

    /**
     * 根据操作类型查询日志
     */
    @GetMapping("/byAction")
    public ApiResponse<List<AdminLog>> getLogsByAction(@RequestParam String action) {
        List<AdminLog> logs = adminLogService.getLogsByAction(action);
        return ApiResponse.success(logs);
    }
}

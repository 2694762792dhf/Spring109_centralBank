package com.controller;

import com.model.Account;
import com.service.AccountService;
import com.util.IdCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataReceiverController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private IdCardUtil idCardUtil;

    @RequestMapping(value = "/receiveData", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receiveData(@RequestBody Account account) {
        try {
            System.out.println("接收到了！");
            String code = account.getCode();

            if (idCardUtil.isValidIdCard(code)) {
                Account existingAccount = accountService.select_(code);

                if (existingAccount == null) {
                    int insertResult = accountService.insert_(account);
                    if (insertResult > 0) {
                        return ResponseEntity.ok("成功");
                    }
                } else {
                    return ResponseEntity.ok("失败");
                }
            } else {
                return ResponseEntity.ok("失败");
            }
        } catch (Exception e) {
            // 记录异常以便调试
            e.printStackTrace();
            return ResponseEntity.status(500).body("发生了错误");
        }
        return ResponseEntity.status(100).body("无法入库！");
    }
}


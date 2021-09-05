package com.yonyoucloud.drools.controller;

import com.yonyoucloud.drools.fact.RuleExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/test/drools")
@RestController
public class TestDroolsController {
    @GetMapping("/rule/helloworld")
    public RuleExecutionResult testNormalRule() {
        RuleExecutionResult result = RuleExecutionResult.builder().build();

        try {
            KieServices kss = KieServices.Factory.get();
            KieContainer kieContainer = kss.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("helloWorldKSession");
            kieSession.setGlobal("log", log);

            kieSession.insert(result);

            int count = kieSession.fireAllRules();
            log.info(String.format("已执行%d规则", count));
            kieSession.dispose();
        } catch (Exception e) {
             log.error("执行规则失败", e);
             result.setCode("999");
             result.setSuccess(false);
             result.setMessage(e.toString());
        }

        return result;
    }
}

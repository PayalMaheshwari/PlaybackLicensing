package com.netflix.playback.service;

import com.netflix.playback.rules.Rule;
import com.netflix.playback.web.MemberEligibilityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Validator {

    @Autowired
    ApplicationContext applicationContext;


    public boolean validate(MemberEligibilityRequest memberEligibilityRequest) {
        Map<String, Rule> beansList = applicationContext.getBeansOfType(Rule.class);
        boolean eligible = true;
        for (Rule r : beansList.values()) {
            if (r.isEnabled()) {
                if (!r.evaluate(memberEligibilityRequest)){
                    eligible = false;
                    break;
                }
            }
        }
        return eligible;
    }
}

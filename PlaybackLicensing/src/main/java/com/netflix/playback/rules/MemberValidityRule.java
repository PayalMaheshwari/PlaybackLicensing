package com.netflix.playback.rules;

import com.netflix.playback.data.dto.Member;
import com.netflix.playback.web.MemberEligibilityRequest;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MemberValidityRule implements Rule {

    @Value("${member.validity.rule}")
    private Boolean memberValidityRule;
    private Counter failureCounter;
    private Counter successCounter;

    @Override
    public boolean isEnabled() {
        return memberValidityRule;
    }

    @Override
    public boolean evaluate(MemberEligibilityRequest memberEligibilityRequest) {
        Member member = memberEligibilityRequest.getMember();
        if(member.isActive()) {
            successCounter.increment();
            return true;
        }
        failureCounter.increment();
        return false;
    }

    public MemberValidityRule(MeterRegistry registry) {
        this.successCounter = registry.counter("MemberValidityRule.success");
        this.failureCounter = registry.counter("MemberValidityRule.failure");
    }
}

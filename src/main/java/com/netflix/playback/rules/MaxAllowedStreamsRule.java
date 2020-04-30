package com.netflix.playback.rules;

import com.netflix.playback.data.dto.Member;
import com.netflix.playback.web.MemberEligibilityRequest;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MaxAllowedStreamsRule implements Rule {

    private Counter failureCounter;
    private Counter successCounter;

    @Value("${member.maxstreamsallowed.rule}")
    private Boolean memberMaxAllowedStreamsRule;

    @Override
    public boolean isEnabled() {
        return memberMaxAllowedStreamsRule;
    }

    @Override
    public boolean evaluate(MemberEligibilityRequest memberEligibilityRequest) {
        Member member = memberEligibilityRequest.getMember();
        if(member.getActiveStreams().size() + 1 < member.getMaxAllowedStreams()){
            successCounter.increment();
            return true;
        }
        failureCounter.increment();
        return false;
    }

    public MaxAllowedStreamsRule(MeterRegistry registry) {
        this.successCounter = registry.counter("MaxAllowedStreamsRule.success");
        this.failureCounter = registry.counter("MaxAllowedStreamsRule.failure");
    }
}

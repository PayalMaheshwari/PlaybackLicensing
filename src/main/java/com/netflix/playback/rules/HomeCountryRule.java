package com.netflix.playback.rules;

import com.netflix.playback.data.dto.Content;
import com.netflix.playback.data.dto.Member;
import com.netflix.playback.web.MemberEligibilityRequest;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HomeCountryRule implements Rule {

    @Value("${member.country.rule}")
    private Boolean memberCountryRule;
    private Counter failureCounter;
    private Counter successCounter;

    @Override
    public boolean isEnabled() {
        return memberCountryRule;
    }

    @Override
    public boolean evaluate(MemberEligibilityRequest memberEligibilityRequest) {
        Member member = memberEligibilityRequest.getMember();
        Content content = memberEligibilityRequest.getContent();
        if (content.getStreamingAllowedInCountries().contains(member.getHomeCountry())) {
            successCounter.increment();
            return true;
        }
        failureCounter.increment();
        return false;
    }

    public HomeCountryRule(MeterRegistry registry) {
        this.successCounter = registry.counter("HomeCountryRule.success");
        this.failureCounter = registry.counter("HomeCountryRule.failure");
    }
}

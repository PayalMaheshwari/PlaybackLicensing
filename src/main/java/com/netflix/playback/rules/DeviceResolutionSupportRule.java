package com.netflix.playback.rules;

import com.netflix.playback.data.dto.Content;
import com.netflix.playback.data.dto.Device;
import com.netflix.playback.web.MemberEligibilityRequest;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DeviceResolutionSupportRule implements Rule {

    @Value("${device.resolution.rule}")
    private Boolean deviceResolutionRule;
    private Counter failureCounter;
    private Counter successCounter;

    public DeviceResolutionSupportRule(MeterRegistry registry) {
        this.successCounter = registry.counter("DeviceResolutionSupportRule.success");
        this.failureCounter = registry.counter("DeviceResolutionSupportRule.failure");
    }

    @Override
    public boolean isEnabled() {
        return deviceResolutionRule;
    }

    @Override
    public boolean evaluate(MemberEligibilityRequest memberEligibilityRequest) {
        Device device = memberEligibilityRequest.getDevice();
        Content content = memberEligibilityRequest.getContent();
        if ( device.getMaxVideoResolutionSupport().compareTo(content.getMinRequiredResolution()) < 0) {
            failureCounter.increment();
            return false;
        }
        successCounter.increment();
        return true;
    }

}

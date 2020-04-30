package com.netflix.playback.rules;

import com.netflix.playback.web.MemberEligibilityRequest;

public interface Rule {

    boolean isEnabled();

    boolean evaluate(MemberEligibilityRequest memberEligibilityRequest);

}

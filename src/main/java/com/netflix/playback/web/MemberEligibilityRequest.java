package com.netflix.playback.web;


import com.netflix.playback.data.dto.Content;
import com.netflix.playback.data.dto.Device;
import com.netflix.playback.data.dto.Member;

public class MemberEligibilityRequest {

    private Member member;
    private Device device;
    private Content content;

    public MemberEligibilityRequest() {
    }

    public MemberEligibilityRequest(Member member, Device device, Content content) {
        this.member = member;
        this.device = device;
        this.content = content;
    }

    public Member getMember() {
        return member;
    }

    public Device getDevice() {
        return device;
    }

    public Content getContent() {
        return content;
    }
}

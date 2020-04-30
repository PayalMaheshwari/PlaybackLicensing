package com.netflix.playback.service;

import com.netflix.playback.data.dto.Content;
import com.netflix.playback.data.dto.Device;
import com.netflix.playback.data.dto.Member;
import com.netflix.playback.data.repo.ContentRepository;
import com.netflix.playback.data.repo.DeviceRepository;
import com.netflix.playback.data.repo.MemberRepository;
import com.netflix.playback.exception.ContentNotFoundException;
import com.netflix.playback.exception.DeviceNotFoundException;
import com.netflix.playback.exception.MemberNotFoundException;
import com.netflix.playback.web.MemberEligibilityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlaybackLicensingService {

    @Autowired
    ContentRepository contentRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    Validator validator;


    public PlaybackLicensingService() {

    }

    public boolean evaluateMemberLicencingEligibility(UUID memberId, UUID deviceId, UUID contentId) {
        Member member = getMember(memberId);
        Device device = getDevice(deviceId);
        checkIfMemberIsRequestingLicenseOnHisOwnDevice(member, device);
        Content content = getContent(contentId);
        return validator.validate(new MemberEligibilityRequest(member, device, content));
    }

    private Content getContent(UUID contentId) {

        return contentRepository.findById(contentId)
                .orElseThrow(() -> new ContentNotFoundException("Invalid contentId in the request"));
    }


    private void checkIfMemberIsRequestingLicenseOnHisOwnDevice(Member member, Device device) {
        if(!member.getListOfDevices().contains(device)){
            throw new DeviceNotFoundException("Device not associated with the member");
        }
    }

    private Device getDevice(UUID deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Invalid deviceId in the request"));

    }

    private Member getMember(UUID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Invalid memberId in the request"));
    }
}

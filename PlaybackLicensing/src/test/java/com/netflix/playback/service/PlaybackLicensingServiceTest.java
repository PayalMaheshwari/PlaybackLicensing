package com.netflix.playback.service;

import com.netflix.playback.exception.ContentNotFoundException;
import com.netflix.playback.exception.DeviceNotFoundException;
import com.netflix.playback.exception.MemberNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PlaybackLicensingServiceTest extends BaseTest {

    @Autowired
    PlaybackLicensingService playbackLicensingService;


    @Test
    void testEvaluateMemberLicencingEligibility_MemberNotFound() {
        Assertions.assertThrows(MemberNotFoundException.class, () ->
                playbackLicensingService.evaluateMemberLicencingEligibility(someRandomUUID, deviceId1,contentId));

    }
    @Test
    void testEvaluateMemberLicencingEligibility_DeviceNotFound() {
        Assertions.assertThrows(DeviceNotFoundException.class, () ->
                playbackLicensingService.evaluateMemberLicencingEligibility(memberId1, someRandomUUID,contentId));

    }

    @Test
    void testEvaluateMemberLicencingEligibility_ContentNotFound() {
        Assertions.assertThrows(ContentNotFoundException.class, () ->
                playbackLicensingService.evaluateMemberLicencingEligibility(memberId2, deviceId2,someRandomUUID));

    }

    @Test
    void testEvaluateMemberLicencingEligibility_DeviceDoesntBelongtoMember() {
        Assertions.assertThrows(DeviceNotFoundException.class, () ->
                playbackLicensingService.evaluateMemberLicencingEligibility(memberId1, deviceId2,contentId));

    }
}
package com.netflix.playback.web;

import com.netflix.playback.service.PlaybackLicensingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

//@RefreshScope
@RestController
@RequestMapping("/licensing")
public class PlaybackLicensingController {

    @Autowired
    PlaybackLicensingService playbackLicensingService;


    @GetMapping("/eligibility")
    public Boolean evaluateEligibility(@RequestParam UUID memberId, @RequestParam UUID deviceId, @RequestParam UUID contentId) {
       return playbackLicensingService.evaluateMemberLicencingEligibility(memberId,deviceId,contentId);
    }
}

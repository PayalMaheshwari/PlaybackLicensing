package com.netflix.playback.service;

import com.netflix.playback.data.dto.Content;
import com.netflix.playback.data.dto.Device;
import com.netflix.playback.data.dto.Member;
import com.netflix.playback.data.repo.ContentRepository;
import com.netflix.playback.data.repo.DeviceRepository;
import com.netflix.playback.data.repo.MemberRepository;
import com.netflix.playback.model.CountryCode;
import com.netflix.playback.model.Resolution;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class BaseTest {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    PlaybackLicensingService playbackLicensingService;

    public UUID someRandomUUID = UUID.randomUUID();

    public static UUID memberId1 = UUID.randomUUID();
    public static UUID memberId2 = UUID.randomUUID();
    public static UUID contentId = UUID.randomUUID();
    public static UUID deviceId1 = UUID.randomUUID();
    public static UUID deviceId2 = UUID.randomUUID();

    public Content content1;
    public Content content2;
    public Content content3;
    public Member member1;
    public Member member2;
    public Member member3;
    public Device device1;
    public Device device2;


    @BeforeEach
    public void setUp() {
        content1 = new Content(contentId, "Movie", 10, new Resolution(100, 140));
        content1.setStreamingAllowedInCountries(List.of(CountryCode.IND, CountryCode.USA));
        contentRepository.save(content1);

        content2 = new Content(contentId, "Movie", 10, new Resolution(100, 140));
        content2.setStreamingAllowedInCountries(List.of(CountryCode.AU, CountryCode.GB));
        contentRepository.save(content2);

        content3 = new Content(contentId, "Show", 10, new Resolution(130, 160));
        content3.setStreamingAllowedInCountries(List.of(CountryCode.IND, CountryCode.USA));
        contentRepository.save(content3);

        member1 = new Member(memberId1, true, 3, CountryCode.USA);
        member1.setActiveStreams(List.of(content1));
        memberRepository.save(member1);

        member2 = new Member(memberId2, true, 2, CountryCode.IND);
        member2.setActiveStreams(List.of(content1));
        memberRepository.save(member2);

        member3 = new Member(memberId2, false, 2, CountryCode.IND);
        member3.setActiveStreams(List.of(content1));
        memberRepository.save(member3);

        device1 = new Device(deviceId1, "iphone", new Resolution(120, 130), member1);
        device1.setMaxVideoResolutionSupport(new Resolution(100, 150));

        device2 = new Device(deviceId2, "pixel", new Resolution(120, 130), member2);
        deviceRepository.save(device1);
        deviceRepository.save(device2);
    }
}

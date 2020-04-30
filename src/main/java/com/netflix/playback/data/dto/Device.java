package com.netflix.playback.data.dto;

import com.netflix.playback.model.Resolution;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Device {

    @Id
    private UUID deviceId;
    private String deviceType;
    @Embedded
    private Resolution maxVideoResolutionSupport;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;


    public Device() {
    }

    public Device(UUID deviceId, String deviceType, Resolution maxVideoResolutionSupport, Member member) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.maxVideoResolutionSupport = maxVideoResolutionSupport;
        this.member = member;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Resolution getMaxVideoResolutionSupport() {
        return maxVideoResolutionSupport;
    }

    public void setMaxVideoResolutionSupport(Resolution maxVideoResolutionSupport) {
        this.maxVideoResolutionSupport = maxVideoResolutionSupport;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        return deviceId != null ? deviceId.equals(device.deviceId) : device.deviceId == null;
    }

    @Override
    public int hashCode() {
        return deviceId != null ? deviceId.hashCode() : 0;
    }
}

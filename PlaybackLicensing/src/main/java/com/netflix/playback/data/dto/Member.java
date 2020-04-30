package com.netflix.playback.data.dto;


import com.netflix.playback.model.CountryCode;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Member {

   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    @Id
    private UUID memberId;
    private boolean active;
    private Integer maxAllowedStreams;
    @Enumerated(EnumType.STRING)
    private CountryCode homeCountry;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Device> listOfDevices;

    @ManyToMany
    @JoinTable(
            name = "member_active_content",
            joinColumns = @JoinColumn(name = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "contentId"))
    private List<Content> activeStreams;

    public Member() {
    }

    public Member(UUID memberId, boolean active, Integer maxAllowedStreams, CountryCode homeCountry) {
        this.memberId = memberId;
        this.active = active;
        this.maxAllowedStreams = maxAllowedStreams;
        this.homeCountry = homeCountry;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getMaxAllowedStreams() {
        return maxAllowedStreams;
    }

    public void setMaxAllowedStreams(Integer maxAllowedStreams) {
        this.maxAllowedStreams = maxAllowedStreams;
    }

    public CountryCode getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(CountryCode homeCountry) {
        this.homeCountry = homeCountry;
    }

    public List<Device> getListOfDevices() {
        return listOfDevices;
    }

    public void setListOfDevices(List<Device> listOfDevices) {
        this.listOfDevices = listOfDevices;
    }

    public List<Content> getActiveStreams() {
        return activeStreams;
    }

    public void setActiveStreams(List<Content> activeStreams) {
        this.activeStreams = activeStreams;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", active=" + active +
                ", maxAllowedStreams=" + maxAllowedStreams +
                ", homeCountry=" + homeCountry +
                ", listOfDevices=" + listOfDevices +
                ", activeStreams=" + activeStreams +
                '}';
    }
}

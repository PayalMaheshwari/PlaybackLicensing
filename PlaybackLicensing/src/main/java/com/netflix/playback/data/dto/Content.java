package com.netflix.playback.data.dto;

import com.netflix.playback.model.CountryCode;
import com.netflix.playback.model.Resolution;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Content {

    @Id
    private UUID contentId;
    private String contentType;
    private Integer contentLength;

    @Embedded
    private Resolution minRequiredResolution;

    @ElementCollection(targetClass = CountryCode.class)
    @JoinTable(
            name = "content_allowed_in_countries",
            joinColumns = @JoinColumn(name = "contentId"))
    @Enumerated(EnumType.STRING)
    private List<CountryCode> streamingAllowedInCountries;

    public Content() {
    }

    public Content(UUID contentId, String contentType, Integer contentLength, Resolution minRequiredResolution) {
        this.contentId = contentId;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.minRequiredResolution = minRequiredResolution;
    }

    public Resolution getMinRequiredResolution() {
        return minRequiredResolution;
    }

    public void setMinRequiredResolution(Resolution minRequiredResolution) {
        this.minRequiredResolution = minRequiredResolution;
    }

    public List<CountryCode> getStreamingAllowedInCountries() {
        return streamingAllowedInCountries;
    }

    public void setStreamingAllowedInCountries(List<CountryCode> streamingAllowedInCountries) {
        this.streamingAllowedInCountries = streamingAllowedInCountries;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getContentLength() {
        return contentLength;
    }

    public void setContentLength(Integer contentLength) {
        this.contentLength = contentLength;
    }

    public UUID getContentId() {
        return contentId;
    }
}

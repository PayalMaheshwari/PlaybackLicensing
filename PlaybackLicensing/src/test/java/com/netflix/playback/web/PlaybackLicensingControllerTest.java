package com.netflix.playback.web;

import com.netflix.playback.service.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class PlaybackLicensingControllerTest extends BaseTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void validInput_Http_OK() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/licensing/eligibility")

                .param("memberId", memberId1.toString())
                .param("contentId", contentId.toString())
                .param("deviceId", deviceId1.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void inValidMember_404() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/licensing/eligibility")

                .param("memberId", someRandomUUID.toString())
                .param("contentId", contentId.toString())
                .param("deviceId", deviceId1.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void inValidDevice_404() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/eligibility")

                .param("memberId", memberId1.toString())
                .param("contentId", contentId.toString())
                .param("deviceId", someRandomUUID.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void inValidContent_404() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/eligibility")

                .param("memberId", memberId1.toString())
                .param("contentId", someRandomUUID.toString())
                .param("deviceId", deviceId1.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void inValidMemberDevice_404() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/eligibility")

                .param("memberId", memberId1.toString())
                .param("contentId", contentId.toString())
                .param("deviceId", deviceId2.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
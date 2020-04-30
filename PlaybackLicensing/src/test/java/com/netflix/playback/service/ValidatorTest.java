package com.netflix.playback.service;

import com.netflix.playback.web.MemberEligibilityRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class ValidatorTest extends BaseTest {

    @Autowired
    private Validator validator;


    @Test
    void testValidate_Eligible_HappyPath() {
        assertTrue(validator.validate(new MemberEligibilityRequest(member1,device1,content1)));
    }

    @Test
    void testValidate_InEligible_InactiveMember() {
        assertFalse(validator.validate(new MemberEligibilityRequest(member3,device1,content1)));
    }

    @Test
    void testValidate_InEligible_ContentNotAvailableInMemberHomeCountry() {
        assertFalse(validator.validate(new MemberEligibilityRequest(member2,device1,content2)));
    }

    @Test
    void testValidate_InEligible_MemberDeviceResolutionNotSupported() {
        assertFalse(validator.validate(new MemberEligibilityRequest(member1,device1,content2)));
    }
}
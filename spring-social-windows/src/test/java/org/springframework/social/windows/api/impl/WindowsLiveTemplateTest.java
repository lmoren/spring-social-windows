/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.windows.api.impl;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.social.test.client.RequestMatchers.*;
import static org.springframework.social.test.client.ResponseCreators.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.windows.api.Emails;
import org.springframework.social.windows.api.WindowsLiveProfile;
import org.springframework.social.test.client.MockRestServiceServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Craig Walls
 */
public class WindowsLiveTemplateTest {

    private WindowsLiveTemplate windowsLiveTemplate;
    private MockRestServiceServer mockServer;
    private HttpHeaders responseHeaders;

    @Before
    public void setup() {
        windowsLiveTemplate = new WindowsLiveTemplate("ACCESS_TOKEN");
        mockServer = MockRestServiceServer.createServer(windowsLiveTemplate.getRestTemplate());
        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void getUserProfile() {
        mockServer.expect(requestTo(WindowsLiveTemplate.PROFILE_URL)).andExpect(method(GET))
                .andRespond(withResponse(new ClassPathResource("profile.json", getClass()), responseHeaders));
        WindowsLiveProfile profile = windowsLiveTemplate.getUserProfile();
        assertEquals("8c8ce076ca27823f", profile.getId());
        assertEquals("Roberto", profile.getFirstName());
        assertEquals("Tamburello", profile.getLastName());

    }

    @Test
    public void getProfileId() {
        mockServer.expect(requestTo(WindowsLiveTemplate.PROFILE_URL)).andExpect(method(GET))
                .andRespond(withResponse(new ClassPathResource("profile.json", getClass()), responseHeaders));
        assertEquals("8c8ce076ca27823f", windowsLiveTemplate.getProfileId());
    }

    @Test
    public void getProfileUrl() {
        mockServer.expect(requestTo(WindowsLiveTemplate.PROFILE_URL)).andExpect(method(GET))
                .andRespond(withResponse(new ClassPathResource("profile.json", getClass()), responseHeaders));
        assertEquals("http://cid-8c8ce076ca27823f.profile.live.com/", windowsLiveTemplate.getUserProfile().getLink());
    }

    @Test
    public void getEmails() {
        mockServer.expect(requestTo(WindowsLiveTemplate.PROFILE_URL)).andExpect(method(GET))
                .andRespond(withResponse(new ClassPathResource("profile.json", getClass()), responseHeaders));
        Emails emails = windowsLiveTemplate.getUserProfile().getEmails();
        assertEquals("Roberto.Tamburello@contoso.com", emails.getPreferred());
        assertEquals("Roberto.Tamburello@live.com", emails.getAccount());
        assertEquals("Roberto.Tamburello@contoso.com", emails.getPersonal());
        assertEquals("r_tamburello@example.com", emails.getBusiness());
    }

    @Test
    public void getBirthdate() throws ParseException {
        mockServer.expect(requestTo(WindowsLiveTemplate.PROFILE_URL)).andExpect(method(GET))
                .andRespond(withResponse(new ClassPathResource("profile.json", getClass()), responseHeaders));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        WindowsLiveProfile userProfile = windowsLiveTemplate.getUserProfile();
        String birthday = userProfile.getBirthday();
        String birthmonth = userProfile.getBirthmonth();
        String birthyear = userProfile.getBirthyear();
        Date actualDate = formatter.parse(new StringBuilder(birthday).append("/")
                .append(birthmonth).append("/").append(birthyear).toString());


        Date expectedDate = formatter.parse("20/04/2010");

        assertEquals(expectedDate, actualDate);
    }
}

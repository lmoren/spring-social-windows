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

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.windows.api.WindowsLive;
import org.springframework.social.windows.api.WindowsLiveProfile;

import java.util.List;

/**
 * This is the central class for interacting with WindowsLive.
 * <p>
 * Greenhouse operations require OAuth authentication with the server.
 * Therefore, WindowsLiveTemplate must be constructed with the minimal information
 * required to sign requests with and OAuth 1 Authorization header.
 * </p>
 *
 * @author Craig Walls
 */
public class WindowsLiveTemplate extends AbstractOAuth2ApiBinding implements WindowsLive {

    /**
     * Creates a new WindowsLiveTemplate given the minimal amount of information needed to sign requests with OAuth 1 credentials.
     *
     * @param accessToken       an access token acquired through OAuth authentication with WindowsLive
     */
    public WindowsLiveTemplate(String accessToken) {
        super(accessToken);
        registerWindowsLiveJsonModule();
    }

    public String getProfileId() {
        return getUserProfile().getId();
    }


    public WindowsLiveProfile getUserProfile() {
        return getRestTemplate().getForObject(PROFILE_URL, WindowsLiveProfile.class);
    }

    // private helper

    private void registerWindowsLiveJsonModule() {
        List<HttpMessageConverter<?>> converters = getRestTemplate().getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJacksonHttpMessageConverter) {
                MappingJacksonHttpMessageConverter jsonConverter = (MappingJacksonHttpMessageConverter) converter;
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new WindowsLiveModule());
                jsonConverter.setObjectMapper(objectMapper);
            }
        }
    }

    static final String PROFILE_URL = "https://apis.live.net/v5.0/me";

}

/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.windows.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.windows.api.WindowsLive;
import org.springframework.social.windows.api.WindowsLiveProfile;
import org.springframework.web.client.HttpClientErrorException;

/**
 * WindowsLive ApiAdapter implementation.
 *
 * @author Keith Donald
 */
public class WindowsLiveAdapter implements ApiAdapter<WindowsLive> {

    public boolean test(WindowsLive windowsLive) {
        try {
            windowsLive.getUserProfile();
            return true;
        } catch (HttpClientErrorException e) {
            // TODO: Have api throw more specific exception and trigger off of that.
            return false;
        }
    }

    public void setConnectionValues(WindowsLive windowsLive, ConnectionValues values) {
        WindowsLiveProfile profile = windowsLive.getUserProfile();
        values.setProviderUserId(profile.getId());
        values.setDisplayName(profile.getFirstName() + " " + profile.getLastName());
    }

    public UserProfile fetchUserProfile(WindowsLive windowsLive) {
        WindowsLiveProfile profile = windowsLive.getUserProfile();
        return new UserProfileBuilder().setName(profile.getFirstName() + " " + profile.getLastName()).build();
    }

    public void updateStatus(WindowsLive windowsLive, String message) {
        // not supported yet
    }

}

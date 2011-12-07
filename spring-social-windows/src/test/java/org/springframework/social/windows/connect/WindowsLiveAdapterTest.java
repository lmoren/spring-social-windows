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

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.windows.api.Emails;
import org.springframework.social.windows.api.WindowsLive;
import org.springframework.social.windows.api.WindowsLiveProfile;

public class WindowsLiveAdapterTest {

    private WindowsLiveAdapter apiAdapter = new WindowsLiveAdapter();

    private WindowsLive windowsLive = Mockito.mock(WindowsLive.class);

    @Test
    public void fetchProfile() {
        Mockito.when(windowsLive.getUserProfile()).thenReturn(new WindowsLiveProfile("50A3nOf73z", "Craig", "Walls", "Roberto Tamburello", "http://cid-8c8ce076ca27823f.profile.live.com/"));
        UserProfile profile = apiAdapter.fetchUserProfile(windowsLive);
        assertEquals("Craig Walls", profile.getName());
        assertEquals("Craig", profile.getFirstName());
        assertEquals("Walls", profile.getLastName());
        assertNull(profile.getEmail());
        assertNull(profile.getUsername());
    }

}

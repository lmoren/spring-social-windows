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
package org.springframework.social.windows.api;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Model class containing a user's WindowsLive profile information.
 *
 * @author Craig Walls
 */
public class WindowsLiveProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;

    private final String firstName;

    private final String lastName;

    private final String fullName;

    private final String link;

    private Emails emails;

    private String birthyear;

    private String birthday;

    private String birthmonth;


    public WindowsLiveProfile(String id, String firstName, String lastName, String fullName, String url) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.link = url;
    }

    /**
     * The user's WindowsLive profile ID
     */
    public String getId() {
        return id;
    }

    /**
     * The user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The user's last name
     */
    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLink() {
        return link;
    }

    public Emails getEmails() {
        return emails;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBirthmonth() {
        return birthmonth;
    }
}

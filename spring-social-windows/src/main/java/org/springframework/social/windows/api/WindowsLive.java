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

import org.springframework.social.ApiBinding;

/**
 * <p>
 * Interface specifying a basic set of operations for interacting with WindowsLive.
 * Implemented by {@link org.springframework.social.windows.api.impl.WindowsLiveTemplate}.
 * </p>
 * 
 * <p>
 * Many of the methods contained in this interface require OAuth authentication
 * with WindowsLive. When a method's description speaks of the "current user", it
 * is referring to the user for whom the access token has been issued.
 * </p>
 * 
 * @author Craig Walls
 */
public interface WindowsLive extends ApiBinding {
	/**
	 * Retrieves the user's WindowsLive profile ID.
	 * 
	 * @return the user's WindowsLive profile ID.
	 */
	String getProfileId();

	/**
	 * Retrieves the current user's profile details.
	 * 
	 * @return the user's profile data.
	 */
	WindowsLiveProfile getUserProfile();


}

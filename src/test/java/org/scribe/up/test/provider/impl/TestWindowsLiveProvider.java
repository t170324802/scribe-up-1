/*
  Copyright 2012 Jerome Leleu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.scribe.up.test.provider.impl;

import java.util.Locale;

import org.scribe.up.profile.ProfileHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.windowslive.WindowsLiveProfile;
import org.scribe.up.provider.OAuthProvider;
import org.scribe.up.provider.impl.WindowsLiveProvider;
import org.scribe.up.test.util.CommonHelper;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * This class tests the {@link org.scribe.up.provider.impl.WindowsLiveProvider} class by simulating a complete authentication.
 * 
 * @author Jerome Leleu
 * @since 1.1.0
 */
public class TestWindowsLiveProvider extends TestProvider {
    
    @Override
    protected boolean isJavascriptEnabled() {
        return true;
    }
    
    @Override
    protected OAuthProvider getProvider() {
        final WindowsLiveProvider liveProvider = new WindowsLiveProvider();
        liveProvider.setKey("00000000400BFE75");
        liveProvider.setSecret("9yz0WtTIUQVV7HhBV2tccTziETOt4pRG");
        liveProvider.setCallbackUrl("http://javadoc.leleuj.cloudbees.net/");
        return liveProvider;
    }
    
    @Override
    protected String getCallbackUrl(final HtmlPage authorizationPage) throws Exception {
        final HtmlTextInput login = authorizationPage.getElementByName("login");
        login.setValueAttribute("testscribeup@gmail.com");
        final HtmlPasswordInput password = authorizationPage.getElementByName("passwd");
        password.setValueAttribute("testpwdscribeup");
        final HtmlSubmitInput submit = authorizationPage.getElementByName("SI");
        final HtmlPage callbackPage = submit.click();
        final String callbackUrl = callbackPage.getUrl().toString();
        logger.debug("callbackUrl : {}", callbackUrl);
        return callbackUrl;
    }
    
    @Override
    protected void verifyProfile(final UserProfile userProfile) {
        final WindowsLiveProfile profile = (WindowsLiveProfile) userProfile;
        logger.debug("userProfile : {}", profile);
        assertEquals("416c383b220392d8", profile.getId());
        assertEquals(WindowsLiveProfile.class.getSimpleName() + UserProfile.SEPARATOR + "416c383b220392d8",
                     profile.getTypedId());
        assertTrue(ProfileHelper.isTypedIdOf(profile.getTypedId(), WindowsLiveProfile.class));
        assertCommonProfile(userProfile, null, "Test", "ScribeUP", "Test ScribeUP", null, null, Locale.FRANCE, null,
                            "https://profile.live.com/", null);
        assertEquals(CommonHelper.getFormattedDate(1335878042000L, "yyyy-MM-dd'T'HH:mm:ssz", null), profile
            .getUpdatedTime().toString());
        assertEquals(7, profile.getAttributes().size());
    }
}

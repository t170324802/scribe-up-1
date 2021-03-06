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

import org.scribe.up.profile.Gender;
import org.scribe.up.profile.ProfileHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.wordpress.WordPressLinks;
import org.scribe.up.profile.wordpress.WordPressProfile;
import org.scribe.up.provider.OAuthProvider;
import org.scribe.up.provider.impl.WordPressProvider;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * This class tests the {@link org.scribe.up.provider.impl.WordPressProvider} class by simulating a complete authentication.
 * 
 * @author Jerome Leleu
 * @since 1.1.0
 */
public class TestWordPressProvider extends TestProvider {
    
    @Override
    protected OAuthProvider getProvider() {
        final WordPressProvider wordPressProvider = new WordPressProvider();
        wordPressProvider.setKey("209");
        wordPressProvider.setSecret("xJBXMRVvKrvHqyvM6BpzkenJVMIdQrIWKjPJsezjGYu71y7sDgt8ibz6s9IFLqU8");
        wordPressProvider.setCallbackUrl("http://www.google.com/");
        return wordPressProvider;
    }
    
    @Override
    protected String getCallbackUrl(final HtmlPage authorizationPage) throws Exception {
        HtmlForm form = authorizationPage.getFormByName("loginform");
        final HtmlTextInput login = form.getInputByName("log");
        login.setValueAttribute("testscribeup");
        final HtmlPasswordInput passwd = form.getInputByName("pwd");
        passwd.setValueAttribute("testpwdscribeup");
        
        HtmlElement button = authorizationPage.createElement("button");
        button.setAttribute("type", "submit");
        form.appendChild(button);
        // HtmlButton button = form.getButtonByName("wp-submit");
        
        final HtmlPage confirmPage = button.click();
        form = confirmPage.getFormByName("loginform");
        
        button = confirmPage.createElement("button");
        button.setAttribute("type", "submit");
        form.appendChild(button);
        // button = form.getButtonByName("wp-submit");
        
        final HtmlPage callbackPage = button.click();
        final String callbackUrl = callbackPage.getUrl().toString();
        logger.debug("callbackUrl : {}", callbackUrl);
        return callbackUrl;
    }
    
    @Override
    protected void verifyProfile(final UserProfile userProfile) {
        final WordPressProfile profile = (WordPressProfile) userProfile;
        logger.debug("userProfile : {}", profile);
        assertEquals("35944437", profile.getId());
        assertEquals(WordPressProfile.class.getSimpleName() + UserProfile.SEPARATOR + "35944437", profile.getTypedId());
        assertTrue(ProfileHelper.isTypedIdOf(profile.getTypedId(), WordPressProfile.class));
        assertCommonProfile(userProfile, "testscribeup@gmail.com", null, null, "testscribeup", "testscribeup",
                            Gender.UNSPECIFIED, null,
                            "http://0.gravatar.com/avatar/67c3844a672979889c1e3abbd8c4eb22?s=96&d=identicon&r=G",
                            "http://en.gravatar.com/testscribeup", null);
        assertEquals(36224958, profile.getPrimaryBlog().intValue());
        final WordPressLinks links = profile.getLinks();
        assertEquals("https://public-api.wordpress.com/rest/v1/me", links.getSelf());
        assertEquals("https://public-api.wordpress.com/rest/v1/me/help", links.getHelp());
        assertEquals("https://public-api.wordpress.com/rest/v1/sites/36224958", links.getSite());
        assertEquals(8, profile.getAttributes().size());
    }
}

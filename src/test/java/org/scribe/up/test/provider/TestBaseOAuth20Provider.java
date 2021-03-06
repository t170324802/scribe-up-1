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
package org.scribe.up.test.provider;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.scribe.up.credential.OAuthCredential;
import org.scribe.up.provider.BaseOAuth20Provider;
import org.scribe.up.provider.impl.GitHubProvider;

/**
 * This class tests the OAuth credential retrieval in the {@link org.scribe.up.provider.BaseOAuth20Provider} class.
 * 
 * @author Jerome Leleu
 * @since 1.0.0
 */
public final class TestBaseOAuth20Provider extends TestCase {
    
    private static final String CODE = "code";
    
    private static final String CODE2 = "code2";
    
    private BaseOAuth20Provider getProvider() {
        GitHubProvider provider = new GitHubProvider();
        provider.setKey("key");
        provider.setSecret("secret");
        provider.setCallbackUrl("callbackUrl");
        return provider;
    }
    
    public void testNoCode() {
        Map<String, String[]> parameters = new HashMap<String, String[]>();
        assertNull(getProvider().getCredential(null, parameters));
    }
    
    public void testOk() {
        Map<String, String[]> parameters = new HashMap<String, String[]>();
        String[] codes = new String[] {
            CODE
        };
        parameters.put(BaseOAuth20Provider.OAUTH_CODE, codes);
        OAuthCredential oauthCredential = getProvider().getCredential(null, parameters);
        assertNotNull(oauthCredential);
        assertEquals(CODE, oauthCredential.getVerifier());
    }
    
    public void testTwoCodes() {
        Map<String, String[]> parameters = new HashMap<String, String[]>();
        String[] codes = new String[] {
            CODE, CODE2
        };
        parameters.put(BaseOAuth20Provider.OAUTH_CODE, codes);
        assertNull(getProvider().getCredential(null, parameters));
    }
}

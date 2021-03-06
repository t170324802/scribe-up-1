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
package org.scribe.up.profile.google;

import java.util.List;
import java.util.Locale;

import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.BaseOAuthProfile;
import org.scribe.up.profile.CommonProfile;
import org.scribe.up.profile.Gender;
import org.scribe.up.profile.OAuthAttributesDefinitions;

/**
 * This class is the user profile for Google (using OAuth protocol version 1) with appropriate getters.<br />
 * It is returned by the {@link org.scribe.up.provider.impl.GoogleProvider}.
 * <p />
 * <table border="1" cellspacing="2px">
 * <tr>
 * <th>Method :</th>
 * <th>From the JSON profile response :</th>
 * </tr>
 * <tr>
 * <th colspan="2">The attributes of the {@link org.scribe.up.profile.CommonProfile}</th>
 * </tr>
 * <tr>
 * <td>String getEmail()</td>
 * <td>null</td>
 * </tr>
 * <tr>
 * <td>String getFirstName()</td>
 * <td>the <i>given_name</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getFamilyName()</td>
 * <td>the <i>family_name</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getDisplayName()</td>
 * <td>the <i>display_name</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getUsername()</td>
 * <td>null</td>
 * </tr>
 * <tr>
 * <td>Gender getGender()</td>
 * <td>{@link org.scribe.up.profile.Gender#UNSPECIFIED}</td>
 * </tr>
 * <tr>
 * <td>Locale getLocale()</td>
 * <td>the <i>country</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getPictureUrl()</td>
 * <td>the <i>thumbnailUrl</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getProfileUrl()</td>
 * <td>the <i>profileUrl</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getLocation()</td>
 * <td>null</td>
 * </tr>
 * <tr>
 * <th colspan="2">More specific attributes</th>
 * </tr>
 * <tr>
 * <td>Boolean getIsViewer()</td>
 * <td>the <i>isViewer</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getFormatted()</td>
 * <td>the <i>formatted</i> attribute</td>
 * </tr>
 * <tr>
 * <td>List&lt;GoogleObject&gt; getUrls()</td>
 * <td>the <i>urls</i> attribute</td>
 * </tr>
 * <tr>
 * <td>List&lt;GoogleObject&gt; getPhotos()</td>
 * <td>the <i>photos</i> attribute</td>
 * </tr>
 * </table>
 * 
 * @see org.scribe.up.provider.impl.GoogleProvider
 * @author Jerome Leleu
 * @since 1.1.0
 */
@SuppressWarnings("unchecked")
public class GoogleProfile extends BaseOAuthProfile implements CommonProfile {
    
    private static final long serialVersionUID = 5674515806714296361L;
    
    @Override
    protected AttributesDefinition getAttributesDefinition() {
        return OAuthAttributesDefinitions.googleDefinition;
    }
    
    public String getEmail() {
        return null;
    }
    
    public String getFirstName() {
        return (String) get(GoogleAttributesDefinition.GIVEN_NAME);
    }
    
    public String getFamilyName() {
        return (String) get(GoogleAttributesDefinition.FAMILY_NAME);
    }
    
    public String getDisplayName() {
        return (String) get(GoogleAttributesDefinition.DISPLAY_NAME);
    }
    
    public String getUsername() {
        return null;
    }
    
    public Gender getGender() {
        return Gender.UNSPECIFIED;
    }
    
    public Locale getLocale() {
        return null;
    }
    
    public String getPictureUrl() {
        return (String) get(GoogleAttributesDefinition.THUMBNAIL_URL);
    }
    
    public String getProfileUrl() {
        return (String) get(GoogleAttributesDefinition.PROFILE_URL);
    }
    
    public String getLocation() {
        return null;
    }
    
    public Boolean getIsViewer() {
        return (Boolean) get(GoogleAttributesDefinition.IS_VIEWER);
    }
    
    public String getFormatted() {
        return (String) get(GoogleAttributesDefinition.FORMATTED);
    }
    
    public List<GoogleObject> getUrls() {
        return (List<GoogleObject>) get(GoogleAttributesDefinition.URLS);
    }
    
    public List<GoogleObject> getPhotos() {
        return (List<GoogleObject>) get(GoogleAttributesDefinition.PHOTOS);
    }
}

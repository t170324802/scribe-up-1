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
package org.scribe.up.test.profile.converter;

import junit.framework.TestCase;

import org.scribe.up.profile.Gender;
import org.scribe.up.profile.converter.GenderConverter;

/**
 * This class tests the {@link org.scribe.up.profile.converter.GenderConverter} class.
 * 
 * @author Jerome Leleu
 * @since 1.0.0
 */
public final class TestGenderConverter extends TestCase {
    
    private static final String MALE = "m";
    
    private static final String FEMALE = "f";
    
    private static final String UNSPECIFIED = "unspecified";
    
    private GenderConverter converter = new GenderConverter(MALE, FEMALE);
    
    public void testNull() {
        assertNull(converter.convert(null));
    }
    
    public void testNotAString() {
        assertNull(converter.convert(Boolean.TRUE));
    }
    
    public void testMale() {
        assertEquals(Gender.MALE, converter.convert(MALE));
    }
    
    public void testFemale() {
        assertEquals(Gender.FEMALE, converter.convert(FEMALE));
    }
    
    public void testUnspecified() {
        assertEquals(Gender.UNSPECIFIED, converter.convert(UNSPECIFIED));
    }
    
    public void testMaleEnum() {
        assertEquals(Gender.MALE, converter.convert(Gender.MALE.toString()));
    }
    
    public void testFemaleEnum() {
        assertEquals(Gender.FEMALE, converter.convert(Gender.FEMALE.toString()));
    }
    
    public void testUnspecifiedEnum() {
        assertEquals(Gender.UNSPECIFIED, converter.convert(Gender.UNSPECIFIED.toString()));
    }
}

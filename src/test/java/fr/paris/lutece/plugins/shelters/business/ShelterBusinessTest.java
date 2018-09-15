/*
 * Copyright (c) 2002-2016, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

package fr.paris.lutece.plugins.shelters.business;

import fr.paris.lutece.test.LuteceTestCase;


public class ShelterBusinessTest extends LuteceTestCase
{
    private final static String NAME1 = "Name1";
    private final static String NAME2 = "Name2";
    private final static String DESCRIPTION1 = "Description1";
    private final static String DESCRIPTION2 = "Description2";
    private final static String EMAIL1 = "Email1";
    private final static String EMAIL2 = "Email2";
    private final static String WEBSITE1 = "WebSite1";
    private final static String WEBSITE2 = "WebSite2";
    private final static String WORKGROUPKEY1 = "WorkgroupKey1";
    private final static String WORKGROUPKEY2 = "WorkgroupKey2";
	private final static boolean REMINDERSTATUS1 = true;
    private final static boolean REMINDERSTATUS2 = false;
    private final static int BEDCAPACITY1 = 1;
    private final static int BEDCAPACITY2 = 2;
    private final static String PHONENUMBER1 = "PhoneNumber1";
    private final static String PHONENUMBER2 = "PhoneNumber2";
    private final static int LOCATIONLAT1 = 1;
    private final static int LOCATIONLAT2 = 2;
    private final static int LOCATIONLON1 = 1;
    private final static int LOCATIONLON2 = 2;

    public void testBusiness(  )
    {
        // Initialize an object
        Shelter shelter = new Shelter();
        shelter.setName( NAME1 );
        shelter.setDescription( DESCRIPTION1 );
        shelter.setEmail( EMAIL1 );
        shelter.setWebSite( WEBSITE1 );
        shelter.setWorkgroupKey( WORKGROUPKEY1 );
        shelter.setReminderStatus( REMINDERSTATUS1 );
        shelter.setBedCapacity( BEDCAPACITY1 );
        shelter.setPhoneNumber( PHONENUMBER1 );
        shelter.setLocationLat( LOCATIONLAT1 );
        shelter.setLocationLon( LOCATIONLON1 );

        // Create test
        ShelterHome.create( shelter );
        Shelter shelterStored = ShelterHome.findByPrimaryKey( shelter.getId( ) );
        assertEquals( shelterStored.getName() , shelter.getName( ) );
        assertEquals( shelterStored.getDescription() , shelter.getDescription( ) );
        assertEquals( shelterStored.getEmail() , shelter.getEmail( ) );
        assertEquals( shelterStored.getWebSite() , shelter.getWebSite( ) );
        assertEquals( shelterStored.getWorkgroupKey() , shelter.getWorkgroupKey( ) );
        assertEquals( shelterStored.getReminderStatus() , shelter.getReminderStatus( ) );
        assertEquals( shelterStored.getBedCapacity() , shelter.getBedCapacity( ) );
        assertEquals( shelterStored.getPhoneNumber() , shelter.getPhoneNumber( ) );
        assertEquals( shelterStored.getLocationLat() , shelter.getLocationLat( ) );
        assertEquals( shelterStored.getLocationLon() , shelter.getLocationLon( ) );

        // Update test
        shelter.setName( NAME2 );
        shelter.setDescription( DESCRIPTION2 );
        shelter.setEmail( EMAIL2 );
        shelter.setWebSite( WEBSITE2 );
        shelter.setWorkgroupKey( WORKGROUPKEY2 );
        shelter.setReminderStatus( REMINDERSTATUS2 );
        shelter.setBedCapacity( BEDCAPACITY2 );
        shelter.setPhoneNumber( PHONENUMBER2 );
        shelter.setLocationLat( LOCATIONLAT2 );
        shelter.setLocationLon( LOCATIONLON2 );
        ShelterHome.update( shelter );
        shelterStored = ShelterHome.findByPrimaryKey( shelter.getId( ) );
        assertEquals( shelterStored.getName() , shelter.getName( ) );
        assertEquals( shelterStored.getDescription() , shelter.getDescription( ) );
        assertEquals( shelterStored.getEmail() , shelter.getEmail( ) );
        assertEquals( shelterStored.getWebSite() , shelter.getWebSite( ) );
        assertEquals( shelterStored.getWorkgroupKey() , shelter.getWorkgroupKey( ) );
        assertEquals( shelterStored.getReminderStatus() , shelter.getReminderStatus( ) );
        assertEquals( shelterStored.getBedCapacity() , shelter.getBedCapacity( ) );
        assertEquals( shelterStored.getPhoneNumber() , shelter.getPhoneNumber( ) );
        assertEquals( shelterStored.getLocationLat() , shelter.getLocationLat( ) );
        assertEquals( shelterStored.getLocationLon() , shelter.getLocationLon( ) );

        // List test
        ShelterHome.getSheltersList();

        // Delete test
        ShelterHome.remove( shelter.getId( ) );
        shelterStored = ShelterHome.findByPrimaryKey( shelter.getId( ) );
        assertNull( shelterStored );
        
    }

}
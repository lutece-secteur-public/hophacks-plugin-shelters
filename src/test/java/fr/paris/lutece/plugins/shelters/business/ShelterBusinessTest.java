/*
 * Copyright (c) 2002-2018, Mairie de Paris
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


/**
 * This is the business class test for the object Shelter
 */
public class ShelterBusinessTest extends LuteceTestCase
{
    private static final String NAME1 = "Name1";
    private static final String NAME2 = "Name2";
    private static final String DESCRIPTION1 = "Description1";
    private static final String DESCRIPTION2 = "Description2";
    private static final String EMAIL1 = "Email1";
    private static final String EMAIL2 = "Email2";
    private static final String WEBSITE1 = "WebSite1";
    private static final String WEBSITE2 = "WebSite2";
    private static final String WORKGROUPKEY1 = "WorkgroupKey1";
    private static final String WORKGROUPKEY2 = "WorkgroupKey2";
	private static final boolean REMINDERSTATUS1 = true;
    private static final boolean REMINDERSTATUS2 = false;
    private static final int BEDCAPACITY1 = 1;
    private static final int BEDCAPACITY2 = 2;
    private static final String PHONENUMBER1 = "PhoneNumber1";
    private static final String PHONENUMBER2 = "PhoneNumber2";
    private static final int LOCATIONLAT1 = 1;
    private static final int LOCATIONLAT2 = 2;
    private static final int LOCATIONLON1 = 1;
    private static final int LOCATIONLON2 = 2;
    private static final String ADDRESS1 = "Address1";
    private static final String ADDRESS2 = "Address2";

	/**
	* test Shelter
	*/
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
        shelter.setAddress( ADDRESS1 );

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
        assertEquals( shelterStored.getAddress() , shelter.getAddress( ) );

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
        shelter.setAddress( ADDRESS2 );
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
        assertEquals( shelterStored.getAddress() , shelter.getAddress( ) );

        // List test
        ShelterHome.getSheltersList();

        // Delete test
        ShelterHome.remove( shelter.getId( ) );
        shelterStored = ShelterHome.findByPrimaryKey( shelter.getId( ) );
        assertNull( shelterStored );
        
    }

}
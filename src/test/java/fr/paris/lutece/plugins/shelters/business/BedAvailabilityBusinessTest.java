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
 * This is the business class test for the object BedAvailability
 */
public class BedAvailabilityBusinessTest extends LuteceTestCase
{
    private static final String DATECODE1 = "DateCode1";
    private static final String DATECODE2 = "DateCode2";
    private static final int BEDAVAILABLECOUNT1 = 1;
    private static final int BEDAVAILABLECOUNT2 = 2;
    private static final int TOTALBEDCAPACITY1 = 1;
    private static final int TOTALBEDCAPACITY2 = 2;

	/**
	* test BedAvailability
	*/
    public void testBusiness(  )
    {
        // Initialize an object
        BedAvailability bedAvailability = new BedAvailability();
        bedAvailability.setDateCode( DATECODE1 );
        bedAvailability.setBedAvailableCount( BEDAVAILABLECOUNT1 );
        bedAvailability.setTotalBedCapacity( TOTALBEDCAPACITY1 );

        // Create test
        BedAvailabilityHome.create( bedAvailability );
        BedAvailability bedAvailabilityStored = BedAvailabilityHome.findByPrimaryKey( bedAvailability.getShelterId( ), bedAvailability.getDateCode() );
        assertEquals( bedAvailabilityStored.getDateCode() , bedAvailability.getDateCode( ) );
        assertEquals( bedAvailabilityStored.getBedAvailableCount() , bedAvailability.getBedAvailableCount( ) );
        assertEquals( bedAvailabilityStored.getTotalBedCapacity() , bedAvailability.getTotalBedCapacity( ) );

        // Update test
        bedAvailability.setDateCode( DATECODE2 );
        bedAvailability.setBedAvailableCount( BEDAVAILABLECOUNT2 );
        bedAvailability.setTotalBedCapacity( TOTALBEDCAPACITY2 );
        BedAvailabilityHome.update( bedAvailability );
        bedAvailabilityStored = BedAvailabilityHome.findByPrimaryKey( bedAvailability.getShelterId( ) , bedAvailability.getDateCode());
        assertEquals( bedAvailabilityStored.getDateCode() , bedAvailability.getDateCode( ) );
        assertEquals( bedAvailabilityStored.getBedAvailableCount() , bedAvailability.getBedAvailableCount( ) );
        assertEquals( bedAvailabilityStored.getTotalBedCapacity() , bedAvailability.getTotalBedCapacity( ) );

        // List test
        BedAvailabilityHome.getBedAvailabilitysList();

        // Delete test
        BedAvailabilityHome.removeAllByShelter( bedAvailability.getShelterId( ) );
        bedAvailabilityStored = BedAvailabilityHome.findByPrimaryKey( bedAvailability.getShelterId( ), bedAvailability.getDateCode() );
        assertNull( bedAvailabilityStored );
        
    }

}
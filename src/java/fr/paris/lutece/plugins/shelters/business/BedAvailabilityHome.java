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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for BedAvailability objects
 */
public final class BedAvailabilityHome
{
    // Static variable pointed at the DAO instance
    private static IBedAvailabilityDAO _dao = SpringContextService.getBean( "shelters.bedAvailabilityDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "shelters" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private BedAvailabilityHome(  )
    {
    }

    /**
     * Create an instance of the bedAvailability class
     * @param bedAvailability The instance of the BedAvailability which contains the informations to store
     * @return The  instance of bedAvailability which has been created with its primary key.
     */
    public static BedAvailability create( BedAvailability bedAvailability )
    {
        _dao.insert( bedAvailability, _plugin );

        return bedAvailability;
    }

    /**
     * Update of the bedAvailability which is specified in parameter
     * @param bedAvailability The instance of the BedAvailability which contains the data to store
     * @return The instance of the  bedAvailability which has been updated
     */
    public static BedAvailability update( BedAvailability bedAvailability )
    {
        _dao.store( bedAvailability, _plugin );

        return bedAvailability;
    }

    /**
     * Remove the bedAvailability whose identifier is specified in parameter
     * @param nKey The bedAvailability Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    /**
     * Returns an instance of a bedAvailability whose identifier is specified in parameter
     * @param nShelterId The bedAvailability primary key
     * @param strDateCode
     * @return an instance of BedAvailability
     */
    public static BedAvailability findByPrimaryKey( int nShelterId , String strDateCode )
    {
        return _dao.load( nShelterId, strDateCode, _plugin );
    }

    /**
     * Load the data of all the bedAvailability objects and returns them as a list
     * @return the list which contains the data of all the bedAvailability objects
     */
    public static List<BedAvailability> getBedAvailabilitysList( )
    {
        return _dao.selectBedAvailabilitysList( _plugin );
    }
    
    /**
     * Load the id of all the bedAvailability objects and returns them as a list
     * @return the list which contains the id of all the bedAvailability objects
     */
    public static List<Integer> getIdBedAvailabilitysList( )
    {
        return _dao.selectIdBedAvailabilitysList( _plugin );
    }
    
    /**
     * Load the data of all the bedAvailability objects and returns them as a referenceList
     * @return the referenceList which contains the data of all the bedAvailability objects
     */
    public static ReferenceList getBedAvailabilitysReferenceList( )
    {
        return _dao.selectBedAvailabilitysReferenceList( _plugin );
    }
}


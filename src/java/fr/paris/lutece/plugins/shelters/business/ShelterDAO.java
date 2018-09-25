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
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for Shelter objects
 */
public final class ShelterDAO implements IShelterDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_shelter, name, description, email, web_site, workgroup_key, reminder_status, bed_capacity, phone_number, location_lat, location_lon, address FROM shelters_shelter WHERE id_shelter = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO shelters_shelter ( name, description, email, web_site, workgroup_key, reminder_status, bed_capacity, phone_number, location_lat, location_lon, address ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM shelters_shelter WHERE id_shelter = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE shelters_shelter SET id_shelter = ?, name = ?, description = ?, email = ?, web_site = ?, workgroup_key = ?, reminder_status = ?, bed_capacity = ?, phone_number = ?, location_lat = ?, location_lon = ?, address = ? WHERE id_shelter = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_shelter, name, description, email, web_site, workgroup_key, reminder_status, bed_capacity, phone_number, location_lat, location_lon, address FROM shelters_shelter";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_shelter FROM shelters_shelter";
    private static final String SQL_QUERY_SELECT_BY_ADMIN_USER = "SELECT a.id_shelter, a.name, a.description, a.email, a.web_site, a.workgroup_key, a.reminder_status, a.bed_capacity, a.phone_number, a.location_lat, a.location_lon, a.address "
            + " FROM shelters_shelter a, core_admin_workgroup_user b WHERE a.workgroup_key = b.workgroup_key AND b.id_user = ?";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Shelter shelter, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin );
        try
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++ , shelter.getName( ) );
            daoUtil.setString( nIndex++ , shelter.getDescription( ) );
            daoUtil.setString( nIndex++ , shelter.getEmail( ) );
            daoUtil.setString( nIndex++ , shelter.getWebSite( ) );
            daoUtil.setString( nIndex++ , shelter.getWorkgroupKey( ) );
            daoUtil.setBoolean( nIndex++ , shelter.getReminderStatus( ) );
            daoUtil.setInt( nIndex++ , shelter.getBedCapacity( ) );
            daoUtil.setString( nIndex++ , shelter.getPhoneNumber( ) );
            daoUtil.setFloat( nIndex++ , shelter.getLocationLat( ) );
            daoUtil.setFloat( nIndex++ , shelter.getLocationLon( ) );
            daoUtil.setString( nIndex++ , shelter.getAddress( ) );
            
            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) ) 
            {
                shelter.setId( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
        finally
        {
            daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Shelter load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeQuery( );
        Shelter shelter = null;

        if ( daoUtil.next( ) )
        {
            shelter = new Shelter();
            int nIndex = 1;
            
            shelter.setId( daoUtil.getInt( nIndex++ ) );
            shelter.setName( daoUtil.getString( nIndex++ ) );
            shelter.setDescription( daoUtil.getString( nIndex++ ) );
            shelter.setEmail( daoUtil.getString( nIndex++ ) );
            shelter.setWebSite( daoUtil.getString( nIndex++ ) );
            shelter.setWorkgroupKey( daoUtil.getString( nIndex++ ) );
            shelter.setReminderStatus( daoUtil.getBoolean( nIndex++ ) );
            shelter.setBedCapacity( daoUtil.getInt( nIndex++ ) );
            shelter.setPhoneNumber( daoUtil.getString( nIndex++ ) );
            shelter.setLocationLat( daoUtil.getFloat(nIndex++ ) );
            shelter.setLocationLon( daoUtil.getFloat( nIndex++ ) );
            shelter.setAddress( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );
        return shelter;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Shelter shelter, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , shelter.getId( ) );
        daoUtil.setString( nIndex++ , shelter.getName( ) );
        daoUtil.setString( nIndex++ , shelter.getDescription( ) );
        daoUtil.setString( nIndex++ , shelter.getEmail( ) );
        daoUtil.setString( nIndex++ , shelter.getWebSite( ) );
        daoUtil.setString( nIndex++ , shelter.getWorkgroupKey( ) );
        daoUtil.setBoolean( nIndex++ , shelter.getReminderStatus( ) );
        daoUtil.setInt( nIndex++ , shelter.getBedCapacity( ) );
        daoUtil.setString( nIndex++ , shelter.getPhoneNumber( ) );
        daoUtil.setFloat( nIndex++ , shelter.getLocationLat( ) );
        daoUtil.setFloat( nIndex++ , shelter.getLocationLon( ) );
        daoUtil.setString( nIndex++ , shelter.getAddress( ) );
        daoUtil.setInt( nIndex , shelter.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Shelter> selectSheltersList( Plugin plugin )
    {
        List<Shelter> shelterList = new ArrayList<Shelter>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Shelter shelter = new Shelter(  );
            int nIndex = 1;
            
            shelter.setId( daoUtil.getInt( nIndex++ ) );
            shelter.setName( daoUtil.getString( nIndex++ ) );
            shelter.setDescription( daoUtil.getString( nIndex++ ) );
            shelter.setEmail( daoUtil.getString( nIndex++ ) );
            shelter.setWebSite( daoUtil.getString( nIndex++ ) );
            shelter.setWorkgroupKey( daoUtil.getString( nIndex++ ) );
            shelter.setReminderStatus( daoUtil.getBoolean( nIndex++ ) );
            shelter.setBedCapacity( daoUtil.getInt( nIndex++ ) );
            shelter.setPhoneNumber( daoUtil.getString( nIndex++ ) );
            shelter.setLocationLat( daoUtil.getFloat( nIndex++ ) );
            shelter.setLocationLon( daoUtil.getFloat( nIndex++ ) );
            shelter.setAddress( daoUtil.getString( nIndex++ ) );

            shelterList.add( shelter );
        }

        daoUtil.free( );
        return shelterList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdSheltersList( Plugin plugin )
    {
        List<Integer> shelterList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            shelterList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return shelterList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectSheltersReferenceList( Plugin plugin )
    {
        ReferenceList shelterList = new ReferenceList();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            shelterList.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return shelterList;
    }

    @Override
    public List<Shelter> selectSheltersByAdminUser(int nIdUser, Plugin plugin)
    {
        List<Shelter> shelterList = new ArrayList<Shelter>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_ADMIN_USER, plugin );
        daoUtil.setInt( 1, nIdUser);
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Shelter shelter = new Shelter(  );
            int nIndex = 1;
            
            shelter.setId( daoUtil.getInt( nIndex++ ) );
            shelter.setName( daoUtil.getString( nIndex++ ) );
            shelter.setDescription( daoUtil.getString( nIndex++ ) );
            shelter.setEmail( daoUtil.getString( nIndex++ ) );
            shelter.setWebSite( daoUtil.getString( nIndex++ ) );
            shelter.setWorkgroupKey( daoUtil.getString( nIndex++ ) );
            shelter.setReminderStatus( daoUtil.getBoolean( nIndex++ ) );
            shelter.setBedCapacity( daoUtil.getInt( nIndex++ ) );
            shelter.setPhoneNumber( daoUtil.getString( nIndex++ ) );
            shelter.setLocationLat( daoUtil.getFloat( nIndex++ ) );
            shelter.setLocationLon( daoUtil.getFloat( nIndex++ ) );
            shelter.setAddress( daoUtil.getString( nIndex++ ) );

            shelterList.add( shelter );
        }

        daoUtil.free( );
        return shelterList;
    }
}

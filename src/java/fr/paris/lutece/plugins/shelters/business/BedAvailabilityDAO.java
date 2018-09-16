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
 * This class provides Data Access methods for BedAvailability objects
 */
public final class BedAvailabilityDAO implements IBedAvailabilityDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_shelter, date_code, bed_available_count, total_bed_capacity FROM shelters_bed_availability WHERE id_shelter = ? AND date_code = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO shelters_bed_availability ( id_shelter, date_code, bed_available_count, total_bed_capacity ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM shelters_bed_availability WHERE id_shelter = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE shelters_bed_availability SET bed_available_count = ?, total_bed_capacity = ? WHERE id_shelter = ? AND date_code = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_shelter, date_code, bed_available_count, total_bed_capacity FROM shelters_bed_availability";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_shelter FROM shelters_bed_availability";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( BedAvailability bedAvailability, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin );
        try
        {
            int nIndex = 1;
            daoUtil.setInt( nIndex++ , bedAvailability.getShelterId() );
            daoUtil.setString( nIndex++ , bedAvailability.getDateCode( ) );
            daoUtil.setInt( nIndex++ , bedAvailability.getBedAvailableCount( ) );
            daoUtil.setInt( nIndex++ , bedAvailability.getTotalBedCapacity( ) );
            
            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) ) 
            {
                bedAvailability.setShelterId( daoUtil.getGeneratedKeyInt( 1 ) );
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
    public BedAvailability load( int nKey, String strDateCode, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.setString( 2, strDateCode );
        daoUtil.executeQuery( );
        BedAvailability bedAvailability = null;

        if ( daoUtil.next( ) )
        {
            bedAvailability = new BedAvailability();
            int nIndex = 1;
            
            bedAvailability.setShelterId( daoUtil.getInt( nIndex++ ) );
            bedAvailability.setDateCode( daoUtil.getString( nIndex++ ) );
            bedAvailability.setBedAvailableCount( daoUtil.getInt( nIndex++ ) );
            bedAvailability.setTotalBedCapacity( daoUtil.getInt( nIndex++ ) );
        }

        daoUtil.free( );
        return bedAvailability;
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
    public void store( BedAvailability bedAvailability, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , bedAvailability.getBedAvailableCount( ) );
        daoUtil.setInt( nIndex++ , bedAvailability.getTotalBedCapacity( ) );
        daoUtil.setInt( nIndex++ , bedAvailability.getShelterId( ) );
        daoUtil.setString( nIndex++ , bedAvailability.getDateCode( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<BedAvailability> selectBedAvailabilitysList( Plugin plugin )
    {
        List<BedAvailability> bedAvailabilityList = new ArrayList<BedAvailability>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            BedAvailability bedAvailability = new BedAvailability(  );
            int nIndex = 1;
            
            bedAvailability.setShelterId( daoUtil.getInt( nIndex++ ) );
            bedAvailability.setDateCode( daoUtil.getString( nIndex++ ) );
            bedAvailability.setBedAvailableCount( daoUtil.getInt( nIndex++ ) );
            bedAvailability.setTotalBedCapacity( daoUtil.getInt( nIndex++ ) );

            bedAvailabilityList.add( bedAvailability );
        }

        daoUtil.free( );
        return bedAvailabilityList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdBedAvailabilitysList( Plugin plugin )
    {
        List<Integer> bedAvailabilityList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            bedAvailabilityList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return bedAvailabilityList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectBedAvailabilitysReferenceList( Plugin plugin )
    {
        ReferenceList bedAvailabilityList = new ReferenceList();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            bedAvailabilityList.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return bedAvailabilityList;
    }
}

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

import fr.paris.lutece.portal.business.file.File;
import fr.paris.lutece.portal.business.file.FileHome;
import fr.paris.lutece.portal.business.physicalfile.PhysicalFile;
import fr.paris.lutece.portal.business.physicalfile.PhysicalFileHome;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;
import java.util.Base64;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for Shelter objects
 */
public final class ShelterHome
{
    // Static variable pointed at the DAO instance
    private static IShelterDAO _dao = SpringContextService.getBean( "shelters.shelterDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "shelters" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private ShelterHome(  )
    {
    }

    /**
     * Create an instance of the shelter class
     * @param shelter The instance of the Shelter which contains the informations to store
     * @return The  instance of shelter which has been created with its primary key.
     */
    public static Shelter create( Shelter shelter )
    {
        _dao.insert( shelter, _plugin );

        return shelter;
    }

    /**
     * Update of the shelter which is specified in parameter
     * @param shelter The instance of the Shelter which contains the data to store
     * @return The instance of the  shelter which has been updated
     */
    public static Shelter update( Shelter shelter )
    {
        _dao.store( shelter, _plugin );

        return shelter;
    }

    /**
     * Remove the shelter whose identifier is specified in parameter
     * @param nKey The shelter Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
        
        BedAvailabilityHome.removeAllByShelter( nKey );
    }

    /**
     * Returns an instance of a shelter whose identifier is specified in parameter
     * @param nKey The shelter primary key
     * @return an instance of Shelter
     */
    public static Shelter findByPrimaryKey( int nKey )
    {
        Shelter shelter = _dao.load( nKey, _plugin ); 
                
        addFileContent( shelter );
        
        return shelter;
    }

    /**
     * Load the data of all the shelter objects and returns them as a list
     * @return the list which contains the data of all the shelter objects
     */
    public static List<Shelter> getSheltersList( )
    {
        List<Shelter> shelterList = _dao.selectSheltersList( _plugin );
        
        for (Shelter shelter : shelterList)
        {
            addFileContent( shelter );
        }
        
        return shelterList ;
    }
    
    /**
     * Load the id of all the shelter objects and returns them as a list
     * @return the list which contains the id of all the shelter objects
     */
    public static List<Integer> getIdSheltersList( )
    {
        return _dao.selectIdSheltersList( _plugin );
    }
        
    /**
     * Load the data of all the shelter objects and returns them as a referenceList
     * @return the referenceList which contains the data of all the shelter objects
     */
    public static ReferenceList getSheltersReferenceList( )
    {
        return _dao.selectSheltersReferenceList( _plugin );
    }
    
    /**
     * Load the data of all the shelter objects and returns them as a list
     * @return the list which contains the data of all the shelter objects
     */
    public static List<Shelter> getSheltersByAdminUser( int nIdUser )
    {
        List<Shelter> shelterList = _dao.selectSheltersByAdminUser( nIdUser ,  _plugin );
        
        for (Shelter shelter : shelterList)
        {
            addFileContent( shelter );
        }
        
        return shelterList ;
    }
    
    /**
     * add the image content in base64 and type 
     * to the shelter
     * 
     * @param shelter 
     */
    public static void addFileContent( Shelter shelter )
    {
        
        if ( shelter != null )
        {
            File file ;
            file = FileHome.findByPrimaryKey( shelter.getPictureId( ) );
            
            if ( file != null ) 
            {
                PhysicalFile pf = PhysicalFileHome.findByPrimaryKey(shelter.getPictureId( ) );
                
                if ( pf != null && pf.getValue( ) !=null) 
                {
                    String base64FileContent = Base64.getEncoder().encodeToString( pf.getValue( ) );
                    shelter.setFileContentBase64( base64FileContent );
                    shelter.setFileType(  file.getMimeType( ) );
                }
            }
        }
    }
}


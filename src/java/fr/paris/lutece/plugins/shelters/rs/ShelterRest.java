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
package fr.paris.lutece.plugins.shelters.rs;

import fr.paris.lutece.plugins.shelters.business.Shelter;
import fr.paris.lutece.plugins.shelters.business.ShelterHome;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.plugins.shelters.business.ShelterAvailability;
import fr.paris.lutece.plugins.shelters.service.DateService;
import fr.paris.lutece.plugins.shelters.service.ShelterAvailabilityService;
import fr.paris.lutece.portal.web.l10n.LocaleService;
import fr.paris.lutece.util.json.ErrorJsonResponse;
import fr.paris.lutece.util.json.JsonResponse;
import fr.paris.lutece.util.json.JsonUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Locale;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * ShelterRest
 */
@Path( RestConstants.BASE_PATH + Constants.API_PATH + Constants.VERSION_PATH + Constants.SHELTER_PATH )
public class ShelterRest
{
    private static final int VERSION_1 = 1;
    private final Logger _logger = Logger.getLogger( RestConstants.REST_LOGGER );
    
    /**
     * Get Shelter List
     * @param nVersion the API version
     * @return the Shelter List
     */
    @GET
    @Path( StringUtils.EMPTY )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getShelterList( @PathParam( Constants.VERSION ) Integer nVersion )
    {
        switch ( nVersion )
        {
            case VERSION_1:
                return getShelterListV1( );
            default:
                break;
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) )
                .build( );
    }
    
    /**
     * Get Shelter List V1
     * @return the Shelter List for the version 1
     */
    private Response getShelterListV1( )
    {
        List<ShelterAvailability> listShelters = ShelterAvailabilityService.getSheltersAvailability( ShelterHome.getSheltersList( ) , DateService.getToday() , LocaleService.getDefault());
        
        if( listShelters.isEmpty( ) )
        {
            return Response.status( Response.Status.NO_CONTENT )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( Constants.EMPTY_OBJECT ) ) )
                .build( );
        }
        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( listShelters ) ) )
                .build( );
    }
    
    /**
     * Create Shelter
     * @param nVersion the API version
     * @param name the name
     * @param description the description
     * @param email the email
     * @param web_site the web_site
     * @param workgroup_key the workgroup_key
     * @param reminder_status the reminder_status
     * @param bed_capacity the bed_capacity
     * @param phone_number the phone_number
     * @param location_lat the location_lat
     * @param location_lon the location_lon
     * @param address the address
     * @return the Shelter if created
     */
    @POST
    @Path( StringUtils.EMPTY )
    @Produces( MediaType.APPLICATION_JSON )
    public Response createShelter(
    @FormParam( Constants.SHELTER_ATTRIBUTE_NAME ) String name,
    @FormParam( Constants.SHELTER_ATTRIBUTE_DESCRIPTION ) String description,
    @FormParam( Constants.SHELTER_ATTRIBUTE_EMAIL ) String email,
    @FormParam( Constants.SHELTER_ATTRIBUTE_WEB_SITE ) String web_site,
    @FormParam( Constants.SHELTER_ATTRIBUTE_WORKGROUP_KEY ) String workgroup_key,
    @FormParam( Constants.SHELTER_ATTRIBUTE_REMINDER_STATUS ) String reminder_status,
    @FormParam( Constants.SHELTER_ATTRIBUTE_BED_CAPACITY ) String bed_capacity,
    @FormParam( Constants.SHELTER_ATTRIBUTE_PHONE_NUMBER ) String phone_number,
    @FormParam( Constants.SHELTER_ATTRIBUTE_LOCATION_LAT ) String location_lat,
    @FormParam( Constants.SHELTER_ATTRIBUTE_LOCATION_LON ) String location_lon,
    @FormParam( Constants.SHELTER_ATTRIBUTE_ADDRESS ) String address,
    @PathParam( Constants.VERSION ) Integer nVersion )
    {
        switch ( nVersion )
        {
            case VERSION_1:
                return createShelterV1( name, description, email, web_site, workgroup_key, reminder_status, bed_capacity, phone_number, location_lat, location_lon, address );
            default:
                break;
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) )
                .build( );
    }
    
    /**
     * Create Shelter V1
     * @param name the name
     * @param description the description
     * @param email the email
     * @param web_site the web_site
     * @param workgroup_key the workgroup_key
     * @param reminder_status the reminder_status
     * @param bed_capacity the bed_capacity
     * @param phone_number the phone_number
     * @param location_lat the location_lat
     * @param location_lon the location_lon
     * @param address the address
     * @return the Shelter if created for the version 1
     */
    private Response createShelterV1( String name, String description, String email, String web_site, String workgroup_key, String reminder_status, String bed_capacity, String phone_number, String location_lat, String location_lon, String address )
    {
        if ( StringUtils.isEmpty( name ) || StringUtils.isEmpty( description ) || StringUtils.isEmpty( email ) || StringUtils.isEmpty( web_site ) || StringUtils.isEmpty( workgroup_key ) || StringUtils.isEmpty( reminder_status ) || StringUtils.isEmpty( bed_capacity ) || StringUtils.isEmpty( phone_number ) || StringUtils.isEmpty( location_lat ) || StringUtils.isEmpty( location_lon ) || StringUtils.isEmpty( address ) )
        {
            _logger.error( Constants.ERROR_BAD_REQUEST_EMPTY_PARAMETER );
            return Response.status( Response.Status.BAD_REQUEST )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.BAD_REQUEST.name( ), Constants.ERROR_BAD_REQUEST_EMPTY_PARAMETER ) ) )
                    .build( );
        }
        
        Shelter shelter = new Shelter( );
    	shelter.setName( name );
    	shelter.setDescription( description );
    	shelter.setEmail( email );
    	shelter.setWebSite( web_site );
    	shelter.setWorkgroupKey( workgroup_key );
	    shelter.setReminderStatus( Boolean.parseBoolean( reminder_status ) );
	    shelter.setBedCapacity( Integer.parseInt( bed_capacity ) );
    	shelter.setPhoneNumber( phone_number );
	    shelter.setLocationLat( Integer.parseInt( location_lat ) );
	    shelter.setLocationLon( Integer.parseInt( location_lon ) );
    	shelter.setAddress( address );
        ShelterHome.create( shelter );
        
        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( shelter ) ) )
                .build( );
    }
    
    /**
     * Modify Shelter
     * @param nVersion the API version
     * @param id the id
     * @param name the name
     * @param description the description
     * @param email the email
     * @param web_site the web_site
     * @param workgroup_key the workgroup_key
     * @param reminder_status the reminder_status
     * @param bed_capacity the bed_capacity
     * @param phone_number the phone_number
     * @param location_lat the location_lat
     * @param location_lon the location_lon
     * @param address the address
     * @return the Shelter if modified
     */
    @PUT
    @Path( Constants.ID_PATH )
    @Produces( MediaType.APPLICATION_JSON )
    public Response modifyShelter(
    @PathParam( Constants.ID ) Integer id,
    @FormParam( Constants.SHELTER_ATTRIBUTE_NAME ) String name,
    @FormParam( Constants.SHELTER_ATTRIBUTE_DESCRIPTION ) String description,
    @FormParam( Constants.SHELTER_ATTRIBUTE_EMAIL ) String email,
    @FormParam( Constants.SHELTER_ATTRIBUTE_WEB_SITE ) String web_site,
    @FormParam( Constants.SHELTER_ATTRIBUTE_WORKGROUP_KEY ) String workgroup_key,
    @FormParam( Constants.SHELTER_ATTRIBUTE_REMINDER_STATUS ) String reminder_status,
    @FormParam( Constants.SHELTER_ATTRIBUTE_BED_CAPACITY ) String bed_capacity,
    @FormParam( Constants.SHELTER_ATTRIBUTE_PHONE_NUMBER ) String phone_number,
    @FormParam( Constants.SHELTER_ATTRIBUTE_LOCATION_LAT ) String location_lat,
    @FormParam( Constants.SHELTER_ATTRIBUTE_LOCATION_LON ) String location_lon,
    @FormParam( Constants.SHELTER_ATTRIBUTE_ADDRESS ) String address,
    @PathParam( Constants.VERSION ) Integer nVersion )
    {
        switch ( nVersion )
        {
            case VERSION_1:
                return modifyShelterV1( id, name, description, email, web_site, workgroup_key, reminder_status, bed_capacity, phone_number, location_lat, location_lon, address );
            default:
                break;
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) )
                .build( );
    }
    
    /**
     * Modify Shelter V1
     * @param id the id
     * @param name the name
     * @param description the description
     * @param email the email
     * @param web_site the web_site
     * @param workgroup_key the workgroup_key
     * @param reminder_status the reminder_status
     * @param bed_capacity the bed_capacity
     * @param phone_number the phone_number
     * @param location_lat the location_lat
     * @param location_lon the location_lon
     * @param address the address
     * @return the Shelter if modified for the version 1
     */
    private Response modifyShelterV1( Integer id, String name, String description, String email, String web_site, String workgroup_key, String reminder_status, String bed_capacity, String phone_number, String location_lat, String location_lon, String address )
    {
        if ( StringUtils.isEmpty( name ) || StringUtils.isEmpty( description ) || StringUtils.isEmpty( email ) || StringUtils.isEmpty( web_site ) || StringUtils.isEmpty( workgroup_key ) || StringUtils.isEmpty( reminder_status ) || StringUtils.isEmpty( bed_capacity ) || StringUtils.isEmpty( phone_number ) || StringUtils.isEmpty( location_lat ) || StringUtils.isEmpty( location_lon ) || StringUtils.isEmpty( address ) )
        {
            _logger.error( Constants.ERROR_BAD_REQUEST_EMPTY_PARAMETER );
            return Response.status( Response.Status.BAD_REQUEST )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.BAD_REQUEST.name( ), Constants.ERROR_BAD_REQUEST_EMPTY_PARAMETER ) ) )
                    .build( );
        }
        
        Shelter shelter = ShelterHome.findByPrimaryKey( id );
        if ( shelter == null )
        {
            _logger.error( Constants.ERROR_NOT_FOUND_RESOURCE );
            return Response.status( Response.Status.NOT_FOUND )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_RESOURCE ) ) )
                    .build( );
        }
        
    	shelter.setName( name );
    	shelter.setDescription( description );
    	shelter.setEmail( email );
    	shelter.setWebSite( web_site );
    	shelter.setWorkgroupKey( workgroup_key );
	    shelter.setReminderStatus( Boolean.parseBoolean( reminder_status ) );
	    shelter.setBedCapacity( Integer.parseInt( bed_capacity ) );
    	shelter.setPhoneNumber( phone_number );
	    shelter.setLocationLat( Integer.parseInt( location_lat ) );
	    shelter.setLocationLon( Integer.parseInt( location_lon ) );
    	shelter.setAddress( address );
        ShelterHome.update( shelter );
        
        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( shelter ) ) )
                .build( );
    }
    
    /**
     * Delete Shelter
     * @param nVersion the API version
     * @param id the id
     * @return the Shelter List if deleted
     */
    @DELETE
    @Path( Constants.ID_PATH )
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteShelter(
    @PathParam( Constants.VERSION ) Integer nVersion,
    @PathParam( Constants.ID ) Integer id )
    {
        switch ( nVersion )
        {
            case VERSION_1:
                return deleteShelterV1( id );
            default:
                break;
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) )
                .build( );
    }
    
    /**
     * Delete Shelter V1
     * @param id the id
     * @return the Shelter List if deleted for the version 1
     */
    private Response deleteShelterV1( Integer id )
    {
        Shelter shelter = ShelterHome.findByPrimaryKey( id );
        if ( shelter == null )
        {
            _logger.error( Constants.ERROR_NOT_FOUND_RESOURCE );
            return Response.status( Response.Status.NOT_FOUND )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_RESOURCE ) ) )
                    .build( );
        }
        
        ShelterHome.remove( id );
        
        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( Constants.EMPTY_OBJECT ) ) )
                .build( );
    }
    
    /**
     * Get Shelter
     * @param nVersion the API version
     * @param id the id
     * @return the Shelter
     */
    @GET
    @Path( Constants.ID_PATH )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getShelter(
    @PathParam( Constants.VERSION ) Integer nVersion,
    @PathParam( Constants.ID ) Integer id )
    {
        switch ( nVersion )
        {
            case VERSION_1:
                return getShelterV1( id );
            default:
                break;
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) )
                .build( );
    }
    
    /**
     * Get Shelter V1
     * @param id the id
     * @return the Shelter for the version 1
     */
    private Response getShelterV1( Integer id )
    {
        Shelter shelter = ShelterHome.findByPrimaryKey( id );
        if ( shelter == null )
        {
            _logger.error( Constants.ERROR_NOT_FOUND_RESOURCE );
            return Response.status( Response.Status.NOT_FOUND )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_RESOURCE ) ) )
                    .build( );
        }
        
        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( shelter ) ) )
                .build( );
    }
}
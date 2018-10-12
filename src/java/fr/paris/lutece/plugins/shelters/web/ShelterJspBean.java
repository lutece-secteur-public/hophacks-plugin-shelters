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
 	
package fr.paris.lutece.plugins.shelters.web;

import fr.paris.lutece.plugins.shelters.business.Shelter;
import fr.paris.lutece.plugins.shelters.business.ShelterHome;
import fr.paris.lutece.portal.business.file.File;
import fr.paris.lutece.portal.business.file.FileHome;
import fr.paris.lutece.portal.business.physicalfile.PhysicalFile;
import fr.paris.lutece.portal.business.workgroup.AdminWorkgroup;
import fr.paris.lutece.portal.business.workgroup.AdminWorkgroupHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.web.upload.MultipartHttpServletRequest;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.url.UrlItem;
import java.util.Collection;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;

/**
 * This class provides the user interface to manage Shelter features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageShelters.jsp", controllerPath = "jsp/admin/plugins/shelters/", right = "SHELTERS_MANAGEMENT" )
public class ShelterJspBean extends MVCAdminJspBean
{
    // Templates
    private static final String TEMPLATE_MANAGE_SHELTERS = "/admin/plugins/shelters/manage_shelters.html";
    private static final String TEMPLATE_CREATE_SHELTER = "/admin/plugins/shelters/create_shelter.html";
    private static final String TEMPLATE_MODIFY_SHELTER = "/admin/plugins/shelters/modify_shelter.html";

    // Parameters
    private static final String PARAMETER_ID_SHELTER = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_SHELTERS = "shelters.manage_shelters.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_SHELTER = "shelters.modify_shelter.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_SHELTER = "shelters.create_shelter.pageTitle";

    // Markers
    private static final String MARK_SHELTER_LIST = "shelter_list";
    private static final String MARK_SHELTER = "shelter";
    private static final String MARK_WORKGROUPS_LIST = "workgroups_list";
    
    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_SHELTER = "shelters.message.confirmRemoveShelter";

    // Validations
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "shelters.model.entity.shelter.attribute.";

    // Views
    private static final String VIEW_MANAGE_SHELTERS = "manageShelters";
    private static final String VIEW_CREATE_SHELTER = "createShelter";
    private static final String VIEW_MODIFY_SHELTER = "modifyShelter";

    // Actions
    private static final String ACTION_CREATE_SHELTER = "createShelter";
    private static final String ACTION_MODIFY_SHELTER = "modifyShelter";
    private static final String ACTION_REMOVE_SHELTER = "removeShelter";
    private static final String ACTION_CONFIRM_REMOVE_SHELTER = "confirmRemoveShelter";

    // Infos
    private static final String INFO_SHELTER_CREATED = "shelters.info.shelter.created";
    private static final String INFO_SHELTER_UPDATED = "shelters.info.shelter.updated";
    private static final String INFO_SHELTER_REMOVED = "shelters.info.shelter.removed";
    
    // Session variable to store working values
    private Shelter _shelter;
    
    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_SHELTERS, defaultView = true )
    public String getManageShelters( HttpServletRequest request )
    {
        _shelter = null;
        List<Shelter> listShelters = ShelterHome.getSheltersList(  );
        Map<String, Object> model = getModel();
        model.put( MARK_SHELTER_LIST, listShelters );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_SHELTERS, TEMPLATE_MANAGE_SHELTERS, model );
    }

    /**
     * Returns the form to create a shelter
     *
     * @param request The Http request
     * @return the html code of the shelter form
     */
    @View( VIEW_CREATE_SHELTER )
    public String getCreateShelter( HttpServletRequest request )
    {
        _shelter = ( _shelter != null && _shelter.getId( ) == 0 ) ? _shelter : new Shelter(  );

        Collection<AdminWorkgroup> workgroupsList = AdminWorkgroupHome.findAll();
        ReferenceList listWorkgroups = ReferenceList.convert( workgroupsList, "key" , "description", false );

        Map<String, Object> model = getModel(  );
        model.put( MARK_SHELTER, _shelter );
        model.put( MARK_WORKGROUPS_LIST, listWorkgroups );
        

        return getPage( PROPERTY_PAGE_TITLE_CREATE_SHELTER, TEMPLATE_CREATE_SHELTER, model );
    }

    /**
     * Process the data capture form of a new shelter
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_SHELTER )
    public String doCreateShelter( HttpServletRequest request )
    {
        populate( _shelter, request, request.getLocale( ) );

        if ( _shelter.getPictureId( ) == 0 ) 
            {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            FileItem fileItem = multipartRequest.getFile( "picture" );

            if (fileItem != null && fileItem.getSize( )>0)
            {
                File file = new File( );
                file.setTitle( fileItem.getName( ) );
                file.setSize( (int) fileItem.getSize( ) );
                file.setMimeType( fileItem.getContentType( ) );

                PhysicalFile physicalFile = new PhysicalFile( );
                physicalFile.setValue( fileItem.get( ) );
                file.setPhysicalFile( physicalFile );
                int fileId = FileHome.create( file );

                _shelter.setPictureId( fileId );
                
                // reinit file content (in case of invalid bean validation)
                ShelterHome.addFileContent( _shelter );
            }
        }
        
                // Check constraints
        if ( !validateBean( _shelter, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_SHELTER );
        }
        
        ShelterHome.create( _shelter );
        addInfo( INFO_SHELTER_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_SHELTERS );
    }

    /**
     * Manages the removal form of a shelter whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_SHELTER )
    public String getConfirmRemoveShelter( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_SHELTER ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_SHELTER ) );
        url.addParameter( PARAMETER_ID_SHELTER, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_SHELTER, url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a shelter
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage shelters
     */
    @Action( ACTION_REMOVE_SHELTER )
    public String doRemoveShelter( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_SHELTER ) );
        ShelterHome.remove( nId );
        addInfo( INFO_SHELTER_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_SHELTERS );
    }

    /**
     * Returns the form to update info about a shelter
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_SHELTER )
    public String getModifyShelter( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_SHELTER ) );

        if ( _shelter == null || ( _shelter.getId(  ) != nId ) )
        {
            _shelter = ShelterHome.findByPrimaryKey( nId );
        }
        
        Collection<AdminWorkgroup> workgroupsList = AdminWorkgroupHome.findAll();
        ReferenceList listWorkgroups = ReferenceList.convert( workgroupsList, "key" , "description", false );

        Map<String, Object> model = getModel(  );
        model.put( MARK_SHELTER, _shelter );
        model.put( MARK_WORKGROUPS_LIST, listWorkgroups );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_SHELTER, TEMPLATE_MODIFY_SHELTER, model );
    }

    /**
     * Process the change form of a shelter
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_SHELTER )
    public String doModifyShelter( HttpServletRequest request )
    {
        populate( _shelter, request, request.getLocale( ) );


        // is there a new picture ?
        if (_shelter.getPictureId( ) == 0 ) 
        {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            FileItem fileItem = multipartRequest.getFile( "picture" );

            if (fileItem != null)
            {
                File file = new File( );
                file.setTitle( fileItem.getName( ) );
                file.setSize( (int) fileItem.getSize( ) );
                file.setMimeType( fileItem.getContentType( ) );

                PhysicalFile physicalFile = new PhysicalFile( );
                physicalFile.setValue( fileItem.get( ) );
                file.setPhysicalFile( physicalFile );
                int fileId = FileHome.create( file );

                _shelter.setPictureId( fileId );
                
                // reinit file content (in case of invalid bean validation)
                ShelterHome.addFileContent( _shelter );
            }
        }
        
        // Check constraints
        if ( !validateBean( _shelter, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_SHELTER, PARAMETER_ID_SHELTER, _shelter.getId( ) );
        }
        
        ShelterHome.update( _shelter );
        addInfo( INFO_SHELTER_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_SHELTERS );
    }
}
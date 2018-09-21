/*
 * Copyright (c) 2002-2017, Mairie de Paris
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

import fr.paris.lutece.plugins.shelters.business.BedAvailability;
import fr.paris.lutece.plugins.shelters.business.BedAvailabilityHome;
import fr.paris.lutece.plugins.shelters.business.Shelter;
import fr.paris.lutece.plugins.shelters.business.ShelterAvailability;
import fr.paris.lutece.plugins.shelters.business.ShelterHome;
import fr.paris.lutece.plugins.shelters.service.DateService;
import fr.paris.lutece.plugins.shelters.service.ShelterAvailabilityService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * ManageBedsJspBean
 */
@Controller( controllerJsp = "ManageBeds.jsp", controllerPath = "jsp/admin/plugins/shelters/", right = "SHELTERS_BED_MANAGEMENT" )
public class ManageBedsJspBean extends AbstractManageSheltersJspBean
{
    // Templates
    private static final String TEMPLATE_MANAGE_BEDS = "/admin/plugins/shelters/manage_beds.html";
    private static final String TEMPLATE_MODIFY_BEDAVAILABILITY = "/admin/plugins/shelters/modify_bedavailability.html";



    // Parameters
    private static final String PARAMETER_ID_SHELTER = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MODIFY_BEDAVAILABILITY = "shelters.modify_bedavailability.pageTitle";

    // Markers
    private static final String MARK_BEDAVAILABILITY = "bedavailability";
    private static final String MARK_SHELTER = "shelter";


    // Validations
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "shelters.model.entity.bedavailability.attribute.";

    // Views
    private static final String VIEW_MANAGE_BEDS = "manageBeds";    
    private static final String VIEW_MODIFY_BEDAVAILABILITY = "modifyBedAvailability";

    // Actions
    private static final String ACTION_MODIFY_BEDAVAILABILITY = "modifyBedAvailability";

    // Infos
    private static final String INFO_BEDAVAILABILITY_UPDATED = "shelters.info.bedavailability.updated";

    private static final String MARK_SHELTER_LIST = "shelter_list";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_BEDS = "shelters.manage_beds.pageTitle";
    
    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_BEDS, defaultView = true )
    public String getManageBeds( HttpServletRequest request )
    {
        List<Shelter> listShelters = ShelterHome.getSheltersByAdminUser( getUser().getUserId() );
        
        List<ShelterAvailability> listShelterAvailability = ShelterAvailabilityService.getSheltersAvailability( listShelters, DateService.getToday() , request.getLocale() );
        
        Map<String, Object> model = getModel();
        model.put( MARK_SHELTER_LIST, listShelterAvailability );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_BEDS, TEMPLATE_MANAGE_BEDS, model );
    }
    
    /**
     * Returns the form to update info about a bedavailability
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_BEDAVAILABILITY )
    public String getModifyBedAvailability( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_SHELTER ) );
        String strDateCode = DateService.getToday();

        Shelter shelter = ShelterHome.findByPrimaryKey(nId);
        
        BedAvailability bedavailability = BedAvailabilityHome.findByPrimaryKey( nId , strDateCode );
        if( bedavailability == null )
        {
            bedavailability = new BedAvailability();
            bedavailability.setShelterId(nId);
            bedavailability.setDateCode(strDateCode);
            bedavailability.setTotalBedCapacity( shelter.getBedCapacity());
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_BEDAVAILABILITY, bedavailability );
        model.put( MARK_SHELTER, shelter );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_BEDAVAILABILITY, TEMPLATE_MODIFY_BEDAVAILABILITY, model );
    }

    /**
     * Process the change form of a bedavailability
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_BEDAVAILABILITY )
    public String doModifyBedAvailability( HttpServletRequest request )
    {
        BedAvailability bedavailability = new BedAvailability();
        populate( bedavailability, request, request.getLocale( ) );

        // Check constraints
        if ( !validateBean( bedavailability, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_BEDAVAILABILITY, PARAMETER_ID_SHELTER, bedavailability.getShelterId( ) );
        }

        BedAvailability existing = BedAvailabilityHome.findByPrimaryKey( bedavailability.getShelterId(), bedavailability.getDateCode());
        if( existing == null )
        {
            BedAvailabilityHome.create( bedavailability );
        }
        else
        {
            BedAvailabilityHome.update( bedavailability );
        }
        addInfo( INFO_BEDAVAILABILITY_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_BEDS );
    }
    
}

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
import fr.paris.lutece.plugins.shelters.business.ShelterAvailability;
import fr.paris.lutece.plugins.shelters.business.ShelterHome;
import fr.paris.lutece.plugins.shelters.service.DateService;
import fr.paris.lutece.plugins.shelters.service.ShelterAvailabilityService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides a simple implementation of an XPage
 */
@Controller( xpageName = "shelters" , pageTitleI18nKey = "shelters.xpage.shelters.pageTitle" , pagePathI18nKey = "shelters.xpage.shelters.pagePathLabel" )
public class SheltersApp extends MVCApplication
{
    // Templates
    private static final String TEMPLATE_XPAGE = "/skin/plugins/shelters/shelters.html";

    // Markers
    private static final String MARK_SHELTER_LIST = "shelter_list";
    private static final String VIEW_HOME = "home";
    
    /**
     * Returns the content of the page shelters. 
     * @param request The HTTP request
     * @return The view
     */
    @View( value = VIEW_HOME , defaultView = true )
    public XPage viewHome( HttpServletRequest request )
    {
        List<Shelter> listShelters = ShelterHome.getSheltersList(  );
        List<ShelterAvailability> listShelterAvailability = ShelterAvailabilityService.getSheltersAvailability( listShelters, DateService.getToday() , request.getLocale() );

        Map<String, Object> model = getModel();
        model.put( MARK_SHELTER_LIST, listShelterAvailability );
        return getXPage( TEMPLATE_XPAGE, request.getLocale(  ) , model );
    }
}

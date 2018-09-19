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

package fr.paris.lutece.plugins.shelters.service;

import fr.paris.lutece.plugins.shelters.business.BedAvailability;
import fr.paris.lutece.plugins.shelters.business.BedAvailabilityHome;
import fr.paris.lutece.plugins.shelters.business.Shelter;
import fr.paris.lutece.plugins.shelters.business.ShelterAvailability;
import fr.paris.lutece.portal.service.i18n.I18nService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * ShelterAvailabilityService
 */
public class ShelterAvailabilityService 
{
    private static final String KEY_UNKNOWN = "shelters.message.availability.unknow";
    private static final String KEY_NO_MORE_BED = "shelters.message.availability.no_more_bed";
    private static final String KEY_ONE_BED = "shelters.message.availability.one_bed";
    private static final String KEY_SEVERAL_BEDS = "shelters.message.availability.several_beds";
    
    /**
     * Returns the list of shelters with bed availabilty for a given date 
     * @param listShelters The list of shelter
     * @param strCodeDate The date
     * @param locale The locale for messages
     * @return The list
     */
    
    public static List<ShelterAvailability> getSheltersAvailability( List<Shelter> listShelters , String strCodeDate , Locale locale )
    {
        List<ShelterAvailability> list = new ArrayList<>();
        for( Shelter shelter : listShelters )
        {
            ShelterAvailability sa = new ShelterAvailability( shelter );
            BedAvailability ba = BedAvailabilityHome.findByPrimaryKey( shelter.getId(), strCodeDate );
            if( ba != null )
            {
                sa.setBedAvailable( ba.getBedAvailableCount() );
                
                switch ( sa.getBedAvailable() )
                {
                    case 0:
                        sa.setAvailability( I18nService.getLocalizedString( KEY_NO_MORE_BED , locale ));
                        break;
                    case 1:
                        sa.setAvailability( I18nService.getLocalizedString( KEY_ONE_BED , locale ));
                        break;
                    default:
                        Object[] args = { sa.getBedAvailable() };
                        sa.setAvailability( I18nService.getLocalizedString( KEY_SEVERAL_BEDS , args, locale ));
                        break;
                        
                        
                }
            }
            else
            {
                sa.setAvailability( I18nService.getLocalizedString( KEY_UNKNOWN , locale ));
            }
            list.add( sa );
        }
        
        Collections.sort(list);
        
        return list;
    }

}

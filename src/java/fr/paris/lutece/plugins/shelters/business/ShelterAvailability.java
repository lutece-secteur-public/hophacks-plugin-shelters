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

package fr.paris.lutece.plugins.shelters.business;

/**
 * ShelterAvailability
 */
public class ShelterAvailability extends Shelter implements Comparable
{
    private int _nBedAvailable;
    private String _strAvailability;
    
    public ShelterAvailability( Shelter shelter )
    {
        setAddress( shelter.getAddress() );
        setBedCapacity( shelter.getBedCapacity() );
        setDescription( shelter.getDescription() );
        setEmail( shelter.getEmail() );
        setName( shelter.getName() );
        setId( shelter.getId() );
        setLocationLat( shelter.getLocationLat() );
        setLocationLon( shelter.getLocationLon() );
        setPhoneNumber( shelter.getPhoneNumber() );
        setWebSite( shelter.getWebSite() );
        
        _nBedAvailable = -1;
        _strAvailability = "Unknown";
        
    }

    /**
     * @return the _nBedAvailable
     */
    public int getBedAvailable()
    {
        return _nBedAvailable;
    }

    /**
     * @param _nBedAvailable the _nBedAvailable to set
     */
    public void setBedAvailable(int _nBedAvailable)
    {
        this._nBedAvailable = _nBedAvailable;
    }

    /**
     * @return the _strAvailability
     */
    public String getAvailability()
    {
        return _strAvailability;
    }

    /**
     * @param _strAvailability the _strAvailability to set
     */
    public void setAvailability(String _strAvailability)
    {
        this._strAvailability = _strAvailability;
    }

    @Override
    public int compareTo(Object o)
    {
        return (( ShelterAvailability) o ).getBedAvailable() - _nBedAvailable;
    }
    

}

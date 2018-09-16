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

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;

/**
 * This is the business class for the object BedAvailability
 */ 
public class BedAvailability implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Variables declarations 
    private int _nShelterId;
    
    @NotEmpty( message = "#i18n{shelters.validation.bedavailability.DateCode.notEmpty}" )
    @Size( max = 50 , message = "#i18n{shelters.validation.bedavailability.DateCode.size}" ) 
    private String _strDateCode;
    
    private int _nBedAvailableCount;
    
    private int _nTotalBedCapacity;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getShelterId( )
    {
        return _nShelterId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */ 
    public void setShelterId( int nId )
    {
        _nShelterId = nId;
    }
    
    /**
     * Returns the DateCode
     * @return The DateCode
     */
    public String getDateCode( )
    {
        return _strDateCode;
    }

    /**
     * Sets the DateCode
     * @param strDateCode The DateCode
     */ 
    public void setDateCode( String strDateCode )
    {
        _strDateCode = strDateCode;
    }
    
    /**
     * Returns the BedAvailableCount
     * @return The BedAvailableCount
     */
    public int getBedAvailableCount( )
    {
        return _nBedAvailableCount;
    }

    /**
     * Sets the BedAvailableCount
     * @param nBedAvailableCount The BedAvailableCount
     */ 
    public void setBedAvailableCount( int nBedAvailableCount )
    {
        _nBedAvailableCount = nBedAvailableCount;
    }
    
    /**
     * Returns the TotalBedCapacity
     * @return The TotalBedCapacity
     */
    public int getTotalBedCapacity( )
    {
        return _nTotalBedCapacity;
    }

    /**
     * Sets the TotalBedCapacity
     * @param nTotalBedCapacity The TotalBedCapacity
     */ 
    public void setTotalBedCapacity( int nTotalBedCapacity )
    {
        _nTotalBedCapacity = nTotalBedCapacity;
    }
}

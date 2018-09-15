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
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.Email;
import java.io.Serializable;

/**
 * This is the business class for the object Shelter
 */ 
public class Shelter implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Variables declarations 
    private int _nId;
    
    @NotEmpty( message = "#i18n{shelters.validation.shelter.Name.notEmpty}" )
    @Size( max = 50 , message = "#i18n{shelters.validation.shelter.Name.size}" ) 
    private String _strName;
    
    @Size( max = 255 , message = "#i18n{shelters.validation.shelter.Description.size}" ) 
    private String _strDescription;
    @Email(message = "#i18n{portal.validation.message.email}")
    @NotEmpty( message = "#i18n{shelters.validation.shelter.Email.notEmpty}" )
    @Size( max = 255 , message = "#i18n{shelters.validation.shelter.Email.size}" ) 
    private String _strEmail;
    @URL(message = "#i18n{portal.validation.message.url}")
    @Size( max = 255 , message = "#i18n{shelters.validation.shelter.WebSite.size}" ) 
    private String _strWebSite;
    
    @NotEmpty( message = "#i18n{shelters.validation.shelter.WorkgroupKey.notEmpty}" )
    @Size( max = 50 , message = "#i18n{shelters.validation.shelter.WorkgroupKey.size}" ) 
    private String _strWorkgroupKey;
    
    private boolean _bReminderStatus;
    
    private int _nBedCapacity;
    
    @Size( max = 50 , message = "#i18n{shelters.validation.shelter.PhoneNumber.size}" ) 
    private String _strPhoneNumber;
    
    private int _nLocationLat;
    
    private int _nLocationLon;
    
    private String _strAddress;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getId( )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */ 
    public void setId( int nId )
    {
        _nId = nId;
    }
    
    /**
     * Returns the Name
     * @return The Name
     */
    public String getName( )
    {
        return _strName;
    }

    /**
     * Sets the Name
     * @param strName The Name
     */ 
    public void setName( String strName )
    {
        _strName = strName;
    }
    
    /**
     * Returns the Description
     * @return The Description
     */
    public String getDescription( )
    {
        return _strDescription;
    }

    /**
     * Sets the Description
     * @param strDescription The Description
     */ 
    public void setDescription( String strDescription )
    {
        _strDescription = strDescription;
    }
    
    /**
     * Returns the Email
     * @return The Email
     */
    public String getEmail( )
    {
        return _strEmail;
    }

    /**
     * Sets the Email
     * @param strEmail The Email
     */ 
    public void setEmail( String strEmail )
    {
        _strEmail = strEmail;
    }
    
    /**
     * Returns the WebSite
     * @return The WebSite
     */
    public String getWebSite( )
    {
        return _strWebSite;
    }

    /**
     * Sets the WebSite
     * @param strWebSite The WebSite
     */ 
    public void setWebSite( String strWebSite )
    {
        _strWebSite = strWebSite;
    }
    
    /**
     * Returns the WorkgroupKey
     * @return The WorkgroupKey
     */
    public String getWorkgroupKey( )
    {
        return _strWorkgroupKey;
    }

    /**
     * Sets the WorkgroupKey
     * @param strWorkgroupKey The WorkgroupKey
     */ 
    public void setWorkgroupKey( String strWorkgroupKey )
    {
        _strWorkgroupKey = strWorkgroupKey;
    }
    
    /**
     * Returns the ReminderStatus
     * @return The ReminderStatus
     */
    public boolean getReminderStatus( )
    {
        return _bReminderStatus;
    }

    /**
     * Sets the ReminderStatus
     * @param bReminderStatus The ReminderStatus
     */ 
    public void setReminderStatus( boolean bReminderStatus )
    {
        _bReminderStatus = bReminderStatus;
    }
    
    /**
     * Returns the BedCapacity
     * @return The BedCapacity
     */
    public int getBedCapacity( )
    {
        return _nBedCapacity;
    }

    /**
     * Sets the BedCapacity
     * @param nBedCapacity The BedCapacity
     */ 
    public void setBedCapacity( int nBedCapacity )
    {
        _nBedCapacity = nBedCapacity;
    }
    
    /**
     * Returns the PhoneNumber
     * @return The PhoneNumber
     */
    public String getPhoneNumber( )
    {
        return _strPhoneNumber;
    }

    /**
     * Sets the PhoneNumber
     * @param strPhoneNumber The PhoneNumber
     */ 
    public void setPhoneNumber( String strPhoneNumber )
    {
        _strPhoneNumber = strPhoneNumber;
    }
    
    /**
     * Returns the LocationLat
     * @return The LocationLat
     */
    public int getLocationLat( )
    {
        return _nLocationLat;
    }

    /**
     * Sets the LocationLat
     * @param nLocationLat The LocationLat
     */ 
    public void setLocationLat( int nLocationLat )
    {
        _nLocationLat = nLocationLat;
    }
    
    /**
     * Returns the LocationLon
     * @return The LocationLon
     */
    public int getLocationLon( )
    {
        return _nLocationLon;
    }

    /**
     * Sets the LocationLon
     * @param nLocationLon The LocationLon
     */ 
    public void setLocationLon( int nLocationLon )
    {
        _nLocationLon = nLocationLon;
    }
    
    /**
     * Returns the Address
     * @return The Address
     */
    public String getAddress( )
    {
        return _strAddress;
    }

    /**
     * Sets the Address
     * @param strAddress The Address
     */ 
    public void setAddress( String strAddress )
    {
        _strAddress = strAddress;
    }
}

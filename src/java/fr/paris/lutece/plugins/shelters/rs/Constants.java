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
 * THIS SOFTWARE IS PROVREST_IDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCREST_IDENTAL, SPECIAL, EXEMPLARY, OR
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

/**
 * Rest Constants
 */
public final class Constants 
{
    public static final String API_PATH = "shelters/api";
    public static final String VERSION_PATH = "/v{" + Constants.VERSION + "}";
    public static final String ID_PATH = "/{" + Constants.ID + "}";
    public static final String VERSION = "version";
    public static final String ID = "id";
    
    public static final String SWAGGER_DIRECTORY_PATH = "/plugins/";
    public static final String SWAGGER_PATH = "/swagger";
    public static final String SWAGGER_VERSION_PATH = "/v";
    public static final String SWAGGER_REST_PATH = "rest/";
    public static final String SWAGGER_JSON = "/swagger.json";
    
    public static final String EMPTY_OBJECT = "{}";
    public static final String ERROR_NOT_FOUND_VERSION = "Version not found";
    public static final String ERROR_NOT_FOUND_RESOURCE = "Resource not found";
    public static final String ERROR_BAD_REQUEST_EMPTY_PARAMETER = "Empty parameter";
    
    public static final String SHELTER_PATH = "/shelters";
    public static final String SHELTER_ATTRIBUTE_NAME = "name";
    public static final String SHELTER_ATTRIBUTE_DESCRIPTION = "description";
    public static final String SHELTER_ATTRIBUTE_EMAIL = "email";
    public static final String SHELTER_ATTRIBUTE_WEB_SITE = "web_site";
    public static final String SHELTER_ATTRIBUTE_WORKGROUP_KEY = "workgroup_key";
    public static final String SHELTER_ATTRIBUTE_REMINDER_STATUS = "reminder_status";
    public static final String SHELTER_ATTRIBUTE_BED_CAPACITY = "bed_capacity";
    public static final String SHELTER_ATTRIBUTE_PHONE_NUMBER = "phone_number";
    public static final String SHELTER_ATTRIBUTE_LOCATION_LAT = "location_lat";
    public static final String SHELTER_ATTRIBUTE_LOCATION_LON = "location_lon";
    public static final String SHELTER_ATTRIBUTE_ADDRESS = "address";
    
    /**
     * Private constructor
     */
    private Constants(  )
    {
    }
}

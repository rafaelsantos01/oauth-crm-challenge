package com.meetime.hubspot_integration.service;

import java.net.MalformedURLException;

public interface IAuthorizationService {

    String getAuthorizationUrl() throws MalformedURLException;

    void getAccessToken(String code) ;
}

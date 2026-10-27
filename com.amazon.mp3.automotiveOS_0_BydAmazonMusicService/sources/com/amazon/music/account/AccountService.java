package com.amazon.music.account;

import com.amazon.stratus.AttemptAccountCreationResponse;
import com.amazon.stratus.IsAccountValidResponse;
import com.amazon.stratus.ListDevicesByCustomerIdResponse;
import com.amazon.stratus.RetrieveCapabilityResponse;
import com.amazon.stratus.RetrieveCustomerHomeResponse;
import com.amazon.stratus.RetrieveDeviceResponse;
import com.amazon.stratus.RetrievePreferencesResponse;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesResponse;
import com.amazon.stratus.UpdateDeviceRequest;
import com.amazon.stratus.UpdateDeviceResponse;
import com.android.volley.VolleyError;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class AccountService {
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class.getSimpleName());
    private final MusicIdentityService musicIdentityService;
    private final AccountServiceRequestBuilder requestBuilder;
    private final AccountServiceResponseParser responseParser;
    private final StratusService stratusService;

    public AccountService(StratusService stratusService, AccountServiceResponseParser accountServiceResponseParser, AccountServiceRequestBuilder accountServiceRequestBuilder) {
        this.stratusService = (StratusService) Validate.notNull(stratusService);
        this.responseParser = (AccountServiceResponseParser) Validate.notNull(accountServiceResponseParser);
        this.requestBuilder = (AccountServiceRequestBuilder) Validate.notNull(accountServiceRequestBuilder);
        this.musicIdentityService = null;
    }

    public AccountService(StratusService stratusService, MusicIdentityService musicIdentityService, AccountServiceResponseParser accountServiceResponseParser, AccountServiceRequestBuilder accountServiceRequestBuilder) {
        this.stratusService = (StratusService) Validate.notNull(stratusService);
        this.musicIdentityService = (MusicIdentityService) Validate.notNull(musicIdentityService);
        this.responseParser = (AccountServiceResponseParser) Validate.notNull(accountServiceResponseParser);
        this.requestBuilder = (AccountServiceRequestBuilder) Validate.notNull(accountServiceRequestBuilder);
    }

    public Device getDeviceFromService() {
        RetrieveDeviceResponse retrieveDeviceResponseRetrieveDevice = retrieveDevice();
        if (retrieveDeviceResponseRetrieveDevice == null) {
            return null;
        }
        return this.responseParser.parseDevice(retrieveDeviceResponseRetrieveDevice);
    }

    public DeviceAuthorizations getDeviceAuthorizationsFromService() {
        ListDevicesByCustomerIdResponse listDevicesByCustomerIdResponseListDevicesByCustomerId = listDevicesByCustomerId();
        if (listDevicesByCustomerIdResponseListDevicesByCustomerId == null) {
            return null;
        }
        return this.responseParser.parseDeviceAuthorizations(listDevicesByCustomerIdResponseListDevicesByCustomerId);
    }

    public DeviceCapabilities getDeviceCapabilitiesFromService() {
        RetrieveCapabilityResponse retrieveCapabilityResponseRetrieveCapability = retrieveCapability();
        if (retrieveCapabilityResponseRetrieveCapability == null) {
            return null;
        }
        return this.responseParser.parseDeviceCapabilities(retrieveCapabilityResponseRetrieveCapability);
    }

    private RetrievePreferencesResponse retrievePreferences() {
        try {
            return this.stratusService.retrievePreferences(this.requestBuilder.buildPreferencesRequest());
        } catch (VolleyError e) {
            LOG.error("Error retrieving account ", (Throwable) e);
            return null;
        }
    }

    private RetrievePrivacyCompliancePreferencesResponse retrievePrivacyCompliancePreferences(String str, String str2) {
        try {
            return this.stratusService.retrievePrivacyCompliancePreferences(this.requestBuilder.buildPrivacyCompliancePreferencesRequest(str, str2));
        } catch (VolleyError e) {
            LOG.error("Error retrieving privacy compliance preferences ", (Throwable) e);
            return null;
        }
    }

    public String getMusicRequestIdentityContextToken() {
        try {
            if (this.musicIdentityService == null) {
                LOG.error("Error retrieving musicRequestIdentityContextToken, musicIdentityService is not initialized");
                return null;
            }
            String musicRequestIdentityContextToken = this.musicIdentityService.retrieveMusicRequestIdentityContextToken(this.requestBuilder.buildMRICTokenRequest()).getMusicRequestIdentityContextToken();
            LOG.info("Successfully fetched musicRequestIdentityContextToken for user");
            return musicRequestIdentityContextToken;
        } catch (VolleyError e) {
            LOG.error("Error retrieving musicRequestIdentityContextToken", (Throwable) e);
            return null;
        }
    }

    private RetrieveDeviceResponse retrieveDevice() {
        try {
            return this.stratusService.retrieveDevice(this.requestBuilder.buildDeviceRequest());
        } catch (VolleyError e) {
            LOG.error("Error retrieving device", (Throwable) e);
            return null;
        }
    }

    private ListDevicesByCustomerIdResponse listDevicesByCustomerId() {
        try {
            return this.stratusService.listDevicesByCustomerId(this.requestBuilder.buildListDevicesByCustomerIdRequest());
        } catch (VolleyError e) {
            LOG.error("Error listing devices by customer id", (Throwable) e);
            return null;
        }
    }

    private RetrieveCapabilityResponse retrieveCapability() {
        try {
            return this.stratusService.retrieveCapability(this.requestBuilder.buildRetrieveCapabilityRequest());
        } catch (VolleyError e) {
            LOG.error("Error retrieving capability", (Throwable) e);
            return null;
        }
    }

    public boolean acceptTermsOfUse(String str) {
        try {
            this.stratusService.acceptTermsOfUse(this.requestBuilder.buildAcceptTermsOfUseRequest(str));
            return true;
        } catch (MarketplaceProvisionFailedException | VolleyError e) {
            LOG.error("Error accepting terms of use", e);
            return false;
        }
    }

    public boolean catalogAuthorizeDevice() {
        return this.responseParser.parseCatalogAuthorizeSuccess(updateDevice(this.requestBuilder.buildCatalogAuthorizeDeviceRequest(), "CatalogAuthorizeDeviceRequest", "CatalogAuthorizeDeviceError"));
    }

    public boolean updateExplicitLanguagePreference(boolean z) {
        try {
            return this.responseParser.parseUpdateExplicitLanguagePreferenceSuccess(this.stratusService.updatePreferences(this.requestBuilder.buildUpdateExplicitLanguagePreferenceRequest(z)));
        } catch (VolleyError e) {
            LOG.error("Error updating preference ", (Throwable) e);
            return false;
        }
    }

    private UpdateDeviceResponse updateDevice(UpdateDeviceRequest updateDeviceRequest, String str, String str2) {
        try {
            return this.stratusService.updateDevice(updateDeviceRequest);
        } catch (VolleyError e) {
            LOG.error("Error updating device", (Throwable) e);
            return null;
        }
    }

    public User getUserWithMarketplaceInfo() throws MarketplaceProvisionFailedException {
        RetrieveCustomerHomeResponse retrieveCustomerHomeResponseRetrieveCustomerHome = retrieveCustomerHome();
        if (retrieveCustomerHomeResponseRetrieveCustomerHome == null) {
            return null;
        }
        return this.responseParser.parseUser(retrieveCustomerHomeResponseRetrieveCustomerHome);
    }

    private RetrieveCustomerHomeResponse retrieveCustomerHome() throws MarketplaceProvisionFailedException {
        try {
            return this.stratusService.retrieveCustomerHome(this.requestBuilder.buildRetrieveCustomerHomeRequest());
        } catch (VolleyError e) {
            LOG.error("Error getting customer home territory", (Throwable) e);
            if (isErrorDueToCloudUnsupportedMarketplace(e)) {
                throw new MarketplaceProvisionFailedException();
            }
            return null;
        }
    }

    private boolean isErrorDueToCloudUnsupportedMarketplace(VolleyError volleyError) {
        String message = volleyError.getMessage();
        if (message != null) {
            return message.contains("com.amazon.identity.authentication.id.InvalidCustomerIdException");
        }
        return false;
    }

    public UserStateWrapper getUserStateFromService() {
        IsAccountValidResponse isAccountValidResponseIsAccountValid = isAccountValid();
        if (isAccountValidResponseIsAccountValid == null) {
            return null;
        }
        return new UserStateWrapper(this.responseParser, isAccountValidResponseIsAccountValid, retrievePreferences(), retrievePrivacyCompliancePreferences("ccba-opt-out-preference", "CCBAOptOut"), this.requestBuilder.getConfiguration().getDeviceId());
    }

    private IsAccountValidResponse isAccountValid() {
        try {
            return this.stratusService.isAccountValid(this.requestBuilder.buildIsAccountValidRequest());
        } catch (Exception e) {
            LOG.error("Error getting account state information", (Throwable) e);
            return null;
        }
    }

    public boolean updateTargetedAdsPreference(boolean z) {
        try {
            return this.responseParser.parseUpdateTargetedAdsPreferenceSuccess(this.stratusService.updatePreferences(this.requestBuilder.buildUpdateTargetedAdsPreferenceRequest(z)));
        } catch (VolleyError e) {
            LOG.error("Error updating ads preference ", (Throwable) e);
            return false;
        }
    }

    public AttemptAccountCreationResponse attemptAccountCreation(String str) {
        try {
            return this.stratusService.attemptAccountCreation(this.requestBuilder.buildAttemptAccountCreationRequest(str));
        } catch (VolleyError e) {
            LOG.error("Error attempting account creation ", (Throwable) e);
            return null;
        }
    }
}

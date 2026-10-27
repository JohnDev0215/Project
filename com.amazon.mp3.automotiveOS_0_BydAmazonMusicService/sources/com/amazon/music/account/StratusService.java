package com.amazon.music.account;

import com.amazon.hermes.CoralCall;
import com.amazon.stratus.AcceptTermsOfUseRequest;
import com.amazon.stratus.AttemptAccountCreationRequest;
import com.amazon.stratus.AttemptAccountCreationResponse;
import com.amazon.stratus.IsAccountValidRequest;
import com.amazon.stratus.IsAccountValidResponse;
import com.amazon.stratus.ListDevicesByCustomerIdRequest;
import com.amazon.stratus.ListDevicesByCustomerIdResponse;
import com.amazon.stratus.RetrieveCapabilityRequest;
import com.amazon.stratus.RetrieveCapabilityResponse;
import com.amazon.stratus.RetrieveCustomerHomeRequest;
import com.amazon.stratus.RetrieveCustomerHomeResponse;
import com.amazon.stratus.RetrieveDeviceRequest;
import com.amazon.stratus.RetrieveDeviceResponse;
import com.amazon.stratus.RetrievePreferencesRequest;
import com.amazon.stratus.RetrievePreferencesResponse;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesRequest;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesResponse;
import com.amazon.stratus.UpdateDeviceRequest;
import com.amazon.stratus.UpdateDeviceResponse;
import com.amazon.stratus.UpdatePreferencesRequest;
import com.amazon.stratus.UpdatePreferencesResponse;
import com.amazon.stratus.external.AcceptTermsOfUseCall;
import com.amazon.stratus.external.AttemptAccountCreationCall;
import com.amazon.stratus.external.IsAccountValidCall;
import com.amazon.stratus.external.ListDevicesByCustomerIdCall;
import com.amazon.stratus.external.RetrieveCapabilityCall;
import com.amazon.stratus.external.RetrieveCustomerHomeCall;
import com.amazon.stratus.external.RetrieveDeviceCall;
import com.amazon.stratus.external.RetrievePreferencesCall;
import com.amazon.stratus.external.RetrievePrivacyCompliancePreferencesCall;
import com.amazon.stratus.external.UpdateDeviceCall;
import com.amazon.stratus.external.UpdatePreferencesCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class StratusService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final StratusServiceConfiguration configuration;
    private final StratusMarketplaceProvider marketplaceProvider;
    private RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, 3, 1.0f);

    public StratusService(StratusServiceConfiguration stratusServiceConfiguration, StratusMarketplaceProvider stratusMarketplaceProvider) {
        this.configuration = (StratusServiceConfiguration) Validate.notNull(stratusServiceConfiguration);
        this.marketplaceProvider = (StratusMarketplaceProvider) Validate.notNull(stratusMarketplaceProvider);
    }

    public RetrieveCustomerHomeResponse retrieveCustomerHome(RetrieveCustomerHomeRequest retrieveCustomerHomeRequest) throws VolleyError {
        RetrieveCustomerHomeCall retrieveCustomerHomeCallBuildRetrieveCustomerHomeCall = buildRetrieveCustomerHomeCall(retrieveCustomerHomeRequest);
        setRetryPolicyForCall(retrieveCustomerHomeCallBuildRetrieveCustomerHomeCall);
        return retrieveCustomerHomeCallBuildRetrieveCustomerHomeCall.execute(MAPPER);
    }

    public RetrieveDeviceResponse retrieveDevice(RetrieveDeviceRequest retrieveDeviceRequest) throws VolleyError {
        RetrieveDeviceCall retrieveDeviceCallBuildRetrieveDeviceCall = buildRetrieveDeviceCall(retrieveDeviceRequest);
        setRetryPolicyForCall(retrieveDeviceCallBuildRetrieveDeviceCall);
        return retrieveDeviceCallBuildRetrieveDeviceCall.execute(MAPPER);
    }

    public ListDevicesByCustomerIdResponse listDevicesByCustomerId(ListDevicesByCustomerIdRequest listDevicesByCustomerIdRequest) throws VolleyError {
        ListDevicesByCustomerIdCall listDevicesByCustomerIdCallBuildListDevicesByCustomerIdCall = buildListDevicesByCustomerIdCall(listDevicesByCustomerIdRequest);
        setRetryPolicyForCall(listDevicesByCustomerIdCallBuildListDevicesByCustomerIdCall);
        return listDevicesByCustomerIdCallBuildListDevicesByCustomerIdCall.execute(MAPPER);
    }

    public RetrieveCapabilityResponse retrieveCapability(RetrieveCapabilityRequest retrieveCapabilityRequest) throws VolleyError {
        RetrieveCapabilityCall retrieveCapabilityCallBuildRetrieveCapabilityCall = buildRetrieveCapabilityCall(retrieveCapabilityRequest);
        setRetryPolicyForCall(retrieveCapabilityCallBuildRetrieveCapabilityCall);
        return retrieveCapabilityCallBuildRetrieveCapabilityCall.execute(MAPPER);
    }

    public void acceptTermsOfUse(AcceptTermsOfUseRequest acceptTermsOfUseRequest) throws VolleyError {
        AcceptTermsOfUseCall acceptTermsOfUseCallBuildAcceptTermsOfUseCall = buildAcceptTermsOfUseCall(acceptTermsOfUseRequest);
        setRetryPolicyForCall(acceptTermsOfUseCallBuildAcceptTermsOfUseCall);
        acceptTermsOfUseCallBuildAcceptTermsOfUseCall.execute(MAPPER);
    }

    public AttemptAccountCreationResponse attemptAccountCreation(AttemptAccountCreationRequest attemptAccountCreationRequest) throws VolleyError {
        AttemptAccountCreationCall attemptAccountCreationCallBuildAttemptAccountCreationCall = buildAttemptAccountCreationCall(attemptAccountCreationRequest);
        setRetryPolicyForCall(attemptAccountCreationCallBuildAttemptAccountCreationCall);
        return attemptAccountCreationCallBuildAttemptAccountCreationCall.execute(MAPPER);
    }

    public UpdateDeviceResponse updateDevice(UpdateDeviceRequest updateDeviceRequest) throws VolleyError {
        UpdateDeviceCall updateDeviceCallBuildUpdateDeviceCall = buildUpdateDeviceCall(updateDeviceRequest);
        setRetryPolicyForCall(updateDeviceCallBuildUpdateDeviceCall);
        return updateDeviceCallBuildUpdateDeviceCall.execute(MAPPER);
    }

    public UpdatePreferencesResponse updatePreferences(UpdatePreferencesRequest updatePreferencesRequest) throws VolleyError {
        UpdatePreferencesCall updatePreferencesCallBuildUpdatePreferencesCall = buildUpdatePreferencesCall(updatePreferencesRequest);
        setRetryPolicyForCall(updatePreferencesCallBuildUpdatePreferencesCall);
        return updatePreferencesCallBuildUpdatePreferencesCall.execute(MAPPER);
    }

    public IsAccountValidResponse isAccountValid(IsAccountValidRequest isAccountValidRequest) throws VolleyError {
        IsAccountValidCall isAccountValidCallBuildIsAccountValidCall = buildIsAccountValidCall(isAccountValidRequest);
        setRetryPolicyForCall(isAccountValidCallBuildIsAccountValidCall);
        return isAccountValidCallBuildIsAccountValidCall.execute(MAPPER);
    }

    public RetrievePreferencesResponse retrievePreferences(RetrievePreferencesRequest retrievePreferencesRequest) throws VolleyError {
        RetrievePreferencesCall retrievePreferencesCallBuildRetrievePreferencesCall = buildRetrievePreferencesCall(retrievePreferencesRequest);
        setRetryPolicyForCall(retrievePreferencesCallBuildRetrievePreferencesCall);
        return retrievePreferencesCallBuildRetrievePreferencesCall.execute(MAPPER);
    }

    public RetrievePrivacyCompliancePreferencesResponse retrievePrivacyCompliancePreferences(RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest) throws VolleyError {
        RetrievePrivacyCompliancePreferencesCall retrievePrivacyCompliancePreferencesCallBuildRetrievePrivacyCompliancePreferencesCall = buildRetrievePrivacyCompliancePreferencesCall(retrievePrivacyCompliancePreferencesRequest);
        setRetryPolicyForCall(retrievePrivacyCompliancePreferencesCallBuildRetrievePrivacyCompliancePreferencesCall);
        return retrievePrivacyCompliancePreferencesCallBuildRetrievePrivacyCompliancePreferencesCall.execute(MAPPER);
    }

    RetrieveCustomerHomeCall buildRetrieveCustomerHomeCall(RetrieveCustomerHomeRequest retrieveCustomerHomeRequest) {
        return new RetrieveCustomerHomeCall(getStratusUrl(), retrieveCustomerHomeRequest, this.configuration.getRequestInterceptors());
    }

    RetrieveDeviceCall buildRetrieveDeviceCall(RetrieveDeviceRequest retrieveDeviceRequest) {
        return new RetrieveDeviceCall(getStratusUrl(), retrieveDeviceRequest, this.configuration.getRequestInterceptors());
    }

    ListDevicesByCustomerIdCall buildListDevicesByCustomerIdCall(ListDevicesByCustomerIdRequest listDevicesByCustomerIdRequest) {
        return new ListDevicesByCustomerIdCall(getStratusUrl(), listDevicesByCustomerIdRequest, this.configuration.getRequestInterceptors());
    }

    RetrieveCapabilityCall buildRetrieveCapabilityCall(RetrieveCapabilityRequest retrieveCapabilityRequest) {
        return new RetrieveCapabilityCall(getStratusUrl(), retrieveCapabilityRequest, this.configuration.getRequestInterceptors());
    }

    AcceptTermsOfUseCall buildAcceptTermsOfUseCall(AcceptTermsOfUseRequest acceptTermsOfUseRequest) {
        return new AcceptTermsOfUseCall(getStratusUrl(), acceptTermsOfUseRequest, this.configuration.getRequestInterceptors());
    }

    AttemptAccountCreationCall buildAttemptAccountCreationCall(AttemptAccountCreationRequest attemptAccountCreationRequest) {
        return new AttemptAccountCreationCall(getStratusUrl(), attemptAccountCreationRequest, this.configuration.getRequestInterceptors());
    }

    UpdateDeviceCall buildUpdateDeviceCall(UpdateDeviceRequest updateDeviceRequest) {
        return new UpdateDeviceCall(getStratusUrl(), updateDeviceRequest, this.configuration.getRequestInterceptors());
    }

    UpdatePreferencesCall buildUpdatePreferencesCall(UpdatePreferencesRequest updatePreferencesRequest) {
        return new UpdatePreferencesCall(getStratusUrl(), updatePreferencesRequest, this.configuration.getRequestInterceptors());
    }

    IsAccountValidCall buildIsAccountValidCall(IsAccountValidRequest isAccountValidRequest) {
        return new IsAccountValidCall(getStratusUrl(), isAccountValidRequest, this.configuration.getRequestInterceptors());
    }

    RetrievePreferencesCall buildRetrievePreferencesCall(RetrievePreferencesRequest retrievePreferencesRequest) {
        return new RetrievePreferencesCall(getStratusUrl(), retrievePreferencesRequest, this.configuration.getRequestInterceptors());
    }

    RetrievePrivacyCompliancePreferencesCall buildRetrievePrivacyCompliancePreferencesCall(RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest) {
        return new RetrievePrivacyCompliancePreferencesCall(getStratusUrl(), retrievePrivacyCompliancePreferencesRequest, this.configuration.getRequestInterceptors());
    }

    URL getStratusUrl() {
        try {
            return this.configuration.getStratusUrl(this.marketplaceProvider.getMarketplace());
        } catch (MarketplaceProvisionFailedException unused) {
            return this.configuration.getDefaultStratusUrl();
        }
    }

    private void setRetryPolicyForCall(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }
}

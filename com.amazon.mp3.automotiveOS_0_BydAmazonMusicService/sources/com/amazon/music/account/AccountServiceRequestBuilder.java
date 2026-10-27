package com.amazon.music.account;

import com.amazon.cirrus.shared.model.TermsOfUse;
import com.amazon.music.account.Device;
import com.amazon.musicidentityservice.GetMusicRequestIdentityContextTokenRequest;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityHints;
import com.amazon.stratus.AcceptTermsOfUseRequest;
import com.amazon.stratus.AttemptAccountCreationRequest;
import com.amazon.stratus.IsAccountValidRequest;
import com.amazon.stratus.ListDevicesByCustomerIdRequest;
import com.amazon.stratus.RetrieveCapabilityRequest;
import com.amazon.stratus.RetrieveCustomerHomeRequest;
import com.amazon.stratus.RetrieveDeviceRequest;
import com.amazon.stratus.RetrievePreferencesRequest;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesRequest;
import com.amazon.stratus.UpdateDeviceRequest;
import com.amazon.stratus.UpdatePreferencesRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
class AccountServiceRequestBuilder {
    private static final Boolean IS_CHILD_VALUE = Boolean.TRUE;
    private final AccountServiceConfiguration configuration;

    public AccountServiceRequestBuilder(AccountServiceConfiguration accountServiceConfiguration) {
        this.configuration = (AccountServiceConfiguration) Validate.notNull(accountServiceConfiguration);
    }

    AccountServiceConfiguration getConfiguration() {
        return this.configuration;
    }

    RetrievePreferencesRequest buildPreferencesRequest() {
        RetrievePreferencesRequest retrievePreferencesRequest = new RetrievePreferencesRequest();
        retrievePreferencesRequest.setDeviceType(this.configuration.getDeviceType());
        retrievePreferencesRequest.setDeviceId(this.configuration.getDeviceId());
        retrievePreferencesRequest.setNamespace("all");
        return retrievePreferencesRequest;
    }

    RetrievePrivacyCompliancePreferencesRequest buildPrivacyCompliancePreferencesRequest(String str, String str2) {
        RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest = new RetrievePrivacyCompliancePreferencesRequest();
        retrievePrivacyCompliancePreferencesRequest.setDeviceType(this.configuration.getDeviceType());
        retrievePrivacyCompliancePreferencesRequest.setDeviceId(this.configuration.getDeviceId());
        retrievePrivacyCompliancePreferencesRequest.setNamespace(str);
        return retrievePrivacyCompliancePreferencesRequest;
    }

    GetMusicRequestIdentityContextTokenRequest buildMRICTokenRequest() {
        GetMusicRequestIdentityContextTokenRequest getMusicRequestIdentityContextTokenRequest = new GetMusicRequestIdentityContextTokenRequest();
        getMusicRequestIdentityContextTokenRequest.setMusicRequestIdentityHints(buildMusicRequestIdentityHints());
        return getMusicRequestIdentityContextTokenRequest;
    }

    private MusicRequestIdentityHints buildMusicRequestIdentityHints() {
        MusicRequestIdentityHints musicRequestIdentityHints = new MusicRequestIdentityHints();
        musicRequestIdentityHints.setDeviceId(this.configuration.getDeviceId());
        musicRequestIdentityHints.setDeviceType(this.configuration.getDeviceType());
        HashMap map = new HashMap();
        map.put("CHILD_DIRECTED", IS_CHILD_VALUE);
        musicRequestIdentityHints.setIdentityFlags(map);
        return musicRequestIdentityHints;
    }

    RetrieveDeviceRequest buildDeviceRequest() {
        RetrieveDeviceRequest retrieveDeviceRequest = new RetrieveDeviceRequest();
        retrieveDeviceRequest.setDeviceId(this.configuration.getDeviceId());
        retrieveDeviceRequest.setDeviceType(this.configuration.getDeviceType());
        retrieveDeviceRequest.setTargetDeviceId(this.configuration.getDeviceId());
        retrieveDeviceRequest.setTargetDeviceType(this.configuration.getDeviceType());
        return retrieveDeviceRequest;
    }

    ListDevicesByCustomerIdRequest buildListDevicesByCustomerIdRequest() {
        ListDevicesByCustomerIdRequest listDevicesByCustomerIdRequest = new ListDevicesByCustomerIdRequest();
        listDevicesByCustomerIdRequest.setDeviceId(this.configuration.getDeviceId());
        listDevicesByCustomerIdRequest.setDeviceType(this.configuration.getDeviceType());
        return listDevicesByCustomerIdRequest;
    }

    RetrieveCapabilityRequest buildRetrieveCapabilityRequest() {
        RetrieveCapabilityRequest retrieveCapabilityRequest = new RetrieveCapabilityRequest();
        retrieveCapabilityRequest.setDeviceId(this.configuration.getDeviceId());
        retrieveCapabilityRequest.setDeviceType(this.configuration.getDeviceType());
        ArrayList arrayList = new ArrayList();
        arrayList.add(Device.Capability.RETRIEVE_ROBIN_CONTENT.name());
        retrieveCapabilityRequest.setCapabilityTypes(arrayList);
        return retrieveCapabilityRequest;
    }

    AcceptTermsOfUseRequest buildAcceptTermsOfUseRequest(String str) throws MarketplaceProvisionFailedException {
        AcceptTermsOfUseRequest acceptTermsOfUseRequest = new AcceptTermsOfUseRequest();
        acceptTermsOfUseRequest.setDeviceId(this.configuration.getDeviceId());
        acceptTermsOfUseRequest.setDeviceType(this.configuration.getDeviceType());
        TermsOfUse termsOfUse = new TermsOfUse();
        termsOfUse.setType("MP3");
        termsOfUse.setVersion("2.0.0");
        if (str == null || str.isEmpty()) {
            throw new MarketplaceProvisionFailedException();
        }
        termsOfUse.setMusicTerritory(str);
        acceptTermsOfUseRequest.setTermsOfUse(termsOfUse);
        return acceptTermsOfUseRequest;
    }

    UpdateDeviceRequest buildCatalogAuthorizeDeviceRequest() {
        UpdateDeviceRequest updateDeviceRequestBuildUpdateDeviceRequest = buildUpdateDeviceRequest();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Device.Capability.RETRIEVE_ROBIN_CONTENT.name());
        updateDeviceRequestBuildUpdateDeviceRequest.setCapabilitiesToGrant(arrayList);
        return updateDeviceRequestBuildUpdateDeviceRequest;
    }

    UpdatePreferencesRequest buildUpdateExplicitLanguagePreferenceRequest(boolean z) {
        UpdatePreferencesRequest updatePreferencesRequest = new UpdatePreferencesRequest();
        updatePreferencesRequest.setDeviceId(this.configuration.getDeviceId());
        updatePreferencesRequest.setDeviceType(this.configuration.getDeviceType());
        updatePreferencesRequest.setNamespace(this.configuration.getDeviceId());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("hasExplicitLanguage", Boolean.valueOf(!z));
        linkedHashMap.put("allowedParentalControls", getMapAsJsonString(linkedHashMap2));
        updatePreferencesRequest.setPreferences(linkedHashMap);
        return updatePreferencesRequest;
    }

    private UpdateDeviceRequest buildUpdateDeviceRequest() {
        UpdateDeviceRequest updateDeviceRequest = new UpdateDeviceRequest();
        updateDeviceRequest.setDeviceId(this.configuration.getDeviceId());
        updateDeviceRequest.setDeviceType(this.configuration.getDeviceType());
        updateDeviceRequest.setTargetDeviceId(this.configuration.getDeviceId());
        updateDeviceRequest.setTargetDeviceType(this.configuration.getDeviceType());
        return updateDeviceRequest;
    }

    private String getMapAsJsonString(Map<String, Boolean> map) {
        return new JSONObject((Map<?, ?>) map).toString();
    }

    RetrieveCustomerHomeRequest buildRetrieveCustomerHomeRequest() {
        RetrieveCustomerHomeRequest retrieveCustomerHomeRequest = new RetrieveCustomerHomeRequest();
        retrieveCustomerHomeRequest.setDeviceId(this.configuration.getDeviceId());
        retrieveCustomerHomeRequest.setDeviceType(this.configuration.getDeviceType());
        return retrieveCustomerHomeRequest;
    }

    IsAccountValidRequest buildIsAccountValidRequest() {
        IsAccountValidRequest isAccountValidRequest = new IsAccountValidRequest();
        isAccountValidRequest.setDeviceId(this.configuration.getDeviceId());
        isAccountValidRequest.setDeviceType(this.configuration.getDeviceType());
        isAccountValidRequest.setVerbose(Boolean.TRUE);
        return isAccountValidRequest;
    }

    UpdatePreferencesRequest buildUpdateTargetedAdsPreferenceRequest(boolean z) {
        UpdatePreferencesRequest updatePreferencesRequest = new UpdatePreferencesRequest();
        updatePreferencesRequest.setDeviceId(this.configuration.getDeviceId());
        updatePreferencesRequest.setDeviceType(this.configuration.getDeviceType());
        updatePreferencesRequest.setNamespace("adPreferences");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isBehavioralAdsEnabled", String.valueOf(z));
        updatePreferencesRequest.setPreferences(linkedHashMap);
        return updatePreferencesRequest;
    }

    AttemptAccountCreationRequest buildAttemptAccountCreationRequest(String str) {
        AttemptAccountCreationRequest attemptAccountCreationRequest = new AttemptAccountCreationRequest();
        attemptAccountCreationRequest.setMusicTerritory(str);
        attemptAccountCreationRequest.setAcceptTermsOfUse(Boolean.TRUE);
        attemptAccountCreationRequest.setDeviceId(this.configuration.getDeviceId());
        attemptAccountCreationRequest.setDeviceType(this.configuration.getDeviceType());
        return attemptAccountCreationRequest;
    }
}

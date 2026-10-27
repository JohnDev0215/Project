package com.amazon.music.account;

import com.amazon.music.account.Device;
import com.amazon.stratus.CapabilityInstance;
import com.amazon.stratus.CustomerAccount;
import com.amazon.stratus.DeviceCurrentState;
import com.amazon.stratus.IsAccountValidResponse;
import com.amazon.stratus.ListDevicesByCustomerIdResponse;
import com.amazon.stratus.RetrieveCustomerHomeResponse;
import com.amazon.stratus.RetrieveDeviceResponse;
import com.amazon.stratus.RetrievePreferencesResponse;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesResponse;
import com.amazon.stratus.UpdateDeviceResponse;
import com.amazon.stratus.UpdatePreferencesResponse;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class AccountServiceResponseParser {
    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceResponseParser.class.getSimpleName());

    public User parseUser(RetrieveCustomerHomeResponse retrieveCustomerHomeResponse) {
        if (retrieveCustomerHomeResponse == null) {
            return null;
        }
        return new UserBuilder().withCustomerHomeInfo(retrieveCustomerHomeResponse.getMarketplaceId(), retrieveCustomerHomeResponse.getMusicTerritory(), retrieveCustomerHomeResponse.getCustomerId()).build();
    }

    public User parseUser(IsAccountValidResponse isAccountValidResponse, RetrievePreferencesResponse retrievePreferencesResponse, RetrievePrivacyCompliancePreferencesResponse retrievePrivacyCompliancePreferencesResponse, String str) {
        if (isAccountValidResponse == null) {
            return null;
        }
        UserBuilder userBuilder = new UserBuilder(isAccountValidResponse.isAccountValid());
        CustomerAccount customerAccount = isAccountValidResponse.getCustomerAccount();
        if (customerAccount == null) {
            return null;
        }
        return userBuilder.withAccountInfo(customerAccount.getAccountInfo()).withBenefits(customerAccount.getCustomerBenefits()).withPreferences(parsePreferences(retrievePreferencesResponse), str).withCrossContextualBehavioralAdsOptOutPreferences(parseCCBAPreferences(retrievePrivacyCompliancePreferencesResponse)).build();
    }

    private final Map<String, Map<String, String>> parsePreferences(RetrievePreferencesResponse retrievePreferencesResponse) {
        if (retrievePreferencesResponse == null) {
            return null;
        }
        return retrievePreferencesResponse.getPreferencesByNamespace();
    }

    private Map<String, Map<String, String>> parseCCBAPreferences(RetrievePrivacyCompliancePreferencesResponse retrievePrivacyCompliancePreferencesResponse) {
        if (retrievePrivacyCompliancePreferencesResponse == null) {
            return null;
        }
        return retrievePrivacyCompliancePreferencesResponse.getPreferencesByNamespace();
    }

    public Device parseDevice(RetrieveDeviceResponse retrieveDeviceResponse) {
        Device.AvailableState availableStateValueOf;
        Device.AuthorizationState authorizationStateValueOf;
        HashSet hashSet = new HashSet();
        Device.AuthorizationState authorizationState = null;
        if (retrieveDeviceResponse == null || retrieveDeviceResponse.getDevice() == null) {
            availableStateValueOf = null;
        } else {
            com.amazon.stratus.Device device = retrieveDeviceResponse.getDevice();
            DeviceCurrentState currentState = device.getCurrentState();
            if (currentState != null) {
                authorizationStateValueOf = Device.AuthorizationState.valueOf(currentState.getDeviceAuthState());
                if (currentState.getDeviceCapabilities() != null) {
                    hashSet.addAll(parseCapabilities(currentState.getDeviceCapabilities()));
                }
            } else {
                authorizationStateValueOf = null;
            }
            availableStateValueOf = device.getAvailableState() != null ? Device.AvailableState.valueOf(device.getAvailableState().getNextState()) : null;
            authorizationState = authorizationStateValueOf;
        }
        return new Device(authorizationState, availableStateValueOf, hashSet);
    }

    public DeviceAuthorizations parseDeviceAuthorizations(ListDevicesByCustomerIdResponse listDevicesByCustomerIdResponse) {
        int iIntValue;
        if (listDevicesByCustomerIdResponse != null) {
            int iIntValue2 = listDevicesByCustomerIdResponse.getRemainingDevices().intValue();
            iIntValue = listDevicesByCustomerIdResponse.getRemainingDevicesForCapabilities() != null ? listDevicesByCustomerIdResponse.getRemainingDevicesForCapabilities().get(Device.Capability.RETRIEVE_ROBIN_CONTENT.name()).intValue() : 0;
            i = iIntValue2;
        } else {
            iIntValue = 0;
        }
        return new DeviceAuthorizations(i, iIntValue);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005f A[PHI: r1
  0x005f: PHI (r1v1 int) = (r1v0 int), (r1v0 int), (r1v0 int), (r1v0 int), (r1v0 int), (r1v4 int) binds: [B:3:0x0002, B:5:0x0008, B:7:0x0012, B:9:0x001c, B:11:0x0036, B:13:0x0046] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.music.account.DeviceCapabilities parseDeviceCapabilities(com.amazon.stratus.RetrieveCapabilityResponse r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            if (r5 == 0) goto L5f
            java.util.List r2 = r5.getCapabilities()
            if (r2 == 0) goto L5f
            java.util.List r2 = r5.getCapabilities()
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L5f
            java.util.List r2 = r5.getCapabilities()
            java.lang.Object r2 = r2.get(r1)
            if (r2 == 0) goto L5f
            java.util.List r5 = r5.getCapabilities()
            java.lang.Object r5 = r5.get(r1)
            com.amazon.stratus.DeviceCapabilityDetailsBase r5 = (com.amazon.stratus.DeviceCapabilityDetailsBase) r5
            com.amazon.music.account.Device$Capability r2 = com.amazon.music.account.Device.Capability.RETRIEVE_ROBIN_CONTENT
            java.lang.String r2 = r2.name()
            java.lang.String r3 = r5.getCapability()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L5f
            com.amazon.stratus.RetrieveRobinContentCapability r5 = (com.amazon.stratus.RetrieveRobinContentCapability) r5
            java.lang.Integer r1 = r5.getMaxGrantsPerCustomer()
            int r1 = r1.intValue()
            com.amazon.stratus.Device r2 = r5.getDeviceLosingCapabilityNext()
            if (r2 == 0) goto L5f
            com.amazon.stratus.Device r5 = r5.getDeviceLosingCapabilityNext()
            java.lang.String r2 = r5.getDeviceId()
            com.amazon.stratus.DeviceCurrentState r3 = r5.getCurrentState()
            if (r3 == 0) goto L60
            com.amazon.stratus.DeviceCurrentState r5 = r5.getCurrentState()
            java.lang.String r0 = r5.getDeviceName()
            goto L60
        L5f:
            r2 = r0
        L60:
            com.amazon.music.account.DeviceCapabilities r5 = new com.amazon.music.account.DeviceCapabilities
            r5.<init>(r1, r0, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.account.AccountServiceResponseParser.parseDeviceCapabilities(com.amazon.stratus.RetrieveCapabilityResponse):com.amazon.music.account.DeviceCapabilities");
    }

    public boolean parseCatalogAuthorizeSuccess(UpdateDeviceResponse updateDeviceResponse) {
        com.amazon.stratus.Device device;
        DeviceCurrentState currentState;
        if (updateDeviceResponse == null || (device = updateDeviceResponse.getDevice()) == null || (currentState = device.getCurrentState()) == null) {
            return false;
        }
        Iterator<CapabilityInstance> it = currentState.getDeviceCapabilities().iterator();
        while (it.hasNext()) {
            if (Device.Capability.RETRIEVE_ROBIN_CONTENT.equals(Device.Capability.valueOf(it.next().getCapability()))) {
                return true;
            }
        }
        return false;
    }

    public boolean parseUpdateExplicitLanguagePreferenceSuccess(UpdatePreferencesResponse updatePreferencesResponse) {
        return (updatePreferencesResponse == null || updatePreferencesResponse.getPreferences() == null || !updatePreferencesResponse.getPreferences().containsKey("allowedParentalControls")) ? false : true;
    }

    private Set<Device.Capability> parseCapabilities(List<CapabilityInstance> list) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            hashSet.add(Device.Capability.valueOf(list.get(i).getCapability()));
        }
        return hashSet;
    }

    public boolean parseUpdateTargetedAdsPreferenceSuccess(UpdatePreferencesResponse updatePreferencesResponse) {
        return (updatePreferencesResponse == null || updatePreferencesResponse.getPreferences() == null || !updatePreferencesResponse.getPreferences().containsKey("isBehavioralAdsEnabled")) ? false : true;
    }
}

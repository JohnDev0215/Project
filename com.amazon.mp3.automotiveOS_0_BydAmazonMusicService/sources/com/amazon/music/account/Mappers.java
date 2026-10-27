package com.amazon.music.account;

import com.amazon.stratus.AcceptTermsOfUseRequestSerializer;
import com.amazon.stratus.AttemptAccountCreationRequestSerializer;
import com.amazon.stratus.AttemptAccountCreationResponseDeserializer;
import com.amazon.stratus.AuthorizeDeviceRequestSerializer;
import com.amazon.stratus.AuthorizeDeviceResponseDeserializer;
import com.amazon.stratus.IsAccountValidRequestSerializer;
import com.amazon.stratus.IsAccountValidResponseDeserializer;
import com.amazon.stratus.ListDevicesByCustomerIdRequestSerializer;
import com.amazon.stratus.ListDevicesByCustomerIdResponseDeserializer;
import com.amazon.stratus.RetrieveCapabilityRequestSerializer;
import com.amazon.stratus.RetrieveCapabilityResponseDeserializer;
import com.amazon.stratus.RetrieveCustomerHomeRequestSerializer;
import com.amazon.stratus.RetrieveCustomerHomeResponseDeserializer;
import com.amazon.stratus.RetrieveDeviceRequestSerializer;
import com.amazon.stratus.RetrieveDeviceResponseDeserializer;
import com.amazon.stratus.RetrievePreferencesRequestSerializer;
import com.amazon.stratus.RetrievePreferencesResponseDeserializer;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesRequestSerializer;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesResponseDeserializer;
import com.amazon.stratus.UpdateDeviceRequestSerializer;
import com.amazon.stratus.UpdateDeviceResponseDeserializer;
import com.amazon.stratus.UpdatePreferencesRequestSerializer;
import com.amazon.stratus.UpdatePreferencesResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        RetrieveCustomerHomeRequestSerializer.register(objectMapper);
        RetrieveCustomerHomeResponseDeserializer.register(objectMapper);
        RetrieveDeviceRequestSerializer.register(objectMapper);
        RetrieveDeviceResponseDeserializer.register(objectMapper);
        ListDevicesByCustomerIdRequestSerializer.register(objectMapper);
        ListDevicesByCustomerIdResponseDeserializer.register(objectMapper);
        RetrieveCapabilityRequestSerializer.register(objectMapper);
        RetrieveCapabilityResponseDeserializer.register(objectMapper);
        AcceptTermsOfUseRequestSerializer.register(objectMapper);
        AuthorizeDeviceRequestSerializer.register(objectMapper);
        AuthorizeDeviceResponseDeserializer.register(objectMapper);
        UpdateDeviceRequestSerializer.register(objectMapper);
        UpdateDeviceResponseDeserializer.register(objectMapper);
        UpdatePreferencesRequestSerializer.register(objectMapper);
        UpdatePreferencesResponseDeserializer.register(objectMapper);
        IsAccountValidRequestSerializer.register(objectMapper);
        IsAccountValidResponseDeserializer.register(objectMapper);
        RetrievePreferencesRequestSerializer.register(objectMapper);
        RetrievePreferencesResponseDeserializer.register(objectMapper);
        RetrievePrivacyCompliancePreferencesRequestSerializer.register(objectMapper);
        RetrievePrivacyCompliancePreferencesResponseDeserializer.register(objectMapper);
        AttemptAccountCreationRequestSerializer.register(objectMapper);
        AttemptAccountCreationResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}

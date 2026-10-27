package com.amazon.musicensembleservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class BrowseHierarchyRequestSerializer extends JsonSerializer<BrowseHierarchyRequest> {
    public static final BrowseHierarchyRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BrowseHierarchyRequestSerializer browseHierarchyRequestSerializer = new BrowseHierarchyRequestSerializer();
        INSTANCE = browseHierarchyRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.BrowseHierarchyRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BrowseHierarchyRequest.class, browseHierarchyRequestSerializer);
    }

    private BrowseHierarchyRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BrowseHierarchyRequest browseHierarchyRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (browseHierarchyRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(browseHierarchyRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BrowseHierarchyRequest browseHierarchyRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(browseHierarchyRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(browseHierarchyRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(browseHierarchyRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(browseHierarchyRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(browseHierarchyRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        BrowseHierarchyTypeListSerializer.INSTANCE.serialize(browseHierarchyRequest.getType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(browseHierarchyRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(browseHierarchyRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(browseHierarchyRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(browseHierarchyRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(browseHierarchyRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(browseHierarchyRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}

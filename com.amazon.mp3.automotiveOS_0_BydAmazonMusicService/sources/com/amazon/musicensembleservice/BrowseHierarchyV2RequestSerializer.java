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
public class BrowseHierarchyV2RequestSerializer extends JsonSerializer<BrowseHierarchyV2Request> {
    public static final BrowseHierarchyV2RequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BrowseHierarchyV2RequestSerializer browseHierarchyV2RequestSerializer = new BrowseHierarchyV2RequestSerializer();
        INSTANCE = browseHierarchyV2RequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.BrowseHierarchyV2RequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BrowseHierarchyV2Request.class, browseHierarchyV2RequestSerializer);
    }

    private BrowseHierarchyV2RequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BrowseHierarchyV2Request browseHierarchyV2Request, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (browseHierarchyV2Request == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(browseHierarchyV2Request, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BrowseHierarchyV2Request browseHierarchyV2Request, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(browseHierarchyV2Request.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(browseHierarchyV2Request.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(browseHierarchyV2Request.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(browseHierarchyV2Request.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(browseHierarchyV2Request.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(browseHierarchyV2Request.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        BrowseHierarchyTypeListSerializer.INSTANCE.serialize(browseHierarchyV2Request.getType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(browseHierarchyV2Request.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(browseHierarchyV2Request.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(browseHierarchyV2Request.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(browseHierarchyV2Request.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(browseHierarchyV2Request.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}

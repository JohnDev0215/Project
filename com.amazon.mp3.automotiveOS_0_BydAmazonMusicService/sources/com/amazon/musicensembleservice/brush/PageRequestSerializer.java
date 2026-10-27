package com.amazon.musicensembleservice.brush;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class PageRequestSerializer extends JsonSerializer<PageRequest> {
    public static final PageRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PageRequestSerializer pageRequestSerializer = new PageRequestSerializer();
        INSTANCE = pageRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.PageRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PageRequest.class, pageRequestSerializer);
    }

    private PageRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PageRequest pageRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pageRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pageRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PageRequest pageRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("allowedParentalControlsString");
        JsonSerializer<List<String>> jsonSerializer = StringsSerializer.INSTANCE;
        jsonSerializer.serialize(pageRequest.getAllowedParentalControlsString(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("testTraffic");
        SimpleSerializers.serializeBoolean(pageRequest.isTestTraffic(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(pageRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_LOCALE);
        SimpleSerializers.serializeString(pageRequest.getLocale(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(pageRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("browseId");
        SimpleSerializers.serializeString(pageRequest.getBrowseId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("debug");
        SimpleSerializers.serializeBoolean(pageRequest.isDebug(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(pageRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("nextToken");
        SimpleSerializers.serializeString(pageRequest.getNextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("countOfEntitiesPerWidget");
        SimpleSerializers.serializeInteger(pageRequest.getCountOfEntitiesPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(pageRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("offset");
        SimpleSerializers.serializeInteger(pageRequest.getOffset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(pageRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("artistVideoStoryEntityAsin");
        SimpleSerializers.serializeString(pageRequest.getArtistVideoStoryEntityAsin(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(pageRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(pageRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(pageRequest.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("count");
        SimpleSerializers.serializeInteger(pageRequest.getCount(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("validationPayload");
        ValidationPayloadSerializer.INSTANCE.serialize(pageRequest.getValidationPayload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("campaignsXml");
        SimpleSerializers.serializeString(pageRequest.getCampaignsXml(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(pageRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("languagesOfPerformance");
        jsonSerializer.serialize(pageRequest.getLanguagesOfPerformance(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(pageRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("upsellContent");
        SimpleSerializers.serializeString(pageRequest.getUpsellContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(pageRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("uri");
        SimpleSerializers.serializeString(pageRequest.getUri(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentFeatures");
        ContentFeaturesSerializer.INSTANCE.serialize(pageRequest.getContentFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(pageRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}

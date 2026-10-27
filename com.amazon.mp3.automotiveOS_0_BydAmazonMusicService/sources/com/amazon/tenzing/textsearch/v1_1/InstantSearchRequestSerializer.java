package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class InstantSearchRequestSerializer extends JsonSerializer<InstantSearchRequest> {
    public static final InstantSearchRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InstantSearchRequestSerializer instantSearchRequestSerializer = new InstantSearchRequestSerializer();
        INSTANCE = instantSearchRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.InstantSearchRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InstantSearchRequest.class, instantSearchRequestSerializer);
    }

    private InstantSearchRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InstantSearchRequest instantSearchRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (instantSearchRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(instantSearchRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InstantSearchRequest instantSearchRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_LOCALE);
        SimpleSerializers.serializeString(instantSearchRequest.getLocale(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIdentity");
        CustomerIdentitySerializer.INSTANCE.serialize(instantSearchRequest.getCustomerIdentity(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(instantSearchRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("textSpec");
        InstantTextSpecificationSerializer.INSTANCE.serialize(instantSearchRequest.getTextSpec(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(SearchIntents.EXTRA_QUERY);
        SimpleSerializers.serializeString(instantSearchRequest.getQuery(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResults");
        SimpleSerializers.serializeInteger(instantSearchRequest.getMaxResults(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentTier");
        SimpleSerializers.serializeString(instantSearchRequest.getContentTier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("entitySpec");
        InstantEntitySpecificationSerializer.INSTANCE.serialize(instantSearchRequest.getEntitySpec(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContentUtils.KEY_ACTIVITY_ID);
        SimpleSerializers.serializeString(instantSearchRequest.getActivityId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queryId");
        SimpleSerializers.serializeString(instantSearchRequest.getQueryId(), jsonGenerator, serializerProvider);
    }
}

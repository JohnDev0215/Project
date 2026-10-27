package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
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
public class SuggestRequestSerializer extends JsonSerializer<SuggestRequest> {
    public static final SuggestRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SuggestRequestSerializer suggestRequestSerializer = new SuggestRequestSerializer();
        INSTANCE = suggestRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.SuggestRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SuggestRequest.class, suggestRequestSerializer);
    }

    private SuggestRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SuggestRequest suggestRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (suggestRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(suggestRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SuggestRequest suggestRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(suggestRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(suggestRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(suggestRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(suggestRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(suggestRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("size");
        SimpleSerializers.serializeInteger(suggestRequest.getSize(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(SearchIntents.EXTRA_QUERY);
        SimpleSerializers.serializeString(suggestRequest.getQuery(), jsonGenerator, serializerProvider);
    }
}

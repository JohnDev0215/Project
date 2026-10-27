package com.amazon.nimblymusicservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContextSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class HideFromMostRecentRequestSerializer extends JsonSerializer<HideFromMostRecentRequest> {
    public static final HideFromMostRecentRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HideFromMostRecentRequestSerializer hideFromMostRecentRequestSerializer = new HideFromMostRecentRequestSerializer();
        INSTANCE = hideFromMostRecentRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.HideFromMostRecentRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(HideFromMostRecentRequest.class, hideFromMostRecentRequestSerializer);
    }

    private HideFromMostRecentRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull HideFromMostRecentRequest hideFromMostRecentRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (hideFromMostRecentRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(hideFromMostRecentRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(HideFromMostRecentRequest hideFromMostRecentRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(hideFromMostRecentRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("hiddenRecentsList");
        HiddenRecentsListSerializer.INSTANCE.serialize(hideFromMostRecentRequest.getHiddenRecentsList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(hideFromMostRecentRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(hideFromMostRecentRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(hideFromMostRecentRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
    }
}

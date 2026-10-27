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
public class HideFromRecentTrackActivityRequestSerializer extends JsonSerializer<HideFromRecentTrackActivityRequest> {
    public static final HideFromRecentTrackActivityRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HideFromRecentTrackActivityRequestSerializer hideFromRecentTrackActivityRequestSerializer = new HideFromRecentTrackActivityRequestSerializer();
        INSTANCE = hideFromRecentTrackActivityRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.HideFromRecentTrackActivityRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(HideFromRecentTrackActivityRequest.class, hideFromRecentTrackActivityRequestSerializer);
    }

    private HideFromRecentTrackActivityRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull HideFromRecentTrackActivityRequest hideFromRecentTrackActivityRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (hideFromRecentTrackActivityRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(hideFromRecentTrackActivityRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(HideFromRecentTrackActivityRequest hideFromRecentTrackActivityRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(hideFromRecentTrackActivityRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(hideFromRecentTrackActivityRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(hideFromRecentTrackActivityRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(hideFromRecentTrackActivityRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("uniqueIdentifiers");
        UniqueIdentifierListSerializer.INSTANCE.serialize(hideFromRecentTrackActivityRequest.getUniqueIdentifiers(), jsonGenerator, serializerProvider);
    }
}

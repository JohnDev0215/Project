package com.amazon.musicidentityservice.shared.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class MusicRequestIdentityHintsSerializer extends JsonSerializer<MusicRequestIdentityHints> {
    public static final MusicRequestIdentityHintsSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MusicRequestIdentityHintsSerializer musicRequestIdentityHintsSerializer = new MusicRequestIdentityHintsSerializer();
        INSTANCE = musicRequestIdentityHintsSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicidentityservice.shared.model.MusicRequestIdentityHintsSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MusicRequestIdentityHints.class, musicRequestIdentityHintsSerializer);
    }

    private MusicRequestIdentityHintsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MusicRequestIdentityHints musicRequestIdentityHints, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (musicRequestIdentityHints == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(musicRequestIdentityHints, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MusicRequestIdentityHints musicRequestIdentityHints, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("linkedMusicCid");
        SimpleSerializers.serializeString(musicRequestIdentityHints.getLinkedMusicCid(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identityMetadata");
        IdentityMetadataMapSerializer.INSTANCE.serialize(musicRequestIdentityHints.getIdentityMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("linkedMusicCidConfidenceScore");
        SimpleSerializers.serializeLong(musicRequestIdentityHints.getLinkedMusicCidConfidenceScore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(musicRequestIdentityHints.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(musicRequestIdentityHints.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("speakerCid");
        SimpleSerializers.serializeString(musicRequestIdentityHints.getSpeakerCid(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identityFlags");
        IdentityFlagsMapSerializer.INSTANCE.serialize(musicRequestIdentityHints.getIdentityFlags(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("speakerCidConfidenceScore");
        SimpleSerializers.serializeLong(musicRequestIdentityHints.getSpeakerCidConfidenceScore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("activeCid");
        SimpleSerializers.serializeString(musicRequestIdentityHints.getActiveCid(), jsonGenerator, serializerProvider);
    }
}

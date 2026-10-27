package com.amazon.musicidentityservice.shared.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class MusicRequestIdentityContextSerializer extends JsonSerializer<MusicRequestIdentityContext> {
    public static final MusicRequestIdentityContextSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MusicRequestIdentityContextSerializer musicRequestIdentityContextSerializer = new MusicRequestIdentityContextSerializer();
        INSTANCE = musicRequestIdentityContextSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContextSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MusicRequestIdentityContext.class, musicRequestIdentityContextSerializer);
    }

    private MusicRequestIdentityContextSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MusicRequestIdentityContext musicRequestIdentityContext, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (musicRequestIdentityContext == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(musicRequestIdentityContext, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MusicRequestIdentityContext musicRequestIdentityContext, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("musicIdentities");
        MusicIdentitiesSerializer.INSTANCE.serialize(musicRequestIdentityContext.getMusicIdentities(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityHints");
        MusicRequestIdentityHintsSerializer.INSTANCE.serialize(musicRequestIdentityContext.getMusicRequestIdentityHints(), jsonGenerator, serializerProvider);
    }
}

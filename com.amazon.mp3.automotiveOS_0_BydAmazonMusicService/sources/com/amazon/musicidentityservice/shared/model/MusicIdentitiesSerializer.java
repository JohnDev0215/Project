package com.amazon.musicidentityservice.shared.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class MusicIdentitiesSerializer extends JsonSerializer<MusicIdentities> {
    public static final MusicIdentitiesSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MusicIdentitiesSerializer musicIdentitiesSerializer = new MusicIdentitiesSerializer();
        INSTANCE = musicIdentitiesSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicidentityservice.shared.model.MusicIdentitiesSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MusicIdentities.class, musicIdentitiesSerializer);
    }

    private MusicIdentitiesSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MusicIdentities musicIdentities, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (musicIdentities == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(musicIdentities, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MusicIdentities musicIdentities, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("musicConsumerCids");
        MusicConsumerCidMapSerializer.INSTANCE.serialize(musicIdentities.getMusicConsumerCids(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("libraryOwnerCids");
        LibraryOwnerCidListSerializer.INSTANCE.serialize(musicIdentities.getLibraryOwnerCids(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicAccountCid");
        SimpleSerializers.serializeString(musicIdentities.getMusicAccountCid(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("subscriptionOwnerCid");
        SimpleSerializers.serializeString(musicIdentities.getSubscriptionOwnerCid(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("recentsCids");
        RecentCidListSerializer.INSTANCE.serialize(musicIdentities.getRecentsCids(), jsonGenerator, serializerProvider);
    }
}

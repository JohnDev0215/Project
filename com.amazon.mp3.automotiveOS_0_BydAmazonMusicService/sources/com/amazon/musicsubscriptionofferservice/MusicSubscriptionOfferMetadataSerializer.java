package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class MusicSubscriptionOfferMetadataSerializer extends JsonSerializer<MusicSubscriptionOfferMetadata> {
    public static final MusicSubscriptionOfferMetadataSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MusicSubscriptionOfferMetadataSerializer musicSubscriptionOfferMetadataSerializer = new MusicSubscriptionOfferMetadataSerializer();
        INSTANCE = musicSubscriptionOfferMetadataSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.MusicSubscriptionOfferMetadataSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MusicSubscriptionOfferMetadata.class, musicSubscriptionOfferMetadataSerializer);
    }

    private MusicSubscriptionOfferMetadataSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MusicSubscriptionOfferMetadata musicSubscriptionOfferMetadata, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (musicSubscriptionOfferMetadata == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(musicSubscriptionOfferMetadata, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MusicSubscriptionOfferMetadata musicSubscriptionOfferMetadata, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("musicSubscriptionOfferCta");
        SimpleSerializers.serializeString(musicSubscriptionOfferMetadata.getMusicSubscriptionOfferCta(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicSubscriptionOfferTou");
        SimpleSerializers.serializeString(musicSubscriptionOfferMetadata.getMusicSubscriptionOfferTou(), jsonGenerator, serializerProvider);
    }
}

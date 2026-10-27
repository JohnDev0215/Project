package com.amazon.musicensembleservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailsMetadataTypeSerializer extends JsonSerializer<ArtistDetailsMetadataType> {
    public static final ArtistDetailsMetadataTypeSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ArtistDetailsMetadataTypeSerializer artistDetailsMetadataTypeSerializer = new ArtistDetailsMetadataTypeSerializer();
        INSTANCE = artistDetailsMetadataTypeSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.ArtistDetailsMetadataTypeSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ArtistDetailsMetadataType.class, artistDetailsMetadataTypeSerializer);
    }

    private ArtistDetailsMetadataTypeSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ArtistDetailsMetadataType artistDetailsMetadataType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (artistDetailsMetadataType == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(artistDetailsMetadataType, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ArtistDetailsMetadataType artistDetailsMetadataType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(artistDetailsMetadataType.getType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxCount");
        SimpleSerializers.serializeInteger(artistDetailsMetadataType.getMaxCount(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("sortBy");
        SimpleSerializers.serializeString(artistDetailsMetadataType.getSortBy(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("nextToken");
        SimpleSerializers.serializeString(artistDetailsMetadataType.getNextToken(), jsonGenerator, serializerProvider);
    }
}

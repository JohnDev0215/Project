package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class CatalogTrackSerializer extends JsonSerializer<CatalogTrack> {
    public static final CatalogTrackSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogTrackSerializer catalogTrackSerializer = new CatalogTrackSerializer();
        INSTANCE = catalogTrackSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.CatalogTrackSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CatalogTrack.class, catalogTrackSerializer);
    }

    private CatalogTrackSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CatalogTrack catalogTrack, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (catalogTrack == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(catalogTrack, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CatalogTrack catalogTrack, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(catalogTrack.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("asin");
        SimpleSerializers.serializeString(catalogTrack.getAsin(), jsonGenerator, serializerProvider);
    }
}

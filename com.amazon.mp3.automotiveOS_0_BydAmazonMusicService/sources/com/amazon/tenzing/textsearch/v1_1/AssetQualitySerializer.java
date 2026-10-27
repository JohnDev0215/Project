package com.amazon.tenzing.textsearch.v1_1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AssetQualitySerializer extends JsonSerializer<AssetQuality> {
    public static final AssetQualitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AssetQualitySerializer assetQualitySerializer = new AssetQualitySerializer();
        INSTANCE = assetQualitySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.AssetQualitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AssetQuality.class, assetQualitySerializer);
    }

    private AssetQualitySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AssetQuality assetQuality, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (assetQuality == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(assetQuality, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AssetQuality assetQuality, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("quality");
        QualitiesSerializer.INSTANCE.serialize(assetQuality.getQuality(), jsonGenerator, serializerProvider);
    }
}

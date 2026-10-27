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
public class DisplayableInfoSerializer extends JsonSerializer<DisplayableInfo> {
    public static final DisplayableInfoSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DisplayableInfoSerializer displayableInfoSerializer = new DisplayableInfoSerializer();
        INSTANCE = displayableInfoSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.DisplayableInfoSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DisplayableInfo.class, displayableInfoSerializer);
    }

    private DisplayableInfoSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DisplayableInfo displayableInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (displayableInfo == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(displayableInfo, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DisplayableInfo displayableInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("tierName");
        SimpleSerializers.serializeString(displayableInfo.getTierName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("productName");
        SimpleSerializers.serializeString(displayableInfo.getProductName(), jsonGenerator, serializerProvider);
    }
}

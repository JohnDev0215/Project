package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class SortOrderSerializer extends JsonSerializer<SortOrder> {
    public static final SortOrderSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SortOrderSerializer sortOrderSerializer = new SortOrderSerializer();
        INSTANCE = sortOrderSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.SortOrderSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SortOrder.class, sortOrderSerializer);
    }

    private SortOrderSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SortOrder sortOrder, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (sortOrder == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(sortOrder, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SortOrder sortOrder, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("sort");
        SimpleSerializers.serializeString(sortOrder.getSort(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("order");
        SimpleSerializers.serializeString(sortOrder.getOrder(), jsonGenerator, serializerProvider);
    }
}

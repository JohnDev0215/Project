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
public class FilterSerializer extends JsonSerializer<Filter> {
    public static final FilterSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FilterSerializer filterSerializer = new FilterSerializer();
        INSTANCE = filterSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.FilterSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Filter.class, filterSerializer);
    }

    private FilterSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Filter filter, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (filter == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(filter, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Filter filter, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("attributeName");
        SimpleSerializers.serializeString(filter.getAttributeName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("comparisonType");
        SimpleSerializers.serializeString(filter.getComparisonType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("attributeValue");
        SimpleSerializers.serializeString(filter.getAttributeValue(), jsonGenerator, serializerProvider);
    }
}

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
public class DateRangeSerializer extends JsonSerializer<DateRange> {
    public static final DateRangeSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DateRangeSerializer dateRangeSerializer = new DateRangeSerializer();
        INSTANCE = dateRangeSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.DateRangeSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DateRange.class, dateRangeSerializer);
    }

    private DateRangeSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DateRange dateRange, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (dateRange == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(dateRange, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DateRange dateRange, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("to");
        SimpleSerializers.serializeDate(dateRange.getTo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("from");
        SimpleSerializers.serializeDate(dateRange.getFrom(), jsonGenerator, serializerProvider);
    }
}

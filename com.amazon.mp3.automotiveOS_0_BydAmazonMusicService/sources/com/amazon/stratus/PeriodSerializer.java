package com.amazon.stratus;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PeriodSerializer extends JsonSerializer<Period> {
    public static final PeriodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PeriodSerializer periodSerializer = new PeriodSerializer();
        INSTANCE = periodSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.PeriodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Period.class, periodSerializer);
    }

    private PeriodSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Period period, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (period == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(period, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Period period, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("timeInterval");
        SimpleSerializers.serializeString(period.getTimeInterval(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("numberOfIntervals");
        SimpleSerializers.serializeInteger(period.getNumberOfIntervals(), jsonGenerator, serializerProvider);
    }
}

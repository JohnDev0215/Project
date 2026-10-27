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
public class InQuerySerializer extends JsonSerializer<InQuery> {
    public static final InQuerySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InQuerySerializer inQuerySerializer = new InQuerySerializer();
        INSTANCE = inQuerySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.InQuerySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InQuery.class, inQuerySerializer);
    }

    private InQuerySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InQuery inQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (inQuery == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(inQuery, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InQuery inQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("attribute");
        SimpleSerializers.serializeString(inQuery.getAttribute(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("values");
        StringListSerializer.INSTANCE.serialize(inQuery.getValues(), jsonGenerator, serializerProvider);
    }
}

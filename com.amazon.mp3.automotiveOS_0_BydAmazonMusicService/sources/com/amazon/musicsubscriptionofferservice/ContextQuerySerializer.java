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
public class ContextQuerySerializer extends JsonSerializer<ContextQuery> {
    public static final ContextQuerySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ContextQuerySerializer contextQuerySerializer = new ContextQuerySerializer();
        INSTANCE = contextQuerySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.ContextQuerySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ContextQuery.class, contextQuerySerializer);
    }

    private ContextQuerySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ContextQuery contextQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (contextQuery == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(contextQuery, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ContextQuery contextQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("context");
        SimpleSerializers.serializeString(contextQuery.getContext(), jsonGenerator, serializerProvider);
    }
}

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
public class MatchQuerySerializer extends JsonSerializer<MatchQuery> {
    public static final MatchQuerySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MatchQuerySerializer matchQuerySerializer = new MatchQuerySerializer();
        INSTANCE = matchQuerySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.MatchQuerySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MatchQuery.class, matchQuerySerializer);
    }

    private MatchQuerySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MatchQuery matchQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (matchQuery == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(matchQuery, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MatchQuery matchQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("attribute");
        SimpleSerializers.serializeString(matchQuery.getAttribute(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("value");
        SimpleSerializers.serializeString(matchQuery.getValue(), jsonGenerator, serializerProvider);
    }
}

package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AndQuerySerializer extends JsonSerializer<AndQuery> {
    public static final AndQuerySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AndQuerySerializer andQuerySerializer = new AndQuerySerializer();
        INSTANCE = andQuerySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.AndQuerySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AndQuery.class, andQuerySerializer);
    }

    private AndQuerySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AndQuery andQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (andQuery == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(andQuery, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AndQuery andQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queries");
        QueriesSerializer.INSTANCE.serialize(andQuery.getQueries(), jsonGenerator, serializerProvider);
    }
}

package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class OrQuerySerializer extends JsonSerializer<OrQuery> {
    public static final OrQuerySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        OrQuerySerializer orQuerySerializer = new OrQuerySerializer();
        INSTANCE = orQuerySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.OrQuerySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(OrQuery.class, orQuerySerializer);
    }

    private OrQuerySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull OrQuery orQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (orQuery == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(orQuery, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(OrQuery orQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queries");
        QueriesSerializer.INSTANCE.serialize(orQuery.getQueries(), jsonGenerator, serializerProvider);
    }
}

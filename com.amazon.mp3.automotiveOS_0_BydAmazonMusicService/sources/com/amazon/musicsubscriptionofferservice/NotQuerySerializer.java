package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class NotQuerySerializer extends JsonSerializer<NotQuery> {
    public static final NotQuerySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NotQuerySerializer notQuerySerializer = new NotQuerySerializer();
        INSTANCE = notQuerySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.NotQuerySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(NotQuery.class, notQuerySerializer);
    }

    private NotQuerySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull NotQuery notQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (notQuery == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(notQuery, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(NotQuery notQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(SearchIntents.EXTRA_QUERY);
        QuerySerializer.INSTANCE.serialize(notQuery.getQuery(), jsonGenerator, serializerProvider);
    }
}

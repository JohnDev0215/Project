package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class QuerySerializer extends JsonSerializer<Query> {
    public static final QuerySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        QuerySerializer querySerializer = new QuerySerializer();
        INSTANCE = querySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.QuerySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Query.class, querySerializer);
    }

    private QuerySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Query query, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (query == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (query instanceof MatchQuery) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicsubscriptionofferservice#MatchQuery");
            MatchQuerySerializer.INSTANCE.serializeFields((MatchQuery) query, jsonGenerator, serializerProvider);
        } else if (query instanceof OfferStatusQuery) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicsubscriptionofferservice#OfferStatusQuery");
            OfferStatusQuerySerializer.INSTANCE.serializeFields((OfferStatusQuery) query, jsonGenerator, serializerProvider);
        } else if (query instanceof AndQuery) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicsubscriptionofferservice#AndQuery");
            AndQuerySerializer.INSTANCE.serializeFields((AndQuery) query, jsonGenerator, serializerProvider);
        } else if (query instanceof OrQuery) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicsubscriptionofferservice#OrQuery");
            OrQuerySerializer.INSTANCE.serializeFields((OrQuery) query, jsonGenerator, serializerProvider);
        } else if (query instanceof ContextQuery) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicsubscriptionofferservice#ContextQuery");
            ContextQuerySerializer.INSTANCE.serializeFields((ContextQuery) query, jsonGenerator, serializerProvider);
        } else if (query instanceof InQuery) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicsubscriptionofferservice#InQuery");
            InQuerySerializer.INSTANCE.serializeFields((InQuery) query, jsonGenerator, serializerProvider);
        } else {
            boolean z = query instanceof NotQuery;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(query, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("com.amazon.musicsubscriptionofferservice#NotQuery");
                NotQuerySerializer.INSTANCE.serializeFields((NotQuery) query, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Query query, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}

package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class SearchRequestSerializer extends JsonSerializer<SearchRequest> {
    public static final SearchRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SearchRequestSerializer searchRequestSerializer = new SearchRequestSerializer();
        INSTANCE = searchRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.SearchRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SearchRequest.class, searchRequestSerializer);
    }

    private SearchRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SearchRequest searchRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (searchRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(searchRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SearchRequest searchRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_LOCALE);
        SimpleSerializers.serializeString(searchRequest.getLocale(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("explain");
        SimpleSerializers.serializeBoolean(searchRequest.isExplain(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("features");
        FeaturesRequestSerializer.INSTANCE.serialize(searchRequest.getFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIdentity");
        CustomerIdentitySerializer.INSTANCE.serialize(searchRequest.getCustomerIdentity(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("resultSpecs");
        ResultSpecificationsSerializer.INSTANCE.serialize(searchRequest.getResultSpecs(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(searchRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(SearchIntents.EXTRA_QUERY);
        SimpleSerializers.serializeString(searchRequest.getQuery(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.QUERYMETADATA_QUERY_PARAM);
        SimpleSerializers.serializeString(searchRequest.getQueryMetadata(), jsonGenerator, serializerProvider);
    }
}

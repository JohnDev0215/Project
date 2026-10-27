package com.amazon.music.search;

import com.amazon.tenzing.textsearch.v1_1.SearchRequestSerializer;
import com.amazon.tenzing.textsearch.v1_1.SearchResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/* JADX INFO: loaded from: classes4.dex */
public class Mappers {
    private static final ObjectMapper MAPPER;

    static {
        ObjectMapper objectMapperRegisterModule = new ObjectMapper().registerModule(new JodaModule());
        MAPPER = objectMapperRegisterModule;
        SearchRequestSerializer.register(objectMapperRegisterModule);
        SearchResponseDeserializer.register(objectMapperRegisterModule);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}

package com.amazon.avod.json;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

/* JADX INFO: loaded from: classes2.dex */
public class JacksonCache {
    public static final ObjectMapper OBJECT_MAPPER;

    static {
        Profiler.TraceLevel traceLevel = Profiler.TraceLevel.INFO;
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(traceLevel, "JacksonCache:CreateObjectMapper");
        ObjectMapper objectMapper = new ObjectMapper(JacksonJsonFactoryCache.JSON_FACTORY);
        OBJECT_MAPPER = objectMapper;
        Profiler.endTrace(traceKeyBeginTrace);
        TraceKey traceKeyBeginTrace2 = Profiler.beginTrace(traceLevel, "JacksonCache:ConfigureObjectMapper");
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        objectMapper.registerModule(new GuavaModule());
        Profiler.endTrace(traceKeyBeginTrace2);
    }
}

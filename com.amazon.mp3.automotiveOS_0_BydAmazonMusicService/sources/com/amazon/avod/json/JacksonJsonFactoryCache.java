package com.amazon.avod.json;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.fasterxml.jackson.core.JsonFactory;

/* JADX INFO: loaded from: classes2.dex */
public class JacksonJsonFactoryCache {
    public static final JsonFactory JSON_FACTORY;

    static {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "JacksonJsonFactoryCache:CreateJsonFactory");
        JSON_FACTORY = new JsonFactory();
        Profiler.endTrace(traceKeyBeginTrace);
    }
}

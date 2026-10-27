package com.google.firebase.perf.plugin.instrumentation.network.config;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.hook.HttpClientExecuteRequestContextInstrumentation;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes6.dex */
public class HttpClientExecuteRequestContextIC extends NetworkObjectInstrumentationConfig {
    public HttpClientExecuteRequestContextIC() {
        super(new HttpClientExecuteRequestContextInstrumentation.Factory(), AsmString.CLASS_HTTP_CLIENT, "execute", AsmString.METHOD_DESC_HTTP_CLIENT_EXECUTE_WITH_REQUEST_CONTEXT);
    }
}

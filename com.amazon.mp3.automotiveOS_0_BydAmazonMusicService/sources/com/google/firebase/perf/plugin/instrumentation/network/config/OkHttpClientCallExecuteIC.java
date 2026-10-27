package com.google.firebase.perf.plugin.instrumentation.network.config;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.hook.OkHttpClientCallExecuteInstrumentation;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes6.dex */
public class OkHttpClientCallExecuteIC extends NetworkObjectInstrumentationConfig {
    public OkHttpClientCallExecuteIC() {
        super(new OkHttpClientCallExecuteInstrumentation.Factory(), AsmString.CLASS_OKHTTP300_CALL, "execute", AsmString.METHOD_DESC_OKHTTP300_CALL_EXECUTE);
    }
}

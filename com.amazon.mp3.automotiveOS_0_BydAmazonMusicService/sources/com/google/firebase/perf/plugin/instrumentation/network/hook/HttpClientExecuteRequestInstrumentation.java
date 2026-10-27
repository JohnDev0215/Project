package com.google.firebase.perf.plugin.instrumentation.network.hook;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes6.dex */
public class HttpClientExecuteRequestInstrumentation extends HttpClientExecuteBaseInstrumentation {
    public HttpClientExecuteRequestInstrumentation() {
        super("execute", AsmString.METHOD_DESC_FIREBASE_PERF_HTTP_CLIENT_EXECUTE_WITH_REQUEST);
    }

    public static class Factory implements NetworkObjectInstrumentationFactory {
        @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory
        public NetworkObjectInstrumentation newObjectInstrumentation(String str, String str2, String str3) {
            return new HttpClientExecuteRequestInstrumentation();
        }
    }
}

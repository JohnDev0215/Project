package com.google.firebase.perf.plugin.instrumentation.network.hook;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes6.dex */
public class OkHttpClientCallEnqueueInstrumentation extends BaseInstrumentation {
    public OkHttpClientCallEnqueueInstrumentation(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public static class Factory implements NetworkObjectInstrumentationFactory {
        @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory
        public NetworkObjectInstrumentation newObjectInstrumentation(String str, String str2, String str3) {
            return new OkHttpClientCallEnqueueInstrumentation(AsmString.CLASS_FIREBASE_PERF_OKHTTP300_CLIENT, "enqueue", AsmString.METHOD_DESC_FIREBASE_PERF_OKHTTP300_CALL_ENQUEUE_WITH_CALLBACK);
        }
    }
}

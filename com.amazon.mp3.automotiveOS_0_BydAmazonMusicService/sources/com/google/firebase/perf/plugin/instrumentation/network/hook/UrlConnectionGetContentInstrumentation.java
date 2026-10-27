package com.google.firebase.perf.plugin.instrumentation.network.hook;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes6.dex */
public class UrlConnectionGetContentInstrumentation extends BaseInstrumentation {
    public UrlConnectionGetContentInstrumentation(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public static class Factory implements NetworkObjectInstrumentationFactory {
        @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory
        public NetworkObjectInstrumentation newObjectInstrumentation(String str, String str2, String str3) {
            return new UrlConnectionGetContentInstrumentation(AsmString.CLASS_FIREBASE_PERF_URL_CONNECTION, "getContent", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_GET_CONTENT_WITH_URL);
        }
    }
}

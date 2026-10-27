package com.google.firebase.perf.plugin.instrumentation.network.hook;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory;
import java.net.URLConnection;
import org.objectweb.asm.Type;

/* JADX INFO: loaded from: classes6.dex */
public class UrlConnectionInstrumentation extends PerfReturnObjectInstrumentation {
    public UrlConnectionInstrumentation() {
        super(Type.getType((Class<?>) URLConnection.class).getInternalName());
    }

    public static class Factory implements NetworkObjectInstrumentationFactory {
        @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory
        public NetworkObjectInstrumentation newObjectInstrumentation(String str, String str2, String str3) {
            return new UrlConnectionInstrumentation();
        }
    }
}

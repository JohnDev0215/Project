package com.google.firebase.perf.plugin.instrumentation.network.config;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.hook.UrlConnectionOpenStreamInstrumentation;
import com.google.firebase.perf.plugin.util.AsmString;
import java.lang.reflect.Method;
import java.net.URL;
import org.objectweb.asm.Type;

/* JADX INFO: loaded from: classes6.dex */
public class UrlConnectionOpenStreamIC extends NetworkObjectInstrumentationConfig {
    private static final String CLASS_NAME;
    private static final String METHOD_DESC;
    private static final String METHOD_NAME;

    static {
        try {
            CLASS_NAME = Type.getInternalName(URL.class);
            Method declaredMethod = URL.class.getDeclaredMethod(AsmString.METHOD_FIREBASE_PERF_URL_CONNECTION_OPEN_STREAM_WITH_URL, new Class[0]);
            METHOD_NAME = declaredMethod.getName();
            METHOD_DESC = Type.getType(declaredMethod).getDescriptor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UrlConnectionOpenStreamIC() {
        super(new UrlConnectionOpenStreamInstrumentation.Factory(), CLASS_NAME, METHOD_NAME, METHOD_DESC);
    }
}

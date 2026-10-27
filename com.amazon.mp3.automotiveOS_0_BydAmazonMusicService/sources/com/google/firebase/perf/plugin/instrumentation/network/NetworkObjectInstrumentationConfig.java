package com.google.firebase.perf.plugin.instrumentation.network;

/* JADX INFO: loaded from: classes6.dex */
public abstract class NetworkObjectInstrumentationConfig {
    private final String className;
    private final String id;
    private final String methodDesc;
    private final String methodName;
    private final NetworkObjectInstrumentationFactory networkObjectFactory;

    public NetworkObjectInstrumentationConfig(NetworkObjectInstrumentationFactory networkObjectInstrumentationFactory, String str, String str2, String str3) {
        this.networkObjectFactory = networkObjectInstrumentationFactory;
        this.className = str;
        this.methodName = str2;
        this.methodDesc = str3;
        this.id = getId(str, str2, str3);
    }

    public NetworkObjectInstrumentationFactory getFactory() {
        return this.networkObjectFactory;
    }

    public String getClassName() {
        return this.className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getMethodDesc() {
        return this.methodDesc;
    }

    public boolean equals(Object obj) {
        if (obj instanceof NetworkObjectInstrumentationConfig) {
            return ((NetworkObjectInstrumentationConfig) obj).id.equals(this.id);
        }
        return false;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public static String getId(String str, String str2, String str3) {
        return str + str2 + str3;
    }
}

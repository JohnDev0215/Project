package com.amazon.soa.core;

import SOACoreInterface.v1_0.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
class MethodsRegistry {
    private final Map<String, MethodCallback> callbackLookup = new HashMap();
    private final Map<String, MethodCallback> globalCallbackLookup = new HashMap();

    public void addMethods(final Set<String> methods, final MethodCallback methodCallback) {
        Iterator<String> it = methods.iterator();
        while (it.hasNext()) {
            this.callbackLookup.put(it.next(), methodCallback);
        }
    }

    public void addGlobalMethods(final Set<String> methods, final MethodCallback methodCallback) {
        Iterator<String> it = methods.iterator();
        while (it.hasNext()) {
            this.globalCallbackLookup.put(it.next(), methodCallback);
        }
    }

    public boolean isGlobalMethod(final Method method) {
        return this.globalCallbackLookup.containsKey(method.getClass().getCanonicalName());
    }

    public MethodCallback lookupCallback(final String method) {
        MethodCallback methodCallback = this.callbackLookup.get(method);
        return methodCallback != null ? methodCallback : this.globalCallbackLookup.get(method);
    }
}

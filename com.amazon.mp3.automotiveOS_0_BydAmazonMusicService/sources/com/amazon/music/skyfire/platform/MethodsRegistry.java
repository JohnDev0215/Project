package com.amazon.music.skyfire.platform;

import co.touchlab.stately.collections.IsoMutableMap;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MethodsRegistry.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007J\"\u0010\u000e\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006J\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\u0006J\u0014\u0010\u0013\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fJ\u0014\u0010\u0014\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fR \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/skyfire/platform/MethodsRegistry;", "T", "", "()V", "callbackLookup", "Lco/touchlab/stately/collections/IsoMutableMap;", "", "Lcom/amazon/music/skyfire/platform/MethodCallback;", "globalCallbackLookup", "addGlobalMethods", "", "methods", "", "methodCallback", "addMethods", "isGlobalMethod", "", "methodId", "lookupCallback", "removeGlobalMethods", "removeMethods", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MethodsRegistry<T> {
    private final IsoMutableMap<String, MethodCallback<T>> callbackLookup = new IsoMutableMap<>(null, 1, null);
    private final IsoMutableMap<String, MethodCallback<T>> globalCallbackLookup = new IsoMutableMap<>(null, 1, null);

    public final void addMethods(Set<String> methods, MethodCallback<T> methodCallback) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        Intrinsics.checkNotNullParameter(methodCallback, "methodCallback");
        Iterator<String> it = methods.iterator();
        while (it.hasNext()) {
            this.callbackLookup.put(it.next(), methodCallback);
        }
    }

    public final void addGlobalMethods(Set<String> methods, MethodCallback<T> methodCallback) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        Intrinsics.checkNotNullParameter(methodCallback, "methodCallback");
        Iterator<String> it = methods.iterator();
        while (it.hasNext()) {
            this.globalCallbackLookup.put(it.next(), methodCallback);
        }
    }

    public final void removeMethods(Set<String> methods) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        Iterator<String> it = methods.iterator();
        while (it.hasNext()) {
            this.callbackLookup.remove(it.next());
        }
    }

    public final void removeGlobalMethods(Set<String> methods) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        Iterator<String> it = methods.iterator();
        while (it.hasNext()) {
            this.globalCallbackLookup.remove(it.next());
        }
    }

    public final boolean isGlobalMethod(String methodId) {
        Intrinsics.checkNotNullParameter(methodId, "methodId");
        return this.globalCallbackLookup.containsKey(methodId);
    }

    public final MethodCallback<T> lookupCallback(String methodId) {
        Intrinsics.checkNotNullParameter(methodId, "methodId");
        MethodCallback<T> methodCallback = this.callbackLookup.get(methodId);
        return methodCallback == null ? this.globalCallbackLookup.get(methodId) : methodCallback;
    }
}

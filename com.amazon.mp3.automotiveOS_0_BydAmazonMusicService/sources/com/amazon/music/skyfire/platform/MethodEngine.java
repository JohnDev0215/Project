package com.amazon.music.skyfire.platform;

import co.touchlab.stately.HelpersJVMKt;
import co.touchlab.stately.collections.IsoMutableMap;
import com.amazon.music.skyfire.platform.core.Queue;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MethodEngine.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0015J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0006J\u001c\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00062\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bJ\"\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00062\u0012\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0\u001eJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0006J0\u0010!\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00152\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#J\u000e\u0010$\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0006R&\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006%"}, d2 = {"Lcom/amazon/music/skyfire/platform/MethodEngine;", "T", "", "()V", "methodQueueLookup", "Lco/touchlab/stately/collections/IsoMutableMap;", "", "Lcom/amazon/music/skyfire/platform/MethodQueue;", "getMethodQueueLookup$DMSFCoreKMM_release", "()Lco/touchlab/stately/collections/IsoMutableMap;", "methodsRegistry", "Lcom/amazon/music/skyfire/platform/MethodsRegistry;", "getMethodsRegistry$DMSFCoreKMM_release", "()Lcom/amazon/music/skyfire/platform/MethodsRegistry;", "ownerRegistry", "Lcom/amazon/music/skyfire/platform/OwnerRegistry;", "getOwnerRegistry$DMSFCoreKMM_release", "()Lcom/amazon/music/skyfire/platform/OwnerRegistry;", "deregisterMethods", "", "methods", "", "globalMethods", "deregisterOwner", "ownerId", "handleMethod", "methodEntry", "Lcom/amazon/music/skyfire/platform/MethodEntry;", "handleMethods", "methodEntries", "", "isOwnerAvailable", "", "registerMethods", "methodCallback", "Lcom/amazon/music/skyfire/platform/MethodCallback;", "registerOwner", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
@ExperimentalStdlibApi
public final class MethodEngine<T> {
    private final OwnerRegistry ownerRegistry = new OwnerRegistry();
    private final IsoMutableMap<String, MethodQueue<T>> methodQueueLookup = new IsoMutableMap<>(null, 1, 0 == true ? 1 : 0);
    private final MethodsRegistry<T> methodsRegistry = new MethodsRegistry<>();

    public MethodEngine() {
        HelpersJVMKt.freeze(this);
    }

    /* JADX INFO: renamed from: getOwnerRegistry$DMSFCoreKMM_release, reason: from getter */
    public final OwnerRegistry getOwnerRegistry() {
        return this.ownerRegistry;
    }

    public final IsoMutableMap<String, MethodQueue<T>> getMethodQueueLookup$DMSFCoreKMM_release() {
        return this.methodQueueLookup;
    }

    public final MethodsRegistry<T> getMethodsRegistry$DMSFCoreKMM_release() {
        return this.methodsRegistry;
    }

    public final void registerMethods(Set<String> methods, Set<String> globalMethods, MethodCallback<T> methodCallback) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        Intrinsics.checkNotNullParameter(globalMethods, "globalMethods");
        Intrinsics.checkNotNullParameter(methodCallback, "methodCallback");
        this.methodsRegistry.addMethods(methods, methodCallback);
        this.methodsRegistry.addGlobalMethods(globalMethods, methodCallback);
    }

    public final void deregisterMethods(Set<String> methods, Set<String> globalMethods) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        Intrinsics.checkNotNullParameter(globalMethods, "globalMethods");
        this.methodsRegistry.removeMethods(methods);
        this.methodsRegistry.removeGlobalMethods(globalMethods);
    }

    public final void registerOwner(String ownerId) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        this.ownerRegistry.registerOwner(ownerId);
    }

    public final void deregisterOwner(String ownerId) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        this.ownerRegistry.deregisterOwner(ownerId);
    }

    public final boolean isOwnerAvailable(String ownerId) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        return this.ownerRegistry.isOwnerAvailable(ownerId);
    }

    public final void handleMethods(String ownerId, List<MethodEntry<T>> methodEntries) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(methodEntries, "methodEntries");
        Iterator<MethodEntry<T>> it = methodEntries.iterator();
        while (it.hasNext()) {
            handleMethod(ownerId, it.next());
        }
    }

    public final void handleMethod(String ownerId, MethodEntry<T> methodEntry) {
        MethodQueue<T> singleThreadedQueue;
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(methodEntry, "methodEntry");
        String methodId = methodEntry.getMethodId();
        if (this.ownerRegistry.canProcessMethod(ownerId, methodId) || this.methodsRegistry.isGlobalMethod(methodId)) {
            Queue queue = methodEntry.getQueue();
            String strId = queue.id();
            if (!this.methodQueueLookup.containsKey(strId)) {
                if (queue instanceof com.amazon.music.skyfire.platform.core.MultiThreadedQueue) {
                    singleThreadedQueue = new MultiThreadedQueue<>(this.ownerRegistry, this.methodsRegistry);
                } else {
                    singleThreadedQueue = new SingleThreadedQueue<>(this.ownerRegistry, this.methodsRegistry);
                }
                this.methodQueueLookup.put(strId, singleThreadedQueue);
            }
            MethodQueue<T> methodQueue = this.methodQueueLookup.get(strId);
            if (methodQueue != null) {
                methodQueue.addMethod(ownerId, methodId, methodEntry.getMethod());
            }
        }
    }
}

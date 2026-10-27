package com.amazon.music.skyfire.platform;

import co.touchlab.stately.collections.IsoArrayDeque;
import co.touchlab.stately.collections.IsoMutableMap;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: MultiThreadedQueue.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u001b\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u001a2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0002J\u0015\u0010!\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\"R&\u0010\n\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u0010X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/amazon/music/skyfire/platform/MultiThreadedQueue;", "T", "", "Lcom/amazon/music/skyfire/platform/MethodQueue;", "Lcom/amazon/music/skyfire/platform/OnMethodExecutedListener;", "ownerRegistry", "Lcom/amazon/music/skyfire/platform/OwnerRegistry;", "methodsRegistry", "Lcom/amazon/music/skyfire/platform/MethodsRegistry;", "(Lcom/amazon/music/skyfire/platform/OwnerRegistry;Lcom/amazon/music/skyfire/platform/MethodsRegistry;)V", "lookup", "Lco/touchlab/stately/collections/IsoMutableMap;", "Lcom/amazon/music/skyfire/platform/MethodEntry;", "getLookup$DMSFCoreKMM_release", "()Lco/touchlab/stately/collections/IsoMutableMap;", "methodQueue", "Lco/touchlab/stately/collections/IsoArrayDeque;", "getMethodQueue$DMSFCoreKMM_release", "()Lco/touchlab/stately/collections/IsoArrayDeque;", "getMethodsRegistry$DMSFCoreKMM_release", "()Lcom/amazon/music/skyfire/platform/MethodsRegistry;", "getOwnerRegistry$DMSFCoreKMM_release", "()Lcom/amazon/music/skyfire/platform/OwnerRegistry;", ParserUtil.TAG_QUERY_PARAM_NAME, "", "addMethod", "", "ownerId", "methodId", "method", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "executeMethod", "entry", "onMethodExecuted", "(Ljava/lang/Object;)V", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
@ExperimentalStdlibApi
public final class MultiThreadedQueue<T> extends MethodQueue<T> implements OnMethodExecutedListener<T> {
    private final IsoMutableMap<T, MethodEntry<T>> lookup;
    private final IsoArrayDeque<MethodEntry<T>> methodQueue;
    private final MethodsRegistry<T> methodsRegistry;
    private final OwnerRegistry ownerRegistry;
    private final String tag;

    public MultiThreadedQueue(OwnerRegistry ownerRegistry, MethodsRegistry<T> methodsRegistry) {
        Intrinsics.checkNotNullParameter(ownerRegistry, "ownerRegistry");
        Intrinsics.checkNotNullParameter(methodsRegistry, "methodsRegistry");
        String simpleName = Reflection.getOrCreateKotlinClass(MultiThreadedQueue.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        this.tag = simpleName;
        this.ownerRegistry = ownerRegistry;
        this.methodQueue = new IsoArrayDeque<>(null, 1, 0 == true ? 1 : 0);
        this.lookup = new IsoMutableMap<>(null, 1, null);
        this.methodsRegistry = methodsRegistry;
    }

    /* JADX INFO: renamed from: getOwnerRegistry$DMSFCoreKMM_release, reason: from getter */
    public final OwnerRegistry getOwnerRegistry() {
        return this.ownerRegistry;
    }

    public final IsoArrayDeque<MethodEntry<T>> getMethodQueue$DMSFCoreKMM_release() {
        return this.methodQueue;
    }

    public final IsoMutableMap<T, MethodEntry<T>> getLookup$DMSFCoreKMM_release() {
        return this.lookup;
    }

    public final MethodsRegistry<T> getMethodsRegistry$DMSFCoreKMM_release() {
        return this.methodsRegistry;
    }

    @Override // com.amazon.music.skyfire.platform.MethodQueue
    public void addMethod(String ownerId, String methodId, T method) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(methodId, "methodId");
        Intrinsics.checkNotNullParameter(method, "method");
        MethodEntry<T> methodEntry = new MethodEntry<>(ownerId, methodId, method);
        this.lookup.put(methodEntry.getMethod(), methodEntry);
        this.methodQueue.add(methodEntry);
        executeMethod(methodEntry);
    }

    private final void executeMethod(MethodEntry<T> entry) {
        String ownerId = entry.getOwnerId();
        String methodId = entry.getMethodId();
        if (this.ownerRegistry.canProcessMethod(ownerId, methodId) || this.methodsRegistry.isGlobalMethod(methodId)) {
            MethodCallback<T> methodCallbackLookupCallback = this.methodsRegistry.lookupCallback(methodId);
            if (methodCallbackLookupCallback == null) {
                Log.INSTANCE.warning(this.tag, "methodCallback is null for methodId: " + methodId + " - forgot to call registerMethod?");
                this.methodQueue.remove(entry);
                return;
            }
            methodCallbackLookupCallback.executeMethod(entry.getMethod(), ownerId, this);
        }
    }

    @Override // com.amazon.music.skyfire.platform.OnMethodExecutedListener
    public void onMethodExecuted(T method) {
        Intrinsics.checkNotNullParameter(method, "method");
        MethodEntry<T> methodEntry = this.lookup.get(method);
        if (methodEntry != null) {
            this.methodQueue.remove(methodEntry);
        }
    }
}

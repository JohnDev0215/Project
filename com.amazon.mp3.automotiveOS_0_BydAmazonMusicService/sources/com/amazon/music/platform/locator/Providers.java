package com.amazon.music.platform.locator;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.AtomicMapKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Providers.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0005\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0003*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006R#\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/locator/Providers;", "", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "T", "key", "get", "(Ljava/lang/Object;)Lcom/amazon/music/platform/providers/InterfaceProvider;", "Lcom/amazon/music/platform/AtomicMap;", "", "providerInstances", "Lcom/amazon/music/platform/AtomicMap;", "getProviderInstances", "()Lcom/amazon/music/platform/AtomicMap;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class Providers {
    public static final Providers INSTANCE = new Providers();
    private static final AtomicMap<String, InterfaceProvider> providerInstances = AtomicMapKt.atomicMapOf();

    private Providers() {
    }

    public final AtomicMap<String, InterfaceProvider> getProviderInstances() {
        return providerInstances;
    }

    public final <T extends InterfaceProvider> T get(Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        AtomicMap<String, InterfaceProvider> atomicMap = providerInstances;
        String originalClassName = ProvidersKt.getOriginalClassName(key);
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = atomicMap.get(originalClassName);
        if (interfaceProvider instanceof InterfaceProvider) {
            return (T) interfaceProvider;
        }
        return null;
    }
}

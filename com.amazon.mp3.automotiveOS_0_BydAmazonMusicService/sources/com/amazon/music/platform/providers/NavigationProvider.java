package com.amazon.music.platform.providers;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.AtomicMapKt;
import com.amazon.music.platform.data.navigation.DestinationHandler;
import com.amazon.music.platform.locator.ProvidersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NavigationProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\r\u0010\u000eJ(\u0010\b\u001a\u00020\u0007\"\b\b\u0000\u0010\u0003*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0005R$\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/providers/NavigationProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "", "T", "destinationClass", "Lcom/amazon/music/platform/data/navigation/DestinationHandler;", "destinationHandler", "", "addRoute", "Lcom/amazon/music/platform/AtomicMap;", "", "routes", "Lcom/amazon/music/platform/AtomicMap;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public class NavigationProvider implements InterfaceProvider {
    private final AtomicMap<String, DestinationHandler<?>> routes = AtomicMapKt.atomicMapOf();

    public final <T> void addRoute(Object destinationClass, DestinationHandler<? extends T> destinationHandler) {
        Intrinsics.checkNotNullParameter(destinationClass, "destinationClass");
        Intrinsics.checkNotNullParameter(destinationHandler, "destinationHandler");
        AtomicMap<String, DestinationHandler<?>> atomicMap = this.routes;
        String originalClassName = ProvidersKt.getOriginalClassName(destinationClass);
        Intrinsics.checkNotNull(originalClassName);
        AtomicMapKt.set(atomicMap, originalClassName, destinationHandler);
    }
}

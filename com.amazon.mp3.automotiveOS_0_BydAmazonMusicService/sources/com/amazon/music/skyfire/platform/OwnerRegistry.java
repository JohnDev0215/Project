package com.amazon.music.skyfire.platform;

import co.touchlab.stately.collections.IsoMutableSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OwnerRegistry.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/skyfire/platform/OwnerRegistry;", "", "()V", "ownerRegistry", "Lco/touchlab/stately/collections/IsoMutableSet;", "", "canProcessMethod", "", "ownerId", "methodId", "deregisterOwner", "", "isOwnerAvailable", "registerOwner", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OwnerRegistry {
    private final IsoMutableSet<String> ownerRegistry = new IsoMutableSet<>(null, 1, null);

    public final void registerOwner(String ownerId) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        this.ownerRegistry.add(ownerId);
    }

    public final void deregisterOwner(String ownerId) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        this.ownerRegistry.remove(ownerId);
    }

    public final boolean isOwnerAvailable(String ownerId) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        return this.ownerRegistry.contains(ownerId);
    }

    public final boolean canProcessMethod(String ownerId, String methodId) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(methodId, "methodId");
        return this.ownerRegistry.contains(ownerId);
    }
}

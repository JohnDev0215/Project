package com.android.launcher3;

import android.content.Context;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.launcher3.-$$Lambda$InvariantDeviceProfile$fPD006L49O1k5Z9EPrGeFQ9YZ9U, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$InvariantDeviceProfile$fPD006L49O1k5Z9EPrGeFQ9YZ9U implements Consumer {
    public final /* synthetic */ InvariantDeviceProfile f$0;

    public /* synthetic */ $$Lambda$InvariantDeviceProfile$fPD006L49O1k5Z9EPrGeFQ9YZ9U(InvariantDeviceProfile invariantDeviceProfile) {
        this.f$0 = invariantDeviceProfile;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.lambda$setCurrentGrid$1$InvariantDeviceProfile((Context) obj);
    }
}

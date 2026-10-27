package com.android.internal.app;

import android.content.ComponentName;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.internal.app.-$$Lambda$lrglEmXJyu4r690vPrMCeVxM-Vk, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$lrglEmXJyu4r690vPrMCeVxMVk implements Function {
    public static final /* synthetic */ $$Lambda$lrglEmXJyu4r690vPrMCeVxMVk INSTANCE = new $$Lambda$lrglEmXJyu4r690vPrMCeVxMVk();

    private /* synthetic */ $$Lambda$lrglEmXJyu4r690vPrMCeVxMVk() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (ComponentName) ((Map.Entry) obj).getKey();
    }
}

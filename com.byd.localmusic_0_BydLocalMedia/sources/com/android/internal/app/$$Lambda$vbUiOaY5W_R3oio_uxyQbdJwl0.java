package com.android.internal.app;

import android.content.ComponentName;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.internal.app.-$$Lambda$vbUiOaY5W_R3oio_uxy-QbdJwl0, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$vbUiOaY5W_R3oio_uxyQbdJwl0 implements Function {
    public static final /* synthetic */ $$Lambda$vbUiOaY5W_R3oio_uxyQbdJwl0 INSTANCE = new $$Lambda$vbUiOaY5W_R3oio_uxyQbdJwl0();

    private /* synthetic */ $$Lambda$vbUiOaY5W_R3oio_uxyQbdJwl0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (ComponentName) ((Map.Entry) obj).getKey();
    }
}

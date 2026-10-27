package com.android.server.connectivity;

import java.util.function.Function;

/* JADX INFO: renamed from: com.android.server.connectivity.-$$Lambda$NetworkMonitor$Kh6WmPzS24WnRman3oAj-x8S3_Q, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$NetworkMonitor$Kh6WmPzS24WnRman3oAjx8S3_Q implements Function {
    public final /* synthetic */ NetworkMonitor f$0;

    public /* synthetic */ $$Lambda$NetworkMonitor$Kh6WmPzS24WnRman3oAjx8S3_Q(NetworkMonitor networkMonitor) {
        this.f$0 = networkMonitor;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.f$0.makeURL((String) obj);
    }
}

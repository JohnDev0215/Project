package com.android.internal.widget;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.internal.widget.-$$Lambda$V0sK9ebSJ63M4zBVBrIINLA_QR4, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$V0sK9ebSJ63M4zBVBrIINLA_QR4 implements Consumer {
    public static final /* synthetic */ $$Lambda$V0sK9ebSJ63M4zBVBrIINLA_QR4 INSTANCE = new $$Lambda$V0sK9ebSJ63M4zBVBrIINLA_QR4();

    private /* synthetic */ $$Lambda$V0sK9ebSJ63M4zBVBrIINLA_QR4() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((MessagingMessage) obj).removeMessage();
    }
}

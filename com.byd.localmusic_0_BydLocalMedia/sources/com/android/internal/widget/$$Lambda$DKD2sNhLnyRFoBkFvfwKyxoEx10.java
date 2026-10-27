package com.android.internal.widget;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.internal.widget.-$$Lambda$DKD2sNhLnyRFoBkFvfwKyxoEx10, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$DKD2sNhLnyRFoBkFvfwKyxoEx10 implements Consumer {
    public static final /* synthetic */ $$Lambda$DKD2sNhLnyRFoBkFvfwKyxoEx10 INSTANCE = new $$Lambda$DKD2sNhLnyRFoBkFvfwKyxoEx10();

    private /* synthetic */ $$Lambda$DKD2sNhLnyRFoBkFvfwKyxoEx10() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((MessagingMessage) obj).removeMessage();
    }
}

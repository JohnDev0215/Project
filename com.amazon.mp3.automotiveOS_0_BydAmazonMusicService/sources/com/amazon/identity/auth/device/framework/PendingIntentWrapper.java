package com.amazon.identity.auth.device.framework;

import android.app.PendingIntent;
import android.content.Intent;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class PendingIntentWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PendingIntent f329a;

    /* JADX INFO: compiled from: DCP */
    public enum PendingIntentType {
        Broadcast,
        Service,
        Activity
    }

    public PendingIntentWrapper(PendingIntent pendingIntent, PendingIntentType pendingIntentType, Intent intent) {
        this.f329a = pendingIntent;
    }
}

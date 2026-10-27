package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.framework.PendingIntentWrapper;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"MissingPermission"})
public class h4 {
    public static final String b = "com.amazon.identity.auth.device.h4";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AlarmManager f359a;

    public h4() {
        this.f359a = null;
    }

    public void a(int i, long j, PendingIntentWrapper pendingIntentWrapper) {
        try {
            this.f359a.set(i, j, pendingIntentWrapper != null ? pendingIntentWrapper.f329a : null);
        } catch (SecurityException e) {
            Log.e(ga.a(b), "AlarmManagerWrapper set failed!", e);
        }
    }

    public h4(Context context) {
        this.f359a = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }
}

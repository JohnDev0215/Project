package com.google.firebase.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.stats.WakeLock;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes6.dex */
final class WakeLockHolder {
    static final long WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(1);
    private static final Object syncObject = new Object();

    @GuardedBy("WakeLockHolder.syncObject")
    private static WakeLock wakeLock;

    WakeLockHolder() {
    }

    @GuardedBy("WakeLockHolder.syncObject")
    private static void checkAndInitWakeLock(Context context) {
        if (wakeLock == null) {
            WakeLock wakeLock2 = new WakeLock(context, 1, "wake:com.google.firebase.iid.WakeLockHolder");
            wakeLock = wakeLock2;
            wakeLock2.setReferenceCounted(true);
        }
    }

    static ComponentName startWakefulService(@NonNull Context context, @NonNull Intent intent) {
        synchronized (syncObject) {
            checkAndInitWakeLock(context);
            boolean zIsWakefulIntent = isWakefulIntent(intent);
            setAsWakefulIntent(intent, true);
            ComponentName componentNameStartService = context.startService(intent);
            if (componentNameStartService == null) {
                return null;
            }
            if (!zIsWakefulIntent) {
                wakeLock.acquire(WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
            }
            return componentNameStartService;
        }
    }

    static void sendWakefulServiceIntent(Context context, WithinAppServiceConnection withinAppServiceConnection, final Intent intent) {
        synchronized (syncObject) {
            checkAndInitWakeLock(context);
            boolean zIsWakefulIntent = isWakefulIntent(intent);
            setAsWakefulIntent(intent, true);
            if (!zIsWakefulIntent) {
                wakeLock.acquire(WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
            }
            withinAppServiceConnection.sendIntent(intent).addOnCompleteListener(new EnhancedIntentService$$ExternalSyntheticLambda0(), new OnCompleteListener() { // from class: com.google.firebase.messaging.WakeLockHolder$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    WakeLockHolder.completeWakefulIntent(intent);
                }
            });
        }
    }

    private static void setAsWakefulIntent(@NonNull Intent intent, boolean z) {
        intent.putExtra("com.google.firebase.iid.WakeLockHolder.wakefulintent", z);
    }

    @VisibleForTesting
    static boolean isWakefulIntent(@NonNull Intent intent) {
        return intent.getBooleanExtra("com.google.firebase.iid.WakeLockHolder.wakefulintent", false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void completeWakefulIntent(@NonNull Intent intent) {
        synchronized (syncObject) {
            if (wakeLock != null && isWakefulIntent(intent)) {
                setAsWakefulIntent(intent, false);
                wakeLock.release();
            }
        }
    }
}

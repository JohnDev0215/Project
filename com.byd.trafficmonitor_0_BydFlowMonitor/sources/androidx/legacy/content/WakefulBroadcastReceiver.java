package androidx.legacy.content;

import android.content.BroadcastReceiver;
import android.os.PowerManager;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseArray<PowerManager.WakeLock> f1007a = new SparseArray<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f1008b = 1;
}

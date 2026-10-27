package X;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f9951b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f9952a;

    public e(Object obj) {
        this.f9952a = obj;
    }

    public e() {
        this.f9952a = new Object();
        new Handler(Looper.getMainLooper(), new Z4.e(this, 0));
    }
}

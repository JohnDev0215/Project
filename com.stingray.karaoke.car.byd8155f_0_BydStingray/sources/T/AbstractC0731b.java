package T;

import android.os.Looper;

/* JADX INFO: renamed from: T.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0731b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f8232a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f8233b = 0;

    static {
        long id;
        h6.r.o(C0729a.f8229n);
        try {
            id = Looper.getMainLooper().getThread().getId();
        } catch (Exception unused) {
            id = -1;
        }
        f8232a = id;
    }
}

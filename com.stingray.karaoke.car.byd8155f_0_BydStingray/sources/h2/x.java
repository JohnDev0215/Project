package h2;

import android.os.Handler;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ArrayList f15247b = new ArrayList(50);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f15248a;

    public x(Handler handler) {
        this.f15248a = handler;
    }

    public static w b() {
        w wVar;
        ArrayList arrayList = f15247b;
        synchronized (arrayList) {
            try {
                wVar = arrayList.isEmpty() ? new w() : (w) arrayList.remove(arrayList.size() - 1);
            } catch (Throwable th) {
                throw th;
            }
        }
        return wVar;
    }

    public final w a(int i7, Object obj) {
        w wVarB = b();
        wVarB.f15246a = this.f15248a.obtainMessage(i7, obj);
        return wVarB;
    }

    public final boolean c(Runnable runnable) {
        return this.f15248a.post(runnable);
    }

    public final void d(int i7) {
        AbstractC1346c.c(i7 != 0);
        this.f15248a.removeMessages(i7);
    }

    public final boolean e(int i7) {
        return this.f15248a.sendEmptyMessage(i7);
    }
}

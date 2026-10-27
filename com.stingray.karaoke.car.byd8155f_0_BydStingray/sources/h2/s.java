package h2;

import U8.EnumC0810a;
import V8.InterfaceC0817f;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.SparseIntArray;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static s f15234e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static HandlerThread f15235f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Handler f15236g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f15237a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f15238b;
    public Object c;
    public Object d;

    public s(Context context) {
        this.f15238b = new Handler(Looper.getMainLooper());
        this.c = new CopyOnWriteArrayList();
        this.d = new Object();
        this.f15237a = 0;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(new r(this, 0), intentFilter);
    }

    public static void a(s sVar, int i7) {
        synchronized (sVar.d) {
            try {
                if (sVar.f15237a == i7) {
                    return;
                }
                sVar.f15237a = i7;
                for (WeakReference weakReference : (CopyOnWriteArrayList) sVar.c) {
                    x2.f fVar = (x2.f) weakReference.get();
                    if (fVar != null) {
                        fVar.a(i7);
                    } else {
                        ((CopyOnWriteArrayList) sVar.c).remove(weakReference);
                    }
                }
            } finally {
            }
        }
    }

    public static void b(SparseIntArray sparseIntArray, long j3) {
        if (sparseIntArray != null) {
            int i7 = (int) ((500000 + j3) / 1000000);
            if (j3 >= 0) {
                sparseIntArray.put(i7, sparseIntArray.get(i7) + 1);
            }
        }
    }

    public static synchronized s c(Context context) {
        try {
            if (f15234e == null) {
                f15234e = new s(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return f15234e;
    }

    public int d() {
        int i7;
        synchronized (this.d) {
            i7 = this.f15237a;
        }
        return i7;
    }

    public void e() {
        HandlerThread handlerThread;
        synchronized (this.d) {
            try {
                AbstractC1346c.j(this.f15237a > 0);
                int i7 = this.f15237a - 1;
                this.f15237a = i7;
                if (i7 == 0 && (handlerThread = (HandlerThread) this.c) != null) {
                    handlerThread.quit();
                    this.c = null;
                    this.f15238b = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public s(int i7, EnumC0810a enumC0810a, InterfaceC0817f interfaceC0817f, InterfaceC2194h interfaceC2194h) {
        this.f15238b = interfaceC0817f;
        this.f15237a = i7;
        this.c = enumC0810a;
        this.d = interfaceC2194h;
    }
}

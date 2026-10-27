package T1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import g1.AbstractC1274a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class r implements h {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1.e f8514m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Y4.e f8515n;
    public final Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Handler f8516p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ThreadPoolExecutor f8517q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ThreadPoolExecutor f8518r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AbstractC1274a f8519s;

    public r(Context context, C1.e eVar) {
        Y4.e eVar2 = s.d;
        this.o = new Object();
        l9.l.F(context, "Context cannot be null");
        this.l = context.getApplicationContext();
        this.f8514m = eVar;
        this.f8515n = eVar2;
    }

    @Override // T1.h
    public final void a(AbstractC1274a abstractC1274a) {
        synchronized (this.o) {
            this.f8519s = abstractC1274a;
        }
        synchronized (this.o) {
            try {
                if (this.f8519s == null) {
                    return;
                }
                if (this.f8517q == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new a("emojiCompat", 0));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    this.f8518r = threadPoolExecutor;
                    this.f8517q = threadPoolExecutor;
                }
                this.f8517q.execute(new A2.l(this, 8));
            } finally {
            }
        }
    }

    public final void b() {
        synchronized (this.o) {
            try {
                this.f8519s = null;
                Handler handler = this.f8516p;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                this.f8516p = null;
                ThreadPoolExecutor threadPoolExecutor = this.f8518r;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f8517q = null;
                this.f8518r = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final C1.k c() {
        try {
            Y4.e eVar = this.f8515n;
            Context context = this.l;
            C1.e eVar2 = this.f8514m;
            eVar.getClass();
            ArrayList arrayList = new ArrayList(1);
            Object obj = new Object[]{eVar2}[0];
            Objects.requireNonNull(obj);
            arrayList.add(obj);
            C1.j jVarA = C1.d.a(context, Collections.unmodifiableList(arrayList));
            int i7 = jVarA.l;
            if (i7 != 0) {
                throw new RuntimeException(A7.k.g("fetchFonts failed (", i7, ")"));
            }
            C1.k[] kVarArr = (C1.k[]) ((List) jVarA.f944m).get(0);
            if (kVarArr == null || kVarArr.length == 0) {
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            return kVarArr[0];
        } catch (PackageManager.NameNotFoundException e10) {
            throw new RuntimeException("provider not found", e10);
        }
    }
}

package p;

import android.content.Context;
import com.getkeepsafe.relinker.MissingLibraryException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class px2 implements Runnable {
    public final /* synthetic */ int c = 0;
    public final /* synthetic */ Context e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;
    public final /* synthetic */ Object m;
    public final /* synthetic */ Object n;

    public px2(qx2 qx2Var, androidx.work.impl.utils.futures.b bVar, UUID uuid, ew0 ew0Var, Context context) {
        this.n = qx2Var;
        this.k = bVar;
        this.l = uuid;
        this.m = ew0Var;
        this.e = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.c) {
            case 0:
                try {
                    if (!(((androidx.work.impl.utils.futures.b) this.k).c instanceof d1)) {
                        String string = ((UUID) this.l).toString();
                        ky2 ky2VarH = ((qx2) this.n).c.h(string);
                        if (ky2VarH == null || ky2VarH.b.a()) {
                            throw new IllegalStateException("Calls to setForegroundAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
                        }
                        ((qx1) ((qx2) this.n).b).f(string, (ew0) this.m);
                        this.e.startService(ci2.a(this.e, ci1.u(ky2VarH), (ew0) this.m));
                    }
                    ((androidx.work.impl.utils.futures.b) this.k).j(null);
                    return;
                } catch (Throwable th) {
                    ((androidx.work.impl.utils.futures.b) this.k).k(th);
                    return;
                }
            default:
                Object obj = this.m;
                try {
                    ((r02) this.n).c(this.e, (String) this.k, (String) this.l);
                    u4.z(obj);
                    throw null;
                } catch (MissingLibraryException unused) {
                    u4.z(obj);
                    throw null;
                } catch (UnsatisfiedLinkError unused2) {
                    u4.z(obj);
                    throw null;
                }
        }
    }
}

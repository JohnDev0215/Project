package p;

import android.content.ComponentName;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import com.google.android.play.core.assetpacks.zzbn;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class xf1 implements Runnable {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;
    public final Object l;

    public /* synthetic */ xf1(com.google.android.play.core.assetpacks.b bVar, Bundle bundle, zzbn zzbnVar) {
        this.c = 8;
        this.e = bVar;
        this.k = bundle;
        this.l = zzbnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object objCall = null;
        switch (this.c) {
            case 0:
                if (!cg1.b) {
                    ((zf1) this.l).a();
                    throw null;
                }
                Objects.toString((ComponentName) this.e);
                Objects.toString((IBinder) this.k);
                u4.z(((zf1) this.l).b);
                throw null;
            case 1:
                hk0 hk0Var = (hk0) this.e;
                Typeface typeface = (Typeface) this.k;
                zu zuVar = (zu) hk0Var.e;
                if (zuVar != null) {
                    zuVar.v0(typeface);
                    return;
                }
                return;
            case 2:
                ((cz) this.e).accept(this.k);
                return;
            case 3:
                try {
                    objCall = ((Callable) this.e).call();
                    break;
                } catch (Exception unused) {
                }
                ((Handler) this.l).post(new xf1(this, (cz) this.k, objCall, 2));
                return;
            case 4:
                Iterator it = ((kb) ((ng1) this.l).d.m.keySet()).iterator();
                while (true) {
                    g41 g41Var = (g41) it;
                    if (!g41Var.hasNext()) {
                        return;
                    }
                    jg1 jg1Var = (jg1) ((ng1) this.l).d.m.get((IBinder) g41Var.next());
                    ng1 ng1Var = (ng1) this.l;
                    String str = (String) this.e;
                    Bundle bundle = (Bundle) this.k;
                    ng1Var.getClass();
                    List<pr1> list = (List) jg1Var.e.get(str);
                    if (list != null) {
                        for (pr1 pr1Var : list) {
                            if (he1.z(bundle, (Bundle) pr1Var.b)) {
                                ng1Var.d.d(str, jg1Var, (Bundle) pr1Var.b, bundle);
                            }
                        }
                    }
                }
                break;
            case 5:
                Iterator it2 = ((kb) ((bh1) ((hg1) this.l).b).m.keySet()).iterator();
                while (true) {
                    g41 g41Var2 = (g41) it2;
                    if (!g41Var2.hasNext()) {
                        return;
                    }
                    jg1 jg1Var2 = (jg1) ((bh1) ((hg1) this.l).b).m.get((IBinder) g41Var2.next());
                    hg1 hg1Var = (hg1) this.l;
                    String str2 = (String) this.e;
                    Bundle bundle2 = (Bundle) this.k;
                    hg1Var.getClass();
                    List<pr1> list2 = (List) jg1Var2.e.get(str2);
                    if (list2 != null) {
                        for (pr1 pr1Var2 : list2) {
                            if (he1.z(bundle2, (Bundle) pr1Var2.b)) {
                                ((bh1) hg1Var.b).d(str2, jg1Var2, (Bundle) pr1Var2.b, bundle2);
                            }
                        }
                    }
                }
                break;
            case 6:
                ((qx1) this.e).g((xf2) this.k, (kk0) this.l);
                return;
            case 7:
                if (((View) this.k) != null) {
                    ((r11) this.l).getClass();
                    return;
                }
                return;
            case 8:
                com.google.android.play.core.assetpacks.b bVar = (com.google.android.play.core.assetpacks.b) this.e;
                Bundle bundle3 = (Bundle) this.k;
                zzbn zzbnVar = (zzbn) this.l;
                com.google.android.play.core.assetpacks.j jVar = bVar.g;
                jVar.getClass();
                if (((Boolean) jVar.c(new ww1(jVar, 7, bundle3))).booleanValue()) {
                    bVar.o.post(new yf1(bVar, zzbnVar, 24));
                    ((d33) ((h13) bVar.i).a()).e();
                    return;
                }
                return;
            case 9:
                ((l32) this.l).b((ch) this.e, (bj2) this.k);
                ((AtomicInteger) ((l32) this.l).i.k).set(0);
                l32 l32Var = (l32) this.l;
                double dMin = Math.min(3600000.0d, Math.pow(l32Var.b, l32Var.a()) * (60000.0d / l32Var.a));
                String.format(Locale.US, "%.2f", Double.valueOf(dMin / 1000.0d));
                String str3 = ((ch) this.e).b;
                Log.isLoggable("FirebaseCrashlytics", 3);
                try {
                    Thread.sleep((long) dMin);
                    return;
                } catch (InterruptedException unused2) {
                    return;
                }
            default:
                SequentialDisposable sequentialDisposable = (SequentialDisposable) this.e;
                Disposable disposableA = ((io.reactivex.rxjava3.internal.schedulers.g) this.l).a((Runnable) this.k);
                sequentialDisposable.getClass();
                DisposableHelper.c(sequentialDisposable, disposableA);
                return;
        }
    }

    public /* synthetic */ xf1(Object obj, Object obj2, Object obj3, int i) {
        this.c = i;
        this.l = obj;
        this.e = obj2;
        this.k = obj3;
    }

    public xf1(qx1 qx1Var, xf2 xf2Var, kk0 kk0Var) {
        this.c = 6;
        v03.j(qx1Var, "processor");
        this.e = qx1Var;
        this.k = xf2Var;
        this.l = kk0Var;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ xf1(l32 l32Var, ch chVar, bj2 bj2Var) {
        this(l32Var, chVar, bj2Var, 9);
        this.c = 9;
    }
}

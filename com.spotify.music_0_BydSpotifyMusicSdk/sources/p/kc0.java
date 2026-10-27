package p;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.SpecialEffectsController$Operation$State;
import com.spotify.webapi.service.models.Search;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kc0 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;

    public /* synthetic */ kc0(Object obj, Object obj2, Object obj3, int i) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
        this.l = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zBooleanValue = true;
        switch (this.c) {
            case 0:
                y3 y3Var = (y3) this.e;
                vr2 vr2Var = (vr2) this.k;
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.l;
                y3Var.getClass();
                try {
                    sv0 sv0VarF = ln1.f(y3Var.a);
                    if (sv0VarF == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    rv0 rv0Var = (rv0) sv0VarF.a;
                    synchronized (rv0Var.d) {
                        rv0Var.f = threadPoolExecutor;
                        break;
                    }
                    sv0VarF.a.a(new lc0(vr2Var, threadPoolExecutor));
                    return;
                } catch (Throwable th) {
                    vr2Var.n(th);
                    threadPoolExecutor.shutdown();
                    return;
                }
            case 1:
                List list = (List) this.e;
                bf2 bf2Var = (bf2) this.k;
                androidx.fragment.app.a aVar = (androidx.fragment.app.a) this.l;
                v03.j(list, "$awaitingContainerChanges");
                v03.j(bf2Var, "$operation");
                v03.j(aVar, "this$0");
                if (list.contains(bf2Var)) {
                    list.remove(bf2Var);
                    View view = bf2Var.c.M;
                    SpecialEffectsController$Operation$State specialEffectsController$Operation$State = bf2Var.a;
                    v03.i(view, Search.Type.VIEW);
                    specialEffectsController$Operation$State.a(view);
                    return;
                }
                return;
            case 2:
                dy0 dy0Var = (dy0) this.e;
                View view2 = (View) this.k;
                Rect rect = (Rect) this.l;
                v03.j(dy0Var, "$impl");
                v03.j(rect, "$lastInEpicenterRect");
                dy0.g(view2, rect);
                return;
            case 3:
                androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) this.e;
                View view3 = (View) this.k;
                w70 w70Var = (w70) this.l;
                v03.j(aVar2, "this$0");
                v03.j(w70Var, "$animationInfo");
                aVar2.a.endViewTransition(view3);
                w70Var.b();
                return;
            case 4:
                gz1 gz1Var = (gz1) this.e;
                String str = (String) this.k;
                List list2 = (List) this.l;
                v03.j(gz1Var, "this$0");
                v03.j(str, "$sql");
                v03.j(list2, "$inputArguments");
                throw null;
            case 5:
                qx1 qx1Var = (qx1) this.e;
                lb1 lb1Var = (lb1) this.k;
                vy2 vy2Var = (vy2) this.l;
                String str2 = qx1.l;
                qx1Var.getClass();
                try {
                    zBooleanValue = ((Boolean) lb1Var.get()).booleanValue();
                    break;
                } catch (InterruptedException | ExecutionException unused) {
                }
                synchronized (qx1Var.k) {
                    try {
                        rx2 rx2VarU = ci1.u(vy2Var.k);
                        String str3 = rx2VarU.a;
                        if (qx1Var.c(str3) == vy2Var) {
                            qx1Var.b(str3);
                        }
                        vc1.c().getClass();
                        Iterator it = qx1Var.j.iterator();
                        while (it.hasNext()) {
                            ((uk0) it.next()).b(rx2VarU, zBooleanValue);
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                    break;
                }
                return;
            case 6:
                Callable callable = (Callable) this.e;
                Executor executor = (Executor) this.k;
                bj2 bj2Var = (bj2) this.l;
                ExecutorService executorService = ks2.a;
                try {
                    ((aj2) callable.call()).a(executor, new es2(bj2Var, 1));
                    return;
                } catch (Exception e) {
                    bj2Var.a.f(e);
                    return;
                }
            case 7:
                cv cvVar = (cv) this.e;
                pp ppVar = (pp) this.k;
                u42 u42Var = (u42) this.l;
                k60 k60Var = (k60) cvVar.k;
                if (k60Var.e.i()) {
                    ppVar.a(k60Var, new IOException("Canceled"));
                    return;
                } else {
                    ppVar.b(k60Var, u42Var);
                    return;
                }
            default:
                cv cvVar2 = (cv) this.e;
                ((pp) this.k).a((k60) cvVar2.k, (Throwable) this.l);
                return;
        }
    }
}

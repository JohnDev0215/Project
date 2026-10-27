package defpackage;

import J.N;
import android.os.SystemClock;
import defpackage.ax0;
import defpackage.s4;
import defpackage.v4;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class vk implements Runnable {
    public final /* synthetic */ int e = 0;
    public final /* synthetic */ long f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;
    public final /* synthetic */ Object j;

    public /* synthetic */ vk(long j, od odVar, Callback callback, al alVar, ax0.a aVar) {
        this.g = alVar;
        this.h = aVar;
        this.i = callback;
        this.j = odVar;
        this.f = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.j;
        Object obj2 = this.i;
        Object obj3 = this.h;
        Object obj4 = this.g;
        switch (i) {
            case 0:
                al alVar = (al) obj4;
                ax0.a aVar = (ax0.a) obj3;
                Callback callback = (Callback) obj2;
                od odVar = (od) obj;
                long j = this.f;
                cx0 cx0Var = alVar.a;
                if (odVar != null) {
                    callback.onResult(odVar);
                    String str = aVar.b;
                    alVar.a.getClass();
                    N.Mlt0uwKm(str, 9);
                    cx0Var.getClass();
                    N.MNxzlAnV(aVar.b, j);
                } else {
                    N.Mno1Q7sp(cx0Var.a, 1, aVar.a, aVar.b, aVar.f, new t02(new wk(alVar, callback, aVar, j, 0), 10));
                }
                break;
            default:
                u4 u4Var = (u4) obj4;
                k00 k00Var = (k00) obj3;
                Executor executor = (Executor) obj2;
                s4.a aVar2 = (s4.a) obj;
                u4Var.getClass();
                HashMap map = new HashMap();
                HashSet hashSet = u4Var.h;
                for (String str2 : (String[]) hashSet.toArray(new String[hashSet.size()])) {
                    c92 c92VarC = u4Var.C(str2, k00Var);
                    if (c92VarC != null) {
                        map.put(str2, c92VarC);
                    }
                }
                oj2.b0(SystemClock.elapsedRealtime() - this.f, "Android.FontLookup.FetchAllFontFiles.Time");
                executor.execute(new kg3(aVar2, map, 1));
                break;
        }
    }

    public /* synthetic */ vk(u4 u4Var, CoreImpl coreImpl, long j, Executor executor, v4.e eVar) {
        this.g = u4Var;
        this.h = coreImpl;
        this.f = j;
        this.i = executor;
        this.j = eVar;
    }
}

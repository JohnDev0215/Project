package a.b.k;

import a.h.k.w;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class m implements a.h.k.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f45a;

    public m(l lVar) {
        this.f45a = lVar;
    }

    @Override // a.h.k.k
    public w a(View view, w wVar) {
        int iD = wVar.d();
        int iO = this.f45a.O(wVar, null);
        if (iD != iO) {
            int iB = wVar.b();
            int iC = wVar.c();
            int iA = wVar.a();
            w.a bVar = Build.VERSION.SDK_INT >= 30 ? new w.b(wVar) : new w.a(wVar);
            bVar.f845b.setSystemWindowInsets(a.h.e.b.a(iB, iO, iC, iA).b());
            wVar = bVar.a();
        }
        return a.h.k.q.J(view, wVar);
    }
}

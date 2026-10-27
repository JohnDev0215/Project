package n6;

import S8.AbstractC0727y;
import V8.AbstractC0828q;
import V8.V;
import V8.f0;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0 f20151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V8.N f20152b;
    public Long c;

    public z() {
        int i7 = 3;
        f0 f0VarC = AbstractC0828q.c(null);
        this.f20151a = f0VarC;
        V8.J j3 = new V8.J(AbstractC0828q.c(null), f0VarC, new H5.d(i7, null, i7));
        Z8.e eVar = S8.E.f8084a;
        this.f20152b = AbstractC0828q.m(j3, AbstractC0727y.a(X8.m.f10573a), V.a(), new m7.i(null, null));
        this.c = 0L;
    }

    public final List a() {
        ArrayList arrayList;
        w wVar = (w) this.f20151a.getValue();
        return (wVar == null || (arrayList = wVar.f20138a) == null) ? n7.u.l : arrayList;
    }
}

package e.d0.f;

import e.a0;
import e.d0.g.g;
import e.t;
import e.w;
import e.y;
import java.io.IOException;

/* JADX INFO: compiled from: ConnectInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f3361a;

    public a(w wVar) {
        this.f3361a = wVar;
    }

    @Override // e.t
    public a0 a(t.a aVar) throws IOException {
        g gVar = (g) aVar;
        y yVarB = gVar.b();
        f fVarF = gVar.f();
        return gVar.e(yVarB, fVarF, fVarF.i(this.f3361a, !yVarB.f().equals("GET")), fVarF.d());
    }
}

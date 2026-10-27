package f.b0.f;

import f.b0.g.g;
import f.r;
import f.u;
import f.w;
import f.y;
import java.io.IOException;

/* JADX INFO: compiled from: ConnectInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f5040a;

    public a(u uVar) {
        this.f5040a = uVar;
    }

    @Override // f.r
    public y a(r.a aVar) throws IOException {
        g gVar = (g) aVar;
        w wVarB = gVar.b();
        f fVarF = gVar.f();
        return gVar.d(wVarB, fVarF, fVarF.i(this.f5040a, !wVarB.f().equals("GET")), fVarF.d());
    }
}

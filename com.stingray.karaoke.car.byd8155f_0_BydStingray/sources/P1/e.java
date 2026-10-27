package P1;

import A7.o;
import m7.q;
import n0.C1886o;
import r9.z;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class e extends o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5273m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f f5274n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(f fVar, int i7) {
        super(0);
        this.f5273m = i7;
        this.f5274n = fVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f5273m) {
            case 0:
                f fVar = this.f5274n;
                z zVar = (z) fVar.c.a();
                if (s9.c.a(zVar) != -1) {
                    return C1886o.i(zVar.l.r(), true);
                }
                throw new IllegalStateException(("OkioStorage requires absolute paths, but did not get an absolute path from producePath = " + fVar.c + ", instead got " + zVar).toString());
            default:
                C8.d dVar = f.f5276f;
                f fVar2 = this.f5274n;
                synchronized (dVar) {
                    f.f5275e.remove(((z) fVar2.d.getValue()).l.r());
                }
                return q.f19267a;
        }
    }
}

package M3;

import A3.o;
import A9.k;
import K3.j;
import K3.p;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class d implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f4193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f4194b;

    public d(o oVar, j jVar) {
        this.f4193a = oVar;
        this.f4194b = jVar;
    }

    @Override // M3.f
    public final void a() {
        j jVar = this.f4194b;
        boolean z6 = jVar instanceof p;
        o oVar = this.f4193a;
        if (z6) {
            Drawable drawable = ((p) jVar).f3578a;
            oVar.getClass();
        } else {
            if (!(jVar instanceof K3.e)) {
                throw new k();
            }
            Drawable drawable2 = ((K3.e) jVar).f3526a;
            oVar.getClass();
        }
    }
}

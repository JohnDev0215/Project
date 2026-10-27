package G8;

import F8.A;
import F8.AbstractC0268c;
import F8.J;
import F8.W;

/* JADX INFO: loaded from: classes.dex */
public final class a extends AbstractC0268c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b f2720a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ W f2721b;

    public a(b bVar, W w10) {
        this.f2720a = bVar;
        this.f2721b = w10;
    }

    @Override // F8.AbstractC0268c
    public final I8.d x(J j3, I8.c cVar) {
        A7.m.f(j3, "state");
        A7.m.f(cVar, "type");
        b bVar = this.f2720a;
        A aM0 = bVar.m0(this.f2721b.g(1, bVar.a0(cVar)));
        A7.m.c(aM0);
        return aM0;
    }
}

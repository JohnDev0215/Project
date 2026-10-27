package I3;

import C1.j;
import io.sentry.C1489n1;
import s3.b0;

/* JADX INFO: loaded from: classes.dex */
public final class e extends b0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ C1489n1 f2938h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(int i7, C1489n1 c1489n1) {
        super(i7);
        this.f2938h = c1489n1;
    }

    @Override // s3.b0
    public final void c(Object obj, Object obj2, Object obj3) {
        d dVar = (d) obj2;
        ((j) this.f2938h.f16624m).r((a) obj, dVar.f2936a, dVar.f2937b, dVar.c);
    }

    @Override // s3.b0
    public final int m(Object obj, Object obj2) {
        return ((d) obj2).c;
    }
}

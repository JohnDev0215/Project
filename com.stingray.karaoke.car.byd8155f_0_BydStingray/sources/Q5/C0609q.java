package Q5;

import T.C0761q;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0609q implements InterfaceC2786o {
    public final /* synthetic */ Integer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f7026m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f7027n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f7028p;

    public C0609q(Integer num, boolean z6, long j3, String str, long j10) {
        this.l = num;
        this.f7026m = z6;
        this.f7027n = j3;
        this.o = str;
        this.f7028p = j10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws XmlPullParserException, IOException {
        D.T t10 = (D.T) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(t10, "$this$GradientButton");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(t10) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(819511029);
            g0.f fVar = g0.b.f14670v;
            g0.l lVar = g0.l.f14684a;
            boolean z6 = this.f7026m;
            Integer num = this.l;
            if (num != null && z6) {
                io.sentry.config.a.b(t10.a(lVar, fVar), num.intValue(), this.f7027n, true, c0761q, 3072);
            }
            c0761q.p(false);
            io.sentry.config.a.c(t10.a(lVar, fVar), this.o, this.f7027n, this.f7028p, null, c0761q, 0, 16);
            if (num != null && !z6) {
                io.sentry.config.a.b(t10.a(lVar, fVar), num.intValue(), this.f7027n, false, c0761q, 3072);
            }
        }
        return m7.q.f19267a;
    }
}

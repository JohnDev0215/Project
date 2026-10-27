package U5;

import D.C0138u;
import T.C0761q;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class E implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f9091m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f9092n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f9093p;

    public E(int i7, float f10, float f11, boolean z6, InterfaceC2782k interfaceC2782k) {
        this.l = i7;
        this.f9091m = f10;
        this.f9092n = f11;
        this.o = z6;
        this.f9093p = interfaceC2782k;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws XmlPullParserException, IOException {
        C0138u c0138u = (C0138u) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(c0138u, "$this$BoxWithConstraints");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(c0138u) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            l9.l.b(androidx.compose.foundation.layout.c.c(androidx.compose.foundation.layout.a.i(g0.l.f14684a, (((c0138u.b() * 0.4f) * 4) / 2) - ((c0138u.b() * 0.4f) * this.l), 0.0f, 2), 1.0f), this.l, this.f9091m, this.f9092n, this.o, this.f9093p, c0761q, 0);
        }
        return m7.q.f19267a;
    }
}

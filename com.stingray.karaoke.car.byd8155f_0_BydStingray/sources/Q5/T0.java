package Q5;

import T.C0735d;
import T.C0761q;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class T0 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6565m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f6566n;
    public final /* synthetic */ int o;

    public /* synthetic */ T0(g0.o oVar, String str, int i7, int i10) {
        this.l = i10;
        this.f6565m = oVar;
        this.f6566n = str;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.getClass();
                w0.c.r(this.f6565m, this.f6566n, c0761q, C0735d.Z(this.o | 1));
                break;
            case 1:
                num.getClass();
                C7.a.n(this.f6565m, this.f6566n, c0761q, C0735d.Z(this.o | 1));
                break;
            case 2:
                num.getClass();
                l9.d.w(this.f6565m, this.f6566n, c0761q, C0735d.Z(this.o | 1));
                break;
            case 3:
                num.getClass();
                l9.d.x(this.f6565m, this.f6566n, c0761q, C0735d.Z(this.o | 1));
                break;
            default:
                num.intValue();
                l9.d.h(this.f6565m, this.f6566n, c0761q, C0735d.Z(this.o | 1));
                break;
        }
        return m7.q.f19267a;
    }
}

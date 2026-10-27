package X5;

import T.C0735d;
import T.C0761q;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0847a implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f10220m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f10221n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f10222p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f10223q;

    public /* synthetic */ C0847a(g0.o oVar, String str, String str2, String str3, int i7, int i10) {
        this.l = i10;
        this.f10220m = oVar;
        this.f10221n = str;
        this.o = str2;
        this.f10222p = str3;
        this.f10223q = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        switch (this.l) {
            case 0:
                ((Integer) obj2).intValue();
                l9.d.d(C0735d.Z(this.f10223q | 1), (C0761q) obj, this.f10220m, this.f10221n, this.o, this.f10222p);
                break;
            case 1:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f10223q | 1);
                l9.d.b(iZ, (C0761q) obj, this.f10220m, this.f10221n, this.o, this.f10222p);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f10223q | 1);
                l9.d.c(iZ2, (C0761q) obj, this.f10220m, this.f10221n, this.o, this.f10222p);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0847a(String str, String str2, String str3, g0.o oVar, int i7) {
        this.l = 2;
        this.f10221n = str;
        this.o = str2;
        this.f10222p = str3;
        this.f10220m = oVar;
        this.f10223q = i7;
    }
}

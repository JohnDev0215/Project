package Q5;

import T.C0735d;
import T.C0761q;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import r0.AbstractC2268a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7034m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f7035n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f7036p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f7037q;

    public /* synthetic */ r(g0.o oVar, String str, boolean z6, Object obj, int i7, int i10) {
        this.l = i10;
        this.f7034m = oVar;
        this.f7035n = str;
        this.o = z6;
        this.f7037q = obj;
        this.f7036p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f7036p | 1);
                boolean z6 = this.o;
                D.L l = (D.L) this.f7037q;
                l9.d.e((g0.o) this.f7034m, this.f7035n, z6, l, (C0761q) obj, iZ);
                break;
            case 1:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f7036p | 1);
                boolean z10 = this.o;
                AbstractC2268a abstractC2268a = (AbstractC2268a) this.f7037q;
                com.bumptech.glide.c.v((g0.o) this.f7034m, this.f7035n, z10, abstractC2268a, (C0761q) obj, iZ2);
                break;
            case 2:
                ((Integer) obj2).intValue();
                C7.a.p(this.f7035n, (String) this.f7034m, this.o, (InterfaceC2782k) this.f7037q, (C0761q) obj, C0735d.Z(this.f7036p | 1));
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ3 = C0735d.Z(this.f7036p | 1);
                String str = this.f7035n;
                String str2 = (String) this.f7037q;
                l9.d.i((g0.o) this.f7034m, this.o, str, str2, (C0761q) obj, iZ3);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ r(g0.o oVar, boolean z6, String str, String str2, int i7) {
        this.l = 3;
        this.f7034m = oVar;
        this.o = z6;
        this.f7035n = str;
        this.f7037q = str2;
        this.f7036p = i7;
    }

    public /* synthetic */ r(String str, String str2, boolean z6, InterfaceC2782k interfaceC2782k, int i7) {
        this.l = 2;
        this.f7035n = str;
        this.f7034m = str2;
        this.o = z6;
        this.f7037q = interfaceC2782k;
        this.f7036p = i7;
    }
}

package Q5;

import T.C0735d;
import T.C0761q;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class W2 implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6619m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f6620n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f6621p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f6622q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f6623r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6624s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ boolean f6625t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f6626u;

    public /* synthetic */ W2(g0.o oVar, String str, String str2, String str3, boolean z6, boolean z10, InterfaceC2772a interfaceC2772a, boolean z11, int i7) {
        this.f6619m = oVar;
        this.f6620n = str;
        this.o = str2;
        this.f6621p = str3;
        this.f6622q = z6;
        this.f6623r = z10;
        this.f6624s = interfaceC2772a;
        this.f6625t = z11;
        this.f6626u = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6626u | 1);
                boolean z6 = this.f6625t;
                com.bumptech.glide.c.o(iZ, (C0761q) obj, this.f6619m, this.f6620n, this.o, this.f6621p, this.f6624s, this.f6622q, this.f6623r, z6);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6626u | 1);
                com.bumptech.glide.c.s(iZ2, (C0761q) obj, this.f6619m, this.f6620n, this.o, this.f6621p, this.f6624s, this.f6622q, this.f6623r, this.f6625t);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ W2(g0.o oVar, String str, String str2, String str3, boolean z6, boolean z10, boolean z11, InterfaceC2772a interfaceC2772a, int i7) {
        this.f6619m = oVar;
        this.f6620n = str;
        this.o = str2;
        this.f6621p = str3;
        this.f6622q = z6;
        this.f6623r = z10;
        this.f6625t = z11;
        this.f6624s = interfaceC2772a;
        this.f6626u = i7;
    }
}

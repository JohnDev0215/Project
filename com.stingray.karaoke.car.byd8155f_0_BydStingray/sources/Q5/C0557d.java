package Q5;

import T.C0735d;
import T.C0761q;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0557d implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6737m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6738n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6739p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6740q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f6741r;

    public /* synthetic */ C0557d(g0.o oVar, String str, boolean z6, InterfaceC2772a interfaceC2772a, int i7, int i10) {
        this.f6737m = oVar;
        this.f6741r = str;
        this.f6738n = z6;
        this.o = interfaceC2772a;
        this.f6739p = i7;
        this.f6740q = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6739p | 1);
                InterfaceC2772a interfaceC2772a = this.o;
                com.bumptech.glide.d.a(this.f6737m, this.f6738n, (D.L) this.f6741r, interfaceC2772a, (C0761q) obj, iZ, this.f6740q);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6739p | 1);
                InterfaceC2772a interfaceC2772a2 = this.o;
                H7.H.b(this.f6737m, (String) this.f6741r, this.f6738n, interfaceC2772a2, (C0761q) obj, iZ2, this.f6740q);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0557d(g0.o oVar, boolean z6, D.L l, InterfaceC2772a interfaceC2772a, int i7, int i10) {
        this.f6737m = oVar;
        this.f6738n = z6;
        this.f6741r = l;
        this.o = interfaceC2772a;
        this.f6739p = i7;
        this.f6740q = i10;
    }
}

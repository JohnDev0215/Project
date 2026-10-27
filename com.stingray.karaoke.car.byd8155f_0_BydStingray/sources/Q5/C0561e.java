package Q5;

import T.C0735d;
import T.C0761q;
import a.AbstractC0904a;
import com.stingray.karaoke.car.utils.FontSize;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0561e implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6761m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6762n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6763p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f6764q;

    public /* synthetic */ C0561e(g0.o oVar, Object obj, Object obj2, int i7, int i10, int i11) {
        this.l = i11;
        this.f6761m = oVar;
        this.f6763p = obj;
        this.f6764q = obj2;
        this.f6762n = i7;
        this.o = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6762n | 1);
                String str = (String) this.f6764q;
                com.bumptech.glide.d.k((g0.o) this.f6761m, (FontSize) this.f6763p, str, (C0761q) obj, iZ, this.o);
                break;
            case 1:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6762n | 1);
                C0607p1 c0607p1 = (C0607p1) this.f6763p;
                b0.b bVar = (b0.b) this.f6764q;
                AbstractC0904a.l((g0.o) this.f6761m, c0607p1, bVar, (C0761q) obj, iZ2, this.o);
                break;
            case 2:
                ((Integer) obj2).getClass();
                int iZ3 = C0735d.Z(this.o | 1);
                String str2 = (String) this.f6763p;
                int i7 = this.f6762n;
                l9.d.t((g0.o) this.f6761m, (String) this.f6764q, str2, i7, (C0761q) obj, iZ3);
                break;
            case 3:
                ((Integer) obj2).getClass();
                int iZ4 = C0735d.Z(this.f6762n | 1);
                String str3 = (String) this.f6764q;
                com.bumptech.glide.c.g((g0.o) this.f6761m, (Integer) this.f6763p, str3, (C0761q) obj, iZ4, this.o);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ5 = C0735d.Z(this.o | 1);
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f6761m;
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f6763p;
                H7.H.i((String) this.f6764q, this.f6762n, interfaceC2772a, interfaceC2782k, (C0761q) obj, iZ5);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0561e(g0.o oVar, String str, String str2, int i7, int i10) {
        this.l = 2;
        this.f6761m = oVar;
        this.f6764q = str;
        this.f6763p = str2;
        this.f6762n = i7;
        this.o = i10;
    }

    public /* synthetic */ C0561e(String str, int i7, InterfaceC2772a interfaceC2772a, InterfaceC2782k interfaceC2782k, int i10) {
        this.l = 4;
        this.f6764q = str;
        this.f6762n = i7;
        this.f6761m = interfaceC2772a;
        this.f6763p = interfaceC2782k;
        this.o = i10;
    }
}

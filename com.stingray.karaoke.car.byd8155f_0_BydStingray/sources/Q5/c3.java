package Q5;

import T.C0735d;
import T.C0761q;
import com.stingray.kca.model.KaraokeItem;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c3 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6733m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ KaraokeItem f6734n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6735p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6736q;

    public /* synthetic */ c3(g0.o oVar, KaraokeItem karaokeItem, int i7, InterfaceC2772a interfaceC2772a, int i10, int i11) {
        this.l = i11;
        this.f6733m = oVar;
        this.f6734n = karaokeItem;
        this.f6735p = i7;
        this.o = interfaceC2772a;
        this.f6736q = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6736q | 1);
                KaraokeItem karaokeItem = this.f6734n;
                int i7 = this.f6735p;
                com.bumptech.glide.c.c(i7, iZ, (C0761q) obj, karaokeItem, this.f6733m, this.o);
                break;
            case 1:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6735p | 1);
                InterfaceC2772a interfaceC2772a = this.o;
                int i10 = this.f6736q;
                com.bumptech.glide.c.a(iZ2, i10, (C0761q) obj, this.f6734n, this.f6733m, interfaceC2772a);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ3 = C0735d.Z(this.f6736q | 1);
                KaraokeItem karaokeItem2 = this.f6734n;
                int i11 = this.f6735p;
                com.bumptech.glide.c.h(i11, iZ3, (C0761q) obj, karaokeItem2, this.f6733m, this.o);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ c3(g0.o oVar, KaraokeItem karaokeItem, InterfaceC2772a interfaceC2772a, int i7, int i10) {
        this.l = 1;
        this.f6733m = oVar;
        this.f6734n = karaokeItem;
        this.o = interfaceC2772a;
        this.f6735p = i7;
        this.f6736q = i10;
    }
}

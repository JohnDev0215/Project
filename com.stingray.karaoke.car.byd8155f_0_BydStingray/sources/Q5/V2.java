package Q5;

import T.C0735d;
import T.C0761q;
import com.stingray.kca.model.KaraokeItem;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class V2 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6605m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ KaraokeItem f6606n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6607p;

    public /* synthetic */ V2(g0.o oVar, KaraokeItem karaokeItem, InterfaceC2772a interfaceC2772a, int i7, int i10) {
        this.l = i10;
        this.f6605m = oVar;
        this.f6606n = karaokeItem;
        this.o = interfaceC2772a;
        this.f6607p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.f6607p | 1);
                com.bumptech.glide.c.i(this.f6605m, this.f6606n, this.o, c0761q, iZ);
                break;
            case 1:
                int iZ2 = C0735d.Z(this.f6607p | 1);
                com.bumptech.glide.c.p(this.f6605m, this.f6606n, this.o, c0761q, iZ2);
                break;
            case 2:
                int iZ3 = C0735d.Z(this.f6607p | 1);
                com.bumptech.glide.c.e(this.f6605m, this.f6606n, this.o, c0761q, iZ3);
                break;
            default:
                int iZ4 = C0735d.Z(this.f6607p | 1);
                com.bumptech.glide.c.t(this.f6605m, this.f6606n, this.o, c0761q, iZ4);
                break;
        }
        return m7.q.f19267a;
    }
}

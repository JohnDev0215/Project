package M5;

import A7.m;
import C.k;
import D.C0138u;
import T.C0734c0;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.S;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.kca.model.KaraokeItem;
import g0.o;
import java.io.IOException;
import k6.AbstractC1692D;
import m7.q;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class e implements InterfaceC2786o {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f4202m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f4203n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f4204p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f4205q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f4206r;

    public e(k kVar, androidx.compose.material3.c cVar, boolean z6, String str, L0.g gVar, InterfaceC2772a interfaceC2772a) {
        this.f4204p = kVar;
        this.f4205q = cVar;
        this.f4202m = z6;
        this.f4203n = str;
        this.f4206r = gVar;
        this.o = interfaceC2772a;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws XmlPullParserException, IOException {
        int i7;
        String str;
        switch (this.l) {
            case 0:
                o oVar = (o) obj;
                C0761q c0761q = (C0761q) obj2;
                ((Number) obj3).intValue();
                m.f(oVar, "$this$composed");
                c0761q.Q(1855608176);
                c0761q.Q(-909107623);
                Object objG = c0761q.G();
                S s6 = C0753m.f8270a;
                if (objG == s6) {
                    objG = C0735d.N(0L);
                    c0761q.a0(objG);
                }
                C0734c0 c0734c0 = (C0734c0) objG;
                c0761q.p(false);
                c0761q.Q(-909100181);
                InterfaceC2772a interfaceC2772a = this.o;
                boolean zF = c0761q.f(interfaceC2772a);
                Object objG2 = c0761q.G();
                if (zF || objG2 == s6) {
                    objG2 = new d(c0734c0, 0, interfaceC2772a);
                    c0761q.a0(objG2);
                }
                c0761q.p(false);
                o oVarC = androidx.compose.foundation.a.c(oVar, (k) this.f4204p, (androidx.compose.material3.c) this.f4205q, this.f4202m, this.f4203n, (L0.g) this.f4206r, (InterfaceC2772a) objG2);
                c0761q.p(false);
                return oVarC;
            default:
                C0138u c0138u = (C0138u) obj;
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                m.f(c0138u, "$this$BoxWithConstraints");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q2.f(c0138u) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    boolean zB = AbstractC1692D.b((LayoutMode) c0761q2.k(AbstractC1692D.f18238a));
                    float fB = c0138u.b();
                    if (zB) {
                        fB *= 9;
                        i7 = 16;
                    } else {
                        i7 = 6;
                    }
                    o oVarE = androidx.compose.foundation.layout.c.e((o) this.f4204p, fB / i7);
                    if (!this.f4202m || (str = this.f4203n) == null) {
                        str = (String) this.f4205q;
                    }
                    String str2 = str;
                    KaraokeItem karaokeItem = (KaraokeItem) this.f4206r;
                    Boolean bool = karaokeItem.f13117n;
                    Boolean bool2 = Boolean.TRUE;
                    com.bumptech.glide.c.k(oVarE, str2, false, m.a(bool, bool2), m.a(karaokeItem.f13116m, bool2), this.o, c0761q2, 0, 4);
                }
                return q.f19267a;
        }
    }

    public e(o oVar, boolean z6, String str, String str2, KaraokeItem karaokeItem, InterfaceC2772a interfaceC2772a) {
        this.f4204p = oVar;
        this.f4202m = z6;
        this.f4203n = str;
        this.f4205q = str2;
        this.f4206r = karaokeItem;
        this.o = interfaceC2772a;
    }
}

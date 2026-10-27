package Q5;

import T.C0753m;
import T.C0761q;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemChildType;
import com.stingray.kca.model.KaraokeItemType;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2789r;

/* JADX INFO: loaded from: classes.dex */
public final class i3 implements InterfaceC2789r {
    public final /* synthetic */ KaraokeItem l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6885m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6886n;
    public final /* synthetic */ KaraokeItemChildType o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f6887p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6888q;

    public i3(KaraokeItem karaokeItem, boolean z6, InterfaceC2782k interfaceC2782k, KaraokeItemChildType karaokeItemChildType, boolean z10, int i7) {
        this.l = karaokeItem;
        this.f6885m = z6;
        this.f6886n = interfaceC2782k;
        this.o = karaokeItemChildType;
        this.f6887p = z10;
        this.f6888q = i7;
    }

    @Override // z7.InterfaceC2789r
    public final Object k(g0.o oVar, Integer num, Boolean bool, InterfaceC2772a interfaceC2772a, Object obj, Integer num2) throws XmlPullParserException, IOException {
        int i7;
        int iIntValue = num.intValue();
        boolean zBooleanValue = bool.booleanValue();
        C0761q c0761q = (C0761q) obj;
        int iIntValue2 = num2.intValue();
        A7.m.f(oVar, "modifier");
        A7.m.f(interfaceC2772a, "onClick");
        if ((iIntValue2 & 6) == 0) {
            i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue2;
        } else {
            i7 = iIntValue2;
        }
        if ((iIntValue2 & 48) == 0) {
            i7 |= c0761q.d(iIntValue) ? 32 : 16;
        }
        if ((iIntValue2 & 384) == 0) {
            i7 |= c0761q.g(zBooleanValue) ? 256 : 128;
        }
        if ((iIntValue2 & 3072) == 0) {
            i7 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            KaraokeItem karaokeItem = this.l;
            KaraokeItemType karaokeItemType = karaokeItem.f13109e;
            int i10 = karaokeItemType == null ? -1 : h3.f6858a[karaokeItemType.ordinal()];
            T.S s6 = C0753m.f8270a;
            boolean z6 = this.f6885m;
            InterfaceC2782k interfaceC2782k = this.f6886n;
            if (i10 == 1) {
                c0761q.Q(976236248);
                if (!zBooleanValue || z6) {
                    c0761q.Q(976601831);
                    g0.o oVarD = androidx.compose.foundation.layout.c.d(oVar, 1.0f);
                    c0761q.Q(-1908154241);
                    boolean zF = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem) | ((i7 & 7168) == 2048);
                    Object objG = c0761q.G();
                    if (zF || objG == s6) {
                        objG = new g3(interfaceC2782k, karaokeItem, interfaceC2772a, 1);
                        c0761q.a0(objG);
                    }
                    c0761q.p(false);
                    com.bumptech.glide.c.t(oVarD, karaokeItem, (InterfaceC2772a) objG, c0761q, 0);
                    c0761q.p(false);
                } else {
                    c0761q.Q(976292482);
                    g0.o oVarD2 = androidx.compose.foundation.layout.c.d(oVar, 1.0f);
                    c0761q.Q(-1908164065);
                    boolean zF2 = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem) | ((i7 & 7168) == 2048);
                    Object objG2 = c0761q.G();
                    if (zF2 || objG2 == s6) {
                        objG2 = new g3(interfaceC2782k, karaokeItem, interfaceC2772a, 0);
                        c0761q.a0(objG2);
                    }
                    c0761q.p(false);
                    com.bumptech.glide.c.p(oVarD2, karaokeItem, (InterfaceC2772a) objG2, c0761q, 0);
                    c0761q.p(false);
                }
                c0761q.p(false);
            } else if (i10 != 2) {
                c0761q.Q(978990226);
                c0761q.p(false);
            } else {
                c0761q.Q(977014503);
                if (this.o == KaraokeItemChildType.ARTIST) {
                    c0761q.Q(977045317);
                    if (!zBooleanValue || z6) {
                        c0761q.Q(977453060);
                        g0.o oVarD3 = androidx.compose.foundation.layout.c.d(oVar, 1.0f);
                        c0761q.Q(-1908124213);
                        boolean zF3 = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem) | ((i7 & 7168) == 2048);
                        Object objG3 = c0761q.G();
                        if (zF3 || objG3 == s6) {
                            objG3 = new g3(interfaceC2782k, karaokeItem, interfaceC2772a, 3);
                            c0761q.a0(objG3);
                        }
                        c0761q.p(false);
                        com.bumptech.glide.c.c(iIntValue, (i7 << 3) & 896, c0761q, karaokeItem, oVarD3, (InterfaceC2772a) objG3);
                        c0761q.p(false);
                    } else {
                        c0761q.Q(977102202);
                        g0.o oVarD4 = androidx.compose.foundation.layout.c.d(oVar, 1.0f);
                        c0761q.Q(-1908137205);
                        boolean zF4 = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem) | ((i7 & 7168) == 2048);
                        Object objG4 = c0761q.G();
                        if (zF4 || objG4 == s6) {
                            objG4 = new g3(interfaceC2782k, karaokeItem, interfaceC2772a, 2);
                            c0761q.a0(objG4);
                        }
                        c0761q.p(false);
                        com.bumptech.glide.c.a(0, 0, c0761q, karaokeItem, oVarD4, (InterfaceC2772a) objG4);
                        c0761q.p(false);
                    }
                    c0761q.p(false);
                } else if (this.f6887p) {
                    c0761q.Q(977916696);
                    int iN = (((m6.c.f19252a.N(c0761q) + 1) * this.f6888q) + iIntValue) % 10;
                    g0.o oVarD5 = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(oVar, 1.0f), 1.0f);
                    c0761q.Q(-1908100449);
                    boolean zF5 = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem) | ((i7 & 7168) == 2048);
                    Object objG5 = c0761q.G();
                    if (zF5 || objG5 == s6) {
                        objG5 = new g3(interfaceC2782k, karaokeItem, interfaceC2772a, 4);
                        c0761q.a0(objG5);
                    }
                    c0761q.p(false);
                    com.bumptech.glide.c.h(iN, 0, c0761q, karaokeItem, oVarD5, (InterfaceC2772a) objG5);
                    c0761q.p(false);
                } else {
                    c0761q.Q(978576531);
                    g0.o oVarD6 = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(oVar, 1.0f), 1.0f);
                    c0761q.Q(-1908087937);
                    boolean zF6 = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem) | ((i7 & 7168) == 2048);
                    Object objG6 = c0761q.G();
                    if (zF6 || objG6 == s6) {
                        objG6 = new g3(interfaceC2782k, karaokeItem, interfaceC2772a, 5);
                        c0761q.a0(objG6);
                    }
                    c0761q.p(false);
                    com.bumptech.glide.c.i(oVarD6, karaokeItem, (InterfaceC2772a) objG6, c0761q, 0);
                    c0761q.p(false);
                }
                c0761q.p(false);
            }
        }
        return m7.q.f19267a;
    }
}

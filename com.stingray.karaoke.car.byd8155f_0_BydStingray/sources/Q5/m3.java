package Q5;

import T.C0753m;
import T.C0761q;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemType;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2789r;

/* JADX INFO: loaded from: classes.dex */
public final class m3 implements InterfaceC2789r {
    public final /* synthetic */ KaraokeItem l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6965m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6966n;

    public m3(KaraokeItem karaokeItem, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        this.l = karaokeItem;
        this.f6965m = interfaceC2782k;
        this.f6966n = interfaceC2782k2;
    }

    @Override // z7.InterfaceC2789r
    public final Object k(g0.o oVar, Integer num, Boolean bool, InterfaceC2772a interfaceC2772a, Object obj, Integer num2) throws XmlPullParserException, IOException {
        int i7;
        C0761q c0761q = (C0761q) obj;
        int iIntValue = num2.intValue();
        A7.m.f(oVar, "modifier");
        A7.m.f(interfaceC2772a, "onClick");
        if ((iIntValue & 6) == 0) {
            i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue;
        } else {
            i7 = iIntValue;
        }
        if ((iIntValue & 3072) == 0) {
            i7 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 9219) == 9218 && c0761q.x()) {
            c0761q.K();
        } else {
            KaraokeItem karaokeItem = this.l;
            if (karaokeItem.f13109e == KaraokeItemType.PLAY) {
                g0.o oVarD = androidx.compose.foundation.layout.c.d(oVar, 1.0f);
                c0761q.Q(-2060973280);
                InterfaceC2782k interfaceC2782k = this.f6965m;
                boolean zF = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem);
                Object objG = c0761q.G();
                T.S s6 = C0753m.f8270a;
                if (zF || objG == s6) {
                    objG = new C0627u2(interfaceC2782k, karaokeItem, 1);
                    c0761q.a0(objG);
                }
                InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) objG;
                c0761q.p(false);
                c0761q.Q(-2060971048);
                InterfaceC2782k interfaceC2782k2 = this.f6966n;
                boolean zF2 = ((i7 & 7168) == 2048) | c0761q.f(interfaceC2782k2) | c0761q.h(karaokeItem);
                Object objG2 = c0761q.G();
                if (zF2 || objG2 == s6) {
                    objG2 = new g3(interfaceC2782k2, karaokeItem, interfaceC2772a, 6);
                    c0761q.a0(objG2);
                }
                c0761q.p(false);
                com.bumptech.glide.c.q(oVarD, karaokeItem, interfaceC2772a2, (InterfaceC2772a) objG2, c0761q, 0);
            }
        }
        return m7.q.f19267a;
    }
}

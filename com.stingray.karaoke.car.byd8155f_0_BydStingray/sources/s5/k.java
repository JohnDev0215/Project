package S5;

import A3.v;
import D.L;
import T.C0753m;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class k implements InterfaceC2787p {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8015m;

    public /* synthetic */ k(String str, int i7) {
        this.l = i7;
        this.f8015m = str;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        int i10;
        String strX;
        int i11;
        switch (this.l) {
            case 0:
                g0.o oVar = (g0.o) obj;
                L l = (L) obj2;
                C0761q c0761q = (C0761q) obj3;
                int iIntValue = ((Number) obj4).intValue();
                A7.m.f(oVar, "modifier");
                A7.m.f(l, "contentPadding");
                if ((iIntValue & 6) == 0) {
                    i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue;
                } else {
                    i7 = iIntValue;
                }
                if ((iIntValue & 48) == 0) {
                    i7 |= c0761q.f(l) ? 32 : 16;
                }
                if ((i7 & 147) == 146 && c0761q.x()) {
                    c0761q.K();
                } else {
                    String strX2 = AbstractC2673a.X(R.string.Error_Favorite_Limit_Title, c0761q);
                    String strX3 = AbstractC2673a.X(R.string.Error_Favorite_Limit_Description, c0761q);
                    String strX4 = AbstractC2673a.X(R.string.Global_Ok, c0761q);
                    c0761q.Q(64584498);
                    Object objG = c0761q.G();
                    if (objG == C0753m.f8270a) {
                        objG = new v(3);
                        c0761q.a0(objG);
                    }
                    c0761q.p(false);
                    com.bumptech.glide.d.e(oVar, this.f8015m, strX2, strX3, strX4, null, (InterfaceC2772a) objG, null, l, c0761q, (i7 & 14) | 1572864 | ((i7 << 21) & 234881024), 160);
                }
                break;
            case 1:
                g0.o oVar2 = (g0.o) obj;
                L l10 = (L) obj2;
                C0761q c0761q2 = (C0761q) obj3;
                int iIntValue2 = ((Number) obj4).intValue();
                A7.m.f(oVar2, "modifier");
                A7.m.f(l10, "contentPadding");
                if ((iIntValue2 & 6) == 0) {
                    i10 = (c0761q2.f(oVar2) ? 4 : 2) | iIntValue2;
                } else {
                    i10 = iIntValue2;
                }
                if ((iIntValue2 & 48) == 0) {
                    i10 |= c0761q2.f(l10) ? 32 : 16;
                }
                if ((i10 & 147) == 146 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    String strX5 = AbstractC2673a.X(R.string.Microphone_NotConnected, c0761q2);
                    if (Q8.o.z0("byd8155f", "byd", false)) {
                        c0761q2.Q(-1684753967);
                        strX = AbstractC2673a.X(R.string.Microphone_ReconnectPrompt_SpecialMicrophone, c0761q2);
                    } else {
                        c0761q2.Q(-1684751395);
                        strX = AbstractC2673a.X(R.string.Microphone_ReconnectPrompt, c0761q2);
                    }
                    c0761q2.p(false);
                    String str = strX;
                    String strX6 = AbstractC2673a.X(R.string.Global_Ok, c0761q2);
                    c0761q2.Q(-1684746039);
                    Object objG2 = c0761q2.G();
                    if (objG2 == C0753m.f8270a) {
                        objG2 = new v(3);
                        c0761q2.a0(objG2);
                    }
                    c0761q2.p(false);
                    com.bumptech.glide.d.e(oVar2, this.f8015m, strX5, str, strX6, null, (InterfaceC2772a) objG2, null, l10, c0761q2, (i10 & 14) | 1572864 | ((i10 << 21) & 234881024), 160);
                }
                break;
            default:
                g0.o oVar3 = (g0.o) obj;
                L l11 = (L) obj2;
                C0761q c0761q3 = (C0761q) obj3;
                int iIntValue3 = ((Number) obj4).intValue();
                A7.m.f(oVar3, "modifier");
                A7.m.f(l11, "contentPadding");
                if ((iIntValue3 & 6) == 0) {
                    i11 = (c0761q3.f(oVar3) ? 4 : 2) | iIntValue3;
                } else {
                    i11 = iIntValue3;
                }
                if ((iIntValue3 & 48) == 0) {
                    i11 |= c0761q3.f(l11) ? 32 : 16;
                }
                if ((i11 & 147) == 146 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    String strX7 = AbstractC2673a.X(R.string.Error_Unavailable_Asset, c0761q3);
                    String strX8 = AbstractC2673a.X(R.string.Global_Ok, c0761q3);
                    c0761q3.Q(958779161);
                    Object objG3 = c0761q3.G();
                    if (objG3 == C0753m.f8270a) {
                        objG3 = new v(3);
                        c0761q3.a0(objG3);
                    }
                    c0761q3.p(false);
                    com.bumptech.glide.d.e(oVar3, this.f8015m, null, strX7, strX8, null, (InterfaceC2772a) objG3, null, l11, c0761q3, (i11 & 14) | 1572864 | ((i11 << 21) & 234881024), 164);
                }
                break;
        }
        return m7.q.f19267a;
    }
}

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
public final class m implements InterfaceC2787p {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8017m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8018n;

    public /* synthetic */ m(int i7, String str, InterfaceC2772a interfaceC2772a) {
        this.l = i7;
        this.f8017m = str;
        this.f8018n = interfaceC2772a;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        int i10;
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
                    String strX = AbstractC2673a.X(R.string.ParentalControls_Title, c0761q);
                    String strX2 = AbstractC2673a.X(R.string.ParentalControls_Dialog_Description, c0761q);
                    String strX3 = AbstractC2673a.X(R.string.Global_Ok, c0761q);
                    String strX4 = AbstractC2673a.X(R.string.ParentalControls_Dialog_Button, c0761q);
                    c0761q.Q(-307588745);
                    Object objG = c0761q.G();
                    if (objG == C0753m.f8270a) {
                        objG = new v(3);
                        c0761q.a0(objG);
                    }
                    c0761q.p(false);
                    com.bumptech.glide.d.e(oVar, this.f8017m, strX, strX2, strX3, strX4, (InterfaceC2772a) objG, this.f8018n, l, c0761q, (i7 & 14) | 1572864 | ((i7 << 21) & 234881024), 0);
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
                    l9.l.f(oVar2, this.f8017m, this.f8018n, l10, c0761q2, (i10 & 14) | ((i10 << 6) & 7168));
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
                    String strX5 = AbstractC2673a.X(R.string.SongDetails_OverrideCurrentSong_Title, c0761q3);
                    String strX6 = AbstractC2673a.X(R.string.SongDetails_OverrideCurrentSong_Text, c0761q3);
                    String strX7 = AbstractC2673a.X(R.string.Global_Ok, c0761q3);
                    String strX8 = AbstractC2673a.X(R.string.Global_Cancel, c0761q3);
                    c0761q3.Q(1419539137);
                    InterfaceC2772a interfaceC2772a = this.f8018n;
                    boolean zF = c0761q3.f(interfaceC2772a);
                    String str = this.f8017m;
                    boolean zF2 = zF | c0761q3.f(str);
                    Object objG2 = c0761q3.G();
                    if (zF2 || objG2 == C0753m.f8270a) {
                        objG2 = new a(4, str, interfaceC2772a);
                        c0761q3.a0(objG2);
                    }
                    c0761q3.p(false);
                    com.bumptech.glide.d.e(oVar3, this.f8017m, strX5, strX6, strX7, strX8, (InterfaceC2772a) objG2, null, l11, c0761q3, (i11 & 14) | ((i11 << 21) & 234881024), 128);
                }
                break;
        }
        return m7.q.f19267a;
    }
}

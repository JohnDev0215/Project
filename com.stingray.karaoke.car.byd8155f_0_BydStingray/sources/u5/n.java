package U5;

import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0139v;
import D.C0141x;
import D.C0142y;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import H7.H;
import Q5.C0623t2;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import T.S;
import T.X;
import android.content.res.Configuration;
import android.hardware.bydauto.TsNormalNum;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import g1.AbstractC1274a;
import g1.C1278e;
import g1.C1283j;
import h2.AbstractC1347d;
import k6.AbstractC1692D;
import n6.C1966G;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class n extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f9157A;
    public final /* synthetic */ InterfaceC2772a B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f9158C;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9159m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f9160n;
    public final /* synthetic */ C1283j o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f9161p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ String f9162q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ String f9163r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ String f9164s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ C1966G f9165t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ boolean f9166u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f9167v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f9168w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ boolean f9169x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ float f9170y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ String f9171z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(X x8, C1283j c1283j, InterfaceC2772a interfaceC2772a, String str, String str2, String str3, C1966G c1966g, boolean z6, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, boolean z10, float f10, String str4, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3, InterfaceC2772a interfaceC2772a4, int i7) {
        super(2);
        this.f9159m = i7;
        this.f9160n = x8;
        this.o = c1283j;
        this.f9161p = interfaceC2772a;
        this.f9162q = str;
        this.f9163r = str2;
        this.f9164s = str3;
        this.f9165t = c1966g;
        this.f9166u = z6;
        this.f9167v = interfaceC2782k;
        this.f9168w = interfaceC2782k2;
        this.f9169x = z10;
        this.f9170y = f10;
        this.f9171z = str4;
        this.f9157A = interfaceC2772a2;
        this.B = interfaceC2772a3;
        this.f9158C = interfaceC2772a4;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        boolean z6;
        switch (this.f9159m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                int iIntValue = ((Number) obj2).intValue() & 3;
                m7.q qVar = m7.q.f19267a;
                if (iIntValue == 2 && c0761q.x()) {
                    c0761q.K();
                    return qVar;
                }
                this.f9160n.setValue(qVar);
                C1283j c1283j = this.o;
                int i7 = c1283j.f14726b;
                c1283j.e();
                c0761q.Q(1648448847);
                C1283j c1283j2 = (C1283j) c1283j.d().f14705a;
                C1278e c1278eC = c1283j2.c();
                C1278e c1278eC2 = c1283j2.c();
                C1278e c1278eC3 = c1283j2.c();
                g0.l lVar = g0.l.f14684a;
                c0761q.Q(-1193750908);
                boolean zF = c0761q.f(c1278eC2);
                Object objG = c0761q.G();
                S s6 = C0753m.f8270a;
                if (zF || objG == s6) {
                    objG = new C0623t2(c1278eC2, 2);
                    c0761q.a0(objG);
                }
                c0761q.p(false);
                g0.o oVarB = C1283j.b(lVar, c1278eC, (InterfaceC2782k) objG);
                m6.b bVar = m6.c.f19252a;
                AbstractC1274a.r(androidx.compose.foundation.layout.a.o(oVarB, bVar.E0(c0761q), bVar.F0(c0761q), 0.0f, 0.0f, 12), this.f9162q, this.f9163r, this.f9164s, false, c0761q, 24576);
                c0761q.Q(-1193732904);
                Object objG2 = c0761q.G();
                if (objG2 == s6) {
                    objG2 = C0735d.O(new a1.j(H.c(0, 0)), S.f8224q);
                    c0761q.a0(objG2);
                }
                X x8 = (X) objG2;
                c0761q.p(false);
                a1.b bVar2 = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
                c0761q.Q(-1193724758);
                g0.o oVarT = androidx.compose.foundation.layout.c.t(androidx.compose.foundation.layout.c.b(lVar, 1.0f));
                float fP0 = bVar2.p0((int) (((a1.j) x8.getValue()).f11037a >> 32));
                g0.o oVarH = androidx.compose.foundation.layout.a.h(oVarT, -(Float.compare(((float) ((Configuration) c0761q.k(AndroidCompositionLocals_androidKt.f11687a)).screenWidthDp) - bVar.j0(c0761q), fP0 * 0.85f) < 0 ? r5.screenWidthDp / 5 : 0), r5.screenHeightDp / 10);
                c0761q.p(false);
                c0761q.Q(-1193705633);
                boolean zF2 = c0761q.f(c1278eC);
                Object objG3 = c0761q.G();
                if (zF2 || objG3 == s6) {
                    objG3 = new C0623t2(c1278eC, 3);
                    c0761q.a0(objG3);
                }
                c0761q.p(false);
                g0.o oVarB2 = C1283j.b(oVarH, c1278eC3, (InterfaceC2782k) objG3);
                c0761q.Q(-1193700787);
                Object objG4 = c0761q.G();
                if (objG4 == s6) {
                    objG4 = new N7.g(x8, 4);
                    c0761q.a0(objG4);
                }
                c0761q.p(false);
                w0.c.z(androidx.compose.ui.layout.a.d(oVarB2, (InterfaceC2782k) objG4), 0L, c0761q, 384);
                c0761q.Q(-1193691263);
                Object objG5 = c0761q.G();
                if (objG5 == s6) {
                    objG5 = o.f9172m;
                    c0761q.a0(objG5);
                }
                c0761q.p(false);
                g0.o oVarQ = androidx.compose.foundation.layout.c.q(androidx.compose.foundation.layout.c.d(C1283j.b(lVar, c1278eC2, (InterfaceC2782k) objG5), 0.45f), 0.0f, bVar.j0(c0761q), 1);
                C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.f1079f, g0.b.f14673y, c0761q, 54);
                int i10 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                g0.o oVarD = g0.a.d(c0761q, oVarQ);
                InterfaceC0161k.f1681b.getClass();
                C0159i c0159i = C0160j.f1678b;
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(c0159i);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, c0141xA, C0160j.f1679e);
                C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                C0158h c0158h = C0160j.f1680f;
                if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i10))) {
                    AbstractC1347d.l(i10, c0761q, i10, c0158h);
                }
                C0735d.V(c0761q, oVarD, C0160j.c);
                C0142y c0142y = C0142y.f1119a;
                AbstractC0120b.b(c0761q, C0142y.a(c0142y, lVar, 1.0f));
                String strX = AbstractC2673a.X(R.string.Scoring_NoteStreak, c0761q);
                C1966G c1966g = this.f9165t;
                AbstractC1274a.a(lVar, strX, c1966g.c, FontSize.f13040H6, 0, null, this.f9166u, this.f9167v, this.f9168w, ScoringSoundFxType.RS_STREAK, c0761q, 27654, 6, 96);
                int i11 = (int) (c1966g.f20001b * 100);
                String strX2 = AbstractC2673a.X(R.string.Scoring_NotesHit, c0761q);
                FontSize fontSize = FontSize.f13039H5;
                ScoringSoundFxType scoringSoundFxType = ScoringSoundFxType.RS_HIT;
                c0761q.Q(1095030839);
                Object objG6 = c0761q.G();
                if (objG6 == s6) {
                    objG6 = o.f9173n;
                    c0761q.a0(objG6);
                }
                c0761q.p(false);
                AbstractC1274a.a(lVar, strX2, i11, fontSize, 1000, (InterfaceC2782k) objG6, this.f9166u, this.f9167v, this.f9168w, scoringSoundFxType, c0761q, 1797126, 6, 0);
                AbstractC1274a.l(lVar, AbstractC2673a.X(R.string.Scoring_Score, c0761q), c1966g, this.f9169x, fontSize, this.f9166u, 2000, TsNormalNum.THOUSAND_FOUR_HALF, this.f9167v, this.f9168w, c0761q, 14180870);
                c0761q.Q(-2001810578);
                float f10 = 300;
                float f11 = 400;
                float f12 = 500;
                LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
                if (layoutMode == LayoutMode.NARROW_COMPACT) {
                    z6 = false;
                } else {
                    if (layoutMode == LayoutMode.WIDE_COMPACT) {
                        z6 = false;
                    } else {
                        if (layoutMode == LayoutMode.WIDE_NORMAL || layoutMode == LayoutMode.WIDE_ULTRA || layoutMode == LayoutMode.NARROW_ULTRA || layoutMode == LayoutMode.SQUARE_ULTRA) {
                            z6 = false;
                        } else {
                            if (layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.NARROW_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                                throw new A9.k();
                            }
                            z6 = false;
                            if (!M5.b.r(c0761q, -609592150, bVar, c0761q, false)) {
                                if (!A7.k.u(c0761q, -609591093, bVar, c0761q, false)) {
                                }
                            }
                        }
                        f10 = f12;
                    }
                    f10 = f11;
                }
                c0761q.p(z6);
                l9.l.c(androidx.compose.foundation.layout.c.q(lVar, 0.0f, f10, 1), this.f9170y, 1500, this.f9166u, this.f9167v, c0761q, 3456);
                AbstractC1274a.b(androidx.compose.foundation.layout.c.d(lVar, 1.0f), this.f9171z, fontSize, this.f9166u, this.f9157A, this.f9170y, this.f9167v, c0761q, 3462);
                AbstractC0120b.b(c0761q, C0142y.a(c0142y, lVar, 1.0f));
                AbstractC1274a.e(lVar, this.B, this.f9158C, c0761q, 6);
                AbstractC0120b.b(c0761q, C0142y.a(c0142y, lVar, 1.0f));
                c0761q.p(true);
                c0761q.p(z6);
                if (c1283j.f14726b == i7) {
                    return qVar;
                }
                C0735d.i(this.f9161p, c0761q);
                return qVar;
            default:
                C0761q c0761q2 = (C0761q) obj;
                int iIntValue2 = ((Number) obj2).intValue() & 3;
                m7.q qVar2 = m7.q.f19267a;
                if (iIntValue2 == 2 && c0761q2.x()) {
                    c0761q2.K();
                    return qVar2;
                }
                this.f9160n.setValue(qVar2);
                C1283j c1283j3 = this.o;
                int i12 = c1283j3.f14726b;
                c1283j3.e();
                c0761q2.Q(1629327301);
                C1283j c1283j4 = (C1283j) c1283j3.d().f14705a;
                C1278e c1278eC4 = c1283j4.c();
                C1278e c1278eC5 = c1283j4.c();
                C1278e c1278eC6 = c1283j4.c();
                C1278e c1278eC7 = c1283j4.c();
                C1278e c1278eC8 = c1283j4.c();
                C1278e c1278eC9 = c1283j4.c();
                C1278e c1278eC10 = c1283j4.c();
                C1278e c1278eC11 = c1283j4.c();
                C1278e c1278eC12 = c1283j4.c();
                g0.l lVar2 = g0.l.f14684a;
                c0761q2.Q(1022392130);
                Object objG7 = c0761q2.G();
                S s10 = C0753m.f8270a;
                if (objG7 == s10) {
                    objG7 = o.o;
                    c0761q2.a0(objG7);
                }
                c0761q2.p(false);
                AbstractC1274a.r(C1283j.b(lVar2, c1278eC4, (InterfaceC2782k) objG7), this.f9162q, this.f9163r, this.f9164s, true, c0761q2, 24576);
                c0761q2.Q(1022401705);
                boolean zF3 = c0761q2.f(c1278eC4);
                Object objG8 = c0761q2.G();
                if (zF3 || objG8 == s10) {
                    objG8 = new C0623t2(c1278eC4, 6);
                    c0761q2.a0(objG8);
                }
                c0761q2.p(false);
                g0.o oVarB3 = C1283j.b(lVar2, c1278eC5, (InterfaceC2782k) objG8);
                String strX3 = AbstractC2673a.X(R.string.Scoring_NoteStreak, c0761q2);
                C1966G c1966g2 = this.f9165t;
                int i13 = c1966g2.c;
                FontSize fontSize2 = FontSize.f13040H6;
                AbstractC1274a.a(oVarB3, strX3, i13, fontSize2, 0, null, this.f9166u, this.f9167v, this.f9168w, ScoringSoundFxType.RS_STREAK, c0761q2, 27648, 6, 96);
                c0761q2.Q(1022422110);
                boolean zF4 = c0761q2.f(c1278eC5);
                Object objG9 = c0761q2.G();
                if (zF4 || objG9 == s10) {
                    objG9 = new C0623t2(c1278eC5, 7);
                    c0761q2.a0(objG9);
                }
                c0761q2.p(false);
                g0.o oVarB4 = C1283j.b(lVar2, c1278eC6, (InterfaceC2782k) objG9);
                String strX4 = AbstractC2673a.X(R.string.Scoring_NotesHit, c0761q2);
                int i14 = (int) (c1966g2.f20001b * 100);
                ScoringSoundFxType scoringSoundFxType2 = ScoringSoundFxType.RS_HIT;
                c0761q2.Q(1022437590);
                Object objG10 = c0761q2.G();
                if (objG10 == s10) {
                    objG10 = o.f9174p;
                    c0761q2.a0(objG10);
                }
                c0761q2.p(false);
                AbstractC1274a.a(oVarB4, strX4, i14, fontSize2, 1000, (InterfaceC2782k) objG10, this.f9166u, this.f9167v, this.f9168w, scoringSoundFxType2, c0761q2, 1797120, 6, 0);
                c0761q2.Q(1022445051);
                boolean zF5 = c0761q2.f(c1278eC6);
                Object objG11 = c0761q2.G();
                if (zF5 || objG11 == s10) {
                    objG11 = new C0623t2(c1278eC6, 8);
                    c0761q2.a0(objG11);
                }
                c0761q2.p(false);
                AbstractC1274a.l(C1283j.b(lVar2, c1278eC7, (InterfaceC2782k) objG11), AbstractC2673a.X(R.string.Scoring_Score, c0761q2), c1966g2, this.f9169x, fontSize2, this.f9166u, 2000, TsNormalNum.THOUSAND_FOUR_HALF, this.f9167v, this.f9168w, c0761q2, 14180864);
                c0761q2.Q(1022467881);
                boolean zF6 = c0761q2.f(c1278eC7);
                Object objG12 = c0761q2.G();
                if (zF6 || objG12 == s10) {
                    objG12 = new C0623t2(c1278eC7, 9);
                    c0761q2.a0(objG12);
                }
                c0761q2.p(false);
                l9.l.c(C1283j.b(lVar2, c1278eC8, (InterfaceC2782k) objG12), this.f9170y, 1000, this.f9166u, this.f9167v, c0761q2, 3456);
                c0761q2.Q(1022485896);
                boolean zF7 = c0761q2.f(c1278eC8);
                Object objG13 = c0761q2.G();
                if (zF7 || objG13 == s10) {
                    objG13 = new C0623t2(c1278eC8, 10);
                    c0761q2.a0(objG13);
                }
                c0761q2.p(false);
                AbstractC1274a.b(C1283j.b(lVar2, c1278eC9, (InterfaceC2782k) objG13), this.f9171z, FontSize.f13041H7, this.f9166u, this.f9157A, this.f9170y, this.f9167v, c0761q2, 3456);
                c0761q2.Q(1022504990);
                g0.o oVarE = androidx.compose.foundation.layout.c.e(lVar2, ((Configuration) c0761q2.k(AndroidCompositionLocals_androidKt.f11687a)).screenHeightDp * 0.4f);
                c0761q2.p(false);
                c0761q2.Q(1022512968);
                boolean zF8 = c0761q2.f(c1278eC8);
                Object objG14 = c0761q2.G();
                if (zF8 || objG14 == s10) {
                    objG14 = new C0623t2(c1278eC8, 11);
                    c0761q2.a0(objG14);
                }
                c0761q2.p(false);
                w0.c.z(C1283j.b(oVarE, c1278eC10, (InterfaceC2782k) objG14), 0L, c0761q2, 384);
                float fZ = m6.c.f19252a.z(c0761q2);
                c0761q2.Q(1022526669);
                boolean zF9 = c0761q2.f(c1278eC10);
                Object objG15 = c0761q2.G();
                if (zF9 || objG15 == s10) {
                    objG15 = new C0623t2(c1278eC10, 4);
                    c0761q2.a0(objG15);
                }
                c0761q2.p(false);
                AbstractC1274a.e(androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.a.i(C1283j.b(lVar2, c1278eC11, (InterfaceC2782k) objG15), 0.0f, fZ, 1), 0.5f), this.B, this.f9158C, c0761q2, 0);
                c0761q2.Q(1022539401);
                boolean zF10 = c0761q2.f(c1278eC11);
                Object objG16 = c0761q2.G();
                if (zF10 || objG16 == s10) {
                    objG16 = new C0623t2(c1278eC11, 5);
                    c0761q2.a0(objG16);
                }
                c0761q2.p(false);
                AbstractC0120b.b(c0761q2, androidx.compose.foundation.layout.c.e(C1283j.b(lVar2, c1278eC12, (InterfaceC2782k) objG16), fZ));
                c0761q2.p(false);
                if (c1283j3.f14726b == i12) {
                    return qVar2;
                }
                C0735d.i(this.f9161p, c0761q2);
                return qVar2;
        }
    }
}

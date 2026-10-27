package Q5;

import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0141x;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.C0773y;
import T.InterfaceC0744h0;
import a.AbstractC0904a;
import a4.AbstractC0927h;
import android.content.Context;
import android.graphics.Matrix;
import android.provider.Settings;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import h2.AbstractC1347d;
import java.io.IOException;
import k6.AbstractC1692D;
import n0.AbstractC1861K;
import n0.C1883l;
import n0.C1884m;
import n0.C1891t;
import org.xmlpull.v1.XmlPullParserException;
import r0.AbstractC2268a;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.n2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0600n2 implements InterfaceC2786o {
    public final /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f6982m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6983n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f6984p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ P5.p f6985q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f6986r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f6987s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ String f6988t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ String f6989u;

    public C0600n2(boolean z6, String str, boolean z10, boolean z11, boolean z12, P5.p pVar, PlayerStatus playerStatus, int i7, String str2, String str3) {
        this.l = z6;
        this.f6982m = str;
        this.f6983n = z10;
        this.o = z11;
        this.f6984p = z12;
        this.f6985q = pVar;
        this.f6986r = playerStatus;
        this.f6987s = i7;
        this.f6988t = str2;
        this.f6989u = str3;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws XmlPullParserException, IOException {
        m6.b bVar;
        C0158h c0158h;
        C0158h c0158h2;
        C0158h c0158h3;
        C0159i c0159i;
        m7.q qVar;
        g0.g gVar;
        C0761q c0761q;
        C0159i c0159i2;
        C0158h c0158h4;
        C0761q c0761q2 = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f((D.T) obj, "$this$SwipeToDismissBox");
        int i7 = iIntValue & 17;
        m7.q qVar2 = m7.q.f19267a;
        if (i7 == 16 && c0761q2.x()) {
            c0761q2.K();
            return qVar2;
        }
        g0.l lVar = g0.l.f14684a;
        g0.o oVarD = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
        long j3 = l6.d.c(c0761q2).f18724z;
        l9.n nVar = AbstractC1861K.f19381a;
        g0.o oVarB = androidx.compose.foundation.a.b(oVarD, j3, nVar);
        c0761q2.Q(-581918976);
        boolean z6 = this.l;
        long j10 = z6 ? l6.d.c(c0761q2).f18723y : C1891t.f19451j;
        c0761q2.p(false);
        g0.o oVarB2 = androidx.compose.foundation.a.b(oVarB, j10, nVar);
        m6.b bVar2 = m6.c.f19252a;
        g0.o oVarO = androidx.compose.foundation.layout.a.o(oVarB2, bVar2.F(c0761q2), bVar2.H(6, c0761q2), 0.0f, bVar2.H(6, c0761q2), 4);
        D.S sB = D.P.b(AbstractC0127i.f1079f, g0.b.f14670v, c0761q2, 54);
        int i10 = c0761q2.f8304P;
        InterfaceC0744h0 interfaceC0744h0M = c0761q2.m();
        g0.o oVarD2 = g0.a.d(c0761q2, oVarO);
        InterfaceC0161k.f1681b.getClass();
        C0159i c0159i3 = C0160j.f1678b;
        c0761q2.U();
        if (c0761q2.f8303O) {
            c0761q2.l(c0159i3);
        } else {
            c0761q2.d0();
        }
        C0158h c0158h5 = C0160j.f1679e;
        C0735d.V(c0761q2, sB, c0158h5);
        C0158h c0158h6 = C0160j.d;
        C0735d.V(c0761q2, interfaceC0744h0M, c0158h6);
        C0158h c0158h7 = C0160j.f1680f;
        if (c0761q2.f8303O || !A7.m.a(c0761q2.G(), Integer.valueOf(i10))) {
            AbstractC1347d.l(i10, c0761q2, i10, c0158h7);
        }
        C0158h c0158h8 = C0160j.c;
        C0735d.V(c0761q2, oVarD2, c0158h8);
        g0.o oVarB3 = androidx.compose.foundation.layout.c.b(lVar, 1.0f);
        c0761q2.Q(-1990062989);
        C0773y c0773y = AbstractC1692D.f18238a;
        Object objK = c0761q2.k(c0773y);
        LayoutMode layoutMode = LayoutMode.NARROW_COMPACT;
        boolean z10 = objK == layoutMode || c0761q2.k(c0773y) == LayoutMode.SQUARE_COMPACT;
        c0761q2.p(false);
        g0.o oVarD3 = androidx.compose.foundation.layout.a.d(oVarB3, z10 ? 0.6f : 0.9f);
        C0.K kE = AbstractC0133o.e(g0.b.f14665q, false);
        int i11 = c0761q2.f8304P;
        InterfaceC0744h0 interfaceC0744h0M2 = c0761q2.m();
        g0.o oVarD4 = g0.a.d(c0761q2, oVarD3);
        c0761q2.U();
        if (c0761q2.f8303O) {
            c0761q2.l(c0159i3);
        } else {
            c0761q2.d0();
        }
        C0735d.V(c0761q2, kE, c0158h5);
        C0735d.V(c0761q2, interfaceC0744h0M2, c0158h6);
        if (c0761q2.f8303O || !A7.m.a(c0761q2.G(), Integer.valueOf(i11))) {
            AbstractC1347d.l(i11, c0761q2, i11, c0158h7);
        }
        C0735d.V(c0761q2, oVarD4, c0158h8);
        androidx.compose.foundation.layout.b bVar3 = androidx.compose.foundation.layout.b.f11474a;
        g0.g gVar2 = g0.b.f14664p;
        if (z6) {
            c0761q2.Q(726562437);
            S3.o oVar = new S3.o();
            c0761q2.R(-1248473602);
            A.W w10 = new A.W(3, null, 3);
            T.R0 r02 = AndroidCompositionLocals_androidKt.f11688b;
            Context context = (Context) c0761q2.k(r02);
            c0761q2.R(1388713953);
            boolean zF = c0761q2.f(oVar);
            Object objG = c0761q2.G();
            T.S s6 = C0753m.f8270a;
            T.S s10 = T.S.f8224q;
            if (zF || objG == s6) {
                objG = C0735d.O(new S3.n(), s10);
                c0761q2.a0(objG);
            }
            T.X x8 = (T.X) objG;
            c0761q2.p(false);
            c0761q2.R(1388714244);
            boolean zF2 = c0761q2.f(oVar) | c0761q2.f("__LottieInternalDefaultCacheKey__");
            Object objG2 = c0761q2.G();
            if (zF2 || objG2 == s6) {
                objG2 = P8.n.H(context, oVar, "__LottieInternalDefaultCacheKey__");
                c0761q2.a0(objG2);
            }
            c0761q2.p(false);
            C0735d.g(oVar, "__LottieInternalDefaultCacheKey__", new S3.t(w10, context, oVar, null, "fonts/", ".ttf", "__LottieInternalDefaultCacheKey__", x8, null), c0761q2);
            S3.n nVar2 = (S3.n) x8.getValue();
            c0761q2.p(false);
            O3.a aVar = (O3.a) nVar2.getValue();
            PlayerStatus playerStatus = PlayerStatus.PLAYING;
            PlayerStatus playerStatus2 = this.f6986r;
            boolean z11 = playerStatus2 == playerStatus;
            c0761q2.R(683659508);
            S3.l lVar2 = S3.l.l;
            if (Float.isInfinite(1.0f) || Float.isNaN(1.0f)) {
                throw new IllegalArgumentException("Speed must be a finite number. It is 1.0.");
            }
            c0761q2.R(2024497114);
            c0761q2.R(-610207850);
            Object objG3 = c0761q2.G();
            if (objG3 == s6) {
                objG3 = new S3.h();
                c0761q2.a0(objG3);
            }
            S3.h hVar = (S3.h) objG3;
            c0761q2.p(false);
            c0761q2.p(false);
            c0761q2.R(-180606964);
            Object objG4 = c0761q2.G();
            if (objG4 == s6) {
                objG4 = C0735d.O(Boolean.valueOf(z11), s10);
                c0761q2.a0(objG4);
            }
            c0761q2.p(false);
            c0761q2.R(-180606834);
            Context context2 = (Context) c0761q2.k(r02);
            Matrix matrix = AbstractC0927h.f11132a;
            float f10 = 1.0f / Settings.Global.getFloat(context2.getContentResolver(), "animator_duration_scale", 1.0f);
            c0761q2.p(false);
            C0735d.h(new Object[]{aVar, Boolean.valueOf(z11), null, Float.valueOf(f10), Integer.MAX_VALUE}, new S3.a(z11, true, hVar, aVar, Integer.MAX_VALUE, false, f10, lVar2, false, (T.X) objG4, null), c0761q2);
            c0761q2.p(false);
            g0.o oVarA = bVar3.a(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 0.45f), 1.0f), gVar2);
            O3.a aVar2 = (O3.a) nVar2.getValue();
            c0761q2.Q(-946370780);
            boolean zF3 = c0761q2.f(playerStatus2) | c0761q2.f(hVar);
            Object objG5 = c0761q2.G();
            if (zF3 || objG5 == s6) {
                objG5 = new M5.d(playerStatus2, 5, hVar);
                c0761q2.a0(objG5);
            }
            c0761q2.p(false);
            c0158h = c0158h6;
            c0158h2 = c0158h7;
            c0158h3 = c0158h5;
            c0159i = c0159i3;
            bVar = bVar2;
            qVar = qVar2;
            AbstractC0904a.g(aVar2, (InterfaceC2772a) objG5, oVarA, false, false, false, false, 0, false, null, null, false, false, null, 0, false, c0761q2, 0, 0);
            c0761q2.p(false);
            c0761q = c0761q2;
            gVar = gVar2;
        } else {
            bVar = bVar2;
            c0158h = c0158h6;
            c0158h2 = c0158h7;
            c0158h3 = c0158h5;
            c0159i = c0159i3;
            qVar = qVar2;
            c0761q2.Q(727407063);
            g0.o oVarA2 = bVar3.a(lVar, gVar2);
            String strValueOf = String.valueOf(this.f6987s + 1);
            long j11 = l6.d.c(c0761q2).f18696b;
            c0761q2.Q(-946357966);
            boolean z12 = c0761q2.k(c0773y) == layoutMode || c0761q2.k(c0773y) == LayoutMode.SQUARE_COMPACT;
            c0761q2.p(false);
            gVar = gVar2;
            Q.c1.b(strValueOf, oVarA2, j11, h6.f.g(z12 ? FontSize.Subtitle1 : FontSize.H9, c0761q2), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ((O5.d) c0761q2.k(O5.c.f5157b)).f5158a, c0761q2, 0, 0, 65520);
            c0761q = c0761q2;
            c0761q.p(false);
        }
        c0761q.p(true);
        m6.b bVar4 = bVar;
        com.bumptech.glide.c.k(h6.q.m(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 1.0f), J.e.a(bVar4.g0(c0761q))), this.f6982m, this.f6983n, this.o, this.f6984p, null, c0761q, 0, 32);
        g0.o oVarO2 = androidx.compose.foundation.layout.a.o(D.T.b(lVar, 1.0f), bVar4.F(c0761q) * 1.5f, 0.0f, 0.0f, 0.0f, 14);
        C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
        int i12 = c0761q.f8304P;
        InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
        g0.o oVarD5 = g0.a.d(c0761q, oVarO2);
        c0761q.U();
        if (c0761q.f8303O) {
            c0159i2 = c0159i;
            c0761q.l(c0159i2);
        } else {
            c0159i2 = c0159i;
            c0761q.d0();
        }
        C0158h c0158h9 = c0158h3;
        C0735d.V(c0761q, c0141xA, c0158h9);
        C0158h c0158h10 = c0158h;
        C0735d.V(c0761q, interfaceC0744h0M3, c0158h10);
        if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
            c0158h4 = c0158h2;
            AbstractC1347d.l(i12, c0761q, i12, c0158h4);
        } else {
            c0158h4 = c0158h2;
        }
        C0735d.V(c0761q, oVarD5, c0158h8);
        long j12 = l6.d.c(c0761q).f18696b;
        long jG = h6.f.g(FontSize.H10, c0761q);
        T.R0 r03 = O5.c.f5157b;
        C0761q c0761q3 = c0761q;
        C0158h c0158h11 = c0158h4;
        C0159i c0159i4 = c0159i2;
        Q.c1.b(this.f6988t, null, j12, jG, null, null, null, 0L, null, null, 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(r03)).d, c0761q3, 0, 3120, 55282);
        Q.c1.b(this.f6989u, null, l6.d.c(c0761q3).c, h6.f.g(FontSize.Subtitle1, c0761q3), null, S0.A.f7800s, null, 0L, null, null, 0L, 2, false, 1, 0, null, ((O5.d) c0761q3.k(r03)).f5159b, c0761q3, 196608, 3120, 55250);
        c0761q3.p(true);
        g0.o oVarD6 = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 1.0f);
        A7.m.f(oVarD6, "<this>");
        m7.q qVar3 = qVar;
        g0.o oVarK = oVarD6.k(y0.v.a(lVar, qVar3, new P5.c(this.f6985q, null)));
        C0.K kE2 = AbstractC0133o.e(gVar, false);
        int i13 = c0761q3.f8304P;
        InterfaceC0744h0 interfaceC0744h0M4 = c0761q3.m();
        g0.o oVarD7 = g0.a.d(c0761q3, oVarK);
        c0761q3.U();
        if (c0761q3.f8303O) {
            c0761q3.l(c0159i4);
        } else {
            c0761q3.d0();
        }
        C0735d.V(c0761q3, kE2, c0158h9);
        C0735d.V(c0761q3, interfaceC0744h0M4, c0158h10);
        if (c0761q3.f8303O || !A7.m.a(c0761q3.G(), Integer.valueOf(i13))) {
            AbstractC1347d.l(i13, c0761q3, i13, c0158h11);
        }
        C0735d.V(c0761q3, oVarD7, c0158h8);
        g0.o oVarD8 = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 0.6f), 1.0f);
        AbstractC2268a abstractC2268aW = l9.l.W(R.drawable.ic_drag_handle, c0761q3, 0);
        long j13 = l6.d.c(c0761q3).c;
        h6.q.a(abstractC2268aW, null, oVarD8, gVar, null, 0.0f, new C1883l(j13, 5, C1884m.f19441a.a(j13, 5)), c0761q3, 3504, 48);
        c0761q3.p(true);
        c0761q3.p(true);
        return qVar3;
    }
}

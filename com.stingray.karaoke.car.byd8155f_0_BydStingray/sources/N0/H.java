package N0;

import a.AbstractC0904a;
import android.graphics.RectF;
import android.text.Layout;
import h2.AbstractC1347d;
import java.text.BreakIterator;
import java.util.ArrayList;
import n0.AbstractC1861K;
import n0.C1880i;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final G f4348a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f4349b;
    public final long c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f4350e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f4351f;

    public H(G g7, o oVar, long j3) {
        this.f4348a = g7;
        this.f4349b = oVar;
        this.c = j3;
        ArrayList arrayList = oVar.f4396h;
        float fD = 0.0f;
        this.d = arrayList.isEmpty() ? 0.0f : ((r) arrayList.get(0)).f4402a.d.d(0);
        if (!arrayList.isEmpty()) {
            r rVar = (r) AbstractC1998m.h0(arrayList);
            fD = rVar.f4402a.d.d(r4.f5051g - 1) + rVar.f4405f;
        }
        this.f4350e = fD;
        this.f4351f = oVar.f4395g;
    }

    public final Y0.h a(int i7) {
        o oVar = this.f4349b;
        oVar.j(i7);
        int length = ((C0411f) oVar.f4391a.f4399m).l.length();
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(i7 == length ? AbstractC1999n.C(arrayList) : AbstractC2673a.C(i7, arrayList));
        return rVar.f4402a.d.f5050f.isRtlCharAt(rVar.b(i7)) ? Y0.h.f10616m : Y0.h.l;
    }

    public final m0.d b(int i7) {
        float fI;
        float fI2;
        float fH;
        float fH2;
        o oVar = this.f4349b;
        oVar.i(i7);
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.C(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        int iB = rVar.b(i7);
        CharSequence charSequence = c0406a.f4364e;
        if (iB < 0 || iB >= charSequence.length()) {
            StringBuilder sbJ = M5.b.j("offset(", iB, ") is out of bounds [0,");
            sbJ.append(charSequence.length());
            sbJ.append(')');
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
        O0.x xVar = c0406a.d;
        Layout layout = xVar.f5050f;
        int lineForOffset = layout.getLineForOffset(iB);
        float fG = xVar.g(lineForOffset);
        float fE = xVar.e(lineForOffset);
        boolean z6 = layout.getParagraphDirection(lineForOffset) == 1;
        boolean zIsRtlCharAt = layout.isRtlCharAt(iB);
        if (!z6 || zIsRtlCharAt) {
            if (z6 && zIsRtlCharAt) {
                fH = xVar.i(iB, false);
                fH2 = xVar.i(iB + 1, true);
            } else if (zIsRtlCharAt) {
                fH = xVar.h(iB, false);
                fH2 = xVar.h(iB + 1, true);
            } else {
                fI = xVar.i(iB, false);
                fI2 = xVar.i(iB + 1, true);
            }
            float f10 = fH;
            fI = fH2;
            fI2 = f10;
        } else {
            fI = xVar.h(iB, false);
            fI2 = xVar.h(iB + 1, true);
        }
        RectF rectF = new RectF(fI, fG, fI2, fE);
        float f11 = rectF.left;
        float f12 = rectF.top;
        float f13 = rectF.right;
        float f14 = rectF.bottom;
        long jA = h6.e.a(0.0f, rVar.f4405f);
        return new m0.d(m0.c.d(jA) + f11, m0.c.e(jA) + f12, m0.c.d(jA) + f13, m0.c.e(jA) + f14);
    }

    public final m0.d c(int i7) {
        o oVar = this.f4349b;
        oVar.j(i7);
        int length = ((C0411f) oVar.f4391a.f4399m).l.length();
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(i7 == length ? AbstractC1999n.C(arrayList) : AbstractC2673a.C(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        int iB = rVar.b(i7);
        CharSequence charSequence = c0406a.f4364e;
        if (iB < 0 || iB > charSequence.length()) {
            StringBuilder sbJ = M5.b.j("offset(", iB, ") is out of bounds [0,");
            sbJ.append(charSequence.length());
            sbJ.append(']');
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
        O0.x xVar = c0406a.d;
        float fH = xVar.h(iB, false);
        int lineForOffset = xVar.f5050f.getLineForOffset(iB);
        float fG = xVar.g(lineForOffset);
        float fE = xVar.e(lineForOffset);
        long jA = h6.e.a(0.0f, rVar.f4405f);
        return new m0.d(m0.c.d(jA) + fH, m0.c.e(jA) + fG, m0.c.d(jA) + fH, m0.c.e(jA) + fE);
    }

    public final float d(int i7, boolean z6) {
        o oVar = this.f4349b;
        oVar.j(i7);
        int length = ((C0411f) oVar.f4391a.f4399m).l.length();
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(i7 == length ? AbstractC1999n.C(arrayList) : AbstractC2673a.C(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        int iB = rVar.b(i7);
        O0.x xVar = c0406a.d;
        return z6 ? xVar.h(iB, false) : xVar.i(iB, false);
    }

    public final int e(int i7, boolean z6) {
        int iF;
        o oVar = this.f4349b;
        oVar.k(i7);
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.D(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        int i10 = i7 - rVar.d;
        O0.x xVar = c0406a.d;
        if (z6) {
            Layout layout = xVar.f5050f;
            if (layout.getEllipsisStart(i10) == 0) {
                q qVarC = xVar.c();
                Layout layout2 = (Layout) qVarC.f4399m;
                iF = qVarC.I(layout2.getLineEnd(i10), layout2.getLineStart(i10));
            } else {
                iF = layout.getEllipsisStart(i10) + layout.getLineStart(i10);
            }
        } else {
            iF = xVar.f(i10);
        }
        return iF + rVar.f4403b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof H)) {
            return false;
        }
        H h10 = (H) obj;
        return A7.m.a(this.f4348a, h10.f4348a) && this.f4349b.equals(h10.f4349b) && a1.j.a(this.c, h10.c) && this.d == h10.d && this.f4350e == h10.f4350e && A7.m.a(this.f4351f, h10.f4351f);
    }

    public final int f(int i7) {
        o oVar = this.f4349b;
        int length = ((C0411f) oVar.f4391a.f4399m).l.length();
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(i7 >= length ? AbstractC1999n.C(arrayList) : i7 < 0 ? 0 : AbstractC2673a.C(i7, arrayList));
        return rVar.f4402a.d.f5050f.getLineForOffset(rVar.b(i7)) + rVar.d;
    }

    public final float g(int i7) {
        o oVar = this.f4349b;
        oVar.k(i7);
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.D(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        int i10 = i7 - rVar.d;
        O0.x xVar = c0406a.d;
        return xVar.f5050f.getLineLeft(i10) + (i10 == xVar.f5051g + (-1) ? xVar.f5054j : 0.0f);
    }

    public final float h(int i7) {
        o oVar = this.f4349b;
        oVar.k(i7);
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.D(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        int i10 = i7 - rVar.d;
        O0.x xVar = c0406a.d;
        return xVar.f5050f.getLineRight(i10) + (i10 == xVar.f5051g + (-1) ? xVar.f5055k : 0.0f);
    }

    public final int hashCode() {
        return this.f4351f.hashCode() + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.c((this.f4349b.hashCode() + (this.f4348a.hashCode() * 31)) * 31, 31, this.c), 31, this.d), 31, this.f4350e);
    }

    public final int i(int i7) {
        o oVar = this.f4349b;
        oVar.k(i7);
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.D(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        return c0406a.d.f5050f.getLineStart(i7 - rVar.d) + rVar.f4403b;
    }

    public final Y0.h j(int i7) {
        o oVar = this.f4349b;
        oVar.j(i7);
        int length = ((C0411f) oVar.f4391a.f4399m).l.length();
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(i7 == length ? AbstractC1999n.C(arrayList) : AbstractC2673a.C(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        int iB = rVar.b(i7);
        O0.x xVar = c0406a.d;
        return xVar.f5050f.getParagraphDirection(xVar.f5050f.getLineForOffset(iB)) == 1 ? Y0.h.l : Y0.h.f10616m;
    }

    public final C1880i k(int i7, int i10) {
        o oVar = this.f4349b;
        C0411f c0411f = (C0411f) oVar.f4391a.f4399m;
        if (i7 < 0 || i7 > i10 || i10 > c0411f.l.length()) {
            StringBuilder sbL = M5.b.l("Start(", ") or End(", i7, i10, ") is out of range [0..");
            sbL.append(c0411f.l.length());
            sbL.append("), or start > end!");
            throw new IllegalArgumentException(sbL.toString().toString());
        }
        if (i7 == i10) {
            return AbstractC1861K.i();
        }
        C1880i c1880iI = AbstractC1861K.i();
        AbstractC2673a.F(oVar.f4396h, AbstractC0904a.q(i7, i10), new L.c(i7, i10, 2, c1880iI));
        return c1880iI;
    }

    public final long l(int i7) {
        int iPreceding;
        int iFollowing;
        int iFollowing2;
        o oVar = this.f4349b;
        oVar.j(i7);
        int length = ((C0411f) oVar.f4391a.f4399m).l.length();
        ArrayList arrayList = oVar.f4396h;
        r rVar = (r) arrayList.get(i7 == length ? AbstractC1999n.C(arrayList) : AbstractC2673a.C(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        int iB = rVar.b(i7);
        A2.g gVarJ = c0406a.d.j();
        gVarJ.c(iB);
        BreakIterator breakIterator = (BreakIterator) gVarJ.f321p;
        if (gVarJ.s(breakIterator.preceding(iB))) {
            gVarJ.c(iB);
            iPreceding = iB;
            while (iPreceding != -1 && (!gVarJ.s(iPreceding) || gVarJ.q(iPreceding))) {
                gVarJ.c(iPreceding);
                iPreceding = breakIterator.preceding(iPreceding);
            }
        } else {
            gVarJ.c(iB);
            iPreceding = gVarJ.r(iB) ? (!breakIterator.isBoundary(iB) || gVarJ.p(iB)) ? breakIterator.preceding(iB) : iB : gVarJ.p(iB) ? breakIterator.preceding(iB) : -1;
        }
        if (iPreceding == -1) {
            iPreceding = iB;
        }
        gVarJ.c(iB);
        if (gVarJ.q(breakIterator.following(iB))) {
            gVarJ.c(iB);
            iFollowing = iB;
            while (iFollowing != -1 && (gVarJ.s(iFollowing) || !gVarJ.q(iFollowing))) {
                gVarJ.c(iFollowing);
                iFollowing = breakIterator.following(iFollowing);
            }
        } else {
            gVarJ.c(iB);
            if (gVarJ.p(iB)) {
                iFollowing2 = (!breakIterator.isBoundary(iB) || gVarJ.r(iB)) ? breakIterator.following(iB) : iB;
            } else if (gVarJ.r(iB)) {
                iFollowing2 = breakIterator.following(iB);
            } else {
                iFollowing = -1;
            }
            iFollowing = iFollowing2;
        }
        if (iFollowing != -1) {
            iB = iFollowing;
        }
        return rVar.a(AbstractC0904a.q(iPreceding, iB), false);
    }

    public final String toString() {
        return "TextLayoutResult(layoutInput=" + this.f4348a + ", multiParagraph=" + this.f4349b + ", size=" + ((Object) a1.j.b(this.c)) + ", firstBaseline=" + this.d + ", lastBaseline=" + this.f4350e + ", placeholderRects=" + this.f4351f + ')';
    }
}

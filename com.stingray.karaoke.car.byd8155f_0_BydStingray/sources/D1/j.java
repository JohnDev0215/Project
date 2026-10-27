package d1;

import E0.AbstractC0156f;
import E0.C0170u;
import E0.F;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import l0.AbstractC1713d;
import l0.C1727r;
import l0.InterfaceC1716g;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final io.sentry.hints.i f13314a = new io.sentry.hints.i(18);

    public static final boolean a(View view, View view2) {
        for (ViewParent parent = view2.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == view.getParent()) {
                return true;
            }
        }
        return false;
    }

    public static final Rect b(InterfaceC1716g interfaceC1716g, View view, View view2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationOnScreen(iArr2);
        C1727r c1727rG = AbstractC1713d.g(((androidx.compose.ui.focus.b) interfaceC1716g).f11569f);
        m0.d dVarJ = c1727rG != null ? AbstractC1713d.j(c1727rG) : null;
        if (dVarJ == null) {
            return null;
        }
        int i7 = (int) dVarJ.f18882a;
        int i10 = iArr[0];
        int i11 = iArr2[0];
        int i12 = (int) dVarJ.f18883b;
        int i13 = iArr[1];
        int i14 = iArr2[1];
        return new Rect((i7 + i10) - i11, (i12 + i13) - i14, (((int) dVarJ.c) + i10) - i11, (((int) dVarJ.d) + i13) - i14);
    }

    public static final View c(g0.n nVar) {
        o oVar = AbstractC0156f.t(nVar.l).f1488u;
        View interopView = oVar != null ? oVar.getInteropView() : null;
        if (interopView != null) {
            return interopView;
        }
        throw new IllegalStateException("Could not fetch interop view");
    }

    public static final void d(o oVar, F f10) {
        long jN = ((C0170u) f10.f1469H.c).N(0L);
        int iRound = Math.round(m0.c.d(jN));
        int iRound2 = Math.round(m0.c.e(jN));
        oVar.layout(iRound, iRound2, oVar.getMeasuredWidth() + iRound, oVar.getMeasuredHeight() + iRound2);
    }
}

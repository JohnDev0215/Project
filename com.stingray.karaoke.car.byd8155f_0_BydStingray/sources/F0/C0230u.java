package F0;

import S8.InterfaceC0725w;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.ui.platform.AndroidComposeView;
import l0.AbstractC1713d;
import l0.C1711b;
import l0.InterfaceC1716g;
import n0.AbstractC1861K;
import w0.C2586a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F0.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0230u extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2278m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AndroidComposeView f2279n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0230u(AndroidComposeView androidComposeView, int i7) {
        super(1);
        this.f2278m = i7;
        this.f2279n = androidComposeView;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C1711b c1711b;
        switch (this.f2278m) {
            case 0:
                KeyEvent keyEvent = ((w0.b) obj).f23165a;
                AndroidComposeView androidComposeView = this.f2279n;
                androidComposeView.getClass();
                long jR = w0.c.R(keyEvent);
                if (C2586a.a(jR, C2586a.f23158h)) {
                    c1711b = new C1711b(keyEvent.isShiftPressed() ? 2 : 1);
                } else if (C2586a.a(jR, C2586a.f23156f)) {
                    c1711b = new C1711b(4);
                } else if (C2586a.a(jR, C2586a.f23155e)) {
                    c1711b = new C1711b(3);
                } else {
                    c1711b = C2586a.a(jR, C2586a.c) ? true : C2586a.a(jR, C2586a.f23161k) ? new C1711b(5) : C2586a.a(jR, C2586a.d) ? true : C2586a.a(jR, C2586a.l) ? new C1711b(6) : C2586a.a(jR, C2586a.f23157g) ? true : C2586a.a(jR, C2586a.f23159i) ? true : C2586a.a(jR, C2586a.f23162m) ? new C1711b(7) : C2586a.a(jR, C2586a.f23154b) ? true : C2586a.a(jR, C2586a.f23160j) ? new C1711b(8) : null;
                }
                if (c1711b == null || !h6.q.p(w0.c.S(keyEvent), 2)) {
                    return Boolean.FALSE;
                }
                m0.d dVarT = androidComposeView.t();
                InterfaceC1716g focusOwner = androidComposeView.getFocusOwner();
                C0228t c0228t = new C0228t(c1711b, 1);
                int i7 = c1711b.f18325a;
                Boolean boolD = ((androidx.compose.ui.focus.b) focusOwner).d(i7, dVarT, c0228t);
                if (boolD != null ? boolD.booleanValue() : true) {
                    return Boolean.TRUE;
                }
                if (!(C1711b.a(i7, 1) ? true : C1711b.a(i7, 2))) {
                    return Boolean.FALSE;
                }
                Integer numI = AbstractC1713d.I(i7);
                if (numI == null) {
                    throw new IllegalStateException("Invalid focus direction");
                }
                int iIntValue = numI.intValue();
                Rect rectB = dVarT != null ? AbstractC1861K.B(dVarT) : null;
                if (rectB == null) {
                    throw new IllegalStateException("Invalid rect");
                }
                View viewFindNextFocus = androidComposeView;
                while (true) {
                    if (viewFindNextFocus != null) {
                        FocusFinder focusFinder = FocusFinder.getInstance();
                        View rootView = androidComposeView.getRootView();
                        A7.m.d(rootView, "null cannot be cast to non-null type android.view.ViewGroup");
                        viewFindNextFocus = focusFinder.findNextFocus((ViewGroup) rootView, viewFindNextFocus, iIntValue);
                        if (viewFindNextFocus != null) {
                            if (!viewFindNextFocus.equals(androidComposeView)) {
                                for (ViewParent parent = viewFindNextFocus.getParent(); parent != null; parent = parent.getParent()) {
                                    if (parent == androidComposeView) {
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        viewFindNextFocus = null;
                    }
                }
                if (A7.m.a(viewFindNextFocus, androidComposeView)) {
                    viewFindNextFocus = null;
                }
                if ((viewFindNextFocus == null || !AbstractC1713d.D(viewFindNextFocus, Integer.valueOf(iIntValue), rectB)) && ((androidx.compose.ui.focus.b) androidComposeView.getFocusOwner()).b(i7, false, false)) {
                    Boolean boolD2 = ((androidx.compose.ui.focus.b) androidComposeView.getFocusOwner()).d(i7, null, new C0228t(c1711b, 0));
                    return Boolean.valueOf(boolD2 != null ? boolD2.booleanValue() : true);
                }
                return Boolean.TRUE;
            case 1:
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) obj;
                AndroidComposeView androidComposeView2 = this.f2279n;
                Handler handler = androidComposeView2.getHandler();
                if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                    interfaceC2772a.a();
                } else {
                    Handler handler2 = androidComposeView2.getHandler();
                    if (handler2 != null) {
                        handler2.post(new RunnableC0238y(interfaceC2772a, 0));
                    }
                }
                return m7.q.f19267a;
            default:
                AndroidComposeView androidComposeView3 = this.f2279n;
                return new C0196c0(androidComposeView3, androidComposeView3.getTextInputService(), (InterfaceC0725w) obj);
        }
    }
}

package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import defpackage.df1;
import defpackage.hm2;
import defpackage.xl2;
import defpackage.zm0;
import java.util.LinkedHashSet;
import org.chromium.base.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.share.screenshot.ScreenshotShareSheetDialog;
import org.chromium.chrome.browser.signin.SigninFirstRunFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TabWebContentsDelegateAndroidImpl;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.touch_to_fill.TouchToFillBridge;
import org.chromium.chrome.browser.ui.signin.e;
import org.chromium.chrome.browser.ui.signin.f;
import org.chromium.chrome.browser.ui.signin.h;
import org.chromium.chrome.browser.webauthn.CableAuthenticatorModuleProvider;
import org.chromium.components.browser_ui.client_certificate.SSLClientCertificateRequest;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.content.browser.TracingControllerAndroidImpl;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class d92 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ d92(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                Tab tab = (Tab) obj;
                if (!tab.isDestroyed()) {
                    mk3 mk3VarB = tab.B();
                    LinkedHashSet<Integer> linkedHashSet = i92.u;
                    i92 i92Var = (i92) mk3VarB.b(i92.class);
                    i92.z1(i92Var.j);
                    i92Var.s = true;
                    break;
                }
                break;
            case 1:
                ((ScreenshotShareSheetDialog) obj).I0(true, false);
                break;
            case 2:
                xk2 xk2Var = (xk2) obj;
                if (xk2Var.e.d(xk2Var.t, true)) {
                    oj2.Y(System.currentTimeMillis() - xk2Var.l, "Sharing.SharingHubAndroid.TimeToShowShareSheet");
                }
                break;
            case 3:
                ft2 ft2Var = (ft2) obj;
                if (ft2Var.h) {
                    ft2Var.f.i.b();
                    ft2Var.g.a().e(ft2Var.e);
                    ft2Var.h = false;
                    break;
                }
                break;
            case 4:
                ((pt2) obj).f.b(true);
                break;
            case 5:
                ((Activity) obj).closeContextMenu();
                break;
            case 6:
                l23.a((l23) obj);
                break;
            case 7:
                TabImpl tabImpl = ((TabWebContentsDelegateAndroidImpl) obj).a;
                b.a aVarN = tabImpl.N();
                while (aVarN.hasNext()) {
                    ((n23) aVarN.next()).s(tabImpl);
                }
                break;
            case 8:
                int i2 = xl2.t;
                ((xl2.e) obj).b.onResult(null);
                break;
            case 9:
                ((Animatable) obj).start();
                break;
            case 10:
                q1 q1Var = q1.this;
                Tab tabI = q1Var.k.i();
                if (tabI != null) {
                    q1Var.l.a(tabI);
                }
                q1Var.o = null;
                break;
            case 11:
                ((pn3) obj).b();
                break;
            case 12:
                ((h61) obj).T(null);
                break;
            case 13:
                g gVar = (g) obj;
                gVar.getClass();
                oc3 oc3Var = new oc3(gVar);
                gVar.D = oc3Var;
                gVar.C.a(oc3Var);
                break;
            case 14:
                cf1 cf1Var = (cf1) obj;
                if (!cf1Var.l.get().booleanValue() && cf1Var.k) {
                    if (cf1Var.r.get() != null) {
                        cf1Var.d(true);
                        cf1Var.h.run();
                    } else if (((df1.a) cf1Var.g.j(df1.h)).a) {
                        cf1Var.c(true);
                    }
                    break;
                }
                break;
            case 15:
                ((ib3) obj).k = false;
                break;
            case 16:
                ke3 ke3Var = (ke3) obj;
                ke3Var.c.l(le3.a, false);
                oj2.U(2, 4, "PasswordManager.TouchToFill.UserAction");
                boolean z = ke3Var.f.size() > 0;
                long j = ((TouchToFillBridge) ke3Var.b).a;
                if (j != 0) {
                    N.MZxrSSig(j, z);
                }
                break;
            case 17:
                te3 te3Var = (te3) obj;
                if (te3Var.c == 3) {
                    TracingControllerAndroidImpl tracingControllerAndroidImpl = te3Var.a;
                    x10 x10Var = new x10(11, te3Var);
                    if (tracingControllerAndroidImpl.i == 0) {
                        tracingControllerAndroidImpl.i = N.MWlLnA$6(tracingControllerAndroidImpl);
                    }
                    N.MkLMghix(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, x10Var);
                    break;
                }
                break;
            case 18:
                f fVar = (f) obj;
                fVar.getClass();
                e eVar = new e(fVar);
                h hVar = fVar.e;
                hVar.a();
                hVar.d = new h.c(hVar.b, hVar.a, eVar);
                break;
            case 19:
                ((C0219x0) obj).i.n(C0221y0.e, 2);
                break;
            case 20:
                km2 km2Var = (km2) obj;
                if (!km2Var.b()) {
                    PropertyModel propertyModel = km2Var.j;
                    boolean zK = propertyModel.k(lm2.j);
                    hm2.a aVar = km2Var.h;
                    if (!zK) {
                        SigninFirstRunFragment signinFirstRunFragment = (SigninFirstRunFragment) aVar;
                        signinFirstRunFragment.b().i1(km2Var.r);
                        signinFirstRunFragment.H0();
                    } else if (km2Var.p != null) {
                        SigninFirstRunFragment signinFirstRunFragment2 = (SigninFirstRunFragment) aVar;
                        signinFirstRunFragment2.b().i1(km2Var.r);
                        if (!propertyModel.k(lm2.c)) {
                            signinFirstRunFragment2.b().R0(TextUtils.equals(km2Var.q, km2Var.p) ? 9 : 10);
                            z7 z7VarM = z7.m();
                            Profile profileD = Profile.d();
                            z7VarM.getClass();
                            CoreAccountInfo coreAccountInfoA = z7.v(profileD).a(0);
                            if (coreAccountInfoA != null && coreAccountInfoA.getEmail().equals(km2Var.p)) {
                                signinFirstRunFragment2.H0();
                            } else {
                                propertyModel.l(lm2.f, true);
                                z7 z7VarM2 = z7.m();
                                Profile profileD2 = Profile.d();
                                z7VarM2.getClass();
                                z7.D(profileD2).c(org.chromium.components.signin.b.c(km2Var.p), new jm2(km2Var));
                            }
                        } else {
                            signinFirstRunFragment2.H0();
                        }
                    } else {
                        ((SigninFirstRunFragment) aVar).b().R0(8);
                        AccountManagerFacadeProvider.getInstance().k();
                    }
                    break;
                }
                break;
            case 21:
                CableAuthenticatorModuleProvider cableAuthenticatorModuleProvider = (CableAuthenticatorModuleProvider) obj;
                int i3 = CableAuthenticatorModuleProvider.a0;
                ViewGroup viewGroup = (ViewGroup) cableAuthenticatorModuleProvider.f12J;
                viewGroup.removeAllViews();
                viewGroup.addView(cableAuthenticatorModuleProvider.Z);
                break;
            case 22:
                l43 l43Var = (l43) obj;
                j43 j43Var = l43Var.v;
                if (j43Var != null) {
                    l43Var.v = null;
                    j43Var.run();
                }
                break;
            case 23:
                ((cv1) obj).a();
                break;
            case 24:
                N.M8LmNuWo(((SSLClientCertificateRequest.b) obj).a, null, null);
                break;
            case 25:
                boolean z2 = PickerVideoPlayer.E;
                ((PickerVideoPlayer) obj).a();
                break;
            case 26:
                zm0.a aVar2 = (zm0.a) obj;
                zm0 zm0Var = zm0.this;
                if (zm0Var.a.get() != null) {
                    zm0Var.a.get().getViewTreeObserver().removeOnDrawListener(aVar2);
                    break;
                }
                break;
            case 27:
                Drawable drawable = (Drawable) obj;
                if (drawable.isVisible()) {
                    ((Animatable) drawable).start();
                }
                break;
            default:
                ((fh1) obj).e();
                break;
        }
    }
}

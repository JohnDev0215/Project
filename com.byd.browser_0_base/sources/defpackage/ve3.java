package defpackage;

import J.N;
import android.content.Intent;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import defpackage.b1;
import defpackage.u12;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.library_loader.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.fast_checkout.FastCheckoutBridge;
import org.chromium.chrome.browser.ui.signin.k;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;
import org.chromium.components.browser_ui.site_settings.ClearWebsiteStorageDialog;
import org.chromium.components.browser_ui.site_settings.GroupedWebsitesSettings;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.components.download.NetworkStatusListenerAndroid;
import org.chromium.components.messages.b;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.d;
import org.chromium.components.page_info.e;
import org.chromium.components.paintpreview.player.b;
import org.chromium.content.browser.AppWebMessagePort;
import org.chromium.content.browser.LauncherThread;
import org.chromium.content.browser.TracingControllerAndroidImpl;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ve3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ve3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        f fVar;
        int i = 14;
        int i2 = 0;
        switch (this.e) {
            case 0:
                Intent intent = (Intent) this.f;
                if (!((te3.f == null || te3.a().c == 0) ? false : true)) {
                    new NotificationManagerProxyImpl(xz.a).d(100, "tracing_status");
                    ue3.a = null;
                    return;
                }
                if (!"org.chromium.chrome.browser.tracing.STOP_RECORDING".equals(intent.getAction())) {
                    if ("org.chromium.chrome.browser.tracing.DISCARD_TRACE".equals(intent.getAction())) {
                        te3.a().b(1);
                        return;
                    }
                    return;
                }
                te3 te3VarA = te3.a();
                te3VarA.b(4);
                np1 np1VarA = ue3.a();
                np1VarA.k("Chrome trace is stopping");
                np1VarA.j("Trace data is being collected and compressed.");
                np1VarA.o(true);
                ue3.b(np1VarA.e());
                TracingControllerAndroidImpl tracingControllerAndroidImpl = te3VarA.a;
                se3 se3Var = new se3(i2, te3VarA);
                if (tracingControllerAndroidImpl.d) {
                    N.M$HKWu8q(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, tracingControllerAndroidImpl.f, tracingControllerAndroidImpl.g, tracingControllerAndroidImpl.h, se3Var);
                    return;
                }
                return;
            case 1:
                ((AppCompatActivity) this.f).onBackPressed();
                return;
            case 2:
                long j = ((FastCheckoutBridge) ((ek0) this.f).b).a;
                if (j != 0) {
                    N.MdJsVOZa(j);
                }
                C0173a.a(9);
                return;
            case 3:
                ((k) this.f).b.h0 = false;
                return;
            case 4:
                ((b1.a) this.f).u();
                return;
            case 5:
                nr2 nr2Var = (nr2) this.f;
                nr2Var.getClass();
                if (!a.l.d() || nr2Var.R || nr2Var.O == null) {
                    return;
                }
                Profile profileD = Profile.d();
                ri1 ri1Var = new ri1(nr2Var.a, profileD, nr2Var.f);
                nr2Var.P = new qi1(nr2Var.N, ri1Var, profileD);
                ca3 ca3Var = new ca3(nr2Var.a, profileD, ri1Var, nr2Var.l, 2);
                nr2Var.Q = ca3Var;
                nr2Var.O.b(nr2Var.P, ca3Var, new wd0(22));
                nr2Var.R = true;
                return;
            case 6:
                l43.this.o = null;
                return;
            case 7:
                ((AutoCompleteTextView) this.f).requestFocus();
                return;
            case 8:
                org.chromium.components.browser_ui.bottomsheet.f.n((org.chromium.components.browser_ui.bottomsheet.f) this.f);
                return;
            case 9:
                PickerVideoPlayer pickerVideoPlayer = (PickerVideoPlayer) this.f;
                boolean z = PickerVideoPlayer.E;
                pickerVideoPlayer.a();
                return;
            case 10:
                ao3.e(((ClearWebsiteStorageDialog) this.f).x0, "ClearWebsiteStorageDialog.onConfigurationChanged Runnable");
                return;
            case 11:
                GroupedWebsitesSettings groupedWebsitesSettings = (GroupedWebsitesSettings) this.f;
                int i3 = GroupedWebsitesSettings.k0;
                FragmentActivity fragmentActivityD = groupedWebsitesSettings.D();
                if (fragmentActivityD == null || fragmentActivityD.isFinishing()) {
                    return;
                }
                groupedWebsitesSettings.D().finish();
                return;
            case 12:
                ef2 ef2Var = (ef2) this.f;
                j jVar = ef2Var.d;
                if (jVar != null) {
                    jVar.c();
                }
                kf2 kf2Var = ef2Var.c;
                if (kf2Var != null) {
                    uh3.l(kf2Var);
                }
                ef2Var.c = null;
                ef2Var.d = null;
                return;
            case 13:
                NetworkStatusListenerAndroid.a aVar = (NetworkStatusListenerAndroid.a) this.f;
                aVar.getClass();
                Object obj = ThreadUtils.a;
                aVar.b = new org.chromium.components.download.a(aVar);
                return;
            case 14:
                ((jq) ((eh1) this.f)).a();
                return;
            case 15:
                ig1 ig1Var = (ig1) this.f;
                Runnable runnable = ig1Var.b;
                if (runnable == null) {
                    return;
                }
                ig1Var.a();
                long j2 = ig1Var.a;
                ig1Var.a = j2;
                ig1Var.b = runnable;
                ig1Var.c.postDelayed(runnable, j2);
                return;
            case 16:
                ((fh1) this.f).g();
                return;
            case 17:
                b bVar = (b) this.f;
                bVar.e.a(3, bVar.d);
                return;
            case 18:
                PageInfoController pageInfoController = (PageInfoController) this.f;
                pageInfoController.getClass();
                Clipboard.getInstance().b(pageInfoController.m);
                return;
            case 19:
                d dVar = (d) this.f;
                tu1 tu1Var = dVar.g;
                PageInfoController pageInfoController2 = (PageInfoController) tu1Var;
                pageInfoController2.f(13);
                if (N.M9l6T3Dg(((vq) ((PageInfoController) dVar.g).h).j, dVar.i) || (fVar = dVar.q) == null) {
                    return;
                }
                Profile profile = ((vq) pageInfoController2.h).j;
                Objects.requireNonNull(tu1Var);
                org.chromium.components.browser_ui.site_settings.d.b(profile, fVar, new es3(i, tu1Var));
                return;
            case 20:
                e eVar = (e) this.f;
                if (eVar.m != -1) {
                    eVar.o.a(2);
                }
                PageInfoController pageInfoController3 = (PageInfoController) eVar.g;
                pageInfoController3.f(14);
                pageInfoController3.e(eVar);
                return;
            case 21:
                ((b.InterfaceC0137b) this.f).c();
                return;
            case 22:
                u12.a aVar2 = (u12.a) this.f;
                u12 u12Var = aVar2.h;
                int i4 = aVar2.e;
                int i5 = aVar2.f;
                u12.b(u12Var, i4, i5);
                u12.a[][] aVarArr = u12Var.e;
                if (aVarArr == null) {
                    return;
                }
                aVarArr[i4][i5] = null;
                return;
            case 23:
                Runnable runnable2 = ((f22) this.f).a;
                if (runnable2 != null) {
                    runnable2.run();
                }
                if (k22.a("PaintPreview.Player.Scrolled")) {
                    return;
                }
                n3.X0("PaintPreview.Player.Scrolled");
                k22.a.put("PaintPreview.Player.Scrolled", Long.valueOf(System.currentTimeMillis()));
                return;
            case 24:
                ((org.chromium.components.payments.a) this.f).A(false);
                return;
            case 25:
                x21 x21Var = (x21) this.f;
                int i6 = x21.k;
                x21Var.A();
                return;
            case 26:
                throw ((lu2) this.f);
            case 27:
                AppWebMessagePort appWebMessagePort = (AppWebMessagePort) this.f;
                if (appWebMessagePort.a == 0) {
                    return;
                }
                appWebMessagePort.c = true;
                N.MJ$gNv5y(appWebMessagePort.a);
                return;
            case 28:
                LauncherThread.b.removeCallbacks(((rn) this.f).e);
                return;
            default:
                ((bi1) this.f).h(1);
                return;
        }
    }
}

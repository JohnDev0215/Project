package defpackage;

import android.animation.ValueAnimator;
import android.app.admin.DevicePolicyManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import defpackage.mm0;
import defpackage.z23;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_manager.settings.ExportErrorDialogFragment;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;
import org.chromium.chrome.browser.password_manager.settings.b;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.share.qrcode.scan_tab.CameraPreview;
import org.chromium.chrome.browser.signin.SigninFirstRunFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.a1;
import org.chromium.chrome.browser.tasks.tab_management.f1;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.chrome.browser.tasks.tab_management.v0;
import org.chromium.chrome.browser.toolbar.ToolbarProgressBar;
import org.chromium.chrome.browser.ui.autofill.d;
import org.chromium.chrome.browser.ui.signin.k;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class zx1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ zx1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayListB;
        int i = this.e;
        Camera cameraOpen = null;
        Object obj = this.f;
        switch (i) {
            case 1:
                final b bVar = (b) obj;
                bVar.a = 0;
                if (!bVar.b.equals(Uri.EMPTY)) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/csv");
                    intent.putExtra("android.intent.extra.STREAM", bVar.b);
                    intent.putExtra("android.intent.extra.SUBJECT", PasswordSettings.this.D().getResources().getString(R$string.password_settings_export_subject));
                    try {
                        Intent intentCreateChooser = Intent.createChooser(intent, null);
                        intentCreateChooser.addFlags(268435456);
                        xz.a.startActivity(intentCreateChooser);
                        break;
                    } catch (ActivityNotFoundException unused) {
                        final int i2 = R$string.password_settings_export_no_app;
                        final int i3 = R$string.password_settings_export_learn_google_drive;
                        Runnable runnable = new Runnable() { // from class: vi0
                            public final /* synthetic */ String g = null;

                            @Override // java.lang.Runnable
                            public final void run() {
                                ExportErrorDialogFragment.a aVar = new ExportErrorDialogFragment.a();
                                b bVar2 = bVar;
                                bVar2.e = aVar;
                                aVar.a = i3;
                                aVar.b = PasswordSettings.this.D().getResources().getString(i2);
                                String str = this.g;
                                if (str != null) {
                                    bVar2.e.c = PasswordSettings.this.D().getResources().getString(R$string.password_settings_export_error_details, str);
                                }
                                if (bVar2.f == null) {
                                    bVar2.a();
                                }
                            }
                        };
                        s90 s90Var = bVar.d;
                        s90Var.d = runnable;
                        ln2 ln2Var = s90Var.c;
                        if (ln2Var == null) {
                            r90 r90Var = s90Var.a;
                            if (r90Var != null) {
                                r90Var.I0(false, false);
                            }
                            Runnable runnable2 = s90Var.d;
                            if (runnable2 != null) {
                                PostTask.c(rh3.a, runnable2);
                            }
                            s90Var.a = null;
                            s90Var.d = null;
                            s90Var.c = null;
                        } else {
                            ln2Var.run();
                        }
                    }
                    bVar.b = null;
                    break;
                }
                break;
            case 2:
                NavigationController navigationControllerM = ((az1) obj).i.m();
                if (navigationControllerM != null && navigationControllerM.g()) {
                    navigationControllerM.e();
                    break;
                }
                break;
            case 3:
                j jVar = (j) obj;
                if (jVar.x != null) {
                    jVar.u();
                }
                break;
            case 4:
                ((d52) obj).h.pageScroll(130);
                break;
            case 5:
                ((g52) obj).l.pageScroll(130);
                break;
            case 6:
                ((i7) ((b92) obj).b).c(null);
                break;
            case 7:
                ((od2) obj).l();
                break;
            case 8:
                gm2 gm2Var = (gm2) obj;
                gm2Var.c = true;
                gm2Var.a();
                break;
            case 9:
                SettingsActivity settingsActivity = SettingsActivity.P;
                ((SettingsActivity) obj).K1();
                break;
            case 10:
                s71 s71Var = (s71) obj;
                if (s71Var.n == 1) {
                    if (!s71Var.g.g) {
                        s71Var.y1(11);
                    } else {
                        s71Var.z1(5);
                    }
                }
                break;
            case 11:
                CameraPreview cameraPreview = (CameraPreview) obj;
                int i4 = cameraPreview.h;
                try {
                    cameraOpen = Camera.open(i4);
                } catch (RuntimeException unused2) {
                    cameraPreview.g.onError(i4 == -1 ? 1000 : ((DevicePolicyManager) cameraPreview.e.getSystemService("device_policy")).getCameraDisabled(null) ? 1001 : 1002, null);
                }
                new Handler(Looper.getMainLooper()).post(new l91(cameraPreview, 25, cameraOpen));
                break;
            case 12:
                ao3.e((ViewResourceFrameLayout) obj, "StatusIndicatorCoordinator.initialize Runnable");
                break;
            case 13:
                qt2 qt2Var = (qt2) obj;
                qt2Var.getClass();
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                qt2Var.q = valueAnimatorOfFloat;
                valueAnimatorOfFloat.setInterpolator(h21.e);
                qt2Var.q.setDuration(150L);
                qt2Var.q.addUpdateListener(new it2(qt2Var, i));
                qt2Var.q.addListener(new lt2(qt2Var));
                qt2Var.q.start();
                break;
            case 14:
                ((ka3) obj).getClass();
                oj2.t(Profile.d()).notifyEvent("homepage_tile_clicked");
                oj2.U(0, 3, "NewTabPage.SuggestTiles.SelectedTileType");
                break;
            case 15:
                l23.a((l23) obj);
                break;
            case 16:
                mm0.f.this.e.onResult(1);
                break;
            case 17:
                Object obj2 = z23.A;
                ((z23.i) obj).a();
                break;
            case 18:
                ((m) obj).e(true);
                n3.X0("TabGridDialog.Exit");
                break;
            case 19:
                v0 v0Var = (v0) obj;
                if (v0Var.f.booleanValue() && (v0Var.c.u().c() instanceof g03)) {
                    g03 g03Var = (g03) v0Var.c.u().c();
                    ArrayList arrayList = new ArrayList();
                    Iterator<Integer> it = v0Var.e.c.iterator();
                    while (it.hasNext()) {
                        arrayList.addAll(g03Var.B(it.next().intValue()));
                    }
                    arrayListB = arrayList;
                } else {
                    arrayListB = v0Var.b();
                }
                if (!(v0Var instanceof f1)) {
                    Iterator<v0.b> it2 = v0Var.a.iterator();
                    while (true) {
                        b.a aVar = (b.a) it2;
                        if (aVar.hasNext()) {
                            ((v0.b) aVar.next()).a();
                        }
                    }
                }
                if (v0Var.f()) {
                    ((a1) v0Var.d).f();
                }
                v0Var.d(arrayListB);
                if (v0Var.f()) {
                    ((a1) v0Var.d).d(true);
                    ac2.d(((Tab) arrayListB.get(0)).getContext(), 1);
                }
                break;
            case 20:
                q1.this.o = null;
                break;
            case 21:
                ((o1) ((s1.d) obj)).i.k();
                break;
            case 22:
                ToolbarProgressBar toolbarProgressBar = (ToolbarProgressBar) obj;
                int i5 = ToolbarProgressBar.z;
                toolbarProgressBar.getClass();
                Object obj3 = ThreadUtils.a;
                if (!toolbarProgressBar.i) {
                    toolbarProgressBar.b(0.0f);
                    break;
                }
                break;
            case 23:
                ((ge3) obj).b.d();
                oj2.U(3, 5, "Autofill.TouchToFill.CreditCard.Outcome");
                break;
            case 24:
                d dVar = (d) obj;
                dVar.e.b(4, dVar.f);
                break;
            case 25:
                ((ek0) obj).b(0);
                C0173a.a(3);
                break;
            case 26:
                ((k) obj).b.h0 = false;
                break;
            case 27:
                C0215v0 c0215v0 = (C0215v0) obj;
                c0215v0.a(14);
                c0215v0.d.c(c0215v0.a, true);
                break;
            case 28:
                C0219x0 c0219x0 = (C0219x0) obj;
                PropertyModel propertyModel = c0219x0.i;
                PropertyModel.p pVar = C0221y0.e;
                int iH = propertyModel.h(pVar);
                if (iH == 1 || iH == 4) {
                    propertyModel.n(pVar, 3);
                    if (TextUtils.equals(c0219x0.k, c0219x0.m)) {
                        oj2.K(8);
                    } else if (TextUtils.equals(c0219x0.k, c0219x0.l)) {
                        oj2.K(4);
                    } else {
                        oj2.K(5);
                    }
                    if ((c0219x0.i.h(C0221y0.f) == 0 ? 1 : 0) != 0) {
                        pm2.b.a.removeKey("Chrome.AccountPickerBottomSheet.ConsecutiveActiveDismissalCount");
                    }
                    c0219x0.g.a(c0219x0.k, new dr(26, c0219x0));
                } else if (iH == 0) {
                    c0219x0.u();
                } else if (iH == 5) {
                    c0219x0.j.f(org.chromium.components.signin.b.c(c0219x0.k), c0219x0.f, new jb1(23, c0219x0));
                }
                break;
            default:
                km2 km2Var = (km2) obj;
                if (!km2Var.b()) {
                    SigninFirstRunFragment signinFirstRunFragment = (SigninFirstRunFragment) km2Var.h;
                    signinFirstRunFragment.b().R0(11);
                    signinFirstRunFragment.b().i1(km2Var.r);
                    pm2 pm2Var = pm2.b;
                    pm2Var.getClass();
                    pm2Var.a.n(System.currentTimeMillis(), "ntp.signin_promo_suppression_period_start");
                    z7 z7VarM = z7.m();
                    Profile profileD = Profile.d();
                    z7VarM.getClass();
                    if (!z7.v(profileD).b(0)) {
                        signinFirstRunFragment.H0();
                    } else {
                        km2Var.j.l(lm2.g, true);
                        im2 im2Var = new im2(km2Var);
                        z7 z7VarM2 = z7.m();
                        Profile profileD2 = Profile.d();
                        z7VarM2.getClass();
                        z7.D(profileD2).h(4, im2Var);
                    }
                    break;
                }
                break;
        }
    }
}

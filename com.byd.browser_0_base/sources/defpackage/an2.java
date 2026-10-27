package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import defpackage.qn;
import java.util.HashSet;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.privacy_sandbox.AdPersonalizationFragment;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.components.messages.b;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.a;
import org.chromium.components.page_info.d;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.ui.accessibility.AccessibilityState;
import org.chromium.ui.dragdrop.DropDataProviderImpl;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class an2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ an2(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean[][] zArr;
        int i = this.e;
        Object obj = this.f;
        int i2 = 1;
        switch (i) {
            case 0:
                b bVar = (b) obj;
                bVar.c.c(bVar.b);
                break;
            case 1:
                a aVar = (a) obj;
                ((PageInfoController) aVar.g).f(31);
                vq vqVar = (vq) aVar.e;
                n3 n3Var = new n3();
                Context context = vqVar.i;
                sk skVar = bq.a;
                boolean zM09VlOh_ = N.M09VlOh_("PrivacySandboxSettings4");
                Context context2 = vqVar.i;
                if (zM09VlOh_) {
                    PrivacySandboxSettingsBaseFragment.M0(context2, n3Var, 3);
                } else {
                    n3Var.b(context2, AdPersonalizationFragment.class, null);
                }
                break;
            case 2:
                d dVar = (d) obj;
                PageInfoController pageInfoController = (PageInfoController) dVar.g;
                pageInfoController.f(4);
                pageInfoController.e(dVar);
                break;
            case 3:
            default:
                ((eb3) obj).b();
                break;
            case 4:
                v12 v12Var = (v12) obj;
                u12 u12Var = v12Var.b;
                if (u12Var != null) {
                    if (u12Var.d != null && (zArr = u12Var.g) != null) {
                        TraceEvent.q("PlayerFrameBitmapState.releaseNotVisibleTiles", null);
                        for (int i3 = 0; i3 < u12Var.d.length; i3++) {
                            int i4 = 0;
                            while (true) {
                                Bitmap[] bitmapArr = u12Var.d[i3];
                                if (i4 < bitmapArr.length) {
                                    Bitmap bitmap = bitmapArr[i4];
                                    if (!zArr[i3][i4] && bitmap != null) {
                                        bitmap.recycle();
                                        u12Var.d[i3][i4] = null;
                                    }
                                    i4++;
                                }
                            }
                        }
                        TraceEvent.C("PlayerFrameBitmapState.releaseNotVisibleTiles");
                    }
                    v12Var.b(v12Var.b);
                    break;
                }
                break;
            case 5:
                c22.a((c22) obj);
                break;
            case 6:
                hh2 hh2Var = (hh2) obj;
                if (hh2Var.f.a()) {
                    hh2Var.a();
                    hh2Var.c.onResult(Boolean.TRUE);
                }
                break;
            case 7:
                kh2 kh2Var = (kh2) obj;
                if (kh2Var.f.a()) {
                    kh2Var.d.run();
                    y70 y70Var = kh2Var.b;
                    if (y70Var != null) {
                        y70Var.run();
                        kh2Var.b = null;
                        break;
                    }
                }
                break;
            case 8:
                rn rnVar = (rn) obj;
                rnVar.getClass();
                rnVar.a = ApplicationStatus.getStateForApplication() == 1 || ApplicationStatus.getStateForApplication() == 2;
                ApplicationStatus.e(new kp(i2, rnVar));
                if (rnVar.a) {
                    LauncherThread.b.postDelayed(rnVar.e, Math.round(Math.log(rnVar.d.nextDouble()) * (-1.0d) * 60000));
                }
                break;
            case 9:
                un unVar = ((ChildProcessLauncherHelperImpl) obj).g;
                int i5 = unVar.g.o;
                qn qnVar = unVar.g;
                qnVar.l();
                qn.e eVar = qnVar.g;
                if (eVar != null) {
                    qnVar.g = null;
                    eVar.a(qnVar);
                }
                break;
            case 10:
                xn.a((xn) obj);
                break;
            case 11:
                WebContentsAccessibilityImpl webContentsAccessibilityImpl = (WebContentsAccessibilityImpl) obj;
                webContentsAccessibilityImpl.getClass();
                int accessibilityServiceEventTypeMask = AccessibilityState.getAccessibilityServiceEventTypeMask();
                HashSet hashSet = new HashSet();
                while (accessibilityServiceEventTypeMask != 0) {
                    int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(accessibilityServiceEventTypeMask);
                    hashSet.add(Integer.valueOf(iNumberOfTrailingZeros));
                    accessibilityServiceEventTypeMask &= ~iNumberOfTrailingZeros;
                }
                org.chromium.content.browser.accessibility.b bVar2 = webContentsAccessibilityImpl.I;
                bVar2.c = hashSet;
                bVar2.g = true;
                break;
            case 12:
                ((DropDataProviderImpl) obj).a();
                break;
        }
    }
}

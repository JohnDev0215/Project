package defpackage;

import J.N;
import android.content.res.Resources;
import android.hardware.bydauto.motor.BYDAutoMotorDevice;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.tq0;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.feed.e;
import org.chromium.chrome.browser.firstrun.LightweightFirstRunActivity;
import org.chromium.chrome.browser.history.a;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.ntp.j;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.features.start_surface.StartSurfaceConfiguration;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ql0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ql0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        RecyclerView.a0 a0VarF;
        int i2 = this.e;
        boolean z = false;
        Object obj = this.f;
        switch (i2) {
            case 0:
                e eVar = (e) obj;
                bq2 bq2Var = eVar.g;
                if (bq2Var != null) {
                    cq2 cq2Var = (cq2) bq2Var;
                    View view = cq2Var.g;
                    em1 em1Var = cq2Var.d;
                    view.removeCallbacks(em1Var);
                    cq2Var.g.post(em1Var);
                }
                c cVar = eVar.e;
                if (cVar.r) {
                    PropertyModel.n nVar = xg2.a;
                    PropertyModel propertyModel = eVar.k;
                    if (propertyModel.k(nVar)) {
                        sk skVar = bq.a;
                        if (N.M09VlOh_("FeedHeaderStickToTop")) {
                            uf2 uf2Var = cVar.F;
                            if (uf2Var != null) {
                                int[] iArr = new int[2];
                                ao3.c(nr2.this.h, cVar.x, iArr);
                                i = iArr[1];
                            } else {
                                i = Integer.MAX_VALUE;
                            }
                            if ((i < cVar.u) && eVar.t) {
                                z = true;
                            }
                            propertyModel.l(xg2.m, z);
                            boolean z2 = !z;
                            qb3 qb3Var = cVar.G.get();
                            if (qb3Var != null && (qb3Var.b() || !z2)) {
                                qb3Var.f(z2);
                            }
                        }
                    }
                }
                eVar.l.a();
                eVar.s = true;
                break;
            case 1:
                ((e.b) obj).b();
                break;
            case 2:
                int i3 = org.chromium.chrome.browser.feed.webfeed.e.n;
                ((org.chromium.chrome.browser.feed.webfeed.e) obj).getClass();
                Log.i("cr_WFFollowIntroCtrl", "No intro: not allowed by feature engagement tracker");
                break;
            case 3:
                ((i7) ((ar3) obj).b).c(null);
                break;
            case 4:
                LightweightFirstRunActivity lightweightFirstRunActivity = (LightweightFirstRunActivity) obj;
                int i4 = LightweightFirstRunActivity.y0;
                lightweightFirstRunActivity.getClass();
                SharedPreferencesManager.getInstance().l("Chrome.FirstRun.SkippedByPolicy", true);
                lightweightFirstRunActivity.finish();
                lightweightFirstRunActivity.j2();
                lightweightFirstRunActivity.x0 = null;
                break;
            case 5:
                tq0.b bVar = (tq0.b) obj;
                if (bVar.h != null) {
                    bVar.g = false;
                    bVar.b.removeCallbacks(bVar.f);
                    bVar.i.cancel();
                    bVar.i.alpha(0.0f).setDuration(500L).withEndAction(new zc0(6, bVar)).start();
                    break;
                }
                break;
            case 6:
                iv0 iv0Var = (iv0) obj;
                boolean z3 = iv0Var.m;
                boolean zB = iv0Var.b();
                iv0Var.m = zB;
                if (zB != z3) {
                    iv0Var.c();
                }
                break;
            case 7:
                ((a) obj).P();
                break;
            case 8:
                rv0 rv0Var = (rv0) obj;
                if (!rv0Var.g) {
                    new PrefChangeRegistrar().a("homepage", rv0Var);
                    rv0Var.g = true;
                    rv0Var.c();
                }
                break;
            case 9:
                AsyncInitializationActivity asyncInitializationActivity = (AsyncInitializationActivity) obj;
                asyncInitializationActivity.Y = true;
                StartSurfaceConfiguration.b(SystemClock.elapsedRealtime() - asyncInitializationActivity.O1(), "FirstDrawCompletedTime", asyncInitializationActivity.R1());
                if (!asyncInitializationActivity.X) {
                    asyncInitializationActivity.V1();
                }
                break;
            case 10:
                nj njVar = (nj) obj;
                if (!njVar.d()) {
                    njVar.G0();
                    break;
                }
                break;
            case 11:
                sp spVar = (sp) obj;
                spVar.f = true;
                ArrayList arrayList = spVar.b;
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((Runnable) it.next()).run();
                    }
                    spVar.b = null;
                }
                break;
            case 12:
                ((RecyclerView) obj).N();
                break;
            case 13:
                PropertyModel propertyModel2 = ((pf1) obj).a.h;
                if (propertyModel2 != null) {
                    propertyModel2.l(wi.d, false);
                }
                break;
            case 14:
                jq jqVar = (jq) obj;
                ((org.chromium.components.messages.a) jqVar.e).e.a.c(jqVar.o);
                jqVar.o = -1;
                break;
            case 15:
                ((zs0) obj).g();
                break;
            case 16:
                NewTabPageLayout newTabPageLayout = (NewTabPageLayout) obj;
                int i5 = newTabPageLayout.I;
                if (i5 >= 0 && i5 < newTabPageLayout.F.k() && (a0VarF = newTabPageLayout.G.F(newTabPageLayout.I)) != null) {
                    newTabPageLayout.k(a0VarF.e, newTabPageLayout.I);
                    break;
                }
                break;
            case 17:
                ((j) obj).h();
                break;
            case 18:
                iq1 iq1Var = (iq1) obj;
                if (iq1Var.n == 1) {
                    if (!iq1Var.h || iq1Var.i != iq1Var.g) {
                        boolean z4 = iq1Var.i;
                        iq1Var.g = z4;
                        iq1Var.h = true;
                        iq1Var.l.onResult(Boolean.valueOf(z4));
                    }
                    break;
                }
                break;
            case 19:
                wt2 wt2Var = (wt2) obj;
                int i6 = wt2Var.L + BYDAutoMotorDevice.MOTOR_POS_MIN;
                su1 su1Var = wt2Var.F;
                su1Var.getClass();
                oj2.t(Profile.d()).notifyEvent("permission_request_shown");
                pk3 pk3Var = (pk3) su1Var.a;
                View view2 = (View) su1Var.b;
                Resources resources = view2.getContext().getResources();
                int i7 = R$string.page_info_iph;
                hw0 hw0Var = new hw0(resources, "IPH_PageInfo", i7, i7);
                hw0Var.o = i6;
                hw0Var.j = view2;
                pk3Var.a(hw0Var.a());
                break;
            case 20:
                StatusView statusView = (StatusView) obj;
                statusView.o = false;
                statusView.b();
                statusView.f();
                break;
            case 21:
                Iterator<vr1> it2 = ((org.chromium.chrome.browser.omnibox.suggestions.c) obj).k.iterator();
                while (true) {
                    b.a aVar = (b.a) it2;
                    if (aVar.hasNext()) {
                        ((vr1) aVar.next()).k0();
                    }
                    break;
                }
                break;
            case 22:
                OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = (OmniboxSuggestionsDropdown) obj;
                int i8 = OmniboxSuggestionsDropdown.g1;
                omniboxSuggestionsDropdown.announceForAccessibility(omniboxSuggestionsDropdown.getContext().getString(R$string.accessibility_omnibox_suggested_items, Integer.valueOf(omniboxSuggestionsDropdown.T0.k())));
                break;
            case 23:
                vq vqVar = (vq) obj;
                d.g(vqVar.f, vqVar.m);
                break;
            case 24:
                ((hs2) obj).k(3);
                break;
            case 25:
                org.chromium.chrome.browser.password_entry_edit.a aVar2 = (org.chromium.chrome.browser.password_entry_edit.a) obj;
                CredentialEntryFragmentViewBase credentialEntryFragmentViewBase = aVar2.a;
                ((hu0) aVar2.e).c(credentialEntryFragmentViewBase.D(), credentialEntryFragmentViewBase.D().getString(R$string.help_context_passwords), null, Profile.d());
                break;
            case 26:
            default:
                d52 d52Var = (d52) obj;
                d52Var.h.scrollTo(0, d52Var.i.getTop());
                break;
            case 27:
                yy1 yy1Var = (yy1) obj;
                xy1 xy1Var = yy1Var.a;
                if (xy1Var != null) {
                    xy1Var.run();
                    yy1Var.a = null;
                    break;
                }
                break;
            case 28:
                az1 az1Var = (az1) obj;
                az1Var.o = 3;
                az1Var.g.run();
                break;
        }
    }
}

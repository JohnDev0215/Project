package defpackage;

import J.N;
import android.app.NotificationChannel;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import defpackage.q82;
import defpackage.xl2;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tasks.tab_management.CouponCardView;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.c0;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;
import org.chromium.chrome.browser.toolbar.top.m;
import org.chromium.chrome.browser.touch_to_fill.TouchToFillBridge;
import org.chromium.chrome.browser.ui.BottomContainer;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.chrome.browser.ui.signin.f;
import org.chromium.chrome.browser.usage_stats.UsageStatsConsentActivity;
import org.chromium.components.browser_ui.site_settings.GroupedWebsitesSettings;
import org.chromium.components.browser_ui.site_settings.d;
import org.chromium.components.payments.Address;
import org.chromium.components.payments.PayerData;
import org.chromium.components.payments.a;
import org.chromium.components.payments.e;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class x10 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ x10(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        Address address;
        int iRound;
        int i = this.e;
        long jMax = 0;
        int i2 = 0;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                ((CriticalPersistedTabData) obj2).z = false;
                break;
            case 1:
                Tab tab = (Tab) obj2;
                Callback callback = (Callback) obj;
                int i3 = xl2.t;
                if (!tab.isDestroyed()) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - CriticalPersistedTabData.m(tab).r;
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    boolean zP = a8.P();
                    int iM37SqSAy = xl2.v;
                    if (zP) {
                        sk skVar = bq.a;
                        iM37SqSAy = N.M37SqSAy("CommercePriceTracking", "price_tracking_stale_tab_threshold_seconds", iM37SqSAy);
                    }
                    if (jCurrentTimeMillis <= timeUnit.toMillis(iM37SqSAy)) {
                        xl2.b bVar = l02.d(tab, xl2.class) == null ? null : new xl2.b((xl2) l02.d(tab, xl2.class));
                        GURL url = tab.getUrl();
                        vl2 vl2Var = new vl2(callback, tab, bVar, i2);
                        OptimizationGuideBridge optimizationGuideBridgeA = xl2.a.a.a();
                        gx gxVar = new gx(21, vl2Var);
                        Object obj3 = ThreadUtils.a;
                        long j = optimizationGuideBridgeA.a;
                        if (j != 0) {
                            N.MqwRdGjQ(j, url, 15, gxVar);
                        } else {
                            gxVar.j(0, null);
                        }
                    }
                }
                callback.onResult(null);
                break;
            case 2:
                z53 z53Var = (z53) obj2;
                z51 z51Var = (z51) obj;
                z51 z51Var2 = z53Var.j;
                if (z51Var2 != null) {
                    z51Var2.f(z53Var.k);
                }
                z53Var.j = z51Var;
                rm0 rm0Var = new rm0(new y53(z53Var));
                z53Var.k = rm0Var;
                z51Var.j(rm0Var);
                z53Var.a();
                break;
            case 3:
                TabListRecyclerView.b bVar2 = (TabListRecyclerView.b) obj;
                ((LinearLayoutManager) ((c0) obj2).f.getLayoutManager()).g1(bVar2.a, bVar2.b);
                break;
            case 4:
                CouponCardView couponCardView = (CouponCardView) obj2;
                a10 a10Var = (a10) obj;
                if (a10Var == null || a10Var.n() == null) {
                    couponCardView.setVisibility(8);
                } else {
                    couponCardView.setCouponString(a10Var.n());
                    couponCardView.setVisibility(0);
                }
                break;
            case 5:
                ((Boolean) obj).booleanValue();
                ((s1) obj2).l();
                break;
            case 6:
                ((f91) obj2).b((Tab) obj);
                break;
            case 7:
                ToolbarPhone toolbarPhone = (ToolbarPhone) obj2;
                toolbarPhone.c0 = ((Float) obj).floatValue();
                toolbarPhone.h0();
                toolbarPhone.g0();
                break;
            case 8:
                km1 km1Var = (km1) obj2;
                int i4 = ToolbarTablet.k0;
                float fMax = Math.max(1.0f - (((Float) obj).floatValue() * 2.5f), 0.0f);
                km1Var.k(fMax);
                km1Var.f(fMax);
                break;
            case 9:
                m mVar = (m) obj2;
                mVar.k = ((Boolean) obj).booleanValue();
                mVar.u();
                break;
            case 10:
                ke3 ke3Var = (ke3) obj2;
                int iIntValue = ((Integer) obj).intValue();
                PropertyModel propertyModel = ke3Var.c;
                PropertyModel.n nVar = le3.a;
                if (propertyModel.k(nVar)) {
                    ke3Var.c.l(nVar, false);
                    oj2.U(iIntValue, 10, "PasswordManager.TouchToFill.DismissalReason");
                    oj2.U(1, 4, "PasswordManager.TouchToFill.UserAction");
                    long j2 = ((TouchToFillBridge) ke3Var.b).a;
                    if (j2 != 0) {
                        N.MO$_q9pf(j2);
                    }
                    break;
                }
                break;
            case 11:
                te3 te3Var = (te3) obj2;
                Pair pair = (Pair) obj;
                if (te3Var.c == 3) {
                    float fFloatValue = ((Float) pair.first).floatValue();
                    AccessibilityManager accessibilityManager = (AccessibilityManager) xz.a.getSystemService("accessibility");
                    if ((!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) && ue3.b != (iRound = Math.round(fFloatValue * 100.0f))) {
                        ue3.b = iRound;
                        ue3.a.j(String.format("Trace buffer usage: %s%%", Integer.valueOf(iRound)));
                        ue3.b(ue3.a.e());
                    }
                    PostTask.b(rh3.a, new d92(17, te3Var), 1000L);
                    break;
                }
                break;
            case 12:
                BottomContainer bottomContainer = (BottomContainer) obj2;
                bottomContainer.setTranslationY(bottomContainer.i);
                break;
            case 13:
                r1 r1Var = (r1) obj2;
                Account account = (Account) obj;
                r1Var.getClass();
                if (SystemClock.elapsedRealtime() - r1Var.t > 500) {
                    r1Var.a(account);
                    break;
                }
                break;
            case 14:
                f fVar = (f) obj2;
                fVar.h = (Boolean) obj;
                if (fVar.a == 2) {
                    d92 d92Var = fVar.i;
                    if (d92Var != null) {
                        fVar.f.removeCallbacks(d92Var);
                        fVar.i = null;
                    }
                    fVar.b();
                }
                break;
            case 15:
                km2 km2Var = (km2) obj2;
                km2Var.m = 1;
                km2Var.a();
                break;
            case 16:
                dt2 dt2Var = (dt2) obj2;
                z51 z51Var3 = (z51) obj;
                dt2Var.g = z51Var3;
                ct2 ct2Var = new ct2(dt2Var);
                dt2Var.l = ct2Var;
                z51Var3.j(ct2Var);
                if (dt2Var.g.d() == 16 && !dt2Var.n) {
                    dt2Var.n = true;
                    dt2Var.g();
                    break;
                }
                break;
            case 17:
                ob3 ob3Var = (ob3) obj2;
                Map<String, String> map = (Map) obj;
                ob3Var.getClass();
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    jMax = Math.max(jMax, Long.valueOf(it.next().getKey()).longValue());
                }
                ob3Var.a.b(map);
                break;
            case 18:
                UsageStatsConsentActivity usageStatsConsentActivity = (UsageStatsConsentActivity) obj2;
                int i5 = UsageStatsConsentActivity.I;
                usageStatsConsentActivity.getClass();
                usageStatsConsentActivity.setResult(((Boolean) obj).booleanValue() ? -1 : 0);
                usageStatsConsentActivity.finish();
                break;
            case 19:
                gi0 gi0Var = ((fk3) obj2).b;
                gi0Var.getClass();
                s62 s62Var = new s62();
                gi0Var.b.h(new p62(gi0Var, 13, s62Var), new m62(7));
                s62Var.a(new xo(11));
                break;
            case 20:
                ((nr2) obj2).m((Profile) obj);
                break;
            case 21:
                zb2.b((LoadUrlParams) obj, false, Boolean.FALSE, ((yr2) obj2).n.get());
                break;
            case 22:
                g73 g73Var = (g73) obj2;
                q82.a aVar = (q82.a) obj;
                g73Var.getClass();
                String str = aVar.a;
                sr1 sr1Var = g73Var.c.e;
                if (sr1Var != null) {
                    sr1Var.e(str, aVar.b);
                }
                break;
            case 23:
                cv1 cv1Var = (cv1) obj2;
                View view = cv1Var.g;
                d92 d92Var2 = cv1Var.f;
                view.removeCallbacks(d92Var2);
                cv1Var.g.postDelayed(d92Var2, 5000L);
                break;
            case 24:
                fv1 fv1Var = (fv1) obj2;
                int iIntValue2 = ((Integer) obj).intValue();
                if (((double) Math.round(Math.abs(iv1.b(iIntValue2) - Math.pow(1.2000000476837158d, fv1Var.d)) * 100.0d)) / 100.0d <= 0.03d) {
                    iIntValue2 = iv1.c(fv1Var.d);
                }
                n3.i1(fv1Var.b, iv1.a(iIntValue2));
                fv1Var.a.n(gv1.h, iIntValue2);
                fv1Var.c(iv1.a(iIntValue2));
                fv1Var.c = iv1.b(iIntValue2);
                break;
            case 25:
                ((bb0) obj2).c();
                break;
            case 26:
                ((so1) obj2).e((NotificationChannel) obj);
                break;
            case 27:
                GroupedWebsitesSettings groupedWebsitesSettings = (GroupedWebsitesSettings) obj2;
                int i6 = GroupedWebsitesSettings.k0;
                groupedWebsitesSettings.getClass();
                if (((Boolean) obj).booleanValue()) {
                    d.a(((kr) groupedWebsitesSettings.h0).b, groupedWebsitesSettings.i0, groupedWebsitesSettings.j0);
                }
                break;
            case 28:
                dt3 dt3Var = (dt3) obj2;
                Drawable drawable = (Drawable) obj;
                if (drawable == null) {
                    dt3Var.getClass();
                } else {
                    dt3Var.L(drawable);
                }
                break;
            default:
                a aVar2 = (a) obj2;
                a.C0138a c0138a = (a.C0138a) obj;
                aVar2.getClass();
                Object obj4 = ThreadUtils.a;
                int i7 = c0138a.a;
                Intent intent = c0138a.b;
                rr3 rr3Var = aVar2.w;
                if (intent == null) {
                    aVar2.z("Payment app returned an invalid result. Missing intent data.");
                } else if (intent.getExtras() == null) {
                    aVar2.z("Payment app returned an invalid result. Missing intent extras.");
                } else if (i7 == 0) {
                    aVar2.z("Payment app returned RESULT_CANCELED code. This is how payment apps can close their activity programmatically.");
                } else if (i7 == -1) {
                    String string = intent.getExtras().getString("details");
                    if (string == null) {
                        string = intent.getExtras().getString("instrumentDetails");
                    }
                    if (!TextUtils.isEmpty(string)) {
                        String string2 = intent.getExtras().getString("methodName");
                        if (TextUtils.isEmpty(string2)) {
                            aVar2.z("Payment app returned invalid response. Missing field \"methodName\".");
                        } else if (rr3Var != null) {
                            boolean z = rr3Var.d;
                            if (z) {
                                Bundle bundle = intent.getExtras().getBundle("shippingAddress");
                                if (bundle == null || bundle.isEmpty()) {
                                    aVar2.z("Payment app returned invalid shipping address in response.");
                                } else {
                                    address = Address.a(bundle);
                                }
                            } else {
                                address = new Address();
                            }
                            Address address2 = address;
                            boolean z2 = rr3Var.a;
                            String string3 = z2 ? intent.getExtras().getString("payerName", "") : "";
                            if (z2 && TextUtils.isEmpty(string3)) {
                                aVar2.z("Payment app returned invalid response. Missing field \"payerName\".");
                            } else {
                                boolean z3 = rr3Var.c;
                                String string4 = z3 ? intent.getExtras().getString("payerPhone", "") : "";
                                if (z3 && TextUtils.isEmpty(string4)) {
                                    aVar2.z("Payment app returned invalid response. Missing field \"payerPhone\".");
                                } else {
                                    boolean z4 = rr3Var.b;
                                    String string5 = z4 ? intent.getExtras().getString("payerEmail", "") : "";
                                    if (z4 && TextUtils.isEmpty(string5)) {
                                        aVar2.z("Payment app returned invalid response. Missing field \"payerEmail\".");
                                    } else {
                                        String string6 = z ? intent.getExtras().getString("shippingOptionId", "") : "";
                                        if (z && TextUtils.isEmpty(string6)) {
                                            aVar2.z("Payment app returned invalid response. Missing field \"shipping option\".");
                                        } else {
                                            ((e) aVar2.s).p(string2, string, new PayerData(string3, string4, string5, address2, string6));
                                            aVar2.s = null;
                                        }
                                    }
                                }
                            }
                        } else {
                            ((e) aVar2.s).p(string2, string, new PayerData());
                            aVar2.s = null;
                        }
                    } else {
                        aVar2.z("Payment app returned invalid response. Missing field \"details\".");
                    }
                } else {
                    aVar2.z(String.format(Locale.US, "Payment app returned unrecognized activity result %d.", Integer.valueOf(i7)));
                }
                break;
        }
    }
}

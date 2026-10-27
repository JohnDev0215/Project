package defpackage;

import J.N;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import defpackage.ax0;
import defpackage.ij0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.tracing.TracingNotificationService;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.components.browser_ui.site_settings.WebsiteRowPreference;
import org.chromium.components.browser_ui.widget.async_image.AsyncImageView;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.a;
import org.chromium.components.payments.e;
import org.chromium.components.payments.f;
import org.chromium.components.signin.b;
import org.chromium.components.subresource_filter.AdsBlockedDialog;
import org.chromium.content.browser.input.SelectPopup;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class se3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ se3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v3, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v4, types: [T, y70] */
    /* JADX WARN: Type inference failed for: r5v5, types: [T, tg3] */
    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        pr3 pr3Var = null;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                ((te3) obj2).b(5);
                Context context = xz.a;
                np1 np1VarA = ue3.a();
                np1VarA.k("Chrome trace is complete");
                np1VarA.j("The trace is ready. Open tracing settings to share.");
                np1VarA.o(false);
                String name = TracingSettings.class.getName();
                Intent intent = new Intent();
                intent.setClass(context, SettingsActivity.class);
                if (!(context instanceof Activity)) {
                    intent.addFlags(268435456);
                    intent.addFlags(67108864);
                }
                intent.putExtra("show_fragment", name);
                np1VarA.d(0, "Open tracing settings", PendingIntent.getActivity(context, 0, intent, q11.d(false) | 134217728));
                Intent intent2 = new Intent(context, (Class<?>) TracingNotificationService.class);
                intent2.setAction("org.chromium.chrome.browser.tracing.DISCARD_TRACE");
                np1VarA.a.A.deleteIntent = PendingIntent.getService(context, 0, intent2, q11.d(false) | 134217728);
                ue3.b(np1VarA.e());
                break;
            case 1:
                ui uiVar = (ui) obj2;
                lr2 lr2Var = (lr2) obj;
                uiVar.getClass();
                si siVar = new si(uiVar, lr2Var);
                uiVar.k = siVar;
                lr2Var.q(siVar);
                break;
            case 2:
                ((y13) obj).m(((zy0) obj2).i);
                break;
            case 3:
                nc2 nc2Var = (nc2) obj2;
                h61 h61Var = (h61) obj;
                nc2Var.n(h61Var);
                nc2Var.i0.r(h61Var);
                break;
            case 4:
                SyncConsentFragmentBase syncConsentFragmentBase = (SyncConsentFragmentBase) obj2;
                Intent intent3 = (Intent) obj;
                int i2 = SyncConsentFragmentBase.r0;
                syncConsentFragmentBase.getClass();
                if (intent3 != null) {
                    oj2.L(1);
                    syncConsentFragmentBase.G0(intent3, 1);
                } else {
                    oj2.L(3);
                    FragmentActivity fragmentActivityD = syncConsentFragmentBase.D();
                    Intent intent4 = new Intent("android.settings.SYNC_SETTINGS");
                    ComponentName componentName = q11.a;
                    try {
                        fragmentActivityD.startActivity(intent4, null);
                        break;
                    } catch (ActivityNotFoundException unused) {
                    }
                    syncConsentFragmentBase.j0 = false;
                    if (syncConsentFragmentBase.i0) {
                        syncConsentFragmentBase.D().finish();
                    }
                }
                break;
            case 5:
                ((C0219x0) obj2).a((List) obj);
                break;
            case 6:
                ((f1) obj2).a((List) obj);
                break;
            case 7:
                km2 km2Var = (km2) obj2;
                km2Var.m = 2;
                km2Var.a();
                break;
            case 8:
                ((zw2) obj2).c.b((List) obj);
                break;
            case 9:
                fk3 fk3Var = (fk3) obj2;
                final List<String> list = (List) obj;
                fk3Var.getClass();
                Object obj3 = ThreadUtils.a;
                fk3Var.c(list, false);
                final zw2 zw2Var = fk3Var.d;
                zw2Var.getClass();
                final s62<Void> s62Var = new s62<>();
                zw2Var.d.g(new Callback() { // from class: xw2
                    public final /* synthetic */ boolean f = false;

                    @Override // org.chromium.base.Callback
                    public final void onResult(Object obj4) {
                        final zw2 zw2Var2 = zw2Var;
                        zw2Var2.getClass();
                        final boolean z = this.f;
                        final List list2 = list;
                        final s62 s62Var2 = s62Var;
                        zw2Var2.c.h(new Callback() { // from class: yw2
                            @Override // org.chromium.base.Callback
                            public final void onResult(Object obj5) {
                                boolean z2 = z;
                                List list3 = list2;
                                s62 s62Var3 = s62Var2;
                                List list4 = (List) obj5;
                                zw2 zw2Var3 = zw2Var2;
                                zw2Var3.getClass();
                                ArrayList arrayList = new ArrayList(list4);
                                if (z2) {
                                    z7.d0(4);
                                    arrayList.addAll(list3);
                                } else {
                                    z7.d0(5);
                                    arrayList.removeAll(list3);
                                }
                                String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
                                yf1 yf1Var = new yf1(list4, list3, s62Var3, zw2Var3, z2);
                                UsageStatsBridge usageStatsBridge = zw2Var3.a;
                                N.M2UQ4Zbr(usageStatsBridge.b, usageStatsBridge, strArr, yf1Var);
                            }
                        }, new fm3(9));
                    }
                });
                zw2Var.d = s62Var;
                break;
            case 10:
                final ug3 ug3Var = (ug3) obj2;
                ug3Var.getClass();
                for (final Tutorial tutorial : (List) obj) {
                    HashMap mapE = PropertyModel.e(og3.e);
                    PropertyModel.r<String> rVar = og3.a;
                    ?? r5 = tutorial.b;
                    int i3 = zj.a;
                    PropertyModel.g gVar = new PropertyModel.g();
                    gVar.a = r5;
                    mapE.put(rVar, gVar);
                    PropertyModel.r<String> rVar2 = og3.b;
                    ?? A = im3.a(tutorial.i);
                    PropertyModel.g gVar2 = new PropertyModel.g();
                    gVar2.a = A;
                    mapE.put(rVar2, gVar2);
                    PropertyModel.r<Runnable> rVar3 = og3.c;
                    ?? y70Var = new y70(ug3Var, 18, tutorial);
                    PropertyModel.g gVar3 = new PropertyModel.g();
                    gVar3.a = y70Var;
                    mapE.put(rVar3, gVar3);
                    PropertyModel.r<AsyncImageView.a> rVar4 = og3.d;
                    ?? r52 = new AsyncImageView.a() { // from class: tg3
                        @Override // org.chromium.components.browser_ui.widget.async_image.AsyncImageView.a
                        public final Runnable a(int i4, int i5, gp gpVar) {
                            ug3 ug3Var2 = ug3Var;
                            ug3Var2.getClass();
                            ug3Var2.d.d(ax0.a.a(i4, i5, tutorial.f, "VideoTutorialsList"), new xm1(ug3Var2, 14, gpVar));
                            return new qe3(21);
                        }
                    };
                    PropertyModel.g gVar4 = new PropertyModel.g();
                    gVar4.a = r52;
                    mapE.put(rVar4, gVar4);
                    ug3Var.b.t(new xa1(3, new PropertyModel(mapE)));
                }
                break;
            case 11:
                ((am3) obj2).e((Tutorial) obj);
                break;
            case 12:
                ((jn2) obj2).g.p(kn2.b, (Drawable) obj);
                break;
            case 13:
                WebsiteRowPreference websiteRowPreference = (WebsiteRowPreference) obj2;
                Drawable drawable = (Drawable) obj;
                if (drawable != null) {
                    websiteRowPreference.L(drawable);
                } else {
                    websiteRowPreference.getClass();
                }
                break;
            case 14:
                bw bwVar = (bw) obj2;
                super/*lj*/.r(Integer.valueOf(bwVar.u()));
                break;
            case 15:
                ((v73) obj2).e.r(Boolean.valueOf(((Integer) obj).intValue() != 0));
                break;
            case 16:
                w11 w11Var = (w11) obj2;
                ij0.a aVar = (ij0.a) obj;
                ArrayList arrayList = w11.f;
                w11Var.getClass();
                N.MzEZdCyU(w11Var.b, aVar.a == 2 ? aVar.d : null);
                break;
            case 17:
                ((a) obj2).z((String) obj);
                break;
            case 18:
                e eVar = (e) obj2;
                eVar.getClass();
                if (((Boolean) obj).booleanValue()) {
                    PaymentApp paymentAppQ = eVar.B.f.q();
                    eVar.m(paymentAppQ, new f(paymentAppQ, eVar.t.d()));
                } else {
                    eVar.g.a(0);
                    eVar.l(6, "The operation either timed out or was not allowed. See: https://www.w3.org/TR/webauthn-2/#sctn-privacy-considerations-client.");
                }
                eVar.D = null;
                break;
            case 19:
                List list2 = (List) obj2;
                kz1 kz1Var = (kz1) obj;
                if (kz1Var != null) {
                    ry1 ry1Var = kz1Var.c;
                    pr3Var = new pr3(ry1Var != null ? new nr3(ry1Var.b, ry1Var.c) : null);
                }
                list2.add(pr3Var);
                break;
            case 20:
                org.chromium.components.signin.a aVar2 = (org.chromium.components.signin.a) obj2;
                aVar2.getClass();
                new C0211t0(aVar2, b.f((List) obj)).c(i9.f);
                break;
            case 21:
                N.MnOtNR6U(((AdsBlockedDialog) obj2).e);
                break;
            case 22:
                ((SelectPopup) obj2).d((int[]) obj);
                break;
            case 23:
                ((org.chromium.ui.base.b) obj2).f((Uri) obj);
                break;
            default:
                org.chromium.ui.modelutil.b bVar = (org.chromium.ui.modelutil.b) obj2;
                bVar.k = obj;
                bVar.j = false;
                bVar.b();
                break;
        }
    }
}

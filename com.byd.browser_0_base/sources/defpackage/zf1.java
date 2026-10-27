package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.motor.BYDAutoMotorDevice;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.vf1;
import defpackage.wu1;
import defpackage.xt2;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.chromium.base.Callback;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.merchant_viewer.BottomSheetToolbarView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.components.thinwebview.internal.ThinWebViewImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.b;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.base.c;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MerchantTrustSignalsCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zf1 implements wu1.a, vf1.a {
    public final cg1 a;
    public final tf1 b;
    public final nf1 c;
    public final Context d;
    public final wf1 e;
    public final ag1 f;
    public final dg1 g;
    public final zp1<Profile> h;
    public final WindowAndroid i;
    public final zp1<Tab> j;
    public a k;

    /* JADX INFO: compiled from: MerchantTrustSignalsCoordinator.java */
    public interface a {
    }

    public zf1() {
        throw null;
    }

    public zf1(AppCompatActivity appCompatActivity, g3 g3Var, f fVar, View view, vg1 vg1Var, z2 z2Var, zp1 zp1Var, wf1 wf1Var, c cVar) {
        tf1 tf1Var = new tf1(vg1Var, wf1Var, z2Var);
        ag1 ag1Var = new ag1();
        nf1 nf1Var = new nf1(appCompatActivity, g3Var, fVar, view, wf1Var, cVar, zp1Var);
        dg1 dg1Var = new dg1(zp1Var);
        this.d = appCompatActivity;
        this.f = ag1Var;
        this.e = wf1Var;
        this.g = dg1Var;
        this.h = zp1Var;
        this.i = g3Var;
        this.j = z2Var;
        this.a = new cg1(z2Var, new c01(13, this), wf1Var);
        this.b = tf1Var;
        this.c = nf1Var;
    }

    @Override // wu1.a
    public final void a(ShoppingService.MerchantInfo merchantInfo) {
        b(merchantInfo.c, 2, null);
        oj2.t(this.h.get()).notifyEvent("page_info_store_info_row_clicked");
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [T, l10] */
    public final void b(GURL gurl, int i, String str) {
        this.e.getClass();
        oj2.U(i, 3, "MerchantTrust.BottomSheet.OpenSource");
        String string = this.d.getResources().getString(R$string.merchant_viewer_preview_sheet_title);
        uu2 uu2Var = new uu2(this, i, str);
        nf1 nf1Var = this.c;
        lf1 lf1Var = nf1Var.k;
        ji jiVar = nf1Var.f;
        qf1 qf1Var = nf1Var.i;
        if (lf1Var == null) {
            Context context = nf1Var.e;
            nf1Var.n = new BottomSheetToolbarView(context);
            HashMap mapE = PropertyModel.e(wi.m);
            PropertyModel.r<Runnable> rVar = wi.h;
            ?? l10Var = new l10(25, nf1Var);
            int i2 = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = l10Var;
            mapE.put(rVar, gVar);
            PropertyModel.p pVar = wi.i;
            int i3 = R$drawable.ic_logo_googleg_24dp;
            PropertyModel.d dVar = new PropertyModel.d();
            dVar.a = i3;
            mapE.put(pVar, dVar);
            PropertyModel.n nVar = wi.k;
            PropertyModel.a aVar = new PropertyModel.a();
            aVar.a = true;
            mapE.put(nVar, aVar);
            PropertyModel.n nVar2 = wi.l;
            PropertyModel.a aVar2 = new PropertyModel.a();
            aVar2.a = false;
            mapE.put(nVar2, aVar2);
            PropertyModel propertyModel = new PropertyModel(mapE);
            nf1Var.o = propertyModel;
            nf1Var.p = j.b(propertyModel, nf1Var.n, new bg(11));
            ThinWebViewImpl thinWebViewImpl = new ThinWebViewImpl(context, new t83(), nf1Var.q);
            nf1Var.m = thinWebViewImpl;
            thinWebViewImpl.getView().setLayoutParams(new FrameLayout.LayoutParams(-1, ((int) (jiVar.b() * 0.9f)) - nf1Var.n.a));
            ((ViewGroup.MarginLayoutParams) nf1Var.m.getView().getLayoutParams()).topMargin = nf1Var.n.a;
            ThinWebViewImpl thinWebViewImpl2 = nf1Var.m;
            qf1Var.h = nf1Var.o;
            WebContents webContents = (WebContents) N.MBvQ4D5k(Profile.d(), false, false);
            qf1Var.i = webContents;
            ContentView contentView = new ContentView(qf1Var.a, webContents);
            qf1Var.j = contentView;
            qf1Var.i.i(new ViewAndroidDelegate(contentView), qf1Var.j, qf1Var.b, new b());
            N.MpNVVknM(qf1Var.i, false);
            qf1Var.l = new of1(qf1Var, qf1Var.i);
            qf1Var.k = new pf1(qf1Var);
            ContentView contentView2 = qf1Var.j;
            if (contentView2 != null && contentView2.getParent() != null) {
                ((ViewGroup) qf1Var.j.getParent()).removeView(qf1Var.j);
            }
            thinWebViewImpl2.a(qf1Var.i, qf1Var.j, qf1Var.k);
            nf1Var.k = new lf1(nf1Var.n.b, nf1Var.m.getView(), new yq2(14, nf1Var), new zc0(15, nf1Var));
            mf1 mf1Var = new mf1(nf1Var, uu2Var);
            nf1Var.j = mf1Var;
            jiVar.g(mf1Var);
            nf1Var.g.addOnLayoutChangeListener(nf1Var);
        }
        qf1Var.a(gurl);
        qf1Var.h.p(wi.b, string);
        jiVar.d(nf1Var.k, true);
    }

    public final void c(String str, final boolean z) {
        a aVar;
        if (!N.MRiRQ_Ey(N.MDKqWa7S(0)) || (aVar = this.k) == null || str == null) {
            return;
        }
        Context context = this.d;
        Resources resources = context.getResources();
        int i = R$drawable.ic_storefront_blue;
        Resources.Theme theme = context.getTheme();
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        Drawable drawable = resources.getDrawable(i, theme);
        final int i2 = R$string.merchant_viewer_omnibox_icon_iph;
        final wt2 wt2Var = (wt2) aVar;
        if (this.i == wt2Var.H) {
            org.chromium.chrome.browser.omnibox.f fVar = wt2Var.A;
            if (!str.equals(fVar.n()) || fVar.isIncognito()) {
                return;
            }
            wt2Var.c();
            xt2.a aVar2 = new xt2.a(drawable, false);
            aVar2.e = 1;
            aVar2.f = new Runnable() { // from class: vt2
                @Override // java.lang.Runnable
                public final void run() {
                    wt2 wt2Var2 = wt2Var;
                    if (!z) {
                        wt2Var2.getClass();
                        return;
                    }
                    int i3 = wt2Var2.L + BYDAutoMotorDevice.MOTOR_POS_MIN;
                    su1 su1Var = wt2Var2.F;
                    pk3 pk3Var = (pk3) su1Var.a;
                    View view = (View) su1Var.b;
                    Resources resources2 = view.getContext().getResources();
                    int i4 = i2;
                    hw0 hw0Var = new hw0(resources2, "IPH_PageInfoStoreInfo", i4, i4);
                    hw0Var.o = i3;
                    hw0Var.j = view;
                    hw0Var.e = true;
                    pk3Var.a(hw0Var.a());
                }
            };
            wt2Var.e.p(xt2.l, aVar2);
            wt2Var.D.postDelayed(new ut2(wt2Var, 1), wt2Var.L);
            wt2Var.f101J = true;
            wt2Var.G.a(4);
        }
    }

    public final void d(final rf1 rf1Var, final ShoppingService.MerchantInfo merchantInfo, boolean z) {
        boolean zM6bsIDpc;
        long j;
        GURL gurl;
        NavigationHandle navigationHandle = rf1Var.b;
        final String strJ = (navigationHandle == null || (gurl = navigationHandle.e) == null) ? null : gurl.j();
        PropertyModel.b bVar = new PropertyModel.b(ng1.H);
        bVar.c(ng1.a, 7);
        PropertyModel.r<Drawable> rVar = ng1.l;
        Context context = this.d;
        Resources resources = context.getResources();
        int iM37SqSAy = 0;
        if (a8.P()) {
            sk skVar = bq.a;
            zM6bsIDpc = N.M6bsIDpc("CommerceMerchantViewer", "trust_signals_message_use_google_icon", false);
        } else {
            zM6bsIDpc = false;
        }
        int i = zM6bsIDpc ? R$drawable.ic_logo_googleg_24dp : R$drawable.ic_storefront_blue;
        Resources.Theme theme = context.getTheme();
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        bVar.e(rVar, resources.getDrawable(i, theme));
        bVar.c(ng1.p, 0);
        PropertyModel.r<String> rVar2 = ng1.f;
        Resources resources2 = context.getResources();
        if (a8.P()) {
            sk skVar2 = bq.a;
            iM37SqSAy = N.M37SqSAy("CommerceMerchantViewer", "trust_signals_message_title_ui", 0);
        }
        int iM37SqSAy2 = 1;
        bVar.e(rVar2, resources2.getString(iM37SqSAy == 1 ? R$string.merchant_viewer_message_title_see_reviews : R$string.merchant_viewer_message_title));
        PropertyModel.r<CharSequence> rVar3 = ng1.h;
        if (a8.P()) {
            sk skVar3 = bq.a;
            iM37SqSAy2 = N.M37SqSAy("CommerceMerchantViewer", "trust_signals_message_description_ui", 1);
        }
        bVar.e(rVar3, vf1.a(context, merchantInfo, iM37SqSAy2));
        bVar.e(ng1.c, context.getResources().getString(R$string.merchant_viewer_message_action));
        bVar.e(ng1.x, new p62(this, 8, strJ));
        bVar.e(ng1.d, new nw2() { // from class: uf1
            @Override // defpackage.nw2
            public final Object get() {
                WebContents webContentsC;
                zf1 zf1Var = (zf1) this;
                wf1 wf1Var = zf1Var.e;
                wf1Var.e();
                n3.X0("MerchantTrust.Message.Tapped");
                wf1Var.a = false;
                wf1Var.b = false;
                wf1Var.c = 0L;
                wf1Var.d = 0L;
                zp1<Tab> zp1Var = zf1Var.j;
                if (zp1Var.f() && (webContentsC = zp1Var.get().c()) != null) {
                    N.M$ejnyHh(webContentsC, "Shopping.MerchantTrust.MessageClicked", "HasOccurred");
                }
                zf1Var.b(merchantInfo.c, 1, strJ);
                return 1;
            }
        });
        final PropertyModel propertyModelA = bVar.a();
        final double d = merchantInfo.a;
        if (z) {
            j = 0;
        } else {
            int millis = (int) TimeUnit.SECONDS.toMillis(30L);
            if (a8.P()) {
                sk skVar4 = bq.a;
                millis = N.M37SqSAy("CommerceMerchantViewer", "trust_signals_message_delay_ms", millis);
            }
            j = millis;
        }
        final xr xrVar = new xr(28, this);
        final tf1 tf1Var = this.b;
        tf1Var.getClass();
        Pair<rf1, PropertyModel> pair = new Pair<>(rf1Var, propertyModelA);
        synchronized (tf1Var.d) {
            tf1Var.e = pair;
        }
        wf1 wf1Var = tf1Var.b;
        wf1Var.getClass();
        wf1Var.c = System.nanoTime();
        tf1Var.d.postDelayed(new Runnable() { // from class: sf1
            @Override // java.lang.Runnable
            public final void run() {
                NavigationHandle navigationHandle2;
                GURL gurl2;
                boolean zM6bsIDpc2;
                NavigationHandle navigationHandle3;
                GURL gurl3;
                tf1 tf1Var2 = tf1Var;
                tf1Var2.getClass();
                rf1 rf1Var2 = rf1Var;
                WebContents webContents = rf1Var2.a;
                boolean z2 = (webContents == null || webContents.isDestroyed() || (navigationHandle3 = rf1Var2.b) == null || (gurl3 = navigationHandle3.e) == null || gurl3.k()) ? false : true;
                Callback callback = xrVar;
                zp1<Tab> zp1Var = tf1Var2.c;
                WebContents webContents2 = rf1Var2.a;
                if (!z2 || !zp1Var.f() || !webContents2.equals(zp1Var.get().c())) {
                    callback.onResult(null);
                    WebContents webContents3 = rf1Var2.a;
                    if (!((webContents3 == null || webContents3.isDestroyed() || (navigationHandle2 = rf1Var2.b) == null || (gurl2 = navigationHandle2.e) == null || gurl2.k()) ? false : true)) {
                        tf1Var2.a(3);
                        return;
                    } else if (!zp1Var.f() || webContents2.equals(zp1Var.get().c())) {
                        tf1Var2.a(0);
                        return;
                    } else {
                        tf1Var2.a(4);
                        return;
                    }
                }
                String strA = rf1Var2.a();
                wf1 wf1Var2 = tf1Var2.b;
                wf1Var2.getClass();
                wf1Var2.m = System.nanoTime();
                wf1Var2.n = 0;
                wf1Var2.p = strA;
                wf1Var2.o = d;
                if (a8.P()) {
                    sk skVar5 = bq.a;
                    zM6bsIDpc2 = N.M6bsIDpc("CommerceMerchantViewer", "trust_signals_message_disabled_for_impact_study", false);
                } else {
                    zM6bsIDpc2 = false;
                }
                if (zM6bsIDpc2) {
                    callback.onResult(rf1Var2);
                    tf1Var2.a(0);
                    return;
                }
                tf1Var2.a.b(propertyModelA, webContents2, 2, false);
                wf1Var2.d = System.nanoTime();
                wf1Var2.d();
                callback.onResult(rf1Var2);
                synchronized (tf1Var2.d) {
                    tf1Var2.e = null;
                }
            }
        }, j);
    }
}

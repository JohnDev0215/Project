package org.chromium.components.page_info;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.a8;
import defpackage.bk3;
import defpackage.bq;
import defpackage.d63;
import defpackage.di1;
import defpackage.es3;
import defpackage.f52;
import defpackage.gu1;
import defpackage.in3;
import defpackage.iu1;
import defpackage.ju1;
import defpackage.ku1;
import defpackage.l51;
import defpackage.n3;
import defpackage.om3;
import defpackage.p30;
import defpackage.pu1;
import defpackage.qo;
import defpackage.ru1;
import defpackage.sk;
import defpackage.tu1;
import defpackage.ve3;
import defpackage.vq;
import defpackage.wq;
import defpackage.wu1;
import defpackage.xu1;
import defpackage.y70;
import defpackage.z7;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.content_settings.CookieControlsBridge;
import org.chromium.components.omnibox.AutocompleteSchemeClassifier;
import org.chromium.components.omnibox.OmniboxUrlEmphasizer;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.PageInfoRowView;
import org.chromium.components.page_info.PageInfoView;
import org.chromium.components.page_info.f;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class PageInfoController implements tu1, di1.a {
    public Context e;
    public final WindowAndroid f;
    public final WebContents g;
    public final c h;
    public long i;
    public final PageInfoView j;
    public final PageInfoContainer k;
    public pu1 l;
    public final GURL m;
    public final int n;
    public ku1 o;
    public Runnable p;
    public final f q;
    public xu1 r;
    public final b s;
    public final e t;
    public d u;
    public final ArrayList v;

    public PageInfoController(WebContents webContents, int i, String str, final vq vqVar, wq wqVar) {
        int length;
        int i2;
        this.g = webContents;
        this.n = i;
        this.h = vqVar;
        WindowAndroid windowAndroidV = webContents.V();
        this.f = windowAndroidV;
        this.e = windowAndroidV.i.get();
        ArrayList arrayList = new ArrayList();
        this.v = arrayList;
        String strJ = vqVar.d != 1 ? vqVar.e : a8.D(webContents.s()).j();
        final GURL gurl = new GURL(strJ == null ? "" : strJ);
        this.m = gurl;
        boolean zJ = bk3.j(gurl);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(vqVar.d != 1 ? bk3.p(gurl.j()) : N.M52RypMk(gurl.j()));
        AutocompleteSchemeClassifier autocompleteSchemeClassifier = vqVar.a;
        if (i == 3 && (i2 = OmniboxUrlEmphasizer.c(spannableStringBuilder.toString(), autocompleteSchemeClassifier).b) > 0) {
            spannableStringBuilder.setSpan(new TextAppearanceSpan(this.e, R$style.TextAppearance_RobotoMediumStyle), 0, i2, 34);
        }
        PageInfoContainer pageInfoContainer = new PageInfoContainer(this.e);
        this.k = pageInfoContainer;
        PageInfoContainer.a aVar = new PageInfoContainer.a();
        OmniboxUrlEmphasizer.a(spannableStringBuilder, this.e, autocompleteSchemeClassifier, i, !z7.I(this.e), true);
        aVar.a = spannableStringBuilder;
        String string = spannableStringBuilder.toString();
        OmniboxUrlEmphasizer.a aVarC = OmniboxUrlEmphasizer.c(string.toString(), autocompleteSchemeClassifier);
        if (aVarC.b > 0) {
            String strA = aVarC.a(string);
            length = (strA.equals("http") || strA.equals("https")) ? aVarC.c + aVarC.d : strA.equals("data") ? 0 : string.length();
        } else {
            length = string.length();
        }
        aVar.b = length;
        autocompleteSchemeClassifier.a();
        aVar.c = N.MpICpYBr(gurl);
        aVar.g = new iu1(this, 0);
        aVar.e = new es3(13, pageInfoContainer);
        aVar.f = new ve3(18, this);
        aVar.d = !(DeviceFormFactor.a(this.e) ^ true) || qo.f().c();
        aVar.h = new ju1(this, 0);
        pageInfoContainer.setParams(aVar);
        PageInfoView.a aVar2 = new PageInfoView.a();
        aVar2.c = new iu1(this, 1);
        d63 d63Var = new d63(1, this);
        if ((vqVar.d != 1) && org.chromium.chrome.browser.offlinepages.d.d()) {
            aVar2.b = new y70(vqVar, 10, d63Var);
        } else {
            aVar2.a = false;
        }
        PageInfoView pageInfoView = new PageInfoView(this.e, aVar2);
        this.j = pageInfoView;
        if (!DeviceFormFactor.a(this.e)) {
            pageInfoView.setBackgroundColor(-1);
        }
        final l51 l51Var = new l51(15, this);
        final Resources resources = vqVar.i.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(org.chromium.chrome.R$dimen.page_info_favicon_size);
        FaviconHelper faviconHelper = new FaviconHelper();
        FaviconHelper.FaviconImageCallback faviconImageCallback = new FaviconHelper.FaviconImageCallback() { // from class: uq
            @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
            public final void onFaviconAvailable(Bitmap bitmap, GURL gurl2) {
                vq vqVar2 = vqVar;
                vqVar2.getClass();
                Callback callback = l51Var;
                if (bitmap != null) {
                    callback.onResult(new BitmapDrawable(resources, bitmap));
                } else if (bk3.j(gurl)) {
                    callback.onResult(AppCompatResources.a(vqVar2.i, R$drawable.chromelogo16));
                } else {
                    callback.onResult(null);
                }
            }
        };
        String strJ2 = gurl.j();
        Profile profile = vqVar.j;
        faviconHelper.a(profile, strJ2, dimensionPixelSize, faviconImageCallback);
        pageInfoContainer.b(pageInfoView, null, null);
        b bVar = new b(this, pageInfoView.getConnectionRowView(), webContents, vqVar, str, zJ);
        this.s = bVar;
        arrayList.add(bVar);
        e eVar = new e(this, pageInfoView.getPermissionsRowView(), vqVar, wqVar.a);
        this.t = eVar;
        arrayList.add(eVar);
        d dVar = new d(this, pageInfoView.getCookiesRowView(), vqVar);
        this.u = dVar;
        arrayList.add(dVar);
        ViewGroup rowWrapper = pageInfoView.getRowWrapper();
        ArrayList arrayList2 = new ArrayList();
        sk skVar = bq.a;
        if (N.M09VlOh_("PrivacySandboxSettings3")) {
            PageInfoRowView pageInfoRowView = new PageInfoRowView(rowWrapper.getContext(), null);
            pageInfoRowView.setId(a.l);
            rowWrapper.addView(pageInfoRowView);
            arrayList2.add(new a(this, pageInfoRowView, vqVar));
        }
        TabImpl tabImpl = (TabImpl) N.MMqeq$AW(vqVar.f);
        PageInfoRowView pageInfoRowView2 = new PageInfoRowView(rowWrapper.getContext(), null);
        pageInfoRowView2.setId(ru1.o);
        rowWrapper.addView(pageInfoRowView2);
        arrayList2.add(new ru1(this, pageInfoRowView2, vqVar, new p30(1, tabImpl)));
        int i3 = gu1.k;
        if (N.MLHZlsV8()) {
            PageInfoRowView pageInfoRowView3 = new PageInfoRowView(rowWrapper.getContext(), null);
            pageInfoRowView3.setId(gu1.k);
            rowWrapper.addView(pageInfoRowView3);
            arrayList2.add(new gu1(this, vqVar.h, pageInfoRowView3, vqVar, vqVar.f));
        }
        if (N.MRiRQ_Ey(N.MDKqWa7S(0)) && !profile.h()) {
            PageInfoRowView pageInfoRowView4 = new PageInfoRowView(rowWrapper.getContext(), null);
            pageInfoRowView4.setId(wu1.m);
            rowWrapper.addView(pageInfoRowView4);
            arrayList2.add(new wu1(this, pageInfoRowView4, vqVar.k, vqVar.l.b, vqVar.f, vqVar.j));
        }
        arrayList.addAll(arrayList2);
        this.q = new f(this.e, windowAndroidV);
        this.i = N.MuLM_ayx(this, webContents);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((xu1) it.next()).a();
        }
        this.o = new ku1(this, webContents);
        Context context = this.e;
        PageInfoContainer pageInfoContainer2 = this.k;
        View containerView = webContents.u().getContainerView();
        DeviceFormFactor.a(this.e);
        pu1 pu1Var = new pu1(context, pageInfoContainer2, containerView, vqVar.g.get(), this);
        this.l = pu1Var;
        pu1Var.b.show();
    }

    public static void a(PageInfoController pageInfoController) {
        pu1 pu1Var = pageInfoController.l;
        if (pu1Var != null) {
            pu1Var.b.dismiss();
            pageInfoController.l = null;
        }
        d dVar = pageInfoController.u;
        if (dVar != null) {
            CookieControlsBridge cookieControlsBridge = dVar.k;
            long j = cookieControlsBridge.a;
            if (j != 0) {
                N.MupWWV0Q(j, cookieControlsBridge);
                cookieControlsBridge.a = 0L;
            }
            dVar.k = null;
            pageInfoController.u = null;
        }
    }

    public static void g(Activity activity, WebContents webContents, String str, int i, vq vqVar, wq wqVar) {
        Window window;
        if (activity == null || (window = activity.getWindow()) == null) {
            return;
        }
        View decorView = window.getDecorView();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.g.b(decorView)) {
            if (i == 1) {
                n3.X0("MobileWebsiteSettingsOpenedFromMenu");
            } else if (i == 2) {
                n3.X0("MobileWebsiteSettingsOpenedFromToolbar");
            } else if (i == 3) {
                n3.X0("MobileWebsiteSettingsOpenedFromVR");
            }
            new WeakReference(new PageInfoController(webContents, n3.o0(webContents), str, vqVar, wqVar));
        }
    }

    @CalledByNative
    public final void addPermissionSection(String str, String str2, int i, int i2) {
        this.q.a.add(new f.a(i, i2, str, str2));
    }

    @Override // di1.a
    public final void b(int i) {
        xu1 xu1Var = this.r;
        if (xu1Var != null) {
            xu1Var.f();
            this.r = null;
        }
        this.o.destroy();
        this.o = null;
        N.Mz6XBRgf(this.i, this);
        this.i = 0L;
        this.e = null;
        Runnable runnable = this.p;
        if (runnable != null) {
            runnable.run();
            this.p = null;
        }
    }

    public final void d() {
        if (this.r == null) {
            return;
        }
        ju1 ju1Var = new ju1(this, 1);
        this.k.b(this.j, null, ju1Var);
    }

    public final void e(xu1 xu1Var) {
        if (this.r != null) {
            return;
        }
        this.r = xu1Var;
        String strB = xu1Var.b();
        xu1 xu1Var2 = this.r;
        PageInfoContainer pageInfoContainer = this.k;
        View viewC = xu1Var2.c(pageInfoContainer);
        if (viewC != null) {
            pageInfoContainer.b(viewC, strB, null);
        }
    }

    public final void f(int i) {
        long j = this.i;
        if (j != 0) {
            N.M5DCRkGK(j, this, i);
        }
    }

    @CalledByNative
    public final void setAdPersonalizationInfo(boolean z, String[] strArr) {
        for (xu1 xu1Var : this.v) {
            if (xu1Var instanceof a) {
                a aVar = (a) xu1Var;
                List<String> listAsList = Arrays.asList(strArr);
                aVar.j = z;
                aVar.k = listAsList;
                listAsList.isEmpty();
                PageInfoRowView.a aVar2 = new PageInfoRowView.a();
                aVar2.a = z || !aVar.k.isEmpty();
                aVar2.e = aVar.b();
                aVar2.b = R$drawable.gm_ads_click_24;
                aVar2.h = true;
                aVar2.g = new f52(28, aVar);
                aVar.h.setParams(aVar2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0107  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setSecurityDescription(java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.page_info.PageInfoController.setSecurityDescription(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0043  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updatePermissionDisplay() {
        /*
            Method dump skipped, instruction units count: 523
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.page_info.PageInfoController.updatePermissionDisplay():void");
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
    }
}

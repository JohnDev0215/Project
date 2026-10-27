package org.chromium.chrome.browser.touch_to_fill;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import defpackage.C0175b;
import defpackage.ag;
import defpackage.ce3;
import defpackage.d92;
import defpackage.de3;
import defpackage.ft1;
import defpackage.jb1;
import defpackage.ji;
import defpackage.k6;
import defpackage.ke3;
import defpackage.le3;
import defpackage.lf2;
import defpackage.me3;
import defpackage.oi;
import defpackage.oj2;
import defpackage.on0;
import defpackage.p81;
import defpackage.qx1;
import defpackage.xa1;
import defpackage.z7;
import defpackage.zj;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.history_clusters.ClusterVisit;
import org.chromium.chrome.browser.history_clusters.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.touch_to_fill.data.Credential;
import org.chromium.chrome.browser.touch_to_fill.data.WebAuthnCredential;
import org.chromium.chrome.browser.ui.favicon.a;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class TouchToFillBridge implements ce3 {
    public long a;
    public final de3 b;

    public TouchToFillBridge(long j, WindowAndroid windowAndroid, ji jiVar) {
        this.a = j;
        de3 de3Var = new de3();
        this.b = de3Var;
        Context context = windowAndroid.i.get();
        oi oiVar = new oi(jiVar, windowAndroid);
        PropertyModel propertyModel = de3Var.b;
        LargeIconBridge largeIconBridge = new LargeIconBridge(Profile.d());
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(qx1.c() ? R$dimen.touch_to_fill_favicon_size_modern : R$dimen.touch_to_fill_favicon_size);
        ke3 ke3Var = de3Var.a;
        ke3Var.a = context;
        ke3Var.b = this;
        ke3Var.c = propertyModel;
        ke3Var.d = largeIconBridge;
        ke3Var.e = dimensionPixelSize;
        ke3Var.i = oiVar;
        j.b(propertyModel, new me3(context, jiVar), new ag(14));
    }

    @CalledByNative
    public static TouchToFillBridge create(long j, WindowAndroid windowAndroid) {
        ji jiVarA = h.a(windowAndroid);
        if (jiVarA == null) {
            return null;
        }
        return new TouchToFillBridge(j, windowAndroid, jiVarA);
    }

    @CalledByNative
    public static Credential[] createCredentialArray(int i) {
        return new Credential[i];
    }

    @CalledByNative
    public static WebAuthnCredential[] createWebAuthnCredentialArray(int i) {
        return new WebAuthnCredential[i];
    }

    @CalledByNative
    public static void insertCredential(Credential[] credentialArr, int i, String str, String str2, String str3, String str4, boolean z, boolean z2, long j) {
        credentialArr[i] = new Credential(z7.q(str), str2, z7.q(str3), str4, z, z2, j);
    }

    @CalledByNative
    public static void insertWebAuthnCredential(WebAuthnCredential[] webAuthnCredentialArr, int i, String str, String str2) {
        webAuthnCredentialArr[i] = new WebAuthnCredential(str, str2);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [T, org.chromium.chrome.browser.touch_to_fill.data.WebAuthnCredential] */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, d92] */
    /* JADX WARN: Type inference failed for: r0v7, types: [T, org.chromium.chrome.browser.touch_to_fill.data.Credential] */
    /* JADX WARN: Type inference failed for: r0v9, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v15, types: [T, jb1] */
    /* JADX WARN: Type inference failed for: r4v16, types: [T, java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r4v8, types: [T, java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r5v0, types: [T, on0] */
    @CalledByNative
    public final void showCredentials(GURL gurl, boolean z, WebAuthnCredential[] webAuthnCredentialArr, Credential[] credentialArr, boolean z2, boolean z3) {
        int i;
        List<WebAuthnCredential> listAsList = Arrays.asList(webAuthnCredentialArr);
        List<Credential> listAsList2 = Arrays.asList(credentialArr);
        final ke3 ke3Var = this.b.a;
        ke3Var.h = z3;
        p81 p81Var = (p81) ke3Var.c.j(le3.b);
        p81Var.clear();
        HashMap mapE = PropertyModel.e(le3.d.f);
        PropertyModel.l<String> lVar = le3.d.e;
        T string = listAsList.size() > 0 ? listAsList2.size() > 0 ? ke3Var.a.getString(R$string.touch_to_fill_sheet_title_password_or_passkey) : ke3Var.a.getString(R$string.touch_to_fill_sheet_title_passkey) : ke3Var.a.getString(R$string.touch_to_fill_sheet_uniform_title);
        int i2 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = string;
        mapE.put(lVar, gVar);
        PropertyModel.l<String> lVar2 = le3.d.b;
        ?? J2 = oj2.j(1, gurl);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = J2;
        mapE.put(lVar2, gVar2);
        PropertyModel.h hVar = le3.d.c;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = z;
        mapE.put(hVar, aVar);
        PropertyModel.h hVar2 = le3.d.a;
        PropertyModel.a aVar2 = new PropertyModel.a();
        aVar2.a = z2;
        mapE.put(hVar2, aVar2);
        PropertyModel.j jVar = le3.d.d;
        int i3 = R$drawable.touch_to_fill_header_image;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i3;
        mapE.put(jVar, dVar);
        p81Var.t(new xa1(1, new PropertyModel(mapE)));
        ke3Var.f = listAsList;
        Iterator<WebAuthnCredential> it = listAsList.iterator();
        while (true) {
            i = 4;
            if (!it.hasNext()) {
                break;
            }
            WebAuthnCredential next = it.next();
            HashMap mapE2 = PropertyModel.e(le3.e.e);
            PropertyModel.l<WebAuthnCredential> lVar3 = le3.e.b;
            int i4 = zj.a;
            PropertyModel.g gVar3 = new PropertyModel.g();
            gVar3.a = next;
            mapE2.put(lVar3, gVar3);
            PropertyModel.l<Callback<WebAuthnCredential>> lVar4 = le3.e.d;
            ?? jb1Var = new jb1(18, ke3Var);
            PropertyModel.g gVar4 = new PropertyModel.g();
            gVar4.a = jb1Var;
            mapE2.put(lVar4, gVar4);
            PropertyModel.l<Boolean> lVar5 = le3.e.c;
            ?? r4 = Boolean.FALSE;
            PropertyModel.g gVar5 = new PropertyModel.g();
            gVar5.a = r4;
            final PropertyModel propertyModelP = C0175b.p(mapE2, lVar5, gVar5, mapE2);
            p81Var.t(new xa1(3, propertyModelP));
            if (listAsList.size() + listAsList2.size() == 1) {
                p81Var.t(new xa1(4, propertyModelP));
            }
            final String strJ = gurl.j();
            k6 k6Var = new k6(ke3Var, gurl, new LargeIconBridge.LargeIconCallback() { // from class: je3
                @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
                public final void onLargeIconAvailable(Bitmap bitmap, int i5, boolean z4, int i6) {
                    propertyModelP.p(le3.e.a, new le3.b(strJ, bitmap, i5, ke3Var.e));
                }
            });
            LargeIconBridge largeIconBridge = ke3Var.d;
            int i5 = ke3Var.e;
            largeIconBridge.getClass();
            largeIconBridge.b(new GURL(strJ), i5, k6Var);
        }
        ke3Var.g = listAsList2;
        for (Credential credential : listAsList2) {
            HashMap mapE3 = PropertyModel.e(le3.a.f);
            PropertyModel.l<Credential> lVar6 = le3.a.b;
            int i6 = zj.a;
            PropertyModel.g gVar6 = new PropertyModel.g();
            gVar6.a = credential;
            mapE3.put(lVar6, gVar6);
            PropertyModel.l<Callback<Credential>> lVar7 = le3.a.e;
            ?? on0Var = new on0(29, ke3Var);
            PropertyModel.g gVar7 = new PropertyModel.g();
            gVar7.a = on0Var;
            mapE3.put(lVar7, gVar7);
            PropertyModel.l<String> lVar8 = le3.a.c;
            ?? M25QTkfm = N.M25QTkfm(credential.getOriginUrl());
            PropertyModel.g gVar8 = new PropertyModel.g();
            gVar8.a = M25QTkfm;
            mapE3.put(lVar8, gVar8);
            PropertyModel.l<Boolean> lVar9 = le3.a.d;
            ?? ValueOf = Boolean.valueOf(z2);
            PropertyModel.g gVar9 = new PropertyModel.g();
            gVar9.a = ValueOf;
            final PropertyModel propertyModelP2 = C0175b.p(mapE3, lVar9, gVar9, mapE3);
            p81Var.t(new xa1(2, propertyModelP2));
            if (listAsList.size() + listAsList2.size() == 1) {
                p81Var.t(new xa1(i, propertyModelP2));
            }
            Credential credential2 = (Credential) propertyModelP2.j(lVar6);
            String originUrl = credential2.getOriginUrl();
            ft1 ft1VarB = ft1.b(originUrl);
            if (ft1VarB == null || ft1VarB.a.isOpaque()) {
                originUrl = gurl.j();
            }
            final String str = originUrl;
            lf2 lf2Var = new lf2(ke3Var, str, credential2, gurl, new LargeIconBridge.LargeIconCallback() { // from class: xu0
                public final /* synthetic */ int e = 1;

                @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
                public final void onLargeIconAvailable(Bitmap bitmap, int i7, boolean z4, int i8) {
                    int i9 = this.e;
                    PropertyModel propertyModel = propertyModelP2;
                    Object obj = str;
                    Object obj2 = ke3Var;
                    switch (i9) {
                        case 0:
                            ((b) obj2).getClass();
                            propertyModel.p(wu0.h, a.f(bitmap, ((ClusterVisit) obj).a, i7, null, null, 0));
                            break;
                        default:
                            propertyModel.p(le3.a.a, new le3.b((String) obj, bitmap, i7, ((ke3) obj2).e));
                            break;
                    }
                }
            });
            LargeIconBridge largeIconBridge2 = ke3Var.d;
            int i7 = ke3Var.e;
            largeIconBridge2.getClass();
            largeIconBridge2.b(new GURL(str), i7, lf2Var);
            i = 4;
        }
        HashMap mapE4 = PropertyModel.e(le3.c.c);
        PropertyModel.r<Runnable> rVar = le3.c.a;
        ?? d92Var = new d92(16, ke3Var);
        int i8 = zj.a;
        PropertyModel.g gVar10 = new PropertyModel.g();
        gVar10.a = d92Var;
        mapE4.put(rVar, gVar10);
        PropertyModel.r<String> rVar2 = le3.c.b;
        T string2 = (!qx1.d() || listAsList.size() == 0) ? ke3Var.a.getString(R$string.manage_passwords) : (listAsList2.size() <= 0 || ke3Var.h) ? ke3Var.a.getString(R$string.manage_passkeys) : ke3Var.a.getString(R$string.manage_passwords_and_passkeys);
        PropertyModel.g gVar11 = new PropertyModel.g();
        gVar11.a = string2;
        mapE4.put(rVar2, gVar11);
        p81Var.t(new xa1(5, new PropertyModel(mapE4)));
        oi oiVar = ke3Var.i;
        oiVar.f.g(oiVar);
        ke3Var.c.l(le3.a, true);
    }
}

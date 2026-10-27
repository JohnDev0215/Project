package org.chromium.components.payments;

import J.N;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import defpackage.ac2;
import defpackage.aq3;
import defpackage.es3;
import defpackage.fa1;
import defpackage.im2;
import defpackage.lz1;
import defpackage.m8;
import defpackage.mr3;
import defpackage.my1;
import defpackage.ny1;
import defpackage.nz1;
import defpackage.py1;
import defpackage.rw2;
import defpackage.ty1;
import defpackage.ut1;
import defpackage.x21;
import defpackage.xz;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.a;
import org.chromium.components.payments.d;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: AndroidPaymentAppFinder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements d.b {
    public final HashSet a = new HashSet();
    public final PaymentManifestDownloader b;
    public final PaymentManifestWebDataService c;
    public final PaymentManifestParser d;
    public final ut1 e;
    public final my1 f;
    public final ny1 g;
    public final boolean h;
    public final HashMap i;
    public final HashMap j;
    public final HashMap k;
    public final HashMap l;
    public final HashMap m;
    public final HashMap n;
    public int o;
    public int p;
    public int q;

    /* JADX INFO: compiled from: AndroidPaymentAppFinder.java */
    public static final class a {
        public final HashSet a = new HashSet();
        public final HashSet b = new HashSet();
    }

    public b(PaymentManifestWebDataService paymentManifestWebDataService, PaymentManifestDownloader paymentManifestDownloader, PaymentManifestParser paymentManifestParser, ut1 ut1Var, py1.a aVar, ny1 ny1Var) {
        HashMap map = new HashMap();
        this.i = map;
        this.j = new HashMap();
        this.k = new HashMap();
        this.l = new HashMap();
        this.m = new HashMap();
        this.n = new HashMap();
        this.f = aVar;
        map.put("com.android.vending", new GURL("https://play.google.com/billing"));
        for (GURL gurl : map.values()) {
        }
        this.b = paymentManifestDownloader;
        this.c = paymentManifestWebDataService;
        this.d = paymentManifestParser;
        this.e = ut1Var;
        this.g = ny1Var;
        this.h = ((e) this.f.f()).m;
    }

    public static String g(GURL gurl) {
        String strJ;
        if (gurl == null || (strJ = gurl.j()) == null) {
            return null;
        }
        if (strJ.endsWith("/")) {
            strJ = strJ.substring(0, strJ.length() - 1);
        }
        return strJ;
    }

    public final String[] a(ActivityInfo activityInfo, String str) {
        int i;
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (i = bundle.getInt(str)) == 0) {
            return null;
        }
        ApplicationInfo applicationInfo = activityInfo.applicationInfo;
        this.e.getClass();
        try {
            Resources resourcesForApplication = xz.a.getPackageManager().getResourcesForApplication(applicationInfo);
            if (resourcesForApplication == null) {
                return null;
            }
            return resourcesForApplication.getStringArray(i);
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
            return null;
        }
    }

    public final HashSet b(ActivityInfo activityInfo) {
        HashSet hashSet = new HashSet();
        String[] strArrA = a(activityInfo, "org.chromium.payment_method_names");
        if (strArrA == null) {
            return hashSet;
        }
        for (String strG : strArrA) {
            GURL gurl = new GURL(strG);
            if (ac2.b(gurl)) {
                strG = g(gurl);
            }
            hashSet.add(strG);
        }
        return hashSet;
    }

    public final void c() {
        HashMap map = this.j;
        boolean z = map.size() > 0;
        my1 my1Var = this.f;
        my1Var.b(z);
        if (map.isEmpty() || ((e) my1Var.f()).u) {
            my1Var.e(this.g);
            return;
        }
        this.p = map.size();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            org.chromium.components.payments.a aVar = (org.chromium.components.payments.a) ((Map.Entry) it.next()).getValue();
            m8 m8VarA = ((e) my1Var.f()).t.a();
            Set<String> setJ = aVar.j();
            HashMap map2 = new HashMap();
            for (String str : setJ) {
                if (m8VarA.containsKey(str)) {
                    map2.put(str, (lz1) m8VarA.get(str));
                }
            }
            String str2 = ((e) my1Var.f()).h;
            String str3 = ((e) my1Var.f()).i;
            byte[][] bArr = ((e) my1Var.f()).l;
            Map mapUnmodifiableMap = Collections.unmodifiableMap(((e) my1Var.f()).t.b());
            Set<String> setJ2 = aVar.j();
            HashMap map3 = new HashMap();
            for (String str4 : setJ2) {
                if (mapUnmodifiableMap.containsKey(str4)) {
                    map3.put(str4, (ty1) mapUnmodifiableMap.get(str4));
                }
            }
            im2 im2Var = new im2(this);
            Object obj = ThreadUtils.a;
            aVar.r = im2Var;
            String str5 = aVar.q;
            if (str5 == null) {
                aVar.A(true);
            } else {
                String strMR6Af3ZS = N.MR6Af3ZS(str2, 1);
                String strMR6Af3ZS2 = N.MR6Af3ZS(str3, 1);
                HashMap map4 = new HashMap();
                CollectionUtil.b(map2, new aq3(8, map4));
                boolean z2 = !N.M1X7xdZV("AddIdentityInCanMakePaymentEvent");
                Intent intent = new Intent();
                mr3.c(str5, "serviceName");
                String str6 = aVar.o;
                mr3.c(str6, "packageName");
                intent.setClassName(str6, str5);
                Bundle bundle = new Bundle();
                if (!z2) {
                    mr3.a(strMR6Af3ZS, strMR6Af3ZS2, bArr, map4, bundle);
                }
                intent.putExtras(bundle);
                x21 x21Var = new x21(xz.a, intent, aVar);
                try {
                    x21Var.g = x21Var.e.bindService(x21Var.j, x21Var, 1);
                } catch (SecurityException unused) {
                }
                if (x21Var.g) {
                    x21Var.i.postDelayed(new es3(20, x21Var), 1000L);
                } else {
                    x21Var.A();
                }
            }
        }
    }

    public final void d() {
        int i = this.q - 1;
        this.q = i;
        if (i != 0) {
            return;
        }
        PaymentManifestWebDataService paymentManifestWebDataService = this.c;
        long j = paymentManifestWebDataService.a;
        if (j != 0) {
            N.M0aw7fcV(j, paymentManifestWebDataService);
            paymentManifestWebDataService.a = 0L;
        }
        PaymentManifestDownloader paymentManifestDownloader = this.b;
        paymentManifestDownloader.getClass();
        Object obj = ThreadUtils.a;
        long j2 = paymentManifestDownloader.a;
        if (j2 != 0) {
            N.MJUrxDH$(j2, paymentManifestDownloader);
            paymentManifestDownloader.a = 0L;
            CSPCheckerBridge cSPCheckerBridge = paymentManifestDownloader.b;
            if (cSPCheckerBridge != null) {
                long j3 = cSPCheckerBridge.b;
                if (j3 != 0) {
                    N.M9IMexew(j3);
                    cSPCheckerBridge.b = 0L;
                }
            }
        }
        PaymentManifestParser paymentManifestParser = this.d;
        paymentManifestParser.getClass();
        long j4 = paymentManifestParser.a;
        if (j4 != 0) {
            N.MFuu4tOD(j4);
            paymentManifestParser.a = 0L;
        }
    }

    public final void e() {
        int i = this.o - 1;
        this.o = i;
        if (i != 0) {
            return;
        }
        HashMap map = this.m;
        for (Map.Entry entry : map.entrySet()) {
            GURL gurl = (GURL) entry.getKey();
            if (this.a.contains(gurl)) {
                a aVar = (a) entry.getValue();
                String strG = g(gurl);
                Iterator it = aVar.a.iterator();
                while (it.hasNext()) {
                    f((ResolveInfo) it.next(), strG);
                }
                Iterator it2 = aVar.b.iterator();
                while (it2.hasNext()) {
                    Set set = (Set) this.k.get((GURL) it2.next());
                    if (set != null) {
                        Iterator it3 = set.iterator();
                        while (it3.hasNext()) {
                            a aVar2 = (a) map.get((GURL) it3.next());
                            if (aVar2 != null) {
                                Iterator it4 = aVar2.a.iterator();
                                while (it4.hasNext()) {
                                    f((ResolveInfo) it4.next(), strG);
                                }
                            }
                        }
                    }
                }
            }
        }
        c();
    }

    public final void f(ResolveInfo resolveInfo, String str) {
        rw2 rw2Var;
        my1 my1Var = this.f;
        if (((e) my1Var.f()).u) {
            return;
        }
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        String str2 = activityInfo.packageName;
        String[] strArrA = a(activityInfo, "org.chromium.payment_supported_delegations");
        if (strArrA == null || strArrA.length == 0) {
            rw2Var = new rw2();
        } else {
            int iMin = Math.min(strArrA.length, 4);
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            for (int i = 0; i < iMin; i++) {
                String str3 = strArrA[i];
                if (str3 == null) {
                    Log.e("cr_SupportedDelegations", "null is an invalid delegation value. Only [\"shippingAddress\", \"payerName\", \"payerPhone\", \"payerEmail\"] values are possible.");
                } else if (str3.equals("shippingAddress")) {
                    z = true;
                } else if (strArrA[i].equals("payerName")) {
                    z2 = true;
                } else if (strArrA[i].equals("payerPhone")) {
                    z3 = true;
                } else if (strArrA[i].equals("payerEmail")) {
                    z4 = true;
                } else {
                    fa1.b("SupportedDelegations", "\"%s\" is an invalid delegation value. Only [\"shippingAddress\", \"payerName\", \"payerPhone\", \"payerEmail\"] values are possible.", strArrA[i]);
                }
            }
            rw2Var = new rw2(z, z2, z3, z4);
        }
        rw2 rw2Var2 = rw2Var;
        if (N.M1X7xdZV("EnforceFullDelegation") || str.equals("https://play.google.com/billing")) {
            nz1 nz1Var = ((e) my1Var.f()).n;
            if (!(nz1Var == null || ((!nz1Var.e || rw2Var2.a) && ((!nz1Var.b || rw2Var2.b) && ((!nz1Var.d || rw2Var2.c) && (!nz1Var.c || rw2Var2.d)))))) {
                Log.e("cr_PaymentAppFinder", "Skipping $ for not providing all of the requested PaymentOptions.".replace("$", str2));
                return;
            }
        }
        HashMap map = this.j;
        org.chromium.components.payments.a aVar = (org.chromium.components.payments.a) map.get(str2);
        if (aVar == null) {
            this.e.getClass();
            CharSequence charSequenceLoadLabel = resolveInfo.loadLabel(xz.a.getPackageManager());
            if (TextUtils.isEmpty(charSequenceLoadLabel)) {
                fa1.b("PaymentAppFinder", "Skipping \"%s\" because of empty label.", str2);
                return;
            }
            Bundle bundle = resolveInfo.activityInfo.metaData;
            org.chromium.components.payments.a aVar2 = new org.chromium.components.payments.a(new a.d(((e) my1Var.f()).f), str2, resolveInfo.activityInfo.name, (String) this.n.get(str2), charSequenceLoadLabel.toString(), resolveInfo.loadIcon(xz.a.getPackageManager()), this.h, bundle == null ? null : bundle.getString("org.chromium.default_payment_method_name"), rw2Var2);
            map.put(str2, aVar2);
            aVar = aVar2;
        }
        aVar.m.add(str);
    }
}

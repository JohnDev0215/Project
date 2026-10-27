package org.chromium.components.payments;

import J.N;
import android.content.pm.ResolveInfo;
import android.util.Log;
import defpackage.ac2;
import defpackage.fa1;
import defpackage.ut1;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.PaymentManifestDownloader;
import org.chromium.components.payments.PaymentManifestParser;
import org.chromium.components.payments.PaymentManifestWebDataService;
import org.chromium.components.payments.b;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: compiled from: PaymentManifestVerifier.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d implements PaymentManifestDownloader.ManifestDownloadCallback, PaymentManifestParser.ManifestParseCallback, PaymentManifestWebDataService.PaymentManifestWebDataServiceCallback {
    public final Origin a;
    public final GURL b;
    public final Set<GURL> d;
    public final PaymentManifestWebDataService g;
    public final PaymentManifestDownloader h;
    public final PaymentManifestParser i;
    public final ut1 j;
    public final b k;
    public final MessageDigest l;
    public Origin m;
    public int n;
    public boolean o;
    public boolean p;
    public final HashMap c = new HashMap();
    public final HashSet e = new HashSet();
    public final ArrayList f = new ArrayList();

    /* JADX INFO: compiled from: PaymentManifestVerifier.java */
    public static class a {
        public ResolveInfo a;
        public long b;
        public HashSet c;
    }

    /* JADX INFO: compiled from: PaymentManifestVerifier.java */
    public interface b {
    }

    public d(Origin origin, GURL gurl, Set set, Set set2, PaymentManifestWebDataService paymentManifestWebDataService, PaymentManifestDownloader paymentManifestDownloader, PaymentManifestParser paymentManifestParser, ut1 ut1Var, org.chromium.components.payments.b bVar) {
        MessageDigest messageDigest;
        this.a = origin;
        this.b = gurl;
        if (set != null) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                ResolveInfo resolveInfo = (ResolveInfo) it.next();
                a aVar = new a();
                aVar.a = resolveInfo;
                this.c.put(resolveInfo.activityInfo.packageName, aVar);
            }
        }
        this.d = Collections.unmodifiableSet(set2 == null ? new HashSet() : new HashSet(set2));
        this.h = paymentManifestDownloader;
        this.g = paymentManifestWebDataService;
        this.i = paymentManifestParser;
        this.j = ut1Var;
        this.k = bVar;
        if (this.c.isEmpty()) {
            messageDigest = null;
        } else {
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException unused) {
                Log.e("cr_PaymentManifest", "Unable to generate SHA-256 hashes.");
                messageDigest = null;
            }
        }
        this.l = messageDigest;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b2 : bArr) {
            formatter.format("%02x", Byte.valueOf(b2));
        }
        String string = sb.toString();
        formatter.close();
        return string;
    }

    public static String b(Set<String> set) {
        StringBuilder sb = new StringBuilder("[");
        for (String str : set) {
            sb.append(' ');
            sb.append(str);
        }
        sb.append(" ]");
        return sb.toString();
    }

    public final HashSet c(WebAppManifestSection[] webAppManifestSectionArr) {
        ArrayList arrayList = new ArrayList();
        for (WebAppManifestSection webAppManifestSection : webAppManifestSectionArr) {
            HashSet hashSet = new HashSet();
            int i = 0;
            while (true) {
                byte[][] bArr = webAppManifestSection.c;
                if (i < bArr.length) {
                    hashSet.add(a(bArr[i]));
                    i++;
                }
            }
            arrayList.add(hashSet);
        }
        HashSet hashSet2 = new HashSet();
        for (int i2 = 0; i2 < webAppManifestSectionArr.length; i2++) {
            WebAppManifestSection webAppManifestSection2 = webAppManifestSectionArr[i2];
            a aVar = (a) this.c.get(webAppManifestSection2.a);
            if (aVar != null) {
                long j = aVar.b;
                long j2 = webAppManifestSection2.b;
                String str = webAppManifestSection2.a;
                if (j < j2) {
                    fa1.d("PaymentManifest", "\"%s\" version is %d, but at least %d is required.", str, Long.valueOf(j), Long.valueOf(j2));
                } else {
                    HashSet hashSet3 = aVar.c;
                    if (hashSet3 == null) {
                        fa1.b("PaymentManifest", "Unable to determine fingerprints of \"%s\".", str);
                    } else if (hashSet3.equals(arrayList.get(i2))) {
                        hashSet2.add(str);
                    } else {
                        fa1.d("PaymentManifest", "\"%s\" fingerprints don't match the manifest. Expected %s, but found %s.", str, b((Set) arrayList.get(i2)), b(aVar.c));
                    }
                }
            }
        }
        return hashSet2;
    }

    @Override // org.chromium.components.payments.PaymentManifestDownloader.ManifestDownloadCallback
    public final void onManifestDownloadFailure(String str) {
        if (this.p) {
            return;
        }
        this.p = true;
        b bVar = this.k;
        ((org.chromium.components.payments.b) bVar).f.c(str, 0);
        if (this.o) {
            ((org.chromium.components.payments.b) bVar).e();
        }
        ((org.chromium.components.payments.b) bVar).d();
    }

    @Override // org.chromium.components.payments.PaymentManifestParser.ManifestParseCallback
    public final void onManifestParseFailure() {
        if (this.p) {
            return;
        }
        this.p = true;
        boolean z = this.o;
        b bVar = this.k;
        if (z) {
            ((org.chromium.components.payments.b) bVar).e();
        }
        ((org.chromium.components.payments.b) bVar).d();
    }

    @Override // org.chromium.components.payments.PaymentManifestDownloader.ManifestDownloadCallback
    public final void onPaymentMethodManifestDownloadSuccess(GURL gurl, Origin origin, String str) {
        this.m = origin;
        PaymentManifestParser paymentManifestParser = this.i;
        paymentManifestParser.getClass();
        Object obj = ThreadUtils.a;
        N.M$4TUaJ7(paymentManifestParser.a, gurl, str, this);
    }

    @Override // org.chromium.components.payments.PaymentManifestWebDataService.PaymentManifestWebDataServiceCallback
    public final void onPaymentMethodManifestFetched(String[] strArr) {
        b bVar;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        int i = 0;
        while (true) {
            int length = strArr.length;
            Origin origin = this.a;
            PaymentManifestDownloader paymentManifestDownloader = this.h;
            GURL gurl = this.b;
            if (i >= length) {
                if (strArr.length != 0) {
                    HashMap map = this.c;
                    if (hashSet.containsAll(map.keySet())) {
                        Set<GURL> set = this.d;
                        if (hashSet2.containsAll(set)) {
                            hashSet2.retainAll(set);
                            Iterator it = hashSet2.iterator();
                            while (true) {
                                boolean zHasNext = it.hasNext();
                                bVar = this.k;
                                if (!zHasNext) {
                                    break;
                                }
                                GURL gurl2 = (GURL) it.next();
                                HashMap map2 = ((org.chromium.components.payments.b) bVar).m;
                                b.a aVar = (b.a) map2.get(gurl);
                                if (aVar == null) {
                                    aVar = new b.a();
                                    map2.put(gurl, aVar);
                                }
                                aVar.b.add(gurl2);
                            }
                            if (map.isEmpty()) {
                                ((org.chromium.components.payments.b) bVar).e();
                                paymentManifestDownloader.a(origin, gurl, this);
                                return;
                            }
                            this.n = map.size();
                            for (String str : map.keySet()) {
                                PaymentManifestWebDataService paymentManifestWebDataService = this.g;
                                long j = paymentManifestWebDataService.a;
                                if (!(j == 0 ? false : N.MoJqMSqV(j, paymentManifestWebDataService, str, this))) {
                                    this.o = true;
                                    this.n = 0;
                                    paymentManifestDownloader.a(origin, gurl, this);
                                    return;
                                }
                            }
                            return;
                        }
                    }
                }
                this.o = true;
                paymentManifestDownloader.a(origin, gurl, this);
                return;
            }
            String str2 = strArr[i];
            if (str2 == null) {
                this.o = true;
                paymentManifestDownloader.a(origin, gurl, this);
                return;
            } else {
                GURL gurl3 = new GURL(str2);
                if (ac2.b(gurl3)) {
                    hashSet2.add(gurl3);
                } else {
                    hashSet.add(strArr[i]);
                }
                i++;
            }
        }
    }

    @Override // org.chromium.components.payments.PaymentManifestParser.ManifestParseCallback
    public final void onPaymentMethodManifestParseSuccess(GURL[] gurlArr, GURL[] gurlArr2) {
        HashSet hashSet;
        HashSet<GURL> hashSet2 = new HashSet();
        int i = 0;
        while (true) {
            int length = gurlArr2.length;
            hashSet = this.e;
            if (i >= length) {
                break;
            }
            hashSet2.add(gurlArr2[i]);
            hashSet.add(gurlArr2[i].j());
            i++;
        }
        boolean z = this.o;
        GURL gurl = this.b;
        b bVar = this.k;
        if (z) {
            hashSet2.retainAll(this.d);
            for (GURL gurl2 : hashSet2) {
                HashMap map = ((org.chromium.components.payments.b) bVar).m;
                b.a aVar = (b.a) map.get(gurl);
                if (aVar == null) {
                    aVar = new b.a();
                    map.put(gurl, aVar);
                }
                aVar.b.add(gurl2);
            }
        }
        if (gurlArr.length == 0) {
            if (this.o) {
                ((org.chromium.components.payments.b) bVar).e();
            }
            String strJ = gurl.j();
            String[] strArr = (String[]) hashSet.toArray(new String[hashSet.size()]);
            PaymentManifestWebDataService paymentManifestWebDataService = this.g;
            long j = paymentManifestWebDataService.a;
            if (j != 0) {
                N.MoW5wM_G(j, paymentManifestWebDataService, strJ, strArr);
            }
            ((org.chromium.components.payments.b) bVar).d();
            return;
        }
        this.n = gurlArr.length;
        for (int i2 = 0; i2 < gurlArr.length && !this.p; i2++) {
            Origin origin = this.m;
            GURL gurl3 = gurlArr[i2];
            PaymentManifestDownloader paymentManifestDownloader = this.h;
            paymentManifestDownloader.getClass();
            Object obj = ThreadUtils.a;
            N.MpedIYcV(paymentManifestDownloader.a, paymentManifestDownloader, origin, gurl3, this);
        }
    }

    @Override // org.chromium.components.payments.PaymentManifestWebDataService.PaymentManifestWebDataServiceCallback
    public final void onPaymentWebAppManifestFetched(WebAppManifestSection[] webAppManifestSectionArr) {
        b bVar;
        if (this.o) {
            return;
        }
        Origin origin = this.a;
        PaymentManifestDownloader paymentManifestDownloader = this.h;
        GURL gurl = this.b;
        if (webAppManifestSectionArr == null || webAppManifestSectionArr.length == 0) {
            this.o = true;
            this.n = 0;
            paymentManifestDownloader.a(origin, gurl, this);
            return;
        }
        Iterator it = c(webAppManifestSectionArr).iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            bVar = this.k;
            if (!zHasNext) {
                break;
            }
            ResolveInfo resolveInfo = ((a) this.c.get((String) it.next())).a;
            HashMap map = ((org.chromium.components.payments.b) bVar).m;
            b.a aVar = (b.a) map.get(gurl);
            if (aVar == null) {
                aVar = new b.a();
                map.put(gurl, aVar);
            }
            aVar.a.add(resolveInfo);
        }
        int i = this.n - 1;
        this.n = i;
        if (i != 0) {
            return;
        }
        ((org.chromium.components.payments.b) bVar).e();
        paymentManifestDownloader.a(origin, gurl, this);
    }

    @Override // org.chromium.components.payments.PaymentManifestDownloader.ManifestDownloadCallback
    public final void onWebAppManifestDownloadSuccess(String str) {
        if (this.p) {
            return;
        }
        PaymentManifestParser paymentManifestParser = this.i;
        paymentManifestParser.getClass();
        Object obj = ThreadUtils.a;
        N.MhPu7GL6(paymentManifestParser.a, str, this);
    }

    @Override // org.chromium.components.payments.PaymentManifestParser.ManifestParseCallback
    public final void onWebAppManifestParseSuccess(WebAppManifestSection[] webAppManifestSectionArr) {
        HashSet hashSet;
        if (this.p) {
            return;
        }
        int i = 0;
        while (true) {
            int length = webAppManifestSectionArr.length;
            hashSet = this.e;
            if (i >= length) {
                break;
            }
            hashSet.add(webAppManifestSectionArr[i].a);
            i++;
        }
        ArrayList arrayList = this.f;
        arrayList.add(webAppManifestSectionArr);
        boolean z = this.o;
        GURL gurl = this.b;
        b bVar = this.k;
        if (z) {
            Iterator it = c(webAppManifestSectionArr).iterator();
            while (it.hasNext()) {
                ResolveInfo resolveInfo = ((a) this.c.get((String) it.next())).a;
                HashMap map = ((org.chromium.components.payments.b) bVar).m;
                b.a aVar = (b.a) map.get(gurl);
                if (aVar == null) {
                    aVar = new b.a();
                    map.put(gurl, aVar);
                }
                aVar.a.add(resolveInfo);
            }
        }
        int i2 = this.n - 1;
        this.n = i2;
        if (i2 != 0) {
            return;
        }
        if (this.o) {
            ((org.chromium.components.payments.b) bVar).e();
        }
        String string = gurl.toString();
        String[] strArr = (String[]) hashSet.toArray(new String[hashSet.size()]);
        PaymentManifestWebDataService paymentManifestWebDataService = this.g;
        long j = paymentManifestWebDataService.a;
        if (j != 0) {
            N.MoW5wM_G(j, paymentManifestWebDataService, string, strArr);
        }
        int length2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            length2 += ((WebAppManifestSection[]) arrayList.get(i3)).length;
        }
        WebAppManifestSection[] webAppManifestSectionArr2 = new WebAppManifestSection[length2];
        int i4 = 0;
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            int i6 = 0;
            while (i6 < ((WebAppManifestSection[]) arrayList.get(i5)).length) {
                webAppManifestSectionArr2[i4] = ((WebAppManifestSection[]) arrayList.get(i5))[i6];
                i6++;
                i4++;
            }
        }
        long j2 = paymentManifestWebDataService.a;
        if (j2 != 0) {
            N.MsHxLyvJ(j2, paymentManifestWebDataService, webAppManifestSectionArr2);
        }
        ((org.chromium.components.payments.b) bVar).d();
    }
}

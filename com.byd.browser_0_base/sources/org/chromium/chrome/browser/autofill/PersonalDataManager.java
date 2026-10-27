package org.chromium.chrome.browser.autofill;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import defpackage.ax0;
import defpackage.dx0;
import defpackage.fa1;
import defpackage.n62;
import defpackage.oj2;
import defpackage.p62;
import defpackage.t02;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.components.prefs.PrefService;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class PersonalDataManager {
    public static PersonalDataManager e;
    public final ArrayList b = new ArrayList();
    public final HashMap c = new HashMap();
    public final ax0 d = dx0.b(1, ProfileKey.a());
    public final long a = N.MQw_0Q1A(this);

    public static class CreditCard {
        public String a;
        public String b;
        public final boolean c;
        public final boolean d;
        public final boolean e;
        public String f;
        public final String g;
        public String h;
        public String i;
        public final String j;
        public final int k;
        public String l;
        public final String m;
        public final long n;
        public final String o;
        public String p;
        public final GURL q;
        public final int r;
        public final String s;
        public final String t;
        public final String u;

        public CreditCard() {
            this("", "Chrome settings", true, false, false, "", "", "", "", "", 0, "", "", 0L, "", "", null, 0, "", "", "");
        }

        @CalledByNative
        public static CreditCard create(String str, String str2, boolean z, boolean z2, boolean z3, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, long j, String str11, String str12, GURL gurl, int i2, String str13, String str14, String str15) {
            return new CreditCard(str, str2, z, z2, z3, str3, str4, str6, str7, str8, i, str9, str10, j, str11, str12, gurl, i2, str13, str14, str15);
        }

        public final String a(Context context) {
            return getMonth() + context.getResources().getString(R$string.autofill_expiration_date_separator) + getYear();
        }

        @CalledByNative
        public String getBasicCardIssuerNetwork() {
            return this.j;
        }

        @CalledByNative
        public String getBillingAddressId() {
            return this.l;
        }

        @CalledByNative
        public GURL getCardArtUrl() {
            return this.q;
        }

        @CalledByNative
        public String getGUID() {
            return this.a;
        }

        @CalledByNative
        public long getInstrumentId() {
            return this.n;
        }

        @CalledByNative
        public boolean getIsCached() {
            return this.d;
        }

        @CalledByNative
        public boolean getIsLocal() {
            return this.c;
        }

        @CalledByNative
        public boolean getIsVirtual() {
            return this.e;
        }

        @CalledByNative
        public String getMonth() {
            return this.h;
        }

        @CalledByNative
        public String getName() {
            return this.f;
        }

        @CalledByNative
        public String getNickname() {
            return this.p;
        }

        @CalledByNative
        public String getNumber() {
            return this.g;
        }

        @CalledByNative
        public String getOrigin() {
            return this.b;
        }

        @CalledByNative
        public String getProductDescription() {
            return this.s;
        }

        @CalledByNative
        public String getServerId() {
            return this.m;
        }

        @CalledByNative
        public int getVirtualCardEnrollmentState() {
            return this.r;
        }

        @CalledByNative
        public String getYear() {
            return this.i;
        }

        public CreditCard(String str, String str2, boolean z, boolean z2, boolean z3, String str3, String str4, String str5, String str6, String str7, int i, String str8, String str9, long j, String str10, String str11, GURL gurl, int i2, String str12, String str13, String str14) {
            this.a = str;
            this.b = str2;
            this.c = z;
            this.d = z2;
            this.e = z3;
            this.f = str3;
            this.g = str4;
            this.h = str5;
            this.i = str6;
            this.j = str7;
            this.k = i;
            this.l = str8;
            this.m = str9;
            this.n = j;
            this.o = str10;
            this.p = str11;
            this.q = gurl;
            this.r = i2;
            this.s = str12;
            this.t = str13;
            this.u = str14;
        }
    }

    public interface FullCardRequestDelegate {
        @CalledByNative
        void onFullCardDetails(CreditCard creditCard, String str);

        @CalledByNative
        void onFullCardError();
    }

    public interface GetSubKeysRequestDelegate {
        @CalledByNative
        void onSubKeysReceived(String[] strArr, String[] strArr2);
    }

    public interface NormalizedAddressRequestDelegate {
        @CalledByNative
        void onAddressNormalized(AutofillProfile autofillProfile);

        @CalledByNative
        void onCouldNotNormalize(AutofillProfile autofillProfile);
    }

    public interface a {
        void A();
    }

    public static class b {
        public static final b c = new b("", 0);
        public final String a;
        public final int b;

        public b(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    public static PersonalDataManager c() {
        Object obj = ThreadUtils.a;
        if (e == null) {
            e = new PersonalDataManager();
        }
        return e;
    }

    public static PrefService d() {
        return oj2.k(Profile.d());
    }

    public final void a(GURL gurl, Callback<Bitmap> callback) {
        if (!gurl.b) {
            fa1.k("PersonalDataManager", "Tried to fetch an invalid url %s", gurl.j());
            return;
        }
        this.d.d(ax0.a.b(gurl.j(), "AutofillCardArt"), new t02(callback, 0));
    }

    public final Bitmap b(GURL gurl) {
        HashMap map = this.c;
        if (map.containsKey(gurl.j())) {
            return (Bitmap) map.get(gurl.j());
        }
        a(gurl, new p62(this, 1, gurl));
        return null;
    }

    public final ArrayList<AutofillProfile> e(String[] strArr, String[] strArr2) {
        ArrayList<AutofillProfile> arrayList = new ArrayList<>(strArr2.length);
        for (int i = 0; i < strArr2.length; i++) {
            AutofillProfile autofillProfile = (AutofillProfile) N.M172IO7Q(this.a, this, strArr2[i]);
            autofillProfile.q = strArr[i];
            arrayList.add(autofillProfile);
        }
        return arrayList;
    }

    @CalledByNative
    public final void personalDataChanged() {
        Object obj = ThreadUtils.a;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((a) it.next()).A();
        }
        Object obj2 = ThreadUtils.a;
        long j = this.a;
        String[] strArrM00Q2SNr = N.M00Q2SNr(j, this);
        ArrayList<CreditCard> arrayList = new ArrayList(strArrM00Q2SNr.length);
        for (String str : strArrM00Q2SNr) {
            arrayList.add((CreditCard) N.M3g2doJx(j, this, str));
        }
        for (CreditCard creditCard : arrayList) {
            if (creditCard.getCardArtUrl() != null && creditCard.getCardArtUrl().b && !this.c.containsKey(creditCard.getCardArtUrl().j())) {
                a(creditCard.getCardArtUrl(), new n62(this, 2, creditCard));
            }
        }
    }

    public static class AutofillProfile {
        public String a;
        public final String b;
        public boolean c;
        public final int d;
        public b e;
        public b f;
        public b g;
        public b h;
        public b i;
        public b j;
        public b k;
        public b l;
        public b m;
        public b n;
        public b o;
        public b p;
        public String q;
        public String r;

        public AutofillProfile(String str, String str2, boolean z, int i, b bVar, b bVar2, b bVar3, b bVar4, b bVar5, b bVar6, b bVar7, b bVar8, b bVar9, b bVar10, b bVar11, b bVar12, String str3) {
            this.a = str;
            this.b = str2;
            this.c = z;
            this.d = i;
            this.e = bVar;
            this.f = bVar2;
            this.g = bVar3;
            this.h = bVar4;
            this.i = bVar5;
            this.j = bVar6;
            this.k = bVar7;
            this.l = bVar8;
            this.m = bVar9;
            this.n = bVar10;
            this.o = bVar11;
            this.p = bVar12;
            this.r = str3;
        }

        @CalledByNative
        public static AutofillProfile create(String str, String str2, boolean z, int i, String str3, int i2, String str4, int i3, String str5, int i4, String str6, int i5, String str7, int i6, String str8, int i7, String str9, int i8, String str10, int i9, String str11, int i10, String str12, int i11, String str13, int i12, String str14, int i13, String str15) {
            return new AutofillProfile(str, str2, z, i, new b(str3, i2), new b(str4, i3), new b(str5, i4), new b(str6, i5), new b(str7, i6), new b(str8, i7), new b(str9, i8), new b(str10, i9), new b(str11, i10), new b(str12, i11), new b(str13, i12), new b(str14, i13), str15);
        }

        @CalledByNative
        public String getCompanyName() {
            return this.g.a;
        }

        @CalledByNative
        public int getCompanyNameStatus() {
            return this.g.b;
        }

        @CalledByNative
        public String getCountryCode() {
            return this.n.a;
        }

        @CalledByNative
        public final int getCountryCodeStatus() {
            return this.n.b;
        }

        @CalledByNative
        public String getDependentLocality() {
            return this.k.a;
        }

        @CalledByNative
        public final int getDependentLocalityStatus() {
            return this.k.b;
        }

        @CalledByNative
        public String getEmailAddress() {
            return this.p.a;
        }

        @CalledByNative
        public final int getEmailAddressStatus() {
            return this.p.b;
        }

        @CalledByNative
        public String getFullName() {
            return this.f.a;
        }

        @CalledByNative
        public int getFullNameStatus() {
            return this.f.b;
        }

        @CalledByNative
        public String getGUID() {
            return this.a;
        }

        @CalledByNative
        public String getHonorificPrefix() {
            return this.e.a;
        }

        @CalledByNative
        public final int getHonorificPrefixStatus() {
            return this.e.b;
        }

        @CalledByNative
        public String getLanguageCode() {
            return this.r;
        }

        @CalledByNative
        public String getLocality() {
            return this.j.a;
        }

        @CalledByNative
        public int getLocalityStatus() {
            return this.j.b;
        }

        @CalledByNative
        public String getOrigin() {
            return this.b;
        }

        @CalledByNative
        public String getPhoneNumber() {
            return this.o.a;
        }

        @CalledByNative
        public final int getPhoneNumberStatus() {
            return this.o.b;
        }

        @CalledByNative
        public String getPostalCode() {
            return this.l.a;
        }

        @CalledByNative
        public int getPostalCodeStatus() {
            return this.l.b;
        }

        @CalledByNative
        public String getRegion() {
            return this.i.a;
        }

        @CalledByNative
        public int getRegionStatus() {
            return this.i.b;
        }

        @CalledByNative
        public String getSortingCode() {
            return this.m.a;
        }

        @CalledByNative
        public final int getSortingCodeStatus() {
            return this.m.b;
        }

        @CalledByNative
        public int getSource() {
            return this.d;
        }

        @CalledByNative
        public String getStreetAddress() {
            return this.h.a;
        }

        @CalledByNative
        public int getStreetAddressStatus() {
            return this.h.b;
        }

        public final String toString() {
            return this.q;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public AutofillProfile() {
            b bVar = b.c;
            this("", "Chrome settings", true, 0, bVar, bVar, bVar, bVar, bVar, bVar, bVar, bVar, bVar, new b(Locale.getDefault().getCountry(), 4), bVar, bVar, "");
        }
    }
}

package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0185Kf;
import defpackage.C1001jc;
import defpackage.Cif;
import defpackage.InterfaceC0167Jf;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GoogleSignInAccount extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C1001jc();
    public static InterfaceC0167Jf F = C0185Kf.a;
    public String A;
    public List B;
    public String C;
    public String D;
    public Set E = new HashSet();
    public final int s;
    public String t;
    public String u;
    public String v;
    public String w;
    public Uri x;
    public String y;
    public long z;

    public GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List list, String str7, String str8) {
        this.s = i;
        this.t = str;
        this.u = str2;
        this.v = str3;
        this.w = str4;
        this.x = uri;
        this.y = str5;
        this.z = j;
        this.A = str6;
        this.B = list;
        this.C = str7;
        this.D = str8;
    }

    public static GoogleSignInAccount e(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("photoUrl", null);
        Uri uri = !TextUtils.isEmpty(strOptString) ? Uri.parse(strOptString) : null;
        long j = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(1, jSONArray.getString(i)));
        }
        String strOptString2 = jSONObject.optString("id");
        String strOptString3 = jSONObject.optString("tokenId", null);
        String strOptString4 = jSONObject.optString("email", null);
        String strOptString5 = jSONObject.optString("displayName", null);
        String strOptString6 = jSONObject.optString("givenName", null);
        String strOptString7 = jSONObject.optString("familyName", null);
        Long lValueOf = Long.valueOf(j);
        String string = jSONObject.getString("obfuscatedIdentifier");
        if (lValueOf == null) {
            lValueOf = Long.valueOf(System.currentTimeMillis() / 1000);
        }
        long jLongValue = lValueOf.longValue();
        Cif.e(string);
        Cif.f(hashSet);
        GoogleSignInAccount googleSignInAccount = new GoogleSignInAccount(3, strOptString2, strOptString3, strOptString4, strOptString5, uri, null, jLongValue, string, new ArrayList(hashSet), strOptString6, strOptString7);
        googleSignInAccount.y = jSONObject.optString("serverAuthCode", null);
        return googleSignInAccount;
    }

    public Set b() {
        HashSet hashSet = new HashSet(this.B);
        hashSet.addAll(this.E);
        return hashSet;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        if (googleSignInAccount.A.equals(this.A)) {
            if (((AbstractSet) googleSignInAccount.b()).equals(b())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((AbstractSet) b()).hashCode() + ((this.A.hashCode() + 527) * 31);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.e(parcel, 2, this.t, false);
        AbstractC1116lf.e(parcel, 3, this.u, false);
        AbstractC1116lf.e(parcel, 4, this.v, false);
        AbstractC1116lf.e(parcel, 5, this.w, false);
        AbstractC1116lf.d(parcel, 6, this.x, i, false);
        AbstractC1116lf.e(parcel, 7, this.y, false);
        AbstractC1116lf.b(parcel, 8, this.z);
        AbstractC1116lf.e(parcel, 9, this.A, false);
        AbstractC1116lf.m(parcel, 10, this.B, false);
        AbstractC1116lf.e(parcel, 11, this.C, false);
        AbstractC1116lf.e(parcel, 12, this.D, false);
        AbstractC1116lf.j(parcel, iN);
    }
}

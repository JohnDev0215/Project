package org.chromium.chrome.browser.password_check;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class CompromisedCredential implements Parcelable {
    public static final Parcelable.Creator<CompromisedCredential> CREATOR = new a();
    public final String e;
    public final GURL f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final long m;
    public final long n;
    public final boolean o;
    public final boolean p;

    public class a implements Parcelable.Creator<CompromisedCredential> {
        @Override // android.os.Parcelable.Creator
        public final CompromisedCredential createFromParcel(Parcel parcel) {
            String string = parcel.readString();
            GURL gurlA = GURL.a(parcel.readString());
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            String string5 = parcel.readString();
            String string6 = parcel.readString();
            String string7 = parcel.readString();
            long j = parcel.readLong();
            long j2 = parcel.readLong();
            boolean[] zArr = new boolean[4];
            parcel.readBooleanArray(zArr);
            return new CompromisedCredential(string, gurlA, string2, string3, string4, string5, string6, string7, j, j2, zArr[0], zArr[1]);
        }

        @Override // android.os.Parcelable.Creator
        public final CompromisedCredential[] newArray(int i) {
            return new CompromisedCredential[i];
        }
    }

    public CompromisedCredential(String str, GURL gurl, String str2, String str3, String str4, String str5, String str6, String str7, long j, long j2, boolean z, boolean z2) {
        this.e = str;
        this.f = gurl;
        this.g = str2;
        this.h = str3;
        this.i = str4;
        this.j = str5;
        this.k = str6;
        this.l = str7;
        this.m = j;
        this.n = j2;
        this.o = z;
        this.p = z2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CompromisedCredential compromisedCredential = (CompromisedCredential) obj;
        return this.e.equals(compromisedCredential.e) && this.f.equals(compromisedCredential.f) && this.g.equals(compromisedCredential.g) && this.h.equals(compromisedCredential.h) && this.i.equals(compromisedCredential.i) && this.j.equals(compromisedCredential.j) && this.k.equals(compromisedCredential.k) && this.l.equals(compromisedCredential.l) && this.m == compromisedCredential.m && this.n == compromisedCredential.n && this.o == compromisedCredential.o && this.p == compromisedCredential.p;
    }

    @CalledByNative
    public GURL getAssociatedUrl() {
        return this.f;
    }

    @CalledByNative
    public long getLastUsedTime() {
        return this.n;
    }

    @CalledByNative
    public String getPassword() {
        return this.j;
    }

    @CalledByNative
    public String getSignonRealm() {
        return this.e;
    }

    @CalledByNative
    public String getUsername() {
        return this.g;
    }

    public final int hashCode() {
        return Objects.hash(this.e, this.f.a, this.g, this.h, this.i, this.j, this.k, this.l, Long.valueOf(this.m), Long.valueOf(this.n), Boolean.valueOf(this.o), Boolean.valueOf(this.p));
    }

    public final String toString() {
        return "CompromisedCredential{signonRealm='" + this.e + ", associatedUrl='" + this.f + "'', username='" + this.g + "', displayOrigin='" + this.h + "', displayUsername='" + this.i + "', password='" + this.j + "', passwordChangeUrl='" + this.k + "', associatedApp='" + this.l + "', creationTime=" + this.m + ". lastUsedTime=" + this.n + ", leaked=" + this.o + ", phished=" + this.p + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.e);
        parcel.writeString(this.f.m());
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeLong(this.m);
        parcel.writeLong(this.n);
        parcel.writeBooleanArray(new boolean[]{this.o, this.p});
    }
}

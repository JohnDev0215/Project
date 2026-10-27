package org.chromium.base;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.C0962it;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class UnguessableToken implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0962it();
    public final long s;
    public final long t;

    public UnguessableToken(long j, long j2) {
        this.s = j;
        this.t = j2;
    }

    public static UnguessableToken create(long j, long j2) {
        return new UnguessableToken(j, j2);
    }

    private UnguessableToken parcelAndUnparcelForTesting() {
        Parcel parcelObtain = Parcel.obtain();
        writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        UnguessableToken unguessableToken = (UnguessableToken) CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return unguessableToken;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null || UnguessableToken.class != obj.getClass()) {
            return false;
        }
        UnguessableToken unguessableToken = (UnguessableToken) obj;
        return unguessableToken.s == this.s && unguessableToken.t == this.t;
    }

    public long getHighForSerialization() {
        return this.s;
    }

    public long getLowForSerialization() {
        return this.t;
    }

    public int hashCode() {
        long j = this.t;
        long j2 = this.s;
        return (((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.s);
        parcel.writeLong(this.t);
    }

    public UnguessableToken(long j, long j2, C0962it c0962it) {
        this.s = j;
        this.t = j2;
    }
}

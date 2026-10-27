package org.chromium.base;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class UnguessableToken implements Parcelable {
    public static final Parcelable.Creator<UnguessableToken> CREATOR = new a();
    public final long e;
    public final long f;

    public class a implements Parcelable.Creator<UnguessableToken> {
        @Override // android.os.Parcelable.Creator
        public final UnguessableToken createFromParcel(Parcel parcel) {
            long j = parcel.readLong();
            long j2 = parcel.readLong();
            if (j == 0 || j2 == 0) {
                return null;
            }
            return new UnguessableToken(j, j2);
        }

        @Override // android.os.Parcelable.Creator
        public final UnguessableToken[] newArray(int i) {
            return new UnguessableToken[i];
        }
    }

    public UnguessableToken(long j, long j2) {
        this.e = j;
        this.f = j2;
    }

    @CalledByNative
    public static UnguessableToken create(long j, long j2) {
        return new UnguessableToken(j, j2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnguessableToken unguessableToken = (UnguessableToken) obj;
        return unguessableToken.e == this.e && unguessableToken.f == this.f;
    }

    @CalledByNative
    public long getHighForSerialization() {
        return this.e;
    }

    @CalledByNative
    public long getLowForSerialization() {
        return this.f;
    }

    public final int hashCode() {
        long j = this.f;
        long j2 = this.e;
        return (((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2));
    }

    @CalledByNative
    public final UnguessableToken parcelAndUnparcelForTesting() {
        Parcel parcelObtain = Parcel.obtain();
        writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        UnguessableToken unguessableTokenCreateFromParcel = CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return unguessableTokenCreateFromParcel;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
    }
}

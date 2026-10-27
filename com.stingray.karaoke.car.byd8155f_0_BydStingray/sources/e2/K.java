package e2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class K implements Parcelable {
    public static final Parcelable.Creator<K> CREATOR = new android.support.v4.media.a(6);
    public final J[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f13981m;

    public K(J... jArr) {
        this(-9223372036854775807L, jArr);
    }

    public final K a(J... jArr) {
        if (jArr.length == 0) {
            return this;
        }
        int i7 = AbstractC1343A.f15195a;
        J[] jArr2 = this.l;
        Object[] objArrCopyOf = Arrays.copyOf(jArr2, jArr2.length + jArr.length);
        System.arraycopy(jArr, 0, objArrCopyOf, jArr2.length, jArr.length);
        return new K(this.f13981m, (J[]) objArrCopyOf);
    }

    public final K d(K k10) {
        return k10 == null ? this : a(k10.l);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || K.class != obj.getClass()) {
            return false;
        }
        K k10 = (K) obj;
        return Arrays.equals(this.l, k10.l) && this.f13981m == k10.f13981m;
    }

    public final int hashCode() {
        return C7.a.P(this.f13981m) + (Arrays.hashCode(this.l) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("entries=");
        sb.append(Arrays.toString(this.l));
        long j3 = this.f13981m;
        if (j3 == -9223372036854775807L) {
            str = "";
        } else {
            str = ", presentationTimeUs=" + j3;
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        J[] jArr = this.l;
        parcel.writeInt(jArr.length);
        for (J j3 : jArr) {
            parcel.writeParcelable(j3, 0);
        }
        parcel.writeLong(this.f13981m);
    }

    public K(long j3, J... jArr) {
        this.f13981m = j3;
        this.l = jArr;
    }

    public K(List list) {
        this((J[]) list.toArray(new J[0]));
    }

    public K(Parcel parcel) {
        this.l = new J[parcel.readInt()];
        int i7 = 0;
        while (true) {
            J[] jArr = this.l;
            if (i7 < jArr.length) {
                jArr[i7] = (J) parcel.readParcelable(J.class.getClassLoader());
                i7++;
            } else {
                this.f13981m = parcel.readLong();
                return;
            }
        }
    }
}

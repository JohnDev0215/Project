package e2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: renamed from: e2.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1204l implements Comparator, Parcelable {
    public static final Parcelable.Creator<C1204l> CREATOR = new android.support.v4.media.a(4);
    public final C1203k[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14160m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f14161n;
    public final int o;

    public C1204l(String str, boolean z6, C1203k... c1203kArr) {
        this.f14161n = str;
        c1203kArr = z6 ? (C1203k[]) c1203kArr.clone() : c1203kArr;
        this.l = c1203kArr;
        this.o = c1203kArr.length;
        Arrays.sort(c1203kArr, this);
    }

    public final C1204l a(String str) {
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f14161n, str) ? this : new C1204l(str, false, this.l);
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        C1203k c1203k = (C1203k) obj;
        C1203k c1203k2 = (C1203k) obj2;
        UUID uuid = AbstractC1199g.f14099a;
        return uuid.equals(c1203k.f14134m) ? uuid.equals(c1203k2.f14134m) ? 0 : 1 : c1203k.f14134m.compareTo(c1203k2.f14134m);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1204l.class != obj.getClass()) {
            return false;
        }
        C1204l c1204l = (C1204l) obj;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f14161n, c1204l.f14161n) && Arrays.equals(this.l, c1204l.l);
    }

    public final int hashCode() {
        if (this.f14160m == 0) {
            String str = this.f14161n;
            this.f14160m = ((str == null ? 0 : str.hashCode()) * 31) + Arrays.hashCode(this.l);
        }
        return this.f14160m;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f14161n);
        parcel.writeTypedArray(this.l, 0);
    }

    public C1204l(Parcel parcel) {
        this.f14161n = parcel.readString();
        C1203k[] c1203kArr = (C1203k[]) parcel.createTypedArray(C1203k.CREATOR);
        int i7 = AbstractC1343A.f15195a;
        this.l = c1203kArr;
        this.o = c1203kArr.length;
    }
}

package i2;

import android.os.Parcel;
import android.os.Parcelable;
import e2.J;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a implements J {
    public static final Parcelable.Creator<a> CREATOR = new android.support.v4.media.a(9);
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final byte[] f15668m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f15669n;
    public final int o;

    public a(String str, byte[] bArr, int i7, int i10) {
        d(str, bArr, i10);
        this.l = str;
        this.f15668m = bArr;
        this.f15669n = i7;
        this.o = i10;
    }

    public static void d(String str, byte[] bArr, int i7) {
        boolean z6;
        byte b10;
        str.getClass();
        switch (str) {
            case "com.android.capture.fps":
                if (i7 == 23 && bArr.length == 4) {
                    z6 = true;
                }
                AbstractC1346c.c(z6);
                break;
            case "editable.tracks.samples.location":
                if (i7 == 75 && bArr.length == 1 && ((b10 = bArr[0]) == 0 || b10 == 1)) {
                    z6 = true;
                }
                AbstractC1346c.c(z6);
                break;
            case "editable.tracks.length":
            case "editable.tracks.offset":
                if (i7 == 78 && bArr.length == 8) {
                    z6 = true;
                }
                AbstractC1346c.c(z6);
                break;
            case "editable.tracks.map":
                AbstractC1346c.c(i7 == 0);
                break;
        }
    }

    public final ArrayList a() {
        AbstractC1346c.i("Metadata is not an editable tracks map", this.l.equals("editable.tracks.map"));
        byte[] bArr = this.f15668m;
        byte b10 = bArr[1];
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < b10; i7++) {
            arrayList.add(Integer.valueOf(bArr[i7 + 2]));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.l.equals(aVar.l) && Arrays.equals(this.f15668m, aVar.f15668m) && this.f15669n == aVar.f15669n && this.o == aVar.o;
    }

    public final int hashCode() {
        return ((((Arrays.hashCode(this.f15668m) + M5.b.c(this.l, 527, 31)) * 31) + this.f15669n) * 31) + this.o;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            r6 = this;
            r0 = 1
            java.lang.String r1 = r6.l
            r2 = 0
            byte[] r3 = r6.f15668m
            int r4 = r6.o
            if (r4 == 0) goto L53
            if (r4 == r0) goto L4e
            r6 = 23
            if (r4 == r6) goto L41
            r6 = 67
            if (r4 == r6) goto L38
            r6 = 75
            if (r4 == r6) goto L2c
            r6 = 78
            if (r4 == r6) goto L1d
            goto L7c
        L1d:
            h2.t r6 = new h2.t
            r6.<init>(r3)
            long r2 = r6.A()
            java.lang.String r6 = java.lang.String.valueOf(r2)
            goto La9
        L2c:
            r6 = r3[r2]
            int r6 = java.lang.Byte.toUnsignedInt(r6)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            goto La9
        L38:
            int r6 = x3.AbstractC2673a.G(r3)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            goto La9
        L41:
            int r6 = x3.AbstractC2673a.G(r3)
            float r6 = java.lang.Float.intBitsToFloat(r6)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            goto La9
        L4e:
            java.lang.String r6 = h2.AbstractC1343A.m(r3)
            goto La9
        L53:
            java.lang.String r4 = "editable.tracks.map"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L7c
            java.util.ArrayList r6 = r6.a()
            java.lang.String r0 = "track types = "
            java.lang.StringBuilder r0 = M5.b.i(r0)
            B2.q r2 = new B2.q
            r3 = 44
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r2.<init>(r3)
            java.util.Iterator r6 = r6.iterator()
            r2.b(r0, r6)
            java.lang.String r6 = r0.toString()
            goto La9
        L7c:
            int r6 = h2.AbstractC1343A.f15195a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            int r4 = r3.length
            int r4 = r4 * 2
            r6.<init>(r4)
        L86:
            int r4 = r3.length
            if (r2 >= r4) goto La5
            r4 = r3[r2]
            int r4 = r4 >> 4
            r4 = r4 & 15
            r5 = 16
            char r4 = java.lang.Character.forDigit(r4, r5)
            r6.append(r4)
            r4 = r3[r2]
            r4 = r4 & 15
            char r4 = java.lang.Character.forDigit(r4, r5)
            r6.append(r4)
            int r2 = r2 + r0
            goto L86
        La5:
            java.lang.String r6 = r6.toString()
        La9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "mdta: key="
            r0.<init>(r2)
            r0.append(r1)
            java.lang.String r1 = ", value="
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.a.toString():java.lang.String");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        parcel.writeByteArray(this.f15668m);
        parcel.writeInt(this.f15669n);
        parcel.writeInt(this.o);
    }

    public a(Parcel parcel) {
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.l = string;
        byte[] bArrCreateByteArray = parcel.createByteArray();
        this.f15668m = bArrCreateByteArray;
        this.f15669n = parcel.readInt();
        int i10 = parcel.readInt();
        this.o = i10;
        d(string, bArrCreateByteArray, i10);
    }
}

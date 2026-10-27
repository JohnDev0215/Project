package P2;

import android.os.Parcel;
import android.os.Parcelable;
import d5.I;
import d5.d0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class n extends i {
    public static final Parcelable.Creator<n> CREATOR = new I1.g(17);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5326m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final I f5327n;

    public n(String str, String str2, d0 d0Var) {
        super(str);
        AbstractC1346c.c(!d0Var.isEmpty());
        this.f5326m = str2;
        I iW = I.w(d0Var);
        this.f5327n = iW;
    }

    public static ArrayList a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (str.length() >= 10) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(8, 10))));
            } else if (str.length() >= 7) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
            } else if (str.length() >= 4) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
            }
            return arrayList;
        } catch (NumberFormatException unused) {
            return new ArrayList();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01bb  */
    @Override // e2.J
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(e2.H r24) {
        /*
            Method dump skipped, instruction units count: 916
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P2.n.e(e2.H):void");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n.class != obj.getClass()) {
            return false;
        }
        n nVar = (n) obj;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.l, nVar.l) && Objects.equals(this.f5326m, nVar.f5326m) && this.f5327n.equals(nVar.f5327n);
    }

    public final int hashCode() {
        int iC = M5.b.c(this.l, 527, 31);
        String str = this.f5326m;
        return this.f5327n.hashCode() + ((iC + (str != null ? str.hashCode() : 0)) * 31);
    }

    @Override // P2.i
    public final String toString() {
        return this.l + ": description=" + this.f5326m + ": values=" + this.f5327n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        parcel.writeString(this.f5326m);
        parcel.writeStringArray((String[]) this.f5327n.toArray(new String[0]));
    }
}

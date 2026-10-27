package Q2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.J;
import h2.AbstractC1346c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class c implements J {
    public static final Parcelable.Creator<c> CREATOR = new g(20);
    public final ArrayList l;

    public c(ArrayList arrayList) {
        this.l = arrayList;
        boolean z6 = false;
        if (!arrayList.isEmpty()) {
            long j3 = ((b) arrayList.get(0)).f6120m;
            int i7 = 1;
            while (true) {
                if (i7 >= arrayList.size()) {
                    break;
                }
                if (((b) arrayList.get(i7)).l < j3) {
                    z6 = true;
                    break;
                } else {
                    j3 = ((b) arrayList.get(i7)).f6120m;
                    i7++;
                }
            }
        }
        AbstractC1346c.c(!z6);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        return this.l.equals(((c) obj).l);
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        return "SlowMotion: segments=" + this.l;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeList(this.l);
    }
}

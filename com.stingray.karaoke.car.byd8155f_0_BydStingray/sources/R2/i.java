package R2;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class i extends b {
    public static final Parcelable.Creator<i> CREATOR = new I1.g(26);
    public final List l;

    public i(Parcel parcel) {
        int i7 = parcel.readInt();
        ArrayList arrayList = new ArrayList(i7);
        for (int i10 = 0; i10 < i7; i10++) {
            arrayList.add(new h(parcel));
        }
        this.l = Collections.unmodifiableList(arrayList);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        List list = this.l;
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            h hVar = (h) list.get(i10);
            parcel.writeLong(hVar.f7375a);
            parcel.writeByte(hVar.f7376b ? (byte) 1 : (byte) 0);
            parcel.writeByte(hVar.c ? (byte) 1 : (byte) 0);
            parcel.writeByte(hVar.d ? (byte) 1 : (byte) 0);
            List list2 = hVar.f7378f;
            int size2 = list2.size();
            parcel.writeInt(size2);
            for (int i11 = 0; i11 < size2; i11++) {
                g gVar = (g) list2.get(i11);
                parcel.writeInt(gVar.f7373a);
                parcel.writeLong(gVar.f7374b);
            }
            parcel.writeLong(hVar.f7377e);
            parcel.writeByte(hVar.f7379g ? (byte) 1 : (byte) 0);
            parcel.writeLong(hVar.f7380h);
            parcel.writeInt(hVar.f7381i);
            parcel.writeInt(hVar.f7382j);
            parcel.writeInt(hVar.f7383k);
        }
    }

    public i(ArrayList arrayList) {
        this.l = Collections.unmodifiableList(arrayList);
    }
}

package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class vk implements Parcelable {
    public static final Parcelable.Creator<vk> CREATOR = new tk(1);
    public final ArrayList c;
    public final ArrayList e;

    public vk(Parcel parcel) {
        this.c = parcel.createStringArrayList();
        this.e = parcel.createTypedArrayList(uk.CREATOR);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.c);
        parcel.writeTypedList(this.e);
    }
}

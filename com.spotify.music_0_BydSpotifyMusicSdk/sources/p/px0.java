package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class px0 implements Parcelable {
    public static final Parcelable.Creator<px0> CREATOR = new tk(3);
    public ArrayList c;
    public ArrayList e;
    public uk[] k;
    public int l;
    public String m;
    public ArrayList n;
    public ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f169p;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.c);
        parcel.writeStringList(this.e);
        parcel.writeTypedArray(this.k, i);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeStringList(this.n);
        parcel.writeTypedList(this.o);
        parcel.writeTypedList(this.f169p);
    }
}

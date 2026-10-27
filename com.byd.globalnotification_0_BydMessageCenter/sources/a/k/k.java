package a.k;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class k extends b implements Parcelable, Serializable {
    public static final Parcelable.Creator<k> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f924b;

    public static class a implements Parcelable.Creator<k> {
        @Override // android.os.Parcelable.Creator
        public k createFromParcel(Parcel parcel) {
            return new k(parcel.readInt() == 1);
        }

        @Override // android.os.Parcelable.Creator
        public k[] newArray(int i) {
            return new k[i];
        }
    }

    public k() {
    }

    public k(boolean z) {
        this.f924b = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void j(boolean z) {
        if (z != this.f924b) {
            this.f924b = z;
            h();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f924b ? 1 : 0);
    }
}

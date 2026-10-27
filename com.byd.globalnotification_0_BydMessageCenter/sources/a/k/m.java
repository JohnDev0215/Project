package a.k;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class m extends b implements Parcelable, Serializable {
    public static final Parcelable.Creator<m> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f926b;

    public static class a implements Parcelable.Creator<m> {
        @Override // android.os.Parcelable.Creator
        public m createFromParcel(Parcel parcel) {
            return new m(parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public m[] newArray(int i) {
            return new m[i];
        }
    }

    public m() {
    }

    public m(int i) {
        this.f926b = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void j(int i) {
        if (i != this.f926b) {
            this.f926b = i;
            h();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f926b);
    }
}

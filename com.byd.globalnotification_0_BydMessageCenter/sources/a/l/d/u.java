package a.l.d;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class u implements Parcelable {
    public static final Parcelable.Creator<u> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<w> f1041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<String> f1042b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b[] f1043c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1044d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1045e;

    public static class a implements Parcelable.Creator<u> {
        @Override // android.os.Parcelable.Creator
        public u createFromParcel(Parcel parcel) {
            return new u(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public u[] newArray(int i) {
            return new u[i];
        }
    }

    public u() {
        this.f1045e = null;
    }

    public u(Parcel parcel) {
        this.f1045e = null;
        this.f1041a = parcel.createTypedArrayList(w.CREATOR);
        this.f1042b = parcel.createStringArrayList();
        this.f1043c = (b[]) parcel.createTypedArray(b.CREATOR);
        this.f1044d = parcel.readInt();
        this.f1045e = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f1041a);
        parcel.writeStringList(this.f1042b);
        parcel.writeTypedArray(this.f1043c, i);
        parcel.writeInt(this.f1044d);
        parcel.writeString(this.f1045e);
    }
}

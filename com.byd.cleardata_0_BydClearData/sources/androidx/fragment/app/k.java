package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class k implements Parcelable {
    public static final Parcelable.Creator<k> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<m> f1323b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<String> f1324c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    b[] f1325d;
    String e;
    int f;

    static class a implements Parcelable.Creator<k> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public k[] newArray(int i) {
            return new k[i];
        }
    }

    public k() {
        this.e = null;
    }

    public k(Parcel parcel) {
        this.e = null;
        this.f1323b = parcel.createTypedArrayList(m.CREATOR);
        this.f1324c = parcel.createStringArrayList();
        this.f1325d = (b[]) parcel.createTypedArray(b.CREATOR);
        this.e = parcel.readString();
        this.f = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f1323b);
        parcel.writeStringList(this.f1324c);
        parcel.writeTypedArray(this.f1325d, i);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }
}

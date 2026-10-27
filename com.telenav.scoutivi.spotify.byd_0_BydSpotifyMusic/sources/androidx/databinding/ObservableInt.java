package androidx.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ObservableInt extends b implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableInt> CREATOR = new a();
    static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2030g;

    class a implements Parcelable.Creator<ObservableInt> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ObservableInt createFromParcel(Parcel parcel) {
            return new ObservableInt(parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ObservableInt[] newArray(int i7) {
            return new ObservableInt[i7];
        }
    }

    public ObservableInt(int i7) {
        this.f2030g = i7;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int q() {
        return this.f2030g;
    }

    public void r(int i7) {
        if (i7 != this.f2030g) {
            this.f2030g = i7;
            m();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f2030g);
    }

    public ObservableInt() {
    }
}

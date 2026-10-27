package androidx.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import b.i.b;
import b.i.e;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ObservableInt extends b implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableInt> CREATOR = new a();
    public static final long serialVersionUID = 1;
    private int mValue;

    public static class a implements Parcelable.Creator<ObservableInt> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ObservableInt createFromParcel(Parcel parcel) {
            return new ObservableInt(parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ObservableInt[] newArray(int i2) {
            return new ObservableInt[i2];
        }
    }

    public ObservableInt(int i2) {
        this.mValue = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int get() {
        return this.mValue;
    }

    public void set(int i2) {
        if (i2 != this.mValue) {
            this.mValue = i2;
            notifyChange();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.mValue);
    }

    public ObservableInt() {
    }

    public ObservableInt(e... eVarArr) {
        super(eVarArr);
    }
}

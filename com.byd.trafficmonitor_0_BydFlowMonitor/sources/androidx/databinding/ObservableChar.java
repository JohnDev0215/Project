package androidx.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import b.k.b;
import b.k.e;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ObservableChar extends b implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableChar> CREATOR = new a();
    public static final long serialVersionUID = 1;
    private char mValue;

    public static class a implements Parcelable.Creator<ObservableChar> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ObservableChar createFromParcel(Parcel parcel) {
            return new ObservableChar((char) parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ObservableChar[] newArray(int i2) {
            return new ObservableChar[i2];
        }
    }

    public ObservableChar(char c2) {
        this.mValue = c2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public char get() {
        return this.mValue;
    }

    public void set(char c2) {
        if (c2 != this.mValue) {
            this.mValue = c2;
            notifyChange();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.mValue);
    }

    public ObservableChar() {
    }

    public ObservableChar(e... eVarArr) {
        super(eVarArr);
    }
}

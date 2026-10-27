package p;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class vj2 extends x0 {
    public static final Parcelable.Creator<vj2> CREATOR = new ya2(9);
    public CharSequence k;
    public boolean l;
    public CharSequence m;
    public CharSequence n;
    public CharSequence o;

    public vj2(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.k = (CharSequence) creator.createFromParcel(parcel);
        this.l = parcel.readInt() == 1;
        this.m = (CharSequence) creator.createFromParcel(parcel);
        this.n = (CharSequence) creator.createFromParcel(parcel);
        this.o = (CharSequence) creator.createFromParcel(parcel);
    }

    public final String toString() {
        return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.k) + " hint=" + ((Object) this.m) + " helperText=" + ((Object) this.n) + " placeholderText=" + ((Object) this.o) + "}";
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        TextUtils.writeToParcel(this.k, parcel, i);
        parcel.writeInt(this.l ? 1 : 0);
        TextUtils.writeToParcel(this.m, parcel, i);
        TextUtils.writeToParcel(this.n, parcel, i);
        TextUtils.writeToParcel(this.o, parcel, i);
    }
}

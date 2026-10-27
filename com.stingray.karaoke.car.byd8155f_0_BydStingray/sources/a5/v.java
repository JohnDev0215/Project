package a5;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class v extends K1.c {
    public static final Parcelable.Creator<v> CREATOR = new K1.b(4);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f11195n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f11196p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f11197q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f11198r;

    public v(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.f11195n = (CharSequence) creator.createFromParcel(parcel);
        this.o = parcel.readInt() == 1;
        this.f11196p = (CharSequence) creator.createFromParcel(parcel);
        this.f11197q = (CharSequence) creator.createFromParcel(parcel);
        this.f11198r = (CharSequence) creator.createFromParcel(parcel);
    }

    public final String toString() {
        return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f11195n) + " hint=" + ((Object) this.f11196p) + " helperText=" + ((Object) this.f11197q) + " placeholderText=" + ((Object) this.f11198r) + "}";
    }

    @Override // K1.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        TextUtils.writeToParcel(this.f11195n, parcel, i7);
        parcel.writeInt(this.o ? 1 : 0);
        TextUtils.writeToParcel(this.f11196p, parcel, i7);
        TextUtils.writeToParcel(this.f11197q, parcel, i7);
        TextUtils.writeToParcel(this.f11198r, parcel, i7);
    }
}

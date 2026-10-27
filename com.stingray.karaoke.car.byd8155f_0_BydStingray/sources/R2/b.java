package R2;

import e2.J;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements J {
    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public String toString() {
        return "SCTE-35 splice command: type=".concat(getClass().getSimpleName());
    }
}

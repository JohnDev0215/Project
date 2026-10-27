package P2;

import e2.J;

/* JADX INFO: loaded from: classes.dex */
public abstract class i implements J {
    public final String l;

    public i(String str) {
        this.l = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.l;
    }
}

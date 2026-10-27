package k;

import A7.m;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: k.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1581a implements Parcelable {
    public static final Parcelable.Creator<C1581a> CREATOR = new android.support.v4.media.a(12);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Intent f17600m;

    public C1581a(Intent intent, int i7) {
        this.l = i7;
        this.f17600m = intent;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ActivityResult{resultCode=");
        int i7 = this.l;
        sb.append(i7 != -1 ? i7 != 0 ? String.valueOf(i7) : "RESULT_CANCELED" : "RESULT_OK");
        sb.append(", data=");
        sb.append(this.f17600m);
        sb.append('}');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        m.f(parcel, "dest");
        parcel.writeInt(this.l);
        Intent intent = this.f17600m;
        parcel.writeInt(intent == null ? 0 : 1);
        if (intent != null) {
            intent.writeToParcel(parcel, i7);
        }
    }
}

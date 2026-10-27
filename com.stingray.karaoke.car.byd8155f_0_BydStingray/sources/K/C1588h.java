package k;

import A7.m;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: k.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1588h implements Parcelable {
    public static final Parcelable.Creator<C1588h> CREATOR = new android.support.v4.media.a(13);
    public final IntentSender l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Intent f17610m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f17611n;
    public final int o;

    public C1588h(IntentSender intentSender, Intent intent, int i7, int i10) {
        this.l = intentSender;
        this.f17610m = intent;
        this.f17611n = i7;
        this.o = i10;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        m.f(parcel, "dest");
        parcel.writeParcelable(this.l, i7);
        parcel.writeParcelable(this.f17610m, i7);
        parcel.writeInt(this.f17611n);
        parcel.writeInt(this.o);
    }
}

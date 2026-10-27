package l3;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class W implements Parcelable {
    public static final Parcelable.Creator<W> CREATOR = new android.support.v4.media.a(22);
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CharSequence f18549m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f18550n;
    public final Bundle o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public PlaybackState.CustomAction f18551p;

    public W(String str, CharSequence charSequence, int i7, Bundle bundle) {
        this.l = str;
        this.f18549m = charSequence;
        this.f18550n = i7;
        this.o = bundle;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "Action:mName='" + ((Object) this.f18549m) + ", mIcon=" + this.f18550n + ", mExtras=" + this.o;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        TextUtils.writeToParcel(this.f18549m, parcel, i7);
        parcel.writeInt(this.f18550n);
        parcel.writeBundle(this.o);
    }

    public W(Parcel parcel) {
        String string = parcel.readString();
        string.getClass();
        this.l = string;
        CharSequence charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        charSequence.getClass();
        this.f18549m = charSequence;
        this.f18550n = parcel.readInt();
        this.o = parcel.readBundle(P.class.getClassLoader());
    }
}

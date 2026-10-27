package android.support.v4.media.session;

import P8.n;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a(4);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f11254m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f11255n;
    public final float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f11256p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f11257q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final CharSequence f11258r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final long f11259s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ArrayList f11260t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final long f11261u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Bundle f11262v;

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new b();
        public final String l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final CharSequence f11263m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final int f11264n;
        public final Bundle o;

        public CustomAction(Parcel parcel) {
            this.l = parcel.readString();
            this.f11263m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f11264n = parcel.readInt();
            this.o = parcel.readBundle(n.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return "Action:mName='" + ((Object) this.f11263m) + ", mIcon=" + this.f11264n + ", mExtras=" + this.o;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeString(this.l);
            TextUtils.writeToParcel(this.f11263m, parcel, i7);
            parcel.writeInt(this.f11264n);
            parcel.writeBundle(this.o);
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.l = parcel.readInt();
        this.f11254m = parcel.readLong();
        this.o = parcel.readFloat();
        this.f11259s = parcel.readLong();
        this.f11255n = parcel.readLong();
        this.f11256p = parcel.readLong();
        this.f11258r = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f11260t = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f11261u = parcel.readLong();
        this.f11262v = parcel.readBundle(n.class.getClassLoader());
        this.f11257q = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PlaybackState {state=");
        sb.append(this.l);
        sb.append(", position=");
        sb.append(this.f11254m);
        sb.append(", buffered position=");
        sb.append(this.f11255n);
        sb.append(", speed=");
        sb.append(this.o);
        sb.append(", updated=");
        sb.append(this.f11259s);
        sb.append(", actions=");
        sb.append(this.f11256p);
        sb.append(", error code=");
        sb.append(this.f11257q);
        sb.append(", error message=");
        sb.append(this.f11258r);
        sb.append(", custom actions=");
        sb.append(this.f11260t);
        sb.append(", active item id=");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f11261u, "}", sb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeLong(this.f11254m);
        parcel.writeFloat(this.o);
        parcel.writeLong(this.f11259s);
        parcel.writeLong(this.f11255n);
        parcel.writeLong(this.f11256p);
        TextUtils.writeToParcel(this.f11258r, parcel, i7);
        parcel.writeTypedList(this.f11260t);
        parcel.writeLong(this.f11261u);
        parcel.writeBundle(this.f11262v);
        parcel.writeInt(this.f11257q);
    }
}

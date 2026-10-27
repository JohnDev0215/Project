package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import p.bi1;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new e(4);
    public final int c;
    public final long e;
    public final long k;
    public final float l;
    public final long m;
    public final int n;
    public final CharSequence o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f2p;
    public final ArrayList q;
    public final long r;
    public final Bundle s;
    public PlaybackState t;

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new g();
        public final String c;
        public final CharSequence e;
        public final int k;
        public final Bundle l;
        public PlaybackState.CustomAction m;

        public CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.c = str;
            this.e = charSequence;
            this.k = i;
            this.l = bundle;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return "Action:mName='" + ((Object) this.e) + ", mIcon=" + this.k + ", mExtras=" + this.l;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.c);
            TextUtils.writeToParcel(this.e, parcel, i);
            parcel.writeInt(this.k);
            parcel.writeBundle(this.l);
        }

        public CustomAction(Parcel parcel) {
            this.c = parcel.readString();
            this.e = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.k = parcel.readInt();
            this.l = parcel.readBundle(bi1.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, ArrayList arrayList, long j5, Bundle bundle) {
        this.c = i;
        this.e = j;
        this.k = j2;
        this.l = f;
        this.m = j3;
        this.n = i2;
        this.o = charSequence;
        this.f2p = j4;
        this.q = new ArrayList(arrayList);
        this.r = j5;
        this.s = bundle;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "PlaybackState {state=" + this.c + ", position=" + this.e + ", buffered position=" + this.k + ", speed=" + this.l + ", updated=" + this.f2p + ", actions=" + this.m + ", error code=" + this.n + ", error message=" + this.o + ", custom actions=" + this.q + ", active item id=" + this.r + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeLong(this.e);
        parcel.writeFloat(this.l);
        parcel.writeLong(this.f2p);
        parcel.writeLong(this.k);
        parcel.writeLong(this.m);
        TextUtils.writeToParcel(this.o, parcel, i);
        parcel.writeTypedList(this.q);
        parcel.writeLong(this.r);
        parcel.writeBundle(this.s);
        parcel.writeInt(this.n);
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.c = parcel.readInt();
        this.e = parcel.readLong();
        this.l = parcel.readFloat();
        this.f2p = parcel.readLong();
        this.k = parcel.readLong();
        this.m = parcel.readLong();
        this.o = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.q = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.r = parcel.readLong();
        this.s = parcel.readBundle(bi1.class.getClassLoader());
        this.n = parcel.readInt();
    }
}

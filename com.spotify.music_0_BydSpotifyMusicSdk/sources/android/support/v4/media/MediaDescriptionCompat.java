package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import p.bg1;
import p.hh1;
import p.ih1;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new bg1(1);
    public final String c;
    public final CharSequence e;
    public final CharSequence k;
    public final CharSequence l;
    public final Bitmap m;
    public final Uri n;
    public final Bundle o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Uri f1p;
    public MediaDescription q;

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.c = str;
        this.e = charSequence;
        this.k = charSequence2;
        this.l = charSequence3;
        this.m = bitmap;
        this.n = uri;
        this.o = bundle;
        this.f1p = uri2;
    }

    public final MediaDescription a() {
        MediaDescription mediaDescription = this.q;
        if (mediaDescription != null) {
            return mediaDescription;
        }
        MediaDescription.Builder builderB = hh1.b();
        hh1.n(builderB, this.c);
        hh1.p(builderB, this.e);
        hh1.o(builderB, this.k);
        hh1.j(builderB, this.l);
        hh1.l(builderB, this.m);
        hh1.m(builderB, this.n);
        hh1.k(builderB, this.o);
        ih1.b(builderB, this.f1p);
        MediaDescription mediaDescriptionA = hh1.a(builderB);
        this.q = mediaDescriptionA;
        return mediaDescriptionA;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return ((Object) this.e) + ", " + ((Object) this.k) + ", " + ((Object) this.l);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        a().writeToParcel(parcel, i);
    }
}

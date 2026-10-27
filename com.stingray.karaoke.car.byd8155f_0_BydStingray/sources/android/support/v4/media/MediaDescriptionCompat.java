package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a(0);
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CharSequence f11240m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CharSequence f11241n;
    public final CharSequence o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Bitmap f11242p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Uri f11243q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Bundle f11244r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Uri f11245s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public MediaDescription f11246t;

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.l = str;
        this.f11240m = charSequence;
        this.f11241n = charSequence2;
        this.o = charSequence3;
        this.f11242p = bitmap;
        this.f11243q = uri;
        this.f11244r = bundle;
        this.f11245s = uri2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return ((Object) this.f11240m) + ", " + ((Object) this.f11241n) + ", " + ((Object) this.o);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        MediaDescription mediaDescriptionA = this.f11246t;
        if (mediaDescriptionA == null) {
            MediaDescription.Builder builderB = b.b();
            b.n(builderB, this.l);
            b.p(builderB, this.f11240m);
            b.o(builderB, this.f11241n);
            b.j(builderB, this.o);
            b.l(builderB, this.f11242p);
            b.m(builderB, this.f11243q);
            b.k(builderB, this.f11244r);
            c.b(builderB, this.f11245s);
            mediaDescriptionA = b.a(builderB);
            this.f11246t = mediaDescriptionA;
        }
        mediaDescriptionA.writeToParcel(parcel, i7);
    }
}

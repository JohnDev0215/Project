package android.support.v4.media;

import a.d.a.a.c;
import a.d.a.a.d;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CharSequence f102c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CharSequence f103d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bitmap f104e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Uri f105f;
    public final Bundle g;
    public final Uri h;
    public Object i;

    public static class a implements Parcelable.Creator<MediaDescriptionCompat> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return Build.VERSION.SDK_INT < 21 ? new MediaDescriptionCompat(parcel) : MediaDescriptionCompat.a(c.a(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f106a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public CharSequence f107b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f108c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f109d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Bitmap f110e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Uri f111f;
        public Bundle g;
        public Uri h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f106a, this.f107b, this.f108c, this.f109d, this.f110e, this.f111f, this.g, this.h);
        }

        public b b(@Nullable CharSequence charSequence) {
            this.f109d = charSequence;
            return this;
        }

        public b c(@Nullable Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public b d(@Nullable Bitmap bitmap) {
            this.f110e = bitmap;
            return this;
        }

        public b e(@Nullable Uri uri) {
            this.f111f = uri;
            return this;
        }

        public b f(@Nullable String str) {
            this.f106a = str;
            return this;
        }

        public b g(@Nullable Uri uri) {
            this.h = uri;
            return this;
        }

        public b h(@Nullable CharSequence charSequence) {
            this.f108c = charSequence;
            return this;
        }

        public b i(@Nullable CharSequence charSequence) {
            this.f107b = charSequence;
            return this;
        }
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f100a = str;
        this.f101b = charSequence;
        this.f102c = charSequence2;
        this.f103d = charSequence3;
        this.f104e = bitmap;
        this.f105f = uri;
        this.g = bundle;
        this.h = uri2;
    }

    public static MediaDescriptionCompat a(Object obj) {
        int i;
        Uri uri;
        Bundle bundle = null;
        if (obj == null || (i = Build.VERSION.SDK_INT) < 21) {
            return null;
        }
        b bVar = new b();
        bVar.f(c.f(obj));
        bVar.i(c.h(obj));
        bVar.h(c.g(obj));
        bVar.b(c.b(obj));
        bVar.d(c.d(obj));
        bVar.e(c.e(obj));
        Bundle bundleC = c.c(obj);
        if (bundleC != null) {
            MediaSessionCompat.a(bundleC);
            uri = (Uri) bundleC.getParcelable("android.support.v4.media.description.MEDIA_URI");
        } else {
            uri = null;
        }
        if (uri == null) {
            bundle = bundleC;
        } else if (!bundleC.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") || bundleC.size() != 2) {
            bundleC.remove("android.support.v4.media.description.MEDIA_URI");
            bundleC.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            bundle = bundleC;
        }
        bVar.c(bundle);
        if (uri != null) {
            bVar.g(uri);
        } else if (i >= 23) {
            bVar.g(d.a(obj));
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        mediaDescriptionCompatA.i = obj;
        return mediaDescriptionCompatA;
    }

    public Object b() {
        int i;
        Object obj = this.i;
        if (obj != null || (i = Build.VERSION.SDK_INT) < 21) {
            return obj;
        }
        Object objB = c.a.b();
        c.a.g(objB, this.f100a);
        c.a.i(objB, this.f101b);
        c.a.h(objB, this.f102c);
        c.a.c(objB, this.f103d);
        c.a.e(objB, this.f104e);
        c.a.f(objB, this.f105f);
        Bundle bundle = this.g;
        if (i < 23 && this.h != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.h);
        }
        c.a.d(objB, bundle);
        if (i >= 23) {
            d.a.a(objB, this.h);
        }
        Object objA = c.a.a(objB);
        this.i = objA;
        return objA;
    }

    @Nullable
    public String c() {
        return this.f100a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return ((Object) this.f101b) + ", " + ((Object) this.f102c) + ", " + ((Object) this.f103d);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            c.i(b(), parcel, i);
            return;
        }
        parcel.writeString(this.f100a);
        TextUtils.writeToParcel(this.f101b, parcel, i);
        TextUtils.writeToParcel(this.f102c, parcel, i);
        TextUtils.writeToParcel(this.f103d, parcel, i);
        parcel.writeParcelable(this.f104e, i);
        parcel.writeParcelable(this.f105f, i);
        parcel.writeBundle(this.g);
        parcel.writeParcelable(this.h, i);
    }

    public MediaDescriptionCompat(Parcel parcel) {
        this.f100a = parcel.readString();
        this.f101b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f102c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f103d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.f104e = (Bitmap) parcel.readParcelable(classLoader);
        this.f105f = (Uri) parcel.readParcelable(classLoader);
        this.g = parcel.readBundle(classLoader);
        this.h = (Uri) parcel.readParcelable(classLoader);
    }
}

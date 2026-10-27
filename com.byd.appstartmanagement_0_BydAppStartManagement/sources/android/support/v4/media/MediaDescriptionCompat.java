package android.support.v4.media;

import a.b.a.a.c;
import a.b.a.a.d;
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f89b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CharSequence f90c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CharSequence f91d;
    public final CharSequence e;
    public final Bitmap f;
    public final Uri g;
    public final Bundle h;
    public final Uri i;
    public Object j;

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
        public String f92a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public CharSequence f93b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f94c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f95d;
        public Bitmap e;
        public Uri f;
        public Bundle g;
        public Uri h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f92a, this.f93b, this.f94c, this.f95d, this.e, this.f, this.g, this.h);
        }

        public b b(@Nullable CharSequence charSequence) {
            this.f95d = charSequence;
            return this;
        }

        public b c(@Nullable Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public b d(@Nullable Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public b e(@Nullable Uri uri) {
            this.f = uri;
            return this;
        }

        public b f(@Nullable String str) {
            this.f92a = str;
            return this;
        }

        public b g(@Nullable Uri uri) {
            this.h = uri;
            return this;
        }

        public b h(@Nullable CharSequence charSequence) {
            this.f94c = charSequence;
            return this;
        }

        public b i(@Nullable CharSequence charSequence) {
            this.f93b = charSequence;
            return this;
        }
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f89b = str;
        this.f90c = charSequence;
        this.f91d = charSequence2;
        this.e = charSequence3;
        this.f = bitmap;
        this.g = uri;
        this.h = bundle;
        this.i = uri2;
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
        mediaDescriptionCompatA.j = obj;
        return mediaDescriptionCompatA;
    }

    public Object b() {
        int i;
        Object obj = this.j;
        if (obj != null || (i = Build.VERSION.SDK_INT) < 21) {
            return obj;
        }
        Object objB = c.a.b();
        c.a.g(objB, this.f89b);
        c.a.i(objB, this.f90c);
        c.a.h(objB, this.f91d);
        c.a.c(objB, this.e);
        c.a.e(objB, this.f);
        c.a.f(objB, this.g);
        Bundle bundle = this.h;
        if (i < 23 && this.i != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.i);
        }
        c.a.d(objB, bundle);
        if (i >= 23) {
            d.a.a(objB, this.i);
        }
        Object objA = c.a.a(objB);
        this.j = objA;
        return objA;
    }

    @Nullable
    public String c() {
        return this.f89b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return ((Object) this.f90c) + ", " + ((Object) this.f91d) + ", " + ((Object) this.e);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            c.i(b(), parcel, i);
            return;
        }
        parcel.writeString(this.f89b);
        TextUtils.writeToParcel(this.f90c, parcel, i);
        TextUtils.writeToParcel(this.f91d, parcel, i);
        TextUtils.writeToParcel(this.e, parcel, i);
        parcel.writeParcelable(this.f, i);
        parcel.writeParcelable(this.g, i);
        parcel.writeBundle(this.h);
        parcel.writeParcelable(this.i, i);
    }

    public MediaDescriptionCompat(Parcel parcel) {
        this.f89b = parcel.readString();
        this.f90c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f91d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.e = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.f = (Bitmap) parcel.readParcelable(classLoader);
        this.g = (Uri) parcel.readParcelable(classLoader);
        this.h = parcel.readBundle(classLoader);
        this.i = (Uri) parcel.readParcelable(classLoader);
    }
}

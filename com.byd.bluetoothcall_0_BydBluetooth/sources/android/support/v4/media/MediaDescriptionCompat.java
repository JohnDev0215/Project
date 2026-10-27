package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.c;
import android.support.v4.media.d;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f90b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final CharSequence f91c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final CharSequence f92d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final CharSequence f93e;
    private final Bitmap f;
    private final Uri g;
    private final Bundle h;
    private final Uri i;
    private Object j;

    static class a implements Parcelable.Creator<MediaDescriptionCompat> {
        a() {
        }

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
        private String f94a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private CharSequence f95b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f96c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private CharSequence f97d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Bitmap f98e;
        private Uri f;
        private Bundle g;
        private Uri h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f94a, this.f95b, this.f96c, this.f97d, this.f98e, this.f, this.g, this.h);
        }

        public b b(CharSequence charSequence) {
            this.f97d = charSequence;
            return this;
        }

        public b c(Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public b d(Bitmap bitmap) {
            this.f98e = bitmap;
            return this;
        }

        public b e(Uri uri) {
            this.f = uri;
            return this;
        }

        public b f(String str) {
            this.f94a = str;
            return this;
        }

        public b g(Uri uri) {
            this.h = uri;
            return this;
        }

        public b h(CharSequence charSequence) {
            this.f96c = charSequence;
            return this;
        }

        public b i(CharSequence charSequence) {
            this.f95b = charSequence;
            return this;
        }
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f90b = str;
        this.f91c = charSequence;
        this.f92d = charSequence2;
        this.f93e = charSequence3;
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

    public Object c() {
        int i;
        Object obj = this.j;
        if (obj != null || (i = Build.VERSION.SDK_INT) < 21) {
            return obj;
        }
        Object objB = c.a.b();
        c.a.g(objB, this.f90b);
        c.a.i(objB, this.f91c);
        c.a.h(objB, this.f92d);
        c.a.c(objB, this.f93e);
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

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String f() {
        return this.f90b;
    }

    public String toString() {
        return ((Object) this.f91c) + ", " + ((Object) this.f92d) + ", " + ((Object) this.f93e);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            c.i(c(), parcel, i);
            return;
        }
        parcel.writeString(this.f90b);
        TextUtils.writeToParcel(this.f91c, parcel, i);
        TextUtils.writeToParcel(this.f92d, parcel, i);
        TextUtils.writeToParcel(this.f93e, parcel, i);
        parcel.writeParcelable(this.f, i);
        parcel.writeParcelable(this.g, i);
        parcel.writeBundle(this.h);
        parcel.writeParcelable(this.i, i);
    }

    MediaDescriptionCompat(Parcel parcel) {
        this.f90b = parcel.readString();
        this.f91c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f92d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f93e = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.f = (Bitmap) parcel.readParcelable(classLoader);
        this.g = (Uri) parcel.readParcelable(classLoader);
        this.h = parcel.readBundle(classLoader);
        this.i = (Uri) parcel.readParcelable(classLoader);
    }
}

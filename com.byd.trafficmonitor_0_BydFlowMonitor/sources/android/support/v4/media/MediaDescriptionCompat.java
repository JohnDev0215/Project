package android.support.v4.media;

import a.d.a.b.c;
import a.d.a.b.d;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CharSequence f109c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CharSequence f110d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CharSequence f111e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Bitmap f112f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Uri f113g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Bundle f114h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Uri f115i;
    public Object j;

    public static class a implements Parcelable.Creator<MediaDescriptionCompat> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return Build.VERSION.SDK_INT < 21 ? new MediaDescriptionCompat(parcel) : MediaDescriptionCompat.j(c.a(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat[] newArray(int i2) {
            return new MediaDescriptionCompat[i2];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f116a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public CharSequence f117b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f118c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f119d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Bitmap f120e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Uri f121f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Bundle f122g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Uri f123h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f116a, this.f117b, this.f118c, this.f119d, this.f120e, this.f121f, this.f122g, this.f123h);
        }

        public b b(CharSequence charSequence) {
            this.f119d = charSequence;
            return this;
        }

        public b c(Bundle bundle) {
            this.f122g = bundle;
            return this;
        }

        public b d(Bitmap bitmap) {
            this.f120e = bitmap;
            return this;
        }

        public b e(Uri uri) {
            this.f121f = uri;
            return this;
        }

        public b f(String str) {
            this.f116a = str;
            return this;
        }

        public b g(Uri uri) {
            this.f123h = uri;
            return this;
        }

        public b h(CharSequence charSequence) {
            this.f118c = charSequence;
            return this;
        }

        public b i(CharSequence charSequence) {
            this.f117b = charSequence;
            return this;
        }
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f108b = str;
        this.f109c = charSequence;
        this.f110d = charSequence2;
        this.f111e = charSequence3;
        this.f112f = bitmap;
        this.f113g = uri;
        this.f114h = bundle;
        this.f115i = uri2;
    }

    public static MediaDescriptionCompat j(Object obj) {
        int i2;
        Uri uri;
        Bundle bundle = null;
        if (obj == null || (i2 = Build.VERSION.SDK_INT) < 21) {
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
        } else if (i2 >= 23) {
            bVar.g(d.a(obj));
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        mediaDescriptionCompatA.j = obj;
        return mediaDescriptionCompatA;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Object k() {
        int i2;
        Object obj = this.j;
        if (obj != null || (i2 = Build.VERSION.SDK_INT) < 21) {
            return obj;
        }
        Object objB = c.a.b();
        c.a.g(objB, this.f108b);
        c.a.i(objB, this.f109c);
        c.a.h(objB, this.f110d);
        c.a.c(objB, this.f111e);
        c.a.e(objB, this.f112f);
        c.a.f(objB, this.f113g);
        Bundle bundle = this.f114h;
        if (i2 < 23 && this.f115i != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.f115i);
        }
        c.a.d(objB, bundle);
        if (i2 >= 23) {
            d.a.a(objB, this.f115i);
        }
        Object objA = c.a.a(objB);
        this.j = objA;
        return objA;
    }

    public String l() {
        return this.f108b;
    }

    public String toString() {
        return ((Object) this.f109c) + ", " + ((Object) this.f110d) + ", " + ((Object) this.f111e);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            c.i(k(), parcel, i2);
            return;
        }
        parcel.writeString(this.f108b);
        TextUtils.writeToParcel(this.f109c, parcel, i2);
        TextUtils.writeToParcel(this.f110d, parcel, i2);
        TextUtils.writeToParcel(this.f111e, parcel, i2);
        parcel.writeParcelable(this.f112f, i2);
        parcel.writeParcelable(this.f113g, i2);
        parcel.writeBundle(this.f114h);
        parcel.writeParcelable(this.f115i, i2);
    }

    public MediaDescriptionCompat(Parcel parcel) {
        this.f108b = parcel.readString();
        this.f109c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f110d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f111e = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.f112f = (Bitmap) parcel.readParcelable(classLoader);
        this.f113g = (Uri) parcel.readParcelable(classLoader);
        this.f114h = parcel.readBundle(classLoader);
        this.f115i = (Uri) parcel.readParcelable(classLoader);
    }
}

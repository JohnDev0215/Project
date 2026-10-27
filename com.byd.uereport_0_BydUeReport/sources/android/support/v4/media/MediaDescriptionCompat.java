package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import h.c;
import h.d;

/* JADX INFO: loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f142d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CharSequence f143e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CharSequence f144f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final CharSequence f145g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Bitmap f146h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Uri f147i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Bundle f148j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Uri f149k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f150l;

    public static class a implements Parcelable.Creator<MediaDescriptionCompat> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return Build.VERSION.SDK_INT < 21 ? new MediaDescriptionCompat(parcel) : MediaDescriptionCompat.f(c.a(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat[] newArray(int i8) {
            return new MediaDescriptionCompat[i8];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f151a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public CharSequence f152b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f153c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f154d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Bitmap f155e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Uri f156f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Bundle f157g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Uri f158h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f151a, this.f152b, this.f153c, this.f154d, this.f155e, this.f156f, this.f157g, this.f158h);
        }

        public b b(CharSequence charSequence) {
            this.f154d = charSequence;
            return this;
        }

        public b c(Bundle bundle) {
            this.f157g = bundle;
            return this;
        }

        public b d(Bitmap bitmap) {
            this.f155e = bitmap;
            return this;
        }

        public b e(Uri uri) {
            this.f156f = uri;
            return this;
        }

        public b f(String str) {
            this.f151a = str;
            return this;
        }

        public b g(Uri uri) {
            this.f158h = uri;
            return this;
        }

        public b h(CharSequence charSequence) {
            this.f153c = charSequence;
            return this;
        }

        public b i(CharSequence charSequence) {
            this.f152b = charSequence;
            return this;
        }
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f142d = str;
        this.f143e = charSequence;
        this.f144f = charSequence2;
        this.f145g = charSequence3;
        this.f146h = bitmap;
        this.f147i = uri;
        this.f148j = bundle;
        this.f149k = uri2;
    }

    public static MediaDescriptionCompat f(Object obj) {
        int i8;
        Uri uri;
        Bundle bundle = null;
        if (obj == null || (i8 = Build.VERSION.SDK_INT) < 21) {
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
        } else if (i8 >= 23) {
            bVar.g(d.a(obj));
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        mediaDescriptionCompatA.f150l = obj;
        return mediaDescriptionCompatA;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Object g() {
        int i8;
        Object obj = this.f150l;
        if (obj != null || (i8 = Build.VERSION.SDK_INT) < 21) {
            return obj;
        }
        Object objB = c.a.b();
        c.a.g(objB, this.f142d);
        c.a.i(objB, this.f143e);
        c.a.h(objB, this.f144f);
        c.a.c(objB, this.f145g);
        c.a.e(objB, this.f146h);
        c.a.f(objB, this.f147i);
        Bundle bundle = this.f148j;
        if (i8 < 23 && this.f149k != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.f149k);
        }
        c.a.d(objB, bundle);
        if (i8 >= 23) {
            d.a.a(objB, this.f149k);
        }
        Object objA = c.a.a(objB);
        this.f150l = objA;
        return objA;
    }

    public String h() {
        return this.f142d;
    }

    public String toString() {
        return ((Object) this.f143e) + ", " + ((Object) this.f144f) + ", " + ((Object) this.f145g);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        if (Build.VERSION.SDK_INT >= 21) {
            c.i(g(), parcel, i8);
            return;
        }
        parcel.writeString(this.f142d);
        TextUtils.writeToParcel(this.f143e, parcel, i8);
        TextUtils.writeToParcel(this.f144f, parcel, i8);
        TextUtils.writeToParcel(this.f145g, parcel, i8);
        parcel.writeParcelable(this.f146h, i8);
        parcel.writeParcelable(this.f147i, i8);
        parcel.writeBundle(this.f148j);
        parcel.writeParcelable(this.f149k, i8);
    }

    public MediaDescriptionCompat(Parcel parcel) {
        this.f142d = parcel.readString();
        this.f143e = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f144f = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f145g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.f146h = (Bitmap) parcel.readParcelable(classLoader);
        this.f147i = (Uri) parcel.readParcelable(classLoader);
        this.f148j = parcel.readBundle(classLoader);
        this.f149k = (Uri) parcel.readParcelable(classLoader);
    }
}

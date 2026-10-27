package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f202e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final CharSequence f203g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final CharSequence f204h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final CharSequence f205i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Bitmap f206j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final Uri f207k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final Bundle f208l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final Uri f209m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private MediaDescription f210n;

    class a implements Parcelable.Creator<MediaDescriptionCompat> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return MediaDescriptionCompat.b(MediaDescription.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat[] newArray(int i7) {
            return new MediaDescriptionCompat[i7];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f211a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private CharSequence f212b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f213c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private CharSequence f214d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Bitmap f215e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private Uri f216f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private Bundle f217g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Uri f218h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f211a, this.f212b, this.f213c, this.f214d, this.f215e, this.f216f, this.f217g, this.f218h);
        }

        public b b(CharSequence charSequence) {
            this.f214d = charSequence;
            return this;
        }

        public b c(Bundle bundle) {
            this.f217g = bundle;
            return this;
        }

        public b d(Bitmap bitmap) {
            this.f215e = bitmap;
            return this;
        }

        public b e(Uri uri) {
            this.f216f = uri;
            return this;
        }

        public b f(String str) {
            this.f211a = str;
            return this;
        }

        public b g(Uri uri) {
            this.f218h = uri;
            return this;
        }

        public b h(CharSequence charSequence) {
            this.f213c = charSequence;
            return this;
        }

        public b i(CharSequence charSequence) {
            this.f212b = charSequence;
            return this;
        }
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f202e = str;
        this.f203g = charSequence;
        this.f204h = charSequence2;
        this.f205i = charSequence3;
        this.f206j = bitmap;
        this.f207k = uri;
        this.f208l = bundle;
        this.f209m = uri2;
    }

    public static MediaDescriptionCompat b(Object obj) {
        Bundle bundle = null;
        if (obj == null) {
            return null;
        }
        b bVar = new b();
        MediaDescription mediaDescription = (MediaDescription) obj;
        bVar.f(mediaDescription.getMediaId());
        bVar.i(mediaDescription.getTitle());
        bVar.h(mediaDescription.getSubtitle());
        bVar.b(mediaDescription.getDescription());
        bVar.d(mediaDescription.getIconBitmap());
        bVar.e(mediaDescription.getIconUri());
        Bundle extras = mediaDescription.getExtras();
        if (extras != null) {
            extras = MediaSessionCompat.b(extras);
        }
        Uri uri = extras != null ? (Uri) extras.getParcelable("android.support.v4.media.description.MEDIA_URI") : null;
        if (uri == null) {
            bundle = extras;
        } else if (!extras.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") || extras.size() != 2) {
            extras.remove("android.support.v4.media.description.MEDIA_URI");
            extras.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            bundle = extras;
        }
        bVar.c(bundle);
        if (uri != null) {
            bVar.g(uri);
        } else {
            bVar.g(mediaDescription.getMediaUri());
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        mediaDescriptionCompatA.f210n = mediaDescription;
        return mediaDescriptionCompatA;
    }

    public Bundle c() {
        return this.f208l;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Uri h() {
        return this.f207k;
    }

    public Object j() {
        MediaDescription mediaDescription = this.f210n;
        if (mediaDescription != null) {
            return mediaDescription;
        }
        MediaDescription.Builder builder = new MediaDescription.Builder();
        builder.setMediaId(this.f202e);
        builder.setTitle(this.f203g);
        builder.setSubtitle(this.f204h);
        builder.setDescription(this.f205i);
        builder.setIconBitmap(this.f206j);
        builder.setIconUri(this.f207k);
        builder.setExtras(this.f208l);
        builder.setMediaUri(this.f209m);
        MediaDescription mediaDescriptionBuild = builder.build();
        this.f210n = mediaDescriptionBuild;
        return mediaDescriptionBuild;
    }

    public String k() {
        return this.f202e;
    }

    public Uri m() {
        return this.f209m;
    }

    public CharSequence p() {
        return this.f204h;
    }

    public CharSequence q() {
        return this.f203g;
    }

    public String toString() {
        return ((Object) this.f203g) + ", " + ((Object) this.f204h) + ", " + ((Object) this.f205i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        ((MediaDescription) j()).writeToParcel(parcel, i7);
    }
}

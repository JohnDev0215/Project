package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f32c;
    private final CharSequence d;
    private final CharSequence e;
    private final CharSequence f;
    private final Bitmap g;
    private final Uri h;
    private final Bundle i;
    private final Uri j;
    private MediaDescription k;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<MediaDescriptionCompat> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return Build.VERSION.SDK_INT < 21 ? new MediaDescriptionCompat(parcel) : MediaDescriptionCompat.a(MediaDescription.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f33a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private CharSequence f34b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f35c;
        private CharSequence d;
        private Bitmap e;
        private Uri f;
        private Bundle g;
        private Uri h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f33a, this.f34b, this.f35c, this.d, this.e, this.f, this.g, this.h);
        }

        public b b(@Nullable CharSequence charSequence) {
            this.d = charSequence;
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
            this.f33a = str;
            return this;
        }

        public b g(@Nullable Uri uri) {
            this.h = uri;
            return this;
        }

        public b h(@Nullable CharSequence charSequence) {
            this.f35c = charSequence;
            return this;
        }

        public b i(@Nullable CharSequence charSequence) {
            this.f34b = charSequence;
            return this;
        }
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f32c = str;
        this.d = charSequence;
        this.e = charSequence2;
        this.f = charSequence3;
        this.g = bitmap;
        this.h = uri;
        this.i = bundle;
        this.j = uri2;
    }

    public static MediaDescriptionCompat a(Object obj) {
        int i = Build.VERSION.SDK_INT;
        Bundle bundle = null;
        if (obj == null || i < 21) {
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
            extras = MediaSessionCompat.c(extras);
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
        } else if (i >= 23) {
            bVar.g(mediaDescription.getMediaUri());
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        mediaDescriptionCompatA.k = mediaDescription;
        return mediaDescriptionCompatA;
    }

    @Nullable
    public Bitmap b() {
        return this.g;
    }

    @Nullable
    public Uri c() {
        return this.h;
    }

    public Object d() {
        int i = Build.VERSION.SDK_INT;
        if (this.k != null || i < 21) {
            return this.k;
        }
        MediaDescription.Builder builder = new MediaDescription.Builder();
        builder.setMediaId(this.f32c);
        builder.setTitle(this.d);
        builder.setSubtitle(this.e);
        builder.setDescription(this.f);
        builder.setIconBitmap(this.g);
        builder.setIconUri(this.h);
        Bundle bundle = this.i;
        if (i < 23 && this.j != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.j);
        }
        builder.setExtras(bundle);
        if (i >= 23) {
            builder.setMediaUri(this.j);
        }
        MediaDescription mediaDescriptionBuild = builder.build();
        this.k = mediaDescriptionBuild;
        return mediaDescriptionBuild;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public String e() {
        return this.f32c;
    }

    @Nullable
    public CharSequence f() {
        return this.e;
    }

    @Nullable
    public CharSequence g() {
        return this.d;
    }

    public String toString() {
        return ((Object) this.d) + ", " + ((Object) this.e) + ", " + ((Object) this.f);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            ((MediaDescription) d()).writeToParcel(parcel, i);
            return;
        }
        parcel.writeString(this.f32c);
        TextUtils.writeToParcel(this.d, parcel, i);
        TextUtils.writeToParcel(this.e, parcel, i);
        TextUtils.writeToParcel(this.f, parcel, i);
        parcel.writeParcelable(this.g, i);
        parcel.writeParcelable(this.h, i);
        parcel.writeBundle(this.i);
        parcel.writeParcelable(this.j, i);
    }

    MediaDescriptionCompat(Parcel parcel) {
        this.f32c = parcel.readString();
        this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.e = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.g = (Bitmap) parcel.readParcelable(classLoader);
        this.h = (Uri) parcel.readParcelable(classLoader);
        this.i = parcel.readBundle(classLoader);
        this.j = (Uri) parcel.readParcelable(classLoader);
    }
}

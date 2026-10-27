package l3;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: l3.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1740E implements Parcelable {
    public static final Parcelable.Creator<C1740E> CREATOR = new android.support.v4.media.a(15);
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CharSequence f18513m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CharSequence f18514n;
    public final CharSequence o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Bitmap f18515p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Uri f18516q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Bundle f18517r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Uri f18518s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public MediaDescription f18519t;

    public C1740E(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.l = str;
        this.f18513m = charSequence;
        this.f18514n = charSequence2;
        this.o = charSequence3;
        this.f18515p = bitmap;
        this.f18516q = uri;
        this.f18517r = bundle;
        this.f18518s = uri2;
    }

    public static C1740E a(Object obj) {
        Bundle bundle;
        Uri mediaUri = null;
        if (obj == null) {
            return null;
        }
        MediaDescription mediaDescription = (MediaDescription) obj;
        String mediaId = mediaDescription.getMediaId();
        CharSequence title = mediaDescription.getTitle();
        CharSequence subtitle = mediaDescription.getSubtitle();
        CharSequence description = mediaDescription.getDescription();
        Bitmap iconBitmap = mediaDescription.getIconBitmap();
        Uri iconUri = mediaDescription.getIconUri();
        Bundle extras = mediaDescription.getExtras();
        if (extras == null) {
            extras = null;
        } else {
            P.o(extras);
            try {
                extras.isEmpty();
            } catch (BadParcelableException unused) {
                h6.n.j("MediaSessionCompat", "Could not unparcel the data.");
                extras = null;
            }
        }
        if (extras != null) {
            extras = new Bundle(extras);
        }
        if (extras != null) {
            Uri uri = (Uri) extras.getParcelable("android.support.v4.media.description.MEDIA_URI");
            if (uri == null) {
                bundle = extras;
                mediaUri = uri;
            } else if (extras.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") && extras.size() == 2) {
                bundle = null;
                mediaUri = uri;
            } else {
                extras.remove("android.support.v4.media.description.MEDIA_URI");
                extras.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
                bundle = extras;
                mediaUri = uri;
            }
        } else {
            bundle = extras;
        }
        if (mediaUri == null) {
            mediaUri = mediaDescription.getMediaUri();
        }
        C1740E c1740e = new C1740E(mediaId, title, subtitle, description, iconBitmap, iconUri, bundle, mediaUri);
        c1740e.f18519t = mediaDescription;
        return c1740e;
    }

    public final MediaDescription d() {
        MediaDescription mediaDescription = this.f18519t;
        if (mediaDescription != null) {
            return mediaDescription;
        }
        MediaDescription.Builder builder = new MediaDescription.Builder();
        builder.setMediaId(this.l);
        builder.setTitle(this.f18513m);
        builder.setSubtitle(this.f18514n);
        builder.setDescription(this.o);
        builder.setIconBitmap(this.f18515p);
        builder.setIconUri(this.f18516q);
        builder.setExtras(this.f18517r);
        builder.setMediaUri(this.f18518s);
        MediaDescription mediaDescriptionBuild = builder.build();
        this.f18519t = mediaDescriptionBuild;
        return mediaDescriptionBuild;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return ((Object) this.f18513m) + ", " + ((Object) this.f18514n) + ", " + ((Object) this.o);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        d().writeToParcel(parcel, i7);
    }
}

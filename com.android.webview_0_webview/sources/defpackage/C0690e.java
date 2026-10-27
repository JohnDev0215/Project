package defpackage;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;

/* JADX INFO: renamed from: e, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0690e implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        Uri mediaUri;
        Bundle bundle;
        Object objCreateFromParcel = MediaDescription.CREATOR.createFromParcel(parcel);
        if (objCreateFromParcel == null) {
            return null;
        }
        MediaDescription mediaDescription = (MediaDescription) objCreateFromParcel;
        String mediaId = mediaDescription.getMediaId();
        CharSequence title = mediaDescription.getTitle();
        CharSequence subtitle = mediaDescription.getSubtitle();
        CharSequence description = mediaDescription.getDescription();
        Bitmap iconBitmap = mediaDescription.getIconBitmap();
        Uri iconUri = mediaDescription.getIconUri();
        Bundle extras = mediaDescription.getExtras();
        if (extras != null) {
            AbstractC1025k.a(extras);
            mediaUri = (Uri) extras.getParcelable("android.support.v4.media.description.MEDIA_URI");
        } else {
            mediaUri = null;
        }
        if (mediaUri == null) {
            bundle = extras;
        } else if (extras.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") && extras.size() == 2) {
            bundle = null;
        } else {
            extras.remove("android.support.v4.media.description.MEDIA_URI");
            extras.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            bundle = extras;
        }
        if (mediaUri == null) {
            mediaUri = Build.VERSION.SDK_INT >= 23 ? mediaDescription.getMediaUri() : null;
        }
        MediaDescriptionCompat mediaDescriptionCompat = new MediaDescriptionCompat(mediaId, title, subtitle, description, iconBitmap, iconUri, bundle, mediaUri);
        mediaDescriptionCompat.A = objCreateFromParcel;
        return mediaDescriptionCompat;
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new MediaDescriptionCompat[i];
    }
}

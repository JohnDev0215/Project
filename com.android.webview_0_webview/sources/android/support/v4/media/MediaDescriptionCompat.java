package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.C0690e;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0690e();
    public Object A;
    public final String s;
    public final CharSequence t;
    public final CharSequence u;
    public final CharSequence v;
    public final Bitmap w;
    public final Uri x;
    public final Bundle y;
    public final Uri z;

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.s = str;
        this.t = charSequence;
        this.u = charSequence2;
        this.v = charSequence3;
        this.w = bitmap;
        this.x = uri;
        this.y = bundle;
        this.z = uri2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return ((Object) this.t) + ", " + ((Object) this.u) + ", " + ((Object) this.v);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int i2 = Build.VERSION.SDK_INT;
        Object objBuild = this.A;
        if (objBuild == null) {
            MediaDescription.Builder builder = new MediaDescription.Builder();
            builder.setMediaId(this.s);
            builder.setTitle(this.t);
            builder.setSubtitle(this.u);
            builder.setDescription(this.v);
            builder.setIconBitmap(this.w);
            builder.setIconUri(this.x);
            Bundle bundle = this.y;
            if (i2 < 23 && this.z != null) {
                if (bundle == null) {
                    bundle = new Bundle();
                    bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
                }
                bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.z);
            }
            builder.setExtras(bundle);
            if (i2 >= 23) {
                builder.setMediaUri(this.z);
            }
            objBuild = builder.build();
            this.A = objBuild;
        }
        ((MediaDescription) objBuild).writeToParcel(parcel, i);
    }
}

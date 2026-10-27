package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import defpackage.C0635d;

/* JADX INFO: renamed from: d, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0635d implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(final Parcel parcel) {
        return new Parcelable(parcel) { // from class: android.support.v4.media.MediaBrowserCompat$MediaItem
            public static final Parcelable.Creator CREATOR = new C0635d();
            public final int s;
            public final MediaDescriptionCompat t;

            {
                this.s = parcel.readInt();
                this.t = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "MediaItem{mFlags=" + this.s + ", mDescription=" + this.t + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel2, int i) {
                parcel2.writeInt(this.s);
                this.t.writeToParcel(parcel2, i);
            }
        };
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new MediaBrowserCompat$MediaItem[i];
    }
}

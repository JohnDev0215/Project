package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat$QueueItem;
import defpackage.C0858h;
import defpackage.Z7;

/* JADX INFO: renamed from: h, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0858h implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(final Parcel parcel) {
        return new Parcelable(parcel) { // from class: android.support.v4.media.session.MediaSessionCompat$QueueItem
            public static final Parcelable.Creator CREATOR = new C0858h();
            public final MediaDescriptionCompat s;
            public final long t;

            {
                this.s = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                this.t = parcel.readLong();
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                StringBuilder sbF = Z7.f("MediaSession.QueueItem {Description=");
                sbF.append(this.s);
                sbF.append(", Id=");
                sbF.append(this.t);
                sbF.append(" }");
                return sbF.toString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel2, int i) {
                this.s.writeToParcel(parcel2, i);
                parcel2.writeLong(this.t);
            }
        };
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new MediaSessionCompat$QueueItem[i];
    }
}

package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$Token;
import defpackage.C0969j;

/* JADX INFO: renamed from: j, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0969j implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        final Parcelable parcelable = parcel.readParcelable(null);
        return new Parcelable(parcelable) { // from class: android.support.v4.media.session.MediaSessionCompat$Token
            public static final Parcelable.Creator CREATOR = new C0969j();
            public final Object s;

            {
                this.s = parcelable;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof MediaSessionCompat$Token)) {
                    return false;
                }
                MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) obj;
                Object obj2 = this.s;
                if (obj2 == null) {
                    return mediaSessionCompat$Token.s == null;
                }
                Object obj3 = mediaSessionCompat$Token.s;
                if (obj3 == null) {
                    return false;
                }
                return obj2.equals(obj3);
            }

            public int hashCode() {
                Object obj = this.s;
                if (obj == null) {
                    return 0;
                }
                return obj.hashCode();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel2, int i) {
                parcel2.writeParcelable((Parcelable) this.s, i);
            }
        };
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new MediaSessionCompat$Token[i];
    }
}

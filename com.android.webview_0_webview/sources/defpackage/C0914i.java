package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper;
import defpackage.C0914i;

/* JADX INFO: renamed from: i, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0914i implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(final Parcel parcel) {
        return new Parcelable(parcel) { // from class: android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper
            public static final Parcelable.Creator CREATOR = new C0914i();
            public ResultReceiver s;

            {
                this.s = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel2, int i) {
                this.s.writeToParcel(parcel2, i);
            }
        };
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new MediaSessionCompat$ResultReceiverWrapper[i];
    }
}

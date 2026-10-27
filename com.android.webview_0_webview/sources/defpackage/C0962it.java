package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.base.UnguessableToken;

/* JADX INFO: renamed from: it, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0962it implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        long j = parcel.readLong();
        long j2 = parcel.readLong();
        if (j == 0 || j2 == 0) {
            return null;
        }
        return new UnguessableToken(j, j2, null);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new UnguessableToken[i];
    }
}

package defpackage;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;

/* JADX INFO: renamed from: Ge, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0112Ge implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        PendingIntent pendingIntent = null;
        int iK = 0;
        int iK2 = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iK2 = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 2) {
                strN = AbstractC1060kf.n(parcel, i);
            } else if (i2 == 3) {
                pendingIntent = (PendingIntent) AbstractC1060kf.b(parcel, i, PendingIntent.CREATOR);
            } else if (i2 != 1000) {
                AbstractC1060kf.f(parcel, i);
            } else {
                iK = AbstractC1060kf.k(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Status(iK, iK2, strN, pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Status[i];
    }
}

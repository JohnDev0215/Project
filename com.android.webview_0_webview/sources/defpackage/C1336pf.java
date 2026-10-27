package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzam;

/* JADX INFO: renamed from: pf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1336pf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        IBinder iBinderO = null;
        ConnectionResult connectionResult = null;
        int iK = 0;
        boolean zI = false;
        boolean zI2 = false;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 2) {
                iBinderO = AbstractC1060kf.o(parcel, i);
            } else if (i2 == 3) {
                connectionResult = (ConnectionResult) AbstractC1060kf.b(parcel, i, ConnectionResult.CREATOR);
            } else if (i2 == 4) {
                zI = AbstractC1060kf.i(parcel, i);
            } else if (i2 != 5) {
                AbstractC1060kf.f(parcel, i);
            } else {
                zI2 = AbstractC1060kf.i(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzam(iK, iBinderO, connectionResult, zI, zI2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzam[i];
    }
}

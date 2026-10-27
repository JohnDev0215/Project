package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.signin.internal.zzm;

/* JADX INFO: renamed from: mj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1175mj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        ConnectionResult connectionResult = null;
        zzam zzamVar = null;
        int iK = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 2) {
                connectionResult = (ConnectionResult) AbstractC1060kf.b(parcel, i, ConnectionResult.CREATOR);
            } else if (i2 != 3) {
                AbstractC1060kf.f(parcel, i);
            } else {
                zzamVar = (zzam) AbstractC1060kf.b(parcel, i, zzam.CREATOR);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzm(iK, connectionResult, zzamVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzm[i];
    }
}

package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.internal.zzbh;
import java.util.List;

/* JADX INFO: renamed from: Li, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0206Li implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        List listH = zzbh.B;
        LocationRequest locationRequest = null;
        String strN = null;
        String strN2 = null;
        boolean zI = false;
        boolean zI2 = false;
        boolean zI3 = false;
        boolean zI4 = false;
        boolean zI5 = false;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 != 1) {
                switch (i2) {
                    case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                        listH = AbstractC1060kf.h(parcel, i, ClientIdentity.CREATOR);
                        break;
                    case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                        strN = AbstractC1060kf.n(parcel, i);
                        break;
                    case 7:
                        zI = AbstractC1060kf.i(parcel, i);
                        break;
                    case 8:
                        zI2 = AbstractC1060kf.i(parcel, i);
                        break;
                    case 9:
                        zI3 = AbstractC1060kf.i(parcel, i);
                        break;
                    case 10:
                        strN2 = AbstractC1060kf.n(parcel, i);
                        break;
                    case 11:
                        zI4 = AbstractC1060kf.i(parcel, i);
                        break;
                    case 12:
                        zI5 = AbstractC1060kf.i(parcel, i);
                        break;
                    default:
                        AbstractC1060kf.f(parcel, i);
                        break;
                }
            } else {
                locationRequest = (LocationRequest) AbstractC1060kf.b(parcel, i, LocationRequest.CREATOR);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzbh(locationRequest, listH, strN, zI, zI2, zI3, strN2, zI4, zI5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbh[i];
    }
}

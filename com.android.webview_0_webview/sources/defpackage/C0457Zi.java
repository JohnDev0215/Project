package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.LocationRequest;

/* JADX INFO: renamed from: Zi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0457Zi implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        long jL = 3600000;
        long jL2 = 600000;
        long jL3 = Long.MAX_VALUE;
        long jL4 = 0;
        int iK = 102;
        boolean zI = false;
        int iK2 = Integer.MAX_VALUE;
        float fM = 0.0f;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iK = AbstractC1060kf.k(parcel, i);
                    break;
                case 2:
                    jL = AbstractC1060kf.l(parcel, i);
                    break;
                case 3:
                    jL2 = AbstractC1060kf.l(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    zI = AbstractC1060kf.i(parcel, i);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    jL3 = AbstractC1060kf.l(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    iK2 = AbstractC1060kf.k(parcel, i);
                    break;
                case 7:
                    fM = AbstractC1060kf.m(parcel, i);
                    break;
                case 8:
                    jL4 = AbstractC1060kf.l(parcel, i);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new LocationRequest(iK, jL, jL2, zI, jL3, iK2, fM, jL4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationRequest[i];
    }
}

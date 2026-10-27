package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Oj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0260Oj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        int iK = 0;
        int iK2 = 0;
        int iK3 = 0;
        int iK4 = 0;
        int iK5 = 0;
        int iK6 = 0;
        boolean zI = false;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    iK = AbstractC1060kf.k(parcel, i);
                    break;
                case 3:
                    iK2 = AbstractC1060kf.k(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    iK3 = AbstractC1060kf.k(parcel, i);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    iK4 = AbstractC1060kf.k(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    iK5 = AbstractC1060kf.k(parcel, i);
                    break;
                case 7:
                    iK6 = AbstractC1060kf.k(parcel, i);
                    break;
                case 8:
                    zI = AbstractC1060kf.i(parcel, i);
                    break;
                case 9:
                    strN = AbstractC1060kf.n(parcel, i);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode.CalendarDateTime(iK, iK2, iK3, iK4, iK5, iK6, zI, strN);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.CalendarDateTime[i];
    }
}

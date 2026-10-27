package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Pj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0278Pj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        String strN2 = null;
        String strN3 = null;
        String strN4 = null;
        String strN5 = null;
        String strN6 = null;
        String strN7 = null;
        String strN8 = null;
        String strN9 = null;
        String strN10 = null;
        String strN11 = null;
        String strN12 = null;
        String strN13 = null;
        String strN14 = null;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    strN = AbstractC1060kf.n(parcel, i);
                    break;
                case 3:
                    strN2 = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    strN3 = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    strN4 = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    strN5 = AbstractC1060kf.n(parcel, i);
                    break;
                case 7:
                    strN6 = AbstractC1060kf.n(parcel, i);
                    break;
                case 8:
                    strN7 = AbstractC1060kf.n(parcel, i);
                    break;
                case 9:
                    strN8 = AbstractC1060kf.n(parcel, i);
                    break;
                case 10:
                    strN9 = AbstractC1060kf.n(parcel, i);
                    break;
                case 11:
                    strN10 = AbstractC1060kf.n(parcel, i);
                    break;
                case 12:
                    strN11 = AbstractC1060kf.n(parcel, i);
                    break;
                case 13:
                    strN12 = AbstractC1060kf.n(parcel, i);
                    break;
                case 14:
                    strN13 = AbstractC1060kf.n(parcel, i);
                    break;
                case 15:
                    strN14 = AbstractC1060kf.n(parcel, i);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode.DriverLicense(strN, strN2, strN3, strN4, strN5, strN6, strN7, strN8, strN9, strN10, strN11, strN12, strN13, strN14);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.DriverLicense[i];
    }
}

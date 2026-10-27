package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Qj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0296Qj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        Barcode.PersonName personName = null;
        String strN = null;
        String strN2 = null;
        Barcode.Phone[] phoneArr = null;
        Barcode.Email[] emailArr = null;
        String[] strArrD = null;
        Barcode.Address[] addressArr = null;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    personName = (Barcode.PersonName) AbstractC1060kf.b(parcel, i, Barcode.PersonName.CREATOR);
                    break;
                case 3:
                    strN = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    strN2 = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    phoneArr = (Barcode.Phone[]) AbstractC1060kf.g(parcel, i, Barcode.Phone.CREATOR);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    emailArr = (Barcode.Email[]) AbstractC1060kf.g(parcel, i, Barcode.Email.CREATOR);
                    break;
                case 7:
                    strArrD = AbstractC1060kf.d(parcel, i);
                    break;
                case 8:
                    addressArr = (Barcode.Address[]) AbstractC1060kf.g(parcel, i, Barcode.Address.CREATOR);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode.ContactInfo(personName, strN, strN2, phoneArr, emailArr, strArrD, addressArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.ContactInfo[i];
    }
}

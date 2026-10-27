package defpackage;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Lj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0207Lj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        String strN2 = null;
        Point[] pointArr = null;
        Barcode.Email email = null;
        Barcode.Phone phone = null;
        Barcode.Sms sms = null;
        Barcode.WiFi wiFi = null;
        Barcode.UrlBookmark urlBookmark = null;
        Barcode.GeoPoint geoPoint = null;
        Barcode.CalendarEvent calendarEvent = null;
        Barcode.ContactInfo contactInfo = null;
        Barcode.DriverLicense driverLicense = null;
        byte[] bArrCreateByteArray = null;
        int iK = 0;
        int iK2 = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    iK = AbstractC1060kf.k(parcel, i);
                    break;
                case 3:
                    strN = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    strN2 = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    iK2 = AbstractC1060kf.k(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    pointArr = (Point[]) AbstractC1060kf.g(parcel, i, Point.CREATOR);
                    break;
                case 7:
                    email = (Barcode.Email) AbstractC1060kf.b(parcel, i, Barcode.Email.CREATOR);
                    break;
                case 8:
                    phone = (Barcode.Phone) AbstractC1060kf.b(parcel, i, Barcode.Phone.CREATOR);
                    break;
                case 9:
                    sms = (Barcode.Sms) AbstractC1060kf.b(parcel, i, Barcode.Sms.CREATOR);
                    break;
                case 10:
                    wiFi = (Barcode.WiFi) AbstractC1060kf.b(parcel, i, Barcode.WiFi.CREATOR);
                    break;
                case 11:
                    urlBookmark = (Barcode.UrlBookmark) AbstractC1060kf.b(parcel, i, Barcode.UrlBookmark.CREATOR);
                    break;
                case 12:
                    geoPoint = (Barcode.GeoPoint) AbstractC1060kf.b(parcel, i, Barcode.GeoPoint.CREATOR);
                    break;
                case 13:
                    calendarEvent = (Barcode.CalendarEvent) AbstractC1060kf.b(parcel, i, Barcode.CalendarEvent.CREATOR);
                    break;
                case 14:
                    contactInfo = (Barcode.ContactInfo) AbstractC1060kf.b(parcel, i, Barcode.ContactInfo.CREATOR);
                    break;
                case 15:
                    driverLicense = (Barcode.DriverLicense) AbstractC1060kf.b(parcel, i, Barcode.DriverLicense.CREATOR);
                    break;
                case 16:
                    int iA = AbstractC1060kf.a(parcel, i);
                    int iDataPosition = parcel.dataPosition();
                    if (iA != 0) {
                        bArrCreateByteArray = parcel.createByteArray();
                        parcel.setDataPosition(iDataPosition + iA);
                    } else {
                        bArrCreateByteArray = null;
                    }
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode(iK, strN, strN2, iK2, pointArr, email, phone, sms, wiFi, urlBookmark, geoPoint, calendarEvent, contactInfo, driverLicense, bArrCreateByteArray);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode[i];
    }
}

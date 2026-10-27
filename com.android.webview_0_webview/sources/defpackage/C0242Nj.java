package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Nj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0242Nj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        String strN2 = null;
        String strN3 = null;
        String strN4 = null;
        String strN5 = null;
        Barcode.CalendarDateTime calendarDateTime = null;
        Barcode.CalendarDateTime calendarDateTime2 = null;
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
                    calendarDateTime = (Barcode.CalendarDateTime) AbstractC1060kf.b(parcel, i, Barcode.CalendarDateTime.CREATOR);
                    break;
                case 8:
                    calendarDateTime2 = (Barcode.CalendarDateTime) AbstractC1060kf.b(parcel, i, Barcode.CalendarDateTime.CREATOR);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode.CalendarEvent(strN, strN2, strN3, strN4, strN5, calendarDateTime, calendarDateTime2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.CalendarEvent[i];
    }
}

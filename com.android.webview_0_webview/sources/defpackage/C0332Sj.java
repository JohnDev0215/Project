package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Sj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0332Sj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        String strN2 = null;
        String strN3 = null;
        int iK = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 3) {
                strN = AbstractC1060kf.n(parcel, i);
            } else if (i2 == 4) {
                strN2 = AbstractC1060kf.n(parcel, i);
            } else if (i2 != 5) {
                AbstractC1060kf.f(parcel, i);
            } else {
                strN3 = AbstractC1060kf.n(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode.Email(iK, strN, strN2, strN3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.Email[i];
    }
}

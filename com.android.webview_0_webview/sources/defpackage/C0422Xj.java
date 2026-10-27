package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Xj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0422Xj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        String strN2 = null;
        int iK = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                strN = AbstractC1060kf.n(parcel, i);
            } else if (i2 == 3) {
                strN2 = AbstractC1060kf.n(parcel, i);
            } else if (i2 != 4) {
                AbstractC1060kf.f(parcel, i);
            } else {
                iK = AbstractC1060kf.k(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode.WiFi(strN, strN2, iK);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.WiFi[i];
    }
}

package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Kj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0189Kj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        int iK = 0;
        String[] strArrD = null;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 != 3) {
                AbstractC1060kf.f(parcel, i);
            } else {
                strArrD = AbstractC1060kf.d(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode.Address(iK, strArrD);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.Address[i];
    }
}

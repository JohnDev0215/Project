package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* JADX INFO: renamed from: Rj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0314Rj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        double d = 0.0d;
        double d2 = 0.0d;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                AbstractC1060kf.c(parcel, i, 8);
                d = parcel.readDouble();
            } else if (i2 != 3) {
                AbstractC1060kf.f(parcel, i);
            } else {
                AbstractC1060kf.c(parcel, i, 8);
                d2 = parcel.readDouble();
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Barcode.GeoPoint(d, d2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.GeoPoint[i];
    }
}

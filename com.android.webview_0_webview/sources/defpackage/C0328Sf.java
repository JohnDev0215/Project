package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;

/* JADX INFO: renamed from: Sf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0328Sf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        int iK = 0;
        long jL = -1;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                strN = AbstractC1060kf.n(parcel, i);
            } else if (i2 == 2) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 != 3) {
                AbstractC1060kf.f(parcel, i);
            } else {
                jL = AbstractC1060kf.l(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Feature(strN, iK, jL);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Feature[i];
    }
}

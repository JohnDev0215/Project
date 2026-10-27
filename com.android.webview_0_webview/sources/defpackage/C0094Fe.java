package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;

/* JADX INFO: renamed from: Fe, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0094Fe implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        int iK = 0;
        String strN = null;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 != 2) {
                AbstractC1060kf.f(parcel, i);
            } else {
                strN = AbstractC1060kf.n(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new Scope(iK, strN);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Scope[i];
    }
}

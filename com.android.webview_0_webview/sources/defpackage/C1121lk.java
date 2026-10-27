package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.face.internal.client.LandmarkParcel;

/* JADX INFO: renamed from: lk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1121lk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        float fM = 0.0f;
        float fM2 = 0.0f;
        int iK = 0;
        int iK2 = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 2) {
                fM = AbstractC1060kf.m(parcel, i);
            } else if (i2 == 3) {
                fM2 = AbstractC1060kf.m(parcel, i);
            } else if (i2 != 4) {
                AbstractC1060kf.f(parcel, i);
            } else {
                iK2 = AbstractC1060kf.k(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new LandmarkParcel(iK, fM, fM2, iK2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new LandmarkParcel[i];
    }
}

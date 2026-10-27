package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.ConnectionInfo;

/* JADX INFO: renamed from: sf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1500sf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        Bundle bundleP = null;
        Feature[] featureArr = null;
        int iK = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                bundleP = AbstractC1060kf.p(parcel, i);
            } else if (i2 == 2) {
                featureArr = (Feature[]) AbstractC1060kf.g(parcel, i, Feature.CREATOR);
            } else if (i2 != 3) {
                AbstractC1060kf.f(parcel, i);
            } else {
                iK = AbstractC1060kf.k(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new ConnectionInfo(bundleP, featureArr, iK);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ConnectionInfo[i];
    }
}

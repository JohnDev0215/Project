package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.text.internal.client.zza;

/* JADX INFO: renamed from: qk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1396qk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        int iK = 0;
        int iK2 = 0;
        int iK3 = 0;
        int iK4 = 0;
        float fM = 0.0f;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 3) {
                iK2 = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 4) {
                iK3 = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 5) {
                iK4 = AbstractC1060kf.k(parcel, i);
            } else if (i2 != 6) {
                AbstractC1060kf.f(parcel, i);
            } else {
                fM = AbstractC1060kf.m(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zza(iK, iK2, iK3, iK4, fM);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zza[i];
    }
}

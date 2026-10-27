package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzipv;

/* JADX INFO: renamed from: Yg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0437Yg implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        int iK = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            if ((65535 & i) != 2) {
                AbstractC1060kf.f(parcel, i);
            } else {
                iK = AbstractC1060kf.k(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzipv(iK);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzipv[i];
    }
}

package defpackage;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.face.internal.client.zzb;

/* JADX INFO: renamed from: ck, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0619ck implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        PointF[] pointFArr = null;
        int iK = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                pointFArr = (PointF[]) AbstractC1060kf.g(parcel, i, PointF.CREATOR);
            } else if (i2 != 3) {
                AbstractC1060kf.f(parcel, i);
            } else {
                iK = AbstractC1060kf.k(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzb(pointFArr, iK);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzb[i];
    }
}

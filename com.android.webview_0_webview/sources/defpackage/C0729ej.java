package defpackage;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.signin.internal.zzb;

/* JADX INFO: renamed from: ej, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0729ej implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        int iK = 0;
        Intent intent = null;
        int iK2 = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 2) {
                iK2 = AbstractC1060kf.k(parcel, i);
            } else if (i2 != 3) {
                AbstractC1060kf.f(parcel, i);
            } else {
                intent = (Intent) AbstractC1060kf.b(parcel, i, Intent.CREATOR);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzb(iK, iK2, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzb[i];
    }
}

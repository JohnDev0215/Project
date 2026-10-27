package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.internal.zzae;

/* JADX INFO: renamed from: zi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1888zi implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        Status status = null;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            if ((65535 & i) != 1) {
                AbstractC1060kf.f(parcel, i);
            } else {
                status = (Status) AbstractC1060kf.b(parcel, i, Status.CREATOR);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzae(status);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzae[i];
    }
}

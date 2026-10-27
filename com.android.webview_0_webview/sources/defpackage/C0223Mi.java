package defpackage;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.internal.zzbh;
import com.google.android.gms.location.internal.zzbj;

/* JADX INFO: renamed from: Mi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0223Mi implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        zzbh zzbhVar = null;
        IBinder iBinderO = null;
        PendingIntent pendingIntent = null;
        IBinder iBinderO2 = null;
        IBinder iBinderO3 = null;
        int iK = 1;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iK = AbstractC1060kf.k(parcel, i);
                    break;
                case 2:
                    zzbhVar = (zzbh) AbstractC1060kf.b(parcel, i, zzbh.CREATOR);
                    break;
                case 3:
                    iBinderO = AbstractC1060kf.o(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    pendingIntent = (PendingIntent) AbstractC1060kf.b(parcel, i, PendingIntent.CREATOR);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    iBinderO2 = AbstractC1060kf.o(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    iBinderO3 = AbstractC1060kf.o(parcel, i);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzbj(iK, zzbhVar, iBinderO, pendingIntent, iBinderO2, iBinderO3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbj[i];
    }
}

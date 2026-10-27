package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.text.internal.client.zza;
import com.google.android.gms.vision.text.internal.client.zzf;
import com.google.android.gms.vision.text.internal.client.zzp;

/* JADX INFO: renamed from: uk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1615uk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        zzp[] zzpVarArr = null;
        zza zzaVar = null;
        zza zzaVar2 = null;
        zza zzaVar3 = null;
        String strN = null;
        String strN2 = null;
        float fM = 0.0f;
        int iK = 0;
        boolean zI = false;
        int iK2 = 0;
        int iK3 = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    zzpVarArr = (zzp[]) AbstractC1060kf.g(parcel, i, zzp.CREATOR);
                    break;
                case 3:
                    zzaVar = (zza) AbstractC1060kf.b(parcel, i, zza.CREATOR);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    zzaVar2 = (zza) AbstractC1060kf.b(parcel, i, zza.CREATOR);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    zzaVar3 = (zza) AbstractC1060kf.b(parcel, i, zza.CREATOR);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    strN = AbstractC1060kf.n(parcel, i);
                    break;
                case 7:
                    fM = AbstractC1060kf.m(parcel, i);
                    break;
                case 8:
                    strN2 = AbstractC1060kf.n(parcel, i);
                    break;
                case 9:
                    iK = AbstractC1060kf.k(parcel, i);
                    break;
                case 10:
                    zI = AbstractC1060kf.i(parcel, i);
                    break;
                case 11:
                    iK2 = AbstractC1060kf.k(parcel, i);
                    break;
                case 12:
                    iK3 = AbstractC1060kf.k(parcel, i);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzf(zzpVarArr, zzaVar, zzaVar2, zzaVar3, strN, fM, strN2, iK, zI, iK2, iK3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzf[i];
    }
}

package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;

/* JADX INFO: renamed from: jc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1001jc implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        String strN2 = null;
        String strN3 = null;
        String strN4 = null;
        Uri uri = null;
        String strN5 = null;
        String strN6 = null;
        ArrayList arrayListH = null;
        String strN7 = null;
        String strN8 = null;
        long jL = 0;
        int iK = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iK = AbstractC1060kf.k(parcel, i);
                    break;
                case 2:
                    strN = AbstractC1060kf.n(parcel, i);
                    break;
                case 3:
                    strN2 = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    strN3 = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    strN4 = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    uri = (Uri) AbstractC1060kf.b(parcel, i, Uri.CREATOR);
                    break;
                case 7:
                    strN5 = AbstractC1060kf.n(parcel, i);
                    break;
                case 8:
                    jL = AbstractC1060kf.l(parcel, i);
                    break;
                case 9:
                    strN6 = AbstractC1060kf.n(parcel, i);
                    break;
                case 10:
                    arrayListH = AbstractC1060kf.h(parcel, i, Scope.CREATOR);
                    break;
                case 11:
                    strN7 = AbstractC1060kf.n(parcel, i);
                    break;
                case 12:
                    strN8 = AbstractC1060kf.n(parcel, i);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new GoogleSignInAccount(iK, strN, strN2, strN3, strN4, uri, strN5, jL, strN6, arrayListH, strN7, strN8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}

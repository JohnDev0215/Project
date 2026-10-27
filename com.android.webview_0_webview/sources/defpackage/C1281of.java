package defpackage;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.zzak;

/* JADX INFO: renamed from: of, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1281of implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        int iK = 0;
        Account account = null;
        GoogleSignInAccount googleSignInAccount = null;
        int iK2 = 0;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iK = AbstractC1060kf.k(parcel, i);
            } else if (i2 == 2) {
                account = (Account) AbstractC1060kf.b(parcel, i, Account.CREATOR);
            } else if (i2 == 3) {
                iK2 = AbstractC1060kf.k(parcel, i);
            } else if (i2 != 4) {
                AbstractC1060kf.f(parcel, i);
            } else {
                googleSignInAccount = (GoogleSignInAccount) AbstractC1060kf.b(parcel, i, GoogleSignInAccount.CREATOR);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzak(iK, account, iK2, googleSignInAccount);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzak[i];
    }
}

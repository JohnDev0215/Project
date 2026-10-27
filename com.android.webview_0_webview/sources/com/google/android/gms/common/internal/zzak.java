package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C1281of;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class zzak extends zza {
    public static final Parcelable.Creator CREATOR = new C1281of();
    public final int s;
    public final Account t;
    public final int u;
    public final GoogleSignInAccount v;

    public zzak(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.s = i;
        this.t = account;
        this.u = i2;
        this.v = googleSignInAccount;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.d(parcel, 2, this.t, i, false);
        AbstractC1116lf.l(parcel, 3, this.u);
        AbstractC1116lf.d(parcel, 4, this.v, i, false);
        AbstractC1116lf.j(parcel, iN);
    }

    public zzak(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this.s = 2;
        this.t = account;
        this.u = i;
        this.v = googleSignInAccount;
    }
}

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractBinderC0130He;
import defpackage.AbstractC1116lf;
import defpackage.AbstractC1278oc;
import defpackage.C0095Ff;
import defpackage.C1665vf;
import defpackage.InterfaceC0502af;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GetServiceRequest extends zza {
    public static final Parcelable.Creator CREATOR = new C1665vf();
    public Feature[] A;
    public Feature[] B;
    public boolean C;
    public int D;
    public final int s;
    public final int t;
    public int u;
    public String v;
    public IBinder w;
    public Scope[] x;
    public Bundle y;
    public Account z;

    public GetServiceRequest(int i) {
        this.s = 4;
        this.u = AbstractC1278oc.a;
        this.t = i;
        this.C = true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.l(parcel, 2, this.t);
        AbstractC1116lf.l(parcel, 3, this.u);
        AbstractC1116lf.e(parcel, 4, this.v, false);
        AbstractC1116lf.c(parcel, 5, this.w);
        AbstractC1116lf.g(parcel, 6, this.x, i);
        Bundle bundle = this.y;
        if (bundle != null) {
            int i2 = AbstractC1116lf.i(parcel, 7);
            parcel.writeBundle(bundle);
            AbstractC1116lf.j(parcel, i2);
        }
        AbstractC1116lf.d(parcel, 8, this.z, i, false);
        AbstractC1116lf.g(parcel, 10, this.A, i);
        AbstractC1116lf.g(parcel, 11, this.B, i);
        AbstractC1116lf.f(parcel, 12, this.C);
        AbstractC1116lf.l(parcel, 13, this.D);
        AbstractC1116lf.j(parcel, iN);
    }

    public GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, Feature[] featureArr, Feature[] featureArr2, boolean z, int i4) {
        Account accountV;
        InterfaceC0502af c0095Ff;
        this.s = i;
        this.t = i2;
        this.u = i3;
        if ("com.google.android.gms".equals(str)) {
            this.v = "com.google.android.gms";
        } else {
            this.v = str;
        }
        if (i < 2) {
            if (iBinder != null) {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                if (iInterfaceQueryLocalInterface instanceof InterfaceC0502af) {
                    c0095Ff = (InterfaceC0502af) iInterfaceQueryLocalInterface;
                } else {
                    c0095Ff = new C0095Ff(iBinder);
                }
                accountV = AbstractBinderC0130He.v(c0095Ff);
            } else {
                accountV = null;
            }
            this.z = accountV;
        } else {
            this.w = iBinder;
            this.z = account;
        }
        this.x = scopeArr;
        this.y = bundle;
        this.A = featureArr;
        this.B = featureArr2;
        this.C = z;
        this.D = i4;
    }
}

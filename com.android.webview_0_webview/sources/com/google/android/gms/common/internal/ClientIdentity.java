package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC0893hf;
import defpackage.AbstractC1116lf;
import defpackage.C1391qf;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ClientIdentity extends zza {
    public static final Parcelable.Creator CREATOR = new C1391qf();
    public final int s;
    public final String t;

    public ClientIdentity(int i, String str) {
        this.s = i;
        this.t = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ClientIdentity)) {
            ClientIdentity clientIdentity = (ClientIdentity) obj;
            if (clientIdentity.s == this.s && AbstractC0893hf.a(clientIdentity.t, this.t)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.s;
    }

    public String toString() {
        int i = this.s;
        String str = this.t;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append(i);
        sb.append(":");
        sb.append(str);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.e(parcel, 2, this.t, false);
        AbstractC1116lf.j(parcel, iN);
    }
}

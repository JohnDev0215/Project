package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC0893hf;
import defpackage.AbstractC1116lf;
import defpackage.C0310Rf;
import defpackage.C0571bs;
import defpackage.C0837gf;
import java.util.Arrays;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ConnectionResult extends zza {
    public final int s;
    public final int t;
    public final PendingIntent u;
    public final String v;
    public static final ConnectionResult w = new ConnectionResult(0);
    public static final Parcelable.Creator CREATOR = new C0310Rf();

    public ConnectionResult(int i) {
        this.s = 1;
        this.t = i;
        this.u = null;
        this.v = null;
    }

    public static String b(int i) {
        if (i == 99) {
            return "UNFINISHED";
        }
        if (i == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i) {
            case -1:
                return "UNKNOWN";
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                return "SIGN_IN_REQUIRED";
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                return "INVALID_ACCOUNT";
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i) {
                    case 13:
                        return "CANCELED";
                    case 14:
                        return "TIMEOUT";
                    case 15:
                        return "INTERRUPTED";
                    case 16:
                        return "API_UNAVAILABLE";
                    case 17:
                        return "SIGN_IN_FAILED";
                    case 18:
                        return "SERVICE_UPDATING";
                    case 19:
                        return "SERVICE_MISSING_PERMISSION";
                    case 20:
                        return "RESTRICTED_PROFILE";
                    case 21:
                        return "API_VERSION_UPDATE_REQUIRED";
                    default:
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("UNKNOWN_ERROR_CODE(");
                        sb.append(i);
                        sb.append(")");
                        return sb.toString();
                }
        }
    }

    public final boolean e() {
        return (this.t == 0 || this.u == null) ? false : true;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        return this.t == connectionResult.t && AbstractC0893hf.a(this.u, connectionResult.u) && AbstractC0893hf.a(this.v, connectionResult.v);
    }

    public final boolean f() {
        return this.t == 0;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.t), this.u, this.v});
    }

    public final String toString() {
        C0837gf c0837gfB = AbstractC0893hf.b(this);
        c0837gfB.a("statusCode", b(this.t));
        c0837gfB.a("resolution", this.u);
        c0837gfB.a("message", this.v);
        return c0837gfB.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.l(parcel, 2, this.t);
        AbstractC1116lf.d(parcel, 3, this.u, i, false);
        AbstractC1116lf.e(parcel, 4, this.v, false);
        AbstractC1116lf.j(parcel, iN);
    }

    public ConnectionResult(int i, int i2, PendingIntent pendingIntent, String str) {
        this.s = i;
        this.t = i2;
        this.u = pendingIntent;
        this.v = str;
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this.s = 1;
        this.t = i;
        this.u = pendingIntent;
        this.v = null;
    }
}

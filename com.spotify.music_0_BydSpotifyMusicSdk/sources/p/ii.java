package p;

import com.google.android.datatransport.cct.internal.NetworkConnectionInfo$MobileSubtype;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo$NetworkType;

/* JADX INFO: loaded from: classes.dex */
public final class ii extends wm1 {
    public final NetworkConnectionInfo$NetworkType a;
    public final NetworkConnectionInfo$MobileSubtype b;

    public ii(NetworkConnectionInfo$NetworkType networkConnectionInfo$NetworkType, NetworkConnectionInfo$MobileSubtype networkConnectionInfo$MobileSubtype) {
        this.a = networkConnectionInfo$NetworkType;
        this.b = networkConnectionInfo$MobileSubtype;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof wm1)) {
            return false;
        }
        wm1 wm1Var = (wm1) obj;
        NetworkConnectionInfo$NetworkType networkConnectionInfo$NetworkType = this.a;
        if (networkConnectionInfo$NetworkType != null ? networkConnectionInfo$NetworkType.equals(((ii) wm1Var).a) : ((ii) wm1Var).a == null) {
            NetworkConnectionInfo$MobileSubtype networkConnectionInfo$MobileSubtype = this.b;
            if (networkConnectionInfo$MobileSubtype == null) {
                if (((ii) wm1Var).b == null) {
                    return true;
                }
            } else if (networkConnectionInfo$MobileSubtype.equals(((ii) wm1Var).b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        NetworkConnectionInfo$NetworkType networkConnectionInfo$NetworkType = this.a;
        int iHashCode = ((networkConnectionInfo$NetworkType == null ? 0 : networkConnectionInfo$NetworkType.hashCode()) ^ 1000003) * 1000003;
        NetworkConnectionInfo$MobileSubtype networkConnectionInfo$MobileSubtype = this.b;
        return (networkConnectionInfo$MobileSubtype != null ? networkConnectionInfo$MobileSubtype.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        return "NetworkConnectionInfo{networkType=" + this.a + ", mobileSubtype=" + this.b + "}";
    }
}

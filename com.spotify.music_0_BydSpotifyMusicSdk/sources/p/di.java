package p;

import com.google.firebase.installations.remote.InstallationResponse$ResponseCode;

/* JADX INFO: loaded from: classes.dex */
public final class di {
    public final String a;
    public final String b;
    public final String c;
    public final vi d;
    public final InstallationResponse$ResponseCode e;

    public di(String str, String str2, String str3, vi viVar, InstallationResponse$ResponseCode installationResponse$ResponseCode) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = viVar;
        this.e = installationResponse$ResponseCode;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof di)) {
            return false;
        }
        di diVar = (di) obj;
        String str = this.a;
        if (str != null ? str.equals(diVar.a) : diVar.a == null) {
            String str2 = this.b;
            if (str2 != null ? str2.equals(diVar.b) : diVar.b == null) {
                String str3 = this.c;
                if (str3 != null ? str3.equals(diVar.c) : diVar.c == null) {
                    vi viVar = this.d;
                    if (viVar != null ? viVar.equals(diVar.d) : diVar.d == null) {
                        InstallationResponse$ResponseCode installationResponse$ResponseCode = this.e;
                        if (installationResponse$ResponseCode == null) {
                            if (diVar.e == null) {
                                return true;
                            }
                        } else if (installationResponse$ResponseCode.equals(diVar.e)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.a;
        int iHashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.b;
        int iHashCode2 = (iHashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.c;
        int iHashCode3 = (iHashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        vi viVar = this.d;
        int iHashCode4 = (iHashCode3 ^ (viVar == null ? 0 : viVar.hashCode())) * 1000003;
        InstallationResponse$ResponseCode installationResponse$ResponseCode = this.e;
        return (installationResponse$ResponseCode != null ? installationResponse$ResponseCode.hashCode() : 0) ^ iHashCode4;
    }

    public final String toString() {
        return "InstallationResponse{uri=" + this.a + ", fid=" + this.b + ", refreshToken=" + this.c + ", authToken=" + this.d + ", responseCode=" + this.e + "}";
    }
}

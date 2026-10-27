package p;

import com.google.firebase.installations.local.PersistedInstallation$RegistrationStatus;

/* JADX INFO: loaded from: classes.dex */
public final class ki {
    public static final /* synthetic */ int h = 0;
    public final String a;
    public final PersistedInstallation$RegistrationStatus b;
    public final String c;
    public final String d;
    public final long e;
    public final long f;
    public final String g;

    static {
        jm2 jm2Var = new jm2(6);
        jm2Var.f = 0L;
        jm2Var.j(PersistedInstallation$RegistrationStatus.c);
        jm2Var.e = 0L;
        jm2Var.h();
    }

    public ki(String str, PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus, String str2, String str3, long j, long j2, String str4) {
        this.a = str;
        this.b = persistedInstallation$RegistrationStatus;
        this.c = str2;
        this.d = str3;
        this.e = j;
        this.f = j2;
        this.g = str4;
    }

    public final jm2 a() {
        jm2 jm2Var = new jm2();
        jm2Var.a = this.a;
        jm2Var.b = this.b;
        jm2Var.c = this.c;
        jm2Var.d = this.d;
        jm2Var.e = Long.valueOf(this.e);
        jm2Var.f = Long.valueOf(this.f);
        jm2Var.g = this.g;
        return jm2Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ki)) {
            return false;
        }
        ki kiVar = (ki) obj;
        String str = this.a;
        if (str != null ? str.equals(kiVar.a) : kiVar.a == null) {
            if (this.b.equals(kiVar.b)) {
                String str2 = kiVar.c;
                String str3 = this.c;
                if (str3 != null ? str3.equals(str2) : str2 == null) {
                    String str4 = kiVar.d;
                    String str5 = this.d;
                    if (str5 != null ? str5.equals(str4) : str4 == null) {
                        if (this.e == kiVar.e && this.f == kiVar.f) {
                            String str6 = kiVar.g;
                            String str7 = this.g;
                            if (str7 == null) {
                                if (str6 == null) {
                                    return true;
                                }
                            } else if (str7.equals(str6)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.a;
        int iHashCode = ((((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        String str2 = this.c;
        int iHashCode2 = (iHashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.d;
        int iHashCode3 = (iHashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        long j = this.e;
        int i = (iHashCode3 ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.f;
        int i2 = (i ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        String str4 = this.g;
        return (str4 != null ? str4.hashCode() : 0) ^ i2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PersistedInstallationEntry{firebaseInstallationId=");
        sb.append(this.a);
        sb.append(", registrationStatus=");
        sb.append(this.b);
        sb.append(", authToken=");
        sb.append(this.c);
        sb.append(", refreshToken=");
        sb.append(this.d);
        sb.append(", expiresInSecs=");
        sb.append(this.e);
        sb.append(", tokenCreationEpochInSecs=");
        sb.append(this.f);
        sb.append(", fisError=");
        return u4.m(sb, this.g, "}");
    }
}

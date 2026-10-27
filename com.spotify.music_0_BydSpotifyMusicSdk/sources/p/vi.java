package p;

import com.google.firebase.installations.remote.TokenResult$ResponseCode;

/* JADX INFO: loaded from: classes.dex */
public final class vi {
    public final String a;
    public final long b;
    public final TokenResult$ResponseCode c;

    public vi(String str, long j, TokenResult$ResponseCode tokenResult$ResponseCode) {
        this.a = str;
        this.b = j;
        this.c = tokenResult$ResponseCode;
    }

    public static kk0 a() {
        kk0 kk0Var = new kk0(18);
        kk0Var.k = 0L;
        return kk0Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vi)) {
            return false;
        }
        vi viVar = (vi) obj;
        String str = this.a;
        if (str != null ? str.equals(viVar.a) : viVar.a == null) {
            if (this.b == viVar.b) {
                TokenResult$ResponseCode tokenResult$ResponseCode = viVar.c;
                TokenResult$ResponseCode tokenResult$ResponseCode2 = this.c;
                if (tokenResult$ResponseCode2 == null) {
                    if (tokenResult$ResponseCode == null) {
                        return true;
                    }
                } else if (tokenResult$ResponseCode2.equals(tokenResult$ResponseCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.a;
        int iHashCode = str == null ? 0 : str.hashCode();
        long j = this.b;
        int i = (((iHashCode ^ 1000003) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003;
        TokenResult$ResponseCode tokenResult$ResponseCode = this.c;
        return (tokenResult$ResponseCode != null ? tokenResult$ResponseCode.hashCode() : 0) ^ i;
    }

    public final String toString() {
        return "TokenResult{token=" + this.a + ", tokenExpirationTimestamp=" + this.b + ", responseCode=" + this.c + "}";
    }
}

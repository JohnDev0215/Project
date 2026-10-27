package f;

import java.io.IOException;

/* JADX INFO: compiled from: Protocol.java */
/* JADX INFO: loaded from: classes.dex */
public enum c0 {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    private final String protocol;

    c0(String str) {
        this.protocol = str;
    }

    public static c0 a(String str) throws IOException {
        c0 c0Var = HTTP_1_0;
        if (str.equals(c0Var.protocol)) {
            return c0Var;
        }
        c0 c0Var2 = HTTP_1_1;
        if (str.equals(c0Var2.protocol)) {
            return c0Var2;
        }
        c0 c0Var3 = H2_PRIOR_KNOWLEDGE;
        if (str.equals(c0Var3.protocol)) {
            return c0Var3;
        }
        c0 c0Var4 = HTTP_2;
        if (str.equals(c0Var4.protocol)) {
            return c0Var4;
        }
        c0 c0Var5 = SPDY_3;
        if (str.equals(c0Var5.protocol)) {
            return c0Var5;
        }
        c0 c0Var6 = QUIC;
        if (str.equals(c0Var6.protocol)) {
            return c0Var6;
        }
        throw new IOException("Unexpected protocol: " + str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}

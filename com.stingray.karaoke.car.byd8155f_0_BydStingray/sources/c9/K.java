package c9;

/* JADX INFO: loaded from: classes.dex */
public enum K {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");

    public final String l;

    K(String str) {
        this.l = str;
    }
}

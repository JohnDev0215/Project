package c.a.b.i;

/* JADX INFO: compiled from: DigestAlgorithm.java */
/* JADX INFO: loaded from: classes.dex */
public enum a {
    MD2("MD2"),
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512");

    private final String value;

    a(String str) {
        this.value = str;
    }

    public String a() {
        return this.value;
    }
}

package c.a.b.j;

/* JADX INFO: compiled from: SymmetricAlgorithm.java */
/* JADX INFO: loaded from: classes.dex */
public enum e {
    AES("AES"),
    ARCFOUR("ARCFOUR"),
    Blowfish("Blowfish"),
    DES("DES"),
    DESede("DESede"),
    RC2("RC2"),
    PBEWithMD5AndDES("PBEWithMD5AndDES"),
    PBEWithSHA1AndDESede("PBEWithSHA1AndDESede"),
    PBEWithSHA1AndRC2_40("PBEWithSHA1AndRC2_40");

    private final String value;

    e(String str) {
        this.value = str;
    }

    public String a() {
        return this.value;
    }
}

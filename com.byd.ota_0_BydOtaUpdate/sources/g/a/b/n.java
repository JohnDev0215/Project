package g.a.b;

/* JADX INFO: loaded from: classes.dex */
public interface n {
    int doFinal(byte[] bArr, int i2);

    String getAlgorithmName();

    int getDigestSize();

    void reset();

    void update(byte b2);

    void update(byte[] bArr, int i2, int i3);
}

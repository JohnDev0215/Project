package a.a.a.a;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static byte[] a(String str, PublicKey publicKey) {
        KeyPair keyPairA;
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        d dVar = new d();
        try {
            keyPairA = d.a();
        } catch (Exception e) {
            e.printStackTrace();
            keyPairA = null;
        }
        dVar.b(keyPairA, publicKey);
        return dVar.f(bytes);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, String str) throws KeyStoreException {
        PrivateKey privateKeyD = g.d(bArr2, "privatekeyalias", new BouncyCastleProvider(), str);
        if (privateKeyD == null) {
            return null;
        }
        return d.d(Base64.toBase64String(bArr), privateKeyD);
    }
}

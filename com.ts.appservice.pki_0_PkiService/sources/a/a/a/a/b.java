package a.a.a.a;

import com.byd.pki.filling.CsrP12Info;
import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.encoders.Base64;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Provider f0a = new BouncyCastleProvider();

    public static CsrP12Info a(String str) throws Throwable {
        CsrP12Info csrP12Info = new CsrP12Info();
        KeyPair keyPairA = d.a();
        Provider provider = f0a;
        CsrP12Info csrP12InfoB = g.b(keyPairA, provider, str);
        e.a(Base64.toBase64String(new JcaPKCS10CertificationRequestBuilder(new X500Principal("C=CN,O=BYD INDUSTRY,OU=GHY,CN=BYD Device CA ECC CSR"), keyPairA.getPublic()).build(new JcaContentSignerBuilder("SHA256WithECDSA").setProvider(provider).build(keyPairA.getPrivate())).getEncoded()), new File(str + "eccCsr.p10"));
        csrP12Info.setCsrPath(str + "eccCsr.p10");
        csrP12Info.setPrivateKeyP12Secret(csrP12InfoB.getPrivateKeyP12Secret());
        csrP12Info.setPrivateKeyP12path(csrP12InfoB.getPrivateKeyP12path());
        csrP12Info.setSecretType(csrP12InfoB.getSecretType());
        return csrP12Info;
    }

    public static CsrP12Info b(String str) throws Throwable {
        CsrP12Info csrP12Info = new CsrP12Info();
        Provider provider = f0a;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", provider);
        keyPairGenerator.initialize(2048);
        KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPairGenerateKeyPair.getPrivate();
        PublicKey publicKey = keyPairGenerateKeyPair.getPublic();
        CsrP12Info csrP12Info2 = new CsrP12Info();
        try {
            csrP12Info2 = g.g(keyPairGenerateKeyPair, provider, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        e.a(Base64.toBase64String(new JcaPKCS10CertificationRequestBuilder(new X500Principal("C=CN,O=BYD INDUSTRY,OU=GHY,CN=BYD Device CA RSA CSR"), publicKey).build(new JcaContentSignerBuilder("SHA256WithRSA").setProvider(f0a).build(privateKey)).getEncoded()), new File(str + "rsaCsr.p10"));
        csrP12Info.setCsrPath(str + "rsaCsr.p10");
        csrP12Info.setPrivateKeyP12Secret(csrP12Info2.getPrivateKeyP12Secret());
        csrP12Info.setPrivateKeyP12path(csrP12Info2.getPrivateKeyP12path());
        csrP12Info.setSecretType(csrP12Info2.getSecretType());
        return csrP12Info;
    }
}

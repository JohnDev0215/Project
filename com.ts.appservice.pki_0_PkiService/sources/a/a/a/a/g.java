package a.a.a.a;

import android.os.Environment;
import com.byd.pki.filling.CertP12Info;
import com.byd.pki.filling.CommonConsts;
import com.byd.pki.filling.CsrP12Info;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.encoders.Base64;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Provider f5a = new BouncyCastleProvider();

    public static CertP12Info a(String str, String str2) throws InvalidKeySpecException, NoSuchAlgorithmException, OperatorCreationException, IOException, KeyStoreException, CertificateException {
        FileOutputStream fileOutputStream;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
        String str3 = "";
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
            if (!line.contains("BEGIN ") && !line.contains("END ")) {
                str3 = str3 + line;
            }
        }
        bufferedReader.close();
        PrivateKey privateKeyGeneratePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str3)));
        Provider provider = f5a;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", provider);
        keyPairGenerator.initialize(2048);
        KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        CertP12Info certP12Info = new CertP12Info();
        X509Certificate x509CertificateI = i(keyPairGenerateKeyPair, provider, "SHA256WithRSA");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(null, null);
        keyStore.setKeyEntry("privatekeyalias", privateKeyGeneratePrivate, null, new Certificate[]{x509CertificateI});
        String strA = i.a();
        String str4 = str2 + "deviceRsaKey.p12";
        String externalStorageState = Environment.getExternalStorageState();
        System.out.println("state =" + externalStorageState);
        String str5 = strA + strA;
        if (externalStorageState.equals("mounted")) {
            File file = new File(str4);
            System.out.println("file.exists() =" + file.exists() + " path = " + str4);
            if (file.exists()) {
                fileOutputStream = new FileOutputStream(file);
                keyStore.store(fileOutputStream, str5.toCharArray());
            } else {
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                keyStore.store(fileOutputStream, str5.toCharArray());
            }
            fileOutputStream.close();
        }
        certP12Info.setCertP12Secret(strA);
        certP12Info.setCertP12Path(str4);
        certP12Info.setSecretType(CommonConsts.DEVICE_RSA_KEY);
        return certP12Info;
    }

    public static CsrP12Info b(KeyPair keyPair, Provider provider, String str) throws NoSuchAlgorithmException, OperatorCreationException, IOException, KeyStoreException, CertificateException {
        FileOutputStream fileOutputStream;
        CsrP12Info csrP12Info = new CsrP12Info();
        X509Certificate x509CertificateI = i(keyPair, provider, "SHA256WithECDSA");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(null, null);
        keyStore.setKeyEntry("privatekeyalias", keyPair.getPrivate(), null, new Certificate[]{x509CertificateI});
        String strA = i.a();
        String str2 = str + "eccCsrPri.p12";
        String externalStorageState = Environment.getExternalStorageState();
        System.out.println("state =" + externalStorageState);
        String str3 = strA + strA;
        if (externalStorageState.equals("mounted")) {
            File file = new File(str2);
            System.out.println("file.exists() =" + file.exists() + " path = " + str2);
            if (file.exists()) {
                fileOutputStream = new FileOutputStream(file);
                keyStore.store(fileOutputStream, str3.toCharArray());
            } else {
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                keyStore.store(fileOutputStream, str3.toCharArray());
            }
            fileOutputStream.close();
        }
        csrP12Info.setPrivateKeyP12Secret(strA);
        csrP12Info.setPrivateKeyP12path(str2);
        csrP12Info.setSecretType(CommonConsts.ECC256_KEY);
        return csrP12Info;
    }

    public static PrivateKey c(String str, String str2, String str3) throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try {
            String externalStorageState = Environment.getExternalStorageState();
            String str4 = str3 + str3;
            System.out.println("st =" + externalStorageState);
            if (externalStorageState.equals("mounted")) {
                File file = new File(str);
                System.out.println("file.exists() =" + file.exists());
                if (file.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    keyStore.load(fileInputStream, str4.toCharArray());
                    fileInputStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyStore.getKey(str2, null).getEncoded()));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static PrivateKey d(byte[] bArr, String str, Provider provider, String str2) throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            keyStore.load(byteArrayInputStream, (str2 + str2).toCharArray());
            byteArrayInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return KeyFactory.getInstance("EC", provider).generatePrivate(new PKCS8EncodedKeySpec(keyStore.getKey(str, null).getEncoded()));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Certificate e(byte[] bArr, String str, String str2) throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            keyStore.load(byteArrayInputStream, (str2 + str2).toCharArray());
            byteArrayInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyStore.getCertificate(str);
    }

    public static CertP12Info f(String str, String str2, String str3) throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException {
        FileOutputStream fileOutputStream;
        CertP12Info certP12Info = new CertP12Info();
        Certificate certificateGenerateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new FileInputStream(new File(str)));
        String str4 = str2 + str3 + ".p12";
        String strA = i.a();
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(null, null);
        keyStore.setCertificateEntry("cert", certificateGenerateCertificate);
        String str5 = strA + strA;
        String externalStorageState = Environment.getExternalStorageState();
        System.out.println("state =" + externalStorageState);
        if (externalStorageState.equals("mounted")) {
            File file = new File(str4);
            System.out.println("file.exists() =" + file.exists() + " path = " + str4);
            if (file.exists()) {
                fileOutputStream = new FileOutputStream(file);
                keyStore.store(fileOutputStream, str5.toCharArray());
            } else {
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                keyStore.store(fileOutputStream, str5.toCharArray());
            }
            fileOutputStream.close();
        }
        certP12Info.setCertP12Path(str4);
        certP12Info.setCertP12Secret(strA);
        certP12Info.setSecretType(str3);
        return certP12Info;
    }

    public static CsrP12Info g(KeyPair keyPair, Provider provider, String str) throws NoSuchAlgorithmException, OperatorCreationException, IOException, KeyStoreException, CertificateException {
        FileOutputStream fileOutputStream;
        CsrP12Info csrP12Info = new CsrP12Info();
        X509Certificate x509CertificateI = i(keyPair, provider, "SHA256WithRSA");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(null, null);
        keyStore.setKeyEntry("privatekeyalias", keyPair.getPrivate(), null, new Certificate[]{x509CertificateI});
        String strA = i.a();
        String str2 = str + "rsaCsrPri.p12";
        String externalStorageState = Environment.getExternalStorageState();
        System.out.println("state =" + externalStorageState);
        String str3 = strA + strA;
        if (externalStorageState.equals("mounted")) {
            File file = new File(str2);
            System.out.println("file.exists() =" + file.exists() + " path = " + str2);
            if (file.exists()) {
                fileOutputStream = new FileOutputStream(file);
                keyStore.store(fileOutputStream, str3.toCharArray());
            } else {
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                keyStore.store(fileOutputStream, str3.toCharArray());
            }
            fileOutputStream.close();
        }
        csrP12Info.setPrivateKeyP12Secret(strA);
        csrP12Info.setPrivateKeyP12path(str2);
        csrP12Info.setSecretType(CommonConsts.RSA2048_KEY);
        return csrP12Info;
    }

    public static PrivateKey h(byte[] bArr, String str, String str2) throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            keyStore.load(byteArrayInputStream, (str2 + str2).toCharArray());
            byteArrayInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyStore.getKey(str, null).getEncoded()));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static X509Certificate i(KeyPair keyPair, Provider provider, String str) throws OperatorCreationException {
        X500Name x500Name = new X500Name("CN=publickeystorageonly");
        BigInteger bigInteger = BigInteger.ONE;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, 1);
        Date time = calendar.getTime();
        ContentSigner contentSignerBuild = new JcaContentSignerBuilder(str).setProvider(provider).build(keyPair.getPrivate());
        JcaX509v3CertificateBuilder jcaX509v3CertificateBuilder = new JcaX509v3CertificateBuilder(x500Name, bigInteger, date, time, x500Name, keyPair.getPublic());
        jcaX509v3CertificateBuilder.build(contentSignerBuild);
        return new JcaX509CertificateConverter().getCertificate(jcaX509v3CertificateBuilder.build(contentSignerBuild));
    }
}

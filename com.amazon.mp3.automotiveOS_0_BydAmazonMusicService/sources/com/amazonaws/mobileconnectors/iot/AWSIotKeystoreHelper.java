package com.amazonaws.mobileconnectors.iot;

import com.amazonaws.AmazonClientException;
import com.amazonaws.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class AWSIotKeystoreHelper {
    public static final String AWS_IOT_INTERNAL_KEYSTORE_PASSWORD = "awsiotkeystorepassword";
    private static final String AWS_IOT_PEM_BEGIN_CERT_TAG = "-----BEGIN CERTIFICATE-----";
    private static final String AWS_IOT_PEM_END_CERT_TAG = "-----END CERTIFICATE-----";
    private static final Integer KEY_LENGTH_BITS = 2048;

    private AWSIotKeystoreHelper() {
    }

    public static KeyPair generatePrivateAndPublicKeys() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_LENGTH_BITS.intValue(), new SecureRandom());
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new AWSIotCertificateException("Error creating keypair generator.", e);
        }
    }

    public static void saveCertificateAndPrivateKey(String str, String str2, PrivateKey privateKey, String str3, String str4, String str5) {
        if (str == null) {
            throw new IllegalArgumentException("certId cannot be null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("certPem cannot be null");
        }
        if (privateKey == null) {
            throw new IllegalArgumentException("privKey cannot be null");
        }
        if (str3 == null) {
            throw new IllegalArgumentException("keystorePath cannot be null");
        }
        if (str4 == null) {
            throw new IllegalArgumentException("keystoreName cannot be null");
        }
        if (str5 == null) {
            throw new IllegalArgumentException("keystorePassword cannot be null");
        }
        byte[][] dERFromPEM = parseDERFromPEM(str2, AWS_IOT_PEM_BEGIN_CERT_TAG, AWS_IOT_PEM_END_CERT_TAG);
        try {
            X509Certificate[] x509CertificateArr = new X509Certificate[dERFromPEM.length];
            for (int i = 0; i < dERFromPEM.length; i++) {
                x509CertificateArr[i] = generateCertificateFromDER(dERFromPEM[i]);
            }
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            File file = new File(str3, str4);
            if (!file.exists()) {
                createKeystore(str3, str4, str5);
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            keyStore.load(fileInputStream, str5.toCharArray());
            fileInputStream.close();
            keyStore.setCertificateEntry(str, x509CertificateArr[0]);
            keyStore.setKeyEntry(str, privateKey, str5.toCharArray(), x509CertificateArr);
            FileOutputStream fileOutputStream = new FileOutputStream(str3.endsWith("/") ? str3 + str4 : str3 + "/" + str4);
            keyStore.store(fileOutputStream, str5.toCharArray());
            fileOutputStream.close();
        } catch (IOException e) {
            throw new AmazonClientException("Error saving certificate and key.", e);
        } catch (KeyStoreException e2) {
            throw new AWSIotCertificateException("Error saving certificate and key.", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AWSIotCertificateException("Error saving certificate and key.", e3);
        } catch (CertificateException e4) {
            throw new AWSIotCertificateException("Error saving certificate and key.", e4);
        }
    }

    public static void saveCertificateAndPrivateKey(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            saveCertificateAndPrivateKey(str, str2, new PrivateKeyReader(str3).getPrivateKey(), str4, str5, str6);
        } catch (IOException e) {
            throw new AmazonClientException("An error occurred saving the certificate and key.", e);
        } catch (InvalidKeySpecException e2) {
            throw new AWSIotCertificateException("An error occurred saving the certificate and key.", e2);
        }
    }

    public static KeyStore getIotKeystore(String str, String str2, String str3, String str4) {
        String str5;
        if (str == null) {
            throw new IllegalArgumentException("certId cannot be null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("keystorePath is null");
        }
        if (str3 == null) {
            throw new IllegalArgumentException("keystoreName is null");
        }
        if (str4 == null) {
            throw new IllegalArgumentException("keystorePassword is null");
        }
        if (str2.endsWith("/")) {
            str5 = str2 + str3;
        } else {
            str5 = str2 + "/" + str3;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream fileInputStream = new FileInputStream(str5);
            keyStore.load(fileInputStream, str4.toCharArray());
            fileInputStream.close();
            return getTempKeystore(keyStore, str, str4);
        } catch (IOException e) {
            throw new AmazonClientException("Error retrieving certificate and key.", e);
        } catch (KeyStoreException e2) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e3);
        } catch (CertificateException e4) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e4);
        }
    }

    public static KeyStore getIotKeystore(String str, InputStream inputStream, String str2) {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(inputStream, str2.toCharArray());
            return getTempKeystore(keyStore, str, str2);
        } catch (IOException e) {
            throw new AmazonClientException("Error retrieving certificate and key.", e);
        } catch (KeyStoreException e2) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e3);
        } catch (CertificateException e4) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e4);
        }
    }

    private static KeyStore getTempKeystore(KeyStore keyStore, String str, String str2) {
        try {
            KeyStore keyStore2 = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore2.load(null);
            Certificate[] certificateChain = keyStore.getCertificateChain(str);
            X509Certificate[] x509CertificateArr = new X509Certificate[certificateChain.length];
            for (int i = 0; i < certificateChain.length; i++) {
                x509CertificateArr[i] = (X509Certificate) certificateChain[i];
            }
            keyStore2.setCertificateEntry("cert-alias", x509CertificateArr[0]);
            keyStore2.setKeyEntry("key-alias", keyStore.getKey(str, str2.toCharArray()), AWS_IOT_INTERNAL_KEYSTORE_PASSWORD.toCharArray(), x509CertificateArr);
            return keyStore2;
        } catch (IOException e) {
            throw new AmazonClientException("Error retrieving certificate and key.", e);
        } catch (KeyStoreException e2) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e3);
        } catch (UnrecoverableKeyException e4) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e4);
        } catch (CertificateException e5) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e5);
        }
    }

    public static Boolean isKeystorePresent(String str, String str2) {
        return Boolean.valueOf(new File(str, str2).exists());
    }

    public static Boolean keystoreContainsAlias(String str, String str2, String str3, String str4) {
        Boolean bool = Boolean.FALSE;
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream fileInputStream = new FileInputStream(str2 + "/" + str3);
            keyStore.load(fileInputStream, str4.toCharArray());
            if (keyStore.containsAlias(str)) {
                bool = Boolean.TRUE;
            }
            fileInputStream.close();
            return bool;
        } catch (IOException e) {
            throw new AmazonClientException("Error retrieving certificate and key.", e);
        } catch (KeyStoreException e2) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e3);
        } catch (CertificateException e4) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e4);
        }
    }

    public static void deleteKeystoreAlias(String str, String str2, String str3, String str4) {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream fileInputStream = new FileInputStream(str2 + "/" + str3);
            keyStore.load(fileInputStream, str4.toCharArray());
            fileInputStream.close();
            keyStore.deleteEntry(str);
            keyStore.store(new FileOutputStream(str2 + "/" + str3), str4.toCharArray());
        } catch (IOException e) {
            throw new AmazonClientException("Error retrieving certificate and key.", e);
        } catch (KeyStoreException e2) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e3);
        } catch (CertificateException e4) {
            throw new AWSIotCertificateException("Error retrieving certificate and key.", e4);
        }
    }

    static byte[][] parseDERFromPEM(String str, String str2, String str3) {
        int i;
        String[] strArrSplit = str.split(str2);
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        while (true) {
            if (i2 >= strArrSplit.length) {
                break;
            }
            arrayList.add(strArrSplit[i2].split(str3)[0]);
            i2++;
        }
        byte[][] bArr = new byte[arrayList.size()][];
        for (i = 0; i < arrayList.size(); i++) {
            bArr[i] = Base64.decode((String) arrayList.get(i));
        }
        return bArr;
    }

    static X509Certificate generateCertificateFromDER(byte[] bArr) throws CertificateException {
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
    }

    static void createKeystore(String str, String str2, String str3) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        String str4;
        if (str == null) {
            throw new IllegalArgumentException("keystorePath is null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("keystoreName is null");
        }
        if (str3 == null) {
            throw new IllegalArgumentException("keystorePassword is null");
        }
        if (str.endsWith("/")) {
            str4 = str + str2;
        } else {
            str4 = str + "/" + str2;
        }
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] charArray = str3.toCharArray();
        keyStore.load(null, charArray);
        FileOutputStream fileOutputStream = new FileOutputStream(str4);
        keyStore.store(fileOutputStream, charArray);
        fileOutputStream.close();
    }
}

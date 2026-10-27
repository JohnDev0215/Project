package android.security;

import com.android.org.bouncycastle.util.io.pem.PemObject;
import com.android.org.bouncycastle.util.io.pem.PemReader;
import com.android.org.bouncycastle.util.io.pem.PemWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Credentials {
    public static final String APP_SOURCE_CERTIFICATE = "FSV_";
    public static final String CA_CERTIFICATE = "CACERT_";
    public static final String CERTIFICATE_USAGE_APP_SOURCE = "appsrc";
    public static final String CERTIFICATE_USAGE_CA = "ca";
    public static final String CERTIFICATE_USAGE_USER = "user";
    public static final String CERTIFICATE_USAGE_WIFI = "wifi";
    public static final String EXTENSION_CER = ".cer";
    public static final String EXTENSION_CRT = ".crt";
    public static final String EXTENSION_P12 = ".p12";
    public static final String EXTENSION_PFX = ".pfx";
    public static final String EXTRA_CA_CERTIFICATES_DATA = "ca_certificates_data";
    public static final String EXTRA_CERTIFICATE_USAGE = "certificate_install_usage";
    public static final String EXTRA_INSTALL_AS_UID = "install_as_uid";
    public static final String EXTRA_PRIVATE_KEY = "PKEY";
    public static final String EXTRA_PUBLIC_KEY = "KEY";
    public static final String EXTRA_USER_CERTIFICATE_DATA = "user_certificate_data";
    public static final String EXTRA_USER_KEY_ALIAS = "user_key_pair_name";
    public static final String EXTRA_USER_PRIVATE_KEY_DATA = "user_private_key_data";
    public static final String INSTALL_ACTION = "android.credentials.INSTALL";
    public static final String INSTALL_AS_USER_ACTION = "android.credentials.INSTALL_AS_USER";
    public static final String LOCKDOWN_VPN = "LOCKDOWN_VPN";
    private static final String LOGTAG = "Credentials";
    public static final String PLATFORM_VPN = "PLATFORM_VPN_";
    public static final String USER_CERTIFICATE = "USRCERT_";
    public static final String USER_PRIVATE_KEY = "USRPKEY_";
    public static final String USER_SECRET_KEY = "USRSKEY_";
    public static final String VPN = "VPN_";
    public static final String WIFI = "WIFI_";

    public static byte[] convertToPem(Certificate... certificateArr) throws IOException, CertificateEncodingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.US_ASCII));
        for (Certificate certificate : certificateArr) {
            pemWriter.writeObject(new PemObject("CERTIFICATE", certificate.getEncoded()));
        }
        pemWriter.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static List<X509Certificate> convertFromPem(byte[] bArr) throws IOException, CertificateException {
        PemReader pemReader = new PemReader(new InputStreamReader(new ByteArrayInputStream(bArr), StandardCharsets.US_ASCII));
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            ArrayList arrayList = new ArrayList();
            while (true) {
                PemObject pemObject = pemReader.readPemObject();
                if (pemObject == null) {
                    return arrayList;
                }
                if (pemObject.getType().equals("CERTIFICATE")) {
                    arrayList.add((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(pemObject.getContent())));
                } else {
                    throw new IllegalArgumentException("Unknown type " + pemObject.getType());
                }
            }
        } finally {
            pemReader.close();
        }
    }

    public static boolean deleteAllTypesForAlias(KeyStore keyStore, String str) {
        return deleteAllTypesForAlias(keyStore, str, -1);
    }

    public static boolean deleteAllTypesForAlias(KeyStore keyStore, String str, int i) {
        return deleteCertificateTypesForAlias(keyStore, str, i) & deleteUserKeyTypeForAlias(keyStore, str, i);
    }

    public static boolean deleteCertificateTypesForAlias(KeyStore keyStore, String str) {
        return deleteCertificateTypesForAlias(keyStore, str, -1);
    }

    public static boolean deleteCertificateTypesForAlias(KeyStore keyStore, String str, int i) {
        return keyStore.delete(CA_CERTIFICATE + str, i) & keyStore.delete(USER_CERTIFICATE + str, i);
    }

    public static boolean deleteUserKeyTypeForAlias(KeyStore keyStore, String str) {
        return deleteUserKeyTypeForAlias(keyStore, str, -1);
    }

    public static boolean deleteUserKeyTypeForAlias(KeyStore keyStore, String str, int i) {
        int iDelete2 = keyStore.delete2(USER_PRIVATE_KEY + str, i);
        if (iDelete2 != 7) {
            return iDelete2 == 1;
        }
        return keyStore.delete(USER_SECRET_KEY + str, i);
    }

    public static boolean deleteLegacyKeyForAlias(KeyStore keyStore, String str, int i) {
        return keyStore.delete(USER_SECRET_KEY + str, i);
    }
}

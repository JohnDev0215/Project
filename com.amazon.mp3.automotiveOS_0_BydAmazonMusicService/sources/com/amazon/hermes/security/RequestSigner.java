package com.amazon.hermes.security;

import com.amazon.music.destination.parser.ParserUtil;
import com.google.firebase.perf.FirebasePerformance;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class RequestSigner {
    private static final String TAG = "RequestSigner";

    public static class InvalidCorpusException extends Exception {
        private static final long serialVersionUID = 5709822964473346972L;
    }

    private enum KeyFormat {
        PKCS8,
        PKCS1,
        INVALID
    }

    public static Map<String, String> signRequest(String str, URL url, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        try {
            CorpusBuilder corpusBuilder = new CorpusBuilder(str, url.getPath(), str2, str4);
            byte[] bytes = corpusBuilder.getCorpus().getBytes(ParserUtil.UTF_8);
            PrivateKey key = parseKey(str3);
            if (key != null) {
                Signature signature = Signature.getInstance("SHA256withRSA");
                signature.initSign(key);
                signature.update(bytes);
                String strEncodeBytes = Base64.encodeBytes(signature.sign(), 8);
                map.put("x-adp-alg", "SHA256withRSA:1.0");
                map.put("x-adp-signature", strEncodeBytes + ":" + corpusBuilder.getTimestamp());
                map.put("x-adp-token", str4);
            }
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException unused) {
        }
        return map;
    }

    private static PrivateKey parseKey(String str) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String strConvertPEMToBase64DER;
        KeyFormat keyFormat;
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        String strTrim = str.trim();
        if (isPEMEncoded(strTrim)) {
            strConvertPEMToBase64DER = convertPEMToBase64DER(strTrim);
            keyFormat = getKeyFormat(strTrim);
        } else {
            strConvertPEMToBase64DER = strTrim;
            keyFormat = KeyFormat.PKCS8;
        }
        if (keyFormat != KeyFormat.PKCS8) {
            throw new RuntimeException("Only PKCS8 Private key is supported");
        }
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(strConvertPEMToBase64DER)));
    }

    private static boolean isPEMEncoded(String str) {
        return str.charAt(0) == '-' && str.charAt(str.length() - 1) == '-';
    }

    private static String convertPEMToBase64DER(String str) {
        return str.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "").trim();
    }

    private static KeyFormat getKeyFormat(String str) {
        if (str.contains("-----BEGIN PRIVATE KEY-----")) {
            return KeyFormat.PKCS8;
        }
        if (str.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            return KeyFormat.PKCS1;
        }
        return KeyFormat.INVALID;
    }

    private static class CorpusBuilder {
        String mBody;
        StringBuilder mCorpus = new StringBuilder();
        String mCurrentTimestamp = getCurrentTimestamp();
        String mDeviceToken;
        String mMethod;
        String url;

        public CorpusBuilder(String str, String str2, String str3, String str4) {
            this.mMethod = str;
            this.url = str2;
            this.mBody = str3;
            this.mDeviceToken = str4;
        }

        private String getCurrentTimestamp() {
            Locale locale = Locale.US;
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", locale).format(new GregorianCalendar(locale).getTime());
        }

        public String getCorpus() {
            if (!isValid()) {
                return null;
            }
            this.mCorpus.append(this.mMethod);
            this.mCorpus.append("\n");
            this.mCorpus.append(this.url.toString());
            this.mCorpus.append("\n");
            this.mCorpus.append(this.mCurrentTimestamp);
            this.mCorpus.append("\n");
            this.mCorpus.append(this.mBody);
            this.mCorpus.append("\n");
            this.mCorpus.append(this.mDeviceToken);
            return this.mCorpus.toString();
        }

        public boolean isValid() {
            String str = this.mMethod;
            return ((str != FirebasePerformance.HttpMethod.GET && str != FirebasePerformance.HttpMethod.POST) || this.url == null || StringUtils.isEmpty(this.mCurrentTimestamp) || StringUtils.isEmpty(this.mBody) || StringUtils.isEmpty(this.mDeviceToken)) ? false : true;
        }

        public String getTimestamp() {
            return this.mCurrentTimestamp;
        }
    }
}

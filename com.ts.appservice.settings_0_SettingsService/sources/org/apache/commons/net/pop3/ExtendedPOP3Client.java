package org.apache.commons.net.pop3;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.net.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedPOP3Client extends POP3SClient {
    public boolean auth(AUTH_METHOD method, String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        if (sendCommand(13, method.getAuthName()) != 2) {
            return false;
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$net$pop3$ExtendedPOP3Client$AUTH_METHOD[method.ordinal()];
        if (i == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u0000");
            sb.append(username);
            sb.append("\u0000");
            sb.append(password);
            return sendCommand(new String(Base64.encodeBase64(sb.toString().getBytes(getCharset())), getCharset())) == 0;
        }
        if (i != 2) {
            return false;
        }
        byte[] serverChallenge = Base64.decodeBase64(getReplyString().substring(2).trim());
        Mac hmac_md5 = Mac.getInstance("HmacMD5");
        hmac_md5.init(new SecretKeySpec(password.getBytes(getCharset()), "HmacMD5"));
        byte[] hmacResult = convertToHexString(hmac_md5.doFinal(serverChallenge)).getBytes(getCharset());
        byte[] usernameBytes = username.getBytes(getCharset());
        byte[] toEncode = new byte[usernameBytes.length + 1 + hmacResult.length];
        System.arraycopy(usernameBytes, 0, toEncode, 0, usernameBytes.length);
        toEncode[usernameBytes.length] = 32;
        System.arraycopy(hmacResult, 0, toEncode, usernameBytes.length + 1, hmacResult.length);
        return sendCommand(Base64.encodeBase64StringUnChunked(toEncode)) == 0;
    }

    /* JADX INFO: renamed from: org.apache.commons.net.pop3.ExtendedPOP3Client$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$net$pop3$ExtendedPOP3Client$AUTH_METHOD;

        static {
            int[] iArr = new int[AUTH_METHOD.values().length];
            $SwitchMap$org$apache$commons$net$pop3$ExtendedPOP3Client$AUTH_METHOD = iArr;
            try {
                iArr[AUTH_METHOD.PLAIN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$apache$commons$net$pop3$ExtendedPOP3Client$AUTH_METHOD[AUTH_METHOD.CRAM_MD5.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private String convertToHexString(byte[] a) {
        StringBuilder result = new StringBuilder(a.length * 2);
        for (byte element : a) {
            if ((element & 255) <= 15) {
                result.append("0");
            }
            result.append(Integer.toHexString(element & 255));
        }
        return result.toString();
    }

    public enum AUTH_METHOD {
        PLAIN("PLAIN"),
        CRAM_MD5("CRAM-MD5");

        private final String methodName;

        AUTH_METHOD(String methodName) {
            this.methodName = methodName;
        }

        public final String getAuthName() {
            return this.methodName;
        }
    }
}

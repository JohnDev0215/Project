package org.apache.commons.net.imap;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import org.apache.commons.net.imap.IMAP;
import org.apache.commons.net.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public class AuthenticatingIMAPClient extends IMAPSClient {
    public AuthenticatingIMAPClient() {
        this(IMAPSClient.DEFAULT_PROTOCOL, false);
    }

    public AuthenticatingIMAPClient(boolean implicit) {
        this(IMAPSClient.DEFAULT_PROTOCOL, implicit);
    }

    public AuthenticatingIMAPClient(String proto) {
        this(proto, false);
    }

    public AuthenticatingIMAPClient(String proto, boolean implicit) {
        this(proto, implicit, null);
    }

    public AuthenticatingIMAPClient(String proto, boolean implicit, SSLContext ctx) {
        super(proto, implicit, ctx);
    }

    public AuthenticatingIMAPClient(boolean implicit, SSLContext ctx) {
        this(IMAPSClient.DEFAULT_PROTOCOL, implicit, ctx);
    }

    public AuthenticatingIMAPClient(SSLContext context) {
        this(false, context);
    }

    public boolean authenticate(AUTH_METHOD method, String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, InvalidKeyException {
        return auth(method, username, password);
    }

    public boolean auth(AUTH_METHOD method, String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        if (!IMAPReply.isContinuation(sendCommand(IMAPCommand.AUTHENTICATE, method.getAuthName()))) {
            return false;
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$net$imap$AuthenticatingIMAPClient$AUTH_METHOD[method.ordinal()];
        if (i == 1) {
            int result = sendData(Base64.encodeBase64StringUnChunked(("\u0000" + username + "\u0000" + password).getBytes(getCharset())));
            if (result == 0) {
                setState(IMAP.IMAPState.AUTH_STATE);
            }
            return result == 0;
        }
        if (i == 2) {
            byte[] serverChallenge = Base64.decodeBase64(getReplyString().substring(2).trim());
            Mac hmac_md5 = Mac.getInstance("HmacMD5");
            hmac_md5.init(new SecretKeySpec(password.getBytes(getCharset()), "HmacMD5"));
            byte[] hmacResult = convertToHexString(hmac_md5.doFinal(serverChallenge)).getBytes(getCharset());
            byte[] usernameBytes = username.getBytes(getCharset());
            byte[] toEncode = new byte[usernameBytes.length + 1 + hmacResult.length];
            System.arraycopy(usernameBytes, 0, toEncode, 0, usernameBytes.length);
            toEncode[usernameBytes.length] = 32;
            System.arraycopy(hmacResult, 0, toEncode, usernameBytes.length + 1, hmacResult.length);
            int result2 = sendData(Base64.encodeBase64StringUnChunked(toEncode));
            if (result2 == 0) {
                setState(IMAP.IMAPState.AUTH_STATE);
            }
            return result2 == 0;
        }
        if (i == 3) {
            if (sendData(Base64.encodeBase64StringUnChunked(username.getBytes(getCharset()))) != 3) {
                return false;
            }
            int result3 = sendData(Base64.encodeBase64StringUnChunked(password.getBytes(getCharset())));
            if (result3 == 0) {
                setState(IMAP.IMAPState.AUTH_STATE);
            }
            return result3 == 0;
        }
        if (i != 4 && i != 5) {
            return false;
        }
        int result4 = sendData(username);
        if (result4 == 0) {
            setState(IMAP.IMAPState.AUTH_STATE);
        }
        return result4 == 0;
    }

    /* JADX INFO: renamed from: org.apache.commons.net.imap.AuthenticatingIMAPClient$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$net$imap$AuthenticatingIMAPClient$AUTH_METHOD;

        static {
            int[] iArr = new int[AUTH_METHOD.values().length];
            $SwitchMap$org$apache$commons$net$imap$AuthenticatingIMAPClient$AUTH_METHOD = iArr;
            try {
                iArr[AUTH_METHOD.PLAIN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$apache$commons$net$imap$AuthenticatingIMAPClient$AUTH_METHOD[AUTH_METHOD.CRAM_MD5.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$apache$commons$net$imap$AuthenticatingIMAPClient$AUTH_METHOD[AUTH_METHOD.LOGIN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$apache$commons$net$imap$AuthenticatingIMAPClient$AUTH_METHOD[AUTH_METHOD.XOAUTH.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$apache$commons$net$imap$AuthenticatingIMAPClient$AUTH_METHOD[AUTH_METHOD.XOAUTH2.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
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
        CRAM_MD5("CRAM-MD5"),
        LOGIN("LOGIN"),
        XOAUTH("XOAUTH"),
        XOAUTH2("XOAUTH2");

        private final String authName;

        AUTH_METHOD(String name) {
            this.authName = name;
        }

        public final String getAuthName() {
            return this.authName;
        }
    }
}

package com.amazon.music.crypto;

import android.util.Base64;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ECBCryptoInterceptor extends CryptoInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(ECBCryptoInterceptor.class.getSimpleName());
    private static final Charset CHARSET = Charset.forName(ParserUtil.UTF_8);

    @Override // com.amazon.music.crypto.CryptoInterceptor
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // com.amazon.music.crypto.CryptoInterceptor
    public String getTransformationType() {
        return "AES/ECB/PKCS5Padding";
    }

    public ECBCryptoInterceptor(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        super(str);
        this.mInput.init(1, this.mSecretKeySpec);
        this.mOutput.init(2, this.mSecretKeySpec);
    }

    @Override // com.amazon.music.crypto.CryptoInterceptor
    synchronized String encrypt(String str) throws EncryptionException {
        String strEncodeToString;
        try {
            strEncodeToString = Base64.encodeToString(this.mInput.doFinal(str.getBytes(CHARSET)), 2);
            if (strEncodeToString.isEmpty()) {
                LOG.error("Encrypted string is empty!");
                dumpDebugInfo(this.mInput);
            }
        } catch (Exception e) {
            LOG.warn("could not encrypt the text", (Throwable) e);
            throw new EncryptionException();
        }
        return strEncodeToString;
    }

    @Override // com.amazon.music.crypto.CryptoInterceptor
    synchronized String decrypt(String str) throws DecryptionException {
        try {
            if (str.isEmpty()) {
                LOG.error("Encrypted string is empty!");
                dumpDebugInfo(this.mOutput);
            }
        } catch (BadPaddingException | IllegalBlockSizeException unused) {
            throw new DecryptionException();
        }
        return new String(this.mOutput.doFinal(Base64.decode(str, 2)), CHARSET);
    }

    @Override // com.amazon.music.crypto.CryptoInterceptor
    Charset getCharset() {
        return CHARSET;
    }
}

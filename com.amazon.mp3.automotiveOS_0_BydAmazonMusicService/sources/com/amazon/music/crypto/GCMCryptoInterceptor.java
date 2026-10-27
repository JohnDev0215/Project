package com.amazon.music.crypto;

import android.util.Base64;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class GCMCryptoInterceptor extends CryptoInterceptor {
    private final SecureRandom mSecureRandom;
    private static final Logger LOG = LoggerFactory.getLogger(GCMCryptoInterceptor.class.getSimpleName());
    private static final Charset CHARSET = Charset.forName(ParserUtil.UTF_8);

    @Override // com.amazon.music.crypto.CryptoInterceptor
    String getAlgorithmName() {
        return "AES";
    }

    @Override // com.amazon.music.crypto.CryptoInterceptor
    String getTransformationType() {
        return "AES/GCM/NoPadding";
    }

    public GCMCryptoInterceptor(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        super(str);
        this.mSecureRandom = new SecureRandom();
    }

    private byte[] getIv() {
        byte[] bArr = new byte[12];
        this.mSecureRandom.nextBytes(bArr);
        return bArr;
    }

    @Override // com.amazon.music.crypto.CryptoInterceptor
    synchronized String encrypt(String str) throws EncryptionException {
        String strEncodeToString;
        try {
            byte[] iv = getIv();
            this.mInput.init(1, this.mSecretKeySpec, new GCMParameterSpec(128, iv));
            byte[] bArrDoFinal = this.mInput.doFinal(str.getBytes(CHARSET));
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iv.length + bArrDoFinal.length);
            byteBufferAllocate.put(iv);
            byteBufferAllocate.put(bArrDoFinal);
            strEncodeToString = Base64.encodeToString(byteBufferAllocate.array(), 2);
            if (strEncodeToString.isEmpty()) {
                LOG.error("Encrypted string is empty!");
                dumpDebugInfo(this.mInput);
            }
        } catch (IllegalArgumentException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOG.warn("could not encrypt the text", e);
            throw new EncryptionException();
        }
        return strEncodeToString;
    }

    @Override // com.amazon.music.crypto.CryptoInterceptor
    synchronized String decrypt(String str) throws DecryptionException {
        byte[] bArrDecode;
        try {
            if (str.isEmpty()) {
                LOG.error("Encrypted string is empty!");
                dumpDebugInfo(this.mOutput);
            }
            bArrDecode = Base64.decode(str, 2);
            this.mOutput.init(2, this.mSecretKeySpec, new GCMParameterSpec(128, bArrDecode, 0, 12));
        } catch (IllegalArgumentException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOG.error("could not decrypt the text", e);
            throw new DecryptionException();
        }
        return new String(this.mOutput.doFinal(bArrDecode, 12, bArrDecode.length - 12), CHARSET);
    }

    @Override // com.amazon.music.crypto.CryptoInterceptor
    Charset getCharset() {
        return CHARSET;
    }
}

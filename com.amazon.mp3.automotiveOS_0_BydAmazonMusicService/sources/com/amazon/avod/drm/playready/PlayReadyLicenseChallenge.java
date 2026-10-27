package com.amazon.avod.drm.playready;

import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyLicenseChallenge {
    private final byte[] mByteArrayChallenge;
    private final String mSoapChallenge;

    public PlayReadyLicenseChallenge(byte[] bArr) {
        this.mByteArrayChallenge = bArr;
        this.mSoapChallenge = new String(bArr);
    }

    public String asBase64() {
        try {
            return Base64.encodeBytes(this.mSoapChallenge.getBytes(ParserUtil.UTF_8));
        } catch (UnsupportedEncodingException e) {
            DLog.exceptionf(e, "Impossible error (UTF-8 is a valid encoding)", new Object[0]);
            throw new RuntimeException("Impossible error (UTF-8 is a valid encoding)", e);
        }
    }

    public String toString() {
        return this.mSoapChallenge;
    }
}

package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class Utils {
    private static final int RADIX = 16;

    Utils() {
    }

    static int digit16(byte b2) throws DecoderException {
        int iDigit = Character.digit((char) b2, 16);
        if (iDigit != -1) {
            return iDigit;
        }
        throw new DecoderException("Invalid URL encoding: not a valid digit (radix 16): " + ((int) b2));
    }

    static char hexDigit(int i) {
        return Character.toUpperCase(Character.forDigit(i & 15, 16));
    }
}

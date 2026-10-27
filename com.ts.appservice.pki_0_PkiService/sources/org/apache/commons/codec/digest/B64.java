package org.apache.commons.codec.digest;

import java.security.SecureRandom;
import java.util.Random;
import org.bouncycastle.math.ec.Tnaf;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class B64 {
    static final String B64T_STRING = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final char[] B64T_ARRAY = B64T_STRING.toCharArray();

    B64() {
    }

    static void b64from24bit(byte b2, byte b3, byte b4, int i, StringBuilder sb) {
        int i2 = ((b2 << Tnaf.POW_2_WIDTH) & 16777215) | ((b3 << 8) & 65535) | (b4 & 255);
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                return;
            }
            sb.append(B64T_ARRAY[i2 & 63]);
            i2 >>= 6;
            i = i3;
        }
    }

    static String getRandomSalt(int i) {
        return getRandomSalt(i, new SecureRandom());
    }

    static String getRandomSalt(int i, Random random) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 1; i2 <= i; i2++) {
            sb.append(B64T_STRING.charAt(random.nextInt(64)));
        }
        return sb.toString();
    }
}

package org.tukaani.xz.check;

import java.security.NoSuchAlgorithmException;
import org.tukaani.xz.UnsupportedOptionsException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Check {
    String name;
    int size;

    public abstract byte[] finish();

    public abstract void update(byte[] bArr, int i, int i2);

    public void update(byte[] buf) {
        update(buf, 0, buf.length);
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    public static Check getInstance(int checkType) throws UnsupportedOptionsException {
        if (checkType == 0) {
            return new None();
        }
        if (checkType == 1) {
            return new CRC32();
        }
        if (checkType == 4) {
            return new CRC64();
        }
        if (checkType == 10) {
            try {
                return new SHA256();
            } catch (NoSuchAlgorithmException e) {
            }
        }
        throw new UnsupportedOptionsException("Unsupported Check ID " + checkType);
    }
}

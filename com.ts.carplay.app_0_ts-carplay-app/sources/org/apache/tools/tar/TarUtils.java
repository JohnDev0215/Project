package org.apache.tools.tar;

/* JADX INFO: loaded from: classes.dex */
public class TarUtils {
    public static long parseOctal(byte[] header, int offset, int length) {
        long result = 0;
        boolean stillPadding = true;
        int end = offset + length;
        for (int i = offset; i < end && header[i] != 0; i++) {
            if (header[i] == 32 || header[i] == 48) {
                if (stillPadding) {
                    continue;
                } else {
                    if (header[i] == 32) {
                        break;
                    }
                    stillPadding = false;
                    result = (result << 3) + ((long) (header[i] - TarConstants.LF_NORMAL));
                }
            } else {
                stillPadding = false;
                result = (result << 3) + ((long) (header[i] - TarConstants.LF_NORMAL));
            }
        }
        return result;
    }

    public static StringBuffer parseName(byte[] header, int offset, int length) {
        StringBuffer result = new StringBuffer(length);
        int end = offset + length;
        for (int i = offset; i < end && header[i] != 0; i++) {
            result.append((char) header[i]);
        }
        return result;
    }

    public static int getNameBytes(StringBuffer name, byte[] buf, int offset, int length) {
        int i = 0;
        while (i < length && i < name.length()) {
            buf[offset + i] = (byte) name.charAt(i);
            i++;
        }
        while (i < length) {
            buf[offset + i] = 0;
            i++;
        }
        return offset + length;
    }

    public static int getOctalBytes(long value, byte[] buf, int offset, int length) {
        int idx = length - 1;
        buf[offset + idx] = 0;
        int idx2 = idx - 1;
        buf[offset + idx2] = 32;
        int idx3 = idx2 - 1;
        if (value == 0) {
            buf[offset + idx3] = TarConstants.LF_NORMAL;
            idx3--;
        } else {
            long val = value;
            while (idx3 >= 0 && val > 0) {
                buf[offset + idx3] = (byte) (((byte) (7 & val)) + TarConstants.LF_NORMAL);
                val >>= 3;
                idx3--;
            }
        }
        while (idx3 >= 0) {
            buf[offset + idx3] = 32;
            idx3--;
        }
        return offset + length;
    }

    public static int getLongOctalBytes(long value, byte[] buf, int offset, int length) {
        byte[] temp = new byte[length + 1];
        getOctalBytes(value, temp, 0, length + 1);
        System.arraycopy(temp, 0, buf, offset, length);
        return offset + length;
    }

    public static int getCheckSumOctalBytes(long value, byte[] buf, int offset, int length) {
        getOctalBytes(value, buf, offset, length);
        buf[(offset + length) - 1] = 32;
        buf[(offset + length) - 2] = 0;
        return offset + length;
    }

    public static long computeCheckSum(byte[] buf) {
        long sum = 0;
        for (byte b : buf) {
            sum += (long) (b & 255);
        }
        return sum;
    }
}

package net.bytebuddy.jar.asm;

import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public class TypePath {
    public static final int ARRAY_ELEMENT = 0;
    public static final int INNER_TYPE = 1;
    public static final int TYPE_ARGUMENT = 3;
    public static final int WILDCARD_BOUND = 2;
    byte[] b;
    int offset;

    TypePath(byte[] bArr, int i) {
        this.b = bArr;
        this.offset = i;
    }

    public int getLength() {
        return this.b[this.offset];
    }

    public int getStep(int i) {
        return this.b[this.offset + (i * 2) + 1];
    }

    public int getStepArgument(int i) {
        return this.b[this.offset + (i * 2) + 2];
    }

    public static TypePath fromString(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        ByteVector byteVector = new ByteVector(length);
        byteVector.putByte(0);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt == '[') {
                byteVector.put11(0, 0);
            } else if (cCharAt == '.') {
                byteVector.put11(1, 0);
            } else if (cCharAt == '*') {
                byteVector.put11(2, 0);
            } else if (cCharAt >= '0' && cCharAt <= '9') {
                int i3 = cCharAt - '0';
                while (i2 < length) {
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 < '0' || cCharAt2 > '9') {
                        break;
                    }
                    i3 = ((i3 * 10) + cCharAt2) - 48;
                    i2++;
                }
                if (i2 < length && str.charAt(i2) == ';') {
                    i2++;
                }
                byteVector.put11(3, i3);
            }
            i = i2;
        }
        byteVector.data[0] = (byte) (byteVector.length / 2);
        return new TypePath(byteVector.data, 0);
    }

    public String toString() {
        int length = getLength();
        StringBuilder sb = new StringBuilder(length * 2);
        for (int i = 0; i < length; i++) {
            int step = getStep(i);
            if (step == 0) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            } else if (step == 1) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
            } else if (step == 2) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.WILDCARD_TYPE_PATH);
            } else if (step == 3) {
                sb.append(getStepArgument(i)).append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INDEXED_TYPE_DELIMITER);
            } else {
                sb.append('_');
            }
        }
        return sb.toString();
    }
}

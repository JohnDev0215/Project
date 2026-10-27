package org.objectweb.asm;

/* JADX INFO: loaded from: classes6.dex */
public final class TypePath {
    private final byte[] typePathContainer;
    private final int typePathOffset;

    TypePath(byte[] bArr, int i) {
        this.typePathContainer = bArr;
        this.typePathOffset = i;
    }

    public int getLength() {
        return this.typePathContainer[this.typePathOffset];
    }

    public int getStep(int i) {
        return this.typePathContainer[this.typePathOffset + (i * 2) + 1];
    }

    public int getStepArgument(int i) {
        return this.typePathContainer[this.typePathOffset + (i * 2) + 2];
    }

    public String toString() {
        int length = getLength();
        StringBuilder sb = new StringBuilder(length * 2);
        for (int i = 0; i < length; i++) {
            int step = getStep(i);
            if (step == 0) {
                sb.append('[');
            } else if (step == 1) {
                sb.append('.');
            } else if (step == 2) {
                sb.append('*');
            } else if (step == 3) {
                sb.append(getStepArgument(i));
                sb.append(';');
            } else {
                throw new AssertionError();
            }
        }
        return sb.toString();
    }

    static void put(TypePath typePath, ByteVector byteVector) {
        if (typePath == null) {
            byteVector.putByte(0);
            return;
        }
        byte[] bArr = typePath.typePathContainer;
        int i = typePath.typePathOffset;
        byteVector.putByteArray(bArr, i, (bArr[i] * 2) + 1);
    }
}

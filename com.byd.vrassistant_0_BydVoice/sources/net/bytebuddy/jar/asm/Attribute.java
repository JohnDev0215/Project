package net.bytebuddy.jar.asm;

/* JADX INFO: loaded from: classes3.dex */
public class Attribute {
    Attribute next;
    public final String type;
    byte[] value;

    protected Label[] getLabels() {
        return null;
    }

    public boolean isCodeAttribute() {
        return false;
    }

    public boolean isUnknown() {
        return true;
    }

    protected Attribute(String str) {
        this.type = str;
    }

    protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        Attribute attribute = new Attribute(this.type);
        attribute.value = new byte[i2];
        System.arraycopy(classReader.b, i, attribute.value, 0, i2);
        return attribute;
    }

    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        ByteVector byteVector = new ByteVector();
        byteVector.data = this.value;
        byteVector.length = this.value.length;
        return byteVector;
    }

    final int getCount() {
        int i = 0;
        for (Attribute attribute = this; attribute != null; attribute = attribute.next) {
            i++;
        }
        return i;
    }

    final int getSize(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        for (Attribute attribute = this; attribute != null; attribute = attribute.next) {
            classWriter.newUTF8(attribute.type);
            i4 += attribute.write(classWriter, bArr, i, i2, i3).length + 6;
        }
        return i4;
    }

    final void put(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3, ByteVector byteVector) {
        for (Attribute attribute = this; attribute != null; attribute = attribute.next) {
            ByteVector byteVectorWrite = attribute.write(classWriter, bArr, i, i2, i3);
            byteVector.putShort(classWriter.newUTF8(attribute.type)).putInt(byteVectorWrite.length);
            byteVector.putByteArray(byteVectorWrite.data, 0, byteVectorWrite.length);
        }
    }
}

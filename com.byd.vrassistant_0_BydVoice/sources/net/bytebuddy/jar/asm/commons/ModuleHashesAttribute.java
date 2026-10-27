package net.bytebuddy.jar.asm.commons;

import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ByteVector;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;

/* JADX INFO: loaded from: classes3.dex */
public final class ModuleHashesAttribute extends Attribute {
    public String algorithm;
    public List<byte[]> hashes;
    public List<String> modules;

    public ModuleHashesAttribute(String str, List<String> list, List<byte[]> list2) {
        super("ModuleHashes");
        this.algorithm = str;
        this.modules = list;
        this.hashes = list2;
    }

    public ModuleHashesAttribute() {
        this(null, null, null);
    }

    @Override // net.bytebuddy.jar.asm.Attribute
    protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        String utf8 = classReader.readUTF8(i, cArr);
        int unsignedShort = classReader.readUnsignedShort(i + 2);
        ArrayList arrayList = new ArrayList(unsignedShort);
        ArrayList arrayList2 = new ArrayList(unsignedShort);
        int i4 = i + 4;
        for (int i5 = 0; i5 < unsignedShort; i5++) {
            String module = classReader.readModule(i4, cArr);
            int unsignedShort2 = classReader.readUnsignedShort(i4 + 2);
            int i6 = i4 + 4;
            byte[] bArr = new byte[unsignedShort2];
            for (int i7 = 0; i7 < unsignedShort2; i7++) {
                bArr[i7] = (byte) (classReader.readByte(i6 + i7) & 255);
            }
            i4 = i6 + unsignedShort2;
            arrayList.add(module);
            arrayList2.add(bArr);
        }
        return new ModuleHashesAttribute(utf8, arrayList, arrayList2);
    }

    @Override // net.bytebuddy.jar.asm.Attribute
    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(classWriter.newUTF8(this.algorithm));
        List<String> list = this.modules;
        int size = list == null ? 0 : list.size();
        byteVector.putShort(size);
        for (int i4 = 0; i4 < size; i4++) {
            byteVector.putShort(classWriter.newModule(this.modules.get(i4)));
            byte[] bArr2 = this.hashes.get(i4);
            byteVector.putShort(bArr2.length);
            for (byte b : bArr2) {
                byteVector.putByte(b);
            }
        }
        return byteVector;
    }
}

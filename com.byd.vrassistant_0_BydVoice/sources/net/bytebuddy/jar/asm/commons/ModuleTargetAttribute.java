package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ByteVector;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;

/* JADX INFO: loaded from: classes3.dex */
public final class ModuleTargetAttribute extends Attribute {
    public String platform;

    public ModuleTargetAttribute(String str) {
        super("ModuleTarget");
        this.platform = str;
    }

    public ModuleTargetAttribute() {
        this(null);
    }

    @Override // net.bytebuddy.jar.asm.Attribute
    protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        return new ModuleTargetAttribute(classReader.readUTF8(i, cArr));
    }

    @Override // net.bytebuddy.jar.asm.Attribute
    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        ByteVector byteVector = new ByteVector();
        String str = this.platform;
        byteVector.putShort(str == null ? 0 : classWriter.newUTF8(str));
        return byteVector;
    }
}

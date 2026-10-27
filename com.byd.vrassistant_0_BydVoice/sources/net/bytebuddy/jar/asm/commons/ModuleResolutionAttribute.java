package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ByteVector;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;

/* JADX INFO: loaded from: classes3.dex */
public final class ModuleResolutionAttribute extends Attribute {
    public static final int RESOLUTION_DO_NOT_RESOLVE_BY_DEFAULT = 1;
    public static final int RESOLUTION_WARN_DEPRECATED = 2;
    public static final int RESOLUTION_WARN_DEPRECATED_FOR_REMOVAL = 4;
    public static final int RESOLUTION_WARN_INCUBATING = 8;
    public int resolution;

    public ModuleResolutionAttribute(int i) {
        super("ModuleResolution");
        this.resolution = i;
    }

    public ModuleResolutionAttribute() {
        this(0);
    }

    @Override // net.bytebuddy.jar.asm.Attribute
    protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        return new ModuleResolutionAttribute(classReader.readUnsignedShort(i));
    }

    @Override // net.bytebuddy.jar.asm.Attribute
    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.resolution);
        return byteVector;
    }
}

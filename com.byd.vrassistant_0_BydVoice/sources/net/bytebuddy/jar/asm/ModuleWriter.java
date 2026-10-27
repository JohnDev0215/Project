package net.bytebuddy.jar.asm;

/* JADX INFO: loaded from: classes3.dex */
final class ModuleWriter extends ModuleVisitor {
    private final int access;
    int attributeCount;
    int attributesSize;
    private final ClassWriter cw;
    private int exportCount;
    private ByteVector exports;
    private int mainClass;
    private final int name;
    private int openCount;
    private ByteVector opens;
    private int packageCount;
    private ByteVector packages;
    private int provideCount;
    private ByteVector provides;
    private int requireCount;
    private ByteVector requires;
    int size;
    private int useCount;
    private ByteVector uses;
    private final int version;

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public void visitEnd() {
    }

    ModuleWriter(ClassWriter classWriter, int i, int i2, int i3) {
        super(Opcodes.ASM6);
        this.cw = classWriter;
        this.size = 16;
        this.name = i;
        this.access = i2;
        this.version = i3;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public void visitMainClass(String str) {
        if (this.mainClass == 0) {
            this.cw.newUTF8("ModuleMainClass");
            this.attributeCount++;
            this.attributesSize += 8;
        }
        this.mainClass = this.cw.newClass(str);
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public void visitPackage(String str) {
        if (this.packages == null) {
            this.cw.newUTF8("ModulePackages");
            this.packages = new ByteVector();
            this.attributeCount++;
            this.attributesSize += 8;
        }
        this.packages.putShort(this.cw.newPackage(str));
        this.packageCount++;
        this.attributesSize += 2;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public void visitRequire(String str, int i, String str2) {
        if (this.requires == null) {
            this.requires = new ByteVector();
        }
        this.requires.putShort(this.cw.newModule(str)).putShort(i).putShort(str2 == null ? 0 : this.cw.newUTF8(str2));
        this.requireCount++;
        this.size += 6;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public void visitExport(String str, int i, String... strArr) {
        if (this.exports == null) {
            this.exports = new ByteVector();
        }
        this.exports.putShort(this.cw.newPackage(str)).putShort(i);
        if (strArr == null) {
            this.exports.putShort(0);
            this.size += 6;
        } else {
            this.exports.putShort(strArr.length);
            for (String str2 : strArr) {
                this.exports.putShort(this.cw.newModule(str2));
            }
            this.size += (strArr.length * 2) + 6;
        }
        this.exportCount++;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public void visitOpen(String str, int i, String... strArr) {
        if (this.opens == null) {
            this.opens = new ByteVector();
        }
        this.opens.putShort(this.cw.newPackage(str)).putShort(i);
        if (strArr == null) {
            this.opens.putShort(0);
            this.size += 6;
        } else {
            this.opens.putShort(strArr.length);
            for (String str2 : strArr) {
                this.opens.putShort(this.cw.newModule(str2));
            }
            this.size += (strArr.length * 2) + 6;
        }
        this.openCount++;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public void visitUse(String str) {
        if (this.uses == null) {
            this.uses = new ByteVector();
        }
        this.uses.putShort(this.cw.newClass(str));
        this.useCount++;
        this.size += 2;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public void visitProvide(String str, String... strArr) {
        if (this.provides == null) {
            this.provides = new ByteVector();
        }
        this.provides.putShort(this.cw.newClass(str));
        this.provides.putShort(strArr.length);
        for (String str2 : strArr) {
            this.provides.putShort(this.cw.newClass(str2));
        }
        this.provideCount++;
        this.size += (strArr.length * 2) + 4;
    }

    void putAttributes(ByteVector byteVector) {
        if (this.mainClass != 0) {
            byteVector.putShort(this.cw.newUTF8("ModuleMainClass")).putInt(2).putShort(this.mainClass);
        }
        if (this.packages != null) {
            byteVector.putShort(this.cw.newUTF8("ModulePackages")).putInt((this.packageCount * 2) + 2).putShort(this.packageCount).putByteArray(this.packages.data, 0, this.packages.length);
        }
    }

    void put(ByteVector byteVector) {
        byteVector.putInt(this.size);
        byteVector.putShort(this.name).putShort(this.access).putShort(this.version);
        byteVector.putShort(this.requireCount);
        ByteVector byteVector2 = this.requires;
        if (byteVector2 != null) {
            byteVector.putByteArray(byteVector2.data, 0, this.requires.length);
        }
        byteVector.putShort(this.exportCount);
        ByteVector byteVector3 = this.exports;
        if (byteVector3 != null) {
            byteVector.putByteArray(byteVector3.data, 0, this.exports.length);
        }
        byteVector.putShort(this.openCount);
        ByteVector byteVector4 = this.opens;
        if (byteVector4 != null) {
            byteVector.putByteArray(byteVector4.data, 0, this.opens.length);
        }
        byteVector.putShort(this.useCount);
        ByteVector byteVector5 = this.uses;
        if (byteVector5 != null) {
            byteVector.putByteArray(byteVector5.data, 0, this.uses.length);
        }
        byteVector.putShort(this.provideCount);
        ByteVector byteVector6 = this.provides;
        if (byteVector6 != null) {
            byteVector.putByteArray(byteVector6.data, 0, this.provides.length);
        }
    }
}

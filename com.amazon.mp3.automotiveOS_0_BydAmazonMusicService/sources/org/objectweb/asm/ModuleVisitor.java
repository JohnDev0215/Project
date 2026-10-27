package org.objectweb.asm;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ModuleVisitor {
    protected final int api;
    protected ModuleVisitor mv;

    public abstract void visitEnd();

    public abstract void visitExport(String str, int i, String... strArr);

    public abstract void visitMainClass(String str);

    public abstract void visitOpen(String str, int i, String... strArr);

    public abstract void visitPackage(String str);

    public abstract void visitProvide(String str, String... strArr);

    public abstract void visitRequire(String str, int i, String str2);

    public abstract void visitUse(String str);

    public ModuleVisitor(int i) {
        this(i, null);
    }

    public ModuleVisitor(int i, ModuleVisitor moduleVisitor) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            throw new IllegalArgumentException("Unsupported api " + i);
        }
        if (i == 17432576) {
            Constants.checkAsmExperimental(this);
        }
        this.api = i;
        this.mv = moduleVisitor;
    }
}

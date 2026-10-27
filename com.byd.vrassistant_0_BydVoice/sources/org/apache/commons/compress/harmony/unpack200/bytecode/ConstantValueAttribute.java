package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public class ConstantValueAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private int constantIndex;
    private final ClassFileEntry entry;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return 2;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public ConstantValueAttribute(ClassFileEntry classFileEntry) {
        super(attributeName);
        classFileEntry.getClass();
        this.entry = classFileEntry;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        ConstantValueAttribute constantValueAttribute = (ConstantValueAttribute) obj;
        ClassFileEntry classFileEntry = this.entry;
        if (classFileEntry == null) {
            if (constantValueAttribute.entry != null) {
                return false;
            }
        } else if (!classFileEntry.equals(constantValueAttribute.entry)) {
            return false;
        }
        return true;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName(), this.entry};
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        ClassFileEntry classFileEntry = this.entry;
        return iHashCode + (classFileEntry == null ? 0 : classFileEntry.hashCode());
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.entry.resolve(classConstantPool);
        this.constantIndex = classConstantPool.indexOf(this.entry);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Constant:" + this.entry;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.constantIndex);
    }
}

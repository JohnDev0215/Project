package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public class SourceFileAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private final CPUTF8 name;
    private int nameIndex;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return 2;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    public boolean isSourceFileAttribute() {
        return true;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public SourceFileAttribute(CPUTF8 cputf8) {
        super(attributeName);
        this.name = cputf8;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        SourceFileAttribute sourceFileAttribute = (SourceFileAttribute) obj;
        CPUTF8 cputf8 = this.name;
        if (cputf8 == null) {
            if (sourceFileAttribute.name != null) {
                return false;
            }
        } else if (!cputf8.equals(sourceFileAttribute.name)) {
            return false;
        }
        return true;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName(), this.name};
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        CPUTF8 cputf8 = this.name;
        return iHashCode + (cputf8 == null ? 0 : cputf8.hashCode());
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.nameIndex = classConstantPool.indexOf(this.name);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "SourceFile: " + this.name;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.nameIndex);
    }
}

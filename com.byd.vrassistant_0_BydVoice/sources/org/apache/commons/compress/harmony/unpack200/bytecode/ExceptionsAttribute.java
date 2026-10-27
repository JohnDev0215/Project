package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class ExceptionsAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private transient int[] exceptionIndexes;
    private final CPClass[] exceptions;

    private static int hashCode(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int iHashCode = 1;
        for (int i = 0; i < objArr.length; i++) {
            int i2 = iHashCode * 31;
            Object obj = objArr[i];
            iHashCode = i2 + (obj == null ? 0 : obj.hashCode());
        }
        return iHashCode;
    }

    public ExceptionsAttribute(CPClass[] cPClassArr) {
        super(attributeName);
        this.exceptions = cPClassArr;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj) && getClass() == obj.getClass() && Arrays.equals(this.exceptions, ((ExceptionsAttribute) obj).exceptions);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return (this.exceptions.length * 2) + 2;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected ClassFileEntry[] getNestedClassFileEntries() {
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[this.exceptions.length + 1];
        int i = 0;
        while (true) {
            CPClass[] cPClassArr = this.exceptions;
            if (i < cPClassArr.length) {
                classFileEntryArr[i] = cPClassArr[i];
                i++;
            } else {
                classFileEntryArr[cPClassArr.length] = getAttributeName();
                return classFileEntryArr;
            }
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        return (super.hashCode() * 31) + hashCode(this.exceptions);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.exceptionIndexes = new int[this.exceptions.length];
        int i = 0;
        while (true) {
            CPClass[] cPClassArr = this.exceptions;
            if (i >= cPClassArr.length) {
                return;
            }
            cPClassArr[i].resolve(classConstantPool);
            this.exceptionIndexes[i] = classConstantPool.indexOf(this.exceptions[i]);
            i++;
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Exceptions: ");
        int i = 0;
        while (true) {
            CPClass[] cPClassArr = this.exceptions;
            if (i < cPClassArr.length) {
                stringBuffer.append(cPClassArr[i]);
                stringBuffer.append(' ');
                i++;
            } else {
                return stringBuffer.toString();
            }
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.exceptionIndexes.length);
        int i = 0;
        while (true) {
            int[] iArr = this.exceptionIndexes;
            if (i >= iArr.length) {
                return;
            }
            dataOutputStream.writeShort(iArr[i]);
            i++;
        }
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }
}

package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;

/* JADX INFO: loaded from: classes4.dex */
public class RuntimeVisibleorInvisibleAnnotationsAttribute extends AnnotationsAttribute {
    private final AnnotationsAttribute.Annotation[] annotations;
    private final int num_annotations;

    public RuntimeVisibleorInvisibleAnnotationsAttribute(CPUTF8 cputf8, AnnotationsAttribute.Annotation[] annotationArr) {
        super(cputf8);
        this.num_annotations = annotationArr.length;
        this.annotations = annotationArr;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        int length = 2;
        for (int i = 0; i < this.num_annotations; i++) {
            length += this.annotations[i].getLength();
        }
        return length;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        int i = 0;
        while (true) {
            AnnotationsAttribute.Annotation[] annotationArr = this.annotations;
            if (i >= annotationArr.length) {
                return;
            }
            annotationArr[i].resolve(classConstantPool);
            i++;
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        int size = dataOutputStream.size();
        dataOutputStream.writeShort(this.num_annotations);
        for (int i = 0; i < this.num_annotations; i++) {
            this.annotations[i].writeBody(dataOutputStream);
        }
        if (dataOutputStream.size() - size != getLength()) {
            throw new Error();
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return this.attributeName.underlyingString() + ": " + this.num_annotations + " annotations";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected ClassFileEntry[] getNestedClassFileEntries() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.attributeName);
        int i = 0;
        while (true) {
            AnnotationsAttribute.Annotation[] annotationArr = this.annotations;
            if (i >= annotationArr.length) {
                break;
            }
            arrayList.addAll(annotationArr[i].getClassFileEntries());
            i++;
        }
        int size = arrayList.size();
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[size];
        for (int i2 = 0; i2 < size; i2++) {
            classFileEntryArr[i2] = (ClassFileEntry) arrayList.get(i2);
        }
        return classFileEntryArr;
    }
}

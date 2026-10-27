package com.google.firebase.perf.plugin.instrumentation;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ByteVector;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;

/* JADX INFO: loaded from: classes6.dex */
public class PerfInstrumentedAttribute extends Attribute {
    private final String extra;

    @Override // org.objectweb.asm.Attribute
    public boolean isCodeAttribute() {
        return false;
    }

    @Override // org.objectweb.asm.Attribute
    public boolean isUnknown() {
        return false;
    }

    public PerfInstrumentedAttribute(String str) {
        super(PerfInstrumentedAttribute.class.getSimpleName());
        this.extra = str;
    }

    @Override // org.objectweb.asm.Attribute
    protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        return new PerfInstrumentedAttribute(classReader.readUTF8(i, cArr));
    }

    @Override // org.objectweb.asm.Attribute
    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        return new ByteVector().putShort(classWriter.newUTF8(this.extra));
    }
}

package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public class CPFloat extends CPConstantNumber {
    public CPFloat(Float f, int i) {
        super((byte) 4, f, i);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeFloat(getNumber().floatValue());
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Float: " + getValue();
    }
}

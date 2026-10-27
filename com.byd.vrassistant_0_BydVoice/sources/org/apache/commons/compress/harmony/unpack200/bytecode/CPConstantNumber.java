package org.apache.commons.compress.harmony.unpack200.bytecode;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CPConstantNumber extends CPConstant {
    public CPConstantNumber(byte b, Object obj, int i) {
        super(b, obj, i);
    }

    protected Number getNumber() {
        return (Number) getValue();
    }
}

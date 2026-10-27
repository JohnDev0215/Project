package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* JADX INFO: loaded from: classes4.dex */
public class WideForm extends VariableInstructionForm {
    public WideForm(int i, String str) {
        super(i, str);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int iNextWideByteCode = operandManager.nextWideByteCode();
        if (iNextWideByteCode == 132) {
            setByteCodeOperandsFormat2(iNextWideByteCode, byteCode, operandManager, i);
        } else {
            setByteCodeOperandsFormat1(iNextWideByteCode, byteCode, operandManager, i);
        }
    }

    protected void setByteCodeOperandsFormat1(int i, ByteCode byteCode, OperandManager operandManager, int i2) {
        int iNextLocal = operandManager.nextLocal();
        int[] iArr = {byteCode.getOpcode(), i, 0, 0};
        setRewrite2Bytes(iNextLocal, 2, iArr);
        byteCode.setRewrite(iArr);
    }

    protected void setByteCodeOperandsFormat2(int i, ByteCode byteCode, OperandManager operandManager, int i2) {
        int iNextLocal = operandManager.nextLocal();
        int iNextShort = operandManager.nextShort();
        int[] iArr = new int[6];
        iArr[0] = byteCode.getOpcode();
        iArr[1] = i;
        setRewrite2Bytes(iNextLocal, 2, iArr);
        setRewrite2Bytes(iNextShort, 4, iArr);
        byteCode.setRewrite(iArr);
    }
}

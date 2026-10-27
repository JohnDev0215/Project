package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* JADX INFO: loaded from: classes4.dex */
public class LookupSwitchForm extends SwitchForm {
    public LookupSwitchForm(int i, String str) {
        super(i, str);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int iNextCaseCount = operandManager.nextCaseCount();
        int iNextLabel = operandManager.nextLabel();
        int[] iArr = new int[iNextCaseCount];
        for (int i2 = 0; i2 < iNextCaseCount; i2++) {
            iArr[i2] = operandManager.nextCaseValues();
        }
        int[] iArr2 = new int[iNextCaseCount];
        for (int i3 = 0; i3 < iNextCaseCount; i3++) {
            iArr2[i3] = operandManager.nextLabel();
        }
        int i4 = iNextCaseCount + 1;
        int[] iArr3 = new int[i4];
        iArr3[0] = iNextLabel;
        int i5 = 1;
        for (int i6 = 1; i6 < i4; i6++) {
            iArr3[i6] = iArr2[i6 - 1];
        }
        byteCode.setByteCodeTargets(iArr3);
        int i7 = 3 - (i % 4);
        int i8 = iNextCaseCount * 4;
        int[] iArr4 = new int[i7 + 1 + 4 + 4 + i8 + i8];
        iArr4[0] = byteCode.getOpcode();
        int i9 = 0;
        while (i9 < i7) {
            iArr4[i5] = 0;
            i9++;
            i5++;
        }
        int i10 = i5 + 1;
        iArr4[i5] = -1;
        int i11 = i10 + 1;
        iArr4[i10] = -1;
        int i12 = i11 + 1;
        iArr4[i11] = -1;
        int i13 = i12 + 1;
        iArr4[i12] = -1;
        setRewrite4Bytes(iNextCaseCount, i13, iArr4);
        int i14 = i13 + 4;
        for (int i15 = 0; i15 < iNextCaseCount; i15++) {
            setRewrite4Bytes(iArr[i15], i14, iArr4);
            int i16 = i14 + 4;
            int i17 = i16 + 1;
            iArr4[i16] = -1;
            int i18 = i17 + 1;
            iArr4[i17] = -1;
            int i19 = i18 + 1;
            iArr4[i18] = -1;
            i14 = i19 + 1;
            iArr4[i19] = -1;
        }
        byteCode.setRewrite(iArr4);
    }
}

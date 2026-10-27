package net.bytebuddy.utility.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public class StackAwareMethodVisitor extends MethodVisitor {
    private static final int[] SIZE_CHANGE = new int[202];
    private List<StackSize> current;
    private int freeIndex;
    private final Map<Label, List<StackSize>> sizes;

    static {
        int i = 0;
        while (true) {
            int[] iArr = SIZE_CHANGE;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEEEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i) - 'E';
            i++;
        }
    }

    public StackAwareMethodVisitor(MethodVisitor methodVisitor, MethodDescription methodDescription) {
        super(Opcodes.ASM6, methodVisitor);
        this.current = new ArrayList();
        this.sizes = new HashMap();
        this.freeIndex = methodDescription.getStackSize();
    }

    private void adjustStack(int i) {
        adjustStack(i, 0);
    }

    private void adjustStack(int i, int i2) {
        if (i > 2) {
            throw new IllegalStateException("Cannot push multiple values onto the operand stack: " + i);
        }
        if (i > 0) {
            int size = this.current.size();
            while (i2 > 0 && size > 0) {
                size--;
                i2 -= this.current.get(size).getSize();
            }
            if (i2 < 0) {
                throw new IllegalStateException("Unexpected offset underflow: " + i2);
            }
            this.current.add(size, StackSize.of(i));
            return;
        }
        if (i2 != 0) {
            throw new IllegalStateException("Cannot specify non-zero offset " + i2 + " for non-incrementing value: " + i);
        }
        while (i < 0) {
            if (this.current.isEmpty()) {
                return;
            }
            List<StackSize> list = this.current;
            i += list.remove(list.size() - 1).getSize();
        }
        if (i == 1) {
            this.current.add(StackSize.SINGLE);
        } else if (i != 0) {
            throw new IllegalStateException("Unexpected remainder on the operand stack: " + i);
        }
    }

    public void drainStack() {
        doDrain(this.current);
    }

    public int drainStack(int i, int i2, StackSize stackSize) {
        List<StackSize> list = this.current;
        int size = list.get(list.size() - 1).getSize() - stackSize.getSize();
        if (this.current.size() == 1 && size == 0) {
            return 0;
        }
        super.visitVarInsn(i, this.freeIndex);
        if (size == 1) {
            super.visitInsn(87);
        } else if (size != 0) {
            throw new IllegalStateException("Unexpected remainder on the operand stack: " + size);
        }
        List<StackSize> list2 = this.current;
        doDrain(list2.subList(0, list2.size() - 1));
        super.visitVarInsn(i2, this.freeIndex);
        return this.freeIndex + stackSize.getSize();
    }

    private void doDrain(List<StackSize> list) {
        ListIterator<StackSize> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            StackSize stackSizePrevious = listIterator.previous();
            int i = AnonymousClass1.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[stackSizePrevious.ordinal()];
            if (i == 1) {
                super.visitInsn(87);
            } else if (i == 2) {
                super.visitInsn(88);
            } else {
                throw new IllegalStateException("Unexpected stack size: " + stackSizePrevious);
            }
        }
    }

    /* JADX INFO: renamed from: net.bytebuddy.utility.visitor.StackAwareMethodVisitor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize;

        static {
            int[] iArr = new int[StackSize.values().length];
            $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize = iArr;
            try {
                iArr[StackSize.SINGLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[StackSize.DOUBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void register(Label label, List<StackSize> list) {
        this.sizes.put(label, list);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0059  */
    @Override // net.bytebuddy.jar.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitInsn(int r4) {
        /*
            r3 = this;
            r0 = 47
            r1 = -2
            r2 = 2
            if (r4 == r0) goto L61
            r0 = 49
            if (r4 == r0) goto L61
            r0 = 133(0x85, float:1.86E-43)
            if (r4 == r0) goto L59
            r0 = 144(0x90, float:2.02E-43)
            if (r4 == r0) goto L51
            r0 = 191(0xbf, float:2.68E-43)
            if (r4 == r0) goto L4b
            r0 = 90
            if (r4 == r0) goto L41
            r0 = 91
            if (r4 == r0) goto L37
            r0 = 93
            if (r4 == r0) goto L41
            r0 = 94
            if (r4 == r0) goto L37
            switch(r4) {
                case 135: goto L59;
                case 136: goto L51;
                case 137: goto L51;
                default: goto L29;
            }
        L29:
            switch(r4) {
                case 140: goto L59;
                case 141: goto L59;
                case 142: goto L51;
                default: goto L2c;
            }
        L2c:
            switch(r4) {
                case 172: goto L4b;
                case 173: goto L4b;
                case 174: goto L4b;
                case 175: goto L4b;
                case 176: goto L4b;
                case 177: goto L4b;
                default: goto L2f;
            }
        L2f:
            int[] r0 = net.bytebuddy.utility.visitor.StackAwareMethodVisitor.SIZE_CHANGE
            r0 = r0[r4]
            r3.adjustStack(r0)
            goto L67
        L37:
            int[] r0 = net.bytebuddy.utility.visitor.StackAwareMethodVisitor.SIZE_CHANGE
            r0 = r0[r4]
            int r1 = r0 + 2
            r3.adjustStack(r0, r1)
            goto L67
        L41:
            int[] r0 = net.bytebuddy.utility.visitor.StackAwareMethodVisitor.SIZE_CHANGE
            r0 = r0[r4]
            int r1 = r0 + 1
            r3.adjustStack(r0, r1)
            goto L67
        L4b:
            java.util.List<net.bytebuddy.implementation.bytecode.StackSize> r0 = r3.current
            r0.clear()
            goto L67
        L51:
            r3.adjustStack(r1)
            r0 = 1
            r3.adjustStack(r0)
            goto L67
        L59:
            r0 = -1
            r3.adjustStack(r0)
            r3.adjustStack(r2)
            goto L67
        L61:
            r3.adjustStack(r1)
            r3.adjustStack(r2)
        L67:
            super.visitInsn(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.utility.visitor.StackAwareMethodVisitor.visitInsn(int):void");
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        adjustStack(SIZE_CHANGE[i]);
        super.visitIntInsn(i, i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        if (i != 169) {
            switch (i) {
                case 54:
                case 56:
                case 58:
                    this.freeIndex = Math.max(this.freeIndex, i2 + 1);
                    break;
                case 55:
                case 57:
                    this.freeIndex = Math.max(this.freeIndex, i2 + 2);
                    break;
            }
        } else {
            this.current.clear();
        }
        adjustStack(SIZE_CHANGE[i]);
        super.visitVarInsn(i, i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        adjustStack(SIZE_CHANGE[i]);
        super.visitTypeInsn(i, str);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        int size = Type.getType(str3).getSize();
        switch (i) {
            case 178:
                adjustStack(size);
                break;
            case 179:
                adjustStack(-size);
                break;
            case 180:
                adjustStack(-1);
                adjustStack(size);
                break;
            case 181:
                adjustStack((-size) - 1);
                break;
            default:
                throw new IllegalStateException("Unexpected opcode: " + i);
        }
        super.visitFieldInsn(i, str, str2, str3);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str3);
        adjustStack((-(argumentsAndReturnSizes >> 2)) + (i == 184 ? 1 : 0));
        adjustStack(argumentsAndReturnSizes & 3);
        super.visitMethodInsn(i, str, str2, str3, z);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str2);
        adjustStack((-(argumentsAndReturnSizes >> 2)) + 1);
        adjustStack(argumentsAndReturnSizes & 3);
        super.visitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        adjustStack(((obj instanceof Long) || (obj instanceof Double)) ? 2 : 1);
        super.visitLdcInsn(obj);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        adjustStack(1 - i);
        super.visitMultiANewArrayInsn(str, i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitJumpInsn(int i, Label label) {
        adjustStack(SIZE_CHANGE[i]);
        this.sizes.put(label, new ArrayList(i == 168 ? CompoundList.of(this.current, StackSize.SINGLE) : this.current));
        if (i == 167) {
            this.current.clear();
        }
        super.visitJumpInsn(i, label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLabel(Label label) {
        List<StackSize> list = this.sizes.get(label);
        if (list != null) {
            this.current = new ArrayList(list);
        }
        super.visitLabel(label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLineNumber(int i, Label label) {
        super.visitLineNumber(i, label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        adjustStack(-1);
        ArrayList arrayList = new ArrayList(this.current);
        this.sizes.put(label, arrayList);
        for (Label label2 : labelArr) {
            this.sizes.put(label2, arrayList);
        }
        super.visitTableSwitchInsn(i, i2, label, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        adjustStack(-1);
        ArrayList arrayList = new ArrayList(this.current);
        this.sizes.put(label, arrayList);
        for (Label label2 : labelArr) {
            this.sizes.put(label2, arrayList);
        }
        super.visitLookupSwitchInsn(label, iArr, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        this.sizes.put(label3, Collections.singletonList(StackSize.SINGLE));
        super.visitTryCatchBlock(label, label2, label3, str);
    }
}

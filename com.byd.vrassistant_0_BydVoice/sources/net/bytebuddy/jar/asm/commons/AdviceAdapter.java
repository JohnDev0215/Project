package net.bytebuddy.jar.asm.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AdviceAdapter extends GeneratorAdapter implements Opcodes {
    private Map<Label, List<Object>> branches;
    private boolean constructor;
    protected int methodAccess;
    protected String methodDesc;
    private List<Object> stackFrame;
    private boolean superInitialized;
    private static final Object THIS = new Object();
    private static final Object OTHER = new Object();

    protected void onMethodEnter() {
    }

    protected void onMethodExit(int i) {
    }

    protected AdviceAdapter(int i, MethodVisitor methodVisitor, int i2, String str, String str2) {
        super(i, methodVisitor, i2, str, str2);
        this.methodAccess = i2;
        this.methodDesc = str2;
        this.constructor = MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(str);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitCode() {
        this.mv.visitCode();
        if (this.constructor) {
            this.stackFrame = new ArrayList();
            this.branches = new HashMap();
        } else {
            this.superInitialized = true;
            onMethodEnter();
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLabel(Label label) {
        Map<Label, List<Object>> map;
        List<Object> list;
        this.mv.visitLabel(label);
        if (!this.constructor || (map = this.branches) == null || (list = map.get(label)) == null) {
            return;
        }
        this.stackFrame = list;
        this.branches.remove(label);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0122 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x012c  */
    @Override // net.bytebuddy.jar.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitInsn(int r5) {
        /*
            Method dump skipped, instruction units count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.commons.AdviceAdapter.visitInsn(int):void");
    }

    @Override // net.bytebuddy.jar.asm.commons.LocalVariablesSorter, net.bytebuddy.jar.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        super.visitVarInsn(i, i2);
        if (this.constructor) {
            switch (i) {
                case 21:
                case 23:
                    pushValue(OTHER);
                    break;
                case 22:
                case 24:
                    Object obj = OTHER;
                    pushValue(obj);
                    pushValue(obj);
                    break;
                case 25:
                    pushValue(i2 == 0 ? THIS : OTHER);
                    break;
                default:
                    switch (i) {
                        case 54:
                        case 56:
                        case 58:
                            popValue();
                            break;
                        case 55:
                        case 57:
                            popValue();
                            popValue();
                            break;
                    }
                    break;
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        this.mv.visitFieldInsn(i, str, str2, str3);
        if (this.constructor) {
            char cCharAt = str3.charAt(0);
            boolean z = cCharAt == 'J' || cCharAt == 'D';
            if (i == 178) {
                Object obj = OTHER;
                pushValue(obj);
                if (z) {
                    pushValue(obj);
                    return;
                }
                return;
            }
            if (i == 179) {
                popValue();
                if (z) {
                    popValue();
                    return;
                }
                return;
            }
            if (i != 181) {
                if (z) {
                    pushValue(OTHER);
                }
            } else {
                popValue();
                popValue();
                if (z) {
                    popValue();
                }
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        this.mv.visitIntInsn(i, i2);
        if (!this.constructor || i == 188) {
            return;
        }
        pushValue(OTHER);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        this.mv.visitLdcInsn(obj);
        if (this.constructor) {
            Object obj2 = OTHER;
            pushValue(obj2);
            if ((obj instanceof Double) || (obj instanceof Long)) {
                pushValue(obj2);
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        this.mv.visitMultiANewArrayInsn(str, i);
        if (this.constructor) {
            for (int i2 = 0; i2 < i; i2++) {
                popValue();
            }
            pushValue(OTHER);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        this.mv.visitTypeInsn(i, str);
        if (this.constructor && i == 187) {
            pushValue(OTHER);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    @Deprecated
    public void visitMethodInsn(int i, String str, String str2, String str3) {
        if (this.api >= 327680) {
            super.visitMethodInsn(i, str, str2, str3);
        } else {
            doVisitMethodInsn(i, str, str2, str3, i == 185);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        if (this.api < 327680) {
            super.visitMethodInsn(i, str, str2, str3, z);
        } else {
            doVisitMethodInsn(i, str, str2, str3, z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doVisitMethodInsn(int r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, boolean r11) {
        /*
            r6 = this;
            net.bytebuddy.jar.asm.MethodVisitor r0 = r6.mv
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r0.visitMethodInsn(r1, r2, r3, r4, r5)
            boolean r8 = r6.constructor
            if (r8 == 0) goto L64
            net.bytebuddy.jar.asm.Type[] r8 = net.bytebuddy.jar.asm.Type.getArgumentTypes(r10)
            r9 = 0
            r11 = r9
        L14:
            int r0 = r8.length
            r1 = 2
            if (r11 >= r0) goto L29
            r6.popValue()
            r0 = r8[r11]
            int r0 = r0.getSize()
            if (r0 != r1) goto L26
            r6.popValue()
        L26:
            int r11 = r11 + 1
            goto L14
        L29:
            r8 = 182(0xb6, float:2.55E-43)
            if (r7 == r8) goto L4b
            r8 = 183(0xb7, float:2.56E-43)
            if (r7 == r8) goto L36
            r8 = 185(0xb9, float:2.59E-43)
            if (r7 == r8) goto L4b
            goto L4e
        L36:
            java.lang.Object r7 = r6.popValue()
            java.lang.Object r8 = net.bytebuddy.jar.asm.commons.AdviceAdapter.THIS
            if (r7 != r8) goto L4e
            boolean r7 = r6.superInitialized
            if (r7 != 0) goto L4e
            r6.onMethodEnter()
            r7 = 1
            r6.superInitialized = r7
            r6.constructor = r9
            goto L4e
        L4b:
            r6.popValue()
        L4e:
            net.bytebuddy.jar.asm.Type r7 = net.bytebuddy.jar.asm.Type.getReturnType(r10)
            net.bytebuddy.jar.asm.Type r8 = net.bytebuddy.jar.asm.Type.VOID_TYPE
            if (r7 == r8) goto L64
            java.lang.Object r8 = net.bytebuddy.jar.asm.commons.AdviceAdapter.OTHER
            r6.pushValue(r8)
            int r7 = r7.getSize()
            if (r7 != r1) goto L64
            r6.pushValue(r8)
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.commons.AdviceAdapter.doVisitMethodInsn(int, java.lang.String, java.lang.String, java.lang.String, boolean):void");
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        this.mv.visitInvokeDynamicInsn(str, str2, handle, objArr);
        if (this.constructor) {
            for (Type type : Type.getArgumentTypes(str2)) {
                popValue();
                if (type.getSize() == 2) {
                    popValue();
                }
            }
            Type returnType = Type.getReturnType(str2);
            if (returnType != Type.VOID_TYPE) {
                Object obj = OTHER;
                pushValue(obj);
                if (returnType.getSize() == 2) {
                    pushValue(obj);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0020  */
    @Override // net.bytebuddy.jar.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitJumpInsn(int r2, net.bytebuddy.jar.asm.Label r3) {
        /*
            r1 = this;
            net.bytebuddy.jar.asm.MethodVisitor r0 = r1.mv
            r0.visitJumpInsn(r2, r3)
            boolean r0 = r1.constructor
            if (r0 == 0) goto L2c
            r0 = 168(0xa8, float:2.35E-43)
            if (r2 == r0) goto L24
            r0 = 198(0xc6, float:2.77E-43)
            if (r2 == r0) goto L20
            r0 = 199(0xc7, float:2.79E-43)
            if (r2 == r0) goto L20
            switch(r2) {
                case 153: goto L20;
                case 154: goto L20;
                case 155: goto L20;
                case 156: goto L20;
                case 157: goto L20;
                case 158: goto L20;
                case 159: goto L19;
                case 160: goto L19;
                case 161: goto L19;
                case 162: goto L19;
                case 163: goto L19;
                case 164: goto L19;
                case 165: goto L19;
                case 166: goto L19;
                default: goto L18;
            }
        L18:
            goto L29
        L19:
            r1.popValue()
            r1.popValue()
            goto L29
        L20:
            r1.popValue()
            goto L29
        L24:
            java.lang.Object r2 = net.bytebuddy.jar.asm.commons.AdviceAdapter.OTHER
            r1.pushValue(r2)
        L29:
            r1.addBranch(r3)
        L2c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.commons.AdviceAdapter.visitJumpInsn(int, net.bytebuddy.jar.asm.Label):void");
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.mv.visitLookupSwitchInsn(label, iArr, labelArr);
        if (this.constructor) {
            popValue();
            addBranches(label, labelArr);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        this.mv.visitTableSwitchInsn(i, i2, label, labelArr);
        if (this.constructor) {
            popValue();
            addBranches(label, labelArr);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        super.visitTryCatchBlock(label, label2, label3, str);
        if (!this.constructor || this.branches.containsKey(label3)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(OTHER);
        this.branches.put(label3, arrayList);
    }

    private void addBranches(Label label, Label[] labelArr) {
        addBranch(label);
        for (Label label2 : labelArr) {
            addBranch(label2);
        }
    }

    private void addBranch(Label label) {
        if (this.branches.containsKey(label)) {
            return;
        }
        this.branches.put(label, new ArrayList(this.stackFrame));
    }

    private Object popValue() {
        return this.stackFrame.remove(r0.size() - 1);
    }

    private Object peekValue() {
        return this.stackFrame.get(r0.size() - 1);
    }

    private void pushValue(Object obj) {
        this.stackFrame.add(obj);
    }
}

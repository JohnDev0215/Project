package org.objectweb.asm.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/* JADX INFO: loaded from: classes6.dex */
public abstract class AdviceAdapter extends GeneratorAdapter implements Opcodes {
    private static final String INVALID_OPCODE = "Invalid opcode ";
    private Map<Label, List<Object>> forwardJumpStackFrames;
    private final boolean isConstructor;
    protected int methodAccess;
    protected String methodDesc;
    private List<Object> stackFrame;
    private boolean superClassConstructorCalled;
    private static final Object UNINITIALIZED_THIS = new Object();
    private static final Object OTHER = new Object();

    protected void onMethodEnter() {
    }

    protected void onMethodExit(int i) {
    }

    protected AdviceAdapter(int i, MethodVisitor methodVisitor, int i2, String str, String str2) {
        super(i, methodVisitor, i2, str, str2);
        this.methodAccess = i2;
        this.methodDesc = str2;
        this.isConstructor = "<init>".equals(str);
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitCode() {
        super.visitCode();
        if (this.isConstructor) {
            this.stackFrame = new ArrayList();
            this.forwardJumpStackFrames = new HashMap();
        } else {
            onMethodEnter();
        }
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitLabel(Label label) {
        Map<Label, List<Object>> map;
        List<Object> list;
        super.visitLabel(label);
        if (!this.isConstructor || (map = this.forwardJumpStackFrames) == null || (list = map.get(label)) == null) {
            return;
        }
        this.stackFrame = list;
        this.superClassConstructorCalled = false;
        this.forwardJumpStackFrames.remove(label);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:35:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0136 A[FALL_THROUGH] */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // org.objectweb.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitInsn(int r5) {
        /*
            Method dump skipped, instruction units count: 556
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.commons.AdviceAdapter.visitInsn(int):void");
    }

    @Override // org.objectweb.asm.commons.LocalVariablesSorter, org.objectweb.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        super.visitVarInsn(i, i2);
        if (!this.isConstructor || this.superClassConstructorCalled) {
            return;
        }
        if (i != 169) {
            switch (i) {
                case 21:
                case 23:
                    pushValue(OTHER);
                    return;
                case 22:
                case 24:
                    Object obj = OTHER;
                    pushValue(obj);
                    pushValue(obj);
                    return;
                case 25:
                    pushValue(i2 == 0 ? UNINITIALIZED_THIS : OTHER);
                    return;
                default:
                    switch (i) {
                        case 54:
                        case 56:
                        case 58:
                            popValue();
                            return;
                        case 55:
                        case 57:
                            popValue();
                            popValue();
                            return;
                        default:
                            throw new IllegalArgumentException(INVALID_OPCODE + i);
                    }
            }
        }
        endConstructorBasicBlockWithoutSuccessor();
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        super.visitFieldInsn(i, str, str2, str3);
        if (!this.isConstructor || this.superClassConstructorCalled) {
            return;
        }
        char cCharAt = str3.charAt(0);
        boolean z = cCharAt == 'J' || cCharAt == 'D';
        switch (i) {
            case 178:
                Object obj = OTHER;
                pushValue(obj);
                if (z) {
                    pushValue(obj);
                    return;
                }
                return;
            case 179:
                popValue();
                if (z) {
                    popValue();
                    return;
                }
                return;
            case 180:
                if (z) {
                    pushValue(OTHER);
                    return;
                }
                return;
            case 181:
                popValue();
                popValue();
                if (z) {
                    popValue();
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException(INVALID_OPCODE + i);
        }
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        super.visitIntInsn(i, i2);
        if (!this.isConstructor || this.superClassConstructorCalled || i == 188) {
            return;
        }
        pushValue(OTHER);
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        super.visitLdcInsn(obj);
        if (!this.isConstructor || this.superClassConstructorCalled) {
            return;
        }
        Object obj2 = OTHER;
        pushValue(obj2);
        if ((obj instanceof Double) || (obj instanceof Long) || ((obj instanceof ConstantDynamic) && ((ConstantDynamic) obj).getSize() == 2)) {
            pushValue(obj2);
        }
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        super.visitMultiANewArrayInsn(str, i);
        if (!this.isConstructor || this.superClassConstructorCalled) {
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            popValue();
        }
        pushValue(OTHER);
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        super.visitTypeInsn(i, str);
        if (this.isConstructor && !this.superClassConstructorCalled && i == 187) {
            pushValue(OTHER);
        }
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        if (this.api < 327680 && (i & 256) == 0) {
            super.visitMethodInsn(i, str, str2, str3, z);
        } else {
            super.visitMethodInsn(i, str, str2, str3, z);
            doVisitMethodInsn(i & (-257), str2, str3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doVisitMethodInsn(int r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            boolean r0 = r5.isConstructor
            if (r0 == 0) goto L63
            boolean r0 = r5.superClassConstructorCalled
            if (r0 != 0) goto L63
            org.objectweb.asm.Type[] r0 = org.objectweb.asm.Type.getArgumentTypes(r8)
            int r1 = r0.length
            r2 = 0
        Le:
            r3 = 2
            if (r2 >= r1) goto L22
            r4 = r0[r2]
            r5.popValue()
            int r4 = r4.getSize()
            if (r4 != r3) goto L1f
            r5.popValue()
        L1f:
            int r2 = r2 + 1
            goto Le
        L22:
            r0 = 182(0xb6, float:2.55E-43)
            if (r6 == r0) goto L4a
            r0 = 183(0xb7, float:2.56E-43)
            if (r6 == r0) goto L2f
            r7 = 185(0xb9, float:2.59E-43)
            if (r6 == r7) goto L4a
            goto L4d
        L2f:
            java.lang.Object r6 = r5.popValue()
            java.lang.Object r0 = org.objectweb.asm.commons.AdviceAdapter.UNINITIALIZED_THIS
            if (r6 != r0) goto L4d
            boolean r6 = r5.superClassConstructorCalled
            if (r6 != 0) goto L4d
            java.lang.String r6 = "<init>"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L4d
            r6 = 1
            r5.superClassConstructorCalled = r6
            r5.onMethodEnter()
            goto L4d
        L4a:
            r5.popValue()
        L4d:
            org.objectweb.asm.Type r6 = org.objectweb.asm.Type.getReturnType(r8)
            org.objectweb.asm.Type r7 = org.objectweb.asm.Type.VOID_TYPE
            if (r6 == r7) goto L63
            java.lang.Object r7 = org.objectweb.asm.commons.AdviceAdapter.OTHER
            r5.pushValue(r7)
            int r6 = r6.getSize()
            if (r6 != r3) goto L63
            r5.pushValue(r7)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.commons.AdviceAdapter.doVisitMethodInsn(int, java.lang.String, java.lang.String):void");
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        super.visitInvokeDynamicInsn(str, str2, handle, objArr);
        doVisitMethodInsn(186, str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    @Override // org.objectweb.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitJumpInsn(int r2, org.objectweb.asm.Label r3) {
        /*
            r1 = this;
            super.visitJumpInsn(r2, r3)
            boolean r0 = r1.isConstructor
            if (r0 == 0) goto L2e
            boolean r0 = r1.superClassConstructorCalled
            if (r0 != 0) goto L2e
            r0 = 198(0xc6, float:2.77E-43)
            if (r2 == r0) goto L28
            r0 = 199(0xc7, float:2.79E-43)
            if (r2 == r0) goto L28
            switch(r2) {
                case 153: goto L28;
                case 154: goto L28;
                case 155: goto L28;
                case 156: goto L28;
                case 157: goto L28;
                case 158: goto L28;
                case 159: goto L21;
                case 160: goto L21;
                case 161: goto L21;
                case 162: goto L21;
                case 163: goto L21;
                case 164: goto L21;
                case 165: goto L21;
                case 166: goto L21;
                case 167: goto L1d;
                case 168: goto L17;
                default: goto L16;
            }
        L16:
            goto L2b
        L17:
            java.lang.Object r2 = org.objectweb.asm.commons.AdviceAdapter.OTHER
            r1.pushValue(r2)
            goto L2b
        L1d:
            r1.endConstructorBasicBlockWithoutSuccessor()
            goto L2b
        L21:
            r1.popValue()
            r1.popValue()
            goto L2b
        L28:
            r1.popValue()
        L2b:
            r1.addForwardJump(r3)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.commons.AdviceAdapter.visitJumpInsn(int, org.objectweb.asm.Label):void");
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        super.visitLookupSwitchInsn(label, iArr, labelArr);
        if (!this.isConstructor || this.superClassConstructorCalled) {
            return;
        }
        popValue();
        addForwardJumps(label, labelArr);
        endConstructorBasicBlockWithoutSuccessor();
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        super.visitTableSwitchInsn(i, i2, label, labelArr);
        if (!this.isConstructor || this.superClassConstructorCalled) {
            return;
        }
        popValue();
        addForwardJumps(label, labelArr);
        endConstructorBasicBlockWithoutSuccessor();
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        super.visitTryCatchBlock(label, label2, label3, str);
        if (!this.isConstructor || this.forwardJumpStackFrames.containsKey(label3)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(OTHER);
        this.forwardJumpStackFrames.put(label3, arrayList);
    }

    private void addForwardJumps(Label label, Label[] labelArr) {
        addForwardJump(label);
        for (Label label2 : labelArr) {
            addForwardJump(label2);
        }
    }

    private void addForwardJump(Label label) {
        if (this.forwardJumpStackFrames.containsKey(label)) {
            return;
        }
        this.forwardJumpStackFrames.put(label, new ArrayList(this.stackFrame));
    }

    private void endConstructorBasicBlockWithoutSuccessor() {
        this.superClassConstructorCalled = true;
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

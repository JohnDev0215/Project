package net.bytebuddy.jar.asm.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;

/* JADX INFO: loaded from: classes3.dex */
public class AnalyzerAdapter extends MethodVisitor {
    private List<Label> labels;
    public List<Object> locals;
    private int maxLocals;
    private int maxStack;
    private String owner;
    public List<Object> stack;
    public Map<Object, Object> uninitializedTypes;

    public AnalyzerAdapter(String str, int i, String str2, String str3, MethodVisitor methodVisitor) {
        this(Opcodes.ASM6, str, i, str2, str3, methodVisitor);
        if (getClass() != AnalyzerAdapter.class) {
            throw new IllegalStateException();
        }
    }

    protected AnalyzerAdapter(int i, String str, int i2, String str2, String str3, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
        this.owner = str;
        this.locals = new ArrayList();
        this.stack = new ArrayList();
        this.uninitializedTypes = new HashMap();
        if ((i2 & 8) == 0) {
            if (MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(str2)) {
                this.locals.add(Opcodes.UNINITIALIZED_THIS);
            } else {
                this.locals.add(str);
            }
        }
        Type[] argumentTypes = Type.getArgumentTypes(str3);
        for (int i3 = 0; i3 < argumentTypes.length; i3++) {
            switch (argumentTypes[i3].getSort()) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    this.locals.add(Opcodes.INTEGER);
                    break;
                case 6:
                    this.locals.add(Opcodes.FLOAT);
                    break;
                case 7:
                    this.locals.add(Opcodes.LONG);
                    this.locals.add(Opcodes.TOP);
                    break;
                case 8:
                    this.locals.add(Opcodes.DOUBLE);
                    this.locals.add(Opcodes.TOP);
                    break;
                case 9:
                    this.locals.add(argumentTypes[i3].getDescriptor());
                    break;
                default:
                    this.locals.add(argumentTypes[i3].getInternalName());
                    break;
            }
        }
        this.maxLocals = this.locals.size();
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        if (i != -1) {
            throw new IllegalStateException("ClassReader.accept() should be called with EXPAND_FRAMES flag");
        }
        if (this.mv != null) {
            this.mv.visitFrame(i, i2, objArr, i3, objArr2);
        }
        List<Object> list = this.locals;
        if (list != null) {
            list.clear();
            this.stack.clear();
        } else {
            this.locals = new ArrayList();
            this.stack = new ArrayList();
        }
        visitFrameTypes(i2, objArr, this.locals);
        visitFrameTypes(i3, objArr2, this.stack);
        this.maxStack = Math.max(this.maxStack, this.stack.size());
    }

    private static void visitFrameTypes(int i, Object[] objArr, List<Object> list) {
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = objArr[i2];
            list.add(obj);
            if (obj == Opcodes.LONG || obj == Opcodes.DOUBLE) {
                list.add(Opcodes.TOP);
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInsn(int i) {
        if (this.mv != null) {
            this.mv.visitInsn(i);
        }
        execute(i, 0, null);
        if ((i < 172 || i > 177) && i != 191) {
            return;
        }
        this.locals = null;
        this.stack = null;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        if (this.mv != null) {
            this.mv.visitIntInsn(i, i2);
        }
        execute(i, i2, null);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        if (this.mv != null) {
            this.mv.visitVarInsn(i, i2);
        }
        execute(i, i2, null);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        if (i == 187) {
            if (this.labels == null) {
                Label label = new Label();
                ArrayList arrayList = new ArrayList(3);
                this.labels = arrayList;
                arrayList.add(label);
                if (this.mv != null) {
                    this.mv.visitLabel(label);
                }
            }
            for (int i2 = 0; i2 < this.labels.size(); i2++) {
                this.uninitializedTypes.put(this.labels.get(i2), str);
            }
        }
        if (this.mv != null) {
            this.mv.visitTypeInsn(i, str);
        }
        execute(i, 0, str);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        if (this.mv != null) {
            this.mv.visitFieldInsn(i, str, str2, str3);
        }
        execute(i, 0, str3);
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

    private void doVisitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        Object obj;
        if (this.mv != null) {
            this.mv.visitMethodInsn(i, str, str2, str3, z);
        }
        if (this.locals == null) {
            this.labels = null;
            return;
        }
        pop(str3);
        if (i != 184) {
            Object objPop = pop();
            if (i == 183) {
                if (str2.charAt(0) == '<') {
                    if (objPop == Opcodes.UNINITIALIZED_THIS) {
                        obj = this.owner;
                    } else {
                        obj = this.uninitializedTypes.get(objPop);
                    }
                    for (int i2 = 0; i2 < this.locals.size(); i2++) {
                        if (this.locals.get(i2) == objPop) {
                            this.locals.set(i2, obj);
                        }
                    }
                    for (int i3 = 0; i3 < this.stack.size(); i3++) {
                        if (this.stack.get(i3) == objPop) {
                            this.stack.set(i3, obj);
                        }
                    }
                }
            }
        }
        pushDesc(str3);
        this.labels = null;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        if (this.mv != null) {
            this.mv.visitInvokeDynamicInsn(str, str2, handle, objArr);
        }
        if (this.locals == null) {
            this.labels = null;
            return;
        }
        pop(str2);
        pushDesc(str2);
        this.labels = null;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitJumpInsn(int i, Label label) {
        if (this.mv != null) {
            this.mv.visitJumpInsn(i, label);
        }
        execute(i, 0, null);
        if (i == 167) {
            this.locals = null;
            this.stack = null;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLabel(Label label) {
        if (this.mv != null) {
            this.mv.visitLabel(label);
        }
        if (this.labels == null) {
            this.labels = new ArrayList(3);
        }
        this.labels.add(label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        if (this.mv != null) {
            this.mv.visitLdcInsn(obj);
        }
        if (this.locals == null) {
            this.labels = null;
            return;
        }
        if (obj instanceof Integer) {
            push(Opcodes.INTEGER);
        } else if (obj instanceof Long) {
            push(Opcodes.LONG);
            push(Opcodes.TOP);
        } else if (obj instanceof Float) {
            push(Opcodes.FLOAT);
        } else if (obj instanceof Double) {
            push(Opcodes.DOUBLE);
            push(Opcodes.TOP);
        } else if (obj instanceof String) {
            push("java/lang/String");
        } else if (obj instanceof Type) {
            int sort = ((Type) obj).getSort();
            if (sort == 10 || sort == 9) {
                push(TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME);
            } else if (sort == 11) {
                push("java/lang/invoke/MethodType");
            } else {
                throw new IllegalArgumentException();
            }
        } else if (obj instanceof Handle) {
            push("java/lang/invoke/MethodHandle");
        } else {
            throw new IllegalArgumentException();
        }
        this.labels = null;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIincInsn(int i, int i2) {
        if (this.mv != null) {
            this.mv.visitIincInsn(i, i2);
        }
        execute(132, i, null);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(i, i2, label, labelArr);
        }
        execute(170, 0, null);
        this.locals = null;
        this.stack = null;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(label, iArr, labelArr);
        }
        execute(171, 0, null);
        this.locals = null;
        this.stack = null;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(str, i);
        }
        execute(197, i, str);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMaxs(int i, int i2) {
        if (this.mv != null) {
            this.maxStack = Math.max(this.maxStack, i);
            this.maxLocals = Math.max(this.maxLocals, i2);
            this.mv.visitMaxs(this.maxStack, this.maxLocals);
        }
    }

    private Object get(int i) {
        this.maxLocals = Math.max(this.maxLocals, i + 1);
        return i < this.locals.size() ? this.locals.get(i) : Opcodes.TOP;
    }

    private void set(int i, Object obj) {
        this.maxLocals = Math.max(this.maxLocals, i + 1);
        while (i >= this.locals.size()) {
            this.locals.add(Opcodes.TOP);
        }
        this.locals.set(i, obj);
    }

    private void push(Object obj) {
        this.stack.add(obj);
        this.maxStack = Math.max(this.maxStack, this.stack.size());
    }

    private void pushDesc(String str) {
        int iIndexOf = str.charAt(0) == '(' ? str.indexOf(41) + 1 : 0;
        char cCharAt = str.charAt(iIndexOf);
        if (cCharAt != 'F') {
            if (cCharAt != 'S') {
                if (cCharAt == 'V') {
                    return;
                }
                if (cCharAt != 'I') {
                    if (cCharAt == 'J') {
                        push(Opcodes.LONG);
                        push(Opcodes.TOP);
                        return;
                    }
                    if (cCharAt != 'Z') {
                        if (cCharAt == '[') {
                            if (iIndexOf == 0) {
                                push(str);
                                return;
                            } else {
                                push(str.substring(iIndexOf, str.length()));
                                return;
                            }
                        }
                        switch (cCharAt) {
                            case 'B':
                            case 'C':
                                break;
                            case 'D':
                                push(Opcodes.DOUBLE);
                                push(Opcodes.TOP);
                                break;
                            default:
                                if (iIndexOf == 0) {
                                    push(str.substring(1, str.length() - 1));
                                } else {
                                    push(str.substring(iIndexOf + 1, str.length() - 1));
                                }
                                break;
                        }
                        return;
                    }
                }
            }
            push(Opcodes.INTEGER);
            return;
        }
        push(Opcodes.FLOAT);
    }

    private Object pop() {
        return this.stack.remove(r0.size() - 1);
    }

    private void pop(int i) {
        int size = this.stack.size();
        int i2 = size - i;
        for (int i3 = size - 1; i3 >= i2; i3--) {
            this.stack.remove(i3);
        }
    }

    private void pop(String str) {
        char cCharAt = str.charAt(0);
        if (cCharAt != '(') {
            if (cCharAt == 'J' || cCharAt == 'D') {
                pop(2);
                return;
            } else {
                pop(1);
                return;
            }
        }
        int size = 0;
        for (Type type : Type.getArgumentTypes(str)) {
            size += type.getSize();
        }
        pop(size);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void execute(int r6, int r7, java.lang.String r8) {
        /*
            Method dump skipped, instruction units count: 1054
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.commons.AnalyzerAdapter.execute(int, int, java.lang.String):void");
    }
}

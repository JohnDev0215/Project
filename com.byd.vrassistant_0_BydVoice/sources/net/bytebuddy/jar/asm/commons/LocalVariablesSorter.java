package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;

/* JADX INFO: loaded from: classes3.dex */
public class LocalVariablesSorter extends MethodVisitor {
    private static final Type OBJECT_TYPE = Type.getObjectType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
    protected final int firstLocal;
    private int[] mapping;
    private Object[] newLocals;
    protected int nextLocal;

    protected void setLocalType(int i, Type type) {
    }

    protected void updateNewLocals(Object[] objArr) {
    }

    public LocalVariablesSorter(int i, String str, MethodVisitor methodVisitor) {
        this(Opcodes.ASM6, i, str, methodVisitor);
        if (getClass() != LocalVariablesSorter.class) {
            throw new IllegalStateException();
        }
    }

    protected LocalVariablesSorter(int i, int i2, String str, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
        this.mapping = new int[40];
        this.newLocals = new Object[20];
        Type[] argumentTypes = Type.getArgumentTypes(str);
        this.nextLocal = (i2 & 8) == 0 ? 1 : 0;
        for (Type type : argumentTypes) {
            this.nextLocal += type.getSize();
        }
        this.firstLocal = this.nextLocal;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0009  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x000c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0012  */
    @Override // net.bytebuddy.jar.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitVarInsn(int r3, int r4) {
        /*
            r2 = this;
            switch(r3) {
                case 21: goto L12;
                case 22: goto Lf;
                case 23: goto Lc;
                case 24: goto L9;
                default: goto L3;
            }
        L3:
            switch(r3) {
                case 54: goto L12;
                case 55: goto Lf;
                case 56: goto Lc;
                case 57: goto L9;
                default: goto L6;
            }
        L6:
            net.bytebuddy.jar.asm.Type r0 = net.bytebuddy.jar.asm.commons.LocalVariablesSorter.OBJECT_TYPE
            goto L14
        L9:
            net.bytebuddy.jar.asm.Type r0 = net.bytebuddy.jar.asm.Type.DOUBLE_TYPE
            goto L14
        Lc:
            net.bytebuddy.jar.asm.Type r0 = net.bytebuddy.jar.asm.Type.FLOAT_TYPE
            goto L14
        Lf:
            net.bytebuddy.jar.asm.Type r0 = net.bytebuddy.jar.asm.Type.LONG_TYPE
            goto L14
        L12:
            net.bytebuddy.jar.asm.Type r0 = net.bytebuddy.jar.asm.Type.INT_TYPE
        L14:
            net.bytebuddy.jar.asm.MethodVisitor r1 = r2.mv
            int r4 = r2.remap(r4, r0)
            r1.visitVarInsn(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.commons.LocalVariablesSorter.visitVarInsn(int, int):void");
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIincInsn(int i, int i2) {
        this.mv.visitIincInsn(remap(i, Type.INT_TYPE), i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMaxs(int i, int i2) {
        this.mv.visitMaxs(i, this.nextLocal);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        this.mv.visitLocalVariable(str, str2, str3, label, label2, remap(i, Type.getType(str2)));
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
        Type type = Type.getType(str);
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr2[i2] = remap(iArr[i2], type);
        }
        return this.mv.visitLocalVariableAnnotation(i, typePath, labelArr, labelArr2, iArr2, str, z);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        if (i != -1) {
            throw new IllegalStateException("ClassReader.accept() should be called with EXPAND_FRAMES flag");
        }
        Object[] objArr3 = this.newLocals;
        int length = objArr3.length;
        Object[] objArr4 = new Object[length];
        int i4 = 0;
        System.arraycopy(objArr3, 0, objArr4, 0, length);
        updateNewLocals(this.newLocals);
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            Object obj = objArr[i6];
            int i7 = (obj == Opcodes.LONG || obj == Opcodes.DOUBLE) ? 2 : 1;
            if (obj != Opcodes.TOP) {
                Type objectType = OBJECT_TYPE;
                if (obj == Opcodes.INTEGER) {
                    objectType = Type.INT_TYPE;
                } else if (obj == Opcodes.FLOAT) {
                    objectType = Type.FLOAT_TYPE;
                } else if (obj == Opcodes.LONG) {
                    objectType = Type.LONG_TYPE;
                } else if (obj == Opcodes.DOUBLE) {
                    objectType = Type.DOUBLE_TYPE;
                } else if (obj instanceof String) {
                    objectType = Type.getObjectType((String) obj);
                }
                setFrameLocal(remap(i5, objectType), obj);
            }
            i5 += i7;
        }
        int i8 = 0;
        int i9 = 0;
        while (true) {
            Object[] objArr5 = this.newLocals;
            if (i4 < objArr5.length) {
                int i10 = i4 + 1;
                Object obj2 = objArr5[i4];
                if (obj2 != null && obj2 != Opcodes.TOP) {
                    this.newLocals[i8] = obj2;
                    int i11 = i8 + 1;
                    if (obj2 == Opcodes.LONG || obj2 == Opcodes.DOUBLE) {
                        i10++;
                    }
                    i4 = i10;
                    i9 = i11;
                } else {
                    this.newLocals[i8] = Opcodes.TOP;
                    i4 = i10;
                }
                i8++;
            } else {
                this.mv.visitFrame(i, i9, this.newLocals, i3, objArr2);
                this.newLocals = objArr4;
                return;
            }
        }
    }

    public int newLocal(Type type) {
        Object descriptor;
        switch (type.getSort()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                descriptor = Opcodes.INTEGER;
                break;
            case 6:
                descriptor = Opcodes.FLOAT;
                break;
            case 7:
                descriptor = Opcodes.LONG;
                break;
            case 8:
                descriptor = Opcodes.DOUBLE;
                break;
            case 9:
                descriptor = type.getDescriptor();
                break;
            default:
                descriptor = type.getInternalName();
                break;
        }
        int iNewLocalMapping = newLocalMapping(type);
        setLocalType(iNewLocalMapping, type);
        setFrameLocal(iNewLocalMapping, descriptor);
        return iNewLocalMapping;
    }

    private void setFrameLocal(int i, Object obj) {
        int length = this.newLocals.length;
        if (i >= length) {
            Object[] objArr = new Object[Math.max(length * 2, i + 1)];
            System.arraycopy(this.newLocals, 0, objArr, 0, length);
            this.newLocals = objArr;
        }
        this.newLocals[i] = obj;
    }

    private int remap(int i, Type type) {
        if (type.getSize() + i <= this.firstLocal) {
            return i;
        }
        int size = ((i * 2) + type.getSize()) - 1;
        int length = this.mapping.length;
        if (size >= length) {
            int[] iArr = new int[Math.max(length * 2, size + 1)];
            System.arraycopy(this.mapping, 0, iArr, 0, length);
            this.mapping = iArr;
        }
        int i2 = this.mapping[size];
        if (i2 != 0) {
            return i2 - 1;
        }
        int iNewLocalMapping = newLocalMapping(type);
        setLocalType(iNewLocalMapping, type);
        this.mapping[size] = iNewLocalMapping + 1;
        return iNewLocalMapping;
    }

    protected int newLocalMapping(Type type) {
        int i = this.nextLocal;
        this.nextLocal = type.getSize() + i;
        return i;
    }
}

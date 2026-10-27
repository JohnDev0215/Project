package org.objectweb.asm.commons;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;

/* JADX INFO: loaded from: classes6.dex */
public class LocalVariablesSorter extends MethodVisitor {
    private static final Type OBJECT_TYPE = Type.getObjectType("java/lang/Object");
    protected final int firstLocal;
    protected int nextLocal;
    private Object[] remappedLocalTypes;
    private int[] remappedVariableIndices;

    protected void setLocalType(int i, Type type) {
    }

    protected void updateNewLocals(Object[] objArr) {
    }

    public LocalVariablesSorter(int i, String str, MethodVisitor methodVisitor) {
        this(589824, i, str, methodVisitor);
        if (getClass() != LocalVariablesSorter.class) {
            throw new IllegalStateException();
        }
    }

    protected LocalVariablesSorter(int i, int i2, String str, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
        this.remappedVariableIndices = new int[40];
        this.remappedLocalTypes = new Object[20];
        int i3 = i2 & 8;
        this.nextLocal = i3 == 0 ? 1 : 0;
        for (Type type : Type.getArgumentTypes(str)) {
            this.nextLocal += type.getSize();
        }
        this.firstLocal = this.nextLocal;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    @Override // org.objectweb.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitVarInsn(int r3, int r4) {
        /*
            r2 = this;
            r0 = 169(0xa9, float:2.37E-43)
            if (r3 == r0) goto L2d
            switch(r3) {
                case 21: goto L2a;
                case 22: goto L27;
                case 23: goto L24;
                case 24: goto L21;
                case 25: goto L2d;
                default: goto L7;
            }
        L7:
            switch(r3) {
                case 54: goto L2a;
                case 55: goto L27;
                case 56: goto L24;
                case 57: goto L21;
                case 58: goto L2d;
                default: goto La;
            }
        La:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid opcode "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r4.<init>(r3)
            throw r4
        L21:
            org.objectweb.asm.Type r0 = org.objectweb.asm.Type.DOUBLE_TYPE
            goto L2f
        L24:
            org.objectweb.asm.Type r0 = org.objectweb.asm.Type.FLOAT_TYPE
            goto L2f
        L27:
            org.objectweb.asm.Type r0 = org.objectweb.asm.Type.LONG_TYPE
            goto L2f
        L2a:
            org.objectweb.asm.Type r0 = org.objectweb.asm.Type.INT_TYPE
            goto L2f
        L2d:
            org.objectweb.asm.Type r0 = org.objectweb.asm.commons.LocalVariablesSorter.OBJECT_TYPE
        L2f:
            int r4 = r2.remap(r4, r0)
            super.visitVarInsn(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.commons.LocalVariablesSorter.visitVarInsn(int, int):void");
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitIincInsn(int i, int i2) {
        super.visitIincInsn(remap(i, Type.INT_TYPE), i2);
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitMaxs(int i, int i2) {
        super.visitMaxs(i, this.nextLocal);
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        super.visitLocalVariable(str, str2, str3, label, label2, remap(i, Type.getType(str2)));
    }

    @Override // org.objectweb.asm.MethodVisitor
    public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
        Type type = Type.getType(str);
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr2[i2] = remap(iArr[i2], type);
        }
        return super.visitLocalVariableAnnotation(i, typePath, labelArr, labelArr2, iArr2, str, z);
    }

    @Override // org.objectweb.asm.MethodVisitor
    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        if (i != -1) {
            throw new IllegalArgumentException("LocalVariablesSorter only accepts expanded frames (see ClassReader.EXPAND_FRAMES)");
        }
        Object[] objArr3 = this.remappedLocalTypes;
        int length = objArr3.length;
        Object[] objArr4 = new Object[length];
        int i4 = 0;
        System.arraycopy(objArr3, 0, objArr4, 0, length);
        updateNewLocals(this.remappedLocalTypes);
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = 2;
            if (i5 >= i2) {
                break;
            }
            Object obj = objArr[i5];
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
                setFrameLocal(remap(i6, objectType), obj);
            }
            if (obj != Opcodes.LONG && obj != Opcodes.DOUBLE) {
                i7 = 1;
            }
            i6 += i7;
            i5++;
        }
        int i8 = 0;
        int i9 = 0;
        while (true) {
            Object[] objArr5 = this.remappedLocalTypes;
            if (i4 < objArr5.length) {
                Object obj2 = objArr5[i4];
                i4 += (obj2 == Opcodes.LONG || obj2 == Opcodes.DOUBLE) ? 2 : 1;
                if (obj2 != null && obj2 != Opcodes.TOP) {
                    i9 = i8 + 1;
                    objArr5[i8] = obj2;
                    i8 = i9;
                } else {
                    objArr5[i8] = Opcodes.TOP;
                    i8++;
                }
            } else {
                super.visitFrame(i, i9, objArr5, i3, objArr2);
                this.remappedLocalTypes = objArr4;
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
            case 10:
                descriptor = type.getInternalName();
                break;
            default:
                throw new AssertionError();
        }
        int iNewLocalMapping = newLocalMapping(type);
        setLocalType(iNewLocalMapping, type);
        setFrameLocal(iNewLocalMapping, descriptor);
        return iNewLocalMapping;
    }

    private void setFrameLocal(int i, Object obj) {
        int length = this.remappedLocalTypes.length;
        if (i >= length) {
            Object[] objArr = new Object[Math.max(length * 2, i + 1)];
            System.arraycopy(this.remappedLocalTypes, 0, objArr, 0, length);
            this.remappedLocalTypes = objArr;
        }
        this.remappedLocalTypes[i] = obj;
    }

    private int remap(int i, Type type) {
        if (type.getSize() + i <= this.firstLocal) {
            return i;
        }
        int size = ((i * 2) + type.getSize()) - 1;
        int length = this.remappedVariableIndices.length;
        if (size >= length) {
            int[] iArr = new int[Math.max(length * 2, size + 1)];
            System.arraycopy(this.remappedVariableIndices, 0, iArr, 0, length);
            this.remappedVariableIndices = iArr;
        }
        int i2 = this.remappedVariableIndices[size];
        if (i2 != 0) {
            return i2 - 1;
        }
        int iNewLocalMapping = newLocalMapping(type);
        setLocalType(iNewLocalMapping, type);
        this.remappedVariableIndices[size] = iNewLocalMapping + 1;
        return iNewLocalMapping;
    }

    protected int newLocalMapping(Type type) {
        int i = this.nextLocal;
        this.nextLocal = type.getSize() + i;
        return i;
    }
}

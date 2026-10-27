package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;

/* JADX INFO: loaded from: classes3.dex */
public class InstructionAdapter extends MethodVisitor {
    public static final Type OBJECT_TYPE = Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR);

    public InstructionAdapter(MethodVisitor methodVisitor) {
        this(Opcodes.ASM6, methodVisitor);
        if (getClass() != InstructionAdapter.class) {
            throw new IllegalStateException();
        }
    }

    protected InstructionAdapter(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInsn(int i) {
        if (i == 190) {
            arraylength();
            return;
        }
        if (i == 191) {
            athrow();
            return;
        }
        if (i == 194) {
            monitorenter();
            return;
        }
        if (i != 195) {
            switch (i) {
                case 0:
                    nop();
                    return;
                case 1:
                    aconst(null);
                    return;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    iconst(i - 3);
                    return;
                case 9:
                case 10:
                    lconst(i - 9);
                    return;
                case 11:
                case 12:
                case 13:
                    fconst(i - 11);
                    return;
                case 14:
                case 15:
                    dconst(i - 14);
                    return;
                default:
                    switch (i) {
                        case 46:
                            aload(Type.INT_TYPE);
                            return;
                        case 47:
                            aload(Type.LONG_TYPE);
                            return;
                        case 48:
                            aload(Type.FLOAT_TYPE);
                            return;
                        case 49:
                            aload(Type.DOUBLE_TYPE);
                            return;
                        case 50:
                            aload(OBJECT_TYPE);
                            return;
                        case 51:
                            aload(Type.BYTE_TYPE);
                            return;
                        case 52:
                            aload(Type.CHAR_TYPE);
                            return;
                        case 53:
                            aload(Type.SHORT_TYPE);
                            return;
                        default:
                            switch (i) {
                                case 79:
                                    astore(Type.INT_TYPE);
                                    return;
                                case 80:
                                    astore(Type.LONG_TYPE);
                                    return;
                                case 81:
                                    astore(Type.FLOAT_TYPE);
                                    return;
                                case 82:
                                    astore(Type.DOUBLE_TYPE);
                                    return;
                                case 83:
                                    astore(OBJECT_TYPE);
                                    return;
                                case 84:
                                    astore(Type.BYTE_TYPE);
                                    return;
                                case 85:
                                    astore(Type.CHAR_TYPE);
                                    return;
                                case 86:
                                    astore(Type.SHORT_TYPE);
                                    return;
                                case 87:
                                    pop();
                                    return;
                                case 88:
                                    pop2();
                                    return;
                                case 89:
                                    dup();
                                    return;
                                case 90:
                                    dupX1();
                                    return;
                                case 91:
                                    dupX2();
                                    return;
                                case 92:
                                    dup2();
                                    return;
                                case 93:
                                    dup2X1();
                                    return;
                                case 94:
                                    dup2X2();
                                    return;
                                case 95:
                                    swap();
                                    return;
                                case 96:
                                    add(Type.INT_TYPE);
                                    return;
                                case 97:
                                    add(Type.LONG_TYPE);
                                    return;
                                case 98:
                                    add(Type.FLOAT_TYPE);
                                    return;
                                case 99:
                                    add(Type.DOUBLE_TYPE);
                                    return;
                                case 100:
                                    sub(Type.INT_TYPE);
                                    return;
                                case 101:
                                    sub(Type.LONG_TYPE);
                                    return;
                                case 102:
                                    sub(Type.FLOAT_TYPE);
                                    return;
                                case 103:
                                    sub(Type.DOUBLE_TYPE);
                                    return;
                                case 104:
                                    mul(Type.INT_TYPE);
                                    return;
                                case 105:
                                    mul(Type.LONG_TYPE);
                                    return;
                                case 106:
                                    mul(Type.FLOAT_TYPE);
                                    return;
                                case 107:
                                    mul(Type.DOUBLE_TYPE);
                                    return;
                                case 108:
                                    div(Type.INT_TYPE);
                                    return;
                                case 109:
                                    div(Type.LONG_TYPE);
                                    return;
                                case 110:
                                    div(Type.FLOAT_TYPE);
                                    return;
                                case 111:
                                    div(Type.DOUBLE_TYPE);
                                    return;
                                case 112:
                                    rem(Type.INT_TYPE);
                                    return;
                                case 113:
                                    rem(Type.LONG_TYPE);
                                    return;
                                case 114:
                                    rem(Type.FLOAT_TYPE);
                                    return;
                                case 115:
                                    rem(Type.DOUBLE_TYPE);
                                    return;
                                case 116:
                                    neg(Type.INT_TYPE);
                                    return;
                                case 117:
                                    neg(Type.LONG_TYPE);
                                    return;
                                case 118:
                                    neg(Type.FLOAT_TYPE);
                                    return;
                                case 119:
                                    neg(Type.DOUBLE_TYPE);
                                    return;
                                case 120:
                                    shl(Type.INT_TYPE);
                                    return;
                                case 121:
                                    shl(Type.LONG_TYPE);
                                    return;
                                case 122:
                                    shr(Type.INT_TYPE);
                                    return;
                                case 123:
                                    shr(Type.LONG_TYPE);
                                    return;
                                case 124:
                                    ushr(Type.INT_TYPE);
                                    return;
                                case 125:
                                    ushr(Type.LONG_TYPE);
                                    return;
                                case 126:
                                    and(Type.INT_TYPE);
                                    return;
                                case 127:
                                    and(Type.LONG_TYPE);
                                    return;
                                case 128:
                                    or(Type.INT_TYPE);
                                    return;
                                case 129:
                                    or(Type.LONG_TYPE);
                                    return;
                                case 130:
                                    xor(Type.INT_TYPE);
                                    return;
                                case 131:
                                    xor(Type.LONG_TYPE);
                                    return;
                                default:
                                    switch (i) {
                                        case 133:
                                            cast(Type.INT_TYPE, Type.LONG_TYPE);
                                            return;
                                        case 134:
                                            cast(Type.INT_TYPE, Type.FLOAT_TYPE);
                                            return;
                                        case 135:
                                            cast(Type.INT_TYPE, Type.DOUBLE_TYPE);
                                            return;
                                        case 136:
                                            cast(Type.LONG_TYPE, Type.INT_TYPE);
                                            return;
                                        case 137:
                                            cast(Type.LONG_TYPE, Type.FLOAT_TYPE);
                                            return;
                                        case 138:
                                            cast(Type.LONG_TYPE, Type.DOUBLE_TYPE);
                                            return;
                                        case 139:
                                            cast(Type.FLOAT_TYPE, Type.INT_TYPE);
                                            return;
                                        case 140:
                                            cast(Type.FLOAT_TYPE, Type.LONG_TYPE);
                                            return;
                                        case 141:
                                            cast(Type.FLOAT_TYPE, Type.DOUBLE_TYPE);
                                            return;
                                        case 142:
                                            cast(Type.DOUBLE_TYPE, Type.INT_TYPE);
                                            return;
                                        case 143:
                                            cast(Type.DOUBLE_TYPE, Type.LONG_TYPE);
                                            return;
                                        case 144:
                                            cast(Type.DOUBLE_TYPE, Type.FLOAT_TYPE);
                                            return;
                                        case 145:
                                            cast(Type.INT_TYPE, Type.BYTE_TYPE);
                                            return;
                                        case 146:
                                            cast(Type.INT_TYPE, Type.CHAR_TYPE);
                                            return;
                                        case 147:
                                            cast(Type.INT_TYPE, Type.SHORT_TYPE);
                                            return;
                                        case 148:
                                            lcmp();
                                            return;
                                        case 149:
                                            cmpl(Type.FLOAT_TYPE);
                                            return;
                                        case 150:
                                            cmpg(Type.FLOAT_TYPE);
                                            return;
                                        case 151:
                                            cmpl(Type.DOUBLE_TYPE);
                                            return;
                                        case 152:
                                            cmpg(Type.DOUBLE_TYPE);
                                            return;
                                        default:
                                            switch (i) {
                                                case 172:
                                                    areturn(Type.INT_TYPE);
                                                    return;
                                                case 173:
                                                    areturn(Type.LONG_TYPE);
                                                    return;
                                                case 174:
                                                    areturn(Type.FLOAT_TYPE);
                                                    return;
                                                case 175:
                                                    areturn(Type.DOUBLE_TYPE);
                                                    return;
                                                case 176:
                                                    areturn(OBJECT_TYPE);
                                                    return;
                                                case 177:
                                                    areturn(Type.VOID_TYPE);
                                                    return;
                                                default:
                                                    throw new IllegalArgumentException();
                                            }
                                    }
                            }
                    }
            }
        }
        monitorexit();
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        if (i == 16) {
            iconst(i2);
            return;
        }
        if (i == 17) {
            iconst(i2);
            return;
        }
        if (i == 188) {
            switch (i2) {
                case 4:
                    newarray(Type.BOOLEAN_TYPE);
                    return;
                case 5:
                    newarray(Type.CHAR_TYPE);
                    return;
                case 6:
                    newarray(Type.FLOAT_TYPE);
                    return;
                case 7:
                    newarray(Type.DOUBLE_TYPE);
                    return;
                case 8:
                    newarray(Type.BYTE_TYPE);
                    return;
                case 9:
                    newarray(Type.SHORT_TYPE);
                    return;
                case 10:
                    newarray(Type.INT_TYPE);
                    return;
                case 11:
                    newarray(Type.LONG_TYPE);
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        }
        throw new IllegalArgumentException();
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        if (i != 169) {
            switch (i) {
                case 21:
                    load(i2, Type.INT_TYPE);
                    return;
                case 22:
                    load(i2, Type.LONG_TYPE);
                    return;
                case 23:
                    load(i2, Type.FLOAT_TYPE);
                    return;
                case 24:
                    load(i2, Type.DOUBLE_TYPE);
                    return;
                case 25:
                    load(i2, OBJECT_TYPE);
                    return;
                default:
                    switch (i) {
                        case 54:
                            store(i2, Type.INT_TYPE);
                            return;
                        case 55:
                            store(i2, Type.LONG_TYPE);
                            return;
                        case 56:
                            store(i2, Type.FLOAT_TYPE);
                            return;
                        case 57:
                            store(i2, Type.DOUBLE_TYPE);
                            return;
                        case 58:
                            store(i2, OBJECT_TYPE);
                            return;
                        default:
                            throw new IllegalArgumentException();
                    }
            }
        }
        ret(i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        Type objectType = Type.getObjectType(str);
        if (i == 187) {
            anew(objectType);
            return;
        }
        if (i == 189) {
            newarray(objectType);
        } else if (i == 192) {
            checkcast(objectType);
        } else {
            if (i == 193) {
                instanceOf(objectType);
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        switch (i) {
            case 178:
                getstatic(str, str2, str3);
                return;
            case 179:
                putstatic(str, str2, str3);
                return;
            case 180:
                getfield(str, str2, str3);
                return;
            case 181:
                putfield(str, str2, str3);
                return;
            default:
                throw new IllegalArgumentException();
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

    private void doVisitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        switch (i) {
            case 182:
                invokevirtual(str, str2, str3, z);
                return;
            case 183:
                invokespecial(str, str2, str3, z);
                return;
            case 184:
                invokestatic(str, str2, str3, z);
                return;
            case 185:
                invokeinterface(str, str2, str3);
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        invokedynamic(str, str2, handle, objArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitJumpInsn(int i, Label label) {
        if (i == 198) {
            ifnull(label);
            return;
        }
        if (i != 199) {
            switch (i) {
                case 153:
                    ifeq(label);
                    return;
                case 154:
                    ifne(label);
                    return;
                case 155:
                    iflt(label);
                    return;
                case 156:
                    ifge(label);
                    return;
                case 157:
                    ifgt(label);
                    return;
                case 158:
                    ifle(label);
                    return;
                case 159:
                    ificmpeq(label);
                    return;
                case 160:
                    ificmpne(label);
                    return;
                case 161:
                    ificmplt(label);
                    return;
                case 162:
                    ificmpge(label);
                    return;
                case 163:
                    ificmpgt(label);
                    return;
                case 164:
                    ificmple(label);
                    return;
                case 165:
                    ifacmpeq(label);
                    return;
                case 166:
                    ifacmpne(label);
                    return;
                case 167:
                    goTo(label);
                    return;
                case 168:
                    jsr(label);
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        }
        ifnonnull(label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLabel(Label label) {
        mark(label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        if (obj instanceof Integer) {
            iconst(((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Byte) {
            iconst(((Byte) obj).intValue());
            return;
        }
        if (obj instanceof Character) {
            iconst(((Character) obj).charValue());
            return;
        }
        if (obj instanceof Short) {
            iconst(((Short) obj).intValue());
            return;
        }
        if (obj instanceof Boolean) {
            iconst(((Boolean) obj).booleanValue() ? 1 : 0);
            return;
        }
        if (obj instanceof Float) {
            fconst(((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Long) {
            lconst(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            dconst(((Double) obj).doubleValue());
            return;
        }
        if (obj instanceof String) {
            aconst(obj);
        } else if (obj instanceof Type) {
            tconst((Type) obj);
        } else {
            if (obj instanceof Handle) {
                hconst((Handle) obj);
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIincInsn(int i, int i2) {
        iinc(i, i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        tableswitch(i, i2, label, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        lookupswitch(label, iArr, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        multianewarray(str, i);
    }

    public void nop() {
        this.mv.visitInsn(0);
    }

    public void aconst(Object obj) {
        if (obj == null) {
            this.mv.visitInsn(1);
        } else {
            this.mv.visitLdcInsn(obj);
        }
    }

    public void iconst(int i) {
        if (i >= -1 && i <= 5) {
            this.mv.visitInsn(i + 3);
            return;
        }
        if (i >= -128 && i <= 127) {
            this.mv.visitIntInsn(16, i);
        } else if (i >= -32768 && i <= 32767) {
            this.mv.visitIntInsn(17, i);
        } else {
            this.mv.visitLdcInsn(Integer.valueOf(i));
        }
    }

    public void lconst(long j) {
        if (j == 0 || j == 1) {
            this.mv.visitInsn(((int) j) + 9);
        } else {
            this.mv.visitLdcInsn(Long.valueOf(j));
        }
    }

    public void fconst(float f) {
        int iFloatToIntBits = Float.floatToIntBits(f);
        if (iFloatToIntBits == 0 || iFloatToIntBits == 1065353216 || iFloatToIntBits == 1073741824) {
            this.mv.visitInsn(((int) f) + 11);
        } else {
            this.mv.visitLdcInsn(Float.valueOf(f));
        }
    }

    public void dconst(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        if (jDoubleToLongBits == 0 || jDoubleToLongBits == 4607182418800017408L) {
            this.mv.visitInsn(((int) d) + 14);
        } else {
            this.mv.visitLdcInsn(Double.valueOf(d));
        }
    }

    public void tconst(Type type) {
        this.mv.visitLdcInsn(type);
    }

    public void hconst(Handle handle) {
        this.mv.visitLdcInsn(handle);
    }

    public void load(int i, Type type) {
        this.mv.visitVarInsn(type.getOpcode(21), i);
    }

    public void aload(Type type) {
        this.mv.visitInsn(type.getOpcode(46));
    }

    public void store(int i, Type type) {
        this.mv.visitVarInsn(type.getOpcode(54), i);
    }

    public void astore(Type type) {
        this.mv.visitInsn(type.getOpcode(79));
    }

    public void pop() {
        this.mv.visitInsn(87);
    }

    public void pop2() {
        this.mv.visitInsn(88);
    }

    public void dup() {
        this.mv.visitInsn(89);
    }

    public void dup2() {
        this.mv.visitInsn(92);
    }

    public void dupX1() {
        this.mv.visitInsn(90);
    }

    public void dupX2() {
        this.mv.visitInsn(91);
    }

    public void dup2X1() {
        this.mv.visitInsn(93);
    }

    public void dup2X2() {
        this.mv.visitInsn(94);
    }

    public void swap() {
        this.mv.visitInsn(95);
    }

    public void add(Type type) {
        this.mv.visitInsn(type.getOpcode(96));
    }

    public void sub(Type type) {
        this.mv.visitInsn(type.getOpcode(100));
    }

    public void mul(Type type) {
        this.mv.visitInsn(type.getOpcode(104));
    }

    public void div(Type type) {
        this.mv.visitInsn(type.getOpcode(108));
    }

    public void rem(Type type) {
        this.mv.visitInsn(type.getOpcode(112));
    }

    public void neg(Type type) {
        this.mv.visitInsn(type.getOpcode(116));
    }

    public void shl(Type type) {
        this.mv.visitInsn(type.getOpcode(120));
    }

    public void shr(Type type) {
        this.mv.visitInsn(type.getOpcode(122));
    }

    public void ushr(Type type) {
        this.mv.visitInsn(type.getOpcode(124));
    }

    public void and(Type type) {
        this.mv.visitInsn(type.getOpcode(126));
    }

    public void or(Type type) {
        this.mv.visitInsn(type.getOpcode(128));
    }

    public void xor(Type type) {
        this.mv.visitInsn(type.getOpcode(130));
    }

    public void iinc(int i, int i2) {
        this.mv.visitIincInsn(i, i2);
    }

    public void cast(Type type, Type type2) {
        if (type != type2) {
            if (type == Type.DOUBLE_TYPE) {
                if (type2 == Type.FLOAT_TYPE) {
                    this.mv.visitInsn(144);
                    return;
                } else if (type2 == Type.LONG_TYPE) {
                    this.mv.visitInsn(143);
                    return;
                } else {
                    this.mv.visitInsn(142);
                    cast(Type.INT_TYPE, type2);
                    return;
                }
            }
            if (type == Type.FLOAT_TYPE) {
                if (type2 == Type.DOUBLE_TYPE) {
                    this.mv.visitInsn(141);
                    return;
                } else if (type2 == Type.LONG_TYPE) {
                    this.mv.visitInsn(140);
                    return;
                } else {
                    this.mv.visitInsn(139);
                    cast(Type.INT_TYPE, type2);
                    return;
                }
            }
            if (type == Type.LONG_TYPE) {
                if (type2 == Type.DOUBLE_TYPE) {
                    this.mv.visitInsn(138);
                    return;
                } else if (type2 == Type.FLOAT_TYPE) {
                    this.mv.visitInsn(137);
                    return;
                } else {
                    this.mv.visitInsn(136);
                    cast(Type.INT_TYPE, type2);
                    return;
                }
            }
            if (type2 == Type.BYTE_TYPE) {
                this.mv.visitInsn(145);
                return;
            }
            if (type2 == Type.CHAR_TYPE) {
                this.mv.visitInsn(146);
                return;
            }
            if (type2 == Type.DOUBLE_TYPE) {
                this.mv.visitInsn(135);
                return;
            }
            if (type2 == Type.FLOAT_TYPE) {
                this.mv.visitInsn(134);
            } else if (type2 == Type.LONG_TYPE) {
                this.mv.visitInsn(133);
            } else if (type2 == Type.SHORT_TYPE) {
                this.mv.visitInsn(147);
            }
        }
    }

    public void lcmp() {
        this.mv.visitInsn(148);
    }

    public void cmpl(Type type) {
        this.mv.visitInsn(type == Type.FLOAT_TYPE ? 149 : 151);
    }

    public void cmpg(Type type) {
        this.mv.visitInsn(type == Type.FLOAT_TYPE ? 150 : 152);
    }

    public void ifeq(Label label) {
        this.mv.visitJumpInsn(153, label);
    }

    public void ifne(Label label) {
        this.mv.visitJumpInsn(154, label);
    }

    public void iflt(Label label) {
        this.mv.visitJumpInsn(155, label);
    }

    public void ifge(Label label) {
        this.mv.visitJumpInsn(156, label);
    }

    public void ifgt(Label label) {
        this.mv.visitJumpInsn(157, label);
    }

    public void ifle(Label label) {
        this.mv.visitJumpInsn(158, label);
    }

    public void ificmpeq(Label label) {
        this.mv.visitJumpInsn(159, label);
    }

    public void ificmpne(Label label) {
        this.mv.visitJumpInsn(160, label);
    }

    public void ificmplt(Label label) {
        this.mv.visitJumpInsn(161, label);
    }

    public void ificmpge(Label label) {
        this.mv.visitJumpInsn(162, label);
    }

    public void ificmpgt(Label label) {
        this.mv.visitJumpInsn(163, label);
    }

    public void ificmple(Label label) {
        this.mv.visitJumpInsn(164, label);
    }

    public void ifacmpeq(Label label) {
        this.mv.visitJumpInsn(165, label);
    }

    public void ifacmpne(Label label) {
        this.mv.visitJumpInsn(166, label);
    }

    public void goTo(Label label) {
        this.mv.visitJumpInsn(167, label);
    }

    public void jsr(Label label) {
        this.mv.visitJumpInsn(168, label);
    }

    public void ret(int i) {
        this.mv.visitVarInsn(169, i);
    }

    public void tableswitch(int i, int i2, Label label, Label... labelArr) {
        this.mv.visitTableSwitchInsn(i, i2, label, labelArr);
    }

    public void lookupswitch(Label label, int[] iArr, Label[] labelArr) {
        this.mv.visitLookupSwitchInsn(label, iArr, labelArr);
    }

    public void areturn(Type type) {
        this.mv.visitInsn(type.getOpcode(172));
    }

    public void getstatic(String str, String str2, String str3) {
        this.mv.visitFieldInsn(178, str, str2, str3);
    }

    public void putstatic(String str, String str2, String str3) {
        this.mv.visitFieldInsn(179, str, str2, str3);
    }

    public void getfield(String str, String str2, String str3) {
        this.mv.visitFieldInsn(180, str, str2, str3);
    }

    public void putfield(String str, String str2, String str3) {
        this.mv.visitFieldInsn(181, str, str2, str3);
    }

    @Deprecated
    public void invokevirtual(String str, String str2, String str3) {
        if (this.api >= 327680) {
            invokevirtual(str, str2, str3, false);
        } else {
            this.mv.visitMethodInsn(182, str, str2, str3);
        }
    }

    public void invokevirtual(String str, String str2, String str3, boolean z) {
        if (this.api >= 327680) {
            this.mv.visitMethodInsn(182, str, str2, str3, z);
        } else {
            if (z) {
                throw new IllegalArgumentException("INVOKEVIRTUAL on interfaces require ASM 5");
            }
            invokevirtual(str, str2, str3);
        }
    }

    @Deprecated
    public void invokespecial(String str, String str2, String str3) {
        if (this.api >= 327680) {
            invokespecial(str, str2, str3, false);
        } else {
            this.mv.visitMethodInsn(183, str, str2, str3, false);
        }
    }

    public void invokespecial(String str, String str2, String str3, boolean z) {
        if (this.api >= 327680) {
            this.mv.visitMethodInsn(183, str, str2, str3, z);
        } else {
            if (z) {
                throw new IllegalArgumentException("INVOKESPECIAL on interfaces require ASM 5");
            }
            invokespecial(str, str2, str3);
        }
    }

    @Deprecated
    public void invokestatic(String str, String str2, String str3) {
        if (this.api >= 327680) {
            invokestatic(str, str2, str3, false);
        } else {
            this.mv.visitMethodInsn(184, str, str2, str3, false);
        }
    }

    public void invokestatic(String str, String str2, String str3, boolean z) {
        if (this.api >= 327680) {
            this.mv.visitMethodInsn(184, str, str2, str3, z);
        } else {
            if (z) {
                throw new IllegalArgumentException("INVOKESTATIC on interfaces require ASM 5");
            }
            invokestatic(str, str2, str3);
        }
    }

    public void invokeinterface(String str, String str2, String str3) {
        this.mv.visitMethodInsn(185, str, str2, str3, true);
    }

    public void invokedynamic(String str, String str2, Handle handle, Object[] objArr) {
        this.mv.visitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    public void anew(Type type) {
        this.mv.visitTypeInsn(187, type.getInternalName());
    }

    public void newarray(Type type) {
        int i;
        switch (type.getSort()) {
            case 1:
                i = 4;
                break;
            case 2:
                i = 5;
                break;
            case 3:
                i = 8;
                break;
            case 4:
                i = 9;
                break;
            case 5:
                i = 10;
                break;
            case 6:
                i = 6;
                break;
            case 7:
                i = 11;
                break;
            case 8:
                i = 7;
                break;
            default:
                this.mv.visitTypeInsn(189, type.getInternalName());
                return;
        }
        this.mv.visitIntInsn(188, i);
    }

    public void arraylength() {
        this.mv.visitInsn(190);
    }

    public void athrow() {
        this.mv.visitInsn(191);
    }

    public void checkcast(Type type) {
        this.mv.visitTypeInsn(192, type.getInternalName());
    }

    public void instanceOf(Type type) {
        this.mv.visitTypeInsn(193, type.getInternalName());
    }

    public void monitorenter() {
        this.mv.visitInsn(194);
    }

    public void monitorexit() {
        this.mv.visitInsn(195);
    }

    public void multianewarray(String str, int i) {
        this.mv.visitMultiANewArrayInsn(str, i);
    }

    public void ifnull(Label label) {
        this.mv.visitJumpInsn(198, label);
    }

    public void ifnonnull(Label label) {
        this.mv.visitJumpInsn(199, label);
    }

    public void mark(Label label) {
        this.mv.visitLabel(label);
    }
}

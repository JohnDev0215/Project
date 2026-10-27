package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

/* JADX INFO: loaded from: classes3.dex */
public class CodeSizeEvaluator extends MethodVisitor implements Opcodes {
    private int maxSize;
    private int minSize;

    public CodeSizeEvaluator(MethodVisitor methodVisitor) {
        this(Opcodes.ASM6, methodVisitor);
    }

    protected CodeSizeEvaluator(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    public int getMinSize() {
        return this.minSize;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInsn(int i) {
        this.minSize++;
        this.maxSize++;
        if (this.mv != null) {
            this.mv.visitInsn(i);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        if (i == 17) {
            this.minSize += 3;
            this.maxSize += 3;
        } else {
            this.minSize += 2;
            this.maxSize += 2;
        }
        if (this.mv != null) {
            this.mv.visitIntInsn(i, i2);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        if (i2 < 4 && i != 169) {
            this.minSize++;
            this.maxSize++;
        } else if (i2 >= 256) {
            this.minSize += 4;
            this.maxSize += 4;
        } else {
            this.minSize += 2;
            this.maxSize += 2;
        }
        if (this.mv != null) {
            this.mv.visitVarInsn(i, i2);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        this.minSize += 3;
        this.maxSize += 3;
        if (this.mv != null) {
            this.mv.visitTypeInsn(i, str);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        this.minSize += 3;
        this.maxSize += 3;
        if (this.mv != null) {
            this.mv.visitFieldInsn(i, str, str2, str3);
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
        if (i == 185) {
            this.minSize += 5;
            this.maxSize += 5;
        } else {
            this.minSize += 3;
            this.maxSize += 3;
        }
        if (this.mv != null) {
            this.mv.visitMethodInsn(i, str, str2, str3, z);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        this.minSize += 5;
        this.maxSize += 5;
        if (this.mv != null) {
            this.mv.visitInvokeDynamicInsn(str, str2, handle, objArr);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitJumpInsn(int i, Label label) {
        this.minSize += 3;
        if (i == 167 || i == 168) {
            this.maxSize += 5;
        } else {
            this.maxSize += 8;
        }
        if (this.mv != null) {
            this.mv.visitJumpInsn(i, label);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        if ((obj instanceof Long) || (obj instanceof Double)) {
            this.minSize += 3;
            this.maxSize += 3;
        } else {
            this.minSize += 2;
            this.maxSize += 3;
        }
        if (this.mv != null) {
            this.mv.visitLdcInsn(obj);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIincInsn(int i, int i2) {
        if (i > 255 || i2 > 127 || i2 < -128) {
            this.minSize += 6;
            this.maxSize += 6;
        } else {
            this.minSize += 3;
            this.maxSize += 3;
        }
        if (this.mv != null) {
            this.mv.visitIincInsn(i, i2);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        this.minSize += (labelArr.length * 4) + 13;
        this.maxSize += (labelArr.length * 4) + 16;
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(i, i2, label, labelArr);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.minSize += (iArr.length * 8) + 9;
        this.maxSize += (iArr.length * 8) + 12;
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(label, iArr, labelArr);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        this.minSize += 4;
        this.maxSize += 4;
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(str, i);
        }
    }
}

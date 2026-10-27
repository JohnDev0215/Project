package net.bytebuddy.jar.asm.commons;

import kotlin.text.Typography;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class RemappingSignatureAdapter extends SignatureVisitor {
    private String className;
    private final Remapper remapper;
    private final SignatureVisitor v;

    public RemappingSignatureAdapter(SignatureVisitor signatureVisitor, Remapper remapper) {
        this(Opcodes.ASM6, signatureVisitor, remapper);
    }

    protected RemappingSignatureAdapter(int i, SignatureVisitor signatureVisitor, Remapper remapper) {
        super(i);
        this.v = signatureVisitor;
        this.remapper = remapper;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitClassType(String str) {
        this.className = str;
        this.v.visitClassType(this.remapper.mapType(str));
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitInnerClassType(String str) {
        String str2 = this.remapper.mapType(this.className) + Typography.dollar;
        String str3 = this.className + Typography.dollar + str;
        this.className = str3;
        String strMapType = this.remapper.mapType(str3);
        this.v.visitInnerClassType(strMapType.substring(strMapType.startsWith(str2) ? str2.length() : strMapType.lastIndexOf(36) + 1));
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitFormalTypeParameter(String str) {
        this.v.visitFormalTypeParameter(str);
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitTypeVariable(String str) {
        this.v.visitTypeVariable(str);
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitArrayType() {
        this.v.visitArrayType();
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitBaseType(char c) {
        this.v.visitBaseType(c);
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitClassBound() {
        this.v.visitClassBound();
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitExceptionType() {
        this.v.visitExceptionType();
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitInterface() {
        this.v.visitInterface();
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitInterfaceBound() {
        this.v.visitInterfaceBound();
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitParameterType() {
        this.v.visitParameterType();
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitReturnType() {
        this.v.visitReturnType();
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitSuperclass() {
        this.v.visitSuperclass();
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitTypeArgument() {
        this.v.visitTypeArgument();
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public SignatureVisitor visitTypeArgument(char c) {
        this.v.visitTypeArgument(c);
        return this;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitEnd() {
        this.v.visitEnd();
    }
}

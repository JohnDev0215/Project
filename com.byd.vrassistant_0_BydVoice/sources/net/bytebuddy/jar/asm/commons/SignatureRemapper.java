package net.bytebuddy.jar.asm.commons;

import java.util.Stack;
import kotlin.text.Typography;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class SignatureRemapper extends SignatureVisitor {
    private Stack<String> classNames;
    private final Remapper remapper;
    private final SignatureVisitor v;

    public SignatureRemapper(SignatureVisitor signatureVisitor, Remapper remapper) {
        this(Opcodes.ASM6, signatureVisitor, remapper);
    }

    protected SignatureRemapper(int i, SignatureVisitor signatureVisitor, Remapper remapper) {
        super(i);
        this.classNames = new Stack<>();
        this.v = signatureVisitor;
        this.remapper = remapper;
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitClassType(String str) {
        this.classNames.push(str);
        this.v.visitClassType(this.remapper.mapType(str));
    }

    @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
    public void visitInnerClassType(String str) {
        String strPop = this.classNames.pop();
        String str2 = strPop + Typography.dollar + str;
        this.classNames.push(str2);
        String str3 = this.remapper.mapType(strPop) + Typography.dollar;
        String strMapType = this.remapper.mapType(str2);
        this.v.visitInnerClassType(strMapType.substring(strMapType.startsWith(str3) ? str3.length() : strMapType.lastIndexOf(36) + 1));
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
        this.classNames.pop();
    }
}

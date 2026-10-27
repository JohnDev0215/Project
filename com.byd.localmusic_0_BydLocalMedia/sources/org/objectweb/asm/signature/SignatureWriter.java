package org.objectweb.asm.signature;

import android.text.format.DateFormat;
import com.android.internal.accessibility.common.ShortcutConstants;
import kotlin.text.Typography;
import org.objectweb.asm.Opcodes;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:org/objectweb/asm/signature/SignatureWriter.SCL.lombok */
public class SignatureWriter extends SignatureVisitor {
    private final StringBuilder stringBuilder;
    private boolean hasFormals;
    private boolean hasParameters;
    private int argumentStack;

    public SignatureWriter() {
        super(Opcodes.ASM9);
        this.stringBuilder = new StringBuilder();
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public void visitFormalTypeParameter(String name) {
        if (!this.hasFormals) {
            this.hasFormals = true;
            this.stringBuilder.append(Typography.less);
        }
        this.stringBuilder.append(name);
        this.stringBuilder.append(ShortcutConstants.SERVICES_SEPARATOR);
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitClassBound() {
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitInterfaceBound() {
        this.stringBuilder.append(ShortcutConstants.SERVICES_SEPARATOR);
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitSuperclass() {
        endFormals();
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitInterface() {
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitParameterType() {
        endFormals();
        if (!this.hasParameters) {
            this.hasParameters = true;
            this.stringBuilder.append('(');
        }
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitReturnType() {
        endFormals();
        if (!this.hasParameters) {
            this.stringBuilder.append('(');
        }
        this.stringBuilder.append(')');
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitExceptionType() {
        this.stringBuilder.append('^');
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public void visitBaseType(char descriptor) {
        this.stringBuilder.append(descriptor);
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public void visitTypeVariable(String name) {
        this.stringBuilder.append('T');
        this.stringBuilder.append(name);
        this.stringBuilder.append(';');
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitArrayType() {
        this.stringBuilder.append('[');
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public void visitClassType(String name) {
        this.stringBuilder.append(DateFormat.STANDALONE_MONTH);
        this.stringBuilder.append(name);
        this.argumentStack *= 2;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public void visitInnerClassType(String name) {
        endArguments();
        this.stringBuilder.append('.');
        this.stringBuilder.append(name);
        this.argumentStack *= 2;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public void visitTypeArgument() {
        if (this.argumentStack % 2 == 0) {
            this.argumentStack |= 1;
            this.stringBuilder.append(Typography.less);
        }
        this.stringBuilder.append('*');
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public SignatureVisitor visitTypeArgument(char wildcard) {
        if (this.argumentStack % 2 == 0) {
            this.argumentStack |= 1;
            this.stringBuilder.append(Typography.less);
        }
        if (wildcard != '=') {
            this.stringBuilder.append(wildcard);
        }
        return this;
    }

    @Override // org.objectweb.asm.signature.SignatureVisitor
    public void visitEnd() {
        endArguments();
        this.stringBuilder.append(';');
    }

    public String toString() {
        return this.stringBuilder.toString();
    }

    private void endFormals() {
        if (this.hasFormals) {
            this.hasFormals = false;
            this.stringBuilder.append(Typography.greater);
        }
    }

    private void endArguments() {
        if (this.argumentStack % 2 == 1) {
            this.stringBuilder.append(Typography.greater);
        }
        this.argumentStack /= 2;
    }
}

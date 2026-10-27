package net.bytebuddy.jar.asm.signature;

/* JADX INFO: loaded from: classes3.dex */
public class SignatureReader {
    private final String signature;

    public SignatureReader(String str) {
        this.signature = str;
    }

    public void accept(SignatureVisitor signatureVisitor) {
        char cCharAt;
        String str = this.signature;
        int length = str.length();
        int i = 0;
        if (str.charAt(0) == '<') {
            i = 2;
            do {
                int iIndexOf = str.indexOf(58, i);
                signatureVisitor.visitFormalTypeParameter(str.substring(i - 1, iIndexOf));
                int type = iIndexOf + 1;
                char cCharAt2 = str.charAt(type);
                if (cCharAt2 == 'L' || cCharAt2 == '[' || cCharAt2 == 'T') {
                    type = parseType(str, type, signatureVisitor.visitClassBound());
                }
                while (true) {
                    i = type + 1;
                    cCharAt = str.charAt(type);
                    if (cCharAt != ':') {
                        break;
                    } else {
                        type = parseType(str, i, signatureVisitor.visitInterfaceBound());
                    }
                }
            } while (cCharAt != '>');
        }
        if (str.charAt(i) == '(') {
            int type2 = i + 1;
            while (str.charAt(type2) != ')') {
                type2 = parseType(str, type2, signatureVisitor.visitParameterType());
            }
            int type3 = parseType(str, type2 + 1, signatureVisitor.visitReturnType());
            while (type3 < length) {
                type3 = parseType(str, type3 + 1, signatureVisitor.visitExceptionType());
            }
            return;
        }
        int type4 = parseType(str, i, signatureVisitor.visitSuperclass());
        while (type4 < length) {
            type4 = parseType(str, type4, signatureVisitor.visitInterface());
        }
    }

    public void acceptType(SignatureVisitor signatureVisitor) {
        parseType(this.signature, 0, signatureVisitor);
    }

    private static int parseType(String str, int i, SignatureVisitor signatureVisitor) {
        int i2 = i + 1;
        char cCharAt = str.charAt(i);
        if (cCharAt != 'F' && cCharAt != 'V' && cCharAt != 'I' && cCharAt != 'J' && cCharAt != 'S') {
            if (cCharAt == 'T') {
                int iIndexOf = str.indexOf(59, i2);
                signatureVisitor.visitTypeVariable(str.substring(i2, iIndexOf));
                return iIndexOf + 1;
            }
            if (cCharAt != 'Z') {
                if (cCharAt == '[') {
                    return parseType(str, i2, signatureVisitor.visitArrayType());
                }
                switch (cCharAt) {
                    case 'B':
                    case 'C':
                    case 'D':
                        break;
                    default:
                        int i3 = i2;
                        boolean z = false;
                        boolean z2 = false;
                        while (true) {
                            int type = i2 + 1;
                            char cCharAt2 = str.charAt(i2);
                            if (cCharAt2 == '.' || cCharAt2 == ';') {
                                if (!z) {
                                    String strSubstring = str.substring(i3, type - 1);
                                    if (z2) {
                                        signatureVisitor.visitInnerClassType(strSubstring);
                                    } else {
                                        signatureVisitor.visitClassType(strSubstring);
                                    }
                                }
                                if (cCharAt2 == ';') {
                                    signatureVisitor.visitEnd();
                                    return type;
                                }
                                z = false;
                                z2 = true;
                                i3 = type;
                                i2 = i3;
                            } else {
                                if (cCharAt2 == '<') {
                                    String strSubstring2 = str.substring(i3, type - 1);
                                    if (z2) {
                                        signatureVisitor.visitInnerClassType(strSubstring2);
                                    } else {
                                        signatureVisitor.visitClassType(strSubstring2);
                                    }
                                    while (true) {
                                        char cCharAt3 = str.charAt(type);
                                        if (cCharAt3 == '*') {
                                            type++;
                                            signatureVisitor.visitTypeArgument();
                                        } else if (cCharAt3 == '+' || cCharAt3 == '-') {
                                            type = parseType(str, type + 1, signatureVisitor.visitTypeArgument(cCharAt3));
                                        } else if (cCharAt3 != '>') {
                                            type = parseType(str, type, signatureVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF));
                                        } else {
                                            z = true;
                                        }
                                    }
                                }
                                i2 = type;
                            }
                        }
                        break;
                }
            }
        }
        signatureVisitor.visitBaseType(cCharAt);
        return i2;
    }
}

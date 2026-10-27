package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CodeWriter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0002\u001a)\u0010\t\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0000\"\u0014\u0010\n\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b\"\u001a\u0010\r\u001a\u00020\f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"", "part", "extractMemberName", "Lcom/squareup/kotlinpoet/CodeWriter;", "codeWriter", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "buildCodeString", "NO_PACKAGE", "Ljava/lang/String;", "Lcom/squareup/kotlinpoet/TypeName;", "NULLABLE_ANY", "Lcom/squareup/kotlinpoet/TypeName;", "getNULLABLE_ANY", "()Lcom/squareup/kotlinpoet/TypeName;", "kotlinpoet"}, k = 2, mv = {1, 6, 0})
public final class CodeWriterKt {
    private static final String NO_PACKAGE = new String();
    private static final TypeName NULLABLE_ANY = TypeName.copy$default(TypeNames.ANY, true, null, 2, null);

    public static final TypeName getNULLABLE_ANY() {
        return NULLABLE_ANY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String extractMemberName(String str) {
        if (!Character.isJavaIdentifierStart(str.charAt(0))) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("not an identifier: ", str).toString());
        }
        int length = str.length();
        if (1 <= length) {
            int i = 1;
            while (true) {
                int i2 = i + 1;
                String strSubstring = str.substring(0, i);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                if (!UtilKt.isIdentifier(strSubstring)) {
                    String strSubstring2 = str.substring(0, i - 1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    return strSubstring2;
                }
                if (i == length) {
                    break;
                }
                i = i2;
            }
        }
        return str;
    }

    public static final String buildCodeString(CodeWriter codeWriter, Function1<? super CodeWriter, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        StringBuilder sb = new StringBuilder();
        LineWrapper lineWrapper = new LineWrapper(sb, "  ", Integer.MAX_VALUE);
        try {
            LineWrapper lineWrapper2 = codeWriter.out;
            codeWriter.out = lineWrapper;
            builderAction.invoke(codeWriter);
            codeWriter.out = lineWrapper2;
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(lineWrapper, null);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
            return string;
        } finally {
        }
    }
}

package com.squareup.kotlinpoet;

import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CodeBlock.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0005\u001a0\u0010\u0006\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u0007¨\u0006\u0007"}, d2 = {"", "Lcom/squareup/kotlinpoet/CodeBlock;", "", "separator", "prefix", "suffix", "joinToCode", "kotlinpoet"}, k = 2, mv = {1, 6, 0})
@JvmName(name = "CodeBlocks")
public final class CodeBlocks {
    @JvmOverloads
    public static final CodeBlock joinToCode(Collection<CodeBlock> collection, CharSequence separator, CharSequence prefix, CharSequence suffix) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        Object[] array = collection.toArray(new CodeBlock[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        CodeBlock[] codeBlockArr = (CodeBlock[]) array;
        int length = codeBlockArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = "%L";
        }
        return CodeBlock.INSTANCE.of(ArraysKt___ArraysKt.joinToString$default(strArr, separator, prefix, suffix, 0, (CharSequence) null, (Function1) null, 56, (Object) null), Arrays.copyOf(codeBlockArr, codeBlockArr.length));
    }

    public static /* synthetic */ CodeBlock joinToCode$default(Collection collection, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Object obj) {
        if ((i & 1) != 0) {
            charSequence = ", ";
        }
        if ((i & 2) != 0) {
            charSequence2 = "";
        }
        if ((i & 4) != 0) {
            charSequence3 = "";
        }
        return joinToCode(collection, charSequence, charSequence2, charSequence3);
    }
}

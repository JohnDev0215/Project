package com.squareup.kotlinpoet;

import java.util.EnumSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KModifier.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"VISIBILITY_MODIFIERS", "", "Lcom/squareup/kotlinpoet/KModifier;", "getVISIBILITY_MODIFIERS", "()Ljava/util/Set;", "kotlinpoet"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class KModifierKt {
    private static final Set<KModifier> VISIBILITY_MODIFIERS;

    static {
        EnumSet enumSetOf = EnumSet.of(KModifier.PUBLIC, KModifier.INTERNAL, KModifier.PROTECTED, KModifier.PRIVATE);
        Intrinsics.checkNotNullExpressionValue(enumSetOf, "of(PUBLIC, INTERNAL, PROTECTED, PRIVATE)");
        VISIBILITY_MODIFIERS = enumSetOf;
    }

    public static final Set<KModifier> getVISIBILITY_MODIFIERS() {
        return VISIBILITY_MODIFIERS;
    }
}

package dagger.spi.shaded.androidx.room.compiler.processing;

import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.ParameterizedTypeName;
import com.squareup.kotlinpoet.TypeName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinPoetExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\f\u0010\u0001\u001a\u00020\u0000*\u00020\u0000H\u0000\u001a)\u0010\u0006\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00000\u0002*\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007\"\u001e\u0010\n\u001a\u00060\bj\u0002`\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/squareup/kotlinpoet/TypeName;", "rawTypeName", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;", "T", "Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "element", "addOriginatingElement", "(Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;Landroidx/room/compiler/processing/XElement;)Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/javapoet/KClassName;", "KOTLIN_NONE_TYPE_NAME", "Lcom/squareup/kotlinpoet/ClassName;", "getKOTLIN_NONE_TYPE_NAME", "()Lcom/squareup/kotlinpoet/ClassName;", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class KotlinPoetExtKt {
    private static final ClassName KOTLIN_NONE_TYPE_NAME = new ClassName("dagger.spi.shaded.androidx.room.compiler.processing.error", "NotAType");

    public static final TypeName rawTypeName(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "<this>");
        return typeName instanceof ParameterizedTypeName ? ((ParameterizedTypeName) typeName).getRawType() : typeName;
    }
}

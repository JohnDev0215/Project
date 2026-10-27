package dagger.spi.shaded.androidx.room.compiler.codegen;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.squareup.javapoet.ClassName;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XTypeName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u0001:\u0001\"B)\b\u0000\u0012\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007\u0012\n\u0010\u000e\u001a\u00060\fj\u0002`\r\u0012\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b \u0010!J!\u0010\u0004\u001a\u00020\u00012\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0002\"\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005R\u001e\u0010\b\u001a\u00060\u0006j\u0002`\u00078\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u000e\u001a\u00060\fj\u0002`\r8\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0014\u001a\u0004\b\u001d\u0010\u0016¨\u0006#"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/codegen/XClassName;", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "", "typeArguments", "parametrizedBy", "([Landroidx/room/compiler/codegen/XTypeName;)Landroidx/room/compiler/codegen/XTypeName;", "Lcom/squareup/javapoet/ClassName;", "Lcom/squareup/kotlinpoet/javapoet/JClassName;", "java", "Lcom/squareup/javapoet/ClassName;", "getJava$room_compiler_processing", "()Lcom/squareup/javapoet/ClassName;", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/javapoet/KClassName;", "kotlin", "Lcom/squareup/kotlinpoet/ClassName;", "getKotlin$room_compiler_processing", "()Lcom/squareup/kotlinpoet/ClassName;", "", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "Ljava/lang/String;", "getPackageName", "()Ljava/lang/String;", "", "simpleNames", "Ljava/util/List;", "getSimpleNames", "()Ljava/util/List;", "canonicalName", "getCanonicalName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "<init>", "(Lcom/squareup/javapoet/ClassName;Lcom/squareup/kotlinpoet/ClassName;Landroidx/room/compiler/processing/XNullability;)V", "Companion", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class XClassName extends XTypeName {
    private final String canonicalName;
    private final ClassName java;
    private final com.squareup.kotlinpoet.ClassName kotlin;
    private final String packageName;
    private final List<String> simpleNames;

    @Override // dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName
    /* JADX INFO: renamed from: getJava$room_compiler_processing, reason: from getter */
    public ClassName getJava() {
        return this.java;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName
    /* JADX INFO: renamed from: getKotlin$room_compiler_processing, reason: from getter */
    public com.squareup.kotlinpoet.ClassName getKotlin() {
        return this.kotlin;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public XClassName(ClassName java, com.squareup.kotlinpoet.ClassName kotlin2, XNullability nullability) {
        super(java, kotlin2, nullability);
        Intrinsics.checkNotNullParameter(java, "java");
        Intrinsics.checkNotNullParameter(kotlin2, "kotlin");
        Intrinsics.checkNotNullParameter(nullability, "nullability");
        this.java = java;
        this.kotlin = kotlin2;
        String strPackageName = getJava().packageName();
        Intrinsics.checkNotNullExpressionValue(strPackageName, "java.packageName()");
        this.packageName = strPackageName;
        List<String> listSimpleNames = getJava().simpleNames();
        Intrinsics.checkNotNullExpressionValue(listSimpleNames, "java.simpleNames()");
        this.simpleNames = listSimpleNames;
        String strCanonicalName = getJava().canonicalName();
        Intrinsics.checkNotNullExpressionValue(strCanonicalName, "java.canonicalName()");
        this.canonicalName = strCanonicalName;
    }
}

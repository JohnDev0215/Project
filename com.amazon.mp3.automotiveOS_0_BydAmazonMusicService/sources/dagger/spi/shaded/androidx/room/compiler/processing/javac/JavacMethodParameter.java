package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XExecutableParameterElement;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmValueParameter;
import dagger.spi.shaded.androidx.room.compiler.processing.util.NamingUtilsKt;
import javax.lang.model.element.VariableElement;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacMethodParameter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010#\u001a\u00020\"\u0012\u000e\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0$\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b&\u0010'R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0012\u001a\u0004\u0018\u00010\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0017\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u001b\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006("}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodParameter;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacVariableElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XExecutableParameterElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacExecutableElement;", "enclosingElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacExecutableElement;", "getEnclosingElement", "()Landroidx/room/compiler/processing/javac/JavacExecutableElement;", "", "argIndex", "I", "getArgIndex", "()I", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmValueParameter;", "kotlinMetadata$delegate", "Lkotlin/Lazy;", "getKotlinMetadata", "()Landroidx/room/compiler/processing/javac/kotlin/KmValueParameter;", "kotlinMetadata", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMemberContainer;", "closestMemberContainer$delegate", "getClosestMemberContainer", "()Landroidx/room/compiler/processing/XMemberContainer;", "closestMemberContainer", "", "getName", "()Ljava/lang/String;", "name", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKotlinType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ljavax/lang/model/element/VariableElement;", "element", "Lkotlin/Function0;", "kotlinMetadataFactory", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Landroidx/room/compiler/processing/javac/JavacExecutableElement;Ljavax/lang/model/element/VariableElement;Lkotlin/jvm/functions/Function0;I)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacMethodParameter extends JavacVariableElement implements XExecutableParameterElement {
    private final int argIndex;

    /* JADX INFO: renamed from: closestMemberContainer$delegate, reason: from kotlin metadata */
    private final Lazy closestMemberContainer;
    private final JavacExecutableElement enclosingElement;

    /* JADX INFO: renamed from: kotlinMetadata$delegate, reason: from kotlin metadata */
    private final Lazy kotlinMetadata;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public JavacExecutableElement getEnclosingElement() {
        return this.enclosingElement;
    }

    public final int getArgIndex() {
        return this.argIndex;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavacMethodParameter(JavacProcessingEnv env, JavacExecutableElement enclosingElement, VariableElement element, final Function0<KmValueParameter> kotlinMetadataFactory, int i) {
        super(env, element);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(enclosingElement, "enclosingElement");
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(kotlinMetadataFactory, "kotlinMetadataFactory");
        this.enclosingElement = enclosingElement;
        this.argIndex = i;
        this.kotlinMetadata = LazyKt__LazyJVMKt.lazy(new Function0<KmValueParameter>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodParameter$kotlinMetadata$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KmValueParameter invoke() {
                return kotlinMetadataFactory.invoke();
            }
        });
        this.closestMemberContainer = LazyKt__LazyJVMKt.lazy(new Function0<JavacTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodParameter$closestMemberContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacTypeElement invoke() {
                return this.this$0.getEnclosingElement().getEnclosingElement();
            }
        });
    }

    private final KmValueParameter getKotlinMetadata() {
        return (KmValueParameter) this.kotlinMetadata.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacVariableElement, dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public String getName() {
        String name;
        KmValueParameter kotlinMetadata = getKotlinMetadata();
        if (kotlinMetadata == null || (name = kotlinMetadata.getName()) == null) {
            name = super.getName();
        }
        return NamingUtilsKt.sanitizeAsJavaParameterName(name, this.argIndex);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacVariableElement
    public KmType getKotlinType() {
        KmValueParameter kotlinMetadata = getKotlinMetadata();
        if (kotlinMetadata != null) {
            return kotlinMetadata.getType();
        }
        return null;
    }
}

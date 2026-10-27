package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XFieldElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmProperty;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement;
import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacFieldElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aR\u001d\u0010\t\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000e\u001a\u00020\n8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0010\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacFieldElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacVariableElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFieldElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XHasModifiers;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmProperty;", "kotlinMetadata$delegate", "Lkotlin/Lazy;", "getKotlinMetadata", "()Landroidx/room/compiler/processing/javac/kotlin/KmProperty;", "kotlinMetadata", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "enclosingElement$delegate", "getEnclosingElement", "()Landroidx/room/compiler/processing/javac/JavacTypeElement;", "enclosingElement", "getClosestMemberContainer", "closestMemberContainer", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKotlinType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ljavax/lang/model/element/VariableElement;", "element", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/VariableElement;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacFieldElement extends JavacVariableElement implements XFieldElement, XHasModifiers {
    private final /* synthetic */ JavacHasModifiers $$delegate_0;

    /* JADX INFO: renamed from: enclosingElement$delegate, reason: from kotlin metadata */
    private final Lazy enclosingElement;

    /* JADX INFO: renamed from: kotlinMetadata$delegate, reason: from kotlin metadata */
    private final Lazy kotlinMetadata;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavacFieldElement(final JavacProcessingEnv env, final VariableElement element) {
        super(env, element);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(element, "element");
        this.$$delegate_0 = new JavacHasModifiers((Element) element);
        this.kotlinMetadata = LazyKt__LazyJVMKt.lazy(new Function0<KmProperty>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacFieldElement$kotlinMetadata$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KmProperty invoke() {
                KotlinMetadataElement kotlinMetadata;
                JavacTypeElement enclosingElement = this.this$0.getEnclosingElement();
                if (!(enclosingElement instanceof JavacTypeElement)) {
                    enclosingElement = null;
                }
                if (enclosingElement == null || (kotlinMetadata = enclosingElement.getKotlinMetadata()) == null) {
                    return null;
                }
                return kotlinMetadata.getPropertyMetadata(this.this$0.getName());
            }
        });
        this.enclosingElement = LazyKt__LazyJVMKt.lazy(new Function0<JavacTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacFieldElement$enclosingElement$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacTypeElement invoke() {
                return ElementExtKt.requireEnclosingType(element, env);
            }
        });
    }

    private final KmProperty getKotlinMetadata() {
        return (KmProperty) this.kotlinMetadata.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacVariableElement
    public KmType getKotlinType() {
        KmProperty kotlinMetadata = getKotlinMetadata();
        if (kotlinMetadata != null) {
            return kotlinMetadata.getType();
        }
        return null;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public JavacTypeElement getEnclosingElement() {
        return (JavacTypeElement) this.enclosingElement.getValue();
    }
}

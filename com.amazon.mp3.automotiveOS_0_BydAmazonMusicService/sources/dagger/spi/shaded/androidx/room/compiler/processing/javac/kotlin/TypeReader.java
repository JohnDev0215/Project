package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmAnnotation;
import kotlinx.metadata.KmExtensionType;
import kotlinx.metadata.KmTypeExtensionVisitor;
import kotlinx.metadata.KmTypeVisitor;
import kotlinx.metadata.KmVariance;
import kotlinx.metadata.jvm.JvmTypeExtensionVisitor;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B%\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\u001c\u0010\u000f\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001e\u0010\u0017\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/TypeReader;", "Lkotlinx/metadata/KmTypeVisitor;", "flags", "", "Lkotlinx/metadata/Flags;", "output", "Lkotlin/Function1;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "", "(ILkotlin/jvm/functions/Function1;)V", "extendsBound", "isExtensionType", "", "typeArguments", "", "visitArgument", "variance", "Lkotlinx/metadata/KmVariance;", "visitEnd", "visitExtensions", "Lkotlinx/metadata/KmTypeExtensionVisitor;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/metadata/KmExtensionType;", "visitFlexibleTypeUpperBound", "typeFlexibilityId", "", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
final class TypeReader extends KmTypeVisitor {
    private KmType extendsBound;
    private final int flags;
    private boolean isExtensionType;
    private final Function1<KmType, Unit> output;
    private final List<KmType> typeArguments;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TypeReader(int i, Function1<? super KmType, Unit> output) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(output, "output");
        this.flags = i;
        this.output = output;
        this.typeArguments = new ArrayList();
    }

    @Override // kotlinx.metadata.KmTypeVisitor
    public KmTypeVisitor visitArgument(int flags, KmVariance variance) {
        Intrinsics.checkNotNullParameter(variance, "variance");
        return new TypeReader(flags, new Function1<KmType, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.TypeReader.visitArgument.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KmType kmType) {
                invoke2(kmType);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KmType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                TypeReader.this.typeArguments.add(it);
            }
        });
    }

    @Override // kotlinx.metadata.KmTypeVisitor
    public KmTypeVisitor visitFlexibleTypeUpperBound(int flags, String typeFlexibilityId) {
        return new TypeReader(flags, new Function1<KmType, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.TypeReader.visitFlexibleTypeUpperBound.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KmType kmType) {
                invoke2(kmType);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KmType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                TypeReader.this.extendsBound = it;
            }
        });
    }

    @Override // kotlinx.metadata.KmTypeVisitor
    public KmTypeExtensionVisitor visitExtensions(KmExtensionType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (Intrinsics.areEqual(type, JvmTypeExtensionVisitor.TYPE)) {
            return new JvmTypeExtensionVisitor() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.TypeReader.visitExtensions.1
                {
                    super(null, 1, null);
                }

                @Override // kotlinx.metadata.jvm.JvmTypeExtensionVisitor
                public void visitAnnotation(KmAnnotation annotation) {
                    Intrinsics.checkNotNullParameter(annotation, "annotation");
                    if (Intrinsics.areEqual(annotation.getClassName(), "kotlin/ExtensionFunctionType")) {
                        TypeReader.this.isExtensionType = true;
                    }
                }
            };
        }
        return null;
    }

    @Override // kotlinx.metadata.KmTypeVisitor
    public void visitEnd() {
        this.output.invoke(new KmType(this.flags, this.typeArguments, this.extendsBound, this.isExtensionType));
    }
}

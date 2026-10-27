package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import com.amazon.identity.auth.device.utils.AccountConstants;
import dagger.spi.shaded.androidx.room.compiler.processing.util.NamingUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.KmExtensionType;
import kotlinx.metadata.KmPropertyExtensionVisitor;
import kotlinx.metadata.KmPropertyVisitor;
import kotlinx.metadata.KmTypeVisitor;
import kotlinx.metadata.KmValueParameterVisitor;
import kotlinx.metadata.jvm.JvmFieldSignature;
import kotlinx.metadata.jvm.JvmMethodSignature;
import kotlinx.metadata.jvm.JvmPropertyExtensionVisitor;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J4\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u000bj\u0002`\f2\n\u0010\u0010\u001a\u00060\u000bj\u0002`\fH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/PropertyReader;", "Lkotlinx/metadata/KmClassVisitor;", "result", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmProperty;", "(Ljava/util/List;)V", "getResult", "()Ljava/util/List;", "visitProperty", "Lkotlinx/metadata/KmPropertyVisitor;", "flags", "", "Lkotlinx/metadata/Flags;", "name", "", "getterFlags", "setterFlags", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
final class PropertyReader extends KmClassVisitor {
    private final List<KmProperty> result;

    public final List<KmProperty> getResult() {
        return this.result;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertyReader(List<KmProperty> result) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(result, "result");
        this.result = result;
    }

    @Override // kotlinx.metadata.KmDeclarationContainerVisitor
    public KmPropertyVisitor visitProperty(int flags, String name, int getterFlags, int setterFlags) {
        Intrinsics.checkNotNullParameter(name, "name");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        return new AnonymousClass1(new Ref.ObjectRef(), new Ref.ObjectRef(), name, objectRef, flags);
    }

    /* JADX INFO: renamed from: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.PropertyReader$visitProperty$1, reason: invalid class name */
    /* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
    @Metadata(d1 = {"\u0000A\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0014\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00142\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"dagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/PropertyReader$visitProperty$1", "Lkotlinx/metadata/KmPropertyVisitor;", "returnType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getReturnType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "setReturnType", "(Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "visitEnd", "", "visitExtensions", "Lkotlinx/metadata/KmPropertyExtensionVisitor;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/metadata/KmExtensionType;", "visitReturnType", "Lkotlinx/metadata/KmTypeVisitor;", "flags", "", "Lkotlinx/metadata/Flags;", "visitSetterParameter", "Lkotlinx/metadata/KmValueParameterVisitor;", "name", "", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class AnonymousClass1 extends KmPropertyVisitor {
        final /* synthetic */ int $flags;
        final /* synthetic */ Ref.ObjectRef<JvmMethodSignature> $getter;
        final /* synthetic */ String $name;
        final /* synthetic */ Ref.ObjectRef<JvmMethodSignature> $setter;
        final /* synthetic */ Ref.ObjectRef<KmValueParameter> $setterParam;
        public KmType returnType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef<JvmMethodSignature> objectRef, Ref.ObjectRef<JvmMethodSignature> objectRef2, String str, Ref.ObjectRef<KmValueParameter> objectRef3, int i) {
            super(null, 1, null);
            this.$setter = objectRef;
            this.$getter = objectRef2;
            this.$name = str;
            this.$setterParam = objectRef3;
            this.$flags = i;
        }

        public final KmType getReturnType() {
            KmType kmType = this.returnType;
            if (kmType != null) {
                return kmType;
            }
            Intrinsics.throwUninitializedPropertyAccessException("returnType");
            return null;
        }

        public final void setReturnType(KmType kmType) {
            Intrinsics.checkNotNullParameter(kmType, "<set-?>");
            this.returnType = kmType;
        }

        @Override // kotlinx.metadata.KmPropertyVisitor
        public void visitEnd() {
            KmFunction kmFunction;
            List<KmProperty> result = PropertyReader.this.getResult();
            KmType returnType = getReturnType();
            JvmMethodSignature jvmMethodSignature = this.$setter.element;
            KmFunction kmFunction2 = null;
            if (jvmMethodSignature != null) {
                Ref.ObjectRef<KmValueParameter> objectRef = this.$setterParam;
                String str = this.$name;
                KmValueParameter kmValueParameter = objectRef.element;
                if (kmValueParameter == null) {
                    kmValueParameter = new KmValueParameter(NamingUtilsKt.sanitizeAsJavaParameterName("set-?", 0), getReturnType(), 0);
                }
                kmFunction = new KmFunction(jvmMethodSignature.getName(), JvmAbi.INSTANCE.computeSetterName(str), jvmMethodSignature.asString(), 0, CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsJVMKt.listOf(kmValueParameter), KotlinClassMetadataUtilsKt.KM_VOID_TYPE, null, true);
            } else {
                kmFunction = null;
            }
            JvmMethodSignature jvmMethodSignature2 = this.$getter.element;
            if (jvmMethodSignature2 != null) {
                kmFunction2 = new KmFunction(jvmMethodSignature2.getName(), JvmAbi.INSTANCE.computeGetterName(this.$name), jvmMethodSignature2.asString(), this.$flags, CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), getReturnType(), null, true);
            }
            result.add(new KmProperty(this.$name, returnType, kmFunction2, kmFunction));
        }

        @Override // kotlinx.metadata.KmPropertyVisitor
        public KmTypeVisitor visitReturnType(int flags) {
            return new TypeReader(flags, new Function1<KmType, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.PropertyReader$visitProperty$1$visitReturnType$1
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
                    this.this$0.setReturnType(it);
                }
            });
        }

        @Override // kotlinx.metadata.KmPropertyVisitor
        public KmValueParameterVisitor visitSetterParameter(int flags, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            final Ref.ObjectRef<KmValueParameter> objectRef = this.$setterParam;
            return new ValueParameterReader(name, flags, new Function1<KmValueParameter, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.PropertyReader$visitProperty$1$visitSetterParameter$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KmValueParameter kmValueParameter) {
                    invoke2(kmValueParameter);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KmValueParameter it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    objectRef.element = it;
                }
            });
        }

        @Override // kotlinx.metadata.KmPropertyVisitor
        public KmPropertyExtensionVisitor visitExtensions(KmExtensionType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (!Intrinsics.areEqual(type, JvmPropertyExtensionVisitor.TYPE)) {
                return null;
            }
            final Ref.ObjectRef<JvmMethodSignature> objectRef = this.$getter;
            final Ref.ObjectRef<JvmMethodSignature> objectRef2 = this.$setter;
            return new JvmPropertyExtensionVisitor() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.PropertyReader$visitProperty$1$visitExtensions$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null, 1, null);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlinx.metadata.jvm.JvmPropertyExtensionVisitor
                public void visit(int jvmFlags, JvmFieldSignature fieldSignature, JvmMethodSignature getterSignature, JvmMethodSignature setterSignature) {
                    objectRef.element = getterSignature;
                    objectRef2.element = setterSignature;
                }
            };
        }
    }
}

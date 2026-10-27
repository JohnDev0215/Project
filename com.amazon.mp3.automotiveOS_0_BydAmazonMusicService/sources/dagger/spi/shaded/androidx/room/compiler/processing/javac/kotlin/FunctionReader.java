package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import com.amazon.identity.auth.device.utils.AccountConstants;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.FunctionReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.KmExtensionType;
import kotlinx.metadata.KmFunctionExtensionVisitor;
import kotlinx.metadata.KmFunctionVisitor;
import kotlinx.metadata.KmTypeParameterVisitor;
import kotlinx.metadata.KmTypeVisitor;
import kotlinx.metadata.KmValueParameterVisitor;
import kotlinx.metadata.KmVariance;
import kotlinx.metadata.jvm.JvmFunctionExtensionVisitor;
import kotlinx.metadata.jvm.JvmMethodSignature;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u001c\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/FunctionReader;", "Lkotlinx/metadata/KmClassVisitor;", "result", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmFunction;", "(Ljava/util/List;)V", "getResult", "()Ljava/util/List;", "visitFunction", "Lkotlinx/metadata/KmFunctionVisitor;", "flags", "", "Lkotlinx/metadata/Flags;", "name", "", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
final class FunctionReader extends KmClassVisitor {
    private final List<KmFunction> result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionReader(List<KmFunction> result) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(result, "result");
        this.result = result;
    }

    public final List<KmFunction> getResult() {
        return this.result;
    }

    /* JADX INFO: renamed from: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.FunctionReader$visitFunction$1, reason: invalid class name */
    /* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
    @Metadata(d1 = {"\u0000k\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\u0010 \u001a\u00060!j\u0002`\"H\u0016J\u0014\u0010#\u001a\u00020\u001f2\n\u0010 \u001a\u00060!j\u0002`\"H\u0016J,\u0010$\u001a\u00020%2\n\u0010 \u001a\u00060!j\u0002`\"2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020*H\u0016J\u001c\u0010+\u001a\u00020,2\n\u0010 \u001a\u00060!j\u0002`\"2\u0006\u0010&\u001a\u00020'H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"dagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/FunctionReader$visitFunction$1", "Lkotlinx/metadata/KmFunctionVisitor;", "methodSignature", "Lkotlinx/metadata/jvm/JvmMethodSignature;", "getMethodSignature", "()Lkotlinx/metadata/jvm/JvmMethodSignature;", "setMethodSignature", "(Lkotlinx/metadata/jvm/JvmMethodSignature;)V", "parameters", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmValueParameter;", "getParameters", "()Ljava/util/List;", "receiverType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getReceiverType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "setReceiverType", "(Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "returnType", "getReturnType", "setReturnType", "typeParameters", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmTypeParameter;", "visitEnd", "", "visitExtensions", "Lkotlinx/metadata/KmFunctionExtensionVisitor;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/metadata/KmExtensionType;", "visitReceiverParameterType", "Lkotlinx/metadata/KmTypeVisitor;", "flags", "", "Lkotlinx/metadata/Flags;", "visitReturnType", "visitTypeParameter", "Lkotlinx/metadata/KmTypeParameterVisitor;", "name", "", "id", "variance", "Lkotlinx/metadata/KmVariance;", "visitValueParameter", "Lkotlinx/metadata/KmValueParameterVisitor;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class AnonymousClass1 extends KmFunctionVisitor {
        final /* synthetic */ int $flags;
        final /* synthetic */ String $name;
        public JvmMethodSignature methodSignature;
        private final List<KmValueParameter> parameters;
        private KmType receiverType;
        public KmType returnType;
        private final List<KmTypeParameter> typeParameters;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, int i) {
            super(null, 1, null);
            this.$name = str;
            this.$flags = i;
            this.typeParameters = new ArrayList();
            this.parameters = new ArrayList();
        }

        public final JvmMethodSignature getMethodSignature() {
            JvmMethodSignature jvmMethodSignature = this.methodSignature;
            if (jvmMethodSignature != null) {
                return jvmMethodSignature;
            }
            Intrinsics.throwUninitializedPropertyAccessException("methodSignature");
            return null;
        }

        public final void setMethodSignature(JvmMethodSignature jvmMethodSignature) {
            Intrinsics.checkNotNullParameter(jvmMethodSignature, "<set-?>");
            this.methodSignature = jvmMethodSignature;
        }

        public final List<KmValueParameter> getParameters() {
            return this.parameters;
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

        public final void setReceiverType(KmType kmType) {
            this.receiverType = kmType;
        }

        @Override // kotlinx.metadata.KmFunctionVisitor
        public KmTypeParameterVisitor visitTypeParameter(int flags, String name, int id, KmVariance variance) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(variance, "variance");
            return new TypeParameterReader(name, flags, new Function1<KmTypeParameter, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.FunctionReader$visitFunction$1$visitTypeParameter$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KmTypeParameter kmTypeParameter) {
                    invoke2(kmTypeParameter);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KmTypeParameter it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    this.this$0.typeParameters.add(it);
                }
            });
        }

        @Override // kotlinx.metadata.KmFunctionVisitor
        public KmValueParameterVisitor visitValueParameter(int flags, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new ValueParameterReader(name, flags, new Function1<KmValueParameter, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.FunctionReader$visitFunction$1$visitValueParameter$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KmValueParameter kmValueParameter) {
                    invoke2(kmValueParameter);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KmValueParameter it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    this.this$0.getParameters().add(it);
                }
            });
        }

        @Override // kotlinx.metadata.KmFunctionVisitor
        public KmTypeVisitor visitReceiverParameterType(int flags) {
            return new TypeReader(flags, new Function1<KmType, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.FunctionReader$visitFunction$1$visitReceiverParameterType$1
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
                    this.this$0.setReceiverType(it);
                }
            });
        }

        @Override // kotlinx.metadata.KmFunctionVisitor
        public KmFunctionExtensionVisitor visitExtensions(KmExtensionType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (!Intrinsics.areEqual(type, JvmFunctionExtensionVisitor.TYPE)) {
                throw new IllegalStateException(("Unsupported extension type: " + type).toString());
            }
            return new JvmFunctionExtensionVisitor() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.FunctionReader$visitFunction$1$visitExtensions$1
                {
                    super(null, 1, null);
                }

                @Override // kotlinx.metadata.jvm.JvmFunctionExtensionVisitor
                public void visit(JvmMethodSignature signature) {
                    FunctionReader.AnonymousClass1 anonymousClass1 = this.this$0;
                    Intrinsics.checkNotNull(signature);
                    anonymousClass1.setMethodSignature(signature);
                }
            };
        }

        @Override // kotlinx.metadata.KmFunctionVisitor
        public KmTypeVisitor visitReturnType(int flags) {
            return new TypeReader(flags, new Function1<KmType, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.FunctionReader$visitFunction$1$visitReturnType$1
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

        @Override // kotlinx.metadata.KmFunctionVisitor
        public void visitEnd() {
            List<KmFunction> result = FunctionReader.this.getResult();
            String name = getMethodSignature().getName();
            String strAsString = getMethodSignature().asString();
            List<KmTypeParameter> list = this.typeParameters;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((KmTypeParameter) it.next()).asKmType());
            }
            result.add(new KmFunction(name, this.$name, strAsString, this.$flags, arrayList, this.parameters, getReturnType(), this.receiverType, false, 256, null));
        }
    }

    @Override // kotlinx.metadata.KmDeclarationContainerVisitor
    public KmFunctionVisitor visitFunction(int flags, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new AnonymousClass1(name, flags);
    }
}

package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import com.amazon.identity.auth.device.utils.AccountConstants;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.ConstructorReader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.KmConstructorExtensionVisitor;
import kotlinx.metadata.KmConstructorVisitor;
import kotlinx.metadata.KmExtensionType;
import kotlinx.metadata.KmValueParameterVisitor;
import kotlinx.metadata.jvm.JvmConstructorExtensionVisitor;
import kotlinx.metadata.jvm.JvmMethodSignature;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/ConstructorReader;", "Lkotlinx/metadata/KmClassVisitor;", "result", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmConstructor;", "(Ljava/util/List;)V", "getResult", "()Ljava/util/List;", "visitConstructor", "Lkotlinx/metadata/KmConstructorVisitor;", "flags", "", "Lkotlinx/metadata/Flags;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
final class ConstructorReader extends KmClassVisitor {
    private final List<KmConstructor> result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstructorReader(List<KmConstructor> result) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(result, "result");
        this.result = result;
    }

    public final List<KmConstructor> getResult() {
        return this.result;
    }

    /* JADX INFO: renamed from: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.ConstructorReader$visitConstructor$1, reason: invalid class name */
    /* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
    @Metadata(d1 = {"\u0000C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\u0016j\u0002`\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"dagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/ConstructorReader$visitConstructor$1", "Lkotlinx/metadata/KmConstructorVisitor;", "descriptor", "", "getDescriptor", "()Ljava/lang/String;", "setDescriptor", "(Ljava/lang/String;)V", "parameters", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmValueParameter;", "getParameters", "()Ljava/util/List;", "visitEnd", "", "visitExtensions", "Lkotlinx/metadata/KmConstructorExtensionVisitor;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/metadata/KmExtensionType;", "visitValueParameter", "Lkotlinx/metadata/KmValueParameterVisitor;", "flags", "", "Lkotlinx/metadata/Flags;", "name", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class AnonymousClass1 extends KmConstructorVisitor {
        final /* synthetic */ int $flags;
        public String descriptor;
        private final List<KmValueParameter> parameters;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i) {
            super(null, 1, null);
            this.$flags = i;
            this.parameters = new ArrayList();
        }

        public final String getDescriptor() {
            String str = this.descriptor;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("descriptor");
            return null;
        }

        public final void setDescriptor(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.descriptor = str;
        }

        public final List<KmValueParameter> getParameters() {
            return this.parameters;
        }

        @Override // kotlinx.metadata.KmConstructorVisitor
        public KmValueParameterVisitor visitValueParameter(int flags, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new ValueParameterReader(name, flags, new Function1<KmValueParameter, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.ConstructorReader$visitConstructor$1$visitValueParameter$1
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

        @Override // kotlinx.metadata.KmConstructorVisitor
        public KmConstructorExtensionVisitor visitExtensions(KmExtensionType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (!Intrinsics.areEqual(type, JvmConstructorExtensionVisitor.TYPE)) {
                throw new IllegalStateException(("Unsupported extension type: " + type).toString());
            }
            return new JvmConstructorExtensionVisitor() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.ConstructorReader$visitConstructor$1$visitExtensions$1
                {
                    super(null, 1, null);
                }

                @Override // kotlinx.metadata.jvm.JvmConstructorExtensionVisitor
                public void visit(JvmMethodSignature signature) {
                    ConstructorReader.AnonymousClass1 anonymousClass1 = this.this$0;
                    Intrinsics.checkNotNull(signature);
                    anonymousClass1.setDescriptor(signature.asString());
                }
            };
        }

        @Override // kotlinx.metadata.KmConstructorVisitor
        public void visitEnd() {
            ConstructorReader.this.getResult().add(new KmConstructor(getDescriptor(), this.$flags, this.parameters));
        }
    }

    @Override // kotlinx.metadata.KmClassVisitor
    public KmConstructorVisitor visitConstructor(int flags) {
        return new AnonymousClass1(flags);
    }
}

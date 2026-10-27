package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmTypeVisitor;
import kotlinx.metadata.KmValueParameterVisitor;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003\u0012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\u0004\b\u001e\u0010\u001fJ\u0014\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R#\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00070\u00118\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\"\u0010\u0018\u001a\u00020\u00178\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/ValueParameterReader;", "Lkotlinx/metadata/KmValueParameterVisitor;", "", "Lkotlinx/metadata/Flags;", "flags", "Lkotlinx/metadata/KmTypeVisitor;", "visitType", "", "visitEnd", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "I", "getFlags", "()I", "Lkotlin/Function1;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmValueParameter;", "output", "Lkotlin/jvm/functions/Function1;", "getOutput", "()Lkotlin/jvm/functions/Function1;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "setType", "(Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "<init>", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
final class ValueParameterReader extends KmValueParameterVisitor {
    private final int flags;
    private final String name;
    private final Function1<KmValueParameter, Unit> output;
    public KmType type;

    public final int getFlags() {
        return this.flags;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ValueParameterReader(String name, int i, Function1<? super KmValueParameter, Unit> output) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(output, "output");
        this.name = name;
        this.flags = i;
        this.output = output;
    }

    public final KmType getType() {
        KmType kmType = this.type;
        if (kmType != null) {
            return kmType;
        }
        Intrinsics.throwUninitializedPropertyAccessException(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        return null;
    }

    public final void setType(KmType kmType) {
        Intrinsics.checkNotNullParameter(kmType, "<set-?>");
        this.type = kmType;
    }

    @Override // kotlinx.metadata.KmValueParameterVisitor
    public KmTypeVisitor visitType(int flags) {
        return new TypeReader(flags, new Function1<KmType, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.ValueParameterReader.visitType.1
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
                ValueParameterReader.this.setType(it);
            }
        });
    }

    @Override // kotlinx.metadata.KmValueParameterVisitor
    public void visitEnd() {
        this.output.invoke(new KmValueParameter(this.name, getType(), this.flags));
    }
}

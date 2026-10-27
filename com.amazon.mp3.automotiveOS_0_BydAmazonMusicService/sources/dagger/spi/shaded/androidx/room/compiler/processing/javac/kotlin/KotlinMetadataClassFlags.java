package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.metadata.Flag;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.jvm.KotlinClassMetadata;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0006\u0010\u0003\u001a\u00020\u0002R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\u000f\u001a\u00060\tj\u0002`\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KotlinMetadataClassFlags;", "", "", "isInterface", "Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;", "classMetadata", "Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;", "getClassMetadata", "()Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;", "", "Lkotlinx/metadata/Flags;", "flags$delegate", "Lkotlin/Lazy;", "getFlags", "()I", "flags", "<init>", "(Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class KotlinMetadataClassFlags {
    private final KotlinClassMetadata.Class classMetadata;

    /* JADX INFO: renamed from: flags$delegate, reason: from kotlin metadata */
    private final Lazy flags;

    public KotlinMetadataClassFlags(KotlinClassMetadata.Class classMetadata) {
        Intrinsics.checkNotNullParameter(classMetadata, "classMetadata");
        this.classMetadata = classMetadata;
        this.flags = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataClassFlags$flags$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                final Ref.IntRef intRef = new Ref.IntRef();
                this.this$0.getClassMetadata().accept(new KmClassVisitor() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataClassFlags$flags$2.1
                    {
                        super(null, 1, 0 == true ? 1 : 0);
                    }

                    @Override // kotlinx.metadata.KmClassVisitor
                    public void visit(int flags, String name) {
                        Intrinsics.checkNotNullParameter(name, "name");
                        intRef.element = flags;
                        super.visit(flags, name);
                    }
                });
                return Integer.valueOf(intRef.element);
            }
        });
    }

    public final KotlinClassMetadata.Class getClassMetadata() {
        return this.classMetadata;
    }

    private final int getFlags() {
        return ((Number) this.flags.getValue()).intValue();
    }

    public final boolean isInterface() {
        return Flag.Class.IS_INTERFACE.invoke(getFlags());
    }
}

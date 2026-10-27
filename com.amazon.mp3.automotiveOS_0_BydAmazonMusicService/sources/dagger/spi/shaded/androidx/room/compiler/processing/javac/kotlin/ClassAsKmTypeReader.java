package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.KmTypeParameterVisitor;
import kotlinx.metadata.KmTypeVisitor;
import kotlinx.metadata.KmVariance;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0004\b \u0010!J \u0010\t\u001a\u00020\b2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006H\u0016J,\u0010\u000e\u001a\u00020\r2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0014\u0010\u0010\u001a\u00020\u000f2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016R#\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u00128\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u0018R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/ClassAsKmTypeReader;", "Lkotlinx/metadata/KmClassVisitor;", "", "Lkotlinx/metadata/Flags;", "flags", "", "Lkotlinx/metadata/ClassName;", "name", "", "visit", "id", "Lkotlinx/metadata/KmVariance;", "variance", "Lkotlinx/metadata/KmTypeParameterVisitor;", "visitTypeParameter", "Lkotlinx/metadata/KmTypeVisitor;", "visitSupertype", "visitEnd", "Lkotlin/Function1;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmClassTypeInfo;", "output", "Lkotlin/jvm/functions/Function1;", "getOutput", "()Lkotlin/jvm/functions/Function1;", "I", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmTypeParameter;", "typeParameters", "Ljava/util/List;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "superType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class ClassAsKmTypeReader extends KmClassVisitor {
    private int flags;
    private final Function1<KmClassTypeInfo, Unit> output;
    private KmType superType;
    private final List<KmTypeParameter> typeParameters;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ClassAsKmTypeReader(Function1<? super KmClassTypeInfo, Unit> output) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(output, "output");
        this.output = output;
        this.typeParameters = new ArrayList();
    }

    @Override // kotlinx.metadata.KmClassVisitor
    public void visit(int flags, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.flags = flags;
    }

    @Override // kotlinx.metadata.KmClassVisitor
    public KmTypeParameterVisitor visitTypeParameter(int flags, String name, int id, KmVariance variance) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(variance, "variance");
        return new TypeParameterReader(name, flags, new Function1<KmTypeParameter, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.ClassAsKmTypeReader.visitTypeParameter.1
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
                ClassAsKmTypeReader.this.typeParameters.add(it);
            }
        });
    }

    @Override // kotlinx.metadata.KmClassVisitor
    public KmTypeVisitor visitSupertype(int flags) {
        return new TypeReader(flags, new Function1<KmType, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.ClassAsKmTypeReader.visitSupertype.1
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
                ClassAsKmTypeReader.this.superType = it;
            }
        });
    }

    @Override // kotlinx.metadata.KmClassVisitor
    public void visitEnd() {
        Function1<KmClassTypeInfo, Unit> function1 = this.output;
        int i = this.flags;
        List<KmTypeParameter> list = this.typeParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((KmTypeParameter) it.next()).asKmType());
        }
        function1.invoke(new KmClassTypeInfo(new KmType(i, arrayList, null, false), this.superType));
    }
}

package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.jvm.KotlinClassMetadata;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u0005H\u0000\u001a\u0012\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003*\u00020\u0005H\u0000\u001a\u0012\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003*\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"KM_VOID_TYPE", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "readConstructors", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmConstructor;", "Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;", "readFunctions", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmFunction;", "readProperties", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmProperty;", "room-compiler-processing"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class KotlinClassMetadataUtilsKt {
    private static final KmType KM_VOID_TYPE = new KmType(0, CollectionsKt__CollectionsKt.emptyList(), null, false);

    public static final List<KmFunction> readFunctions(KotlinClassMetadata.Class r2) {
        Intrinsics.checkNotNullParameter(r2, "<this>");
        ArrayList arrayList = new ArrayList();
        r2.accept(new FunctionReader(arrayList));
        return arrayList;
    }

    public static final List<KmConstructor> readConstructors(KotlinClassMetadata.Class r2) {
        Intrinsics.checkNotNullParameter(r2, "<this>");
        ArrayList arrayList = new ArrayList();
        r2.accept(new ConstructorReader(arrayList));
        return arrayList;
    }

    public static final List<KmProperty> readProperties(KotlinClassMetadata.Class r2) {
        Intrinsics.checkNotNullParameter(r2, "<this>");
        ArrayList arrayList = new ArrayList();
        r2.accept(new PropertyReader(arrayList));
        return arrayList;
    }
}

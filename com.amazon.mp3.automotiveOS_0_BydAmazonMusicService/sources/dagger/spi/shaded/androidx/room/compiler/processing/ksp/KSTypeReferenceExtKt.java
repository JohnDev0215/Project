package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KSTypeReferenceExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¨\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSType;", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "createTypeReference", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class KSTypeReferenceExtKt {
    public static final KSTypeReference createTypeReference(KSType kSType) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        return new NoLocationTypeReference(kSType);
    }
}

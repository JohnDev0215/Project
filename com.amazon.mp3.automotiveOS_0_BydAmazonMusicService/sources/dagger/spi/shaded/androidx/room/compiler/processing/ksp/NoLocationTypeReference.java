package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;

/* JADX INFO: compiled from: KSTypeReferenceExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/NoLocationTypeReference;", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "Lcom/google/devtools/ksp/symbol/KSType;", "resolve", "resolved", "Lcom/google/devtools/ksp/symbol/KSType;", "getResolved", "()Lcom/google/devtools/ksp/symbol/KSType;", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "getAnnotations", "()Lkotlin/sequences/Sequence;", "annotations", "<init>", "(Lcom/google/devtools/ksp/symbol/KSType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
final class NoLocationTypeReference implements KSTypeReference {
    private final KSType resolved;

    public NoLocationTypeReference(KSType resolved) {
        Intrinsics.checkNotNullParameter(resolved, "resolved");
        this.resolved = resolved;
    }

    @Override // com.google.devtools.ksp.symbol.KSAnnotated
    public Sequence<KSAnnotation> getAnnotations() {
        return SequencesKt__SequencesKt.emptySequence();
    }

    @Override // com.google.devtools.ksp.symbol.KSTypeReference
    /* JADX INFO: renamed from: resolve, reason: from getter */
    public KSType getResolved() {
        return this.resolved;
    }
}

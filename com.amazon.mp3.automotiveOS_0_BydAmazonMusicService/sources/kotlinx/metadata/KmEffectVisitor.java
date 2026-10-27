package kotlinx.metadata;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: visitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/metadata/KmEffectVisitor;", "", "delegate", "(Lkotlinx/metadata/KmEffectVisitor;)V", "visitConclusionOfConditionalEffect", "Lkotlinx/metadata/KmEffectExpressionVisitor;", "visitConstructorArgument", "visitEnd", "", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class KmEffectVisitor {
    private final KmEffectVisitor delegate;

    @JvmOverloads
    public KmEffectVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public KmEffectVisitor(KmEffectVisitor kmEffectVisitor) {
        this.delegate = kmEffectVisitor;
    }

    public /* synthetic */ KmEffectVisitor(KmEffectVisitor kmEffectVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : kmEffectVisitor);
    }

    public KmEffectExpressionVisitor visitConstructorArgument() {
        KmEffectVisitor kmEffectVisitor = this.delegate;
        if (kmEffectVisitor != null) {
            return kmEffectVisitor.visitConstructorArgument();
        }
        return null;
    }

    public KmEffectExpressionVisitor visitConclusionOfConditionalEffect() {
        KmEffectVisitor kmEffectVisitor = this.delegate;
        if (kmEffectVisitor != null) {
            return kmEffectVisitor.visitConclusionOfConditionalEffect();
        }
        return null;
    }

    public void visitEnd() {
        KmEffectVisitor kmEffectVisitor = this.delegate;
        if (kmEffectVisitor != null) {
            kmEffectVisitor.visitEnd();
        }
    }
}

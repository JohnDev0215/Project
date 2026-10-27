package kotlinx.metadata;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: visitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0018\u0010\u0019J.\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016J\u0016\u0010\r\u001a\u0004\u0018\u00010\f2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0016J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\f2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0016J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lkotlinx/metadata/KmTypeAliasVisitor;", "", "", "Lkotlinx/metadata/Flags;", "flags", "", "name", "id", "Lkotlinx/metadata/KmVariance;", "variance", "Lkotlinx/metadata/KmTypeParameterVisitor;", "visitTypeParameter", "Lkotlinx/metadata/KmTypeVisitor;", "visitUnderlyingType", "visitExpandedType", "Lkotlinx/metadata/KmAnnotation;", "annotation", "", "visitAnnotation", "Lkotlinx/metadata/KmVersionRequirementVisitor;", "visitVersionRequirement", "visitEnd", "delegate", "Lkotlinx/metadata/KmTypeAliasVisitor;", "<init>", "(Lkotlinx/metadata/KmTypeAliasVisitor;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
public abstract class KmTypeAliasVisitor {
    private final KmTypeAliasVisitor delegate;

    @JvmOverloads
    public KmTypeAliasVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public KmTypeAliasVisitor(KmTypeAliasVisitor kmTypeAliasVisitor) {
        this.delegate = kmTypeAliasVisitor;
    }

    public /* synthetic */ KmTypeAliasVisitor(KmTypeAliasVisitor kmTypeAliasVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : kmTypeAliasVisitor);
    }

    public KmTypeParameterVisitor visitTypeParameter(int flags, String name, int id, KmVariance variance) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(variance, "variance");
        KmTypeAliasVisitor kmTypeAliasVisitor = this.delegate;
        if (kmTypeAliasVisitor != null) {
            return kmTypeAliasVisitor.visitTypeParameter(flags, name, id, variance);
        }
        return null;
    }

    public KmTypeVisitor visitUnderlyingType(int flags) {
        KmTypeAliasVisitor kmTypeAliasVisitor = this.delegate;
        if (kmTypeAliasVisitor != null) {
            return kmTypeAliasVisitor.visitUnderlyingType(flags);
        }
        return null;
    }

    public KmTypeVisitor visitExpandedType(int flags) {
        KmTypeAliasVisitor kmTypeAliasVisitor = this.delegate;
        if (kmTypeAliasVisitor != null) {
            return kmTypeAliasVisitor.visitExpandedType(flags);
        }
        return null;
    }

    public void visitAnnotation(KmAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        KmTypeAliasVisitor kmTypeAliasVisitor = this.delegate;
        if (kmTypeAliasVisitor != null) {
            kmTypeAliasVisitor.visitAnnotation(annotation);
        }
    }

    public KmVersionRequirementVisitor visitVersionRequirement() {
        KmTypeAliasVisitor kmTypeAliasVisitor = this.delegate;
        if (kmTypeAliasVisitor != null) {
            return kmTypeAliasVisitor.visitVersionRequirement();
        }
        return null;
    }

    public void visitEnd() {
        KmTypeAliasVisitor kmTypeAliasVisitor = this.delegate;
        if (kmTypeAliasVisitor != null) {
            kmTypeAliasVisitor.visitEnd();
        }
    }
}

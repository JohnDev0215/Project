package kotlinx.metadata;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: visitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\u0003J1\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0005H\u0016J \u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/metadata/KmVersionRequirementVisitor;", "", "delegate", "(Lkotlinx/metadata/KmVersionRequirementVisitor;)V", "visit", "", "kind", "Lkotlinx/metadata/KmVersionRequirementVersionKind;", "level", "Lkotlinx/metadata/KmVersionRequirementLevel;", "errorCode", "", "message", "", "(Lkotlinx/metadata/KmVersionRequirementVersionKind;Lkotlinx/metadata/KmVersionRequirementLevel;Ljava/lang/Integer;Ljava/lang/String;)V", "visitEnd", "visitVersion", "major", "minor", "patch", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class KmVersionRequirementVisitor {
    private final KmVersionRequirementVisitor delegate;

    @JvmOverloads
    public KmVersionRequirementVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public KmVersionRequirementVisitor(KmVersionRequirementVisitor kmVersionRequirementVisitor) {
        this.delegate = kmVersionRequirementVisitor;
    }

    public /* synthetic */ KmVersionRequirementVisitor(KmVersionRequirementVisitor kmVersionRequirementVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : kmVersionRequirementVisitor);
    }

    public void visit(KmVersionRequirementVersionKind kind, KmVersionRequirementLevel level, Integer errorCode, String message) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(level, "level");
        KmVersionRequirementVisitor kmVersionRequirementVisitor = this.delegate;
        if (kmVersionRequirementVisitor != null) {
            kmVersionRequirementVisitor.visit(kind, level, errorCode, message);
        }
    }

    public void visitVersion(int major, int minor, int patch) {
        KmVersionRequirementVisitor kmVersionRequirementVisitor = this.delegate;
        if (kmVersionRequirementVisitor != null) {
            kmVersionRequirementVisitor.visitVersion(major, minor, patch);
        }
    }

    public void visitEnd() {
        KmVersionRequirementVisitor kmVersionRequirementVisitor = this.delegate;
        if (kmVersionRequirementVisitor != null) {
            kmVersionRequirementVisitor.visitEnd();
        }
    }
}

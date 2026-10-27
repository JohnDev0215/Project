package kotlinx.metadata;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: visitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00002\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016J\u001e\u0010\b\u001a\u0004\u0018\u00010\u00002\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0014\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u0004\u0018\u00010\u00002\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u000eH\u0016J\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00002\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016J\u0014\u0010\u0019\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0016J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0006H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lkotlinx/metadata/KmTypeVisitor;", "", "delegate", "(Lkotlinx/metadata/KmTypeVisitor;)V", "visitAbbreviatedType", "flags", "", "Lkotlinx/metadata/Flags;", "visitArgument", "variance", "Lkotlinx/metadata/KmVariance;", "visitClass", "", "name", "", "Lkotlinx/metadata/ClassName;", "visitEnd", "visitExtensions", "Lkotlinx/metadata/KmTypeExtensionVisitor;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/metadata/KmExtensionType;", "visitFlexibleTypeUpperBound", "typeFlexibilityId", "visitOuterType", "visitStarProjection", "visitTypeAlias", "visitTypeParameter", "id", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class KmTypeVisitor {
    private final KmTypeVisitor delegate;

    @JvmOverloads
    public KmTypeVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public KmTypeVisitor(KmTypeVisitor kmTypeVisitor) {
        this.delegate = kmTypeVisitor;
    }

    public /* synthetic */ KmTypeVisitor(KmTypeVisitor kmTypeVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : kmTypeVisitor);
    }

    public void visitClass(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            kmTypeVisitor.visitClass(name);
        }
    }

    public void visitTypeAlias(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            kmTypeVisitor.visitTypeAlias(name);
        }
    }

    public void visitTypeParameter(int id) {
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            kmTypeVisitor.visitTypeParameter(id);
        }
    }

    public KmTypeVisitor visitArgument(int flags, KmVariance variance) {
        Intrinsics.checkNotNullParameter(variance, "variance");
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            return kmTypeVisitor.visitArgument(flags, variance);
        }
        return null;
    }

    public void visitStarProjection() {
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            kmTypeVisitor.visitStarProjection();
        }
    }

    public KmTypeVisitor visitAbbreviatedType(int flags) {
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            return kmTypeVisitor.visitAbbreviatedType(flags);
        }
        return null;
    }

    public KmTypeVisitor visitOuterType(int flags) {
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            return kmTypeVisitor.visitOuterType(flags);
        }
        return null;
    }

    public KmTypeVisitor visitFlexibleTypeUpperBound(int flags, String typeFlexibilityId) {
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            return kmTypeVisitor.visitFlexibleTypeUpperBound(flags, typeFlexibilityId);
        }
        return null;
    }

    public KmTypeExtensionVisitor visitExtensions(KmExtensionType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            return kmTypeVisitor.visitExtensions(type);
        }
        return null;
    }

    public void visitEnd() {
        KmTypeVisitor kmTypeVisitor = this.delegate;
        if (kmTypeVisitor != null) {
            kmTypeVisitor.visitEnd();
        }
    }
}

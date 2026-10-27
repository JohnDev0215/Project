package kotlinx.metadata;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: visitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\u0003J \u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0016J\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0017J\b\u0010\u0013\u001a\u00020\u0007H\u0016J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00122\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0016J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\u001c\u001a\u00020\u00072\n\u0010\u000b\u001a\u00060\fj\u0002`\rH\u0016J\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u00122\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0016J.\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\u0010\b\u001a\u00060\tj\u0002`\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\"H\u0016J\n\u0010#\u001a\u0004\u0018\u00010$H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00008TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006%"}, d2 = {"Lkotlinx/metadata/KmClassVisitor;", "Lkotlinx/metadata/KmDeclarationContainerVisitor;", "delegate", "(Lkotlinx/metadata/KmClassVisitor;)V", "getDelegate", "()Lkotlinx/metadata/KmClassVisitor;", "visit", "", "flags", "", "Lkotlinx/metadata/Flags;", "name", "", "Lkotlinx/metadata/ClassName;", "visitCompanionObject", "visitConstructor", "Lkotlinx/metadata/KmConstructorVisitor;", "visitContextReceiverType", "Lkotlinx/metadata/KmTypeVisitor;", "visitEnd", "visitEnumEntry", "visitExtensions", "Lkotlinx/metadata/KmClassExtensionVisitor;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/metadata/KmExtensionType;", "visitInlineClassUnderlyingPropertyName", "visitInlineClassUnderlyingType", "visitNestedClass", "visitSealedSubclass", "visitSupertype", "visitTypeParameter", "Lkotlinx/metadata/KmTypeParameterVisitor;", "id", "variance", "Lkotlinx/metadata/KmVariance;", "visitVersionRequirement", "Lkotlinx/metadata/KmVersionRequirementVisitor;", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class KmClassVisitor extends KmDeclarationContainerVisitor {
    @JvmOverloads
    public KmClassVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public KmClassVisitor(KmClassVisitor kmClassVisitor) {
        super(kmClassVisitor);
    }

    public /* synthetic */ KmClassVisitor(KmClassVisitor kmClassVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : kmClassVisitor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.metadata.KmDeclarationContainerVisitor
    public KmClassVisitor getDelegate() {
        return (KmClassVisitor) super.getDelegate();
    }

    public void visit(int flags, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visit(flags, name);
        }
    }

    public KmTypeParameterVisitor visitTypeParameter(int flags, String name, int id, KmVariance variance) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(variance, "variance");
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitTypeParameter(flags, name, id, variance);
        }
        return null;
    }

    public KmTypeVisitor visitSupertype(int flags) {
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitSupertype(flags);
        }
        return null;
    }

    public KmConstructorVisitor visitConstructor(int flags) {
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitConstructor(flags);
        }
        return null;
    }

    public void visitCompanionObject(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitCompanionObject(name);
        }
    }

    public void visitNestedClass(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitNestedClass(name);
        }
    }

    public void visitEnumEntry(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitEnumEntry(name);
        }
    }

    public void visitSealedSubclass(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitSealedSubclass(name);
        }
    }

    public void visitInlineClassUnderlyingPropertyName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitInlineClassUnderlyingPropertyName(name);
        }
    }

    public KmTypeVisitor visitInlineClassUnderlyingType(int flags) {
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitInlineClassUnderlyingType(flags);
        }
        return null;
    }

    public KmTypeVisitor visitContextReceiverType(int flags) {
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitContextReceiverType(flags);
        }
        return null;
    }

    public KmVersionRequirementVisitor visitVersionRequirement() {
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitVersionRequirement();
        }
        return null;
    }

    public KmClassExtensionVisitor visitExtensions(KmExtensionType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitExtensions(type);
        }
        return null;
    }

    public void visitEnd() {
        KmClassVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitEnd();
        }
    }
}

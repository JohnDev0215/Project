package kotlinx.metadata;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: visitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0017J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0016J\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0016J.\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lkotlinx/metadata/KmFunctionVisitor;", "", "delegate", "(Lkotlinx/metadata/KmFunctionVisitor;)V", "visitContextReceiverType", "Lkotlinx/metadata/KmTypeVisitor;", "flags", "", "Lkotlinx/metadata/Flags;", "visitContract", "Lkotlinx/metadata/KmContractVisitor;", "visitEnd", "", "visitExtensions", "Lkotlinx/metadata/KmFunctionExtensionVisitor;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/metadata/KmExtensionType;", "visitReceiverParameterType", "visitReturnType", "visitTypeParameter", "Lkotlinx/metadata/KmTypeParameterVisitor;", "name", "", "id", "variance", "Lkotlinx/metadata/KmVariance;", "visitValueParameter", "Lkotlinx/metadata/KmValueParameterVisitor;", "visitVersionRequirement", "Lkotlinx/metadata/KmVersionRequirementVisitor;", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class KmFunctionVisitor {
    private final KmFunctionVisitor delegate;

    @JvmOverloads
    public KmFunctionVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public KmFunctionVisitor(KmFunctionVisitor kmFunctionVisitor) {
        this.delegate = kmFunctionVisitor;
    }

    public /* synthetic */ KmFunctionVisitor(KmFunctionVisitor kmFunctionVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : kmFunctionVisitor);
    }

    public KmTypeParameterVisitor visitTypeParameter(int flags, String name, int id, KmVariance variance) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(variance, "variance");
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            return kmFunctionVisitor.visitTypeParameter(flags, name, id, variance);
        }
        return null;
    }

    public KmTypeVisitor visitReceiverParameterType(int flags) {
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            return kmFunctionVisitor.visitReceiverParameterType(flags);
        }
        return null;
    }

    public KmTypeVisitor visitContextReceiverType(int flags) {
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            return kmFunctionVisitor.visitContextReceiverType(flags);
        }
        return null;
    }

    public KmValueParameterVisitor visitValueParameter(int flags, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            return kmFunctionVisitor.visitValueParameter(flags, name);
        }
        return null;
    }

    public KmTypeVisitor visitReturnType(int flags) {
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            return kmFunctionVisitor.visitReturnType(flags);
        }
        return null;
    }

    public KmVersionRequirementVisitor visitVersionRequirement() {
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            return kmFunctionVisitor.visitVersionRequirement();
        }
        return null;
    }

    public KmContractVisitor visitContract() {
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            return kmFunctionVisitor.visitContract();
        }
        return null;
    }

    public KmFunctionExtensionVisitor visitExtensions(KmExtensionType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            return kmFunctionVisitor.visitExtensions(type);
        }
        return null;
    }

    public void visitEnd() {
        KmFunctionVisitor kmFunctionVisitor = this.delegate;
        if (kmFunctionVisitor != null) {
            kmFunctionVisitor.visitEnd();
        }
    }
}

package kotlinx.metadata;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: visitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001e\u0010\b\u001a\u0004\u0018\u00010\u00072\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J6\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u00052\n\u0010\t\u001a\u00060\u0002j\u0002`\u00032\n\u0010\n\u001a\u00060\u0002j\u0002`\u0003H\u0016J\u001e\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00008\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lkotlinx/metadata/KmDeclarationContainerVisitor;", "", "", "Lkotlinx/metadata/Flags;", "flags", "", "name", "Lkotlinx/metadata/KmFunctionVisitor;", "visitFunction", "getterFlags", "setterFlags", "Lkotlinx/metadata/KmPropertyVisitor;", "visitProperty", "Lkotlinx/metadata/KmTypeAliasVisitor;", "visitTypeAlias", "delegate", "Lkotlinx/metadata/KmDeclarationContainerVisitor;", "getDelegate", "()Lkotlinx/metadata/KmDeclarationContainerVisitor;", "<init>", "(Lkotlinx/metadata/KmDeclarationContainerVisitor;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
public abstract class KmDeclarationContainerVisitor {
    private final KmDeclarationContainerVisitor delegate;

    @JvmOverloads
    public KmDeclarationContainerVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public KmDeclarationContainerVisitor(KmDeclarationContainerVisitor kmDeclarationContainerVisitor) {
        this.delegate = kmDeclarationContainerVisitor;
    }

    public /* synthetic */ KmDeclarationContainerVisitor(KmDeclarationContainerVisitor kmDeclarationContainerVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : kmDeclarationContainerVisitor);
    }

    protected KmDeclarationContainerVisitor getDelegate() {
        return this.delegate;
    }

    public KmFunctionVisitor visitFunction(int flags, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmDeclarationContainerVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitFunction(flags, name);
        }
        return null;
    }

    public KmPropertyVisitor visitProperty(int flags, String name, int getterFlags, int setterFlags) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmDeclarationContainerVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitProperty(flags, name, getterFlags, setterFlags);
        }
        return null;
    }

    public KmTypeAliasVisitor visitTypeAlias(int flags, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        KmDeclarationContainerVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitTypeAlias(flags, name);
        }
        return null;
    }
}

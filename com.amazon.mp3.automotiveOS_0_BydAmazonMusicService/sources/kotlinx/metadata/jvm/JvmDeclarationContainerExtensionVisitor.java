package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmPropertyVisitor;

/* JADX INFO: compiled from: jvmExtensionVisitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0011\u0010\u0012J6\u0010\n\u001a\u0004\u0018\u00010\t2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u00052\n\u0010\u0007\u001a\u00060\u0002j\u0002`\u00032\n\u0010\b\u001a\u00060\u0002j\u0002`\u0003H\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016R\u001c\u0010\r\u001a\u0004\u0018\u00010\u00008\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lkotlinx/metadata/jvm/JvmDeclarationContainerExtensionVisitor;", "", "", "Lkotlinx/metadata/Flags;", "flags", "", "name", "getterFlags", "setterFlags", "Lkotlinx/metadata/KmPropertyVisitor;", "visitLocalDelegatedProperty", "", "visitModuleName", "delegate", "Lkotlinx/metadata/jvm/JvmDeclarationContainerExtensionVisitor;", "getDelegate", "()Lkotlinx/metadata/jvm/JvmDeclarationContainerExtensionVisitor;", "<init>", "(Lkotlinx/metadata/jvm/JvmDeclarationContainerExtensionVisitor;)V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public abstract class JvmDeclarationContainerExtensionVisitor {
    private final JvmDeclarationContainerExtensionVisitor delegate;

    @JvmOverloads
    public JvmDeclarationContainerExtensionVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public JvmDeclarationContainerExtensionVisitor(JvmDeclarationContainerExtensionVisitor jvmDeclarationContainerExtensionVisitor) {
        this.delegate = jvmDeclarationContainerExtensionVisitor;
    }

    public /* synthetic */ JvmDeclarationContainerExtensionVisitor(JvmDeclarationContainerExtensionVisitor jvmDeclarationContainerExtensionVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : jvmDeclarationContainerExtensionVisitor);
    }

    protected JvmDeclarationContainerExtensionVisitor getDelegate() {
        return this.delegate;
    }

    public KmPropertyVisitor visitLocalDelegatedProperty(int flags, String name, int getterFlags, int setterFlags) {
        Intrinsics.checkNotNullParameter(name, "name");
        JvmDeclarationContainerExtensionVisitor delegate = getDelegate();
        if (delegate != null) {
            return delegate.visitLocalDelegatedProperty(flags, name, getterFlags, setterFlags);
        }
        return null;
    }

    public void visitModuleName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        JvmDeclarationContainerExtensionVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitModuleName(name);
        }
    }
}

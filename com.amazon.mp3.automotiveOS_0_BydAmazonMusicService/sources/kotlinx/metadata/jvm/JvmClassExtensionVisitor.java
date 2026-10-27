package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.metadata.KmClassExtensionVisitor;
import kotlinx.metadata.KmExtensionType;

/* JADX INFO: compiled from: jvmExtensionVisitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 \u00112\u00020\u00012\u00020\u0002:\u0001\u0011B\u0015\b\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0014\u0010\n\u001a\u00020\u00052\n\u0010\t\u001a\u00060\u0007j\u0002`\bH\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u00008TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lkotlinx/metadata/jvm/JvmClassExtensionVisitor;", "Lkotlinx/metadata/KmClassExtensionVisitor;", "Lkotlinx/metadata/jvm/JvmDeclarationContainerExtensionVisitor;", "", "internalName", "", "visitAnonymousObjectOriginName", "", "Lkotlinx/metadata/Flags;", "flags", "visitJvmFlags", "visitEnd", "getDelegate", "()Lkotlinx/metadata/jvm/JvmClassExtensionVisitor;", "delegate", "<init>", "(Lkotlinx/metadata/jvm/JvmClassExtensionVisitor;)V", "Companion", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public class JvmClassExtensionVisitor extends JvmDeclarationContainerExtensionVisitor implements KmClassExtensionVisitor {

    @JvmField
    public static final KmExtensionType TYPE = new KmExtensionType(Reflection.getOrCreateKotlinClass(JvmClassExtensionVisitor.class));

    @JvmOverloads
    public JvmClassExtensionVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ JvmClassExtensionVisitor(JvmClassExtensionVisitor jvmClassExtensionVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : jvmClassExtensionVisitor);
    }

    @JvmOverloads
    public JvmClassExtensionVisitor(JvmClassExtensionVisitor jvmClassExtensionVisitor) {
        super(jvmClassExtensionVisitor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.metadata.jvm.JvmDeclarationContainerExtensionVisitor
    public JvmClassExtensionVisitor getDelegate() {
        return (JvmClassExtensionVisitor) super.getDelegate();
    }

    public void visitAnonymousObjectOriginName(String internalName) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        JvmClassExtensionVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitAnonymousObjectOriginName(internalName);
        }
    }

    public void visitJvmFlags(int flags) {
        JvmClassExtensionVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitJvmFlags(flags);
        }
    }

    public void visitEnd() {
        JvmClassExtensionVisitor delegate = getDelegate();
        if (delegate != null) {
            delegate.visitEnd();
        }
    }
}

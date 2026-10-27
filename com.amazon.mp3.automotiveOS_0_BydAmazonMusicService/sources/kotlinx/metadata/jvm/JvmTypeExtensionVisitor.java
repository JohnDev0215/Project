package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.metadata.KmAnnotation;
import kotlinx.metadata.KmExtensionType;
import kotlinx.metadata.KmTypeExtensionVisitor;

/* JADX INFO: compiled from: jvmExtensionVisitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0015\b\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016R\u0016\u0010\n\u001a\u0004\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lkotlinx/metadata/jvm/JvmTypeExtensionVisitor;", "Lkotlinx/metadata/KmTypeExtensionVisitor;", "", "isRaw", "", "visit", "Lkotlinx/metadata/KmAnnotation;", "annotation", "visitAnnotation", "visitEnd", "delegate", "Lkotlinx/metadata/jvm/JvmTypeExtensionVisitor;", "<init>", "(Lkotlinx/metadata/jvm/JvmTypeExtensionVisitor;)V", "Companion", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public class JvmTypeExtensionVisitor implements KmTypeExtensionVisitor {

    @JvmField
    public static final KmExtensionType TYPE = new KmExtensionType(Reflection.getOrCreateKotlinClass(JvmTypeExtensionVisitor.class));
    private final JvmTypeExtensionVisitor delegate;

    @JvmOverloads
    public JvmTypeExtensionVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public JvmTypeExtensionVisitor(JvmTypeExtensionVisitor jvmTypeExtensionVisitor) {
        this.delegate = jvmTypeExtensionVisitor;
    }

    public /* synthetic */ JvmTypeExtensionVisitor(JvmTypeExtensionVisitor jvmTypeExtensionVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : jvmTypeExtensionVisitor);
    }

    public void visit(boolean isRaw) {
        JvmTypeExtensionVisitor jvmTypeExtensionVisitor = this.delegate;
        if (jvmTypeExtensionVisitor != null) {
            jvmTypeExtensionVisitor.visit(isRaw);
        }
    }

    public void visitAnnotation(KmAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        JvmTypeExtensionVisitor jvmTypeExtensionVisitor = this.delegate;
        if (jvmTypeExtensionVisitor != null) {
            jvmTypeExtensionVisitor.visitAnnotation(annotation);
        }
    }

    public void visitEnd() {
        JvmTypeExtensionVisitor jvmTypeExtensionVisitor = this.delegate;
        if (jvmTypeExtensionVisitor != null) {
            jvmTypeExtensionVisitor.visitEnd();
        }
    }
}

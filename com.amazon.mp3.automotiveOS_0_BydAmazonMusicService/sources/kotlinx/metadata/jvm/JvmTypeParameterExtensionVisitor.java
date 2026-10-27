package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.metadata.KmAnnotation;
import kotlinx.metadata.KmExtensionType;
import kotlinx.metadata.KmTypeParameterExtensionVisitor;

/* JADX INFO: compiled from: jvmExtensionVisitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0016\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0015\b\u0007\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lkotlinx/metadata/jvm/JvmTypeParameterExtensionVisitor;", "Lkotlinx/metadata/KmTypeParameterExtensionVisitor;", "Lkotlinx/metadata/KmAnnotation;", "annotation", "", "visitAnnotation", "visitEnd", "delegate", "Lkotlinx/metadata/jvm/JvmTypeParameterExtensionVisitor;", "<init>", "(Lkotlinx/metadata/jvm/JvmTypeParameterExtensionVisitor;)V", "Companion", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public class JvmTypeParameterExtensionVisitor implements KmTypeParameterExtensionVisitor {

    @JvmField
    public static final KmExtensionType TYPE = new KmExtensionType(Reflection.getOrCreateKotlinClass(JvmTypeParameterExtensionVisitor.class));
    private final JvmTypeParameterExtensionVisitor delegate;

    @JvmOverloads
    public JvmTypeParameterExtensionVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public JvmTypeParameterExtensionVisitor(JvmTypeParameterExtensionVisitor jvmTypeParameterExtensionVisitor) {
        this.delegate = jvmTypeParameterExtensionVisitor;
    }

    public /* synthetic */ JvmTypeParameterExtensionVisitor(JvmTypeParameterExtensionVisitor jvmTypeParameterExtensionVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : jvmTypeParameterExtensionVisitor);
    }

    public void visitAnnotation(KmAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        JvmTypeParameterExtensionVisitor jvmTypeParameterExtensionVisitor = this.delegate;
        if (jvmTypeParameterExtensionVisitor != null) {
            jvmTypeParameterExtensionVisitor.visitAnnotation(annotation);
        }
    }

    public void visitEnd() {
        JvmTypeParameterExtensionVisitor jvmTypeParameterExtensionVisitor = this.delegate;
        if (jvmTypeParameterExtensionVisitor != null) {
            jvmTypeParameterExtensionVisitor.visitEnd();
        }
    }
}

package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.metadata.KmExtensionType;
import kotlinx.metadata.KmFunctionExtensionVisitor;

/* JADX INFO: compiled from: jvmExtensionVisitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0015\b\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016R\u0016\u0010\n\u001a\u0004\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lkotlinx/metadata/jvm/JvmFunctionExtensionVisitor;", "Lkotlinx/metadata/KmFunctionExtensionVisitor;", "Lkotlinx/metadata/jvm/JvmMethodSignature;", "signature", "", "visit", "", "internalName", "visitLambdaClassOriginName", "visitEnd", "delegate", "Lkotlinx/metadata/jvm/JvmFunctionExtensionVisitor;", "<init>", "(Lkotlinx/metadata/jvm/JvmFunctionExtensionVisitor;)V", "Companion", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public class JvmFunctionExtensionVisitor implements KmFunctionExtensionVisitor {

    @JvmField
    public static final KmExtensionType TYPE = new KmExtensionType(Reflection.getOrCreateKotlinClass(JvmFunctionExtensionVisitor.class));
    private final JvmFunctionExtensionVisitor delegate;

    @JvmOverloads
    public JvmFunctionExtensionVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public JvmFunctionExtensionVisitor(JvmFunctionExtensionVisitor jvmFunctionExtensionVisitor) {
        this.delegate = jvmFunctionExtensionVisitor;
    }

    public /* synthetic */ JvmFunctionExtensionVisitor(JvmFunctionExtensionVisitor jvmFunctionExtensionVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : jvmFunctionExtensionVisitor);
    }

    public void visit(JvmMethodSignature signature) {
        JvmFunctionExtensionVisitor jvmFunctionExtensionVisitor = this.delegate;
        if (jvmFunctionExtensionVisitor != null) {
            jvmFunctionExtensionVisitor.visit(signature);
        }
    }

    public void visitLambdaClassOriginName(String internalName) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        JvmFunctionExtensionVisitor jvmFunctionExtensionVisitor = this.delegate;
        if (jvmFunctionExtensionVisitor != null) {
            jvmFunctionExtensionVisitor.visitLambdaClassOriginName(internalName);
        }
    }

    public void visitEnd() {
        JvmFunctionExtensionVisitor jvmFunctionExtensionVisitor = this.delegate;
        if (jvmFunctionExtensionVisitor != null) {
            jvmFunctionExtensionVisitor.visitEnd();
        }
    }
}

package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlinx.metadata.KmConstructorExtensionVisitor;
import kotlinx.metadata.KmExtensionType;

/* JADX INFO: compiled from: jvmExtensionVisitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0015\b\u0007\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lkotlinx/metadata/jvm/JvmConstructorExtensionVisitor;", "Lkotlinx/metadata/KmConstructorExtensionVisitor;", "Lkotlinx/metadata/jvm/JvmMethodSignature;", "signature", "", "visit", "delegate", "Lkotlinx/metadata/jvm/JvmConstructorExtensionVisitor;", "<init>", "(Lkotlinx/metadata/jvm/JvmConstructorExtensionVisitor;)V", "Companion", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public class JvmConstructorExtensionVisitor implements KmConstructorExtensionVisitor {

    @JvmField
    public static final KmExtensionType TYPE = new KmExtensionType(Reflection.getOrCreateKotlinClass(JvmConstructorExtensionVisitor.class));
    private final JvmConstructorExtensionVisitor delegate;

    @JvmOverloads
    public JvmConstructorExtensionVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public JvmConstructorExtensionVisitor(JvmConstructorExtensionVisitor jvmConstructorExtensionVisitor) {
        this.delegate = jvmConstructorExtensionVisitor;
    }

    public /* synthetic */ JvmConstructorExtensionVisitor(JvmConstructorExtensionVisitor jvmConstructorExtensionVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : jvmConstructorExtensionVisitor);
    }

    public void visit(JvmMethodSignature signature) {
        JvmConstructorExtensionVisitor jvmConstructorExtensionVisitor = this.delegate;
        if (jvmConstructorExtensionVisitor != null) {
            jvmConstructorExtensionVisitor.visit(signature);
        }
    }
}

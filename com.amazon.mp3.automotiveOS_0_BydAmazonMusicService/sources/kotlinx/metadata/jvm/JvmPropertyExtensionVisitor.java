package kotlinx.metadata.jvm;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlinx.metadata.KmExtensionType;
import kotlinx.metadata.KmPropertyExtensionVisitor;

/* JADX INFO: compiled from: jvmExtensionVisitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0012\u0010\u0013J2\u0010\u000b\u001a\u00020\n2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\u000b\u001a\u00020\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0007H\u0017J\u0012\u0010\r\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lkotlinx/metadata/jvm/JvmPropertyExtensionVisitor;", "Lkotlinx/metadata/KmPropertyExtensionVisitor;", "", "Lkotlinx/metadata/Flags;", "jvmFlags", "Lkotlinx/metadata/jvm/JvmFieldSignature;", "fieldSignature", "Lkotlinx/metadata/jvm/JvmMethodSignature;", "getterSignature", "setterSignature", "", "visit", "signature", "visitSyntheticMethodForAnnotations", "visitSyntheticMethodForDelegate", "visitEnd", "delegate", "Lkotlinx/metadata/jvm/JvmPropertyExtensionVisitor;", "<init>", "(Lkotlinx/metadata/jvm/JvmPropertyExtensionVisitor;)V", "Companion", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public class JvmPropertyExtensionVisitor implements KmPropertyExtensionVisitor {

    @JvmField
    public static final KmExtensionType TYPE = new KmExtensionType(Reflection.getOrCreateKotlinClass(JvmPropertyExtensionVisitor.class));
    private final JvmPropertyExtensionVisitor delegate;

    @JvmOverloads
    public JvmPropertyExtensionVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public JvmPropertyExtensionVisitor(JvmPropertyExtensionVisitor jvmPropertyExtensionVisitor) {
        this.delegate = jvmPropertyExtensionVisitor;
    }

    public /* synthetic */ JvmPropertyExtensionVisitor(JvmPropertyExtensionVisitor jvmPropertyExtensionVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : jvmPropertyExtensionVisitor);
    }

    public void visit(int jvmFlags, JvmFieldSignature fieldSignature, JvmMethodSignature getterSignature, JvmMethodSignature setterSignature) {
        JvmPropertyExtensionVisitor jvmPropertyExtensionVisitor = this.delegate;
        if (jvmPropertyExtensionVisitor != null) {
            jvmPropertyExtensionVisitor.visit(jvmFlags, fieldSignature, getterSignature, setterSignature);
        }
        visit(fieldSignature, getterSignature, setterSignature);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use visit(Flags, JvmFieldSignature?, JvmMethodSignature?, JvmMethodSignature?) instead.", replaceWith = @ReplaceWith(expression = "visit(flagsOf(), fieldSignature, getterSignature, setterSignature)", imports = {"kotlinx.metadata.flagsOf"}))
    public void visit(JvmFieldSignature fieldSignature, JvmMethodSignature getterSignature, JvmMethodSignature setterSignature) {
        JvmPropertyExtensionVisitor jvmPropertyExtensionVisitor = this.delegate;
        if (jvmPropertyExtensionVisitor != null) {
            jvmPropertyExtensionVisitor.visit(fieldSignature, getterSignature, setterSignature);
        }
    }

    public void visitSyntheticMethodForAnnotations(JvmMethodSignature signature) {
        JvmPropertyExtensionVisitor jvmPropertyExtensionVisitor = this.delegate;
        if (jvmPropertyExtensionVisitor != null) {
            jvmPropertyExtensionVisitor.visitSyntheticMethodForAnnotations(signature);
        }
    }

    public void visitSyntheticMethodForDelegate(JvmMethodSignature signature) {
        JvmPropertyExtensionVisitor jvmPropertyExtensionVisitor = this.delegate;
        if (jvmPropertyExtensionVisitor != null) {
            jvmPropertyExtensionVisitor.visitSyntheticMethodForDelegate(signature);
        }
    }

    public void visitEnd() {
        JvmPropertyExtensionVisitor jvmPropertyExtensionVisitor = this.delegate;
        if (jvmPropertyExtensionVisitor != null) {
            jvmPropertyExtensionVisitor.visitEnd();
        }
    }
}

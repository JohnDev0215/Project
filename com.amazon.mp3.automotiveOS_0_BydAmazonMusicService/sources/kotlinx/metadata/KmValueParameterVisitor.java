package kotlinx.metadata;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: visitors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0016J\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u0016\u0010\n\u001a\u0004\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lkotlinx/metadata/KmValueParameterVisitor;", "", "", "Lkotlinx/metadata/Flags;", "flags", "Lkotlinx/metadata/KmTypeVisitor;", "visitType", "visitVarargElementType", "", "visitEnd", "delegate", "Lkotlinx/metadata/KmValueParameterVisitor;", "<init>", "(Lkotlinx/metadata/KmValueParameterVisitor;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
public abstract class KmValueParameterVisitor {
    private final KmValueParameterVisitor delegate;

    @JvmOverloads
    public KmValueParameterVisitor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public KmValueParameterVisitor(KmValueParameterVisitor kmValueParameterVisitor) {
        this.delegate = kmValueParameterVisitor;
    }

    public /* synthetic */ KmValueParameterVisitor(KmValueParameterVisitor kmValueParameterVisitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : kmValueParameterVisitor);
    }

    public KmTypeVisitor visitType(int flags) {
        KmValueParameterVisitor kmValueParameterVisitor = this.delegate;
        if (kmValueParameterVisitor != null) {
            return kmValueParameterVisitor.visitType(flags);
        }
        return null;
    }

    public KmTypeVisitor visitVarargElementType(int flags) {
        KmValueParameterVisitor kmValueParameterVisitor = this.delegate;
        if (kmValueParameterVisitor != null) {
            return kmValueParameterVisitor.visitVarargElementType(flags);
        }
        return null;
    }

    public void visitEnd() {
        KmValueParameterVisitor kmValueParameterVisitor = this.delegate;
        if (kmValueParameterVisitor != null) {
            kmValueParameterVisitor.visitEnd();
        }
    }
}

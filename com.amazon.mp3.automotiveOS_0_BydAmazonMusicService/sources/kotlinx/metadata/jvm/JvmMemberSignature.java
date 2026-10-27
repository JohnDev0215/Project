package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: JvmMemberSignature.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0003\u001a\u00020\u0002H&J\u0006\u0010\u0004\u001a\u00020\u0002\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lkotlinx/metadata/jvm/JvmMemberSignature;", "", "", "asString", "toString", "<init>", "()V", "Lkotlinx/metadata/jvm/JvmFieldSignature;", "Lkotlinx/metadata/jvm/JvmMethodSignature;", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public abstract class JvmMemberSignature {
    public /* synthetic */ JvmMemberSignature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String asString();

    private JvmMemberSignature() {
    }

    public final String toString() {
        return asString();
    }
}

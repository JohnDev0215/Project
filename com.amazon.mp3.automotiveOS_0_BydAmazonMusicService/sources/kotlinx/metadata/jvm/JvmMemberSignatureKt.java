package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.jvm.deserialization.JvmMemberSignature;

/* JADX INFO: compiled from: JvmMemberSignature.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"wrapAsPublic", "Lkotlinx/metadata/jvm/JvmFieldSignature;", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmMemberSignature$Field;", "Lkotlinx/metadata/jvm/JvmMethodSignature;", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmMemberSignature$Method;", "kotlinx-metadata-jvm"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class JvmMemberSignatureKt {
    public static final JvmMethodSignature wrapAsPublic(JvmMemberSignature.Method method) {
        Intrinsics.checkNotNullParameter(method, "<this>");
        return new JvmMethodSignature(method.getName(), method.getDesc());
    }

    public static final JvmFieldSignature wrapAsPublic(JvmMemberSignature.Field field) {
        Intrinsics.checkNotNullParameter(field, "<this>");
        return new JvmFieldSignature(field.getName(), field.getDesc());
    }
}

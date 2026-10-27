package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KmTypeExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"nullability", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getNullability", "(Landroidx/room/compiler/processing/javac/kotlin/KmType;)Landroidx/room/compiler/processing/XNullability;", "room-compiler-processing"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class KmTypeExtKt {
    public static final XNullability getNullability(KmType kmType) {
        XNullability nullability;
        Intrinsics.checkNotNullParameter(kmType, "<this>");
        if (kmType.isNullable()) {
            return XNullability.NULLABLE;
        }
        KmType extendsBound = kmType.getExtendsBound();
        return (extendsBound == null || (nullability = getNullability(extendsBound)) == null) ? XNullability.NONNULL : nullability;
    }
}

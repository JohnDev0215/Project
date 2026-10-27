package dagger.spi.shaded.androidx.room.compiler.processing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0003\u001a\u00020\u0001*\u00020\u0000\u0082\u0002\u0010\n\u000e\b\u0000\u0012\u0002\u0018\u0000\u001a\u0006\u0010\u0000\"\u00020\u0002¨\u0006\u0004"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XArrayType;", "isArray", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class XTypeKt {
    public static final boolean isArray(XType xType) {
        Intrinsics.checkNotNullParameter(xType, "<this>");
        return xType instanceof XArrayType;
    }
}

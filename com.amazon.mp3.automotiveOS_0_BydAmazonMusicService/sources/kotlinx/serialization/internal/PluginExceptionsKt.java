package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: PluginExceptions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¨\u0006\u0007"}, d2 = {"", "seen", "goldenMask", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "", "throwMissingFieldException", "kotlinx-serialization-core"}, k = 2, mv = {1, 7, 1})
public final class PluginExceptionsKt {
    public static final void throwMissingFieldException(int i, int i2, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        ArrayList arrayList = new ArrayList();
        int i3 = (~i) & i2;
        for (int i4 = 0; i4 < 32; i4++) {
            if ((i3 & 1) != 0) {
                arrayList.add(descriptor.getElementName(i4));
            }
            i3 >>>= 1;
        }
        throw new MissingFieldException(arrayList, descriptor.getSerialName());
    }
}

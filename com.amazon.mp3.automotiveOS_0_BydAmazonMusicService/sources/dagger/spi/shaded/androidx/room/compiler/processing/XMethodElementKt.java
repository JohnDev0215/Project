package dagger.spi.shaded.androidx.room.compiler.processing;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XMethodElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"filterMethodsByConfig", "", "T", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "room-compiler-processing"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class XMethodElementKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends XMethodElement> List<T> filterMethodsByConfig(List<? extends T> list, XProcessingEnv env) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(env, "env");
        if (!env.getConfig().getExcludeMethodsWithInvalidJvmSourceNames()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((XMethodElement) obj).hasValidJvmSourceName()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}

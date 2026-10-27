package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Reflection;
import spi.e;

/* JADX INFO: loaded from: classes2.dex */
public final class ClassicTypeCheckerContextKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String errorMessage(Object obj) {
        StringBuilder sbK = e.k("ClassicTypeCheckerContext couldn't handle ");
        sbK.append(Reflection.getOrCreateKotlinClass(obj.getClass()));
        sbK.append(' ');
        sbK.append(obj);
        return sbK.toString();
    }
}

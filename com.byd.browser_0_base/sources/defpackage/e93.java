package defpackage;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: compiled from: ThreadStrictModeInterceptor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e93 {
    public final ArrayList a = new ArrayList();
    public Consumer<jo3> b;

    public final void a(final int i, String str) {
        String[] strArrSplit = str.split("#");
        final String str2 = strArrSplit[0];
        final String str3 = strArrSplit[1];
        this.a.add(new Function() { // from class: d93
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                jo3 jo3Var = (jo3) obj;
                int i2 = jo3Var.a;
                int i3 = i;
                if ((i2 & i3) != 0) {
                    for (StackTraceElement stackTraceElement : jo3Var.b) {
                        if (stackTraceElement.getClassName().equals(str2) && stackTraceElement.getMethodName().equals(str3)) {
                            return Integer.valueOf(i3);
                        }
                    }
                }
                return null;
            }
        });
    }
}

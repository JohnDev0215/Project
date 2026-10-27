package p;

import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class rl0 {
    public static volatile rl0 a;
    public static final rl0 b = new rl0();

    public rl0() {
        Collections.emptyMap();
    }

    public static rl0 a() {
        rl0 rl0Var = a;
        if (rl0Var == null) {
            synchronized (rl0.class) {
                try {
                    rl0Var = a;
                    if (rl0Var == null) {
                        Class cls = ql0.a;
                        rl0 rl0Var2 = null;
                        if (cls != null) {
                            try {
                                rl0Var2 = (rl0) cls.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
                            } catch (Exception unused) {
                            }
                        }
                        if (rl0Var2 == null) {
                            rl0Var2 = b;
                        }
                        a = rl0Var2;
                        rl0Var = rl0Var2;
                    }
                } finally {
                }
            }
        }
        return rl0Var;
    }
}

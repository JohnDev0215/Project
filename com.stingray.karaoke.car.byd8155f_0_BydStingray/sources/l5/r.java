package l5;

import java.lang.reflect.AccessibleObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f18664a;

    static {
        r pVar;
        if (AbstractC1781i.f18646a >= 9) {
            try {
                pVar = new p(AccessibleObject.class.getDeclaredMethod("canAccess", Object.class));
            } catch (NoSuchMethodException unused) {
                pVar = null;
            }
        } else {
            pVar = null;
        }
        if (pVar == null) {
            pVar = new q();
        }
        f18664a = pVar;
    }

    public abstract boolean a(Object obj, AccessibleObject accessibleObject);
}

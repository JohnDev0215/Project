package p;

import java.lang.reflect.Field;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes.dex */
public final class mo2 implements PrivilegedAction {
    public final /* synthetic */ Field a;

    public mo2(Field field) {
        this.a = field;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        this.a.setAccessible(true);
        return null;
    }
}

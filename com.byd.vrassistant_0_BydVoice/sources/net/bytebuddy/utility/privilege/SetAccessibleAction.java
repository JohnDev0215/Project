package net.bytebuddy.utility.privilege;

import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes3.dex */
public class SetAccessibleAction<T extends AccessibleObject> implements PrivilegedAction<T> {
    private final T accessibleObject;

    protected boolean canEqual(Object obj) {
        return obj instanceof SetAccessibleAction;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetAccessibleAction)) {
            return false;
        }
        SetAccessibleAction setAccessibleAction = (SetAccessibleAction) obj;
        if (!setAccessibleAction.canEqual(this)) {
            return false;
        }
        T t = this.accessibleObject;
        T t2 = setAccessibleAction.accessibleObject;
        return t != null ? t.equals(t2) : t2 == null;
    }

    public int hashCode() {
        T t = this.accessibleObject;
        return 59 + (t == null ? 43 : t.hashCode());
    }

    public SetAccessibleAction(T t) {
        this.accessibleObject = t;
    }

    @Override // java.security.PrivilegedAction
    public T run() {
        this.accessibleObject.setAccessible(true);
        return this.accessibleObject;
    }
}

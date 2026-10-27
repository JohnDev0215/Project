package defpackage;

import android.os.Process;
import java.util.HashMap;

/* JADX INFO: compiled from: UserDataHost.java */
/* JADX INFO: loaded from: classes.dex */
public final class mk3 {
    public final long a = Process.myTid();
    public HashMap<Class<? extends lk3>, lk3> b = new HashMap<>();

    public final void a() {
        if (this.a != Process.myTid()) {
            throw new IllegalStateException("UserData must only be used on a single thread.");
        }
        if (this.b == null) {
            throw new IllegalStateException("Operation is not allowed after destroy().");
        }
    }

    public final <T extends lk3> T b(Class<T> cls) {
        a();
        if (cls != null) {
            return cls.cast(this.b.get(cls));
        }
        throw new IllegalArgumentException("Neither key nor object of UserDataHost can be null.");
    }

    public final void c(Class cls) {
        a();
        if (!this.b.containsKey(cls)) {
            throw new IllegalStateException("UserData for the key is not present.");
        }
    }

    public final <T extends lk3> T d(Class<T> cls, T t) {
        a();
        if (!((cls == null || t == null) ? false : true)) {
            throw new IllegalArgumentException("Neither key nor object of UserDataHost can be null.");
        }
        this.b.put(cls, t);
        return (T) b(cls);
    }
}

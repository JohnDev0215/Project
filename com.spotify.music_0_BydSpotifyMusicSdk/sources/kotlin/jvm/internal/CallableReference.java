package kotlin.jvm.internal;

import java.io.Serializable;
import p.d81;
import p.jr1;
import p.r22;
import p.ts;

/* JADX INFO: loaded from: classes.dex */
public abstract class CallableReference implements d81, Serializable {
    public static final Object e = NoReceiver.c;
    public transient d81 c;
    private final boolean isTopLevel;
    private final String name;
    private final Class owner;
    protected final Object receiver;
    private final String signature;

    public static class NoReceiver implements Serializable {
        public static final NoReceiver c = new NoReceiver();

        private NoReceiver() {
        }

        private Object readResolve() {
            return c;
        }
    }

    public CallableReference(Object obj, Class cls, String str, String str2, boolean z) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z;
    }

    public abstract d81 a();

    public final String b() {
        return this.name;
    }

    public final ts c() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        if (!this.isTopLevel) {
            return r22.a(cls);
        }
        r22.a.getClass();
        return new jr1(cls);
    }

    public final String d() {
        return this.signature;
    }
}

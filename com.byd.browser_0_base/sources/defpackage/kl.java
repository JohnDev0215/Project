package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: CallableReference.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class kl implements v31, Serializable {
    public transient v31 e;
    public final Object f;
    public final Class g;
    public final String h;
    public final String i;
    public final boolean j;

    /* JADX INFO: compiled from: CallableReference.java */
    public static class a implements Serializable {
        public static final a e = new a();

        private Object readResolve() {
            return e;
        }
    }

    public kl(Object obj, Class cls, String str, String str2, boolean z) {
        this.f = obj;
        this.g = cls;
        this.h = str;
        this.i = str2;
        this.j = z;
    }

    public abstract y62 b();

    public final mt c() {
        Class cls = this.g;
        if (cls == null) {
            return null;
        }
        if (this.j) {
            fa2.a.getClass();
            return new yt1(cls);
        }
        fa2.a.getClass();
        return new nt(cls);
    }
}

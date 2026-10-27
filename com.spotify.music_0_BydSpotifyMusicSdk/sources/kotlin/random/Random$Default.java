package kotlin.random;

import java.io.Serializable;
import p.b02;

/* JADX INFO: loaded from: classes.dex */
public final class Random$Default extends b02 implements Serializable {

    public static final class Serialized implements Serializable {
        public static final Serialized c = new Serialized();
        private static final long serialVersionUID = 0;

        private Serialized() {
        }

        private final Object readResolve() {
            return b02.c;
        }
    }

    public /* synthetic */ Random$Default(int i) {
        this();
    }

    private final Object writeReplace() {
        return Serialized.c;
    }

    @Override // p.b02
    public final int a() {
        return b02.e.a();
    }

    private Random$Default() {
    }
}

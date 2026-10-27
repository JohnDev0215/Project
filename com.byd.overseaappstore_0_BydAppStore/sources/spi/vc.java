package spi;

import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;

/* JADX INFO: loaded from: classes2.dex */
public final class vc {
    public static final d a;
    public static final k4<Throwable> b;

    public static final class a implements spi.g {
        public final String toString() {
            return "EmptyAction";
        }
    }

    public static final class b implements k4<Object> {
        @Override // spi.k4
        public final void accept(Object obj) {
        }

        public final String toString() {
            return "EmptyConsumer";
        }
    }

    public static final class c {
    }

    public static final class d implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
        }

        public final String toString() {
            return "EmptyRunnable";
        }
    }

    public static final class e implements k4<Throwable> {
        @Override // spi.k4
        public final void accept(Throwable th) throws Throwable {
            vn.a(th);
        }
    }

    public static final class f implements el<Object> {
        @Override // spi.el
        public final boolean a() {
            return false;
        }
    }

    public static final class g implements uc<Object, Object> {
        @Override // spi.uc
        public final Object apply(Object obj) {
            return obj;
        }

        public final String toString() {
            return "IdentityFunction";
        }
    }

    public static final class h implements k4<ps> {
        @Override // spi.k4
        public final void accept(ps psVar) throws Throwable {
            psVar.request(Long.MAX_VALUE);
        }
    }

    public static final class i implements qs<Object> {
    }

    public static final class j implements k4<Throwable> {
        @Override // spi.k4
        public final void accept(Throwable th) throws Throwable {
            vn.a(new OnErrorNotImplementedException(th));
        }
    }

    public static final class k implements el<Object> {
        @Override // spi.el
        public final boolean a() {
            return true;
        }
    }

    static {
        new g();
        a = new d();
        new a();
        new b();
        new e();
        b = new j();
        new c();
        new k();
        new f();
        new i();
        new h();
    }

    private vc() {
        throw new IllegalStateException("No instances!");
    }
}

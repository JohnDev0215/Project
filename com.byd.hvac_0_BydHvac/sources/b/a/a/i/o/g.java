package b.a.a.i.o;

import android.os.Build;
import b.a.a.i.o.d;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d.f f950a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f951b;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements d.f {
        @Override // b.a.a.i.o.d.f
        public b.a.a.i.o.d createAnimator() {
            return new b.a.a.i.o.d(Build.VERSION.SDK_INT >= 12 ? new f() : new e());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class c implements b {
        public c() {
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class d implements b {
        public d() {
        }

        public /* synthetic */ d(a aVar) {
            this();
        }
    }

    static {
        a aVar = null;
        if (Build.VERSION.SDK_INT >= 21) {
            f951b = new d(aVar);
        } else {
            f951b = new c(aVar);
        }
    }

    public static b.a.a.i.o.d a() {
        return f950a.createAnimator();
    }
}

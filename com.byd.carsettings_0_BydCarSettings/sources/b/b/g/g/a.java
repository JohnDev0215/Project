package b.b.g.g;

import android.os.Handler;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.db.model.BaseDBModel;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: BaseDbHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a<M extends BaseDBModel> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ExecutorService f759a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.h.d f761c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f762d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f763e = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.h.a<M> f760b = new b.b.h.a<>();

    /* JADX INFO: renamed from: b.b.g.g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseDbHelper.java */
    public class RunnableC0041a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BaseDBModel f764a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b.b.h.c f765b;

        public RunnableC0041a(BaseDBModel baseDBModel, b.b.h.c cVar) {
            this.f764a = baseDBModel;
            this.f765b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.h.a<M> aVar = a.this.f760b;
            if (aVar == null) {
                return;
            }
            aVar.s((M) this.f764a, this.f765b);
        }
    }

    /* JADX INFO: compiled from: BaseDbHelper.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f767a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f768b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.b.h.c f769c;

        public b(String str, String str2, b.b.h.c cVar) {
            this.f767a = str;
            this.f768b = str2;
            this.f769c = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.h.a<M> aVar = a.this.f760b;
            if (aVar == null) {
                return;
            }
            aVar.e(this.f767a, this.f768b, this.f769c);
        }
    }

    /* JADX INFO: compiled from: BaseDbHelper.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Handler f771a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f772b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f773c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f774d;

        public c(Handler handler, int i, String str, boolean z) {
            this.f771a = handler;
            this.f772b = i;
            this.f773c = str;
            this.f774d = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.h.a<M> aVar = a.this.f760b;
            if (aVar == null) {
                return;
            }
            aVar.k(this.f771a, this.f772b, this.f773c, this.f774d);
        }
    }

    /* JADX INFO: compiled from: BaseDbHelper.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Handler f776a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f777b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f778c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f779d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String[] f780e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String[] f781f;

        public d(Handler handler, int i, String str, String str2, String[] strArr, String[] strArr2) {
            this.f776a = handler;
            this.f777b = i;
            this.f778c = str;
            this.f779d = str2;
            this.f780e = strArr;
            this.f781f = strArr2;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.h.a<M> aVar = a.this.f760b;
            if (aVar == null) {
                return;
            }
            aVar.o(this.f776a, this.f777b, this.f778c, this.f779d, this.f780e, this.f781f);
        }
    }

    /* JADX INFO: compiled from: BaseDbHelper.java */
    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f782a;

        public e(List list) {
            this.f782a = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.h.a<M> aVar = a.this.f760b;
            if (aVar == null) {
                return;
            }
            aVar.r(this.f782a);
        }
    }

    public static void a(Runnable runnable) {
        if (f759a == null) {
            f759a = Executors.newCachedThreadPool();
        }
        f759a.execute(runnable);
    }

    public void b(b.b.h.d dVar) {
        if (dVar == null) {
            return;
        }
        if (this.f760b == null) {
            this.f760b = new b.b.h.a<>();
        }
        dVar.k("123456");
        this.f761c = dVar;
        this.f760b.b(BydApp.a(), dVar);
        this.f762d = true;
    }

    public void c(b.b.h.c cVar) {
        b.b.h.a<M> aVar = this.f760b;
        if (aVar == null) {
            return;
        }
        aVar.d(cVar);
    }

    public void d(String str, String str2, b.b.h.c cVar) {
        a(new b(str, str2, cVar));
    }

    public void e(Handler handler, int i, String str, boolean z) {
        a(new c(handler, i, str, z));
    }

    public List<M> f(String str, String str2) {
        b.b.h.a<M> aVar = this.f760b;
        if (aVar == null) {
            return null;
        }
        return aVar.l(str, str2);
    }

    public int g() {
        b.b.h.a<M> aVar = this.f760b;
        if (aVar == null) {
            return 0;
        }
        return aVar.m();
    }

    public int h(String str, String str2) {
        b.b.h.a<M> aVar = this.f760b;
        if (aVar == null) {
            return 0;
        }
        return aVar.n(str, str2);
    }

    public void i(Handler handler, int i, String str, String str2, String[] strArr, String[] strArr2) {
        a(new d(handler, i, str, str2, strArr, strArr2));
    }

    public List<M> j(String str, String str2, String str3, boolean z, int i) {
        b.b.h.a<M> aVar = this.f760b;
        if (aVar == null) {
            return null;
        }
        return aVar.p(str, str2, str3, z, i);
    }

    public void k(List<M> list) {
        a(new e(list));
    }

    public void l(M m, b.b.h.c cVar) {
        a(new RunnableC0041a(m, cVar));
    }
}

package t3;

import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.EmptyResult;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: Subjecter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public l f8065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public h f8066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Handler f8067d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f8064a = new i();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ExecutorService f8068e = Executors.newFixedThreadPool(1, new a());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LinkedHashMap<f, e> f8069f = new LinkedHashMap<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<b> f8070g = new ArrayList<>();

    /* JADX INFO: compiled from: Subjecter.java */
    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f8071a = new AtomicInteger(1);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("NotifyThread-" + this.f8071a.getAndIncrement());
            return thread;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Object> f8073a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f8074b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f8075c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f8076d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f8077e;

        public String c() {
            return this.f8077e;
        }

        public int d() {
            return this.f8075c;
        }

        public String e() {
            return this.f8074b;
        }

        public int f() {
            return this.f8076d;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public interface c {
        boolean a(Object obj, String str, Object... objArr);
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public j f8078d;

        public d(j jVar) {
            this.f8078d = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (o.this.f8065b != null) {
                o.this.f8065b.a(this.f8078d);
            }
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c f8080a;
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Object> f8081a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f8082b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f8083c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f8084d;

        public Object a() {
            return this.f8081a.get();
        }

        public String b() {
            return this.f8082b;
        }

        public long c() {
            if (a() != null) {
                return this.f8083c;
            }
            return 0L;
        }

        public boolean d() {
            return a() != null && this.f8084d > this.f8083c;
        }
    }

    public o() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.f8067d = new Handler();
    }

    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2, types: [T] */
    public static <T> T d(Object[] objArr) {
        ?? r22;
        if (objArr.length == 0) {
            r22 = (T) new EmptyResult();
        } else {
            int length = objArr.length;
            r22 = objArr;
            if (length == 1) {
                r22 = (T) objArr[0];
            }
        }
        return ((??[OBJECT, ARRAY]) r22) instanceof ComplexData ? (T) d(((??[OBJECT, ARRAY]) r22).h()) : (T) r22;
    }

    public ArrayList<b> b(Object obj, String str) {
        ArrayList<b> arrayList = new ArrayList<>();
        for (b bVar : this.f8070g) {
            if (bVar.f8073a.get() == obj && (bVar.f8077e.equals(str) || ((obj instanceof IInterface) && bVar.f8077e.equals("__NOTIFY_MESSAGE__")))) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public LinkedHashMap<f, e> c() {
        LinkedHashMap<f, e> linkedHashMap;
        synchronized (o.class) {
            linkedHashMap = this.f8069f;
        }
        return linkedHashMap;
    }

    public <T> T e(j jVar) {
        l lVar = this.f8065b;
        if (lVar == null) {
            this.f8064a.a("notify message failed: MVC isn't init");
            return null;
        }
        if (jVar == null) {
            this.f8064a.a("notify message failed: inMessage is null");
            return null;
        }
        if (jVar.f8043e) {
            return (T) lVar.a(jVar);
        }
        this.f8068e.submit(new d(jVar));
        return null;
    }

    public void f(h hVar) {
        this.f8066c = hVar;
    }

    public void g(i iVar) {
        this.f8064a = iVar;
    }

    public void h(l lVar) {
        this.f8065b = lVar;
    }
}

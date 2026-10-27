package c.a.b;

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
    public l f3493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public h f3494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Handler f3495d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f3492a = new i();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ExecutorService f3496e = Executors.newFixedThreadPool(1, new a());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LinkedHashMap<f, e> f3497f = new LinkedHashMap<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<b> f3498g = new ArrayList<>();

    /* JADX INFO: compiled from: Subjecter.java */
    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f3499a = new AtomicInteger(1);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("NotifyThread-" + this.f3499a.getAndIncrement());
            return thread;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Object> f3501a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3502b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3503c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3504d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3505e;

        public String c() {
            return this.f3505e;
        }

        public int d() {
            return this.f3503c;
        }

        public String e() {
            return this.f3502b;
        }

        public int f() {
            return this.f3504d;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public interface c {
        boolean a(Object obj, String str, Object... objArr);
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public j f3506b;

        public d(j jVar) {
            this.f3506b = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (o.this.f3493b != null) {
                o.this.f3493b.a(this.f3506b);
            }
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c f3508a;
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Object> f3509a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3510b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f3511c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f3512d;

        public Object a() {
            return this.f3509a.get();
        }

        public String b() {
            return this.f3510b;
        }

        public long c() {
            if (a() != null) {
                return this.f3511c;
            }
            return 0L;
        }

        public boolean d() {
            return a() != null && this.f3512d > this.f3511c;
        }
    }

    public o() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.f3495d = new Handler();
    }

    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2, types: [T] */
    public static <T> T d(Object[] objArr) {
        ?? r2;
        if (objArr.length == 0) {
            r2 = (T) new EmptyResult();
        } else {
            int length = objArr.length;
            r2 = objArr;
            if (length == 1) {
                r2 = (T) objArr[0];
            }
        }
        return ((??[OBJECT, ARRAY]) r2) instanceof ComplexData ? (T) d(((??[OBJECT, ARRAY]) r2).l()) : (T) r2;
    }

    public ArrayList<b> b(Object obj, String str) {
        ArrayList<b> arrayList = new ArrayList<>();
        for (b bVar : this.f3498g) {
            if (bVar.f3501a.get() == obj && (bVar.f3505e.equals(str) || ((obj instanceof IInterface) && bVar.f3505e.equals("__NOTIFY_MESSAGE__")))) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public LinkedHashMap<f, e> c() {
        LinkedHashMap<f, e> linkedHashMap;
        synchronized (o.class) {
            linkedHashMap = this.f3497f;
        }
        return linkedHashMap;
    }

    public <T> T e(j jVar) {
        l lVar = this.f3493b;
        if (lVar == null) {
            this.f3492a.a("notify message failed: MVC isn't init");
            return null;
        }
        if (jVar == null) {
            this.f3492a.a("notify message failed: inMessage is null");
            return null;
        }
        if (jVar.f3471e) {
            return (T) lVar.a(jVar);
        }
        this.f3496e.submit(new d(jVar));
        return null;
    }

    public void f(h hVar) {
        this.f3494c = hVar;
    }

    public void g(i iVar) {
        this.f3492a = iVar;
    }

    public void h(l lVar) {
        this.f3493b = lVar;
    }
}

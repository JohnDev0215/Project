package b.a.c;

import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.EmptyResult;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: Subjecter.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public n f260b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f261c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Handler f262d;
    public f f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f259a = new k();
    public ExecutorService e = Executors.newFixedThreadPool(1, new a());
    public LinkedHashMap<g, e> g = new LinkedHashMap<>();
    public ArrayList<b> h = new ArrayList<>();

    /* JADX INFO: compiled from: Subjecter.java */
    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f263a = new AtomicInteger(1);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("NotifyThread-" + this.f263a.getAndIncrement());
            return thread;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Object> f265a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f266b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f267c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f268d;
        public String e;

        public b(WeakReference<Object> weakReference, String str, int i, int i2, String str2) {
            this.f265a = weakReference;
            this.f266b = str;
            this.f267c = i;
            this.f268d = i2;
            this.e = str2;
        }

        public String f() {
            return this.e;
        }

        public int g() {
            return this.f267c;
        }

        public String h() {
            return this.f266b;
        }

        public int i() {
            return this.f268d;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public interface c {
        boolean a(Object obj, String str, Object... objArr);
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public l f269b;

        public d(l lVar) {
            this.f269b = lVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (r.this.f260b != null) {
                r.this.f260b.a(this.f269b);
            }
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c f271a;

        public e a(c cVar) {
            this.f271a = cVar;
            return this;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class f implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f272b;

        public f(int i) {
            this.f272b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (r.class) {
                ArrayList<g> arrayList = new ArrayList();
                for (Map.Entry entry : r.this.g.entrySet()) {
                    g gVar = (g) entry.getKey();
                    Object objB = ((g) entry.getKey()).b();
                    if (!gVar.f() || objB == null) {
                        arrayList.add(entry.getKey());
                        r.this.n(objB);
                    }
                }
                for (g gVar2 : arrayList) {
                    r.this.g.remove(gVar2);
                    r.this.f259a.d("unregister " + r.this.k(this.f272b) + ": " + gVar2.d());
                }
            }
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Object> f274a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f276c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f277d = s.a();
        public long e = s.a();

        public g(Object obj) {
            this.f274a = new WeakReference<>(obj);
            this.f275b = obj.toString();
            this.f276c = a(obj);
        }

        public final String a(Object obj) {
            String name;
            String strSubstring;
            if (obj instanceof IInterface) {
                name = r.this.i((IInterface) obj);
                if (name == null) {
                    name = obj.getClass().getSimpleName();
                }
            } else {
                Class<?> cls = obj.getClass();
                name = cls.isAnonymousClass() ? cls.getName() : cls.getSimpleName();
            }
            int iLastIndexOf = this.f275b.lastIndexOf(64);
            if (iLastIndexOf != -1) {
                String str = this.f275b;
                strSubstring = str.substring(iLastIndexOf, str.length());
            } else {
                strSubstring = "";
            }
            return name + strSubstring;
        }

        public Object b() {
            return this.f274a.get();
        }

        public String c() {
            return this.f276c;
        }

        public String d() {
            return this.f275b;
        }

        public long e() {
            if (b() != null) {
                return this.f277d;
            }
            return 0L;
        }

        public boolean f() {
            return b() != null && this.e > this.f277d;
        }

        public void g() {
            if (b() == null || this.e <= this.f277d) {
                return;
            }
            this.f277d = s.a();
            r.this.f259a.d("makeInvalid: " + b());
        }

        public void h() {
            if (b() != null) {
                this.e = s.a();
                r.this.f259a.d("makeValid: " + b());
            }
        }
    }

    public r() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.f262d = new Handler();
    }

    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2, types: [T] */
    public static <T> T j(Object[] objArr) {
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
        return ((??[OBJECT, ARRAY]) r2) instanceof ComplexData ? (T) j(((??[OBJECT, ARRAY]) r2).c()) : (T) r2;
    }

    public final void e(Object obj, Class<?> cls) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        do {
            o(cls, obj, declaredMethods);
            for (Class<?> cls2 : cls.getInterfaces()) {
                o(cls2, obj, declaredMethods);
            }
            cls = cls.getSuperclass();
        } while (cls != null);
    }

    public final void f(Object obj, Class<?>... clsArr) {
        if (obj instanceof b.a.c.c) {
            e(obj, obj.getClass());
            return;
        }
        if (obj instanceof IInterface) {
            if (clsArr.length == 0) {
                e(obj, obj.getClass());
                return;
            }
            for (Class<?> cls : clsArr) {
                e(obj, cls);
            }
        }
    }

    public ArrayList<b> g(Object obj, String str) {
        ArrayList<b> arrayList = new ArrayList<>();
        for (b bVar : this.h) {
            if (bVar.f265a.get() == obj && (bVar.e.equals(str) || ((obj instanceof IInterface) && bVar.e.equals("__NOTIFY_MESSAGE__")))) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public LinkedHashMap<g, e> h() {
        LinkedHashMap<g, e> linkedHashMap;
        synchronized (r.class) {
            linkedHashMap = this.g;
        }
        return linkedHashMap;
    }

    public String i(IInterface iInterface) {
        return null;
    }

    public final String k(int i) {
        return i == 0 ? "model" : "view";
    }

    public <T> T l(l lVar) {
        n nVar = this.f260b;
        if (nVar == null) {
            this.f259a.a("notify message failed: MVC isn't init");
            return null;
        }
        if (lVar == null) {
            this.f259a.a("notify message failed: inMessage is null");
            return null;
        }
        if (lVar.e) {
            return (T) nVar.a(lVar);
        }
        this.e.submit(new d(lVar));
        return null;
    }

    public e m(int i, Object obj, Class<?>... clsArr) {
        synchronized (r.class) {
            for (Map.Entry<g, e> entry : this.g.entrySet()) {
                if (entry.getKey().b() == obj) {
                    entry.getKey().h();
                    return entry.getValue();
                }
            }
            e eVar = new e();
            this.g.put(new g(obj), eVar);
            f(obj, clsArr);
            this.f259a.d("register " + k(i) + ": " + obj);
            return eVar;
        }
    }

    public final void n(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.h) {
            if (bVar.f265a.get() == obj) {
                arrayList.add(bVar);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.h.remove((b) it.next());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o(java.lang.Class<?> r21, java.lang.Object r22, java.lang.reflect.Method[] r23) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.c.r.o(java.lang.Class, java.lang.Object, java.lang.reflect.Method[]):void");
    }

    public void p(j jVar) {
        this.f261c = jVar;
    }

    public void q(k kVar) {
        this.f259a = kVar;
    }

    public void r(n nVar) {
        this.f260b = nVar;
    }

    public void s(int i, Object obj) {
        f fVar = this.f;
        if (fVar != null) {
            this.f262d.removeCallbacks(fVar);
        }
        synchronized (r.class) {
            Iterator<Map.Entry<g, e>> it = this.g.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<g, e> next = it.next();
                if (next.getKey().b() == obj) {
                    next.getKey().g();
                    break;
                }
            }
        }
        if (this.f == null) {
            this.f = new f(i);
        }
        this.f262d.postDelayed(this.f, 50L);
    }
}

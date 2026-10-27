package b.b.l;

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
    public n f2018b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f2019c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Handler f2020d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f f2022f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f2017a = new k();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ExecutorService f2021e = Executors.newFixedThreadPool(1, new a());
    public LinkedHashMap<g, e> g = new LinkedHashMap<>();
    public ArrayList<b> h = new ArrayList<>();

    /* JADX INFO: compiled from: Subjecter.java */
    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f2023a = new AtomicInteger(1);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("NotifyThread-" + this.f2023a.getAndIncrement());
            return thread;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Object> f2025a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2026b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2027c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2028d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f2029e;

        public b(WeakReference<Object> weakReference, String str, int i, int i2, String str2) {
            this.f2025a = weakReference;
            this.f2026b = str;
            this.f2027c = i;
            this.f2028d = i2;
            this.f2029e = str2;
        }

        public String f() {
            return this.f2029e;
        }

        public int g() {
            return this.f2027c;
        }

        public String h() {
            return this.f2026b;
        }

        public int i() {
            return this.f2028d;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public interface c {
        boolean a(Object obj, String str, Object... objArr);
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public l f2031a;

        public d(l lVar) {
            this.f2031a = lVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (r.this.f2018b != null) {
                r.this.f2018b.a(this.f2031a);
            }
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c f2033a;

        public e a(c cVar) {
            this.f2033a = cVar;
            return this;
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2034a;

        public f(int i) {
            this.f2034a = i;
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
                    r.this.f2017a.d("unregister " + r.this.k(this.f2034a) + ": " + gVar2.d());
                }
            }
        }
    }

    /* JADX INFO: compiled from: Subjecter.java */
    public class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Object> f2036a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2037b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f2038c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f2039d = s.a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f2040e = s.a();

        public g(Object obj) {
            this.f2036a = new WeakReference<>(obj);
            this.f2037b = obj.toString();
            this.f2038c = a(obj);
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
            int iLastIndexOf = this.f2037b.lastIndexOf(64);
            if (iLastIndexOf != -1) {
                String str = this.f2037b;
                strSubstring = str.substring(iLastIndexOf, str.length());
            } else {
                strSubstring = "";
            }
            return name + strSubstring;
        }

        public Object b() {
            return this.f2036a.get();
        }

        public String c() {
            return this.f2038c;
        }

        public String d() {
            return this.f2037b;
        }

        public long e() {
            if (b() != null) {
                return this.f2039d;
            }
            return 0L;
        }

        public boolean f() {
            return b() != null && this.f2040e > this.f2039d;
        }

        public void g() {
            if (b() == null || this.f2040e <= this.f2039d) {
                return;
            }
            this.f2039d = s.a();
            r.this.f2017a.d("makeInvalid: " + b());
        }

        public void h() {
            if (b() != null) {
                this.f2040e = s.a();
                r.this.f2017a.d("makeValid: " + b());
            }
        }
    }

    public r() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.f2020d = new Handler();
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
        if (obj instanceof b.b.l.c) {
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
            if (bVar.f2025a.get() == obj && (bVar.f2029e.equals(str) || ((obj instanceof IInterface) && bVar.f2029e.equals("__NOTIFY_MESSAGE__")))) {
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
        n nVar = this.f2018b;
        if (nVar == null) {
            this.f2017a.a("notify message failed: MVC isn't init");
            return null;
        }
        if (lVar == null) {
            this.f2017a.a("notify message failed: inMessage is null");
            return null;
        }
        if (lVar.f1998e) {
            return (T) nVar.a(lVar);
        }
        this.f2021e.submit(new d(lVar));
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
            this.f2017a.d("register " + k(i) + ": " + obj);
            return eVar;
        }
    }

    public final void n(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.h) {
            if (bVar.f2025a.get() == obj) {
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
        throw new UnsupportedOperationException("Method not decompiled: b.b.l.r.o(java.lang.Class, java.lang.Object, java.lang.reflect.Method[]):void");
    }

    public void p(j jVar) {
        this.f2019c = jVar;
    }

    public void q(k kVar) {
        this.f2017a = kVar;
    }

    public void r(n nVar) {
        this.f2018b = nVar;
    }

    public void s(int i, Object obj) {
        f fVar = this.f2022f;
        if (fVar != null) {
            this.f2020d.removeCallbacks(fVar);
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
        if (this.f2022f == null) {
            this.f2022f = new f(i);
        }
        this.f2020d.postDelayed(this.f2022f, 50L);
    }
}

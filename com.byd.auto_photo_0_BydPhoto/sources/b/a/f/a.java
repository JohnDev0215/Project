package b.a.f;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f463a;
    private Handler e = new HandlerC0015a(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ConcurrentHashMap<Class<?>, List<c>> f464b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<Object, List<Class<?>>> f465c = new HashMap<>();
    private ExecutorService d = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: b.a.f.a$a, reason: collision with other inner class name */
    class HandlerC0015a extends Handler {
        HandlerC0015a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            Object[] objArr = (Object[]) message.obj;
            ((c) objArr[0]).a(objArr[1]);
        }
    }

    private class b<T> implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private T f467a;

        b(T t) {
            this.f467a = t;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (c cVar : (List) a.this.f464b.get(this.f467a.getClass())) {
                int i = cVar.d;
                if (i == 1) {
                    a.this.e.obtainMessage(1, new Object[]{cVar, this.f467a}).sendToTarget();
                } else if (i == 2) {
                    cVar.a(this.f467a);
                }
            }
        }
    }

    private a() {
    }

    private void c(Object obj, Class<?> cls, List<c> list) {
        b.a.f.b bVar;
        for (Method method : cls.getMethods()) {
            if (Void.TYPE.equals(method.getReturnType()) && (method.getModifiers() & 1032) == 0) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && (bVar = (b.a.f.b) method.getAnnotation(b.a.f.b.class)) != null) {
                    list.add(new c(obj, method, parameterTypes[0], bVar.threadMode()));
                }
            }
        }
    }

    private List<c> d(Object obj) {
        Class<?> cls = obj.getClass();
        LinkedList linkedList = new LinkedList();
        c(obj, cls, linkedList);
        return linkedList;
    }

    public static a e() {
        if (f463a == null) {
            synchronized (a.class) {
                if (f463a == null) {
                    f463a = new a();
                }
            }
        }
        return f463a;
    }

    private void h(Object obj, c cVar) {
        Class<?> cls = cVar.f471c;
        List<c> linkedList = this.f464b.get(cls);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            this.f464b.put(cls, linkedList);
        }
        linkedList.add(cVar);
        List<Class<?>> linkedList2 = this.f465c.get(obj);
        if (linkedList2 == null) {
            linkedList2 = new LinkedList<>();
            this.f465c.put(obj, linkedList2);
        }
        linkedList2.add(cls);
    }

    public <T> void f(T t) {
        if (t != null && this.f464b.containsKey(t.getClass())) {
            this.d.submit(new b(t));
        }
    }

    public void g(Object obj) {
        List<c> listD = d(obj);
        synchronized (this) {
            Iterator<c> it = listD.iterator();
            while (it.hasNext()) {
                h(obj, it.next());
            }
        }
    }

    public void i(Object obj) {
        List<Class<?>> list = this.f465c.get(obj);
        if (list == null) {
            return;
        }
        Iterator<Class<?>> it = list.iterator();
        while (it.hasNext()) {
            List<c> list2 = this.f464b.get(it.next());
            if (list2 != null) {
                Iterator<c> it2 = list2.iterator();
                while (it2.hasNext()) {
                    if (obj.equals(it2.next().f469a)) {
                        it2.remove();
                    }
                }
            }
        }
        this.f465c.remove(obj);
    }
}

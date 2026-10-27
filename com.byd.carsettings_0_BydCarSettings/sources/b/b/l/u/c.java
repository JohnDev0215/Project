package b.b.l.u;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.rpc.GCMsg;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: RpcBase.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c implements Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicLong f2049a = new AtomicLong();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Object[] f2050b = new Object[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f2051c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Handler f2052d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2053e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e f2054f;
    public String g;
    public ArrayList<b> h = new ArrayList<>();
    public ArrayList<Object> i = new ArrayList<>();

    /* JADX INFO: compiled from: RpcBase.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f2055a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<Long> f2056b = new ArrayList<>();

        public a(Object obj) {
            this.f2055a = obj;
        }

        public String toString() {
            return "ObjectIdList: " + this.f2055a + c.f(this.f2056b, ", idList");
        }
    }

    /* JADX INFO: compiled from: RpcBase.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2057a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f2058b = new ArrayList<>();

        public b(int i) {
            this.f2057a = i;
        }

        public String toString() {
            return "PidObjectIdList: " + this.f2057a + c.f(this.f2058b, ", objectIdLists");
        }
    }

    public c() {
        String string = toString();
        this.g = string.substring(string.lastIndexOf(".") + 1);
        this.f2054f = new e(this);
    }

    public static String d(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (objArr != null) {
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                sb.append(obj == null ? "null" : obj.toString());
                sb.append(", ");
            }
            int iLastIndexOf = sb.lastIndexOf(", ");
            if (iLastIndexOf > 0) {
                sb.delete(iLastIndexOf, iLastIndexOf + 2);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String e(Class<?>[] clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (clsArr != null) {
            int length = clsArr.length;
            for (int i = 0; i < length; i++) {
                Class<?> cls = clsArr[i];
                sb.append(cls == null ? "null" : cls.getName());
                sb.append(", ");
            }
            int iLastIndexOf = sb.lastIndexOf(", ");
            if (iLastIndexOf > 0) {
                sb.delete(iLastIndexOf, iLastIndexOf + 2);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static <E> String f(List<E> list, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str + "[");
        if (list != null) {
            int size = list.size();
            if (size != 0) {
                stringBuffer.append(list.get(0).toString());
            }
            for (int i = 1; i < size; i++) {
                stringBuffer.append(", ");
                stringBuffer.append(list.get(i).toString());
            }
        } else {
            stringBuffer.append("empty list");
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public static long h() {
        return System.currentTimeMillis() + f2049a.incrementAndGet();
    }

    public static boolean p(Method method) {
        return method.getName().equals("equals") && method.getParameterTypes().length == 1 && method.getParameterTypes()[0] == Object.class;
    }

    public static boolean q(Method method) {
        return method.getName().equals("hashCode") && method.getParameterTypes().length == 0;
    }

    public static boolean r(Method method) {
        return method.getName().equals("toString") && method.getParameterTypes().length == 0;
    }

    public void b(int i, long j, Object obj) {
        synchronized (this.h) {
            ArrayList<Long> arrayListJ = j(l(o(i, true), obj));
            if (!arrayListJ.contains(Long.valueOf(j))) {
                arrayListJ.add(Long.valueOf(j));
                if (f.b()) {
                    f.d(this.g + " add object: pid " + i + ", id " + j + ", " + obj);
                }
            }
        }
    }

    public boolean c(int i, long j, String str) {
        synchronized (this.h) {
            if (c.a.a.a.a.c.c(str)) {
                Iterator<Object> it = this.i.iterator();
                while (it.hasNext()) {
                    b(i, j, it.next());
                }
                return true;
            }
            boolean z = false;
            for (Object obj : this.i) {
                if (obj.getClass().getName().equals(str)) {
                    b(i, j, obj);
                    z = true;
                }
            }
            return z;
        }
    }

    public String g() {
        return this.g + " " + f(this.h, "mPidObjectIdLists");
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    public String i() {
        return this.f2053e;
    }

    public final ArrayList<Long> j(a aVar) {
        return aVar.f2056b;
    }

    public long k(int i, Object obj) {
        synchronized (this.h) {
            b bVarO = o(i, false);
            if (bVarO != null) {
                for (a aVar : bVarO.f2058b) {
                    if (aVar.f2056b.size() > 0 && aVar.f2055a == obj) {
                        return aVar.f2056b.get(0).longValue();
                    }
                }
            }
            return -1L;
        }
    }

    public final a l(b bVar, Object obj) {
        ArrayList<a> arrayList = bVar.f2058b;
        for (a aVar : arrayList) {
            if (aVar.f2055a == obj) {
                return aVar;
            }
        }
        a aVar2 = new a(obj);
        arrayList.add(aVar2);
        return aVar2;
    }

    public ArrayList<Object> m(int i) {
        ArrayList<Object> arrayList;
        synchronized (this.h) {
            arrayList = new ArrayList<>();
            b bVarO = o(i, false);
            if (bVarO != null) {
                Iterator<a> it = bVarO.f2058b.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().f2055a);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Object> n(int i, long j) {
        ArrayList<Object> arrayList;
        synchronized (this.h) {
            arrayList = new ArrayList<>();
            b bVarO = o(i, false);
            if (bVarO != null) {
                for (a aVar : bVarO.f2058b) {
                    if (aVar.f2056b.contains(Long.valueOf(j))) {
                        arrayList.add(aVar.f2055a);
                    }
                }
            }
        }
        return arrayList;
    }

    public final b o(int i, boolean z) {
        for (b bVar : this.h) {
            if (bVar.f2057a == i) {
                return bVar;
            }
        }
        if (!z) {
            return null;
        }
        b bVar2 = new b(i);
        this.h.add(bVar2);
        return bVar2;
    }

    public void s(GCMsg gCMsg) {
        Iterator<Long> it = gCMsg.q().iterator();
        while (it.hasNext()) {
            u(it.next().longValue());
        }
    }

    public void t(Object obj) {
        synchronized (this.h) {
            if (!this.i.contains(obj)) {
                this.i.add(obj);
                if (f.b()) {
                    f.d(this.g + " register object: " + obj);
                }
            }
        }
    }

    public void u(long j) {
        synchronized (this.h) {
            ArrayList<b> arrayList = new ArrayList();
            for (b bVar : this.h) {
                ArrayList<a> arrayList2 = new ArrayList();
                ArrayList<a> arrayList3 = bVar.f2058b;
                for (a aVar : arrayList3) {
                    aVar.f2056b.remove(Long.valueOf(j));
                    if (f.b()) {
                        f.d(this.g + " remove object: pid " + bVar.f2057a + ", id " + j + ", " + aVar.f2055a);
                    }
                    if (aVar.f2056b.size() == 0) {
                        arrayList2.add(aVar);
                    }
                }
                for (a aVar2 : arrayList2) {
                    arrayList2.remove(aVar2);
                    if (f.b()) {
                        f.d(this.g + " remove objectIdList: pid " + bVar.f2057a + ", " + aVar2.f2055a);
                    }
                }
                if (arrayList3.size() == 0) {
                    arrayList.add(bVar);
                }
            }
            for (b bVar2 : arrayList) {
                this.h.remove(bVar2);
                if (f.b()) {
                    f.d(this.g + " remove pidObjectIdList: pid " + bVar2.f2057a);
                }
            }
        }
    }

    public void v(int i) {
        synchronized (this.h) {
            ArrayList<b> arrayList = new ArrayList();
            Iterator<b> it = this.h.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                b next = it.next();
                if (next.f2057a == i) {
                    arrayList.add(next);
                    break;
                }
            }
            for (b bVar : arrayList) {
                this.h.remove(bVar);
                if (f.b()) {
                    f.d(this.g + " remove pidObjectIdList: pid " + bVar.f2057a);
                }
            }
        }
    }

    public synchronized c w(Context context) {
        this.f2051c = context;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            this.f2051c = applicationContext;
        }
        ComplexData.i(this.f2051c.getClassLoader());
        this.f2053e = this.f2051c.getPackageName();
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (this.f2052d == null) {
            this.f2052d = new Handler(this);
        }
        return this;
    }

    public void x(Object obj) {
        synchronized (this.h) {
            this.i.remove(obj);
            if (f.b()) {
                f.d(this.g + " unregister object: " + obj);
            }
        }
    }

    public void y() {
        synchronized (this.h) {
            this.i.clear();
        }
    }
}

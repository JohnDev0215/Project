package c.a.b.r;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.byd.mvcadapter.rpc.GCMsg;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: RpcBase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Handler.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static AtomicLong f3514b = new AtomicLong();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object[] f3515c = new Object[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f3516d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3517e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f3518f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f3519g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<C0073b> f3520h = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<Object> f3521i = new ArrayList<>();

    /* JADX INFO: compiled from: RpcBase.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f3522a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<Long> f3523b = new ArrayList<>();

        public a(Object obj) {
            this.f3522a = obj;
        }

        public String toString() {
            return "ObjectIdList: " + this.f3522a + b.g(this.f3523b, ", idList");
        }
    }

    /* JADX INFO: renamed from: c.a.b.r.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: RpcBase.java */
    public static class C0073b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3524a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f3525b = new ArrayList<>();

        public C0073b(int i2) {
            this.f3524a = i2;
        }

        public String toString() {
            return "PidObjectIdList: " + this.f3524a + b.g(this.f3525b, ", objectIdLists");
        }
    }

    public b() {
        String string = toString();
        this.f3519g = string.substring(string.lastIndexOf(".") + 1);
        this.f3518f = new c(this);
    }

    public static String e(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (objArr != null) {
            int length = objArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                Object obj = objArr[i2];
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

    public static String f(Class<?>[] clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (clsArr != null) {
            int length = clsArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                Class<?> cls = clsArr[i2];
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

    public static <E> String g(List<E> list, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str + "[");
        if (list != null) {
            int size = list.size();
            if (size != 0) {
                stringBuffer.append(list.get(0).toString());
            }
            for (int i2 = 1; i2 < size; i2++) {
                stringBuffer.append(", ");
                stringBuffer.append(list.get(i2).toString());
            }
        } else {
            stringBuffer.append("empty list");
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public static long h() {
        return System.currentTimeMillis() + f3514b.incrementAndGet();
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

    public void c(int i2, long j, Object obj) {
        synchronized (this.f3520h) {
            ArrayList<Long> arrayListJ = j(l(o(i2, true), obj));
            if (!arrayListJ.contains(Long.valueOf(j))) {
                arrayListJ.add(Long.valueOf(j));
                if (d.a()) {
                    d.c(this.f3519g + " add object: pid " + i2 + ", id " + j + ", " + obj);
                }
            }
        }
    }

    public boolean d(int i2, long j, String str) {
        synchronized (this.f3520h) {
            if (d.a.a.a.a.c.c(str)) {
                Iterator<Object> it = this.f3521i.iterator();
                while (it.hasNext()) {
                    c(i2, j, it.next());
                }
                return true;
            }
            boolean z = false;
            for (Object obj : this.f3521i) {
                if (obj.getClass().getName().equals(str)) {
                    c(i2, j, obj);
                    z = true;
                }
            }
            return z;
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    public String i() {
        return this.f3517e;
    }

    public final ArrayList<Long> j(a aVar) {
        return aVar.f3523b;
    }

    public long k(int i2, Object obj) {
        synchronized (this.f3520h) {
            C0073b c0073bO = o(i2, false);
            if (c0073bO != null) {
                for (a aVar : c0073bO.f3525b) {
                    if (aVar.f3523b.size() > 0 && aVar.f3522a == obj) {
                        return aVar.f3523b.get(0).longValue();
                    }
                }
            }
            return -1L;
        }
    }

    public final a l(C0073b c0073b, Object obj) {
        ArrayList<a> arrayList = c0073b.f3525b;
        for (a aVar : arrayList) {
            if (aVar.f3522a == obj) {
                return aVar;
            }
        }
        a aVar2 = new a(obj);
        arrayList.add(aVar2);
        return aVar2;
    }

    public ArrayList<Object> m(int i2) {
        ArrayList<Object> arrayList;
        synchronized (this.f3520h) {
            arrayList = new ArrayList<>();
            C0073b c0073bO = o(i2, false);
            if (c0073bO != null) {
                Iterator<a> it = c0073bO.f3525b.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().f3522a);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Object> n(int i2, long j) {
        ArrayList<Object> arrayList;
        synchronized (this.f3520h) {
            arrayList = new ArrayList<>();
            C0073b c0073bO = o(i2, false);
            if (c0073bO != null) {
                for (a aVar : c0073bO.f3525b) {
                    if (aVar.f3523b.contains(Long.valueOf(j))) {
                        arrayList.add(aVar.f3522a);
                    }
                }
            }
        }
        return arrayList;
    }

    public final C0073b o(int i2, boolean z) {
        for (C0073b c0073b : this.f3520h) {
            if (c0073b.f3524a == i2) {
                return c0073b;
            }
        }
        if (!z) {
            return null;
        }
        C0073b c0073b2 = new C0073b(i2);
        this.f3520h.add(c0073b2);
        return c0073b2;
    }

    public void s(GCMsg gCMsg) {
        Iterator<Long> it = gCMsg.v().iterator();
        while (it.hasNext()) {
            t(it.next().longValue());
        }
    }

    public void t(long j) {
        synchronized (this.f3520h) {
            ArrayList<C0073b> arrayList = new ArrayList();
            for (C0073b c0073b : this.f3520h) {
                ArrayList<a> arrayList2 = new ArrayList();
                ArrayList<a> arrayList3 = c0073b.f3525b;
                for (a aVar : arrayList3) {
                    aVar.f3523b.remove(Long.valueOf(j));
                    if (d.a()) {
                        d.c(this.f3519g + " remove object: pid " + c0073b.f3524a + ", id " + j + ", " + aVar.f3522a);
                    }
                    if (aVar.f3523b.size() == 0) {
                        arrayList2.add(aVar);
                    }
                }
                for (a aVar2 : arrayList2) {
                    arrayList2.remove(aVar2);
                    if (d.a()) {
                        d.c(this.f3519g + " remove objectIdList: pid " + c0073b.f3524a + ", " + aVar2.f3522a);
                    }
                }
                if (arrayList3.size() == 0) {
                    arrayList.add(c0073b);
                }
            }
            for (C0073b c0073b2 : arrayList) {
                this.f3520h.remove(c0073b2);
                if (d.a()) {
                    d.c(this.f3519g + " remove pidObjectIdList: pid " + c0073b2.f3524a);
                }
            }
        }
    }

    public void u(int i2) {
        synchronized (this.f3520h) {
            ArrayList<C0073b> arrayList = new ArrayList();
            Iterator<C0073b> it = this.f3520h.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C0073b next = it.next();
                if (next.f3524a == i2) {
                    arrayList.add(next);
                    break;
                }
            }
            for (C0073b c0073b : arrayList) {
                this.f3520h.remove(c0073b);
                if (d.a()) {
                    d.c(this.f3519g + " remove pidObjectIdList: pid " + c0073b.f3524a);
                }
            }
        }
    }
}

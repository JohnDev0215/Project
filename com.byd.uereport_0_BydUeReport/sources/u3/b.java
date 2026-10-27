package u3;

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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static AtomicLong f8149d = new AtomicLong();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Object[] f8150e = new Object[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f8151f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f8152g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c f8153h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f8154i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList<C0127b> f8155j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<Object> f8156k = new ArrayList<>();

    /* JADX INFO: compiled from: RpcBase.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f8157a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<Long> f8158b = new ArrayList<>();

        public a(Object obj) {
            this.f8157a = obj;
        }

        public String toString() {
            return "ObjectIdList: " + this.f8157a + b.g(this.f8158b, ", idList");
        }
    }

    /* JADX INFO: renamed from: u3.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: RpcBase.java */
    public static class C0127b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8159a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f8160b = new ArrayList<>();

        public C0127b(int i8) {
            this.f8159a = i8;
        }

        public String toString() {
            return "PidObjectIdList: " + this.f8159a + b.g(this.f8160b, ", objectIdLists");
        }
    }

    public b() {
        String string = toString();
        this.f8154i = string.substring(string.lastIndexOf(".") + 1);
        this.f8153h = new c(this);
    }

    public static String e(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (objArr != null) {
            int length = objArr.length;
            for (int i8 = 0; i8 < length; i8++) {
                Object obj = objArr[i8];
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
            for (int i8 = 0; i8 < length; i8++) {
                Class<?> cls = clsArr[i8];
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
            for (int i8 = 1; i8 < size; i8++) {
                stringBuffer.append(", ");
                stringBuffer.append(list.get(i8).toString());
            }
        } else {
            stringBuffer.append("empty list");
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public static long h() {
        return System.currentTimeMillis() + f8149d.incrementAndGet();
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

    public void c(int i8, long j8, Object obj) {
        synchronized (this.f8155j) {
            ArrayList<Long> arrayListJ = j(l(o(i8, true), obj));
            if (!arrayListJ.contains(Long.valueOf(j8))) {
                arrayListJ.add(Long.valueOf(j8));
                if (d.a()) {
                    d.c(this.f8154i + " add object: pid " + i8 + ", id " + j8 + ", " + obj);
                }
            }
        }
    }

    public boolean d(int i8, long j8, String str) {
        synchronized (this.f8155j) {
            if (c7.c.c(str)) {
                Iterator<Object> it = this.f8156k.iterator();
                while (it.hasNext()) {
                    c(i8, j8, it.next());
                }
                return true;
            }
            boolean z7 = false;
            for (Object obj : this.f8156k) {
                if (obj.getClass().getName().equals(str)) {
                    c(i8, j8, obj);
                    z7 = true;
                }
            }
            return z7;
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    public String i() {
        return this.f8152g;
    }

    public final ArrayList<Long> j(a aVar) {
        return aVar.f8158b;
    }

    public long k(int i8, Object obj) {
        synchronized (this.f8155j) {
            C0127b c0127bO = o(i8, false);
            if (c0127bO != null) {
                for (a aVar : c0127bO.f8160b) {
                    if (aVar.f8158b.size() > 0 && aVar.f8157a == obj) {
                        return aVar.f8158b.get(0).longValue();
                    }
                }
            }
            return -1L;
        }
    }

    public final a l(C0127b c0127b, Object obj) {
        ArrayList<a> arrayList = c0127b.f8160b;
        for (a aVar : arrayList) {
            if (aVar.f8157a == obj) {
                return aVar;
            }
        }
        a aVar2 = new a(obj);
        arrayList.add(aVar2);
        return aVar2;
    }

    public ArrayList<Object> m(int i8) {
        ArrayList<Object> arrayList;
        synchronized (this.f8155j) {
            arrayList = new ArrayList<>();
            C0127b c0127bO = o(i8, false);
            if (c0127bO != null) {
                Iterator<a> it = c0127bO.f8160b.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().f8157a);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Object> n(int i8, long j8) {
        ArrayList<Object> arrayList;
        synchronized (this.f8155j) {
            arrayList = new ArrayList<>();
            C0127b c0127bO = o(i8, false);
            if (c0127bO != null) {
                for (a aVar : c0127bO.f8160b) {
                    if (aVar.f8158b.contains(Long.valueOf(j8))) {
                        arrayList.add(aVar.f8157a);
                    }
                }
            }
        }
        return arrayList;
    }

    public final C0127b o(int i8, boolean z7) {
        for (C0127b c0127b : this.f8155j) {
            if (c0127b.f8159a == i8) {
                return c0127b;
            }
        }
        if (!z7) {
            return null;
        }
        C0127b c0127b2 = new C0127b(i8);
        this.f8155j.add(c0127b2);
        return c0127b2;
    }

    public void s(GCMsg gCMsg) {
        Iterator<Long> it = gCMsg.r().iterator();
        while (it.hasNext()) {
            t(it.next().longValue());
        }
    }

    public void t(long j8) {
        synchronized (this.f8155j) {
            ArrayList<C0127b> arrayList = new ArrayList();
            for (C0127b c0127b : this.f8155j) {
                ArrayList<a> arrayList2 = new ArrayList();
                ArrayList<a> arrayList3 = c0127b.f8160b;
                for (a aVar : arrayList3) {
                    aVar.f8158b.remove(Long.valueOf(j8));
                    if (d.a()) {
                        d.c(this.f8154i + " remove object: pid " + c0127b.f8159a + ", id " + j8 + ", " + aVar.f8157a);
                    }
                    if (aVar.f8158b.size() == 0) {
                        arrayList2.add(aVar);
                    }
                }
                for (a aVar2 : arrayList2) {
                    arrayList2.remove(aVar2);
                    if (d.a()) {
                        d.c(this.f8154i + " remove objectIdList: pid " + c0127b.f8159a + ", " + aVar2.f8157a);
                    }
                }
                if (arrayList3.size() == 0) {
                    arrayList.add(c0127b);
                }
            }
            for (C0127b c0127b2 : arrayList) {
                this.f8155j.remove(c0127b2);
                if (d.a()) {
                    d.c(this.f8154i + " remove pidObjectIdList: pid " + c0127b2.f8159a);
                }
            }
        }
    }

    public void u(int i8) {
        synchronized (this.f8155j) {
            ArrayList<C0127b> arrayList = new ArrayList();
            Iterator<C0127b> it = this.f8155j.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C0127b next = it.next();
                if (next.f8159a == i8) {
                    arrayList.add(next);
                    break;
                }
            }
            for (C0127b c0127b : arrayList) {
                this.f8155j.remove(c0127b);
                if (d.a()) {
                    d.c(this.f8154i + " remove pidObjectIdList: pid " + c0127b.f8159a);
                }
            }
        }
    }
}

package c.a.b;

import android.util.Pair;
import android.util.SparseArray;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.GsonData;
import com.byd.mvcadapter.refect.RefUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GsonUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.b.b.d f3449a = new c.b.b.d();

    public static <T> T a(String str, Class<T> cls, Class<?>[] clsArr, ClassLoader classLoader) {
        if (clsArr != null) {
            try {
                if (clsArr.length > 0) {
                    return (T) f3449a.j(str, c.b.b.s.a.c(cls, clsArr).f());
                }
            } catch (RuntimeException e2) {
                e2.printStackTrace();
                return null;
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        }
        return (T) f3449a.i(str, cls);
    }

    public static String b(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return f3449a.r(obj);
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Class<?>[] c(ArrayList<String> arrayList, ClassLoader classLoader) throws ClassNotFoundException {
        int size;
        if (arrayList == null || (size = arrayList.size()) <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class[size];
        for (int i2 = 0; i2 < size; i2++) {
            clsArr[i2] = classLoader.loadClass(arrayList.get(i2));
        }
        return clsArr;
    }

    public static ArrayList<String> d(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() <= 0) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(list.get(0).getClass().getName());
            return arrayList;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() <= 0) {
                return null;
            }
            ArrayList<String> arrayList2 = new ArrayList<>();
            Object next = map.keySet().iterator().next();
            arrayList2.add(next.getClass().getName());
            arrayList2.add(map.get(next).getClass().getName());
            return arrayList2;
        }
        if (!(obj instanceof SparseArray)) {
            return null;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            return null;
        }
        ArrayList<String> arrayList3 = new ArrayList<>();
        arrayList3.add(sparseArray.get(sparseArray.keyAt(0)).getClass().getName());
        return arrayList3;
    }

    public static Pair<Class<?>[], Object[]> e(Object[] objArr) {
        Class[] clsArr = new Class[objArr.length];
        Object[] objArr2 = new Object[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            if (obj == null) {
                clsArr[i2] = null;
                objArr2[i2] = null;
            } else if (obj instanceof GsonData) {
                GsonData gsonData = (GsonData) obj;
                try {
                    clsArr[i2] = RefUtils.b(gsonData.k(), ComplexData.k());
                    if (clsArr[i2].isInterface()) {
                        objArr2[i2] = Long.valueOf(Long.parseLong(gsonData.l()));
                    } else {
                        objArr2[i2] = a(gsonData.l(), clsArr[i2], c(gsonData.j(), ComplexData.k()), ComplexData.k());
                    }
                } catch (Throwable unused) {
                    clsArr[i2] = obj.getClass();
                    objArr2[i2] = obj;
                }
            } else {
                clsArr[i2] = obj.getClass();
                objArr2[i2] = obj;
            }
        }
        return new Pair<>(clsArr, objArr2);
    }
}

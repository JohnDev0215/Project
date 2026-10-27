package b.a.c;

import android.util.Pair;
import android.util.SparseArray;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.GsonData;
import com.byd.mvcadapter.refect.RefUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GsonUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.b.a.d f209a = new b.b.a.d();

    public static <T> T a(String str, Class<T> cls, Class<?>[] clsArr, ClassLoader classLoader) {
        if (clsArr != null) {
            try {
                if (clsArr.length > 0) {
                    return (T) f209a.j(str, b.b.a.s.a.c(cls, clsArr).f());
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return (T) f209a.i(str, cls);
    }

    public static String b(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return f209a.r(obj);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Class<?>[] c(ArrayList<String> arrayList, ClassLoader classLoader) throws ClassNotFoundException {
        int size;
        if (arrayList == null || (size = arrayList.size()) <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class[size];
        for (int i = 0; i < size; i++) {
            clsArr[i] = classLoader.loadClass(arrayList.get(i));
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
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null) {
                clsArr[i] = null;
                objArr2[i] = null;
            } else if (obj instanceof GsonData) {
                GsonData gsonData = (GsonData) obj;
                try {
                    clsArr[i] = RefUtils.c(gsonData.b(), ComplexData.b());
                    if (clsArr[i].isInterface()) {
                        objArr2[i] = Long.valueOf(Long.parseLong(gsonData.c()));
                    } else {
                        objArr2[i] = a(gsonData.c(), clsArr[i], c(gsonData.a(), ComplexData.b()), ComplexData.b());
                    }
                } catch (Throwable unused) {
                    clsArr[i] = obj.getClass();
                    objArr2[i] = obj;
                }
            } else {
                clsArr[i] = obj.getClass();
                objArr2[i] = obj;
            }
        }
        return new Pair<>(clsArr, objArr2);
    }
}

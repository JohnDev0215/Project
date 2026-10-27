package t3;

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
    public static final k6.d f8018a = new k6.d();

    public static <T> T a(String str, Class<T> cls, Class<?>[] clsArr, ClassLoader classLoader) {
        if (clsArr != null) {
            try {
                if (clsArr.length > 0) {
                    return (T) f8018a.j(str, p6.a.c(cls, clsArr).f());
                }
            } catch (RuntimeException e8) {
                e8.printStackTrace();
                return null;
            } catch (Exception e9) {
                e9.printStackTrace();
                return null;
            }
        }
        return (T) f8018a.i(str, cls);
    }

    public static String b(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return f8018a.r(obj);
        } catch (RuntimeException e8) {
            e8.printStackTrace();
            return null;
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    public static Class<?>[] c(ArrayList<String> arrayList, ClassLoader classLoader) throws ClassNotFoundException {
        int size;
        if (arrayList == null || (size = arrayList.size()) <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class[size];
        for (int i8 = 0; i8 < size; i8++) {
            clsArr[i8] = classLoader.loadClass(arrayList.get(i8));
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
        for (int i8 = 0; i8 < objArr.length; i8++) {
            Object obj = objArr[i8];
            if (obj == null) {
                clsArr[i8] = null;
                objArr2[i8] = null;
            } else if (obj instanceof GsonData) {
                GsonData gsonData = (GsonData) obj;
                try {
                    clsArr[i8] = RefUtils.b(gsonData.g(), ComplexData.g());
                    if (clsArr[i8].isInterface()) {
                        objArr2[i8] = Long.valueOf(Long.parseLong(gsonData.h()));
                    } else {
                        objArr2[i8] = a(gsonData.h(), clsArr[i8], c(gsonData.f(), ComplexData.g()), ComplexData.g());
                    }
                } catch (Throwable unused) {
                    clsArr[i8] = obj.getClass();
                    objArr2[i8] = obj;
                }
            } else {
                clsArr[i8] = obj.getClass();
                objArr2[i8] = obj;
            }
        }
        return new Pair<>(clsArr, objArr2);
    }
}

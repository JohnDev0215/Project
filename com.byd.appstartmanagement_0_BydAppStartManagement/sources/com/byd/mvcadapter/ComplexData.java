package com.byd.mvcadapter;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import c.a.a.a.a.b;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ComplexData implements Parcelable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f613d;
    public Object[] e;
    public String[] f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object[] f611b = new Object[0];
    public static final Parcelable.Creator<ComplexData> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ClassLoader f612c = ComplexData.class.getClassLoader();

    public static class a implements Parcelable.Creator<ComplexData> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ComplexData createFromParcel(Parcel parcel) {
            ComplexData complexData = new ComplexData();
            complexData.h(parcel);
            return complexData;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ComplexData[] newArray(int i) {
            return new ComplexData[i];
        }
    }

    public ComplexData() {
        Object[] objArr = f611b;
        this.f613d = objArr;
        this.e = objArr;
        this.f = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0089 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object[] a(java.lang.Object[] r9, java.lang.ClassLoader r10) {
        /*
            if (r10 != 0) goto L4
            java.lang.ClassLoader r10 = com.byd.mvcadapter.ComplexData.f612c
        L4:
            int r0 = r9.length
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
        L8:
            if (r2 >= r0) goto L8f
            r3 = r9[r2]
            boolean r4 = r3 instanceof com.byd.mvcadapter.GsonData
            r5 = 0
            if (r4 == 0) goto L15
            r4 = r3
            com.byd.mvcadapter.GsonData r4 = (com.byd.mvcadapter.GsonData) r4
            goto L2b
        L15:
            java.lang.Class<com.byd.mvcadapter.GsonData> r4 = com.byd.mvcadapter.GsonData.class
            boolean r4 = f(r3, r4)
            if (r4 == 0) goto L2a
            java.lang.String r4 = b.a.c.a.b(r3)
            java.lang.Class<com.byd.mvcadapter.GsonData> r6 = com.byd.mvcadapter.GsonData.class
            java.lang.Object r4 = b.a.c.a.a(r4, r6, r5, r5)
            com.byd.mvcadapter.GsonData r4 = (com.byd.mvcadapter.GsonData) r4
            goto L2b
        L2a:
            r4 = r5
        L2b:
            if (r4 == 0) goto L89
            java.lang.ClassLoader r6 = java.lang.ClassLoader.getSystemClassLoader()
            if (r6 == 0) goto L3c
            java.lang.String r7 = r4.b()     // Catch: com.byd.mvcadapter.refect.RefUtils.ClassNotFoundError -> L3c
            java.lang.Class r7 = com.byd.mvcadapter.refect.RefUtils.c(r7, r6)     // Catch: com.byd.mvcadapter.refect.RefUtils.ClassNotFoundError -> L3c
            goto L3d
        L3c:
            r7 = r5
        L3d:
            if (r7 != 0) goto L49
            if (r10 == r6) goto L49
            java.lang.String r6 = r4.b()     // Catch: com.byd.mvcadapter.refect.RefUtils.ClassNotFoundError -> L49
            java.lang.Class r7 = com.byd.mvcadapter.refect.RefUtils.c(r6, r10)     // Catch: com.byd.mvcadapter.refect.RefUtils.ClassNotFoundError -> L49
        L49:
            if (r7 == 0) goto L89
            boolean r6 = r7.isInterface()     // Catch: java.lang.Throwable -> L89
            if (r6 != 0) goto L89
            java.util.ArrayList r6 = r4.a()     // Catch: java.lang.Throwable -> L89
            java.lang.Class[] r6 = b.a.c.a.c(r6, r10)     // Catch: java.lang.Throwable -> L89
            java.lang.String r8 = r4.c()     // Catch: java.lang.Throwable -> L89
            if (r8 != 0) goto L61
            r3 = r5
            goto L89
        L61:
            int r5 = r8.length()     // Catch: java.lang.Throwable -> L89
            if (r5 != 0) goto L85
            java.lang.String r4 = r4.b()     // Catch: java.lang.Throwable -> L89
            java.lang.String r4 = c.a.a.a.a.b.k(r4)     // Catch: java.lang.Throwable -> L89
            java.lang.Class r4 = c.a.a.a.a.b.c(r4)     // Catch: java.lang.Throwable -> L89
            boolean r5 = r4.isArray()     // Catch: java.lang.Throwable -> L89
            if (r5 == 0) goto L80
            java.lang.String r5 = "[]"
            java.lang.Object r3 = b.a.c.a.a(r5, r4, r6, r10)     // Catch: java.lang.Throwable -> L89
            goto L89
        L80:
            java.lang.Object r3 = com.byd.mvcadapter.refect.RefUtils.v(r4)     // Catch: java.lang.Throwable -> L89
            goto L89
        L85:
            java.lang.Object r3 = b.a.c.a.a(r8, r7, r6, r10)     // Catch: java.lang.Throwable -> L89
        L89:
            r1[r2] = r3
            int r2 = r2 + 1
            goto L8
        L8f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.mvcadapter.ComplexData.a(java.lang.Object[], java.lang.ClassLoader):java.lang.Object[]");
    }

    public static ClassLoader b() {
        return f612c;
    }

    public static boolean f(Object obj, Class<?> cls) {
        if (obj != null && cls != null) {
            Class<?> superclass = obj.getClass();
            if (superclass.getName().equals(cls.getName())) {
                return true;
            }
            if (!superclass.isInterface()) {
                do {
                    superclass = superclass.getSuperclass();
                    if (superclass != null) {
                    }
                } while (!superclass.getName().equals(cls.getName()));
                return true;
            }
            Class<?>[] interfaces = superclass.getInterfaces();
            if (interfaces != null) {
                for (Class<?> cls2 : interfaces) {
                    if (cls2.getName().equals(cls.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean g(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass() == Void.class) {
            return false;
        }
        if (b.h(obj.getClass()) || (obj instanceof Parcelable) || (obj instanceof IBinder)) {
            return true;
        }
        if (obj.getClass().isArray()) {
            try {
                Object[] objArr = (Object[]) obj;
                if (objArr.length > 0) {
                    return g(objArr[0]);
                }
            } catch (ClassCastException unused) {
                return true;
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() > 0) {
                return g(list.get(0));
            }
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                return g(next) && g(map.get(next));
            }
        } else if (obj instanceof SparseArray) {
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() > 0) {
                return g(sparseArray.get(sparseArray.keyAt(0)));
            }
        }
        return false;
    }

    public static void i(ClassLoader classLoader) {
        f612c = classLoader;
    }

    public Object[] c() {
        return this.e;
    }

    public Object[] d(ClassLoader classLoader) {
        return classLoader == null ? c() : a(this.f613d, classLoader);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final Object[] e(Object[] objArr) {
        int length = objArr.length;
        Object[] objArr2 = new Object[length];
        for (int i = 0; i < length; i++) {
            Object gsonData = objArr[i];
            if (gsonData == null) {
                String[] strArr = this.f;
                if (strArr != null && strArr[i] != null) {
                    gsonData = new GsonData(this.f[i], null, null);
                }
            } else if (!g(gsonData)) {
                gsonData = new GsonData(gsonData.getClass().getName(), b.a.c.a.b(gsonData), b.a.c.a.d(gsonData));
            }
            objArr2[i] = gsonData;
        }
        return objArr2;
    }

    public void h(Parcel parcel) {
        Object[] array = parcel.readArray(f612c);
        this.f613d = array;
        this.e = a(array, null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Object[] objArr = this.e;
        if (objArr == null || objArr.length <= 0) {
            return sb.toString();
        }
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            sb.append(", ");
            sb.append(obj != null ? obj.toString() : "null");
        }
        return sb.substring(2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeArray(e(this.e));
    }

    public ComplexData(Object[] objArr) {
        this(objArr, null);
    }

    public ComplexData(Object[] objArr, String[] strArr) {
        Object[] objArr2 = f611b;
        this.f613d = objArr2;
        this.e = objArr2;
        this.f = null;
        this.e = objArr;
        this.f = strArr;
    }
}

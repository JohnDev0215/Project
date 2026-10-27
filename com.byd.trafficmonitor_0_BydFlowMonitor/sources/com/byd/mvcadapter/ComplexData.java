package com.byd.mvcadapter;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import d.a.a.a.a.b;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ComplexData implements Parcelable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f4332d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f4333e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String[] f4334f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object[] f4330b = new Object[0];
    public static final Parcelable.Creator<ComplexData> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ClassLoader f4331c = ComplexData.class.getClassLoader();

    public static class a implements Parcelable.Creator<ComplexData> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ComplexData createFromParcel(Parcel parcel) {
            ComplexData complexData = new ComplexData();
            complexData.q(parcel);
            return complexData;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ComplexData[] newArray(int i2) {
            return new ComplexData[i2];
        }
    }

    public ComplexData() {
        Object[] objArr = f4330b;
        this.f4332d = objArr;
        this.f4333e = objArr;
        this.f4334f = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0089 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object[] j(java.lang.Object[] r9, java.lang.ClassLoader r10) throws java.lang.ClassNotFoundException {
        /*
            if (r10 != 0) goto L4
            java.lang.ClassLoader r10 = com.byd.mvcadapter.ComplexData.f4331c
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
            boolean r4 = o(r3, r4)
            if (r4 == 0) goto L2a
            java.lang.String r4 = c.a.b.a.b(r3)
            java.lang.Class<com.byd.mvcadapter.GsonData> r6 = com.byd.mvcadapter.GsonData.class
            java.lang.Object r4 = c.a.b.a.a(r4, r6, r5, r5)
            com.byd.mvcadapter.GsonData r4 = (com.byd.mvcadapter.GsonData) r4
            goto L2b
        L2a:
            r4 = r5
        L2b:
            if (r4 == 0) goto L89
            java.lang.ClassLoader r6 = java.lang.ClassLoader.getSystemClassLoader()
            if (r6 == 0) goto L3c
            java.lang.String r7 = r4.k()     // Catch: com.byd.mvcadapter.refect.RefUtils.ClassNotFoundError -> L3c
            java.lang.Class r7 = com.byd.mvcadapter.refect.RefUtils.b(r7, r6)     // Catch: com.byd.mvcadapter.refect.RefUtils.ClassNotFoundError -> L3c
            goto L3d
        L3c:
            r7 = r5
        L3d:
            if (r7 != 0) goto L49
            if (r10 == r6) goto L49
            java.lang.String r6 = r4.k()     // Catch: com.byd.mvcadapter.refect.RefUtils.ClassNotFoundError -> L49
            java.lang.Class r7 = com.byd.mvcadapter.refect.RefUtils.b(r6, r10)     // Catch: com.byd.mvcadapter.refect.RefUtils.ClassNotFoundError -> L49
        L49:
            if (r7 == 0) goto L89
            boolean r6 = r7.isInterface()     // Catch: java.lang.Throwable -> L89
            if (r6 != 0) goto L89
            java.util.ArrayList r6 = r4.j()     // Catch: java.lang.Throwable -> L89
            java.lang.Class[] r6 = c.a.b.a.c(r6, r10)     // Catch: java.lang.Throwable -> L89
            java.lang.String r8 = r4.l()     // Catch: java.lang.Throwable -> L89
            if (r8 != 0) goto L61
            r3 = r5
            goto L89
        L61:
            int r5 = r8.length()     // Catch: java.lang.Throwable -> L89
            if (r5 != 0) goto L85
            java.lang.String r4 = r4.k()     // Catch: java.lang.Throwable -> L89
            java.lang.String r4 = d.a.a.a.a.b.k(r4)     // Catch: java.lang.Throwable -> L89
            java.lang.Class r4 = d.a.a.a.a.b.c(r4)     // Catch: java.lang.Throwable -> L89
            boolean r5 = r4.isArray()     // Catch: java.lang.Throwable -> L89
            if (r5 == 0) goto L80
            java.lang.String r5 = "[]"
            java.lang.Object r3 = c.a.b.a.a(r5, r4, r6, r10)     // Catch: java.lang.Throwable -> L89
            goto L89
        L80:
            java.lang.Object r3 = com.byd.mvcadapter.refect.RefUtils.t(r4)     // Catch: java.lang.Throwable -> L89
            goto L89
        L85:
            java.lang.Object r3 = c.a.b.a.a(r8, r7, r6, r10)     // Catch: java.lang.Throwable -> L89
        L89:
            r1[r2] = r3
            int r2 = r2 + 1
            goto L8
        L8f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.mvcadapter.ComplexData.j(java.lang.Object[], java.lang.ClassLoader):java.lang.Object[]");
    }

    public static ClassLoader k() {
        return f4331c;
    }

    public static boolean o(Object obj, Class<?> cls) {
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

    public static boolean p(Object obj) {
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
                    return p(objArr[0]);
                }
            } catch (ClassCastException unused) {
                return true;
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() > 0) {
                return p(list.get(0));
            }
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                return p(next) && p(map.get(next));
            }
        } else if (obj instanceof SparseArray) {
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() > 0) {
                return p(sparseArray.get(sparseArray.keyAt(0)));
            }
        }
        return false;
    }

    public static void r(ClassLoader classLoader) {
        f4331c = classLoader;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Object[] l() {
        return this.f4333e;
    }

    public Object[] m(ClassLoader classLoader) {
        return classLoader == null ? l() : j(this.f4332d, classLoader);
    }

    public final Object[] n(Object[] objArr) {
        int length = objArr.length;
        Object[] objArr2 = new Object[length];
        for (int i2 = 0; i2 < length; i2++) {
            Object gsonData = objArr[i2];
            if (gsonData == null) {
                String[] strArr = this.f4334f;
                if (strArr != null && strArr[i2] != null) {
                    gsonData = new GsonData(this.f4334f[i2], null, null);
                }
            } else if (!p(gsonData)) {
                gsonData = new GsonData(gsonData.getClass().getName(), c.a.b.a.b(gsonData), c.a.b.a.d(gsonData));
            }
            objArr2[i2] = gsonData;
        }
        return objArr2;
    }

    public void q(Parcel parcel) {
        Object[] array = parcel.readArray(f4331c);
        this.f4332d = array;
        this.f4333e = j(array, null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Object[] objArr = this.f4333e;
        if (objArr == null || objArr.length <= 0) {
            return sb.toString();
        }
        int length = objArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = objArr[i2];
            sb.append(", ");
            sb.append(obj != null ? obj.toString() : "null");
        }
        return sb.substring(2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeArray(n(this.f4333e));
    }

    public ComplexData(Object[] objArr) {
        this(objArr, null);
    }

    public ComplexData(Object[] objArr, String[] strArr) {
        Object[] objArr2 = f4330b;
        this.f4332d = objArr2;
        this.f4333e = objArr2;
        this.f4334f = null;
        this.f4333e = objArr;
        this.f4334f = strArr;
    }
}

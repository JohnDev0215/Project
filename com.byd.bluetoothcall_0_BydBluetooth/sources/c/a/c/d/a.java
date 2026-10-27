package c.a.c.d;

import java.util.Arrays;

/* JADX INFO: compiled from: ApiMethod.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f2625a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2626b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f2627c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Class<?>[] f2628d;

    public a(Object obj, String str, Object[] objArr) {
        this.f2625a = obj;
        this.f2626b = str;
        if (objArr == null || objArr.length <= 0) {
            return;
        }
        this.f2627c = objArr;
        this.f2628d = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            this.f2628d[i] = objArr[i].getClass();
        }
    }

    public String toString() {
        return "ApiMethod{obj=" + this.f2625a + ", methodName='" + this.f2626b + "', paramClassArr=" + Arrays.toString(this.f2628d) + ", paramArr=" + Arrays.toString(this.f2627c) + '}';
    }
}

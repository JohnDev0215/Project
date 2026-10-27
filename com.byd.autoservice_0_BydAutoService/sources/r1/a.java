package r1;

import java.util.Arrays;

/* JADX INFO: compiled from: ApiMethod.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f4834a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4835b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f4836c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Class<?>[] f4837d;

    public a(Object obj, String str, Object[] objArr) {
        this.f4834a = obj;
        this.f4835b = str;
        if (objArr == null || objArr.length <= 0) {
            return;
        }
        this.f4836c = objArr;
        this.f4837d = new Class[objArr.length];
        for (int i3 = 0; i3 < objArr.length; i3++) {
            this.f4837d[i3] = objArr[i3].getClass();
        }
    }

    public String toString() {
        return "ApiMethod{obj=" + this.f4834a + ", methodName='" + this.f4835b + "', paramClassArr=" + Arrays.toString(this.f4837d) + ", paramArr=" + Arrays.toString(this.f4836c) + '}';
    }
}

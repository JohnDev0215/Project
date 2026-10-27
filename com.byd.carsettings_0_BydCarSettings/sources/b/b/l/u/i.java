package b.b.l.u;

import android.os.Parcel;
import android.os.Process;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.GsonData;
import com.byd.mvcadapter.refect.RefUtils;
import com.byd.mvcadapter.rpc.Caller;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/* JADX INFO: compiled from: RpcMsg.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f2081a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Caller f2082b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String[] f2083c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f2084d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f2085e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2086f;
    public long g;
    public long h;
    public String i;
    public ComplexData j;
    public ComplexData k;

    public i() {
        this.i = "";
        this.j = new ComplexData();
        this.k = new ComplexData();
    }

    public static String[] e(int i) {
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = "arg" + i2;
        }
        return strArr;
    }

    public static String[] f(Method method) {
        Parameter[] parameters = method.getParameters();
        int length = parameters.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = parameters[i].getName();
        }
        return strArr;
    }

    public Caller a() {
        return this.f2082b;
    }

    public long b() {
        return this.h;
    }

    public Object c() {
        return this.f2085e;
    }

    public long d() {
        return this.g;
    }

    public final String[] g(Class<?>[] clsArr) {
        int length = clsArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = clsArr[i] != null ? clsArr[i].getName() : null;
        }
        return strArr;
    }

    public Object[] h() {
        return this.f2084d;
    }

    public Object[] i(ClassLoader classLoader) {
        return classLoader == null ? h() : this.j.d(classLoader);
    }

    public int j() {
        return this.f2086f;
    }

    public String k() {
        return this.i;
    }

    public void l(Parcel parcel) {
        this.f2082b = (Caller) parcel.readParcelable(ComplexData.b());
        this.f2086f = parcel.readInt();
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        this.i = parcel.readString();
        String[] strArr = new String[parcel.readInt()];
        this.f2083c = strArr;
        parcel.readStringArray(strArr);
        ComplexData complexData = (ComplexData) parcel.readParcelable(ComplexData.b());
        this.j = complexData;
        this.f2084d = complexData.c();
        ComplexData complexData2 = (ComplexData) parcel.readParcelable(ComplexData.b());
        this.k = complexData2;
        Object[] objArrC = complexData2.c();
        if (objArrC.length == 1) {
            this.f2085e = objArrC[0];
        }
    }

    public void m(long j) {
        this.h = j;
    }

    public void n(Object obj) {
        this.f2085e = obj;
    }

    public void o(String str) {
        this.i = str;
    }

    public void p(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2082b, i);
        parcel.writeInt(this.f2086f);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        parcel.writeString(this.i);
        parcel.writeInt(this.f2083c.length);
        parcel.writeStringArray(this.f2083c);
        parcel.writeParcelable(this.j, 0);
        if (this.f2085e != null) {
            this.k = new ComplexData(new Object[]{this.f2085e});
        }
        parcel.writeParcelable(this.k, 0);
    }

    public i(c cVar, int i, long j, Class<?>[] clsArr, String[] strArr, Object[] objArr) {
        this.i = "";
        this.j = new ComplexData();
        this.k = new ComplexData();
        this.f2081a = cVar;
        this.f2086f = i;
        this.f2082b = new Caller(Process.myPid(), Process.myUid(), cVar.i());
        if (j < 0) {
            this.g = c.h();
        } else {
            this.g = j;
        }
        clsArr = clsArr == null ? RefUtils.p(objArr) : clsArr;
        int length = objArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (clsArr[i2].isInterface()) {
                long jK = -1;
                if (objArr[i2] != null) {
                    jK = cVar.k(i, objArr[i2]);
                    if (jK < 0) {
                        jK = c.h();
                        cVar.b(i, jK, objArr[i2]);
                    }
                }
                objArr[i2] = new GsonData(clsArr[i2].getName(), String.valueOf(jK), null);
            }
        }
        this.f2083c = strArr;
        this.f2084d = objArr;
        this.j = new ComplexData(objArr, g(clsArr));
    }
}

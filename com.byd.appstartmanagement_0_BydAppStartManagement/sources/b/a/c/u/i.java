package b.a.c.u;

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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Caller f311c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String[] f312d;
    public Object[] e;
    public Object f;
    public int g;
    public long h;
    public long i;
    public String j;
    public ComplexData k;
    public ComplexData l;

    public i() {
        this.j = "";
        this.k = new ComplexData();
        this.l = new ComplexData();
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
        return this.f311c;
    }

    public long b() {
        return this.i;
    }

    public Object c() {
        return this.f;
    }

    public long d() {
        return this.h;
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
        return this.e;
    }

    public Object[] i(ClassLoader classLoader) {
        return classLoader == null ? h() : this.k.d(classLoader);
    }

    public int j() {
        return this.g;
    }

    public String k() {
        return this.j;
    }

    public void l(Parcel parcel) {
        this.f311c = (Caller) parcel.readParcelable(ComplexData.b());
        this.g = parcel.readInt();
        this.h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readString();
        String[] strArr = new String[parcel.readInt()];
        this.f312d = strArr;
        parcel.readStringArray(strArr);
        ComplexData complexData = (ComplexData) parcel.readParcelable(ComplexData.b());
        this.k = complexData;
        this.e = complexData.c();
        ComplexData complexData2 = (ComplexData) parcel.readParcelable(ComplexData.b());
        this.l = complexData2;
        Object[] objArrC = complexData2.c();
        if (objArrC.length == 1) {
            this.f = objArrC[0];
        }
    }

    public void m(long j) {
        this.i = j;
    }

    public void n(Object obj) {
        this.f = obj;
    }

    public void o(String str) {
        this.j = str;
    }

    public void p(Parcel parcel, int i) {
        parcel.writeParcelable(this.f311c, i);
        parcel.writeInt(this.g);
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
        parcel.writeString(this.j);
        parcel.writeInt(this.f312d.length);
        parcel.writeStringArray(this.f312d);
        parcel.writeParcelable(this.k, 0);
        if (this.f != null) {
            this.l = new ComplexData(new Object[]{this.f});
        }
        parcel.writeParcelable(this.l, 0);
    }

    public i(c cVar, int i, long j, Class<?>[] clsArr, String[] strArr, Object[] objArr) {
        this.j = "";
        this.k = new ComplexData();
        this.l = new ComplexData();
        this.f310b = cVar;
        this.g = i;
        this.f311c = new Caller(Process.myPid(), Process.myUid(), cVar.i());
        if (j < 0) {
            this.h = c.h();
        } else {
            this.h = j;
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
        this.f312d = strArr;
        this.e = objArr;
        this.k = new ComplexData(objArr, g(clsArr));
    }
}

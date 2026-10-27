package u3;

import android.os.Parcel;
import android.os.Process;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.GsonData;
import com.byd.mvcadapter.refect.RefUtils;
import com.byd.mvcadapter.rpc.Caller;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/* JADX INFO: compiled from: RpcMsg.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f8178d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Caller f8179e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String[] f8180f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object[] f8181g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f8182h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8183i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f8184j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f8185k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f8186l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ComplexData f8187m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ComplexData f8188n;

    public f() {
        this.f8186l = "";
        this.f8187m = new ComplexData();
        this.f8188n = new ComplexData();
    }

    public static String[] j(Method method) {
        Parameter[] parameters = method.getParameters();
        int length = parameters.length;
        String[] strArr = new String[length];
        for (int i8 = 0; i8 < length; i8++) {
            strArr[i8] = parameters[i8].getName();
        }
        return strArr;
    }

    public Caller f() {
        return this.f8179e;
    }

    public long g() {
        return this.f8185k;
    }

    public Object h() {
        return this.f8182h;
    }

    public long i() {
        return this.f8184j;
    }

    public final String[] k(Class<?>[] clsArr) {
        int length = clsArr.length;
        String[] strArr = new String[length];
        for (int i8 = 0; i8 < length; i8++) {
            strArr[i8] = clsArr[i8] != null ? clsArr[i8].getName() : null;
        }
        return strArr;
    }

    public Object[] l() {
        return this.f8181g;
    }

    public int m() {
        return this.f8183i;
    }

    public String n() {
        return this.f8186l;
    }

    public void o(Parcel parcel) {
        this.f8179e = (Caller) parcel.readParcelable(ComplexData.g());
        this.f8183i = parcel.readInt();
        this.f8184j = parcel.readLong();
        this.f8185k = parcel.readLong();
        this.f8186l = parcel.readString();
        String[] strArr = new String[parcel.readInt()];
        this.f8180f = strArr;
        parcel.readStringArray(strArr);
        ComplexData complexData = (ComplexData) parcel.readParcelable(ComplexData.g());
        this.f8187m = complexData;
        this.f8181g = complexData.h();
        ComplexData complexData2 = (ComplexData) parcel.readParcelable(ComplexData.g());
        this.f8188n = complexData2;
        Object[] objArrH = complexData2.h();
        if (objArrH.length == 1) {
            this.f8182h = objArrH[0];
        }
    }

    public void p(long j8) {
        this.f8185k = j8;
    }

    public void q(Parcel parcel, int i8) {
        parcel.writeParcelable(this.f8179e, i8);
        parcel.writeInt(this.f8183i);
        parcel.writeLong(this.f8184j);
        parcel.writeLong(this.f8185k);
        parcel.writeString(this.f8186l);
        parcel.writeInt(this.f8180f.length);
        parcel.writeStringArray(this.f8180f);
        parcel.writeParcelable(this.f8187m, 0);
        if (this.f8182h != null) {
            this.f8188n = new ComplexData(new Object[]{this.f8182h});
        }
        parcel.writeParcelable(this.f8188n, 0);
    }

    public f(b bVar, int i8, long j8, Class<?>[] clsArr, String[] strArr, Object[] objArr) {
        this.f8186l = "";
        this.f8187m = new ComplexData();
        this.f8188n = new ComplexData();
        this.f8178d = bVar;
        this.f8183i = i8;
        this.f8179e = new Caller(Process.myPid(), Process.myUid(), bVar.i());
        if (j8 < 0) {
            this.f8184j = b.h();
        } else {
            this.f8184j = j8;
        }
        clsArr = clsArr == null ? RefUtils.n(objArr) : clsArr;
        int length = objArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (clsArr[i9].isInterface()) {
                long jK = -1;
                if (objArr[i9] != null) {
                    jK = bVar.k(i8, objArr[i9]);
                    if (jK < 0) {
                        jK = b.h();
                        bVar.c(i8, jK, objArr[i9]);
                    }
                }
                objArr[i9] = new GsonData(clsArr[i9].getName(), String.valueOf(jK), null);
            }
        }
        this.f8180f = strArr;
        this.f8181g = objArr;
        this.f8187m = new ComplexData(objArr, k(clsArr));
    }
}

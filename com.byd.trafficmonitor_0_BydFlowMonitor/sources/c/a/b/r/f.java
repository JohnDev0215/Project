package c.a.b.r;

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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3543b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Caller f3544c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String[] f3545d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f3546e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f3547f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3548g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f3549h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f3550i;
    public String j;
    public ComplexData k;
    public ComplexData l;

    public f() {
        this.j = "";
        this.k = new ComplexData();
        this.l = new ComplexData();
    }

    public static String[] n(Method method) {
        Parameter[] parameters = method.getParameters();
        int length = parameters.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = parameters[i2].getName();
        }
        return strArr;
    }

    public Caller j() {
        return this.f3544c;
    }

    public long k() {
        return this.f3550i;
    }

    public Object l() {
        return this.f3547f;
    }

    public long m() {
        return this.f3549h;
    }

    public final String[] o(Class<?>[] clsArr) {
        int length = clsArr.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = clsArr[i2] != null ? clsArr[i2].getName() : null;
        }
        return strArr;
    }

    public Object[] p() {
        return this.f3546e;
    }

    public int q() {
        return this.f3548g;
    }

    public String r() {
        return this.j;
    }

    public void s(Parcel parcel) {
        this.f3544c = (Caller) parcel.readParcelable(ComplexData.k());
        this.f3548g = parcel.readInt();
        this.f3549h = parcel.readLong();
        this.f3550i = parcel.readLong();
        this.j = parcel.readString();
        String[] strArr = new String[parcel.readInt()];
        this.f3545d = strArr;
        parcel.readStringArray(strArr);
        ComplexData complexData = (ComplexData) parcel.readParcelable(ComplexData.k());
        this.k = complexData;
        this.f3546e = complexData.l();
        ComplexData complexData2 = (ComplexData) parcel.readParcelable(ComplexData.k());
        this.l = complexData2;
        Object[] objArrL = complexData2.l();
        if (objArrL.length == 1) {
            this.f3547f = objArrL[0];
        }
    }

    public void t(long j) {
        this.f3550i = j;
    }

    public void u(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f3544c, i2);
        parcel.writeInt(this.f3548g);
        parcel.writeLong(this.f3549h);
        parcel.writeLong(this.f3550i);
        parcel.writeString(this.j);
        parcel.writeInt(this.f3545d.length);
        parcel.writeStringArray(this.f3545d);
        parcel.writeParcelable(this.k, 0);
        if (this.f3547f != null) {
            this.l = new ComplexData(new Object[]{this.f3547f});
        }
        parcel.writeParcelable(this.l, 0);
    }

    public f(b bVar, int i2, long j, Class<?>[] clsArr, String[] strArr, Object[] objArr) {
        this.j = "";
        this.k = new ComplexData();
        this.l = new ComplexData();
        this.f3543b = bVar;
        this.f3548g = i2;
        this.f3544c = new Caller(Process.myPid(), Process.myUid(), bVar.i());
        if (j < 0) {
            this.f3549h = b.h();
        } else {
            this.f3549h = j;
        }
        clsArr = clsArr == null ? RefUtils.n(objArr) : clsArr;
        int length = objArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (clsArr[i3].isInterface()) {
                long jK = -1;
                if (objArr[i3] != null) {
                    jK = bVar.k(i2, objArr[i3]);
                    if (jK < 0) {
                        jK = b.h();
                        bVar.c(i2, jK, objArr[i3]);
                    }
                }
                objArr[i3] = new GsonData(clsArr[i3].getName(), String.valueOf(jK), null);
            }
        }
        this.f3545d = strArr;
        this.f3546e = objArr;
        this.k = new ComplexData(objArr, o(clsArr));
    }
}

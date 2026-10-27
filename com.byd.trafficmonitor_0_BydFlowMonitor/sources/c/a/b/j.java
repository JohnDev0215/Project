package c.a.b;

import c.a.b.o;
import com.byd.mvcadapter.ComplexData;

/* JADX INFO: compiled from: MVCMessage.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3469c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3471e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public o.c f3473g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3467a = p.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ComplexData f3470d = new ComplexData(new Object[0]);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3472f = true;

    public j a(String str) {
        this.f3469c = str;
        return this;
    }

    public j b(Object... objArr) {
        if (objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof ComplexData)) {
            this.f3470d = (ComplexData) objArr[0];
        } else {
            this.f3470d = new ComplexData(objArr);
        }
        return this;
    }

    public void c(boolean z) {
        this.f3472f = z;
    }

    public void d(boolean z) {
        this.f3471e = z;
    }

    public j e(int i2) {
        this.f3468b = i2;
        return this;
    }
}

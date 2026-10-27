package t3;

import com.byd.mvcadapter.ComplexData;
import t3.o;

/* JADX INFO: compiled from: MVCMessage.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8040b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f8041c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8043e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public o.c f8045g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f8039a = p.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ComplexData f8042d = new ComplexData(new Object[0]);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8044f = true;

    public j a(String str) {
        this.f8041c = str;
        return this;
    }

    public j b(Object... objArr) {
        if (objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof ComplexData)) {
            this.f8042d = (ComplexData) objArr[0];
        } else {
            this.f8042d = new ComplexData(objArr);
        }
        return this;
    }

    public void c(boolean z7) {
        this.f8044f = z7;
    }

    public void d(boolean z7) {
        this.f8043e = z7;
    }

    public j e(int i8) {
        this.f8040b = i8;
        return this;
    }
}

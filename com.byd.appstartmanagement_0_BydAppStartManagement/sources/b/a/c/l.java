package b.a.c;

import b.a.c.r;
import com.byd.mvcadapter.ComplexData;

/* JADX INFO: compiled from: MVCMessage.java */
/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f241b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f242c;
    public boolean e;
    public r.c g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f240a = s.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ComplexData f243d = new ComplexData(new Object[0]);
    public boolean f = true;

    public l a(String str) {
        this.f242c = str;
        return this;
    }

    public l b(Object... objArr) {
        if (objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof ComplexData)) {
            this.f243d = (ComplexData) objArr[0];
        } else {
            this.f243d = new ComplexData(objArr);
        }
        return this;
    }

    public void c(boolean z) {
        this.f = z;
    }

    public void d(boolean z) {
        this.e = z;
    }

    public l e(int i) {
        this.f241b = i;
        return this;
    }
}

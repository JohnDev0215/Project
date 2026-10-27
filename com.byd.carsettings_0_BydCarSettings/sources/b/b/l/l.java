package b.b.l;

import b.b.l.r;
import com.byd.mvcadapter.ComplexData;

/* JADX INFO: compiled from: MVCMessage.java */
/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1995b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1996c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1998e;
    public r.c g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f1994a = s.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ComplexData f1997d = new ComplexData(new Object[0]);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1999f = true;

    public l a(String str) {
        this.f1996c = str;
        return this;
    }

    public l b(Object... objArr) {
        if (objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof ComplexData)) {
            this.f1997d = (ComplexData) objArr[0];
        } else {
            this.f1997d = new ComplexData(objArr);
        }
        return this;
    }

    public void c(boolean z) {
        this.f1999f = z;
    }

    public void d(boolean z) {
        this.f1998e = z;
    }

    public l e(int i) {
        this.f1995b = i;
        return this;
    }
}

package E1;

import A7.m;
import h1.C1342b;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f1717a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1718b;

    public c(int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f1717a = new Object[i7];
    }

    public Object a() {
        int i7 = this.f1718b;
        if (i7 <= 0) {
            return null;
        }
        int i10 = i7 - 1;
        Object[] objArr = this.f1717a;
        Object obj = objArr[i10];
        m.d(obj, "null cannot be cast to non-null type T of androidx.core.util.Pools.SimplePool");
        objArr[i10] = null;
        this.f1718b--;
        return obj;
    }

    public void b(C1342b c1342b) {
        int i7 = this.f1718b;
        Object[] objArr = this.f1717a;
        if (i7 < objArr.length) {
            objArr[i7] = c1342b;
            this.f1718b = i7 + 1;
        }
    }

    public boolean c(Object obj) {
        Object[] objArr;
        boolean z6;
        m.f(obj, "instance");
        int i7 = this.f1718b;
        int i10 = 0;
        while (true) {
            objArr = this.f1717a;
            if (i10 >= i7) {
                z6 = false;
                break;
            }
            if (objArr[i10] == obj) {
                z6 = true;
                break;
            }
            i10++;
        }
        if (z6) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i11 = this.f1718b;
        if (i11 >= objArr.length) {
            return false;
        }
        objArr[i11] = obj;
        this.f1718b = i11 + 1;
        return true;
    }

    public c() {
        this.f1717a = new Object[256];
    }
}

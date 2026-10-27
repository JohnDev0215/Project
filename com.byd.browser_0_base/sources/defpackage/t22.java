package defpackage;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class t22 {
    public final /* synthetic */ int a;
    public final Object[] b;
    public int c;

    public t22(int i, int i2) {
        this.a = i2;
        if (i2 != 1) {
            if (i <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.b = new Object[i];
        } else {
            if (i <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.b = new Object[i];
        }
    }

    public Object a() {
        int i = this.a;
        Object[] objArr = this.b;
        switch (i) {
            case 0:
                int i2 = this.c;
                if (i2 <= 0) {
                    return null;
                }
                int i3 = i2 - 1;
                Object obj = objArr[i3];
                objArr[i3] = null;
                this.c = i3;
                return obj;
            default:
                int i4 = this.c;
                if (i4 <= 0) {
                    return null;
                }
                int i5 = i4 - 1;
                Object obj2 = objArr[i5];
                objArr[i5] = null;
                this.c = i5;
                return obj2;
        }
    }

    public boolean b(Object obj) {
        int i;
        boolean z;
        int i2 = this.a;
        Object[] objArr = this.b;
        switch (i2) {
            case 0:
                int i3 = this.c;
                if (i3 >= objArr.length) {
                    return false;
                }
                objArr[i3] = obj;
                this.c = i3 + 1;
                return true;
            default:
                int i4 = 0;
                while (true) {
                    i = this.c;
                    if (i4 >= i) {
                        z = false;
                    } else if (objArr[i4] == obj) {
                        z = true;
                    } else {
                        i4++;
                    }
                }
                if (z) {
                    throw new IllegalStateException("Already in the pool!");
                }
                if (i >= objArr.length) {
                    return false;
                }
                objArr[i] = obj;
                this.c = i + 1;
                return true;
        }
    }
}

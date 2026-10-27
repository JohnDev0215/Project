package p;

/* JADX INFO: loaded from: classes.dex */
public class nw1 {
    public final /* synthetic */ int a;
    public final Object[] b;
    public int c;

    public nw1(int i, int i2) {
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
        int i = this.a;
        Object[] objArr = this.b;
        switch (i) {
            case 0:
                int i2 = this.c;
                if (i2 >= objArr.length) {
                    return false;
                }
                objArr[i2] = obj;
                this.c = i2 + 1;
                return true;
            default:
                int i3 = 0;
                while (true) {
                    int i4 = this.c;
                    if (i3 >= i4) {
                        if (i4 >= objArr.length) {
                            return false;
                        }
                        objArr[i4] = obj;
                        this.c = i4 + 1;
                        return true;
                    }
                    if (objArr[i3] == obj) {
                        throw new IllegalStateException("Already in the pool!");
                    }
                    i3++;
                }
                break;
        }
    }
}

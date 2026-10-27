package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class w31 extends p02 {
    public Object[] g;
    public int h;
    public boolean i;

    public w31() {
        he1.e(4, "initialCapacity");
        this.g = new Object[4];
        this.h = 0;
    }

    public final void u(Object obj) {
        obj.getClass();
        int i = this.h + 1;
        Object[] objArr = this.g;
        if (objArr.length < i) {
            this.g = Arrays.copyOf(objArr, p02.h(objArr.length, i));
            this.i = false;
        } else if (this.i) {
            this.g = (Object[]) objArr.clone();
            this.i = false;
        }
        Object[] objArr2 = this.g;
        int i2 = this.h;
        this.h = i2 + 1;
        objArr2[i2] = obj;
    }
}

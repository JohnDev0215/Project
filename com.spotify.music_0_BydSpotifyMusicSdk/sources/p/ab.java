package p;

import io.reactivex.rxjava3.internal.util.NotificationLite;

/* JADX INFO: loaded from: classes.dex */
public final class ab {
    public final /* synthetic */ int a;
    public final int b;
    public final Object[] c;
    public Object[] d;
    public int e;

    public ab(int i) {
        this.a = i;
        if (i != 1) {
            this.b = 4;
            Object[] objArr = new Object[5];
            this.c = objArr;
            this.d = objArr;
            return;
        }
        this.b = 4;
        Object[] objArr2 = new Object[5];
        this.c = objArr2;
        this.d = objArr2;
    }

    public final boolean a(rg2 rg2Var) {
        int i;
        Object[] objArr;
        Object[] objArr2 = this.c;
        while (true) {
            int i2 = 0;
            if (objArr2 == null) {
                return false;
            }
            while (true) {
                i = this.b;
                if (i2 >= i || (objArr = objArr2[i2]) == null) {
                    break;
                }
                if (NotificationLite.d(objArr, rg2Var)) {
                    return true;
                }
                i2++;
            }
            objArr2 = objArr2[i];
        }
    }

    public final void b(Object obj) {
        int i = this.a;
        int i2 = 0;
        int i3 = this.b;
        switch (i) {
            case 0:
                int i4 = this.e;
                if (i4 == i3) {
                    Object[] objArr = new Object[i3 + 1];
                    this.d[i3] = objArr;
                    this.d = objArr;
                } else {
                    i2 = i4;
                }
                this.d[i2] = obj;
                this.e = i2 + 1;
                break;
            default:
                int i5 = this.e;
                if (i5 == i3) {
                    Object[] objArr2 = new Object[i3 + 1];
                    this.d[i3] = objArr2;
                    this.d = objArr2;
                } else {
                    i2 = i5;
                }
                this.d[i2] = obj;
                this.e = i2 + 1;
                break;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0018, code lost:
    
        r0 = r0[r2];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(io.reactivex.rxjava3.internal.util.a r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.c
        L2:
            if (r0 == 0) goto L1d
            r1 = 0
        L5:
            int r2 = r4.b
            if (r1 >= r2) goto L18
            r3 = r0[r1]
            if (r3 != 0) goto Le
            goto L18
        Le:
            boolean r2 = r5.test(r3)
            if (r2 == 0) goto L15
            return
        L15:
            int r1 = r1 + 1
            goto L5
        L18:
            r0 = r0[r2]
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            goto L2
        L1d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ab.c(io.reactivex.rxjava3.internal.util.a):void");
    }
}

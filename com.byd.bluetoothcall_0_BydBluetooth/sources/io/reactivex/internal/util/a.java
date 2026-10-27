package io.reactivex.internal.util;

/* JADX INFO: compiled from: AppendOnlyLinkedArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int f4359a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object[] f4360b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Object[] f4361c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f4362d;

    public a(int i) {
        this.f4359a = i;
        Object[] objArr = new Object[i + 1];
        this.f4360b = objArr;
        this.f4361c = objArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0019, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <U> boolean a(d.a.i<? super U> r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.f4360b
            int r1 = r4.f4359a
        L4:
            r2 = 0
            if (r0 == 0) goto L1e
        L7:
            if (r2 >= r1) goto L19
            r3 = r0[r2]
            if (r3 != 0) goto Le
            goto L19
        Le:
            boolean r3 = io.reactivex.internal.util.NotificationLite.acceptFull(r3, r5)
            if (r3 == 0) goto L16
            r5 = 1
            return r5
        L16:
            int r2 = r2 + 1
            goto L7
        L19:
            r0 = r0[r1]
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            goto L4
        L1e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.util.a.a(d.a.i):boolean");
    }

    public void b(T t) {
        int i = this.f4359a;
        int i2 = this.f4362d;
        if (i2 == i) {
            Object[] objArr = new Object[i + 1];
            this.f4361c[i] = objArr;
            this.f4361c = objArr;
            i2 = 0;
        }
        this.f4361c[i2] = t;
        this.f4362d = i2 + 1;
    }

    public void c(T t) {
        this.f4360b[0] = t;
    }
}

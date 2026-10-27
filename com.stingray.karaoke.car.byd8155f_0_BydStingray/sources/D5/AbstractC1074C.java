package d5;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: d5.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1074C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object[] f13377a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f13378b;
    public boolean c;

    public AbstractC1074C(int i7) {
        AbstractC1095q.e(i7, "initialCapacity");
        this.f13377a = new Object[i7];
        this.f13378b = 0;
    }

    public static int g(int i7, int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException("cannot store more than MAX_VALUE elements");
        }
        if (i10 <= i7) {
            return i7;
        }
        int iHighestOneBit = i7 + (i7 >> 1) + 1;
        if (iHighestOneBit < i10) {
            iHighestOneBit = Integer.highestOneBit(i10 - 1) << 1;
        }
        if (iHighestOneBit < 0) {
            return Integer.MAX_VALUE;
        }
        return iHighestOneBit;
    }

    public final void a(Object obj) {
        obj.getClass();
        f(1);
        Object[] objArr = this.f13377a;
        int i7 = this.f13378b;
        this.f13378b = i7 + 1;
        objArr[i7] = obj;
    }

    public abstract AbstractC1074C b(Object obj);

    public void c(Object obj) {
        a(obj);
    }

    public void d(I i7) {
        e(i7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e(List list) {
        if (list != 0) {
            f(list.size());
            if (list instanceof AbstractC1075D) {
                this.f13378b = ((AbstractC1075D) list).d(this.f13378b, this.f13377a);
                return;
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    public final void f(int i7) {
        Object[] objArr = this.f13377a;
        int iG = g(objArr.length, this.f13378b + i7);
        if (iG > objArr.length || this.c) {
            this.f13377a = Arrays.copyOf(this.f13377a, iG);
            this.c = false;
        }
    }
}

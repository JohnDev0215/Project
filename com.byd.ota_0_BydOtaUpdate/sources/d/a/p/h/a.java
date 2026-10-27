package d.a.p.h;

import d.a.i;
import d.a.o.g;
import io.reactivex.internal.util.NotificationLite;

/* JADX INFO: compiled from: AppendOnlyLinkedArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3254a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f3255b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f3256c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3257d;

    /* JADX INFO: renamed from: d.a.p.h.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppendOnlyLinkedArrayList.java */
    public interface InterfaceC0077a<T> extends g<T> {
        @Override // d.a.o.g
        boolean test(T t);
    }

    public a(int i2) {
        this.f3254a = i2;
        Object[] objArr = new Object[i2 + 1];
        this.f3255b = objArr;
        this.f3256c = objArr;
    }

    public <U> boolean a(i<? super U> iVar) {
        int i2;
        Object[] objArr = this.f3255b;
        int i3 = this.f3254a;
        while (true) {
            if (objArr == null) {
                return false;
            }
            while (i2 < i3) {
                Object[] objArr2 = objArr[i2];
                i2 = (objArr2 == null || NotificationLite.acceptFull(objArr2, iVar)) ? 0 : i2 + 1;
            }
            objArr = objArr[i3];
        }
    }

    public void b(T t) {
        int i2 = this.f3254a;
        int i3 = this.f3257d;
        if (i3 == i2) {
            Object[] objArr = new Object[i2 + 1];
            this.f3256c[i2] = objArr;
            this.f3256c = objArr;
            i3 = 0;
        }
        this.f3256c[i3] = t;
        this.f3257d = i3 + 1;
    }

    public void c(InterfaceC0077a<? super T> interfaceC0077a) {
        int i2;
        int i3 = this.f3254a;
        for (Object[] objArr = this.f3255b; objArr != null; objArr = (Object[]) objArr[i3]) {
            while (i2 < i3) {
                Object obj = objArr[i2];
                i2 = (obj == null || interfaceC0077a.test(obj)) ? 0 : i2 + 1;
            }
        }
    }

    public void d(T t) {
        this.f3255b[0] = t;
    }
}

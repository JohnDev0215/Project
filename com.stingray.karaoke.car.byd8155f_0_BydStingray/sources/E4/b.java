package E4;

import E5.C0180e;
import E5.H;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f1747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1748b;

    public b(int i7) {
        this.f1748b = i7;
        int i10 = i7 * i7;
        ArrayList arrayList = new ArrayList(i10);
        for (int i11 = 0; i11 < i10; i11++) {
            arrayList.add(a.f1745n);
        }
        this.f1747a = arrayList;
    }

    public void a(Class cls, F5.a aVar) {
        ArrayList arrayList = H.d;
        C0180e c0180e = new C0180e(1, cls, aVar);
        ArrayList arrayList2 = this.f1747a;
        int i7 = this.f1748b;
        this.f1748b = 1 + i7;
        arrayList2.add(i7, c0180e);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01e4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00f9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(java.lang.Object r25) {
        /*
            Method dump skipped, instruction units count: 611
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E4.b.b(java.lang.Object):void");
    }

    public a c(int i7, int i10) {
        int i11 = this.f1748b;
        Integer numValueOf = (i7 < 0 || i7 >= i11) ? Integer.valueOf(i7) : (i10 < 0 || i10 >= i11) ? Integer.valueOf(i10) : null;
        if (numValueOf == null) {
            return (a) this.f1747a.get((i10 * i11) + i7);
        }
        StringBuilder sb = new StringBuilder("Index ");
        sb.append(numValueOf);
        sb.append(" is out of 0..");
        sb.append(i11 - 1);
        sb.append(" matrix bound");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public int d() {
        return this.f1748b;
    }

    public boolean e() {
        return this.f1748b < this.f1747a.size();
    }

    public void f(int i7, int i10, a aVar) {
        int i11 = this.f1748b;
        Integer numValueOf = (i7 < 0 || i7 >= i11) ? Integer.valueOf(i7) : (i10 < 0 || i10 >= i11) ? Integer.valueOf(i10) : null;
        if (numValueOf == null) {
            this.f1747a.set((i10 * i11) + i7, aVar);
            return;
        }
        StringBuilder sb = new StringBuilder("Index ");
        sb.append(numValueOf);
        sb.append(" is out of 0..");
        sb.append(i11 - 1);
        sb.append(" matrix bound");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public b(ArrayList arrayList) {
        this.f1747a = arrayList;
    }

    public b() {
        this.f1747a = new ArrayList();
        this.f1748b = 0;
    }
}

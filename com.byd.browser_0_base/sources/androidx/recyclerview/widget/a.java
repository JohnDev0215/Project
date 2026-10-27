package androidx.recyclerview.widget;

import androidx.recyclerview.widget.p;
import defpackage.t22;
import java.util.ArrayList;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements p.a {
    public final InterfaceC0024a d;
    public final t22 a = new t22(30, 1);
    public final ArrayList<b> b = new ArrayList<>();
    public final ArrayList<b> c = new ArrayList<>();
    public int f = 0;
    public final p e = new p(this);

    /* JADX INFO: renamed from: androidx.recyclerview.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    public interface InterfaceC0024a {
    }

    /* JADX INFO: compiled from: AdapterHelper.java */
    public static final class b {
        public int a;
        public int b;
        public Object c;
        public int d;

        public b(int i, int i2, int i3, Object obj) {
            this.a = i;
            this.b = i2;
            this.d = i3;
            this.c = obj;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            int i = this.a;
            if (i != bVar.a) {
                return false;
            }
            if (i == 8 && Math.abs(this.d - this.b) == 1 && this.d == bVar.b && this.b == bVar.d) {
                return true;
            }
            if (this.d != bVar.d || this.b != bVar.b) {
                return false;
            }
            Object obj2 = this.c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.c)) {
                    return false;
                }
            } else if (bVar.c != null) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return (((this.a * 31) + this.b) * 31) + this.d;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append("[");
            int i = this.a;
            sb.append(i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add");
            sb.append(",s:");
            sb.append(this.b);
            sb.append("c:");
            sb.append(this.d);
            sb.append(",p:");
            sb.append(this.c);
            sb.append("]");
            return sb.toString();
        }
    }

    public a(w wVar) {
        this.d = wVar;
    }

    public final boolean a(int i) {
        ArrayList<b> arrayList = this.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = arrayList.get(i2);
            int i3 = bVar.a;
            if (i3 == 8) {
                if (f(bVar.d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = bVar.b;
                int i5 = bVar.d + i4;
                while (i4 < i5) {
                    if (f(i4, i2 + 1) == i) {
                        return true;
                    }
                    i4++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public final void b() {
        ArrayList<b> arrayList = this.c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((w) this.d).a(arrayList.get(i));
        }
        l(arrayList);
        this.f = 0;
    }

    public final void c() {
        b();
        ArrayList<b> arrayList = this.b;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            b bVar = arrayList.get(i);
            int i2 = bVar.a;
            InterfaceC0024a interfaceC0024a = this.d;
            if (i2 == 1) {
                w wVar = (w) interfaceC0024a;
                wVar.a(bVar);
                wVar.d(bVar.b, bVar.d);
            } else if (i2 == 2) {
                w wVar2 = (w) interfaceC0024a;
                wVar2.a(bVar);
                int i3 = bVar.b;
                int i4 = bVar.d;
                RecyclerView recyclerView = wVar2.a;
                recyclerView.R(i3, i4, true);
                recyclerView.p0 = true;
                recyclerView.m0.c += i4;
            } else if (i2 == 4) {
                w wVar3 = (w) interfaceC0024a;
                wVar3.a(bVar);
                wVar3.c(bVar.b, bVar.d, bVar.c);
            } else if (i2 == 8) {
                w wVar4 = (w) interfaceC0024a;
                wVar4.a(bVar);
                wVar4.e(bVar.b, bVar.d);
            }
        }
        l(arrayList);
        this.f = 0;
    }

    public final void d(b bVar) {
        int i;
        int i2 = bVar.a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iM = m(bVar.b, i2);
        int i3 = bVar.b;
        int i4 = bVar.a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < bVar.d; i6++) {
            int iM2 = m((i * i6) + bVar.b, bVar.a);
            int i7 = bVar.a;
            if (i7 == 2 ? iM2 == iM : i7 == 4 && iM2 == iM + 1) {
                i5++;
            } else {
                b bVarH = h(i7, iM, i5, bVar.c);
                e(bVarH, i3);
                k(bVarH);
                if (bVar.a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                iM = iM2;
            }
        }
        Object obj = bVar.c;
        k(bVar);
        if (i5 > 0) {
            b bVarH2 = h(bVar.a, iM, i5, obj);
            e(bVarH2, i3);
            k(bVarH2);
        }
    }

    public final void e(b bVar, int i) {
        w wVar = (w) this.d;
        wVar.a(bVar);
        int i2 = bVar.a;
        if (i2 != 2) {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            wVar.c(i, bVar.d, bVar.c);
        } else {
            int i3 = bVar.d;
            RecyclerView recyclerView = wVar.a;
            recyclerView.R(i, i3, true);
            recyclerView.p0 = true;
            recyclerView.m0.c += i3;
        }
    }

    public final int f(int i, int i2) {
        ArrayList<b> arrayList = this.c;
        int size = arrayList.size();
        while (i2 < size) {
            b bVar = arrayList.get(i2);
            int i3 = bVar.a;
            if (i3 == 8) {
                int i4 = bVar.b;
                if (i4 == i) {
                    i = bVar.d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (bVar.d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = bVar.b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = bVar.d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += bVar.d;
                }
            }
            i2++;
        }
        return i;
    }

    public final boolean g() {
        return this.b.size() > 0;
    }

    public final b h(int i, int i2, int i3, Object obj) {
        b bVar = (b) this.a.a();
        if (bVar == null) {
            return new b(i, i2, i3, obj);
        }
        bVar.a = i;
        bVar.b = i2;
        bVar.d = i3;
        bVar.c = obj;
        return bVar;
    }

    public final void i(b bVar) {
        this.c.add(bVar);
        int i = bVar.a;
        InterfaceC0024a interfaceC0024a = this.d;
        if (i == 1) {
            ((w) interfaceC0024a).d(bVar.b, bVar.d);
            return;
        }
        if (i == 2) {
            int i2 = bVar.b;
            int i3 = bVar.d;
            RecyclerView recyclerView = ((w) interfaceC0024a).a;
            recyclerView.R(i2, i3, false);
            recyclerView.p0 = true;
            return;
        }
        if (i == 4) {
            ((w) interfaceC0024a).c(bVar.b, bVar.d, bVar.c);
        } else if (i == 8) {
            ((w) interfaceC0024a).e(bVar.b, bVar.d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:182:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x011f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0112 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x00cf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.a.j():void");
    }

    public final void k(b bVar) {
        bVar.c = null;
        this.a.b(bVar);
    }

    public final void l(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            k((b) arrayList.get(i));
        }
        arrayList.clear();
    }

    public final int m(int i, int i2) {
        int i3;
        int i4;
        ArrayList<b> arrayList = this.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            b bVar = arrayList.get(size);
            int i5 = bVar.a;
            if (i5 == 8) {
                int i6 = bVar.b;
                int i7 = bVar.d;
                if (i6 < i7) {
                    i4 = i6;
                    i3 = i7;
                } else {
                    i3 = i6;
                    i4 = i7;
                }
                if (i < i4 || i > i3) {
                    if (i < i6) {
                        if (i2 == 1) {
                            bVar.b = i6 + 1;
                            bVar.d = i7 + 1;
                        } else if (i2 == 2) {
                            bVar.b = i6 - 1;
                            bVar.d = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        bVar.d = i7 + 1;
                    } else if (i2 == 2) {
                        bVar.d = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        bVar.b = i6 + 1;
                    } else if (i2 == 2) {
                        bVar.b = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = bVar.b;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= bVar.d;
                    } else if (i5 == 2) {
                        i += bVar.d;
                    }
                } else if (i2 == 1) {
                    bVar.b = i8 + 1;
                } else if (i2 == 2) {
                    bVar.b = i8 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            b bVar2 = arrayList.get(size2);
            if (bVar2.a == 8) {
                int i9 = bVar2.d;
                if (i9 == bVar2.b || i9 < 0) {
                    arrayList.remove(size2);
                    k(bVar2);
                }
            } else if (bVar2.d <= 0) {
                arrayList.remove(size2);
                k(bVar2);
            }
        }
        return i;
    }
}

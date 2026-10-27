package N0;

import java.util.ArrayList;
import java.util.List;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: N0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0408c implements Appendable {
    public final StringBuilder l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f4368m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f4369n;
    public final ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f4370p;

    public C0408c() {
        this.l = new StringBuilder(16);
        this.f4368m = new ArrayList();
        this.f4369n = new ArrayList();
        this.o = new ArrayList();
        this.f4370p = new ArrayList();
    }

    public final void a(D d, int i7, int i10) {
        this.f4368m.add(new C0407b(i7, i10, 8, d));
    }

    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence) {
        if (charSequence instanceof C0411f) {
            b((C0411f) charSequence);
        } else {
            this.l.append(charSequence);
        }
        return this;
    }

    public final void b(C0411f c0411f) {
        StringBuilder sb = this.l;
        int length = sb.length();
        sb.append(c0411f.l);
        List list = c0411f.f4373m;
        if (list != null) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                C0409d c0409d = (C0409d) list.get(i7);
                a((D) c0409d.f4371a, c0409d.f4372b + length, c0409d.c + length);
            }
        }
        List list2 = c0411f.f4374n;
        if (list2 != null) {
            int size2 = list2.size();
            for (int i10 = 0; i10 < size2; i10++) {
                C0409d c0409d2 = (C0409d) list2.get(i10);
                this.f4369n.add(new C0407b(c0409d2.f4372b + length, c0409d2.c + length, 8, (u) c0409d2.f4371a));
            }
        }
        List list3 = c0411f.o;
        if (list3 != null) {
            int size3 = list3.size();
            for (int i11 = 0; i11 < size3; i11++) {
                C0409d c0409d3 = (C0409d) list3.get(i11);
                this.o.add(new C0407b(c0409d3.f4371a, c0409d3.f4372b + length, c0409d3.c + length, c0409d3.d));
            }
        }
    }

    public final void c(String str) {
        this.l.append(str);
    }

    public final void d(int i7) {
        ArrayList arrayList = this.f4370p;
        if (i7 >= arrayList.size()) {
            StringBuilder sbM = android.hardware.bydauto.ac.tsimpl.a.m(i7, " should be less than ");
            sbM.append(arrayList.size());
            throw new IllegalStateException(sbM.toString().toString());
        }
        while (arrayList.size() - 1 >= i7) {
            if (arrayList.isEmpty()) {
                throw new IllegalStateException("Nothing to pop.");
            }
            ((C0407b) arrayList.remove(arrayList.size() - 1)).c = this.l.length();
        }
    }

    public final int e(D d) {
        C0407b c0407b = new C0407b(this.l.length(), 0, 12, d);
        this.f4370p.add(c0407b);
        this.f4368m.add(c0407b);
        return r5.size() - 1;
    }

    public final C0411f f() {
        StringBuilder sb = this.l;
        String string = sb.toString();
        ArrayList arrayList = this.f4368m;
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            arrayList2.add(((C0407b) arrayList.get(i7)).a(sb.length()));
        }
        if (arrayList2.isEmpty()) {
            arrayList2 = null;
        }
        ArrayList arrayList3 = this.f4369n;
        ArrayList arrayList4 = new ArrayList(arrayList3.size());
        int size2 = arrayList3.size();
        for (int i10 = 0; i10 < size2; i10++) {
            arrayList4.add(((C0407b) arrayList3.get(i10)).a(sb.length()));
        }
        if (arrayList4.isEmpty()) {
            arrayList4 = null;
        }
        ArrayList arrayList5 = this.o;
        ArrayList arrayList6 = new ArrayList(arrayList5.size());
        int size3 = arrayList5.size();
        for (int i11 = 0; i11 < size3; i11++) {
            arrayList6.add(((C0407b) arrayList5.get(i11)).a(sb.length()));
        }
        return new C0411f(string, arrayList2, arrayList4, arrayList6.isEmpty() ? null : arrayList6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.util.List] */
    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence, int i7, int i10) {
        ?? arrayList;
        ?? arrayList2;
        ?? r52;
        boolean z6 = charSequence instanceof C0411f;
        StringBuilder sb = this.l;
        if (z6) {
            C0411f c0411f = (C0411f) charSequence;
            int length = sb.length();
            sb.append((CharSequence) c0411f.l, i7, i10);
            List listB = AbstractC0412g.b(c0411f, i7, i10);
            if (listB != null) {
                int size = listB.size();
                for (int i11 = 0; i11 < size; i11++) {
                    C0409d c0409d = (C0409d) listB.get(i11);
                    a((D) c0409d.f4371a, c0409d.f4372b + length, c0409d.c + length);
                }
            }
            String str = c0411f.l;
            if (i7 == i10 || (arrayList = c0411f.f4374n) == 0) {
                arrayList = 0;
            } else if (i7 != 0 || i10 < str.length()) {
                ArrayList arrayList3 = new ArrayList(arrayList.size());
                int size2 = arrayList.size();
                for (int i12 = 0; i12 < size2; i12++) {
                    Object obj = arrayList.get(i12);
                    C0409d c0409d2 = (C0409d) obj;
                    if (AbstractC0412g.c(i7, i10, c0409d2.f4372b, c0409d2.c)) {
                        arrayList3.add(obj);
                    }
                }
                arrayList = new ArrayList(arrayList3.size());
                int size3 = arrayList3.size();
                for (int i13 = 0; i13 < size3; i13++) {
                    C0409d c0409d3 = (C0409d) arrayList3.get(i13);
                    arrayList.add(new C0409d(AbstractC2673a.v(c0409d3.f4372b, i7, i10) - i7, AbstractC2673a.v(c0409d3.c, i7, i10) - i7, c0409d3.f4371a));
                }
            }
            if (arrayList != 0) {
                int size4 = arrayList.size();
                for (int i14 = 0; i14 < size4; i14++) {
                    C0409d c0409d4 = (C0409d) arrayList.get(i14);
                    this.f4369n.add(new C0407b(c0409d4.f4372b + length, c0409d4.c + length, 8, (u) c0409d4.f4371a));
                }
            }
            if (i7 == i10 || (arrayList2 = c0411f.o) == 0) {
                r52 = 0;
            } else {
                if (i7 != 0 || i10 < str.length()) {
                    ArrayList arrayList4 = new ArrayList(arrayList2.size());
                    int size5 = arrayList2.size();
                    for (int i15 = 0; i15 < size5; i15++) {
                        Object obj2 = arrayList2.get(i15);
                        C0409d c0409d5 = (C0409d) obj2;
                        if (AbstractC0412g.c(i7, i10, c0409d5.f4372b, c0409d5.c)) {
                            arrayList4.add(obj2);
                        }
                    }
                    arrayList2 = new ArrayList(arrayList4.size());
                    int size6 = arrayList4.size();
                    for (int i16 = 0; i16 < size6; i16++) {
                        C0409d c0409d6 = (C0409d) arrayList4.get(i16);
                        arrayList2.add(new C0409d(c0409d6.f4371a, AbstractC2673a.v(c0409d6.f4372b, i7, i10) - i7, AbstractC2673a.v(c0409d6.c, i7, i10) - i7, c0409d6.d));
                    }
                }
                r52 = arrayList2;
            }
            if (r52 != 0) {
                int size7 = r52.size();
                for (int i17 = 0; i17 < size7; i17++) {
                    C0409d c0409d7 = (C0409d) r52.get(i17);
                    this.o.add(new C0407b(c0409d7.f4371a, c0409d7.f4372b + length, c0409d7.c + length, c0409d7.d));
                }
            }
        } else {
            sb.append(charSequence, i7, i10);
        }
        return this;
    }

    public C0408c(C0411f c0411f) {
        this();
        b(c0411f);
    }

    @Override // java.lang.Appendable
    public final Appendable append(char c) {
        this.l.append(c);
        return this;
    }
}

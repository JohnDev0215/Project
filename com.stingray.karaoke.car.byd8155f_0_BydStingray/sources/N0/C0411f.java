package N0;

import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: N0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0411f implements CharSequence {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f4373m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List f4374n;
    public final List o;

    static {
        io.sentry.internal.debugmeta.c cVar = C.f4308a;
    }

    public C0411f(String str, List list, List list2, List list3) {
        this.l = str;
        this.f4373m = list;
        this.f4374n = list2;
        this.o = list3;
        if (list2 != null) {
            List listU0 = AbstractC1998m.u0(list2, new C0410e());
            int size = listU0.size();
            int i7 = -1;
            int i10 = 0;
            while (i10 < size) {
                C0409d c0409d = (C0409d) listU0.get(i10);
                if (c0409d.f4372b < i7) {
                    throw new IllegalArgumentException("ParagraphStyle should not overlap");
                }
                int length = this.l.length();
                int i11 = c0409d.c;
                if (i11 > length) {
                    throw new IllegalArgumentException(("ParagraphStyle range [" + c0409d.f4372b + ", " + i11 + ") is out of boundary").toString());
                }
                i10++;
                i7 = i11;
            }
        }
    }

    public final List a(int i7) {
        List list = this.o;
        if (list == null) {
            return n7.u.l;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = list.get(i10);
            C0409d c0409d = (C0409d) obj;
            if ((c0409d.f4371a instanceof AbstractC0418m) && AbstractC0412g.c(0, i7, c0409d.f4372b, c0409d.c)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final List b() {
        List list = this.f4373m;
        return list == null ? n7.u.l : list;
    }

    @Override // java.lang.CharSequence
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0411f subSequence(int i7, int i10) {
        if (i7 > i10) {
            throw new IllegalArgumentException(("start (" + i7 + ") should be less or equal to end (" + i10 + ')').toString());
        }
        String str = this.l;
        if (i7 == 0 && i10 == str.length()) {
            return this;
        }
        String strSubstring = str.substring(i7, i10);
        A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return new C0411f(strSubstring, AbstractC0412g.a(i7, i10, this.f4373m), AbstractC0412g.a(i7, i10, this.f4374n), AbstractC0412g.a(i7, i10, this.o));
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i7) {
        return this.l.charAt(i7);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0411f)) {
            return false;
        }
        C0411f c0411f = (C0411f) obj;
        return A7.m.a(this.l, c0411f.l) && A7.m.a(this.f4373m, c0411f.f4373m) && A7.m.a(this.f4374n, c0411f.f4374n) && A7.m.a(this.o, c0411f.o);
    }

    public final int hashCode() {
        int iHashCode = this.l.hashCode() * 31;
        List list = this.f4373m;
        int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
        List list2 = this.f4374n;
        int iHashCode3 = (iHashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31;
        List list3 = this.o;
        return iHashCode3 + (list3 != null ? list3.hashCode() : 0);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.l.length();
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.l;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C0411f(String str, ArrayList arrayList, int i7) {
        List list = (i7 & 2) != 0 ? n7.u.l : arrayList;
        this(str, list.isEmpty() ? null : list, null, null);
    }
}

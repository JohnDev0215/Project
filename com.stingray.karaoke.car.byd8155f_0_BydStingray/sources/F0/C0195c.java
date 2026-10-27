package F0;

import java.text.BreakIterator;

/* JADX INFO: renamed from: F0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0195c extends AbstractC0193b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static C0195c f2180p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static C0195c f2181q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static C0195c f2182r;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f2183n;
    public Object o;

    @Override // F0.AbstractC0193b
    public final int[] a(int i7) {
        int iF;
        switch (this.f2183n) {
            case 0:
                int length = c().length();
                if (length <= 0 || i7 >= length) {
                    return null;
                }
                if (i7 < 0) {
                    i7 = 0;
                }
                do {
                    BreakIterator breakIterator = (BreakIterator) this.o;
                    if (breakIterator == null) {
                        A7.m.j("impl");
                        throw null;
                    }
                    if (breakIterator.isBoundary(i7)) {
                        BreakIterator breakIterator2 = (BreakIterator) this.o;
                        if (breakIterator2 == null) {
                            A7.m.j("impl");
                            throw null;
                        }
                        int iFollowing = breakIterator2.following(i7);
                        if (iFollowing == -1) {
                            return null;
                        }
                        return b(i7, iFollowing);
                    }
                    BreakIterator breakIterator3 = (BreakIterator) this.o;
                    if (breakIterator3 == null) {
                        A7.m.j("impl");
                        throw null;
                    }
                    i7 = breakIterator3.following(i7);
                } while (i7 != -1);
                return null;
            case 1:
                if (c().length() <= 0 || i7 >= c().length()) {
                    return null;
                }
                if (i7 < 0) {
                    i7 = 0;
                }
                while (!h(i7) && (!h(i7) || (i7 != 0 && h(i7 - 1)))) {
                    BreakIterator breakIterator4 = (BreakIterator) this.o;
                    if (breakIterator4 == null) {
                        A7.m.j("impl");
                        throw null;
                    }
                    i7 = breakIterator4.following(i7);
                    if (i7 == -1) {
                        return null;
                    }
                }
                BreakIterator breakIterator5 = (BreakIterator) this.o;
                if (breakIterator5 == null) {
                    A7.m.j("impl");
                    throw null;
                }
                int iFollowing2 = breakIterator5.following(i7);
                if (iFollowing2 == -1 || !g(iFollowing2)) {
                    return null;
                }
                return b(i7, iFollowing2);
            default:
                if (c().length() <= 0 || i7 >= c().length()) {
                    return null;
                }
                Y0.h hVar = Y0.h.f10616m;
                if (i7 < 0) {
                    N0.H h10 = (N0.H) this.o;
                    if (h10 == null) {
                        A7.m.j("layoutResult");
                        throw null;
                    }
                    iF = h10.f(0);
                } else {
                    N0.H h11 = (N0.H) this.o;
                    if (h11 == null) {
                        A7.m.j("layoutResult");
                        throw null;
                    }
                    int iF2 = h11.f(i7);
                    iF = e(iF2, hVar) == i7 ? iF2 : iF2 + 1;
                }
                N0.H h12 = (N0.H) this.o;
                if (h12 == null) {
                    A7.m.j("layoutResult");
                    throw null;
                }
                if (iF >= h12.f4349b.f4394f) {
                    return null;
                }
                return b(e(iF, hVar), e(iF, Y0.h.l) + 1);
        }
    }

    @Override // F0.AbstractC0193b
    public final int[] d(int i7) {
        int iF;
        switch (this.f2183n) {
            case 0:
                int length = c().length();
                if (length <= 0 || i7 <= 0) {
                    return null;
                }
                if (i7 > length) {
                    i7 = length;
                }
                do {
                    BreakIterator breakIterator = (BreakIterator) this.o;
                    if (breakIterator == null) {
                        A7.m.j("impl");
                        throw null;
                    }
                    if (breakIterator.isBoundary(i7)) {
                        BreakIterator breakIterator2 = (BreakIterator) this.o;
                        if (breakIterator2 == null) {
                            A7.m.j("impl");
                            throw null;
                        }
                        int iPreceding = breakIterator2.preceding(i7);
                        if (iPreceding == -1) {
                            return null;
                        }
                        return b(iPreceding, i7);
                    }
                    BreakIterator breakIterator3 = (BreakIterator) this.o;
                    if (breakIterator3 == null) {
                        A7.m.j("impl");
                        throw null;
                    }
                    i7 = breakIterator3.preceding(i7);
                } while (i7 != -1);
                return null;
            case 1:
                int length2 = c().length();
                if (length2 <= 0 || i7 <= 0) {
                    return null;
                }
                if (i7 > length2) {
                    i7 = length2;
                }
                while (i7 > 0 && !h(i7 - 1) && !g(i7)) {
                    BreakIterator breakIterator4 = (BreakIterator) this.o;
                    if (breakIterator4 == null) {
                        A7.m.j("impl");
                        throw null;
                    }
                    i7 = breakIterator4.preceding(i7);
                    if (i7 == -1) {
                        return null;
                    }
                }
                BreakIterator breakIterator5 = (BreakIterator) this.o;
                if (breakIterator5 == null) {
                    A7.m.j("impl");
                    throw null;
                }
                int iPreceding2 = breakIterator5.preceding(i7);
                if (iPreceding2 == -1 || !h(iPreceding2)) {
                    return null;
                }
                if (iPreceding2 == 0 || !h(iPreceding2 - 1)) {
                    return b(iPreceding2, i7);
                }
                return null;
            default:
                if (c().length() <= 0 || i7 <= 0) {
                    return null;
                }
                int length3 = c().length();
                Y0.h hVar = Y0.h.l;
                if (i7 > length3) {
                    N0.H h10 = (N0.H) this.o;
                    if (h10 == null) {
                        A7.m.j("layoutResult");
                        throw null;
                    }
                    iF = h10.f(c().length());
                } else {
                    N0.H h11 = (N0.H) this.o;
                    if (h11 == null) {
                        A7.m.j("layoutResult");
                        throw null;
                    }
                    int iF2 = h11.f(i7);
                    iF = e(iF2, hVar) + 1 == i7 ? iF2 : iF2 - 1;
                }
                if (iF < 0) {
                    return null;
                }
                return b(e(iF, Y0.h.f10616m), e(iF, hVar) + 1);
        }
    }

    public int e(int i7, Y0.h hVar) {
        N0.H h10 = (N0.H) this.o;
        if (h10 == null) {
            A7.m.j("layoutResult");
            throw null;
        }
        int i10 = h10.i(i7);
        N0.H h11 = (N0.H) this.o;
        if (h11 == null) {
            A7.m.j("layoutResult");
            throw null;
        }
        if (hVar != h11.j(i10)) {
            N0.H h12 = (N0.H) this.o;
            if (h12 != null) {
                return h12.i(i7);
            }
            A7.m.j("layoutResult");
            throw null;
        }
        if (((N0.H) this.o) != null) {
            return r4.e(i7, false) - 1;
        }
        A7.m.j("layoutResult");
        throw null;
    }

    public void f(String str) {
        switch (this.f2183n) {
            case 0:
                this.l = str;
                BreakIterator breakIterator = (BreakIterator) this.o;
                if (breakIterator != null) {
                    breakIterator.setText(str);
                    return;
                } else {
                    A7.m.j("impl");
                    throw null;
                }
            default:
                this.l = str;
                BreakIterator breakIterator2 = (BreakIterator) this.o;
                if (breakIterator2 != null) {
                    breakIterator2.setText(str);
                    return;
                } else {
                    A7.m.j("impl");
                    throw null;
                }
        }
    }

    public boolean g(int i7) {
        return i7 > 0 && h(i7 + (-1)) && (i7 == c().length() || !h(i7));
    }

    public boolean h(int i7) {
        if (i7 < 0 || i7 >= c().length()) {
            return false;
        }
        return Character.isLetterOrDigit(c().codePointAt(i7));
    }
}

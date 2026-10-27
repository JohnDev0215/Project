package b.g.i;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.g.i.d f1769a = new C0039e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.g.i.d f1770b = new C0039e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.g.i.d f1771c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.g.i.d f1772d;
    public static final b.g.i.d e;
    public static final b.g.i.d f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final a f1773a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f1774b;

        private a(boolean z) {
            this.f1774b = z;
        }

        @Override // b.g.i.e.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            boolean z = false;
            while (i < i3) {
                int iA = e.a(Character.getDirectionality(charSequence.charAt(i)));
                if (iA != 0) {
                    if (iA != 1) {
                        continue;
                        i++;
                        z = z;
                    } else if (!this.f1774b) {
                        return 1;
                    }
                } else if (this.f1774b) {
                    return 0;
                }
                z = true;
                i++;
                z = z;
            }
            if (z) {
                return this.f1774b ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f1775a = new b();

        private b() {
        }

        @Override // b.g.i.e.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            int iB = 2;
            while (i < i3 && iB == 2) {
                iB = e.b(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return iB;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private interface c {
        int a(CharSequence charSequence, int i, int i2);
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static abstract class d implements b.g.i.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c f1776a;

        d(c cVar) {
            this.f1776a = cVar;
        }

        private boolean c(CharSequence charSequence, int i, int i2) {
            int iA = this.f1776a.a(charSequence, i, i2);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return b();
            }
            return false;
        }

        @Override // b.g.i.d
        public boolean a(CharSequence charSequence, int i, int i2) {
            if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            }
            return this.f1776a == null ? b() : c(charSequence, i, i2);
        }

        protected abstract boolean b();
    }

    /* JADX INFO: renamed from: b.g.i.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class C0039e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f1777b;

        C0039e(c cVar, boolean z) {
            super(cVar);
            this.f1777b = z;
        }

        @Override // b.g.i.e.d
        protected boolean b() {
            return this.f1777b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final f f1778b = new f();

        f() {
            super(null);
        }

        @Override // b.g.i.e.d
        protected boolean b() {
            return b.g.i.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f1775a;
        f1771c = new C0039e(bVar, false);
        f1772d = new C0039e(bVar, true);
        e = new C0039e(a.f1773a, false);
        f = f.f1778b;
    }

    static int a(int i) {
        if (i != 0) {
            return (i == 1 || i == 2) ? 0 : 2;
        }
        return 1;
    }

    static int b(int i) {
        if (i != 0) {
            if (i == 1 || i == 2) {
                return 0;
            }
            switch (i) {
                case 14:
                case 15:
                    break;
                case 16:
                case 17:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}

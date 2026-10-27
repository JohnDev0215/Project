package b.g.i;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.g.i.d f2278a = new C0051e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.g.i.d f2279b = new C0051e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.g.i.d f2280c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.g.i.d f2281d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.g.i.d f2282e;
    public static final b.g.i.d f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final a f2283a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f2284b;

        private a(boolean z) {
            this.f2284b = z;
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
                    } else if (!this.f2284b) {
                        return 1;
                    }
                } else if (this.f2284b) {
                    return 0;
                }
                z = true;
                i++;
                z = z;
            }
            if (z) {
                return this.f2284b ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f2285a = new b();

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
        private final c f2286a;

        d(c cVar) {
            this.f2286a = cVar;
        }

        private boolean c(CharSequence charSequence, int i, int i2) {
            int iA = this.f2286a.a(charSequence, i, i2);
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
            return this.f2286a == null ? b() : c(charSequence, i, i2);
        }

        protected abstract boolean b();
    }

    /* JADX INFO: renamed from: b.g.i.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class C0051e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f2287b;

        C0051e(c cVar, boolean z) {
            super(cVar);
            this.f2287b = z;
        }

        @Override // b.g.i.e.d
        protected boolean b() {
            return this.f2287b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final f f2288b = new f();

        f() {
            super(null);
        }

        @Override // b.g.i.e.d
        protected boolean b() {
            return b.g.i.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f2285a;
        f2280c = new C0051e(bVar, false);
        f2281d = new C0051e(bVar, true);
        f2282e = new C0051e(a.f2283a, false);
        f = f.f2288b;
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

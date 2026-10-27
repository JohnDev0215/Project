package b.g.i;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.g.i.d f2489a = new C0038e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.g.i.d f2490b = new C0038e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.g.i.d f2491c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.g.i.d f2492d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.g.i.d f2493e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.g.i.d f2494f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final a f2495a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f2496b;

        private a(boolean z) {
            this.f2496b = z;
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
                    } else if (!this.f2496b) {
                        return 1;
                    }
                } else if (this.f2496b) {
                    return 0;
                }
                z = true;
                i++;
                z = z;
            }
            if (z) {
                return this.f2496b ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f2497a = new b();

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
        private final c f2498a;

        d(c cVar) {
            this.f2498a = cVar;
        }

        private boolean c(CharSequence charSequence, int i, int i2) {
            int iA = this.f2498a.a(charSequence, i, i2);
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
            return this.f2498a == null ? b() : c(charSequence, i, i2);
        }

        protected abstract boolean b();
    }

    /* JADX INFO: renamed from: b.g.i.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class C0038e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f2499b;

        C0038e(c cVar, boolean z) {
            super(cVar);
            this.f2499b = z;
        }

        @Override // b.g.i.e.d
        protected boolean b() {
            return this.f2499b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final f f2500b = new f();

        f() {
            super(null);
        }

        @Override // b.g.i.e.d
        protected boolean b() {
            return b.g.i.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f2497a;
        f2491c = new C0038e(bVar, false);
        f2492d = new C0038e(bVar, true);
        f2493e = new C0038e(a.f2495a, false);
        f2494f = f.f2500b;
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

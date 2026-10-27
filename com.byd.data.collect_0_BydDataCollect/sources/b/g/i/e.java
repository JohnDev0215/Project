package b.g.i;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.g.i.d f2644a = new C0044e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.g.i.d f2645b = new C0044e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.g.i.d f2646c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.g.i.d f2647d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.g.i.d f2648e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.g.i.d f2649f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final a f2650a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f2651b;

        private a(boolean z) {
            this.f2651b = z;
        }

        @Override // b.g.i.e.c
        public int a(CharSequence charSequence, int i2, int i3) {
            int i4 = i3 + i2;
            boolean z = false;
            while (i2 < i4) {
                int iA = e.a(Character.getDirectionality(charSequence.charAt(i2)));
                if (iA != 0) {
                    if (iA != 1) {
                        continue;
                        i2++;
                        z = z;
                    } else if (!this.f2651b) {
                        return 1;
                    }
                } else if (this.f2651b) {
                    return 0;
                }
                z = true;
                i2++;
                z = z;
            }
            if (z) {
                return this.f2651b ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f2652a = new b();

        private b() {
        }

        @Override // b.g.i.e.c
        public int a(CharSequence charSequence, int i2, int i3) {
            int i4 = i3 + i2;
            int iB = 2;
            while (i2 < i4 && iB == 2) {
                iB = e.b(Character.getDirectionality(charSequence.charAt(i2)));
                i2++;
            }
            return iB;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private interface c {
        int a(CharSequence charSequence, int i2, int i3);
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static abstract class d implements b.g.i.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c f2653a;

        d(c cVar) {
            this.f2653a = cVar;
        }

        private boolean c(CharSequence charSequence, int i2, int i3) {
            int iA = this.f2653a.a(charSequence, i2, i3);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return b();
            }
            return false;
        }

        @Override // b.g.i.d
        public boolean a(CharSequence charSequence, int i2, int i3) {
            if (charSequence == null || i2 < 0 || i3 < 0 || charSequence.length() - i3 < i2) {
                throw new IllegalArgumentException();
            }
            return this.f2653a == null ? b() : c(charSequence, i2, i3);
        }

        protected abstract boolean b();
    }

    /* JADX INFO: renamed from: b.g.i.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class C0044e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f2654b;

        C0044e(c cVar, boolean z) {
            super(cVar);
            this.f2654b = z;
        }

        @Override // b.g.i.e.d
        protected boolean b() {
            return this.f2654b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final f f2655b = new f();

        f() {
            super(null);
        }

        @Override // b.g.i.e.d
        protected boolean b() {
            return b.g.i.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f2652a;
        f2646c = new C0044e(bVar, false);
        f2647d = new C0044e(bVar, true);
        f2648e = new C0044e(a.f2650a, false);
        f2649f = f.f2655b;
    }

    static int a(int i2) {
        if (i2 != 0) {
            return (i2 == 1 || i2 == 2) ? 0 : 2;
        }
        return 1;
    }

    static int b(int i2) {
        if (i2 != 0) {
            if (i2 == 1 || i2 == 2) {
                return 0;
            }
            switch (i2) {
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

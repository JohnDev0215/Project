package g0;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g0.c f6450a = new e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g0.c f6451b = new e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g0.c f6452c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final g0.c f6453d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final g0.c f6454e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g0.c f6455f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class a implements c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final a f6456b = new a(true);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f6457a;

        private a(boolean z6) {
            this.f6457a = z6;
        }

        @Override // g0.d.c
        public int a(CharSequence charSequence, int i7, int i8) {
            int i9 = i8 + i7;
            boolean z6 = false;
            while (i7 < i9) {
                int iA = d.a(Character.getDirectionality(charSequence.charAt(i7)));
                if (iA != 0) {
                    if (iA != 1) {
                        continue;
                        i7++;
                        z6 = z6;
                    } else if (!this.f6457a) {
                        return 1;
                    }
                } else if (this.f6457a) {
                    return 0;
                }
                z6 = true;
                i7++;
                z6 = z6;
            }
            if (z6) {
                return this.f6457a ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f6458a = new b();

        private b() {
        }

        @Override // g0.d.c
        public int a(CharSequence charSequence, int i7, int i8) {
            int i9 = i8 + i7;
            int iB = 2;
            while (i7 < i9 && iB == 2) {
                iB = d.b(Character.getDirectionality(charSequence.charAt(i7)));
                i7++;
            }
            return iB;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private interface c {
        int a(CharSequence charSequence, int i7, int i8);
    }

    /* JADX INFO: renamed from: g0.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static abstract class AbstractC0078d implements g0.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c f6459a;

        AbstractC0078d(c cVar) {
            this.f6459a = cVar;
        }

        private boolean c(CharSequence charSequence, int i7, int i8) {
            int iA = this.f6459a.a(charSequence, i7, i8);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return b();
            }
            return false;
        }

        @Override // g0.c
        public boolean a(CharSequence charSequence, int i7, int i8) {
            if (charSequence == null || i7 < 0 || i8 < 0 || charSequence.length() - i8 < i7) {
                throw new IllegalArgumentException();
            }
            return this.f6459a == null ? b() : c(charSequence, i7, i8);
        }

        protected abstract boolean b();
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class e extends AbstractC0078d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f6460b;

        e(c cVar, boolean z6) {
            super(cVar);
            this.f6460b = z6;
        }

        @Override // g0.d.AbstractC0078d
        protected boolean b() {
            return this.f6460b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    private static class f extends AbstractC0078d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final f f6461b = new f();

        f() {
            super(null);
        }

        @Override // g0.d.AbstractC0078d
        protected boolean b() {
            return g0.e.a(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f6458a;
        f6452c = new e(bVar, false);
        f6453d = new e(bVar, true);
        f6454e = new e(a.f6456b, false);
        f6455f = f.f6461b;
    }

    static int a(int i7) {
        if (i7 != 0) {
            return (i7 == 1 || i7 == 2) ? 0 : 2;
        }
        return 1;
    }

    static int b(int i7) {
        if (i7 != 0) {
            if (i7 == 1 || i7 == 2) {
                return 0;
            }
            switch (i7) {
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

package j0;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j0.d f3426a = new C0026e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j0.d f3427b = new C0026e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j0.d f3428c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j0.d f3429d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j0.d f3430e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final j0.d f3431f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f3432a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f3433b;

        public a(boolean z3) {
            this.f3433b = z3;
        }

        @Override // j0.e.c
        public int a(CharSequence charSequence, int i3, int i4) {
            int i5 = i4 + i3;
            boolean z3 = false;
            while (i3 < i5) {
                int iA = e.a(Character.getDirectionality(charSequence.charAt(i3)));
                if (iA != 0) {
                    if (iA != 1) {
                        continue;
                        i3++;
                        z3 = z3;
                    } else if (!this.f3433b) {
                        return 1;
                    }
                } else if (this.f3433b) {
                    return 0;
                }
                z3 = true;
                i3++;
                z3 = z3;
            }
            if (z3) {
                return this.f3433b ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f3434a = new b();

        @Override // j0.e.c
        public int a(CharSequence charSequence, int i3, int i4) {
            int i5 = i4 + i3;
            int iB = 2;
            while (i3 < i5 && iB == 2) {
                iB = e.b(Character.getDirectionality(charSequence.charAt(i3)));
                i3++;
            }
            return iB;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public interface c {
        int a(CharSequence charSequence, int i3, int i4);
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static abstract class d implements j0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f3435a;

        public d(c cVar) {
            this.f3435a = cVar;
        }

        @Override // j0.d
        public boolean a(CharSequence charSequence, int i3, int i4) {
            if (charSequence == null || i3 < 0 || i4 < 0 || charSequence.length() - i4 < i3) {
                throw new IllegalArgumentException();
            }
            return this.f3435a == null ? b() : c(charSequence, i3, i4);
        }

        public abstract boolean b();

        public final boolean c(CharSequence charSequence, int i3, int i4) {
            int iA = this.f3435a.a(charSequence, i3, i4);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return b();
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: j0.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class C0026e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f3436b;

        public C0026e(c cVar, boolean z3) {
            super(cVar);
            this.f3436b = z3;
        }

        @Override // j0.e.d
        public boolean b() {
            return this.f3436b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final f f3437b = new f();

        public f() {
            super(null);
        }

        @Override // j0.e.d
        public boolean b() {
            return j0.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f3434a;
        f3428c = new C0026e(bVar, false);
        f3429d = new C0026e(bVar, true);
        f3430e = new C0026e(a.f3432a, false);
        f3431f = f.f3437b;
    }

    public static int a(int i3) {
        if (i3 != 0) {
            return (i3 == 1 || i3 == 2) ? 0 : 2;
        }
        return 1;
    }

    public static int b(int i3) {
        if (i3 != 0) {
            if (i3 == 1 || i3 == 2) {
                return 0;
            }
            switch (i3) {
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

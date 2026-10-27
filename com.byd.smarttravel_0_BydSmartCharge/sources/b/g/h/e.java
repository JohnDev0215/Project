package b.g.h;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.g.h.d f1072a = new C0021e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.g.h.d f1073b = new C0021e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.g.h.d f1074c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.g.h.d f1075d;
    public static final b.g.h.d e;
    public static final b.g.h.d f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f1076a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1077b;

        public a(boolean z) {
            this.f1077b = z;
        }

        @Override // b.g.h.e.c
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
                    } else if (!this.f1077b) {
                        return 1;
                    }
                } else if (this.f1077b) {
                    return 0;
                }
                z = true;
                i++;
                z = z;
            }
            if (z) {
                return this.f1077b ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f1078a = new b();

        @Override // b.g.h.e.c
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
    public interface c {
        int a(CharSequence charSequence, int i, int i2);
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static abstract class d implements b.g.h.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f1079a;

        public d(c cVar) {
            this.f1079a = cVar;
        }

        @Override // b.g.h.d
        public boolean a(CharSequence charSequence, int i, int i2) {
            if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            }
            return this.f1079a == null ? b() : c(charSequence, i, i2);
        }

        public abstract boolean b();

        public final boolean c(CharSequence charSequence, int i, int i2) {
            int iA = this.f1079a.a(charSequence, i, i2);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return b();
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: b.g.h.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class C0021e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1080b;

        public C0021e(c cVar, boolean z) {
            super(cVar);
            this.f1080b = z;
        }

        @Override // b.g.h.e.d
        public boolean b() {
            return this.f1080b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final f f1081b = new f();

        public f() {
            super(null);
        }

        @Override // b.g.h.e.d
        public boolean b() {
            return b.g.h.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f1078a;
        f1074c = new C0021e(bVar, false);
        f1075d = new C0021e(bVar, true);
        e = new C0021e(a.f1076a, false);
        f = f.f1081b;
    }

    public static int a(int i) {
        if (i != 0) {
            return (i == 1 || i == 2) ? 0 : 2;
        }
        return 1;
    }

    public static int b(int i) {
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

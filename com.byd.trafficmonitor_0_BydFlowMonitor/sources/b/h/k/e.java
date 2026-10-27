package b.h.k;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.h.k.d f2562a = new C0041e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.h.k.d f2563b = new C0041e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.h.k.d f2564c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.h.k.d f2565d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.h.k.d f2566e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.h.k.d f2567f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f2568a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f2569b;

        public a(boolean z) {
            this.f2569b = z;
        }

        @Override // b.h.k.e.c
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
                    } else if (!this.f2569b) {
                        return 1;
                    }
                } else if (this.f2569b) {
                    return 0;
                }
                z = true;
                i2++;
                z = z;
            }
            if (z) {
                return this.f2569b ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f2570a = new b();

        @Override // b.h.k.e.c
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
    public interface c {
        int a(CharSequence charSequence, int i2, int i3);
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static abstract class d implements b.h.k.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f2571a;

        public d(c cVar) {
            this.f2571a = cVar;
        }

        @Override // b.h.k.d
        public boolean a(CharSequence charSequence, int i2, int i3) {
            if (charSequence == null || i2 < 0 || i3 < 0 || charSequence.length() - i3 < i2) {
                throw new IllegalArgumentException();
            }
            return this.f2571a == null ? b() : c(charSequence, i2, i3);
        }

        public abstract boolean b();

        public final boolean c(CharSequence charSequence, int i2, int i3) {
            int iA = this.f2571a.a(charSequence, i2, i3);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return b();
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: b.h.k.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class C0041e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f2572b;

        public C0041e(c cVar, boolean z) {
            super(cVar);
            this.f2572b = z;
        }

        @Override // b.h.k.e.d
        public boolean b() {
            return this.f2572b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final f f2573b = new f();

        public f() {
            super(null);
        }

        @Override // b.h.k.e.d
        public boolean b() {
            return b.h.k.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f2570a;
        f2564c = new C0041e(bVar, false);
        f2565d = new C0041e(bVar, true);
        f2566e = new C0041e(a.f2568a, false);
        f2567f = f.f2573b;
    }

    public static int a(int i2) {
        if (i2 != 0) {
            return (i2 == 1 || i2 == 2) ? 0 : 2;
        }
        return 1;
    }

    public static int b(int i2) {
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

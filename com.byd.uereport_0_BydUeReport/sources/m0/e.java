package m0;

import java.util.Locale;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m0.d f6327a = new C0088e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m0.d f6328b = new C0088e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m0.d f6329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m0.d f6330d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m0.d f6331e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final m0.d f6332f;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6333a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f6334b;

        public a(boolean z7) {
            this.f6334b = z7;
        }

        @Override // m0.e.c
        public int a(CharSequence charSequence, int i8, int i9) {
            int i10 = i9 + i8;
            boolean z7 = false;
            while (i8 < i10) {
                int iA = e.a(Character.getDirectionality(charSequence.charAt(i8)));
                if (iA != 0) {
                    if (iA != 1) {
                        continue;
                        i8++;
                        z7 = z7;
                    } else if (!this.f6334b) {
                        return 1;
                    }
                } else if (this.f6334b) {
                    return 0;
                }
                z7 = true;
                i8++;
                z7 = z7;
            }
            if (z7) {
                return this.f6334b ? 1 : 0;
            }
            return 2;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6335a = new b();

        @Override // m0.e.c
        public int a(CharSequence charSequence, int i8, int i9) {
            int i10 = i9 + i8;
            int iB = 2;
            while (i8 < i10 && iB == 2) {
                iB = e.b(Character.getDirectionality(charSequence.charAt(i8)));
                i8++;
            }
            return iB;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public interface c {
        int a(CharSequence charSequence, int i8, int i9);
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static abstract class d implements m0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f6336a;

        public d(c cVar) {
            this.f6336a = cVar;
        }

        @Override // m0.d
        public boolean a(CharSequence charSequence, int i8, int i9) {
            if (charSequence == null || i8 < 0 || i9 < 0 || charSequence.length() - i9 < i8) {
                throw new IllegalArgumentException();
            }
            return this.f6336a == null ? b() : c(charSequence, i8, i9);
        }

        public abstract boolean b();

        public final boolean c(CharSequence charSequence, int i8, int i9) {
            int iA = this.f6336a.a(charSequence, i8, i9);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return b();
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: m0.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class C0088e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f6337b;

        public C0088e(c cVar, boolean z7) {
            super(cVar);
            this.f6337b = z7;
        }

        @Override // m0.e.d
        public boolean b() {
            return this.f6337b;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final f f6338b = new f();

        public f() {
            super(null);
        }

        @Override // m0.e.d
        public boolean b() {
            return m0.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f6335a;
        f6329c = new C0088e(bVar, false);
        f6330d = new C0088e(bVar, true);
        f6331e = new C0088e(a.f6333a, false);
        f6332f = f.f6338b;
    }

    public static int a(int i8) {
        if (i8 != 0) {
            return (i8 == 1 || i8 == 2) ? 0 : 2;
        }
        return 1;
    }

    public static int b(int i8) {
        if (i8 != 0) {
            if (i8 == 1 || i8 == 2) {
                return 0;
            }
            switch (i8) {
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

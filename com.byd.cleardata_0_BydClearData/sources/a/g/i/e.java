package a.g.i;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a.g.i.d f353a = new C0011e(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a.g.i.d f354b = new C0011e(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a.g.i.d f355c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a.g.i.d f356d;
    public static final a.g.i.d e;
    public static final a.g.i.d f;

    private static class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final a f357a = new a(true);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f358b;

        private a(boolean z) {
            this.f358b = z;
        }

        @Override // a.g.i.e.c
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
                    } else if (!this.f358b) {
                        return 1;
                    }
                } else if (this.f358b) {
                    return 0;
                }
                z = true;
                i++;
                z = z;
            }
            if (z) {
                return this.f358b ? 1 : 0;
            }
            return 2;
        }
    }

    private static class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f359a = new b();

        private b() {
        }

        @Override // a.g.i.e.c
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

    private interface c {
        int a(CharSequence charSequence, int i, int i2);
    }

    private static abstract class d implements a.g.i.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c f360a;

        d(c cVar) {
            this.f360a = cVar;
        }

        private boolean c(CharSequence charSequence, int i, int i2) {
            int iA = this.f360a.a(charSequence, i, i2);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return b();
            }
            return false;
        }

        @Override // a.g.i.d
        public boolean a(CharSequence charSequence, int i, int i2) {
            if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            }
            return this.f360a == null ? b() : c(charSequence, i, i2);
        }

        protected abstract boolean b();
    }

    /* JADX INFO: renamed from: a.g.i.e$e, reason: collision with other inner class name */
    private static class C0011e extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f361b;

        C0011e(c cVar, boolean z) {
            super(cVar);
            this.f361b = z;
        }

        @Override // a.g.i.e.d
        protected boolean b() {
            return this.f361b;
        }
    }

    private static class f extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final f f362b = new f();

        f() {
            super(null);
        }

        @Override // a.g.i.e.d
        protected boolean b() {
            return a.g.i.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f359a;
        f355c = new C0011e(bVar, false);
        f356d = new C0011e(bVar, true);
        e = new C0011e(a.f357a, false);
        f = f.f362b;
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

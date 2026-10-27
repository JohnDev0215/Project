package defpackage;

/* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b83 {
    public static final d a = new d(null, false);
    public static final d b = new d(null, true);
    public static final d c;
    public static final d d;

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class a implements b {
        public static final a a = new a();

        /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
        @Override // b83.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int a(java.lang.CharSequence r5, int r6) {
            /*
                r4 = this;
                r4 = 0
                int r6 = r6 + r4
                r0 = 2
                r1 = r4
                r2 = r0
            L5:
                if (r1 >= r6) goto L25
                if (r2 != r0) goto L25
                char r2 = r5.charAt(r1)
                byte r2 = java.lang.Character.getDirectionality(r2)
                b83$d r3 = defpackage.b83.a
                r3 = 1
                if (r2 == 0) goto L21
                if (r2 == r3) goto L1f
                if (r2 == r0) goto L1f
                switch(r2) {
                    case 14: goto L21;
                    case 15: goto L21;
                    case 16: goto L1f;
                    case 17: goto L1f;
                    default: goto L1d;
                }
            L1d:
                r2 = r0
                goto L22
            L1f:
                r2 = r4
                goto L22
            L21:
                r2 = r3
            L22:
                int r1 = r1 + 1
                goto L5
            L25:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: b83.a.a(java.lang.CharSequence, int):int");
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public interface b {
        int a(CharSequence charSequence, int i);
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static abstract class c implements a83 {
        public final b a;

        public c(a aVar) {
            this.a = aVar;
        }

        public abstract boolean a();

        public final boolean b(CharSequence charSequence, int i) {
            if (charSequence == null || i < 0 || charSequence.length() - i < 0) {
                throw new IllegalArgumentException();
            }
            b bVar = this.a;
            if (bVar == null) {
                return a();
            }
            int iA = bVar.a(charSequence, i);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return a();
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: TextDirectionHeuristicsCompat.java */
    public static class d extends c {
        public final boolean b;

        public d(a aVar, boolean z) {
            super(aVar);
            this.b = z;
        }

        @Override // b83.c
        public final boolean a() {
            return this.b;
        }
    }

    static {
        a aVar = a.a;
        c = new d(aVar, false);
        d = new d(aVar, true);
    }
}

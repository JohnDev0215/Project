package a.h.i;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a.h.i.c f791a = new C0014d(null, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a.h.i.c f792b = new C0014d(null, true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a.h.i.c f793c = new C0014d(a.f795a, false);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a.h.i.c f794d = new C0014d(a.f795a, true);

    public static class a implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f795a = new a();

        @Override // a.h.i.d.b
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            int iA = 2;
            while (i < i3 && iA == 2) {
                iA = d.a(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return iA;
        }
    }

    public interface b {
        int a(CharSequence charSequence, int i, int i2);
    }

    public static abstract class c implements a.h.i.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f796a;

        public c(b bVar) {
            this.f796a = bVar;
        }

        public abstract boolean a();

        public boolean b(CharSequence charSequence, int i, int i2) {
            if (i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            }
            b bVar = this.f796a;
            if (bVar == null) {
                return a();
            }
            int iA = bVar.a(charSequence, i, i2);
            if (iA == 0) {
                return true;
            }
            if (iA != 1) {
                return a();
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: a.h.i.d$d, reason: collision with other inner class name */
    public static class C0014d extends c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f797b;

        public C0014d(b bVar, boolean z) {
            super(bVar);
            this.f797b = z;
        }

        @Override // a.h.i.d.c
        public boolean a() {
            return this.f797b;
        }
    }

    public static int a(int i) {
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

package f1;

/* JADX INFO: renamed from: f1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1250e extends com.bumptech.glide.c {
    @Override // com.bumptech.glide.c
    public final boolean I(AbstractC1252g abstractC1252g, C1248c c1248c, C1248c c1248c2) {
        synchronized (abstractC1252g) {
            try {
                if (abstractC1252g.f14543m != c1248c) {
                    return false;
                }
                abstractC1252g.f14543m = c1248c2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.bumptech.glide.c
    public final boolean J(AbstractC1252g abstractC1252g, Object obj, Object obj2) {
        synchronized (abstractC1252g) {
            try {
                if (abstractC1252g.l != obj) {
                    return false;
                }
                abstractC1252g.l = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.bumptech.glide.c
    public final boolean K(AbstractC1252g abstractC1252g, C1251f c1251f, C1251f c1251f2) {
        synchronized (abstractC1252g) {
            try {
                if (abstractC1252g.f14544n != c1251f) {
                    return false;
                }
                abstractC1252g.f14544n = c1251f2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.bumptech.glide.c
    public final void w0(C1251f c1251f, C1251f c1251f2) {
        c1251f.f14539b = c1251f2;
    }

    @Override // com.bumptech.glide.c
    public final void x0(C1251f c1251f, Thread thread) {
        c1251f.f14538a = thread;
    }
}

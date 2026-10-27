package spi;

import com.google.android.material.badge.BadgeDrawable;

/* JADX INFO: compiled from: TypeAdapters.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sb implements ob {
    public final /* synthetic */ Class a;
    public final /* synthetic */ Class b;
    public final /* synthetic */ nb c;

    public sb(Class cls, Class cls2, nb nbVar) {
        this.a = cls;
        this.b = cls2;
        this.c = nbVar;
    }

    @Override // spi.ob
    public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
        Class<? super T> cls = vbVar.a;
        if (cls == this.a || cls == this.b) {
            return this.c;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sbK = c.k("Factory[type=");
        sbK.append(this.b.getName());
        sbK.append(BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX);
        sbK.append(this.a.getName());
        sbK.append(",adapter=");
        sbK.append(this.c);
        sbK.append("]");
        return sbK.toString();
    }
}

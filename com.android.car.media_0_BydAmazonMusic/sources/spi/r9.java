package spi;

import java.lang.reflect.Field;
import spi.s9;

/* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r9 extends s9.b {
    public final /* synthetic */ Field d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ nb f;
    public final /* synthetic */ j4 g;
    public final /* synthetic */ vb h;
    public final /* synthetic */ boolean i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r9(s9 s9Var, String str, boolean z, boolean z2, Field field, boolean z3, nb nbVar, j4 j4Var, vb vbVar, boolean z4) {
        super(str, z, z2);
        this.d = field;
        this.e = z3;
        this.f = nbVar;
        this.g = j4Var;
        this.h = vbVar;
        this.i = z4;
    }

    @Override // spi.s9.b
    public final void a(o5 o5Var, Object obj) throws IllegalAccessException {
        Object objA = this.f.a(o5Var);
        if (objA == null && this.i) {
            return;
        }
        this.d.set(obj, objA);
    }

    @Override // spi.s9.b
    public final void b(v5 v5Var, Object obj) throws IllegalAccessException {
        (this.e ? this.f : new pb(this.g, this.f, this.h.b)).b(v5Var, this.d.get(obj));
    }

    @Override // spi.s9.b
    public final boolean c(Object obj) {
        return this.b && this.d.get(obj) != obj;
    }
}

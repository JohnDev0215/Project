package com.bumptech.glide;

import A4.q;
import android.content.Context;
import android.util.Log;
import androidx.datastore.preferences.protobuf.g0;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public final class j extends w4.a {
    public final Context B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final l f12697C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final Class f12698D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final e f12699E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public a f12700F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public Object f12701G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public ArrayList f12702H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public j f12703I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public j f12704J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final boolean f12705K = true;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f12706L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f12707M;

    static {
    }

    public j(b bVar, l lVar, Class cls, Context context) {
        w4.f fVar;
        this.f12697C = lVar;
        this.f12698D = cls;
        this.B = context;
        C2465e c2465e = lVar.l.f12667n.f12679f;
        a aVar = (a) c2465e.get(cls);
        if (aVar == null) {
            for (Map.Entry entry : (g0) c2465e.entrySet()) {
                if (((Class) entry.getKey()).isAssignableFrom(cls)) {
                    aVar = (a) entry.getValue();
                }
            }
        }
        this.f12700F = aVar == null ? e.f12675k : aVar;
        this.f12699E = bVar.f12667n;
        Iterator it = lVar.f12715r.iterator();
        while (it.hasNext()) {
            s((w4.e) it.next());
        }
        synchronized (lVar) {
            fVar = lVar.f12716s;
        }
        a(fVar);
    }

    @Override // w4.a
    public final boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        if (super.equals(jVar)) {
            return Objects.equals(this.f12698D, jVar.f12698D) && this.f12700F.equals(jVar.f12700F) && Objects.equals(this.f12701G, jVar.f12701G) && Objects.equals(this.f12702H, jVar.f12702H) && Objects.equals(this.f12703I, jVar.f12703I) && Objects.equals(this.f12704J, jVar.f12704J) && this.f12705K == jVar.f12705K && this.f12706L == jVar.f12706L;
        }
        return false;
    }

    @Override // w4.a
    public final int hashCode() {
        return q.g(this.f12706L ? 1 : 0, q.g(this.f12705K ? 1 : 0, q.h(q.h(q.h(q.h(q.h(q.h(q.h(super.hashCode(), this.f12698D), this.f12700F), this.f12701G), this.f12702H), this.f12703I), this.f12704J), null)));
    }

    public final j s(w4.e eVar) {
        if (this.f23286y) {
            return clone().s(eVar);
        }
        if (eVar != null) {
            if (this.f12702H == null) {
                this.f12702H = new ArrayList();
            }
            this.f12702H.add(eVar);
        }
        k();
        return this;
    }

    @Override // w4.a
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final j a(w4.a aVar) {
        A4.h.b(aVar);
        return (j) super.a(aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0156  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final w4.c u(java.lang.Object r26, x4.c r27, w4.e r28, w4.d r29, com.bumptech.glide.a r30, com.bumptech.glide.f r31, int r32, int r33, w4.a r34, java.util.concurrent.Executor r35) {
        /*
            Method dump skipped, instruction units count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.j.u(java.lang.Object, x4.c, w4.e, w4.d, com.bumptech.glide.a, com.bumptech.glide.f, int, int, w4.a, java.util.concurrent.Executor):w4.c");
    }

    @Override // w4.a
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public final j clone() {
        j jVar = (j) super.clone();
        jVar.f12700F = jVar.f12700F.clone();
        if (jVar.f12702H != null) {
            jVar.f12702H = new ArrayList(jVar.f12702H);
        }
        j jVar2 = jVar.f12703I;
        if (jVar2 != null) {
            jVar.f12703I = jVar2.clone();
        }
        j jVar3 = jVar.f12704J;
        if (jVar3 != null) {
            jVar.f12704J = jVar3.clone();
        }
        return jVar;
    }

    public final void w(x4.c cVar, w4.e eVar, w4.a aVar, Executor executor) {
        A4.h.b(cVar);
        if (!this.f12706L) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        w4.c cVarU = u(new Object(), cVar, eVar, null, this.f12700F, aVar.f23276n, aVar.f23278q, aVar.f23277p, aVar, executor);
        w4.c cVarD = cVar.D();
        if (cVarU.f(cVarD) && (aVar.o || !cVarD.h())) {
            A4.h.c(cVarD, "Argument must not be null");
            if (cVarD.isRunning()) {
                return;
            }
            cVarD.g();
            return;
        }
        this.f12697C.a(cVar);
        cVar.n(cVarU);
        l lVar = this.f12697C;
        synchronized (lVar) {
            ((Set) lVar.f12714q.f18608m).add(cVar);
            t4.e eVar2 = lVar.o;
            ((Set) eVar2.f22463n).add(cVarU);
            if (eVar2.f22462m) {
                cVarU.clear();
                if (Log.isLoggable("RequestTracker", 2)) {
                    Log.v("RequestTracker", "Paused, delaying request");
                }
                ((HashSet) eVar2.o).add(cVarU);
            } else {
                cVarU.g();
            }
        }
    }

    public final j x(Object obj) {
        if (this.f23286y) {
            return clone().x(obj);
        }
        this.f12701G = obj;
        this.f12706L = true;
        k();
        return this;
    }
}

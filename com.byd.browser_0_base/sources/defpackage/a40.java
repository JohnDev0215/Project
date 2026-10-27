package defpackage;

import androidx.appcompat.app.AppCompatDelegate;
import defpackage.fy2;
import defpackage.on1;
import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: compiled from: CustomTabNightModeStateController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a40 implements v80, on1 {
    public final i32 f;
    public final fy2 g;
    public int j;
    public AppCompatDelegate k;
    public Boolean l;
    public final b<on1.a> e = new b<>();
    public final z30 h = new fy2.a() { // from class: z30
        @Override // fy2.a
        public final void a() {
            this.e.a();
        }
    };
    public final aw i = new aw(20, this);

    /* JADX WARN: Type inference failed for: r0v1, types: [z30] */
    public a40(r2 r2Var, fy2 fy2Var, i32 i32Var) {
        this.g = fy2Var;
        this.f = i32Var;
        r2Var.f(this);
    }

    public final void a() {
        int i = this.j;
        boolean z = false;
        if (i != 1 && (i == 2 || this.g.b || this.f.d)) {
            z = true;
        }
        Boolean bool = this.l;
        if (bool != null && bool.booleanValue() == z) {
            return;
        }
        Boolean boolValueOf = Boolean.valueOf(z);
        this.l = boolValueOf;
        this.k.D(boolValueOf.booleanValue() ? 2 : 1);
        Iterator<on1.a> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((on1.a) aVar.next()).X0();
            }
        }
    }

    @Override // defpackage.on1
    public final void b(on1.a aVar) {
        this.e.a(aVar);
    }

    @Override // defpackage.on1
    public final boolean c() {
        return false;
    }

    @Override // defpackage.on1
    public final boolean d() {
        Boolean bool = this.l;
        return bool != null && bool.booleanValue();
    }

    @Override // defpackage.on1
    public final void e(on1.a aVar) {
        this.e.c(aVar);
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.g.a.c(this.h);
        this.f.a.c(this.i);
    }
}

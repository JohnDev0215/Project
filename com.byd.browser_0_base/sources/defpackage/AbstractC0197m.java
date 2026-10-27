package defpackage;

import defpackage.m31;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: m, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AbstractCoroutine.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0197m<T> extends r31 implements b00<T>, s00 {
    public final m00 f;

    public AbstractC0197m(m00 m00Var, boolean z) {
        super(z);
        r((m31) m00Var.get(m31.a.e));
        this.f = m00Var.plus(this);
    }

    public void G(Object obj) {
        d(obj);
    }

    @Override // defpackage.b00
    public final void c(Object obj) {
        Throwable thA = vb2.a(obj);
        if (thA != null) {
            obj = new rv(thA);
        }
        Object objU = u(obj);
        if (objU == n3.n) {
            return;
        }
        G(objU);
    }

    @Override // defpackage.r31
    public final String g() {
        return i21.g(" was cancelled", getClass().getSimpleName());
    }

    @Override // defpackage.b00
    public final m00 getContext() {
        return this.f;
    }

    @Override // defpackage.r31, defpackage.m31
    public final boolean isActive() {
        return super.isActive();
    }

    @Override // defpackage.r31
    public final void p(uv uvVar) throws IllegalAccessException, InvocationTargetException {
        a8.H(this.f, uvVar);
    }

    @Override // defpackage.r31
    public final String v() {
        return super.v();
    }

    @Override // defpackage.r31
    public final void y(Object obj) {
        if (obj instanceof rv) {
            Throwable th = ((rv) obj).a;
        }
    }
}

package E5;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Set;

/* JADX INFO: renamed from: E5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0176a extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC0179d f1790a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ q f1791b;
    public final /* synthetic */ AbstractC0179d c;
    public final /* synthetic */ Set d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Type f1792e;

    public C0176a(AbstractC0179d abstractC0179d, q qVar, H h10, AbstractC0179d abstractC0179d2, Set set, Type type) {
        this.f1790a = abstractC0179d;
        this.f1791b = qVar;
        this.c = abstractC0179d2;
        this.d = set;
        this.f1792e = type;
    }

    @Override // E5.q
    public final Object a(u uVar) throws IOException {
        AbstractC0179d abstractC0179d = this.c;
        if (abstractC0179d == null) {
            return this.f1791b.a(uVar);
        }
        if (!abstractC0179d.f1803g && uVar.z() == 9) {
            uVar.v();
            return null;
        }
        try {
            return abstractC0179d.b(uVar);
        } catch (InvocationTargetException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            throw new A9.k(cause + " at " + uVar.k(), cause);
        }
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) throws IOException {
        AbstractC0179d abstractC0179d = this.f1790a;
        if (abstractC0179d == null) {
            this.f1791b.d(xVar, obj);
            return;
        }
        if (!abstractC0179d.f1803g && obj == null) {
            xVar.m();
            return;
        }
        try {
            abstractC0179d.d(xVar, obj);
        } catch (InvocationTargetException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            throw new A9.k(cause + " at " + xVar.j(), cause);
        }
    }

    public final String toString() {
        return "JsonAdapter" + this.d + "(" + this.f1792e + ")";
    }
}

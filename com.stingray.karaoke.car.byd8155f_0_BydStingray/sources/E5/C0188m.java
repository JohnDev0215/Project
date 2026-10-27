package E5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: renamed from: E5.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0188m extends q {
    public static final C0181f c = new C0181f(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f1820a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1821b;

    public C0188m(q qVar, int i7) {
        this.f1821b = i7;
        this.f1820a = qVar;
    }

    @Override // E5.q
    public final Object a(u uVar) {
        switch (this.f1821b) {
            case 0:
                Collection collectionE = e();
                uVar.b();
                while (uVar.m()) {
                    ((ArrayList) collectionE).add(this.f1820a.a(uVar));
                }
                uVar.i();
                return collectionE;
            default:
                Collection collectionE2 = e();
                uVar.b();
                while (uVar.m()) {
                    collectionE2.add(this.f1820a.a(uVar));
                }
                uVar.i();
                return collectionE2;
        }
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        switch (this.f1821b) {
            case 0:
                xVar.b();
                Iterator it = ((Collection) obj).iterator();
                while (it.hasNext()) {
                    this.f1820a.d(xVar, it.next());
                }
                ((w) xVar).z(']', 1, 2);
                break;
            default:
                xVar.b();
                Iterator it2 = ((Collection) obj).iterator();
                while (it2.hasNext()) {
                    this.f1820a.d(xVar, it2.next());
                }
                ((w) xVar).z(']', 1, 2);
                break;
        }
    }

    public final Collection e() {
        switch (this.f1821b) {
            case 0:
                return new ArrayList();
            default:
                return new LinkedHashSet();
        }
    }

    public final String toString() {
        return this.f1820a + ".collection()";
    }
}

package F0;

import E0.AbstractC0156f;
import android.view.DragEvent;
import android.view.View;
import androidx.compose.ui.platform.DragAndDropModifierOnDragListener$modifier$1;
import g0.n;
import j0.C1535c;
import j0.C1536d;
import j0.InterfaceC1533a;
import u.C2461a;
import u.C2466f;

/* JADX INFO: renamed from: F0.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnDragListenerC0235w0 implements View.OnDragListener, InterfaceC1533a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1536d f2291a = new C1536d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2466f f2292b = new C2466f(null);
    public final DragAndDropModifierOnDragListener$modifier$1 c = new E0.V() { // from class: androidx.compose.ui.platform.DragAndDropModifierOnDragListener$modifier$1
        public final boolean equals(Object obj) {
            return obj == this;
        }

        public final int hashCode() {
            return this.f11691a.f2291a.hashCode();
        }

        @Override // E0.V
        public final n i() {
            return this.f11691a.f2291a;
        }

        @Override // E0.V
        public final /* bridge */ /* synthetic */ void l(n nVar) {
        }
    };

    @Override // android.view.View.OnDragListener
    public final boolean onDrag(View view, DragEvent dragEvent) {
        e7.f fVar = new e7.f(dragEvent, 20);
        int action = dragEvent.getAction();
        C1536d c1536d = this.f2291a;
        E0.r0 r0Var = E0.r0.l;
        switch (action) {
            case 1:
                A7.v vVar = new A7.v();
                C1535c c1535c = new C1535c(fVar, c1536d, vVar);
                if (c1535c.b(c1536d) == r0Var) {
                    AbstractC0156f.x(c1536d, c1535c);
                }
                boolean z6 = vVar.l;
                C2466f c2466f = this.f2292b;
                c2466f.getClass();
                C2461a c2461a = new C2461a(c2466f);
                while (c2461a.hasNext()) {
                    ((C1536d) c2461a.next()).L0(fVar);
                }
                break;
            case 2:
                c1536d.K0(fVar);
                break;
            case 4:
                S0.I i7 = new S0.I(fVar, 1);
                if (i7.b(c1536d) == r0Var) {
                    AbstractC0156f.x(c1536d, i7);
                    break;
                }
                break;
            case 5:
                c1536d.I0(fVar);
                break;
            case 6:
                c1536d.J0(fVar);
                break;
        }
        return false;
    }
}

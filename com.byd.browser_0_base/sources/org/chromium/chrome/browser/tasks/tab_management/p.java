package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import defpackage.a8;
import defpackage.g03;
import defpackage.mh0;
import defpackage.n3;
import defpackage.oj2;
import defpackage.p13;
import defpackage.y13;
import defpackage.z7;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: TabGridItemTouchHelperCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p extends l.g {
    public final n0 c;
    public final y13 d;
    public final j0.l e;
    public final String f;
    public final j0.n g;
    public final int h;
    public final a i;
    public final int j;
    public float k;
    public float l;
    public float m;
    public boolean n;
    public boolean o;
    public boolean q;
    public boolean r;
    public int s;
    public RecyclerView x;
    public final Context y;
    public int t = -1;
    public int u = -1;
    public int v = -1;
    public int w = 0;
    public final boolean p = false;

    /* JADX INFO: compiled from: TabGridItemTouchHelperCallback.java */
    public interface a {
        void o(int i);
    }

    public p(Context context, n0 n0Var, y13 y13Var, l0 l0Var, m.c cVar, String str, int i, a aVar) {
        this.c = n0Var;
        this.d = y13Var;
        this.e = l0Var;
        this.f = str;
        this.g = cVar;
        this.y = context;
        this.h = i;
        this.i = aVar;
        this.j = context.getResources().getDimensionPixelSize(R$dimen.tab_selection_editor_longpress_entry_threshold);
    }

    @Override // androidx.recyclerview.widget.l.d
    public final boolean a(RecyclerView.a0 a0Var, RecyclerView.a0 a0Var2) {
        int i = a0Var2.j;
        return (i == 3 || i == 6) ? false : true;
    }

    @Override // androidx.recyclerview.widget.l.d
    public final void b(RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        super.b(recyclerView, a0Var);
        int i = this.w;
        this.w = 0;
        if (i == 2 && recyclerView.getAdapter().k() == 0 && recyclerView.getChildCount() != 0) {
            recyclerView.getLayoutManager().q0(a0Var.e);
        }
    }

    @Override // androidx.recyclerview.widget.l.d
    public final int c(RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        int i = a0Var.j;
        int i2 = (i == 3 || i == 6) ? 0 : this.s;
        this.x = recyclerView;
        return ((48 | i2) << 0) | 12288 | (i2 << 16);
    }

    @Override // androidx.recyclerview.widget.l.d
    public final float d() {
        return this.k / this.x.getWidth();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v9 */
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
    @Override // androidx.recyclerview.widget.l.d
    public final void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var, float f, float f2, int i, boolean z) {
        j0.n nVar;
        super.g(canvas, recyclerView, a0Var, f, f2, i, z);
        int i2 = 1;
        if (Math.abs(f) > 0.0f || Math.abs(f2) > 0.0f) {
            this.o = true;
        }
        n0 n0Var = this.c;
        View view = a0Var.e;
        if (i == 1) {
            float fMax = Math.max(0.2f, 1.0f - ((Math.abs(f) * 0.8f) / this.k));
            m.b bVar = (m.b) a0Var;
            PropertyModel propertyModel = bVar.A;
            PropertyModel.j jVar = n0.a.a;
            int iF = propertyModel.h(jVar) == 0 ? n0Var.F(bVar.A.h(u0.a)) : bVar.A.h(jVar) == 1 ? n0Var.I(bVar.A.h(f.a)) : -1;
            if (iF == -1) {
                return;
            }
            n0Var.get(iF).b.m(n0.a.b, fMax);
            boolean z2 = Math.abs(f) >= this.k;
            if (z2 && !this.q) {
                view.performHapticFeedback(0);
            }
            this.q = z2;
            return;
        }
        float f3 = (f2 * f2) + (f * f);
        int i3 = this.j;
        if (f3 > i3 * i3) {
            this.n = true;
        }
        this.w = i;
        if (i != 2 || !this.p) {
            if (i != 2 || (nVar = this.g) == null) {
                return;
            }
            ?? r10 = ((float) view.getBottom()) + f2 > ((float) recyclerView.getBottom()) - this.m;
            if (this.t == -1) {
                return;
            }
            this.v = r10 != false ? a0Var.f() : -1;
            if (r10 == true) {
                i2 = 2;
            } else if (this.t != -1) {
                i2 = 0;
            }
            m.this.f.n(q.l, i2);
            return;
        }
        if (oj2.D(this.y)) {
            int i4 = this.u;
            float f4 = this.l;
            int i5 = TabListRecyclerView.d1;
            int i6 = 0;
            while (true) {
                if (i6 >= recyclerView.getAdapter().k()) {
                    i6 = -1;
                    break;
                }
                RecyclerView.a0 a0VarF = recyclerView.F(i6);
                if (a0VarF != null) {
                    View view2 = a0VarF.e;
                    if (view2.getLeft() != view.getLeft() || view2.getTop() != view.getTop()) {
                        if ((Math.abs(((float) view2.getLeft()) - (((float) view.getLeft()) + f)) < f4 && Math.abs(((float) view2.getTop()) - (((float) view.getTop()) + f2)) < f4) != false) {
                            break;
                        }
                    }
                }
                i6++;
            }
            this.u = i6;
            RecyclerView.a0 a0VarF2 = this.x.F(i6);
            boolean z3 = a0VarF2 instanceof m.b;
            if (z3) {
                if ((z3 && ((m.b) a0VarF2).A.h(n0.a.a) == 0) == false) {
                    this.u = -1;
                    return;
                }
            }
            n0Var.J(this.u, true);
            if (i4 != this.u) {
                n0Var.J(i4, false);
            }
        }
    }

    @Override // androidx.recyclerview.widget.l.d
    public final boolean h(RecyclerView.a0 a0Var, RecyclerView.a0 a0Var2) {
        int i;
        int i2;
        this.t = a0Var2.f();
        int i3 = this.u;
        n0 n0Var = this.c;
        int i4 = 0;
        if (i3 != -1) {
            n0Var.J(i3, false);
            this.u = -1;
        }
        PropertyModel propertyModel = ((m.b) a0Var).A;
        PropertyModel.p pVar = u0.a;
        int iH = propertyModel.h(pVar);
        int iH2 = ((m.b) a0Var2).A.h(pVar);
        int iF = a0Var2.f() - a0Var.f();
        y13 y13Var = this.d;
        p13 p13VarC = y13Var.u().c();
        TabModel tabModelZ = y13Var.z();
        if (p13VarC instanceof mh0) {
            int iF2 = n0Var.F(iH);
            if (iF > 0) {
                iF++;
            }
            tabModelZ.s(iH, n0Var.D(iF2 + iF));
        } else if (this.p) {
            List<Tab> listB = y13Var.u().c().B(iH2);
            int iR = iF >= 0 ? tabModelZ.r(listB.get(listB.size() - 1)) + 1 : tabModelZ.r(listB.get(0));
            g03 g03Var = (g03) p13VarC;
            List<Tab> listB2 = g03Var.B(iH);
            TabModel tabModel = g03Var.e;
            int iQ = a8.q(iR, 0, tabModel.getCount());
            int iG = z7.G(tabModel, listB2.get(0).getId());
            if (iG != -1 && iG != iQ) {
                for (Tab tab : listB2) {
                    if (tabModel.r(tab) != -1) {
                        int id = tab.getId();
                        if (iQ >= iG) {
                            i = i4;
                            i2 = iQ;
                        } else {
                            i = i4 + 1;
                            i2 = i4 + iQ;
                        }
                        tabModel.s(id, i2);
                        i4 = i;
                    }
                }
            }
        } else {
            int iR2 = tabModelZ.r(y13Var.s(iH2));
            if (iF > 0) {
                iR2++;
            }
            tabModelZ.s(iH, iR2);
        }
        n3.X0("TabGrid.Drag.Reordered." + this.f);
        this.n = true;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0196  */
    @Override // androidx.recyclerview.widget.l.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(androidx.recyclerview.widget.RecyclerView.a0 r11, int r12) {
        /*
            Method dump skipped, instruction units count: 422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tasks.tab_management.p.i(androidx.recyclerview.widget.RecyclerView$a0, int):void");
    }

    @Override // androidx.recyclerview.widget.l.d
    public final void j(RecyclerView.a0 a0Var) {
        m.b bVar = (m.b) a0Var;
        PropertyModel propertyModel = bVar.A;
        PropertyModel.j jVar = n0.a.a;
        if (propertyModel.h(jVar) == 0) {
            this.e.a(bVar.A.h(u0.a));
            n3.X0("MobileStackViewSwipeCloseTab." + this.f);
        } else if (bVar.A.h(jVar) == 1) {
            a0Var.e.findViewById(R$id.close_button).performClick();
        }
        this.n = true;
    }
}

package b.b.p.j;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import b.b.p.j.m;
import b.b.p.j.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {
    public m.a E3;
    public int F3;
    public int G3;
    public n H3;
    public int I3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f917c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f918d;
    public g s;
    public LayoutInflater x;
    public LayoutInflater y;

    public b(Context context, int i2, int i3) {
        this.f917c = context;
        this.x = LayoutInflater.from(context);
        this.F3 = i2;
        this.G3 = i3;
    }

    @Override // b.b.p.j.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.E3;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.p.j.m
    public void d(Context context, g gVar) {
        this.f918d = context;
        this.y = LayoutInflater.from(context);
        this.s = gVar;
    }

    @Override // b.b.p.j.m
    public boolean e(g gVar, i iVar) {
        return false;
    }

    public void f(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.H3).addView(view, i2);
    }

    @Override // b.b.p.j.m
    public boolean g(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.p.j.m
    public void h(m.a aVar) {
        this.E3 = aVar;
    }

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
    @Override // b.b.p.j.m
    public boolean i(r rVar) {
        m.a aVar = this.E3;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.s;
        }
        return aVar.b(gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.p.j.m
    public void j(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.H3;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.s;
        int i2 = 0;
        if (gVar != null) {
            gVar.r();
            ArrayList<i> arrayListE = this.s.E();
            int size = arrayListE.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                i iVar = arrayListE.get(i4);
                if (r(i3, iVar)) {
                    View childAt = viewGroup.getChildAt(i3);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewO = o(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
                        viewO.setPressed(false);
                        viewO.jumpDrawablesToCurrentState();
                    }
                    if (viewO != childAt) {
                        f(viewO, i3);
                    }
                    i3++;
                }
            }
            i2 = i3;
        }
        while (i2 < viewGroup.getChildCount()) {
            if (!m(viewGroup, i2)) {
                i2++;
            }
        }
    }

    public abstract void k(i iVar, n.a aVar);

    public n.a l(ViewGroup viewGroup) {
        return (n.a) this.x.inflate(this.G3, viewGroup, false);
    }

    public boolean m(ViewGroup viewGroup, int i2) {
        viewGroup.removeViewAt(i2);
        return true;
    }

    public m.a n() {
        return this.E3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View o(i iVar, View view, ViewGroup viewGroup) {
        n.a aVarL = view instanceof n.a ? (n.a) view : l(viewGroup);
        k(iVar, aVarL);
        return (View) aVarL;
    }

    public n p(ViewGroup viewGroup) {
        if (this.H3 == null) {
            n nVar = (n) this.x.inflate(this.F3, viewGroup, false);
            this.H3 = nVar;
            nVar.b(this.s);
            j(true);
        }
        return this.H3;
    }

    public void q(int i2) {
        this.I3 = i2;
    }

    public abstract boolean r(int i2, i iVar);
}

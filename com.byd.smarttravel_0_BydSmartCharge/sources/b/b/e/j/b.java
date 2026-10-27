package b.b.e.j;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import b.b.e.j.m;
import b.b.e.j.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f682b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f683c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f684d;
    public LayoutInflater e;
    public LayoutInflater f;
    public m.a g;
    public int h;
    public int i;
    public n j;
    public int k;

    public b(Context context, int i, int i2) {
        this.f682b = context;
        this.e = LayoutInflater.from(context);
        this.h = i;
        this.i = i2;
    }

    @Override // b.b.e.j.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.g;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.e.j.m
    public int c() {
        return this.k;
    }

    @Override // b.b.e.j.m
    public void f(Context context, g gVar) {
        this.f683c = context;
        this.f = LayoutInflater.from(context);
        this.f684d = gVar;
    }

    public void g(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.j).addView(view, i);
    }

    @Override // b.b.e.j.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.e.j.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.e.j.m
    public void k(m.a aVar) {
        this.g = aVar;
    }

    public abstract void l(i iVar, n.a aVar);

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
    @Override // b.b.e.j.m
    public boolean m(r rVar) {
        m.a aVar = this.g;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.f684d;
        }
        return aVar.b(gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.e.j.m
    public void n(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.j;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f684d;
        int i = 0;
        if (gVar != null) {
            gVar.t();
            ArrayList<i> arrayListG = this.f684d.G();
            int size = arrayListG.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i iVar = arrayListG.get(i3);
                if (u(i2, iVar)) {
                    View childAt = viewGroup.getChildAt(i2);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewR = r(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
                        viewR.setPressed(false);
                        viewR.jumpDrawablesToCurrentState();
                    }
                    if (viewR != childAt) {
                        g(viewR, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!p(viewGroup, i)) {
                i++;
            }
        }
    }

    public n.a o(ViewGroup viewGroup) {
        return (n.a) this.e.inflate(this.i, viewGroup, false);
    }

    public boolean p(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public m.a q() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View r(i iVar, View view, ViewGroup viewGroup) {
        n.a aVarO = view instanceof n.a ? (n.a) view : o(viewGroup);
        l(iVar, aVarO);
        return (View) aVarO;
    }

    public n s(ViewGroup viewGroup) {
        if (this.j == null) {
            n nVar = (n) this.e.inflate(this.h, viewGroup, false);
            this.j = nVar;
            nVar.b(this.f684d);
            n(true);
        }
        return this.j;
    }

    public void t(int i) {
        this.k = i;
    }

    public abstract boolean u(int i, i iVar);
}

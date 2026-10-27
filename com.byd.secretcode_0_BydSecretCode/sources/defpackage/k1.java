package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.v1;
import defpackage.w1;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k1 implements v1 {
    public Context b;
    public Context c;
    public p1 d;
    public LayoutInflater e;
    public LayoutInflater f;
    public v1.a g;
    public int h;
    public int i;
    public w1 j;
    public int k;

    public k1(Context context, int i, int i2) {
        this.b = context;
        this.e = LayoutInflater.from(context);
        this.h = i;
        this.i = i2;
    }

    @Override // defpackage.v1
    public void a(p1 p1Var, boolean z) {
        v1.a aVar = this.g;
        if (aVar != null) {
            aVar.a(p1Var, z);
        }
    }

    @Override // defpackage.v1
    public int c() {
        return this.k;
    }

    @Override // defpackage.v1
    public void f(Context context, p1 p1Var) {
        this.c = context;
        this.f = LayoutInflater.from(context);
        this.d = p1Var;
    }

    public void g(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.j).addView(view, i);
    }

    @Override // defpackage.v1
    public boolean i(p1 p1Var, r1 r1Var) {
        return false;
    }

    @Override // defpackage.v1
    public boolean j(p1 p1Var, r1 r1Var) {
        return false;
    }

    @Override // defpackage.v1
    public void k(v1.a aVar) {
        this.g = aVar;
    }

    public abstract void l(r1 r1Var, w1.a aVar);

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
    @Override // defpackage.v1
    public boolean m(a2 a2Var) {
        v1.a aVar = this.g;
        p1 p1Var = a2Var;
        if (aVar == null) {
            return false;
        }
        if (a2Var == null) {
            p1Var = this.d;
        }
        return aVar.b(p1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.v1
    public void n(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.j;
        if (viewGroup == null) {
            return;
        }
        p1 p1Var = this.d;
        int i = 0;
        if (p1Var != null) {
            p1Var.t();
            ArrayList<r1> arrayListG = this.d.G();
            int size = arrayListG.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                r1 r1Var = arrayListG.get(i3);
                if (u(i2, r1Var)) {
                    View childAt = viewGroup.getChildAt(i2);
                    r1 itemData = childAt instanceof w1.a ? ((w1.a) childAt).getItemData() : null;
                    View viewR = r(r1Var, childAt, viewGroup);
                    if (r1Var != itemData) {
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

    public w1.a o(ViewGroup viewGroup) {
        return (w1.a) this.e.inflate(this.i, viewGroup, false);
    }

    public boolean p(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public v1.a q() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View r(r1 r1Var, View view, ViewGroup viewGroup) {
        w1.a aVarO = view instanceof w1.a ? (w1.a) view : o(viewGroup);
        l(r1Var, aVarO);
        return (View) aVarO;
    }

    public w1 s(ViewGroup viewGroup) {
        if (this.j == null) {
            w1 w1Var = (w1) this.e.inflate(this.h, viewGroup, false);
            this.j = w1Var;
            w1Var.b(this.d);
            n(true);
        }
        return this.j;
    }

    public void t(int i) {
        this.k = i;
    }

    public abstract boolean u(int i, r1 r1Var);
}

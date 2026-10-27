package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.m;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public abstract class b implements l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f215c;
    protected Context d;
    protected f e;
    protected LayoutInflater f;
    private l.a g;
    private int h;
    private int i;
    protected m j;

    public b(Context context, int i, int i2) {
        this.f215c = context;
        this.f = LayoutInflater.from(context);
        this.h = i;
        this.i = i2;
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(f fVar, boolean z) {
        l.a aVar = this.g;
        if (aVar != null) {
            aVar.a(fVar, z);
        }
    }

    protected void b(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.j).addView(view, i);
    }

    @Override // androidx.appcompat.view.menu.l
    public void d(Context context, f fVar) {
        this.d = context;
        LayoutInflater.from(context);
        this.e = fVar;
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean e(f fVar, h hVar) {
        return false;
    }

    public abstract void f(h hVar, m.a aVar);

    @Override // androidx.appcompat.view.menu.l
    public boolean g(f fVar, h hVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.l
    public void h(l.a aVar) {
        this.g = aVar;
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
    @Override // androidx.appcompat.view.menu.l
    public boolean i(q qVar) {
        l.a aVar = this.g;
        f fVar = qVar;
        if (aVar == null) {
            return false;
        }
        if (qVar == null) {
            fVar = this.e;
        }
        return aVar.b(fVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.l
    public void j(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.j;
        if (viewGroup == null) {
            return;
        }
        f fVar = this.e;
        int i = 0;
        if (fVar != null) {
            fVar.r();
            ArrayList<h> arrayListE = this.e.E();
            int size = arrayListE.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                h hVar = arrayListE.get(i3);
                if (q(i2, hVar)) {
                    View childAt = viewGroup.getChildAt(i2);
                    h hVarB = childAt instanceof m.a ? ((m.a) childAt).b() : null;
                    View viewN = n(hVar, childAt, viewGroup);
                    if (hVar != hVarB) {
                        viewN.setPressed(false);
                        viewN.jumpDrawablesToCurrentState();
                    }
                    if (viewN != childAt) {
                        b(viewN, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!l(viewGroup, i)) {
                i++;
            }
        }
    }

    public m.a k(ViewGroup viewGroup) {
        return (m.a) this.f.inflate(this.i, viewGroup, false);
    }

    protected boolean l(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public l.a m() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View n(h hVar, View view, ViewGroup viewGroup) {
        m.a aVarK = view instanceof m.a ? (m.a) view : k(viewGroup);
        f(hVar, aVarK);
        return (View) aVarK;
    }

    public m o(ViewGroup viewGroup) {
        if (this.j == null) {
            m mVar = (m) this.f.inflate(this.h, viewGroup, false);
            this.j = mVar;
            mVar.b(this.e);
            j(true);
        }
        return this.j;
    }

    public void p(int i) {
    }

    public abstract boolean q(int i, h hVar);
}

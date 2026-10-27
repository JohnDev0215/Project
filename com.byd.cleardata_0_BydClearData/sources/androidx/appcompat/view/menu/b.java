package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f786b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f787c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected g f788d;
    protected LayoutInflater e;
    protected LayoutInflater f;
    private m.a g;
    private int h;
    private int i;
    protected n j;
    private int k;

    public b(Context context, int i, int i2) {
        this.f786b = context;
        this.e = LayoutInflater.from(context);
        this.h = i;
        this.i = i2;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.g;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    protected void b(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.j).addView(view, i);
    }

    @Override // androidx.appcompat.view.menu.m
    public void d(Context context, g gVar) {
        this.f787c = context;
        this.f = LayoutInflater.from(context);
        this.f788d = gVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean e(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean f(g gVar, i iVar) {
        return false;
    }

    public abstract void g(i iVar, n.a aVar);

    @Override // androidx.appcompat.view.menu.m
    public void h(m.a aVar) {
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
    @Override // androidx.appcompat.view.menu.m
    public boolean i(r rVar) {
        m.a aVar = this.g;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.f788d;
        }
        return aVar.b(gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.m
    public void j(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.j;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f788d;
        int i = 0;
        if (gVar != null) {
            gVar.r();
            ArrayList<i> arrayListE = this.f788d.E();
            int size = arrayListE.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i iVar = arrayListE.get(i3);
                if (q(i2, iVar)) {
                    View childAt = viewGroup.getChildAt(i2);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewN = n(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
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

    public n.a k(ViewGroup viewGroup) {
        return (n.a) this.e.inflate(this.i, viewGroup, false);
    }

    protected boolean l(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public m.a m() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View n(i iVar, View view, ViewGroup viewGroup) {
        n.a aVarK = view instanceof n.a ? (n.a) view : k(viewGroup);
        g(iVar, aVarK);
        return (View) aVarK;
    }

    public n o(ViewGroup viewGroup) {
        if (this.j == null) {
            n nVar = (n) this.e.inflate(this.h, viewGroup, false);
            this.j = nVar;
            nVar.b(this.f788d);
            j(true);
        }
        return this.j;
    }

    public void p(int i) {
        this.k = i;
    }

    public abstract boolean q(int i, i iVar);
}

package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f255b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f256c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected g f257d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected LayoutInflater f258e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected LayoutInflater f259f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private m.a f260g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f261h;
    private int i;
    protected n j;
    private int k;

    public b(Context context, int i, int i2) {
        this.f255b = context;
        this.f258e = LayoutInflater.from(context);
        this.f261h = i;
        this.i = i2;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.f260g;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public int c() {
        return this.k;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, g gVar) {
        this.f256c = context;
        this.f259f = LayoutInflater.from(context);
        this.f257d = gVar;
    }

    protected void g(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.j).addView(view, i);
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void k(m.a aVar) {
        this.f260g = aVar;
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
    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        m.a aVar = this.f260g;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.f257d;
        }
        return aVar.b(gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.j;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f257d;
        int i = 0;
        if (gVar != null) {
            gVar.t();
            ArrayList<i> arrayListG = this.f257d.G();
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
        return (n.a) this.f258e.inflate(this.i, viewGroup, false);
    }

    protected boolean p(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public m.a q() {
        return this.f260g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View r(i iVar, View view, ViewGroup viewGroup) {
        n.a aVarO = view instanceof n.a ? (n.a) view : o(viewGroup);
        l(iVar, aVarO);
        return (View) aVarO;
    }

    public n s(ViewGroup viewGroup) {
        if (this.j == null) {
            n nVar = (n) this.f258e.inflate(this.f261h, viewGroup, false);
            this.j = nVar;
            nVar.b(this.f257d);
            n(true);
        }
        return this.j;
    }

    public void t(int i) {
        this.k = i;
    }

    public abstract boolean u(int i, i iVar);
}

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
    protected Context f262b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f263c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected g f264d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected LayoutInflater f265e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected LayoutInflater f266f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private m.a f267g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f268h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f269i;
    protected n j;
    private int k;

    public b(Context context, int i2, int i3) {
        this.f262b = context;
        this.f265e = LayoutInflater.from(context);
        this.f268h = i2;
        this.f269i = i3;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.f267g;
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
        this.f263c = context;
        this.f266f = LayoutInflater.from(context);
        this.f264d = gVar;
    }

    protected void g(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.j).addView(view, i2);
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
        this.f267g = aVar;
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
        m.a aVar = this.f267g;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.f264d;
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
        g gVar = this.f264d;
        int i2 = 0;
        if (gVar != null) {
            gVar.t();
            ArrayList<i> arrayListG = this.f264d.G();
            int size = arrayListG.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                i iVar = arrayListG.get(i4);
                if (u(i3, iVar)) {
                    View childAt = viewGroup.getChildAt(i3);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewR = r(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
                        viewR.setPressed(false);
                        viewR.jumpDrawablesToCurrentState();
                    }
                    if (viewR != childAt) {
                        g(viewR, i3);
                    }
                    i3++;
                }
            }
            i2 = i3;
        }
        while (i2 < viewGroup.getChildCount()) {
            if (!p(viewGroup, i2)) {
                i2++;
            }
        }
    }

    public n.a o(ViewGroup viewGroup) {
        return (n.a) this.f265e.inflate(this.f269i, viewGroup, false);
    }

    protected boolean p(ViewGroup viewGroup, int i2) {
        viewGroup.removeViewAt(i2);
        return true;
    }

    public m.a q() {
        return this.f267g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View r(i iVar, View view, ViewGroup viewGroup) {
        n.a aVarO = view instanceof n.a ? (n.a) view : o(viewGroup);
        l(iVar, aVarO);
        return (View) aVarO;
    }

    public n s(ViewGroup viewGroup) {
        if (this.j == null) {
            n nVar = (n) this.f265e.inflate(this.f268h, viewGroup, false);
            this.j = nVar;
            nVar.b(this.f264d);
            n(true);
        }
        return this.j;
    }

    public void t(int i2) {
        this.k = i2;
    }

    public abstract boolean u(int i2, i iVar);
}

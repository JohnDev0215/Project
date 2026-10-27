package n;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import n.m;
import n.n;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f3868b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f3869c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f3870d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LayoutInflater f3871e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LayoutInflater f3872f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m.a f3873g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3874h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3875i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public n f3876j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3877k;

    public b(Context context, int i3, int i4) {
        this.f3868b = context;
        this.f3871e = LayoutInflater.from(context);
        this.f3874h = i3;
        this.f3875i = i4;
    }

    @Override // n.m
    public void a(g gVar, boolean z3) {
        m.a aVar = this.f3873g;
        if (aVar != null) {
            aVar.a(gVar, z3);
        }
    }

    @Override // n.m
    public int c() {
        return this.f3877k;
    }

    @Override // n.m
    public void f(Context context, g gVar) {
        this.f3869c = context;
        this.f3872f = LayoutInflater.from(context);
        this.f3870d = gVar;
    }

    public void g(View view, int i3) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f3876j).addView(view, i3);
    }

    @Override // n.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // n.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // n.m
    public void k(m.a aVar) {
        this.f3873g = aVar;
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
    @Override // n.m
    public boolean m(r rVar) {
        m.a aVar = this.f3873g;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.f3870d;
        }
        return aVar.b(gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // n.m
    public void n(boolean z3) {
        ViewGroup viewGroup = (ViewGroup) this.f3876j;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f3870d;
        int i3 = 0;
        if (gVar != null) {
            gVar.t();
            ArrayList<i> arrayListG = this.f3870d.G();
            int size = arrayListG.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                i iVar = arrayListG.get(i5);
                if (u(i4, iVar)) {
                    View childAt = viewGroup.getChildAt(i4);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewR = r(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
                        viewR.setPressed(false);
                        viewR.jumpDrawablesToCurrentState();
                    }
                    if (viewR != childAt) {
                        g(viewR, i4);
                    }
                    i4++;
                }
            }
            i3 = i4;
        }
        while (i3 < viewGroup.getChildCount()) {
            if (!p(viewGroup, i3)) {
                i3++;
            }
        }
    }

    public n.a o(ViewGroup viewGroup) {
        return (n.a) this.f3871e.inflate(this.f3875i, viewGroup, false);
    }

    public boolean p(ViewGroup viewGroup, int i3) {
        viewGroup.removeViewAt(i3);
        return true;
    }

    public m.a q() {
        return this.f3873g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View r(i iVar, View view, ViewGroup viewGroup) {
        n.a aVarO = view instanceof n.a ? (n.a) view : o(viewGroup);
        l(iVar, aVarO);
        return (View) aVarO;
    }

    public n s(ViewGroup viewGroup) {
        if (this.f3876j == null) {
            n nVar = (n) this.f3871e.inflate(this.f3874h, viewGroup, false);
            this.f3876j = nVar;
            nVar.b(this.f3870d);
            n(true);
        }
        return this.f3876j;
    }

    public void t(int i3) {
        this.f3877k = i3;
    }

    public abstract boolean u(int i3, i iVar);
}

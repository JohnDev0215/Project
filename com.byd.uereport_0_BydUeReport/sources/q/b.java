package q;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import q.m;
import q.n;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f7067d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f7068e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public g f7069f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public LayoutInflater f7070g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public LayoutInflater f7071h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m.a f7072i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f7073j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7074k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public n f7075l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7076m;

    public b(Context context, int i8, int i9) {
        this.f7067d = context;
        this.f7070g = LayoutInflater.from(context);
        this.f7073j = i8;
        this.f7074k = i9;
    }

    @Override // q.m
    public void a(g gVar, boolean z7) {
        m.a aVar = this.f7072i;
        if (aVar != null) {
            aVar.a(gVar, z7);
        }
    }

    @Override // q.m
    public void b(Context context, g gVar) {
        this.f7068e = context;
        this.f7071h = LayoutInflater.from(context);
        this.f7069f = gVar;
    }

    public void e(View view, int i8) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f7075l).addView(view, i8);
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
    @Override // q.m
    public boolean f(r rVar) {
        m.a aVar = this.f7072i;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.f7069f;
        }
        return aVar.b(gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // q.m
    public void g(boolean z7) {
        ViewGroup viewGroup = (ViewGroup) this.f7075l;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f7069f;
        int i8 = 0;
        if (gVar != null) {
            gVar.t();
            ArrayList<i> arrayListG = this.f7069f.G();
            int size = arrayListG.size();
            int i9 = 0;
            for (int i10 = 0; i10 < size; i10++) {
                i iVar = arrayListG.get(i10);
                if (u(i9, iVar)) {
                    View childAt = viewGroup.getChildAt(i9);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewR = r(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
                        viewR.setPressed(false);
                        viewR.jumpDrawablesToCurrentState();
                    }
                    if (viewR != childAt) {
                        e(viewR, i9);
                    }
                    i9++;
                }
            }
            i8 = i9;
        }
        while (i8 < viewGroup.getChildCount()) {
            if (!p(viewGroup, i8)) {
                i8++;
            }
        }
    }

    public abstract void h(i iVar, n.a aVar);

    @Override // q.m
    public int i() {
        return this.f7076m;
    }

    @Override // q.m
    public boolean l(g gVar, i iVar) {
        return false;
    }

    @Override // q.m
    public boolean m(g gVar, i iVar) {
        return false;
    }

    @Override // q.m
    public void n(m.a aVar) {
        this.f7072i = aVar;
    }

    public n.a o(ViewGroup viewGroup) {
        return (n.a) this.f7070g.inflate(this.f7074k, viewGroup, false);
    }

    public boolean p(ViewGroup viewGroup, int i8) {
        viewGroup.removeViewAt(i8);
        return true;
    }

    public m.a q() {
        return this.f7072i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View r(i iVar, View view, ViewGroup viewGroup) {
        n.a aVarO = view instanceof n.a ? (n.a) view : o(viewGroup);
        h(iVar, aVarO);
        return (View) aVarO;
    }

    public n s(ViewGroup viewGroup) {
        if (this.f7075l == null) {
            n nVar = (n) this.f7070g.inflate(this.f7073j, viewGroup, false);
            this.f7075l = nVar;
            nVar.b(this.f7069f);
            g(true);
        }
        return this.f7075l;
    }

    public void t(int i8) {
        this.f7076m = i8;
    }

    public abstract boolean u(int i8, i iVar);
}

package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.k;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements j {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected Context f605e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected Context f606g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected e f607h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected LayoutInflater f608i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected LayoutInflater f609j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private j.a f610k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f611l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f612m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    protected k f613n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f614o;

    public a(Context context, int i7, int i8) {
        this.f605e = context;
        this.f608i = LayoutInflater.from(context);
        this.f611l = i7;
        this.f612m = i8;
    }

    @Override // androidx.appcompat.view.menu.j
    public void a(e eVar, boolean z6) {
        j.a aVar = this.f610k;
        if (aVar != null) {
            aVar.a(eVar, z6);
        }
    }

    protected void b(View view, int i7) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f613n).addView(view, i7);
    }

    @Override // androidx.appcompat.view.menu.j
    public void d(Context context, e eVar) {
        this.f606g = context;
        this.f609j = LayoutInflater.from(context);
        this.f607h = eVar;
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean e(e eVar, g gVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean f(e eVar, g gVar) {
        return false;
    }

    public abstract void g(g gVar, k.a aVar);

    @Override // androidx.appcompat.view.menu.j
    public void h(j.a aVar) {
        this.f610k = aVar;
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
    @Override // androidx.appcompat.view.menu.j
    public boolean i(m mVar) {
        j.a aVar = this.f610k;
        e eVar = mVar;
        if (aVar == null) {
            return false;
        }
        if (mVar == null) {
            eVar = this.f607h;
        }
        return aVar.b(eVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.j
    public void j(boolean z6) {
        ViewGroup viewGroup = (ViewGroup) this.f613n;
        if (viewGroup == null) {
            return;
        }
        e eVar = this.f607h;
        int i7 = 0;
        if (eVar != null) {
            eVar.r();
            ArrayList<g> arrayListE = this.f607h.E();
            int size = arrayListE.size();
            int i8 = 0;
            for (int i9 = 0; i9 < size; i9++) {
                g gVar = arrayListE.get(i9);
                if (q(i8, gVar)) {
                    View childAt = viewGroup.getChildAt(i8);
                    g itemData = childAt instanceof k.a ? ((k.a) childAt).getItemData() : null;
                    View viewN = n(gVar, childAt, viewGroup);
                    if (gVar != itemData) {
                        viewN.setPressed(false);
                        viewN.jumpDrawablesToCurrentState();
                    }
                    if (viewN != childAt) {
                        b(viewN, i8);
                    }
                    i8++;
                }
            }
            i7 = i8;
        }
        while (i7 < viewGroup.getChildCount()) {
            if (!l(viewGroup, i7)) {
                i7++;
            }
        }
    }

    public k.a k(ViewGroup viewGroup) {
        return (k.a) this.f608i.inflate(this.f612m, viewGroup, false);
    }

    protected boolean l(ViewGroup viewGroup, int i7) {
        viewGroup.removeViewAt(i7);
        return true;
    }

    public j.a m() {
        return this.f610k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View n(g gVar, View view, ViewGroup viewGroup) {
        k.a aVarK = view instanceof k.a ? (k.a) view : k(viewGroup);
        g(gVar, aVarK);
        return (View) aVarK;
    }

    public k o(ViewGroup viewGroup) {
        if (this.f613n == null) {
            k kVar = (k) this.f608i.inflate(this.f611l, viewGroup, false);
            this.f613n = kVar;
            kVar.b(this.f607h);
            j(true);
        }
        return this.f613n;
    }

    public void p(int i7) {
        this.f614o = i7;
    }

    public abstract boolean q(int i7, g gVar);
}

package a.b.o.i;

import a.b.o.i.m;
import a.b.o.i.n;
import a.b.p.c.b;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f138a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f139b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f140c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public LayoutInflater f141d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m.a f142e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f143f;
    public int g;
    public n h;

    public b(Context context, int i, int i2) {
        this.f138a = context;
        this.f141d = LayoutInflater.from(context);
        this.f143f = i;
        this.g = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View a(i iVar, View view, ViewGroup viewGroup) {
        n.a aVar = view instanceof n.a ? (n.a) view : (n.a) this.f141d.inflate(this.g, viewGroup, false);
        a.b.p.c cVar = (a.b.p.c) this;
        aVar.e(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) cVar.h);
        if (cVar.w == null) {
            cVar.w = cVar.new b();
        }
        actionMenuItemView.setPopupCallback(cVar.w);
        return (View) aVar;
    }

    @Override // a.b.o.i.m
    public boolean c(g gVar, i iVar) {
        return false;
    }

    @Override // a.b.o.i.m
    public void d(m.a aVar) {
        this.f142e = aVar;
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
    @Override // a.b.o.i.m
    public boolean f(r rVar) {
        m.a aVar = this.f142e;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.f140c;
        }
        return aVar.c(gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // a.b.o.i.m
    public void h(boolean z) {
        int i;
        boolean z2;
        ViewGroup viewGroup = (ViewGroup) this.h;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f140c;
        if (gVar != null) {
            gVar.i();
            ArrayList<i> arrayListL = this.f140c.l();
            int size = arrayListL.size();
            i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i iVar = arrayListL.get(i2);
                if (iVar.g()) {
                    View childAt = viewGroup.getChildAt(i);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewA = a(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
                        viewA.setPressed(false);
                        viewA.jumpDrawablesToCurrentState();
                    }
                    if (viewA != childAt) {
                        ViewGroup viewGroup2 = (ViewGroup) viewA.getParent();
                        if (viewGroup2 != null) {
                            viewGroup2.removeView(viewA);
                        }
                        ((ViewGroup) this.h).addView(viewA, i);
                    }
                    i++;
                }
            }
        } else {
            i = 0;
        }
        while (i < viewGroup.getChildCount()) {
            if (viewGroup.getChildAt(i) == ((a.b.p.c) this).i) {
                z2 = false;
            } else {
                viewGroup.removeViewAt(i);
                z2 = true;
            }
            if (!z2) {
                i++;
            }
        }
    }

    @Override // a.b.o.i.m
    public boolean k(g gVar, i iVar) {
        return false;
    }
}

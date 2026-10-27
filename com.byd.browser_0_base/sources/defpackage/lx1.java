package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.mx1;
import java.util.HashMap;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: PasswordManagerDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lx1 {
    public final mx1 a;
    public PropertyModel b;

    public lx1(bi1 bi1Var, View view, fj fjVar) {
        this.a = new mx1(new PropertyModel.b(di1.B), bi1Var, view, fjVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r6v3, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v0, types: [T, java.lang.Object, java.lang.String] */
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
    public final void a(Context context, kx1 kx1Var) {
        View viewInflate = kx1Var.h != null ? LayoutInflater.from(context).inflate(R$layout.password_manager_dialog_with_help_button, (ViewGroup) null) : LayoutInflater.from(context).inflate(R$layout.password_manager_dialog, (ViewGroup) null);
        PropertyModel.l<Runnable> lVar = nx1.a;
        PropertyModel.j jVar = nx1.b;
        PropertyModel.l<String> lVar2 = nx1.d;
        PropertyModel.l<CharSequence> lVar3 = nx1.e;
        HashMap mapE = PropertyModel.e(new v62[]{lVar, jVar, nx1.c, lVar2, lVar3});
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        ?? r7 = kx1Var.a;
        gVar.a = r7;
        mapE.put(lVar2, gVar);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = kx1Var.b;
        mapE.put(lVar3, gVar2);
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = kx1Var.e;
        mapE.put(jVar, dVar);
        ?? r2 = kx1Var.h;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = r2;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar, gVar3, mapE);
        this.b = propertyModelP;
        mx1 mx1Var = this.a;
        mx1Var.getClass();
        mx1Var.k = viewInflate.getResources();
        mx1Var.j = propertyModelP;
        PropertyModel.r<View> rVar = di1.h;
        PropertyModel.b bVar = mx1Var.h;
        bVar.e(rVar, viewInflate);
        bVar.e(di1.a, new mx1.a(kx1Var.f));
        bVar.e(di1.b, r7);
        bVar.e(di1.j, kx1Var.c);
        bVar.e(di1.m, kx1Var.d);
        bVar.c(di1.u, kx1Var.g ? 1 : 0);
        mx1Var.i = bVar.a();
        mx1Var.l = 1;
        j.b(this.b, viewInflate, new zf(20));
    }

    public final void b() {
        mx1 mx1Var = this.a;
        mx1Var.j.l(nx1.c, mx1Var.a(mx1Var.f.getHeight()));
        PropertyModel propertyModelA = mx1Var.h.a();
        mx1Var.i = propertyModelA;
        mx1Var.e.j(propertyModelA, mx1Var.l, false);
    }
}

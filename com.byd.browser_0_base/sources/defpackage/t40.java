package defpackage;

import android.app.Activity;
import android.content.res.ColorStateList;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: CustomTabToolbarColorController.java */
/* JADX INFO: loaded from: classes.dex */
public final class t40 {
    public final oj a;
    public final Activity b;
    public final o23 c;
    public final a30 d;
    public final xd3 e;
    public g f;
    public boolean g;

    public t40(oj ojVar, Activity activity, a30 a30Var, o23 o23Var, xd3 xd3Var) {
        this.a = ojVar;
        this.b = activity;
        this.d = a30Var;
        this.c = o23Var;
        this.e = xd3Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [org.chromium.chrome.browser.toolbar.g] */
    /* JADX WARN: Type inference failed for: r7v3, types: [int] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
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
    public final void a() {
        char c;
        int iC;
        ?? A;
        g gVar = this.f;
        if (gVar == null) {
            return;
        }
        gVar.y0 = true;
        Tab tab = this.d.b;
        boolean z = this.g;
        oj ojVar = this.a;
        if (ojVar.N()) {
            if (!ojVar.m().f()) {
                c = tab == null ? (char) 1 : (char) 0;
            }
            c = 2;
        } else {
            js3 js3VarM = ojVar.M();
            if (!(js3VarM != null && js3VarM.g == 4)) {
                if (tab == null || !z) {
                    if (ojVar.m().f()) {
                        c = 2;
                    }
                }
            }
        }
        Activity activity = this.b;
        if (c == 0) {
            iC = this.e.c(tab.j(), tab);
        } else if (c == 1 || c != 2) {
            ojVar.getClass();
            iC = yp.a(activity, ojVar instanceof xx0);
        } else {
            iC = ojVar.m().d();
        }
        if (c != 0) {
            A = 3;
            if (c == 1) {
                A = ojVar instanceof xx0 ? (char) 2 : (char) 3;
            } else if (c == 2) {
                A = z7.h0(iC);
            }
        } else {
            A = rr1.a(iC, activity, ojVar instanceof xx0);
        }
        ColorStateList colorStateListY0 = n3.y0(activity);
        this.f.d(iC, false);
        this.f.e(A, colorStateListY0);
        this.f.y0 = false;
    }
}

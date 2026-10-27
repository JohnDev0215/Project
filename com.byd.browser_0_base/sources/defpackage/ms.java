package defpackage;

import J.N;
import android.view.View;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.ChromeTabbedActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ms implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeTabbedActivity f;

    public /* synthetic */ ms(ChromeTabbedActivity chromeTabbedActivity, int i) {
        this.e = i;
        this.f = chromeTabbedActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [sr] */
    /* JADX WARN: Type inference failed for: r7v0, types: [sr] */
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
    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        final ChromeTabbedActivity chromeTabbedActivity = this.f;
        switch (i) {
            case 0:
                n3 n3Var = ChromeTabbedActivity.S1;
                lh.d(chromeTabbedActivity, null, chromeTabbedActivity.D2().isIncognito());
                return;
            case 1:
                n3 n3Var2 = ChromeTabbedActivity.S1;
                chromeTabbedActivity.getClass();
                TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.initializeToolbarManager", null);
                try {
                    ji3 ji3Var = chromeTabbedActivity.c1;
                    final int i2 = 0;
                    ji3Var.e.g(false).j(ji3Var.f);
                    final int i3 = 1;
                    chromeTabbedActivity.N0.x.l(chromeTabbedActivity.d1, new View.OnClickListener() { // from class: sr
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i4 = i3;
                            ChromeTabbedActivity chromeTabbedActivity2 = chromeTabbedActivity;
                            switch (i4) {
                                case 0:
                                    n3 n3Var3 = ChromeTabbedActivity.S1;
                                    ((a23) chromeTabbedActivity2.G2()).g(false).c();
                                    chromeTabbedActivity2.C2().f(chromeTabbedActivity2.m3().d() == 2 ? 19 : 2);
                                    chromeTabbedActivity2.o1.b(chromeTabbedActivity2, null);
                                    if (((a23) chromeTabbedActivity2.G2()).r()) {
                                        n3.X0("MobileToolbarStackViewNewIncognitoTab");
                                    } else {
                                        n3.X0("MobileToolbarStackViewNewTab");
                                    }
                                    n3.X0("MobileTopToolbarNewTabButton");
                                    n3.X0("MobileNewTabOpened");
                                    zb2.g("Chrome.StartSurface.OpenNewTabPageCount");
                                    break;
                                default:
                                    n3 n3Var4 = ChromeTabbedActivity.S1;
                                    chromeTabbedActivity2.v3();
                                    break;
                            }
                        }
                    }, new View.OnClickListener() { // from class: sr
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i4 = i2;
                            ChromeTabbedActivity chromeTabbedActivity2 = chromeTabbedActivity;
                            switch (i4) {
                                case 0:
                                    n3 n3Var3 = ChromeTabbedActivity.S1;
                                    ((a23) chromeTabbedActivity2.G2()).g(false).c();
                                    chromeTabbedActivity2.C2().f(chromeTabbedActivity2.m3().d() == 2 ? 19 : 2);
                                    chromeTabbedActivity2.o1.b(chromeTabbedActivity2, null);
                                    if (((a23) chromeTabbedActivity2.G2()).r()) {
                                        n3.X0("MobileToolbarStackViewNewIncognitoTab");
                                    } else {
                                        n3.X0("MobileToolbarStackViewNewTab");
                                    }
                                    n3.X0("MobileTopToolbarNewTabButton");
                                    n3.X0("MobileNewTabOpened");
                                    zb2.g("Chrome.StartSurface.OpenNewTabPageCount");
                                    break;
                                default:
                                    n3 n3Var4 = ChromeTabbedActivity.S1;
                                    chromeTabbedActivity2.v3();
                                    break;
                            }
                        }
                    }, new vt(i3, chromeTabbedActivity), new tr(chromeTabbedActivity, i2), null, new ls(chromeTabbedActivity, 4));
                    chromeTabbedActivity.R1();
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                            break;
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            case 2:
                n3 n3Var3 = ChromeTabbedActivity.S1;
                chromeTabbedActivity.getClass();
                TraceEvent traceEventH02 = TraceEvent.H0("ChromeTabbedActivity.initJourneyManager", null);
                try {
                    sk skVar = bq.a;
                    if (N.M09VlOh_("TabEngagementReportingAndroid")) {
                        chromeTabbedActivity.G2();
                        new t31();
                        break;
                    }
                    if (traceEventH02 != null) {
                        traceEventH02.close();
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    if (traceEventH02 != null) {
                        try {
                            traceEventH02.close();
                            break;
                        } catch (Throwable unused2) {
                        }
                    }
                    throw th2;
                }
            default:
                n3 n3Var4 = ChromeTabbedActivity.S1;
                chromeTabbedActivity.g3();
                return;
        }
    }
}

package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ChromeTabCreator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class or extends fz2 {
    public final Activity a;
    public final boolean b;
    public final WindowAndroid c;
    public TabModel d;
    public w13 e;
    public final nw2<jz2> f;
    public final a g;
    public final f9 h;
    public final nw2<y13> i;
    public final nw2<CompositorViewHolder> j;

    /* JADX INFO: compiled from: ChromeTabCreator.java */
    public interface a {
    }

    public or(Activity activity, WindowAndroid windowAndroid, nw2 nw2Var, boolean z, ChromeTabbedActivity.g gVar, g9 g9Var, nw2 nw2Var2, nw2 nw2Var3) {
        this.a = activity;
        this.c = windowAndroid;
        this.f = nw2Var;
        this.b = z;
        this.g = gVar;
        this.h = g9Var;
        this.i = nw2Var2;
        this.j = nw2Var3;
    }

    public static String k(Integer num) {
        switch (num.intValue()) {
            case 0:
                return "Link";
            case 1:
                return "ExternalApp";
            case 2:
                return "ChromeUI";
            case 3:
                return "Restore";
            case 4:
                return "LongressForeground";
            case 5:
                return "LongpressBackground";
            case 6:
                return "Reparenting";
            case 7:
                return "LauncherShortcut";
            case 8:
                return "SpeculativeBackgroundCreation";
            case 9:
                return "BrowserActions";
            case 10:
                return "NewIncognitoTab";
            case 11:
                return "Startup";
            case 12:
                return "StartSurface";
            case 13:
                return "TabGroupUI";
            case 14:
                return "LongpressBackgroundInGroup";
            case 15:
                return "AppWidget";
            case 16:
                return "LongpressIncognito";
            case 17:
                return "RecentTabs";
            case 18:
                return "ReadingList";
            case 19:
                return "TabSwitcherUI";
            default:
                return "TypeUnknown";
        }
    }

    @Override // defpackage.fz2
    public final Tab a(m33 m33Var, ui2 ui2Var, int i, boolean z, int i2) {
        Tab tabA;
        gx gxVar = new gx(22, this.i.get());
        boolean zB = ((x13) this.e).b(3, z);
        e9 e9VarRemove = this.h.remove(i);
        int i3 = 1;
        WindowAndroid windowAndroid = this.c;
        if (e9VarRemove == null || e9VarRemove.d() == null) {
            tabA = null;
            i3 = 2;
        } else {
            d33 d33Var = (d33) e9VarRemove;
            tabA = d33Var.a;
            if (tabA.isIncognito() != z) {
                throw new IllegalStateException("Incognito state mismatch. TabState: " + z + ". Tab: " + tabA.isIncognito());
            }
            bb2 bb2VarB = bb2.b(tabA);
            CompositorViewHolder compositorViewHolder = this.j.get();
            jz2 jz2VarH = h();
            bb2VarB.getClass();
            compositorViewHolder.v();
            Tab tab = bb2VarB.e;
            tab.E(windowAndroid, jz2VarH);
            N.MM6uB79X(tab.c());
            Runnable runnable = d33Var.b;
            if (runnable != null) {
                runnable.run();
            }
            if (tabA.getUrl().i().equals("file")) {
                tabA.z();
            } else if (tabA.f()) {
                tabA.a();
            }
        }
        if (tabA == null) {
            ry2 ry2Var = new ry2();
            ry2Var.b(3);
            ry2Var.g = 2;
            ry2Var.a = i;
            ry2Var.c = gxVar;
            ry2Var.d = z;
            ry2Var.e = windowAndroid;
            ry2Var.j = h();
            ry2Var.k = !zB;
            ry2Var.l = m33Var;
            ry2Var.m = ui2Var;
            tabA = ry2Var.a();
        }
        boolean z2 = this.b;
        if (z == z2) {
            this.d.i(tabA, i2, 3, i3);
            return tabA;
        }
        throw new IllegalStateException("Incognito state mismatch. TabState: " + m33Var.h + ". Creator: " + z2);
    }

    @Override // defpackage.fz2
    public final Tab b(int i, Tab tab, LoadUrlParams loadUrlParams) {
        return j(loadUrlParams, i, tab, null);
    }

    @Override // defpackage.fz2
    public final Tab c(LoadUrlParams loadUrlParams, int i) {
        return i(loadUrlParams, 3, null, i, null);
    }

    @Override // defpackage.fz2
    public final boolean d(Tab tab, WebContents webContents, int i, GURL gurl) {
        boolean z = this.b;
        int id = tab != null ? tab.getId() : -1;
        int i2 = 0;
        if (this.d.w(id)) {
            return false;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabCreator.createTabWithWebContents", null);
        try {
            xa3 xa3VarQ = xa3.q("Android.Tab.CreateNewTabDuration." + k(Integer.valueOf(i)) + ".WithExistingWebContents");
            try {
                int iG = z7.G(this.d, id);
                int i3 = iG != -1 ? iG + 1 : -1;
                boolean zB = ((x13) this.e).b(i, z);
                jz2 jz2VarH = tab == null ? h() : null;
                int i4 = !zB ? 1 : 0;
                ry2 ry2Var = new ry2();
                ry2Var.g = Integer.valueOf(i4);
                ry2Var.b = tab;
                ry2Var.d = z;
                ry2Var.e = this.c;
                ry2Var.b(i);
                ry2Var.i = webContents;
                ry2Var.j = jz2VarH;
                ry2Var.k = !zB;
                TabImpl tabImplA = ry2Var.a();
                if (!zB) {
                    i2 = i == 17 ? 3 : 1;
                }
                this.d.i(tabImplA, i3, i, i2);
                xa3VarQ.close();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return true;
            } catch (Throwable th) {
                try {
                    xa3VarQ.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th2;
        }
    }

    @Override // defpackage.fz2
    public final boolean e() {
        return false;
    }

    @Override // defpackage.fz2
    public final Tab g(int i, String str) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
        loadUrlParams.p = 0L;
        return j(loadUrlParams, i, null, null);
    }

    public final jz2 h() {
        nw2<jz2> nw2Var = this.f;
        if (nw2Var != null) {
            return nw2Var.get();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0209 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01f0 A[Catch: all -> 0x016f, TryCatch #5 {all -> 0x016f, blocks: (B:94:0x01f0, B:96:0x01f5, B:98:0x01fb, B:103:0x020e, B:65:0x013a, B:67:0x0162, B:70:0x0172, B:75:0x018d, B:77:0x0193, B:81:0x01b1, B:82:0x01b9, B:86:0x01c2, B:90:0x01dd), top: B:127:0x00ce }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01f5 A[Catch: all -> 0x016f, TryCatch #5 {all -> 0x016f, blocks: (B:94:0x01f0, B:96:0x01f5, B:98:0x01fb, B:103:0x020e, B:65:0x013a, B:67:0x0162, B:70:0x0172, B:75:0x018d, B:77:0x0193, B:81:0x01b1, B:82:0x01b9, B:86:0x01c2, B:90:0x01dd), top: B:127:0x00ce }] */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.chromium.chrome.browser.tab.Tab i(org.chromium.content_public.browser.LoadUrlParams r20, int r21, org.chromium.chrome.browser.tab.Tab r22, int r23, android.content.Intent r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.or.i(org.chromium.content_public.browser.LoadUrlParams, int, org.chromium.chrome.browser.tab.Tab, int, android.content.Intent):org.chromium.chrome.browser.tab.Tab");
    }

    public final Tab j(LoadUrlParams loadUrlParams, int i, Tab tab, Intent intent) {
        int iR = this.d.r(tab);
        return i(loadUrlParams, i, tab, iR != -1 ? iR + 1 : -1, intent);
    }
}

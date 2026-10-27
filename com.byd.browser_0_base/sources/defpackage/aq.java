package defpackage;

import J.N;
import android.content.Context;
import defpackage.xh3;
import java.util.HashMap;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ChromeContextMenuPopulator.java */
/* JADX INFO: loaded from: classes.dex */
public final class aq implements vz {
    public final Context a;
    public final pz b;
    public final int c;
    public final nw2<ck2> d;
    public final zi0 e;
    public final ContextMenuParams f;
    public xh3.a g;
    public final uz h;
    public Boolean i;

    public aq(pz pzVar, nw2<ck2> nw2Var, int i, zi0 zi0Var, Context context, ContextMenuParams contextMenuParams, uz uzVar) {
        this.b = pzVar;
        this.d = nw2Var;
        this.c = i;
        this.e = zi0Var;
        this.a = context;
        this.f = contextMenuParams;
        this.h = uzVar;
    }

    public static boolean i(GURL gurl) {
        return gurl == null || gurl.k() || gurl.j().equals("about:blank");
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x041b  */
    @Override // defpackage.vz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<android.util.Pair<java.lang.Integer, defpackage.ya1>> a() {
        /*
            Method dump skipped, instruction units count: 1316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aq.a():java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:147:0x04cf  */
    @Override // defpackage.vz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(int r29) {
        /*
            Method dump skipped, instruction units count: 1543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aq.b(int):boolean");
    }

    @Override // defpackage.vz
    public final void c() {
        isIncognito();
        DeviceFormFactor.a(this.a);
        r61.a.a.getClass();
    }

    @Override // defpackage.vz
    public final String d() {
        return ((az2) this.b).a.getTitle();
    }

    @Override // defpackage.vz
    public final void e() {
        Boolean bool = this.i;
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        xe3 xe3VarT = oj2.t(g());
        if (xe3VarT.isInitialized()) {
            xe3VarT.dismissed("IPH_EphemeralTab");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final xa1 f(int i, boolean z) {
        T tA;
        HashMap mapE = PropertyModel.e(qz.c);
        PropertyModel.p pVar = qz.b;
        int i2 = zp.a[i];
        int i3 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i2;
        mapE.put(pVar, dVar);
        PropertyModel.r<CharSequence> rVar = qz.a;
        if (i != 2) {
            Context context = this.a;
            if (i == 8) {
                tA = zp.a(context, i, "Chrome.Contextmenu.OpenInEphemeralTabClicked", z);
            } else if (i == 14) {
                tA = zp.a(context, i, null, z);
            } else if (i != 19) {
                switch (i) {
                    case 21:
                        tA = context.getString(zp.b(context, i), oj2.q(Profile.d()).c().c());
                        break;
                    case 22:
                        tA = zp.a(context, i, "Chrome.ContextMenu.SearchWithGoogleLensClicked", z);
                        break;
                    case 23:
                        tA = zp.a(context, i, "Chrome.ContextMenu.ShopImageWithGoogleLensClicked", z);
                        break;
                    default:
                        tA = context.getString(zp.b(context, i));
                        break;
                }
            } else {
                tA = zp.a(context, i, "Chrome.Contextmenu.OpenImageInEphemeralTabClicked", z);
            }
        } else {
            tA = j70.a(false);
        }
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = tA;
        mapE.put(rVar, gVar);
        return new xa1(2, new PropertyModel(mapE));
    }

    public final Profile g() {
        return Profile.b(((az2) this.b).a.g);
    }

    public final WindowAndroid h() {
        return ((az2) this.b).a.g.V();
    }

    @Override // defpackage.vz
    public final boolean isIncognito() {
        return ((az2) this.b).a();
    }

    public final void j(String str) {
        az2 az2Var = (az2) this.b;
        if (az2Var.a()) {
            return;
        }
        if (this.g == null) {
            this.g = new xh3.a();
        }
        WebContents webContents = az2Var.a.g;
        if (webContents != null) {
            this.g.getClass();
            N.M$ejnyHh(webContents, "ContextMenuAndroid.Shown", str);
        }
    }

    public final void k(int i) {
        if (t61.b()) {
            az2 az2Var = (az2) this.b;
            if (az2Var.a()) {
                return;
            }
            if (this.g == null) {
                this.g = new xh3.a();
            }
            WebContents webContents = az2Var.a.g;
            if (webContents != null) {
                this.g.getClass();
                N.MCnWTXic(webContents, "ContextMenuAndroid.Selected", "Action", i);
            }
        }
    }
}

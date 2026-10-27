package org.chromium.components.media_router;

import J.N;
import defpackage.me1;
import defpackage.sd1;
import defpackage.vd1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.SysUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes.dex */
public class BrowserMediaRouter {
    public long a;
    public final ArrayList b = new ArrayList();
    public final HashMap c = new HashMap();
    public final HashMap d = new HashMap();
    public final HashMap e = new HashMap();

    public class a {
    }

    static {
        new a();
    }

    public BrowserMediaRouter(long j) {
        this.a = j;
    }

    @CalledByNative
    public static BrowserMediaRouter create(long j) {
        return new BrowserMediaRouter(j);
    }

    public final sd1 a(String str) {
        for (sd1 sd1Var : this.b) {
            if (sd1Var.f()) {
                return sd1Var;
            }
        }
        return null;
    }

    @CalledByNative
    public void closeRoute(String str) {
        sd1 sd1Var = (sd1) this.c.get(str);
        if (sd1Var == null) {
            return;
        }
        sd1Var.h();
    }

    @CalledByNative
    public void createRoute(String str, String str2, String str3, String str4, WebContents webContents, int i) {
        sd1 sd1VarA = a(str);
        if (sd1VarA != null) {
            vd1.e.h(webContents);
            webContents.isIncognito();
            sd1VarA.a();
            return;
        }
        String str5 = "No provider supports createRoute with source: " + str + " and sink: " + str2;
        long j = this.a;
        if (j != 0) {
            N.MpDGY7p4(j, this, str5, i);
        }
    }

    @CalledByNative
    public void detachRoute(String str) {
        HashMap map = this.c;
        sd1 sd1Var = (sd1) map.get(str);
        if (sd1Var == null) {
            return;
        }
        sd1Var.b();
        map.remove(str);
    }

    @CalledByNative
    public FlingingControllerBridge getFlingingControllerBridge(String str) {
        sd1 sd1Var = (sd1) this.c.get(str);
        if (sd1Var == null) {
            return null;
        }
        sd1Var.e();
        return null;
    }

    @CalledByNative
    public String getSinkName(String str, int i) {
        ((me1) ((List) this.e.get(str)).get(i)).getClass();
        return "";
    }

    @CalledByNative
    public String getSinkUrn(String str, int i) {
        ((me1) ((List) this.e.get(str)).get(i)).getClass();
        return "urn:x-org.chromium:media:sink:cast-";
    }

    @CalledByNative
    public void joinRoute(String str, String str2, String str3, WebContents webContents, int i) {
        sd1 sd1VarA = a(str);
        if (sd1VarA != null) {
            vd1.e.h(webContents);
            sd1VarA.d();
        } else {
            long j = this.a;
            if (j != 0) {
                N.M9VY0XZb(j, this, "Route not found.", i);
            }
        }
    }

    @CalledByNative
    public void sendStringMessage(String str, String str2) {
        sd1 sd1Var = (sd1) this.c.get(str);
        if (sd1Var == null) {
            return;
        }
        sd1Var.i();
    }

    @CalledByNative
    public boolean startObservingMediaSinks(String str) {
        if (SysUtils.isLowEndDevice()) {
            return false;
        }
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((sd1) it.next()).c();
        }
        return true;
    }

    @CalledByNative
    public void stopObservingMediaSinks(String str) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((sd1) it.next()).g();
        }
        this.e.remove(str);
        this.d.remove(str);
    }

    @CalledByNative
    public void teardown() {
        this.a = 0L;
    }
}

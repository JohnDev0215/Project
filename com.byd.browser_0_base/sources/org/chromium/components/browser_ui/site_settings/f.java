package org.chromium.components.browser_ui.site_settings;

import J.N;
import defpackage.d02;
import defpackage.i00;
import defpackage.i40;
import defpackage.jj0;
import defpackage.n3;
import defpackage.ts3;
import defpackage.ws3;
import defpackage.wy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.BrowserContextHandle;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: Website.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements ws3 {
    public final ts3 e;
    public final ts3 f;
    public b i;
    public jj0 j;
    public i00 k;
    public final HashMap g = new HashMap();
    public final HashMap h = new HashMap();
    public final ArrayList l = new ArrayList();
    public final ArrayList m = new ArrayList();

    /* JADX INFO: compiled from: Website.java */
    public interface a {
        void l();
    }

    public f(ts3 ts3Var, ts3 ts3Var2) {
        this.e = ts3Var;
        this.f = ts3Var2;
    }

    public final void a(Profile profile, a aVar) {
        ArrayList<e> arrayList = this.l;
        i40 i40Var = new i40(new int[]{arrayList.size() + 1}, aVar);
        b bVar = this.i;
        if (bVar != null) {
            String str = bVar.e;
            N.Mks53EZS(profile, str);
            N.M101q5hN(profile, str, i40Var);
            this.i = null;
        } else {
            i40Var.onStorageInfoCleared();
        }
        for (e eVar : arrayList) {
            N.MykycHKg(profile, eVar.e, eVar.f, i40Var);
        }
        arrayList.clear();
    }

    public final int b(f fVar) {
        if (this == fVar) {
            return 0;
        }
        int iCompareTo = (l() ? this.f : this.e).compareTo(fVar.l() ? fVar.f : fVar.e);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (c() == null) {
            return fVar.c() == null ? 0 : -1;
        }
        if (fVar.c() == null) {
            return 1;
        }
        return c().compareTo(fVar.c());
    }

    public final ts3 c() {
        if (l()) {
            return null;
        }
        return this.f;
    }

    public final Integer d(Profile profile, int i) {
        if (f(i) == null) {
            if (e(i) != null) {
                return e(i).h;
            }
            return null;
        }
        d02 d02VarF = f(i);
        String str = d02VarF.g;
        String str2 = d02VarF.f;
        if (str2 == null) {
            str2 = str;
        }
        return Integer.valueOf(N.MrCE1oma(profile, d02VarF.h, str, str2));
    }

    public final wy e(int i) {
        return (wy) this.g.get(Integer.valueOf(i));
    }

    public final d02 f(int i) {
        return (d02) this.h.get(Integer.valueOf(i));
    }

    @Override // defpackage.ws3
    public final String g() {
        String strH = h();
        return strH.indexOf("://") == -1 ? strH : N.M25QTkfm(strH);
    }

    public final String h() {
        return (l() ? this.f : this.e).l();
    }

    @Override // defpackage.ws3
    public final int i() {
        i00 i00Var = this.k;
        if (i00Var == null) {
            return 0;
        }
        return i00Var.e;
    }

    @Override // defpackage.ws3
    public final long j() {
        b bVar = this.i;
        long j = bVar != null ? 0 + bVar.f : 0L;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            j += ((e) it.next()).g;
        }
        return j;
    }

    public final boolean k(int i) {
        d02 d02VarF = f(i);
        if (d02VarF != null && d02VarF.e) {
            return true;
        }
        wy wyVarE = e(i);
        return wyVarE != null && wyVarE.j;
    }

    public final boolean l() {
        ts3 ts3Var;
        return (!this.e.l().equals("*") || (ts3Var = this.f) == null || ts3Var.l().equals("*")) ? false : true;
    }

    public final void m(BrowserContextHandle browserContextHandle, int i, int i2) {
        if (f(i) != null) {
            d02 d02VarF = f(i);
            String str = d02VarF.f;
            if (str == null) {
                str = d02VarF.g;
            }
            N.MKKuVgiF(browserContextHandle, d02VarF.h, d02VarF.g, str, i2);
            return;
        }
        wy wyVarE = e(i);
        ts3 ts3Var = this.e;
        if (i == 25) {
            if (wyVarE == null) {
                wyVarE = new wy(25, ts3Var.h(), 2);
                n(i, wyVarE);
            }
        } else if (i == 2) {
            if (wyVarE == null) {
                wyVarE = new wy(2, ts3Var.h, Integer.valueOf(i2));
                n(i, wyVarE);
            }
            if (i2 == 2) {
                n3.X0("JavascriptContentSetting.EnableBy.SiteSettings");
            } else {
                n3.X0("JavascriptContentSetting.DisableBy.SiteSettings");
            }
        } else if (i == 30) {
            if (wyVarE == null) {
                wyVarE = new wy(30, ts3Var.h, Integer.valueOf(i2));
                n(i, wyVarE);
            }
            if (i2 == 2) {
                n3.X0("SoundContentSetting.MuteBy.SiteSettings");
            } else {
                n3.X0("SoundContentSetting.UnmuteBy.SiteSettings");
            }
        }
        if (wyVarE != null) {
            String str2 = wyVarE.g;
            if (str2 == null) {
                str2 = "*";
            }
            WebsitePreferenceBridge.b(browserContextHandle, wyVarE.e, wyVarE.f, str2, i2);
        }
    }

    public final void n(int i, wy wyVar) {
        this.g.put(Integer.valueOf(i), wyVar);
    }

    @Override // defpackage.ws3
    public final boolean r(String str) {
        return h().contains(str);
    }

    @Override // defpackage.ws3
    public final GURL v() {
        return new GURL(this.e.h());
    }
}

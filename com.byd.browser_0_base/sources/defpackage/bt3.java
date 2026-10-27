package defpackage;

import J.N;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebsiteGroup.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bt3 implements ws3 {
    public final String e;
    public final List<f> f;
    public final long g;
    public final int h;
    public final jj0 i;

    public bt3(String str, List<f> list) {
        this.e = str;
        this.f = list;
        long j = 0;
        for (f fVar : list) {
            j += fVar.j();
            jj0 jj0Var = fVar.j;
            if (jj0Var != null) {
                this.i = jj0Var;
            }
        }
        this.g = j;
        Iterator<f> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().i();
        }
        this.h = i;
    }

    public final boolean a(Profile profile) {
        List<f> list = this.f;
        if (list.isEmpty()) {
            return false;
        }
        Iterator<f> it = list.iterator();
        while (it.hasNext()) {
            if (!N.M9l6T3Dg(profile, it.next().e.h())) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.ws3
    public final String g() {
        return this.e;
    }

    @Override // defpackage.ws3
    public final int i() {
        return this.h;
    }

    @Override // defpackage.ws3
    public final long j() {
        return this.g;
    }

    @Override // defpackage.ws3
    public final boolean r(String str) {
        if (this.e.contains(str)) {
            return true;
        }
        Iterator<f> it = this.f.iterator();
        while (it.hasNext()) {
            if (it.next().h().contains(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.ws3
    public final GURL v() {
        return new GURL("https://" + this.e);
    }
}

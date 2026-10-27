package defpackage;

import android.content.ComponentName;
import defpackage.lk1;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: BookmarkPage.java */
/* JADX INFO: loaded from: classes.dex */
public final class og extends be {
    public eg k;
    public final String l;

    public og(ComponentName componentName, wp2 wp2Var, boolean z, lk1.b bVar) {
        super(bVar);
        eg egVar = new eg(bVar.a(), componentName, false, z, wp2Var, Profile.d());
        this.k = egVar;
        egVar.i.z = this;
        this.l = bVar.a().getResources().getString(R$string.bookmarks);
        c(this.k.f);
    }

    @Override // defpackage.be, defpackage.jk1
    public final void destroy() {
        this.k.f();
        this.k = null;
        super.destroy();
    }

    @Override // defpackage.be, defpackage.jk1
    public final void e(String str) {
        this.j = str;
        this.k.h(str);
    }

    @Override // defpackage.jk1
    public final String getTitle() {
        return this.l;
    }

    @Override // defpackage.jk1
    public final String k() {
        return "bookmarks";
    }
}

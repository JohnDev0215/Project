package defpackage;

import android.app.Activity;
import defpackage.lk1;
import defpackage.qc0;
import defpackage.wc0;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: DownloadPage.java */
/* JADX INFO: loaded from: classes.dex */
public final class jd0 extends be implements qc0.a {
    public final id0 k;
    public uc0 l;
    public final String m;

    /* JADX WARN: Type inference failed for: r3v4, types: [id0, org.chromium.base.ApplicationStatus$c] */
    public jd0(Activity activity, wp2 wp2Var, bi1 bi1Var, final OTRProfileID oTRProfileID, lk1.b bVar) {
        super(bVar);
        Object obj = ThreadUtils.a;
        wc0.a aVar = new wc0.a();
        aVar.e = true;
        aVar.a = oTRProfileID;
        aVar.b = false;
        aVar.f = DownloadUtils.h();
        uc0 uc0VarA = rc0.a(activity, new wc0(aVar), wp2Var, bi1Var);
        this.l = uc0VarA;
        uc0VarA.a.a(this);
        PostTask.c(rh3.a, new z8(uc0VarA, 11, this));
        this.m = activity.getString(R$string.menu_downloads);
        ?? r3 = new ApplicationStatus.c() { // from class: id0
            @Override // org.chromium.base.ApplicationStatus.c
            public final void d(Activity activity2, int i) {
                if (i == 3) {
                    OTRProfileID oTRProfileID2 = oTRProfileID;
                    DownloadUtils.a((oTRProfileID2 == null ? Profile.d() : Profile.d().e(oTRProfileID2)).g());
                }
            }
        };
        this.k = r3;
        ApplicationStatus.f(r3, activity);
        c(this.l.i);
    }

    @Override // qc0.a
    public final void b(String str) {
        if (str.equals(this.j)) {
            return;
        }
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
        loadUrlParams.o = true;
        ((lk1.b) this.e).b(loadUrlParams, false);
    }

    @Override // defpackage.be, defpackage.jk1
    public final void destroy() {
        this.l.a.c(this);
        this.l.a();
        this.l = null;
        ApplicationStatus.i(this.k);
        super.destroy();
    }

    @Override // defpackage.be, defpackage.jk1
    public final void e(String str) {
        this.j = str;
        this.l.b(str);
    }

    @Override // defpackage.jk1
    public final String getTitle() {
        return this.m;
    }

    @Override // defpackage.jk1
    public final String k() {
        return "csacsacascasc@vsdvdsvdvdv%^*dsvdsvvdsvdsv";
    }
}

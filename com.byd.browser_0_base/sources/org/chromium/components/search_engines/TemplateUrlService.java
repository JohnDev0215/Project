package org.chromium.components.search_engines;

import J.N;
import defpackage.rh3;
import defpackage.y70;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class TemplateUrlService {
    public final org.chromium.base.b<b> a = new org.chromium.base.b<>();
    public final org.chromium.base.b<c> b = new org.chromium.base.b<>();
    public long c;

    public class a implements b {
        public final /* synthetic */ Runnable e;

        public a(Runnable runnable) {
            this.e = runnable;
        }

        @Override // org.chromium.components.search_engines.TemplateUrlService.b
        public final void g() {
            TemplateUrlService templateUrlService = TemplateUrlService.this;
            templateUrlService.getClass();
            Object obj = ThreadUtils.a;
            templateUrlService.a.c(this);
            this.e.run();
        }
    }

    public interface b {
        void g();
    }

    public interface c {
        void M();
    }

    public TemplateUrlService(long j) {
        this.c = j;
    }

    @CalledByNative
    public static void addTemplateUrlToList(List<TemplateUrl> list, TemplateUrl templateUrl) {
        list.add(templateUrl);
    }

    @CalledByNative
    public static TemplateUrlService create(long j) {
        return new TemplateUrlService(j);
    }

    public final void a(c cVar) {
        this.b.a(cVar);
    }

    public final boolean b() {
        return N.MsoVJOXN(this.c, this);
    }

    public final TemplateUrl c() {
        if (f()) {
            return (TemplateUrl) N.MxujzkW4(this.c, this);
        }
        return null;
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.c = 0L;
    }

    public final String d(String str, List<String> list) {
        return N.Mweksmrf(this.c, this, str, list == null ? null : (String[]) list.toArray(new String[0]));
    }

    public final boolean e() {
        return N.MWMFuBEz(this.c, this);
    }

    public final boolean f() {
        Object obj = ThreadUtils.a;
        return N.M4Z0aoFH(this.c, this);
    }

    public final boolean g(GURL gurl) {
        Object obj = ThreadUtils.a;
        return N.MF3JCGn0(this.c, this, gurl);
    }

    public final void h(b bVar) {
        Object obj = ThreadUtils.a;
        this.a.a(bVar);
        if (f()) {
            PostTask.c(rh3.a, new y70(this, 24, bVar));
        }
    }

    public final void i(c cVar) {
        this.b.c(cVar);
    }

    public final void j(Runnable runnable) {
        if (f()) {
            runnable.run();
            return;
        }
        h(new a(runnable));
        Object obj = ThreadUtils.a;
        N.MVKcMDBb(this.c, this);
    }

    @CalledByNative
    public final void onTemplateURLServiceChanged() {
        Iterator<c> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((c) aVar.next()).M();
            }
        }
    }

    @CalledByNative
    public final void templateUrlServiceLoaded() {
        Object obj = ThreadUtils.a;
        Iterator<b> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).g();
            }
        }
    }
}

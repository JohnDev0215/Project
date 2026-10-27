package org.chromium.chrome.browser.init;

import defpackage.n3;
import defpackage.nj;
import defpackage.qj;
import defpackage.rh3;
import defpackage.sp;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes.dex */
public class NativeStartupBridge {

    public class a extends n3 {
    }

    public class b implements Runnable {
        public final /* synthetic */ nj e;

        public b(a aVar) {
            this.e = aVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            sp spVarA = sp.a();
            nj njVar = this.e;
            spVarA.c(njVar);
            sp.a().b(true, njVar);
        }
    }

    @CalledByNative
    public static void loadFullBrowser() {
        if (qj.a().g()) {
            return;
        }
        PostTask.c(rh3.a, new b(new a()));
    }
}

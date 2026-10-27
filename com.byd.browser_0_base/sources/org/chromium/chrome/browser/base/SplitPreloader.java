package org.chromium.chrome.browser.base;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import defpackage.C0173a;
import defpackage.i9;
import defpackage.l91;
import defpackage.wm2;
import defpackage.xs0;
import org.chromium.base.BundleUtils;
import org.chromium.base.JNIUtils;
import org.chromium.chrome.browser.base.SplitChromeApplication;

/* JADX INFO: loaded from: classes.dex */
public class SplitPreloader {
    public final wm2<String, b> a = new wm2<>();
    public final Context b;

    public interface a {
    }

    public class b extends i9<Void> {
        public final String h = "chrome";
        public a i;

        public b(SplitChromeApplication.a aVar) {
            this.i = aVar;
        }

        @Override // defpackage.i9
        public final Void b() {
            Context contextO = o();
            if (this.i == null) {
                return null;
            }
            HandlerThread handlerThreadH = C0173a.h("ActivityPreload");
            new Handler(handlerThreadH.getLooper()).post(new l91(contextO, 7, handlerThreadH));
            return null;
        }

        @Override // defpackage.i9
        public final void l(Void r1) {
            p();
        }

        public final Context o() {
            String str = this.h;
            boolean zD = BundleUtils.d(str);
            SplitPreloader splitPreloader = SplitPreloader.this;
            if (!zD) {
                return splitPreloader.b;
            }
            Context contextA = BundleUtils.a(splitPreloader.b, str);
            xs0 xs0Var = xs0.d;
            return xs0Var.c ? contextA.createConfigurationContext(xs0Var.a(contextA)) : contextA;
        }

        public final void p() {
            try {
                g();
            } catch (Exception unused) {
            }
            a aVar = this.i;
            if (aVar != null) {
                Context contextO = o();
                SplitChromeApplication.a aVar2 = (SplitChromeApplication.a) aVar;
                if (!aVar2.a.getClassLoader().equals(contextO.getClassLoader())) {
                    ClassLoader classLoader = contextO.getClassLoader();
                    SplitChromeApplication splitChromeApplication = SplitChromeApplication.this;
                    BundleUtils.f(splitChromeApplication, classLoader);
                    JNIUtils.b = contextO.getClassLoader();
                    splitChromeApplication.i = contextO.getResources();
                }
                this.i = null;
            }
        }
    }

    public SplitPreloader(Context context) {
        this.b = context;
    }
}

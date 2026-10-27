package defpackage;

import J.N;
import android.os.Handler;
import java.lang.ref.WeakReference;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwContentsStatics;
import org.chromium.base.task.PostTask;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: Gp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0123Gp extends AbstractC0929iE {
    public final WeakReference b;
    public final WeakReference c;
    public boolean d;
    public String e;

    public C0123Gp(WebContents webContents, AwContents awContents, AbstractC0511ao abstractC0511ao) {
        super(webContents);
        this.b = new WeakReference(awContents);
        this.c = new WeakReference(abstractC0511ao);
    }

    public final AbstractC0511ao a(String str) {
        AbstractC0511ao abstractC0511ao = (AbstractC0511ao) this.c.get();
        if (abstractC0511ao == null) {
            return null;
        }
        String strA = AwContentsStatics.a();
        if (strA == null || !strA.equals(str)) {
            return abstractC0511ao;
        }
        return null;
    }

    @Override // defpackage.AbstractC0929iE
    public void didFailLoad(boolean z, int i, String str) {
        AbstractC0511ao abstractC0511ao = (AbstractC0511ao) this.c.get();
        if (abstractC0511ao == null) {
            return;
        }
        String strA = AwContentsStatics.a();
        boolean z2 = strA != null && strA.equals(str);
        if (z && !z2 && i == -3) {
            Handler handler = abstractC0511ao.a.d;
            handler.sendMessage(handler.obtainMessage(9, str));
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didFinishLoad(long j, String str, boolean z) {
        if (!z || a(str) == null) {
            return;
        }
        this.e = str;
    }

    @Override // defpackage.AbstractC0929iE
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        final String str = navigationHandle.e;
        int i = navigationHandle.j;
        if (i != 0 && !navigationHandle.g) {
            didFailLoad(navigationHandle.a, i, str);
        }
        if (navigationHandle.f) {
            this.d = true;
            if (navigationHandle.a) {
                AbstractC0511ao abstractC0511ao = (AbstractC0511ao) this.c.get();
                if (abstractC0511ao != null) {
                    if (!navigationHandle.c && !navigationHandle.h && AbstractC0140Ho.a(navigationHandle.b)) {
                        abstractC0511ao.a.b(str);
                    }
                    Integer num = navigationHandle.d;
                    boolean z = num != null && (num.intValue() & 255) == 8;
                    C1839yo c1839yo = abstractC0511ao.a;
                    C1400qo c1400qo = new C1400qo(str, z);
                    Handler handler = c1839yo.d;
                    handler.sendMessage(handler.obtainMessage(13, c1400qo));
                }
                if (!navigationHandle.c) {
                    PostTask.a(AbstractC0538bE.a, new Runnable(this, str) { // from class: Ep
                        public final C0123Gp s;
                        public final String t;

                        {
                            this.s = this;
                            this.t = str;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            C0123Gp c0123Gp = this.s;
                            String str2 = this.t;
                            AwContents awContents = (AwContents) c0123Gp.b.get();
                            if (awContents != null) {
                                C0105Fp c0105Fp = new C0105Fp(c0123Gp, str2);
                                if (awContents.t(0)) {
                                    return;
                                }
                                N.ME4bwr8X(awContents.s, awContents, 0L, c0105Fp);
                            }
                        }
                    }, 0L);
                }
                if (abstractC0511ao == null || !navigationHandle.i) {
                    return;
                }
                abstractC0511ao.a.a(str);
            }
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didStopLoading(String str) {
        if (str.length() == 0) {
            str = "about:blank";
        }
        AbstractC0511ao abstractC0511aoA = a(str);
        if (abstractC0511aoA == null || !str.equals(this.e)) {
            return;
        }
        Handler handler = abstractC0511aoA.a.d;
        handler.sendMessage(handler.obtainMessage(9, str));
        this.e = null;
    }

    @Override // defpackage.AbstractC0929iE
    public void loadProgressChanged(float f) {
        AbstractC0511ao abstractC0511ao = (AbstractC0511ao) this.c.get();
        if (abstractC0511ao == null) {
            return;
        }
        C1839yo c1839yo = abstractC0511ao.a;
        int iRound = Math.round(f * 100.0f);
        Handler handler = c1839yo.d;
        handler.sendMessage(handler.obtainMessage(11, iRound, 0));
    }

    @Override // defpackage.AbstractC0929iE
    public void titleWasSet(String str) {
        AbstractC0511ao abstractC0511ao = (AbstractC0511ao) this.c.get();
        if (abstractC0511ao == null) {
            return;
        }
        abstractC0511ao.g(str, true);
    }
}

package org.chromium.content.browser;

import J.N;
import android.os.SystemClock;
import android.view.KeyEvent;
import defpackage.AbstractC0857gz;
import defpackage.AbstractC1018jt;
import defpackage.C0913hz;
import defpackage.InterfaceC0594cE;
import defpackage.InterfaceC1074kt;
import defpackage.LA;
import defpackage.NB;
import defpackage.QB;
import defpackage.UA;
import java.util.Objects;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.device.gamepad.GamepadList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContentUiEventHandler extends AbstractC1018jt implements InterfaceC1074kt {
    public final WebContentsImpl s;
    public InterfaceC0594cE t;
    public long u;

    public ContentUiEventHandler(WebContents webContents) {
        this.s = (WebContentsImpl) webContents;
        this.u = N.MXL6itCa(this, webContents);
    }

    private boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean zA0;
        Objects.requireNonNull((C0913hz) this.s.S(C0913hz.class, AbstractC0857gz.a));
        if (GamepadList.a(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (!((keyCode == 82 || keyCode == 3 || keyCode == 4 || keyCode == 5 || keyCode == 6 || keyCode == 26 || keyCode == 79 || keyCode == 27 || keyCode == 80 || keyCode == 25 || keyCode == 164 || keyCode == 24) ? false : true)) {
            return this.t.b(keyEvent);
        }
        ImeAdapterImpl imeAdapterImplP = ImeAdapterImpl.P(this.s);
        LA la = imeAdapterImplP.u;
        if (la != null) {
            QB qb = (QB) la;
            UA.a();
            qb.f.post(new NB(qb, keyEvent));
            zA0 = true;
        } else {
            zA0 = imeAdapterImplP.a0(keyEvent);
        }
        if (zA0) {
            return true;
        }
        return this.t.b(keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean onGenericMotionEvent(android.view.MotionEvent r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.ContentUiEventHandler.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    private boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.t.f(i, keyEvent);
    }

    private void scrollBy(float f, float f2) {
        if (f == 0.0f && f2 == 0.0f) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (GestureListenerManagerImpl.O(this.s).z) {
            N.MW$k83qi(this.u, this, jUptimeMillis);
        }
        N.M_1sgTVt(this.u, this, jUptimeMillis, f, f2);
    }

    private void scrollTo(float f, float f2) {
        scrollBy(f - this.s.y.c(), f2 - this.s.y.d());
    }
}

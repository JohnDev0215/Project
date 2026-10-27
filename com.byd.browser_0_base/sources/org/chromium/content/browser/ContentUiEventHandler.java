package org.chromium.content.browser;

import J.N;
import android.os.SystemClock;
import android.view.KeyEvent;
import defpackage.bt;
import defpackage.cs0;
import defpackage.ds0;
import defpackage.f93;
import defpackage.h93;
import defpackage.lk3;
import defpackage.pd;
import defpackage.z7;
import defpackage.za2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.a;
import org.chromium.device.gamepad.GamepadList;

/* JADX INFO: loaded from: classes3.dex */
public class ContentUiEventHandler implements lk3 {
    public final WebContentsImpl e;
    public a.InterfaceC0153a f;
    public final long g;

    public static final class a {
        public static final pd a = new pd(21);
    }

    public ContentUiEventHandler(WebContentsImpl webContentsImpl) {
        this.e = webContentsImpl;
        this.g = N.MXL6itCa(this, webContentsImpl);
    }

    @CalledByNative
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z;
        boolean zM;
        ((cs0) this.e.n0(cs0.class, cs0.a.a)).getClass();
        boolean z2 = false;
        if (GamepadList.c(keyEvent)) {
            GamepadList gamepadList = GamepadList.b.a;
            synchronized (gamepadList.a) {
                if (gamepadList.e) {
                    ds0 ds0VarA = gamepadList.a(keyEvent.getDeviceId());
                    if (ds0VarA != null) {
                        int keyCode = keyEvent.getKeyCode();
                        int scanCode = keyEvent.getScanCode();
                        if (keyCode == 0 && scanCode >= 704 && scanCode <= 719) {
                            keyCode = (scanCode + 188) - 704;
                        }
                        if (GamepadList.c(keyEvent)) {
                            int action = keyEvent.getAction();
                            float[] fArr = ds0VarA.h;
                            if (action == 0) {
                                fArr[keyCode] = 1.0f;
                            } else if (keyEvent.getAction() == 1) {
                                fArr[keyCode] = 0.0f;
                            }
                            ds0VarA.e = keyEvent.getEventTime();
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                }
                z = false;
            }
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        int keyCode2 = keyEvent.getKeyCode();
        if (keyCode2 != 82 && keyCode2 != 3 && keyCode2 != 4 && keyCode2 != 5 && keyCode2 != 6 && keyCode2 != 26 && keyCode2 != 79 && keyCode2 != 27 && keyCode2 != 80 && keyCode2 != 25 && keyCode2 != 164 && keyCode2 != 24 && keyCode2 != 120) {
            z2 = true;
        }
        if (!z2) {
            return this.f.a(keyEvent);
        }
        ImeAdapterImpl imeAdapterImplB = ImeAdapterImpl.b(this.e);
        bt btVar = imeAdapterImplB.g;
        if (btVar != null) {
            f93 f93Var = (f93) btVar;
            z7.c();
            f93Var.e.post(new h93(f93Var, keyEvent));
            zM = true;
        } else {
            zM = imeAdapterImplB.m(keyEvent);
        }
        if (zM) {
            return true;
        }
        return this.f.a(keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x00eb  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r26) {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.ContentUiEventHandler.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @CalledByNative
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.f.c(i, keyEvent);
    }

    @CalledByNative
    public final void scrollBy(float f, float f2) {
        if (f == 0.0f && f2 == 0.0f) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (GestureListenerManagerImpl.e(this.e).o) {
            N.MW$k83qi(this.g, this, jUptimeMillis);
        }
        N.M_1sgTVt(this.g, this, jUptimeMillis, f, f2);
    }

    @CalledByNative
    public final void scrollTo(float f, float f2) {
        WebContentsImpl webContentsImpl = this.e;
        za2 za2Var = webContentsImpl.l;
        float fA = za2Var.a(za2Var.a);
        za2 za2Var2 = webContentsImpl.l;
        scrollBy(f - fA, f2 - za2Var2.a(za2Var2.b));
    }
}

package defpackage;

import J.N;
import android.hardware.display.DisplayManager;
import android.view.Display;
import org.chromium.ui.display.DisplayAndroidManager;

/* JADX INFO: renamed from: iO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0939iO implements DisplayManager.DisplayListener {
    public final /* synthetic */ DisplayAndroidManager s;

    public C0939iO(DisplayAndroidManager displayAndroidManager, AbstractC0883hO abstractC0883hO) {
        this.s = displayAndroidManager;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i) {
        C0994jO c0994jO = (C0994jO) this.s.c.get(i);
        Display display = ((DisplayManager) Is.a.getSystemService("display")).getDisplay(i);
        if (c0994jO == null || display == null) {
            return;
        }
        c0994jO.f(display);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i) {
        DisplayAndroidManager displayAndroidManager = this.s;
        if (i == displayAndroidManager.b || ((AbstractC0827gO) displayAndroidManager.c.get(i)) == null) {
            return;
        }
        DisplayAndroidManager displayAndroidManager2 = this.s;
        long j = displayAndroidManager2.a;
        if (j != 0) {
            N.MyzQIqd_(j, displayAndroidManager2, i);
        }
        this.s.c.remove(i);
    }
}

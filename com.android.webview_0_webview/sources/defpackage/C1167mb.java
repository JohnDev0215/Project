package defpackage;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: mb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1167mb implements InterfaceC0264On {
    public final /* synthetic */ WebViewChromium s;

    public C1167mb(WebViewChromium webViewChromium, B9 b9) {
        this.s = webViewChromium;
    }

    @Override // defpackage.InterfaceC0264On
    public int a() {
        return this.s.t.super_getScrollBarStyle();
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean b(KeyEvent keyEvent) {
        return this.s.t.super_dispatchKeyEvent(keyEvent);
    }

    @Override // defpackage.InterfaceC0264On
    public void c(Intent intent, int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            C1090l8.f(this.s.t, intent, i);
            return;
        }
        try {
            View.class.getMethod("startActivityForResult", Intent.class, Integer.TYPE).invoke(this.s.s, intent, Integer.valueOf(i));
        } catch (Exception e) {
            throw new RuntimeException("Invalid reflection", e);
        }
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean d(MotionEvent motionEvent) {
        return this.s.t.super_onGenericMotionEvent(motionEvent);
    }

    @Override // defpackage.InterfaceC0264On
    public void e(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        this.s.t.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean f(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override // defpackage.InterfaceC0264On
    public void g(Configuration configuration) {
    }

    @Override // defpackage.InterfaceC0264On
    public void h(int i, int i2) {
        this.s.t.super_scrollTo(i, i2);
    }

    @Override // defpackage.InterfaceC0594cE
    public void onScrollChanged(int i, int i2, int i3, int i4) {
    }

    @Override // defpackage.InterfaceC0264On
    public void setMeasuredDimension(int i, int i2) {
        this.s.t.setMeasuredDimension(i, i2);
    }
}

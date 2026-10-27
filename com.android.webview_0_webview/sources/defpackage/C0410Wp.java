package defpackage;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.MotionEvent;

/* JADX INFO: renamed from: Wp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0410Wp implements InterfaceC0264On {
    public final /* synthetic */ C0428Xp s;

    public C0410Wp(C0428Xp c0428Xp, AbstractC0392Vp abstractC0392Vp) {
        this.s = c0428Xp;
    }

    @Override // defpackage.InterfaceC0264On
    public int a() {
        return super/*android.widget.FrameLayout*/.getScrollBarStyle();
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean b(KeyEvent keyEvent) {
        return super/*android.widget.FrameLayout*/.dispatchKeyEvent(keyEvent);
    }

    @Override // defpackage.InterfaceC0264On
    public void c(Intent intent, int i) {
        throw new RuntimeException("FullScreenView InternalAccessAdapter shouldn't call startActivityForResult. See AwContents#startActivityForResult");
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean d(MotionEvent motionEvent) {
        return super/*android.widget.FrameLayout*/.onGenericMotionEvent(motionEvent);
    }

    @Override // defpackage.InterfaceC0264On
    public void e(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        this.s.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean f(int i, KeyEvent keyEvent) {
        return super/*android.widget.FrameLayout*/.onKeyUp(i, keyEvent);
    }

    @Override // defpackage.InterfaceC0264On
    public void g(Configuration configuration) {
    }

    @Override // defpackage.InterfaceC0264On
    public void h(int i, int i2) {
        super/*android.widget.FrameLayout*/.scrollTo(i, i2);
    }

    @Override // defpackage.InterfaceC0594cE
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        this.s.onScrollChanged(i, i2, i3, i4);
    }

    @Override // defpackage.InterfaceC0264On
    public void setMeasuredDimension(int i, int i2) {
        this.s.setMeasuredDimension(i, i2);
    }
}

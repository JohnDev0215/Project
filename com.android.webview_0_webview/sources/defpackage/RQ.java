package defpackage;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RQ implements View.OnTouchListener {
    public final /* synthetic */ UQ a;

    public RQ(UQ uq) {
        this.a = uq;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        boolean zA = this.a.a();
        if (actionMasked == 0 || actionMasked == 5) {
            this.a.e = true;
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.a.e = false;
        }
        if (this.a.a() != zA) {
            ((HP) this.a.c).c();
        }
        return false;
    }
}

package defpackage;

import android.content.Context;
import android.graphics.RectF;
import android.view.MotionEvent;
import defpackage.zu2;

/* JADX INFO: compiled from: AreaGestureEventFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class h8 extends ks0 {
    public final RectF p;
    public boolean q;

    public h8(Context context, zu2.b bVar) {
        super(context, bVar);
        RectF rectF = new RectF();
        this.p = rectF;
        rectF.setEmpty();
    }

    @Override // org.chromium.chrome.browser.layouts.EventFilter
    public final boolean b(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 0) {
            this.q = false;
        }
        RectF rectF = this.p;
        float x = motionEvent.getX();
        float f = this.a;
        if (!rectF.contains(x * f, motionEvent.getY() * f)) {
            return false;
        }
        if (motionEvent.getActionMasked() == 0) {
            this.q = true;
        } else if (!this.q) {
            return false;
        }
        return true;
    }

    @Override // defpackage.ks0, org.chromium.chrome.browser.layouts.EventFilter
    public final void c(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 0) {
            this.q = false;
        }
        super.c(motionEvent);
    }
}

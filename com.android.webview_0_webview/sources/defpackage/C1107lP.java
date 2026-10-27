package defpackage;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import java.util.Iterator;

/* JADX INFO: renamed from: lP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1107lP implements PopupWindow.OnDismissListener {
    public final /* synthetic */ ViewOnTouchListenerC1218nP s;

    public C1107lP(ViewOnTouchListenerC1218nP viewOnTouchListenerC1218nP) {
        this.s = viewOnTouchListenerC1218nP;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        ViewOnTouchListenerC1218nP viewOnTouchListenerC1218nP = this.s;
        if (viewOnTouchListenerC1218nP.t) {
            return;
        }
        viewOnTouchListenerC1218nP.d.removeCallbacks(viewOnTouchListenerC1218nP.h);
        Iterator it = this.s.k.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                break;
            } else {
                ((PopupWindow.OnDismissListener) ws.next()).onDismiss();
            }
        }
        ViewTreeObserverOnGlobalLayoutListenerC1712wP viewTreeObserverOnGlobalLayoutListenerC1712wP = (ViewTreeObserverOnGlobalLayoutListenerC1712wP) this.s.g;
        viewTreeObserverOnGlobalLayoutListenerC1712wP.w.removeOnAttachStateChangeListener(viewTreeObserverOnGlobalLayoutListenerC1712wP);
        ViewTreeObserver viewTreeObserver = viewTreeObserverOnGlobalLayoutListenerC1712wP.x;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserverOnGlobalLayoutListenerC1712wP.x.removeOnGlobalLayoutListener(viewTreeObserverOnGlobalLayoutListenerC1712wP);
            viewTreeObserverOnGlobalLayoutListenerC1712wP.x.removeOnPreDrawListener(viewTreeObserverOnGlobalLayoutListenerC1712wP);
        }
        viewTreeObserverOnGlobalLayoutListenerC1712wP.x = null;
        viewTreeObserverOnGlobalLayoutListenerC1712wP.t = null;
    }
}
